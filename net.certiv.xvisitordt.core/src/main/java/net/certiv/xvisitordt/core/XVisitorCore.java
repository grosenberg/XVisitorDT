package net.certiv.xvisitordt.core;

import org.osgi.framework.BundleContext;

import net.certiv.dsl.core.DslCore;
import net.certiv.dsl.core.parser.ISourceParserFactory;
import net.certiv.dsl.core.util.Log;
import net.certiv.dsl.core.util.Log.LogLevel;
import net.certiv.xvisitordt.core.parser.XVisitorSourceParserFactory;

/**
 * The activator class controls the plug-in life cycle
 */
public class XVisitorCore extends DslCore {

	private static final String[] EXTENSIONS = new String[] { "xv" };

	private static XVisitorCore plugin;

	private XVisitorSourceParserFactory factory;

	/**
	 * The constructor
	 */
	public XVisitorCore() {
		super();
		Log.defLevel(LogLevel.Debug);
	}

	/**
	 * Returns the shared instance
	 * 
	 * @return the shared instance
	 */
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
	public ISourceParserFactory getSourceParserFactory() {
		if (factory == null) {
			factory = new XVisitorSourceParserFactory();
		}
		return factory;
	}
}
