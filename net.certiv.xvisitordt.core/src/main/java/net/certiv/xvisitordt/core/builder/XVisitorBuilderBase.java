package net.certiv.xvisitordt.core.builder;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.IPath;

import net.certiv.dsl.core.builder.DslBuilder;
import net.certiv.dsl.core.parser.util.ParserUtil;
import net.certiv.dsl.core.util.CoreUtil;
import net.certiv.xvisitordt.core.parser.XVisitorSourceParser;

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

	protected String resolvePackageName(IResource resource) {
		XVisitorSourceParser parser = (XVisitorSourceParser) ParserUtil.getSourceParser(getDslCore(), (IFile) resource);
		return parser.resolvePackageName();
	}
}
