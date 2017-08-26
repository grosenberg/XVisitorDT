package net.certiv.xvisitordt.core.builder;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IMarker;
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
import org.eclipse.jdt.core.search.TypeNameMatch;
import org.eclipse.jdt.internal.corext.codemanipulation.OrganizeImportsOperation;
import org.eclipse.jdt.internal.corext.codemanipulation.OrganizeImportsOperation.IChooseImportQuery;
import org.eclipse.jdt.internal.formatter.DefaultCodeFormatter;
import org.eclipse.jface.text.Document;
import org.eclipse.jface.text.IDocument;
import org.eclipse.text.edits.TextEdit;

import net.certiv.antlr.xvisitor.Tool;
import net.certiv.antlr.xvisitor.tool.Messages;
import net.certiv.dsl.core.DslCore;
import net.certiv.dsl.core.util.CoreUtil;
import net.certiv.dsl.core.util.Log;
import net.certiv.xvisitordt.core.XVisitorCore;
import net.certiv.xvisitordt.core.preferences.PrefsKey;

@SuppressWarnings("restriction")
public class XVisitorBuilder extends XVisitorBuilderBase {

	// private static final IStatus F_STATUS = new Status(IStatus.CANCEL,
	// XVisitorCore.PLUGIN_ID,
	// "XVisitor build failed.");
	// private Job buildJob;

	private static final int WORK_BUILD = 100;

	public static final String BUILDER_ID = "net.certiv.xvisitordt.core.builder";
	public static final String MARKER_TYPE = "net.certiv.xvisitordt.core.problemMarker";

	/* The current .xv file being edited, or null */
	private IFile file;
	private IPath filepath;
	private IPath projpath;

	public XVisitorBuilder() {
		super();
	}

	@Override
	public DslCore getDslCore() {
		return XVisitorCore.getDefault();
	}

	@Override
	public String getBuilderID() {
		return BUILDER_ID;
	}

	@Override
	public IStatus buildSourceModules(IProgressMonitor monitor, int ticks, List<IFile> srcModules)
			throws CoreException {

		if (!builderEnabled() || srcModules.isEmpty()) return null;

		try {
			monitor.beginTask(CoreUtil.EMPTY_STRING, WORK_BUILD);
			file = CoreUtil.getActiveDslFile(getDslCore().getDslFileExtensions());
			filepath = file != null ? file.getFullPath() : null;
			projpath = getProject().getFullPath();

			for (IFile module : srcModules) {
				IPath modpath = module.getFullPath();
				if (restrictToActiveProject() && !projpath.isPrefixOf(modpath)) continue;
				if (restrictToActiveProjectPath() && !modpath.equals(filepath)) continue;
				if (excludeIgnoredPaths(module)) continue;

				// Log.info(this, "Building " + buildDescription(module));
				clearMarkers(module);
				try {
					compileGrammar(module, CoreUtil.subMonitorFor(monitor, WORK_BUILD));
				} catch (Exception e) {
					Log.error(this, "Failed to resolve build element", e);
				}
			}
			Log.debug(this, "Build done");
			return Status.OK_STATUS;
		} finally {
			monitor.done();
		}
	}

	private boolean excludeIgnoredPaths(IFile module) {
		if (resolvePackageName(module) == null) return true;
		IPath path = determineGeneratedSourcePath(module);
		if (path == null) return true;
		for (String segment : path.segments()) {
			if (segment.equals("ignore")) return true;
		}
		return false;
	}

	private void compileGrammar(IResource resource, IProgressMonitor monitor) {
		if (resource != null && resource instanceof IFile && (resource.getName().endsWith(".xv"))) {
			IFile file = (IFile) resource;

			try {
				String srcFile = file.getLocation().toPortableString();
				String outputDirectory = determineBuildFolder(file).toString();
				List<String> srcFiles = new ArrayList<>();
				srcFiles.add(srcFile);

				Log.info(this, "Build  [" + srcFile + "]");
				Log.info(this, "Output [" + outputDirectory + "]");
				monitor.worked(1);

				XVisitorErrorListener toolErrs = new XVisitorErrorListener();

				Tool tool = new Tool();
				tool.removeListeners();
				tool.addListener(toolErrs);
				tool.setLevel("warn");
				tool.setLibDirectory(outputDirectory);
				tool.setOutputDirectory(outputDirectory);
				tool.setGrammarFiles(srcFiles);
				monitor.worked(1);

				try {
					tool.genModel();
					monitor.worked(1);
				} catch (Exception | Error e) {
					Log.error(this, "XVisitor build failed: " + e.getMessage());
					Log.error(this, " - Src Files: " + srcFiles);
					Log.error(this, " - Out Dir  : " + outputDirectory);
				}

				publishErrors(resource, toolErrs);
				postCompileCleanup(file, monitor);
				monitor.worked(1);
			} catch (Exception | Error e) {
				Log.error(this, "Build failed.", e);
			}
		}
	}

	// private void compileGrammar(IResource resource, IProgressMonitor monitor) {
	// if (resource != null && resource instanceof IFile &&
	// (resource.getName().endsWith(".xv"))) {
	// IFile file = (IFile) resource;
	//
	// String srcFile = file.getLocation().toPortableString();
	// String outputDirectory = determineBuildFolder(file).toString();
	// Log.info(this, "Build [" + srcFile + "]");
	// Log.info(this, "Output [" + outputDirectory + "]");
	//
	// XVisitorErrorListener toolErrs = new XVisitorErrorListener();
	//
	// List<String> srcFiles = new ArrayList<>();
	// srcFiles.add(srcFile);
	//
	// monitor.worked(1);
	//
	// buildJob = new Job("XVisitor Builder") {
	//
	// @Override
	// protected IStatus run(IProgressMonitor monitor) {
	//
	// // install a target specific project classloader
	// Thread thread = Thread.currentThread();
	// ClassLoader threadLoader = thread.getContextClassLoader();
	// IProject project = file.getProject();
	//
	// try {
	// ClassLoader projectLoader = ClassUtil.buildClassLoader(project);
	// thread.setContextClassLoader(projectLoader);
	// } catch (MalformedURLException e) {
	// Log.info(this, "Failed to construct classloader; restoring.");
	// thread.setContextClassLoader(threadLoader);
	// return F_STATUS;
	// }
	//
	// Tool tool = new Tool();
	// tool.removeListeners();
	// tool.addListener(toolErrs);
	// tool.setLevel("warn");
	// tool.setLibDirectory(outputDirectory);
	// tool.setOutputDirectory(outputDirectory);
	// tool.setGrammarFiles(srcFiles);
	// monitor.worked(1);
	//
	// boolean ok = true;
	// try {
	// tool.genModel();
	// monitor.worked(1);
	// } catch (Exception | Error e) {
	// ok = false;
	// Log.error(this, "XVisitor build failed: " + e.getMessage());
	// Log.error(this, " - Src Files: " + srcFiles);
	// Log.error(this, " - Lib Dir : " + outputDirectory);
	// Log.error(this, " - Out Dir : " + outputDirectory);
	//
	// URLClassLoader urlLoader = (URLClassLoader) thread.getContextClassLoader();
	// for (URL url : urlLoader.getURLs()) {
	// Log.error(this, " - Classpath: " + url.getPath());
	// }
	// } finally {
	// Log.info(this, "Restoring classloader after generate.");
	// thread.setContextClassLoader(threadLoader);
	// }
	//
	// publishErrors(resource, toolErrs);
	// postCompileCleanup(file, monitor);
	// monitor.worked(1);
	//
	// if (!ok) return F_STATUS;
	// return Status.OK_STATUS;
	// }
	// };
	//
	// // finalize and schedule job
	// buildJob.setPriority(Job.SHORT);buildJob.setSystem(true);buildJob.schedule();
	//
	// }
	// }

	private void publishErrors(IResource resource, XVisitorErrorListener toolErrs) {
		if (toolErrs.hasErrors()) {
			for (Messages err : toolErrs.getErrList()) {
				createProblemMarker(resource, err.offendingToken, err.toString(), IMarker.SEVERITY_ERROR);
				Log.error(this, err.toString());
			}
		}
		if (toolErrs.hasWarnings()) {
			for (Messages err : toolErrs.getWarnList()) {
				createProblemMarker(resource, err.offendingToken, err.toString(), IMarker.SEVERITY_WARNING);
				Log.warn(this, err.toString());
			}
		}
	}

	private void postCompileCleanup(IFile file, IProgressMonitor monitor) {
		if (!file.exists()) {
			Log.info(this, "Compile produced no file[file=" + file.getFullPath() + "]");
			return;
		}
		IProject project = file.getProject();

		// refresh directory
		if (getDslCore().getPrefsManager().getBoolean(project, PrefsKey.BUILDER_REFRESH)) {
			boolean markDerived = getDslCore().getPrefsManager().getBoolean(project, PrefsKey.BUILDER_MARK_DERIVED);
			doBuilderRefresh(file, markDerived, monitor);
		}
		// format all
		if (getDslCore().getPrefsManager().getBoolean(project, PrefsKey.BUILDER_FORMAT)) {
			doBuilderFormat(file, monitor);
		}
		// organize imports
		if (getDslCore().getPrefsManager().getBoolean(project, PrefsKey.BUILDER_ORGANIZE)) {
			doBuilderOrganizeImports(file, monitor);
		}
	}

	// ///////////////////////////////////////////////////////////////////////////////

	private void doBuilderRefresh(IFile file, boolean markDerived, IProgressMonitor monitor) {
		Log.info(this, "Refreshing...");
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
							if (res.getFileExtension().startsWith("g4")) {
								continue;
							} else if (res.getName().equals(name + ".tokens")) {
								res.setDerived(true, monitor);
							} else if (res.getName().startsWith(name + "Parser")) {
								res.setDerived(true, monitor);
							} else if (res.getName().startsWith(name + "Lexer")) {
								res.setDerived(true, monitor);
							} else if (res.getName().equals(name + "." + ext)) {
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
				TextEdit textEdit = formatter.format(DefaultCodeFormatter.K_COMPILATION_UNIT, content, 0,
						content.length(), 0, null);

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

	private IContainer getBuildFolder(IResource resource) {
		IPath path = determineBuildFolder(resource);
		return containerOfPath(path);
	}

	private IContainer containerOfPath(IPath path) {
		return ResourcesPlugin.getWorkspace().getRoot().getContainerForLocation(path);
	}

	private ICompilationUnit[] getCompilationUnits(IResource resource, IContainer folder) {
		ArrayList<ICompilationUnit> cuList = new ArrayList<ICompilationUnit>();
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

	// private String buildDescription(IFile file) {
	// String projName = "";
	// String fileName = "";
	// if (file != null) {
	// projName = file.getProject().getName() + ", ";
	// fileName = file.getName() + ", ";
	// }
	// String name = projName + fileName;
	// return "[" + name + "time=" + new Date(System.currentTimeMillis()) + "]";
	// }
}
