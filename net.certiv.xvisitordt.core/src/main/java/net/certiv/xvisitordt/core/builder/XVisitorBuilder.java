package net.certiv.xvisitordt.core.builder;

import java.net.MalformedURLException;
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
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.jdt.core.ICompilationUnit;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.IPackageFragment;
import org.eclipse.jdt.core.IPackageFragmentRoot;
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
import net.certiv.dsl.core.model.DslModelManager;
import net.certiv.dsl.core.model.ICodeUnit;
import net.certiv.dsl.core.preferences.consts.Builder;
import net.certiv.dsl.core.util.CoreUtil;
import net.certiv.dsl.core.util.Log;
import net.certiv.dsl.core.util.antlr.AntlrUtil;
import net.certiv.dsl.core.util.eclipse.DynamicLoader;
import net.certiv.dsl.core.util.eclipse.JdtUtil;
import net.certiv.xvisitordt.core.XVisitorCore;

public class XVisitorBuilder extends DslBuilder {

	private static final IStatus FAIL_STATUS = new Status(IStatus.CANCEL, XVisitorCore.PLUGIN_ID,
			"XVisitor build failed.");
	private String prefix;

	public XVisitorBuilder() {
		super();

		prefix = getDslCore().getProblemMakerId(XVisitorCore.DSL_NAME);
	}

	@Override
	public DslCore getDslCore() {
		return XVisitorCore.getDefault();
	}

	@Override
	public boolean isBuildAllowed(IProject project, IResource res) {
		if (requireCurrentProject() && !inCurrentProject(res)) return false;

		boolean inSrc = requireSourcePath() && inJavaSourceFolder(res);
		boolean inSpc = requireSpecialPath() && inSpecialFolder(res);
		if (inSrc || inSpc) return true;
		return false;
	}

	/** whether in a Java source folder */
	protected boolean inJavaSourceFolder(IResource res) {
		List<IPackageFragmentRoot> folders = JdtUtil.getJavaSourceFolders(res.getProject());
		for (IPackageFragmentRoot folder : folders) {
			if (folder.getPath().isPrefixOf(res.getFullPath())) return true;
		}
		return false;
	}

	// allow non-source path grammars?
	private boolean inSpecialFolder(IResource res) {
		return false;
	}

	@Override
	public IStatus buildSourceModules(IProgressMonitor monitor, int ticks, List<IFile> modules) throws CoreException {
		if (modules.isEmpty()) return Status.OK_STATUS;

		try {
			monitor.beginTask("XVisitor Build", WORK_BUILD);
			for (IFile module : modules) {
				compileGrammar(module, CoreUtil.subMonitorFor(monitor, WORK_BUILD));
			}
			Log.debug(this, "Build done");
			return Status.OK_STATUS;

		} finally {
			monitor.done();
		}
	}

	private void compileGrammar(IResource resource, IProgressMonitor monitor) {
		if (resource != null && resource instanceof IFile && (resource.getName().endsWith(".xv"))) {
			IFile file = (IFile) resource;
			URL[] urls;
			try {
				urls = DynamicLoader.getURLs(file.getProject());
				Log.info(this, DynamicLoader.dumpURLs(urls));
			} catch (MalformedURLException e) {
				Log.error(this, e.getMessage());
				return;
			}

			String srcFile = file.getLocation().toString();
			String outputDirectory = determineBuildPath(file).toString();

			Log.info(this, "Build [" + srcFile + "]");
			Log.info(this, "Output [" + outputDirectory + "]");

			List<String> srcFiles = new ArrayList<>();
			srcFiles.add(srcFile);
			monitor.worked(1);
			Job buildJob = new Job("XVisitor Builder") {

				@Override
				protected IStatus run(IProgressMonitor monitor) {

					// install a target specific project classloader
					Thread thread = Thread.currentThread();
					ClassLoader parent = thread.getContextClassLoader();

					try {
						DynamicLoader loader = DynamicLoader.create(urls, parent);
						thread.setContextClassLoader(loader);
					} catch (Exception e) {
						Log.info(this, "Failed to construct classloader; restoring.");
						thread.setContextClassLoader(parent);
						return FAIL_STATUS;
					}

					ToolErrorListener errListener = new ToolErrorListener(file, prefix);

					Tool tool = new Tool();
					tool.removeListeners();
					tool.addListener(errListener);
					tool.setLevel("warn");
					tool.setLibDirectory(outputDirectory);
					tool.setOutputDirectory(outputDirectory);
					tool.setGrammarFiles(srcFiles);
					monitor.worked(1);

					boolean ok = true;
					try {
						tool.genModel();
						monitor.worked(1);
					} catch (Exception | Error e) {
						ok = false;
						Log.error(this, "XVisitor build failed: " + e.getMessage());
						Log.error(this, " - Src Files: " + srcFiles);
						Log.error(this, " - Lib Dir : " + outputDirectory);
						Log.error(this, " - Out Dir : " + outputDirectory);

						URLClassLoader urlLoader = (URLClassLoader) thread.getContextClassLoader();
						for (URL url : urlLoader.getURLs()) {
							Log.error(this, " - Classpath: " + url.getPath());
						}
					} finally {
						thread.setContextClassLoader(parent);
					}

					postCompileCleanup(file, monitor);
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

	private void postCompileCleanup(IFile file, IProgressMonitor monitor) {
		if (!file.exists()) {
			Log.info(this, "Compile produced no file[file=" + file.getFullPath() + "]");
			return;
		}
		IProject project = file.getProject();

		// refresh directory
		if (getDslCore().getPrefsManager().getBoolean(project, Builder.BUILDER_REFRESH)) {
			boolean markDerived = getDslCore().getPrefsManager().getBoolean(project, Builder.BUILDER_MARK_DERIVED);
			doBuilderRefresh(file, markDerived, monitor);
		}
		// format all
		if (getDslCore().getPrefsManager().getBoolean(project, Builder.BUILDER_FORMAT)) {
			doBuilderFormat(file, monitor);
		}
		// organize imports
		if (getDslCore().getPrefsManager().getBoolean(project, Builder.BUILDER_ORGANIZE)) {
			doBuilderOrganizeImports(file, monitor);
		}
	}

	private void doBuilderRefresh(IFile file, boolean markDerived, IProgressMonitor monitor) {
		IContainer folder = getBuildFolder(file);
		try {
			if (folder != null) {
				// refresh files
				folder.refreshLocal(IResource.DEPTH_ONE, monitor);
				if (markDerived) {
					// and set generated files as derived resources
					String name = file.getName();
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
				IProject project = file.getProject();
				project.refreshLocal(IResource.DEPTH_INFINITE, monitor);
			}
		} catch (CoreException e) {
			Log.error(this, "Failed to refresh");
		}
		monitor.worked(1);
		Log.info(this, "Workspace refreshed");
	}

	private void doBuilderFormat(IFile file, IProgressMonitor monitor) {
		Log.info(this, "Formatting...");
		IContainer folder = getBuildFolder(file);
		IProject project = file.getProject();
		IJavaProject javaProject = JavaCore.create(project);
		Map<String, String> options = javaProject.getOptions(true);
		CodeFormatter formatter = ToolFactory.createCodeFormatter(options);

		try {
			ICompilationUnit[] cus = getCompilationUnits(file, folder);
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

	private void doBuilderOrganizeImports(IFile file, IProgressMonitor monitor) {
		IContainer folder = getBuildFolder(file);
		IChooseImportQuery query = new IChooseImportQuery() {

			@Override
			public TypeNameMatch[] chooseImports(TypeNameMatch[][] openChoices, ISourceRange[] ranges) {
				return null;
			}
		};

		try {
			ICompilationUnit[] cus = getCompilationUnits(file, folder);
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

	private IContainer getBuildFolder(IFile file) {
		IPath path = determineBuildPath(file);
		return containerOfPath(path);
	}

	/**
	 * Determine the build folder for a given a resource representing a grammar file.
	 *
	 * @param resource typically the grammar IFile
	 * @return a filesystem absolute path to the build folder
	 */
	protected IPath determineBuildPath(IFile file) {
		IPath grammarPath = determineGeneratedSourcePath(file);
		return file.getProject().getLocation().append(grammarPath);
	}

	protected IPath determineGeneratedSourcePath(IFile file) {
		IPath workingPath = JdtUtil.determineSourceFolder(file);
		String pkg = resolvePackageName(file);
		if (pkg != null && !pkg.isEmpty()) {
			workingPath = workingPath.append(pkg.replaceAll("\\.", "/"));
		}
		return workingPath;
	}

	private String resolvePackageName(IFile file) {
		DslModelManager mgr = getDslCore().getModelManager();
		ICodeUnit unit = mgr.create(file);
		return AntlrUtil.resolvePackageName(unit);
	}

	private IContainer containerOfPath(IPath path) {
		return ResourcesPlugin.getWorkspace().getRoot().getContainerForLocation(path);
	}

	private ICompilationUnit[] getCompilationUnits(IResource resource, IContainer folder) {
		ArrayList<ICompilationUnit> cuList = new ArrayList<>();
		IResource[] children = null;
		try {
			children = folder.members();
		} catch (CoreException e) {
			e.printStackTrace();
		}

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
