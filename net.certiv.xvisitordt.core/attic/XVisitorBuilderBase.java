package net.certiv.xvisitordt.core.builder;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.IPath;

import net.certiv.dsl.core.builder.DslBuilder;
import net.certiv.dsl.core.model.ICodeUnit;
import net.certiv.dsl.core.util.CoreUtil;

public abstract class XVisitorBuilderBase extends DslBuilder {

	public XVisitorBuilderBase() {
		super();
	}

	/**
	 * Determine the build folder for a given a resource representing a grammar file.
	 * 
	 * @param resource typically the grammar IFile
	 * @return a filesystem absolute path to the build folder
	 */
	protected IPath determineBuildFolder(IResource resource) {
		IPath grammarPath = determineGeneratedSourcePath(resource);
		IPath outputPath = resource.getProject().getLocation().append(grammarPath);
		return outputPath;
	}

	protected IPath determineGeneratedSourcePath(IResource resource) {
		IPath workingPath = CoreUtil.determineSourceFolder(resource);
		String pkg = resolvePackageName(resource);
		if (pkg != null && !pkg.isEmpty()) {
			workingPath = workingPath.append(pkg.replaceAll("\\.", "/"));
		}
		return workingPath;
	}

	public String resolvePackageName(IResource resource) {
		ICodeUnit unit = getDslCore().getModelManager().create((IFile) resource);
		return unit.resolveParserPackageName();
	}
}