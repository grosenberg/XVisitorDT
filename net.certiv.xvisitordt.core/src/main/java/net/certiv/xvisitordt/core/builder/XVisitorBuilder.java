package net.certiv.xvisitordt.core.builder;

import java.io.IOException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
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
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
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
import org.eclipse.text.edits.TextEdit;

import net.certiv.antlr.xvisitor.Tool;
import net.certiv.dsl.core.DslCore;
import net.certiv.dsl.core.builder.DslBuilder;
import net.certiv.dsl.core.log.Log;
import net.certiv.dsl.core.model.DslSourceRoot;
import net.certiv.dsl.core.model.ICodeUnit;
import net.certiv.dsl.core.parser.DslProblemCollector;
import net.certiv.dsl.core.preferences.consts.Builder;
import net.certiv.dsl.core.util.CoreUtil;
import net.certiv.dsl.core.util.antlr.AntlrUtil;
import net.certiv.dsl.core.util.eclipse.DynamicLoader;
import net.certiv.xvisitordt.core.XVisitorCore;

public class XVisitorBuilder extends DslBuilder {

	private static final IStatus FAIL_STATUS = new Status(IStatus.CANCEL, XVisitorCore.PLUGIN_ID,
			"XVisitor build failed.");

	public XVisitorBuilder() {
		super();
	}

	@Override
	public DslCore getDslCore() {
		return XVisitorCore.getDefault();
	}

	@Override
	public IStatus buildSourceModules(IProgressMonitor monitor, int ticks, List<ICodeUnit> units) throws CoreException {

		if (units.isEmpty()) return Status.OK_STATUS;
		try {
			monitor.beginTask("XVisitor Build", WORK_BUILD);
			for (ICodeUnit unit : units) {
				DslProblemCollector collector = unit.getParseRecord().collector;
				if (collector != null) collector.beginCollecting();
				compileGrammar(unit, CoreUtil.subMonitorFor(monitor, WORK_BUILD));
				if (collector != null) collector.endCollecting();
			}
			return Status.OK_STATUS;

		} finally {
			monitor.done();
		}
	}

	private void compileGrammar(ICodeUnit unit, IProgressMonitor monitor) {
		if (unit != null && unit.getElementName().endsWith(".xv")) {

			IFile file = (IFile) unit.getResource();
			String srcFile = file.getLocation().toString();
			IPath output = determineBuildPath(unit);
			if (output == null) return;

			// Log.info(this, "Build [" + srcFile + "]");
			// Log.info(this, "Output [" + output + "]");

			List<String> srcFiles = new ArrayList<>();
			srcFiles.add(srcFile);
			monitor.worked(1);
			Job buildJob = new Job("XVisitor Builder") {

				@Override
				protected IStatus run(IProgressMonitor monitor) {

					// install a target specific project classloader
					Thread thread = Thread.currentThread();
					ClassLoader parent = thread.getContextClassLoader();

					boolean ok = true;
					try (DynamicLoader loader = DynamicLoader.create(unit.getProject(), parent)) {
						thread.setContextClassLoader(loader);

						Tool tool = new Tool();
						tool.removeListeners();
						tool.addListener(new ToolErrorListener(unit.getParseRecord()));
						tool.setLevel("warn");
						tool.setLibDirectory(output.toString());
						tool.setOutputDirectory(output.toString());
						tool.setGrammarFiles(srcFiles);
						monitor.worked(1);

						try {
							tool.genModel();
							monitor.worked(1);
						} catch (Exception | Error e) {
							ok = false;
							Log.error(this, "XVisitor build failed: " + e.getMessage());
							Log.error(this, " - Src Files: " + srcFiles);
							Log.error(this, " - Lib Dir : " + output);
							Log.error(this, " - Out Dir : " + output);

							try (URLClassLoader urlLoader = (URLClassLoader) thread.getContextClassLoader()) {
								for (URL url : urlLoader.getURLs()) {
									Log.error(this, " - Classpath: " + url.getPath());
								}
							} catch (IOException e1) {}
						}

					} catch (IOException e) {}

					thread.setContextClassLoader(parent);
					postCompileCleanup(unit, output, monitor);
					monitor.worked(1);

					if (!ok) return FAIL_STATUS;
					return Status.OK_STATUS;
				}
			};

			// finalize and schedule job
			buildJob.setPriority(Job.BUILD);
			buildJob.setSystem(true);
			buildJob.schedule();
		}
	}

	private IPath determineBuildPath(ICodeUnit unit) {
		if (unit != null) {
			DslSourceRoot srcRoot = unit.getSourceRoot();
			String pkg = AntlrUtil.resolvePackageName(unit);

			if (srcRoot.isNativeSourceRoot()) {
				if (pkg != null && !pkg.isEmpty()) {
					Path path = new Path(pkg.replaceAll("\\.", "/"));
					return srcRoot.getLocation().append(path);
				}
				return null;

			} else if (srcRoot.isExtraSourceRoot()) {
				IPath output = unit.getDslProject().getLocation().append("target/generated-sources/antlr4/");
				if (pkg != null && !pkg.isEmpty()) {
					Path path = new Path(pkg.replaceAll("\\.", "/"));
					return output.append(path);

				} else {
					IPath path = unit.getParent().getLocation().makeRelativeTo(srcRoot.getLocation());
					return output.append(path);
				}
			}
		}
		return null;
	}

	private void postCompileCleanup(ICodeUnit unit, IPath output, IProgressMonitor monitor) {
		if (!unit.exists()) {
			Log.info(this, "Compile produced no file[file=" + unit.getPath() + "]");
			return;
		}
		IProject project = unit.getProject();
		IContainer folder = ResourcesPlugin.getWorkspace().getRoot().getContainerForLocation(output);

		// refresh directory
		if (getPrefs().getBoolean(project, Builder.BUILDER_REFRESH)) {
			boolean markDerived = getPrefs().getBoolean(project, Builder.BUILDER_MARK_DERIVED);
			doBuilderRefresh(unit, folder, markDerived, monitor);
		}
		// format all
		if (getPrefs().getBoolean(project, Builder.BUILDER_FORMAT)) {
			doBuilderFormat(unit, folder, monitor);
		}
		// organize imports
		if (getPrefs().getBoolean(project, Builder.BUILDER_ORGANIZE)) {
			doBuilderOrganizeImports(unit, folder, monitor);
		}
	}

	private void doBuilderRefresh(ICodeUnit unit, IContainer folder, boolean markDerived, IProgressMonitor monitor) {
		try {
			if (folder != null) {
				folder.refreshLocal(IResource.DEPTH_ONE, monitor);
				if (markDerived) {
					// and set generated files as derived resources
					String name = unit.getElementName();
					int dot = name.lastIndexOf('.');
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
		// Log.info(this, "Workspace refreshed");
	}

	private void doBuilderFormat(ICodeUnit unit, IContainer folder, IProgressMonitor monitor) {
		// Log.info(this, "Formatting...");
		IProject project = unit.getProject();
		IJavaProject javaProject = JavaCore.create(project);
		Map<String, String> options = javaProject.getOptions(true);
		CodeFormatter formatter = ToolFactory.createCodeFormatter(options);

		try {
			ICompilationUnit[] cus = getCompilationUnits(unit.getResource(), folder);
			for (ICompilationUnit cu : cus) {
				monitor.worked(1);
				String content = cu.getSource();
				TextEdit textEdit = formatter.format(CodeFormatter.K_COMPILATION_UNIT, content, 0, content.length(), 0,
						null);

				if (textEdit != null) {
					IDocument document = new Document();
					document.set(content);
					textEdit.apply(document);
					IPackageFragment pack = (IPackageFragment) cu.getParent();
					String name = cu.getElementName();
					ICompilationUnit cuFormatted = pack.createCompilationUnit(name, document.get(), true, monitor);
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
			ICompilationUnit[] cus = getCompilationUnits(unit.getResource(), folder);
			for (ICompilationUnit cu : cus) {
				OrganizeImportsOperation op = new OrganizeImportsOperation(cu, null, true, true, true, query);
				op.run(monitor);
			}
		} catch (OperationCanceledException e) {
			Log.warn(this, "Ambiguous imports, organization cancelled");
		} catch (Exception e) {
			Log.warn(this, "Failed to Organize imports");
		}
		monitor.worked(1);
	}

	private ICompilationUnit[] getCompilationUnits(IResource resource, IContainer folder) {
		ArrayList<ICompilationUnit> cuList = new ArrayList<>();
		IResource[] children = null;
		try {
			children = folder.members();
		} catch (CoreException e) {}

		for (IResource child : children) {
			try {
				if (child instanceof IFile) {
					ICompilationUnit cu = JavaCore.createCompilationUnitFrom((IFile) child);
					if (cu != null) cuList.add(cu);
				}
			} catch (IllegalArgumentException e) {}
		}
		return cuList.toArray(new ICompilationUnit[cuList.size()]);
	}
}
