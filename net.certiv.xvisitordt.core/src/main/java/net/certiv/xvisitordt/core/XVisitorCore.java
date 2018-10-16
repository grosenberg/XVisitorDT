package net.certiv.xvisitordt.core;

import org.osgi.framework.BundleContext;

import net.certiv.dsl.core.DslCore;
import net.certiv.dsl.core.parser.DslSourceParser;
import net.certiv.xvisitordt.core.parser.XVisitorSourceParser;

public class XVisitorCore extends DslCore {

	private static final String[] EXTENSIONS = new String[] { "xv" };

	// Should be unique, lower case, single word
	public static final String DSL_NAME = "xvisitor";

	private static XVisitorCore plugin;

	public XVisitorCore() {
		super();
	}

	public static XVisitorCore getDefault() {
		return plugin;
	}

	@Override
	public DslCore getDslCore() {
		return getDefault();
	}

	@Override
	public void start(BundleContext context) throws Exception {
		plugin = this;
		super.start(context);
	}

	@Override
	public void stop(BundleContext context) throws Exception {
		super.stop(context);
		plugin = null;
	}

	@Override
	public String getPluginId() {
		return getDefault().getBundle().getSymbolicName();
	}

	@Override
	public String[] getDslFileExtensions() {
		return EXTENSIONS;
	}

	@Override
	public DslSourceParser createSourceParser(String type) {
		switch (type) {
			case DSL_NAME:
				return new XVisitorSourceParser();
			default:
				return null;
		}
	}

	@Override
	public String getProblemMakerId(String type) {
		return getPluginId() + String.format(".%s_marker", type);
	}
}
