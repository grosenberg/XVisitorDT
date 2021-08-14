/*******************************************************************************
 * Copyright (c) 2012, 2020 Certiv Analytics.
 *
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package net.certiv.xvisitor.dt.core.builder;

import java.io.IOException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.OperationCanceledException;
import org.eclipse.core.runtime.Status;
import org.eclipse.jdt.core.ICompilationUnit;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.IPackageFragment;
import org.eclipse.jdt.core.ISourceRange;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.core.ToolFactory;
import org.eclipse.jdt.core.formatter.CodeFormatter;
import org.eclipse.jdt.core.manipulation.OrganizeImportsOperation;
import org.eclipse.jdt.core.manipulation.OrganizeImportsOperation.IChooseImportQuery;
import org.eclipse.jdt.core.search.TypeNameMatch;
import org.eclipse.jface.text.Document;
import org.eclipse.jface.text.IDocument;
import org.eclipse.swt.widgets.Display;
import org.eclipse.text.edits.TextEdit;

import net.certiv.antlr.xvisitor.Tool;
import net.certiv.common.log.Log;
import net.certiv.common.util.Chars;
import net.certiv.dsl.core.DslCore;
import net.certiv.dsl.core.builder.Cause;
import net.certiv.dsl.core.builder.DslBuilder;
import net.certiv.dsl.core.console.CS;
import net.certiv.dsl.core.model.ICodeUnit;
import net.certiv.dsl.core.model.ModelException;
import net.certiv.dsl.core.parser.DslParseRecord;
import net.certiv.dsl.core.parser.problems.ProblemCollector;
import net.certiv.dsl.core.preferences.consts.Builder;
import net.certiv.dsl.core.util.CoreUtil;
import net.certiv.dsl.jdt.util.DynamicLoader;
import net.certiv.xvisitor.dt.core.XVisitorCore;
import net.certiv.xvisitor.dt.core.console.Aspect;

public class XVisitorBuilder extends DslBuilder {

	private static final String TASK = "XVisitor build";
	private static final Comparator<URL> URLComp = new Comparator<>() {

		@Override
		public int compare(URL o1, URL o2) {
			return o1.toString().compareToIgnoreCase(o1.toString());
		}
	};

	public static final Comparator<ICodeUnit> NameComp = Comparator.comparing(ICodeUnit::getElementName);

	public XVisitorBuilder() {
	}

	@Override
	public DslCore getDslCore() {
		return XVisitorCore.getDefault();
	}

	@Override
	protected String taskId() {
		return TASK;
	}

	@Override
	protected IStatus buildUnits(List<ICodeUnit> units, IProgressMonitor monitor, int ticks)
			throws CoreException {
		if (units.isEmpty()) return Status.OK_STATUS;
		try {
			monitor.beginTask(TASK, WORK_BUILD);
			units.sort(NameComp);
			for (ICodeUnit unit : units) {
				compileGrammar(unit, CoreUtil.subMonitorFor(monitor, WORK_BUILD));
			}
			return Status.OK_STATUS;

		} finally {
			monitor.done();
		}
	}

	private class IsoBuilder implements Runnable {

		private ICodeUnit unit;
		private IProgressMonitor monitor;

		public IsoBuilder(ICodeUnit unit, IProgressMonitor monitor) {
			this.unit = unit;
			this.monitor = monitor;
		}

		@Override
		public void run() {
			if (!lock(unit)) return;

			try {
				String displayname = srcName(unit, false);
				IPath pathname = unit.getPath();
				String location = unit.getLocation().toString();

				DslParseRecord record = unit.getDefaultParseRecord();
				if (!record.hasTree()) {
					report(CS.ERROR, Cause.UNIT_ERR, srcName(unit, false),
							"reconciler produced no parse tree");
					CoreUtil.showStatusLineMessage("Skipped  %s", pathname);
					return;
				}

				IPath output = null;
				try {
					output = BuildUtil.resolveOutputPath(record);
				} catch (ModelException ex) {
					report(CS.ERROR, Cause.SRC_ERRS, ex.getMessage(), displayname);
				}
				monitor.worked(1);

				if (output == null) {
					report(CS.ERROR, Cause.PATH_ERR, pathname);
					CoreUtil.showStatusLineMessage("No output path for %s", pathname);
					return;
				}

				IPath dest = output.makeRelativeTo(pathname); // XXX: fix?
				report(CS.INFO, Cause.BUILD, "Start", displayname, dest);
				monitor.worked(1);

				Throwable err = null;
				Thread thread = Thread.currentThread();
				ClassLoader parent = thread.getContextClassLoader();
				try (DynamicLoader loader = DynamicLoader.create(unit.getProject(), parent)) {
					thread.setContextClassLoader(loader);

					Tool tool = new Tool();
					tool.removeListeners();
					tool.addListener(new ToolErrorListener(unit.getDefaultParseRecord()));
					tool.setGrammarFiles(location);
					tool.setLevel("warn");
					tool.setOutputDirectory(output.toString());

					ProblemCollector collector = record.getCollector();
					monitor.worked(1);

					try {
						collector.beginCollecting(unit.getResource(), record.markerId);
						tool.genModel();

					} catch (Exception | Error e) {
						err = e;
						report(CS.ERROR, Cause.BUILD_ERR, e.getMessage(), displayname, dest);
						report(CS.INFO, Cause.BUILD_ERR, "dump", "Src", location);
						report(CS.INFO, Cause.BUILD_ERR, "dump", "out", output);

						try (URLClassLoader urlLoader = (URLClassLoader) thread.getContextClassLoader()) {
							URL[] urls = urlLoader.getURLs();
							Arrays.sort(urls, URLComp);
							for (URL url : urls) {
								report(CS.INFO, Cause.LOADER_URL, url);
							}
						} catch (IOException ex) {}

					} finally {
						collector.endCollecting();
						monitor.worked(1);
					}

				} catch (Exception e) {
					report(CS.ERROR, Cause.LOADER_ERR1, e.getMessage());

				} finally {
					thread.setContextClassLoader(parent);
				}

				if (err != null || record.hasErrors()) {
					CoreUtil.showStatusLineMessage("Build error %s", pathname);
					int cnt = record.getErrorCnt() + record.getWarningCnt();
					if (cnt > 0) report(CS.ERROR, Cause.SRC_PRBM, cnt, displayname);
					if (err != null) report(CS.ERROR, Cause.SRC_ERRS, err.getMessage(), displayname);

				} else {
					String msg = "Built " + pathname.toString();
					CoreUtil.showStatusLineMessage(msg, false);
					report(CS.INFO, Cause.BUILT, displayname, dest);
					postCompileCleanup(unit, output, monitor);
				}

				monitor.worked(1);

			} finally {
				unit.unlock();
			}
		}
	}

	private void compileGrammar(ICodeUnit unit, IProgressMonitor monitor) throws ModelException {
		Display display = CoreUtil.getStandardDisplay();
		if (display != null) {
			display.syncExec(new IsoBuilder(unit, monitor));
		}
	}

	@Override
	protected void report(CS kind, Cause cause, Object... args) {
		getDslCore().consoleAppend(Aspect.BUILDER, kind, cause.toString(), args);
	}

	@Override
	protected String destPackage(ICodeUnit unit) {
		return BuildUtil.grammarDefinedPackage(unit.getDefaultParseRecord());
	}

	// ----

	private void postCompileCleanup(ICodeUnit unit, IPath output, IProgressMonitor monitor) {
		if (!unit.exists()) {
			Log.error(this, "Compile produced no file[file=" + unit.getPath() + "]");
			return;
		}
		IProject project = unit.getProject();
		IContainer folder = ResourcesPlugin.getWorkspace().getRoot().getContainerForLocation(output);

		// refresh directory
		if (store.getBoolean(project, Builder.BUILDER_REFRESH)) {
			boolean markDerived = store.getBoolean(project, Builder.BUILDER_MARK_DERIVED);
			doBuilderRefresh(unit, folder, markDerived, monitor);
		}
		// format all
		if (store.getBoolean(project, Builder.BUILDER_FORMAT)) {
			doBuilderFormat(unit, folder, monitor);
		}
		// organize imports
		if (store.getBoolean(project, Builder.BUILDER_ORGANIZE)) {
			doBuilderOrganizeImports(unit, folder, monitor);
		}
	}

	private void doBuilderRefresh(ICodeUnit unit, IContainer folder, boolean markDerived,
			IProgressMonitor monitor) {
		try {
			if (folder != null) {
				folder.refreshLocal(IResource.DEPTH_ONE, monitor);
				if (markDerived) {
					// and set generated files as derived resources
					String name = unit.getElementName();
					int dot = name.lastIndexOf(Chars.DOT);
					name = name.substring(0, dot);
					String ext = name.substring(dot + 1);
					for (IResource res : folder.members()) {
						if (res.getType() == IResource.FILE) {
							if (res.getFileExtension().equals("xv")) continue;
							if (res.getName().equals(name + "Visitor." + ext)) {
								res.setDerived(true, monitor);
							}
						}
					}
				}
			} else {
				Log.error(this, "Failed to determine build folder; refreshing all");
				IProject project = unit.getProject();
				project.refreshLocal(IResource.DEPTH_INFINITE, monitor);
			}
		} catch (CoreException e) {
			Log.error(this, "Failed to refresh");
		}
		monitor.worked(1);
	}

	private void doBuilderFormat(ICodeUnit unit, IContainer folder, IProgressMonitor monitor) {
		IProject project = unit.getProject();
		IJavaProject javaProject = JavaCore.create(project);
		Map<String, String> options = javaProject.getOptions(true);
		CodeFormatter formatter = ToolFactory.createCodeFormatter(options);

		try {
			for (ICompilationUnit cu : getCompilationUnits(unit.getResource(), folder)) {
				monitor.worked(1);
				String content = cu.getSource();
				TextEdit textEdit = formatter.format(CodeFormatter.K_COMPILATION_UNIT, content, 0,
						content.length(), 0, null);

				if (textEdit != null) {
					IDocument document = new Document();
					document.set(content);
					textEdit.apply(document);
					IPackageFragment pack = (IPackageFragment) cu.getParent();
					String name = cu.getElementName();
					ICompilationUnit cuFormatted = pack.createCompilationUnit(name, document.get(), true,
							monitor);
					cuFormatted.save(monitor, true);
				}
			}
		} catch (Exception e) {
			Log.error(this, "Failed to Format");
		}
		monitor.worked(1);
	}

	private void doBuilderOrganizeImports(ICodeUnit unit, IContainer folder, IProgressMonitor monitor) {
		IChooseImportQuery query = new IChooseImportQuery() {

			@Override
			public TypeNameMatch[] chooseImports(TypeNameMatch[][] openChoices, ISourceRange[] ranges) {
				return null;
			}
		};

		try {
			for (ICompilationUnit cu : getCompilationUnits(unit.getResource(), folder)) {
				OrganizeImportsOperation op = new OrganizeImportsOperation(cu, null, true, true, true, query);
				op.run(monitor);
			}
		} catch (OperationCanceledException e) {
			Log.debug(this, "Ambiguous imports, organization skipped");
		} catch (Exception e) {
			Log.warn(this, "Failed to Organize imports");
		}
		monitor.worked(1);
	}

	private List<ICompilationUnit> getCompilationUnits(IResource resource, IContainer folder) {
		List<ICompilationUnit> units = new ArrayList<>();
		IResource[] resources = {};
		try {
			resources = folder.members();
		} catch (CoreException e) {}

		for (IResource res : resources) {
			try {
				if (res instanceof IFile) {
					ICompilationUnit cu = JavaCore.createCompilationUnitFrom((IFile) res);
					if (cu != null) units.add(cu);
				}
			} catch (IllegalArgumentException e) {}
		}
		return units;
	}
}
