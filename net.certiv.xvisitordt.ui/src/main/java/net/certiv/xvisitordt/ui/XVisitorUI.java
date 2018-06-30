package net.certiv.xvisitordt.ui;

import org.eclipse.jface.resource.ImageDescriptor;
import org.osgi.framework.BundleContext;

import net.certiv.dsl.core.DslCore;
import net.certiv.dsl.core.util.Log;
import net.certiv.dsl.core.util.Log.LogLevel;
import net.certiv.dsl.ui.DslImages;
import net.certiv.dsl.ui.DslUI;
import net.certiv.dsl.ui.editor.text.DslTextTools;
import net.certiv.dsl.ui.formatter.IDslFormatterFactory;
import net.certiv.xvisitordt.core.XVisitorCore;
import net.certiv.xvisitordt.ui.editor.XVisitorTextTools;
import net.certiv.xvisitordt.ui.preferences.formatter.FormatterFactory;

/**
 * The activator class controls the plug-in life cycle
 */
public class XVisitorUI extends DslUI {

	// Should be unique, lower case, single word
	private static final String DSL_NAME = "xvisitor";

	private static XVisitorUI plugin;

	private DslImages imageProvider;
	private DslTextTools textTools;
	private IDslFormatterFactory factory;

	/**
	 * The constructor
	 */
	public XVisitorUI() {
		super();
		Log.defLevel(LogLevel.Debug);
	}

	/**
	 * Returns the shared instance of the plugin.
	 * 
	 * @return the shared instance
	 */
	public static XVisitorUI getDefault() {
		return plugin;
	}

	@Override
	public DslUI getDslUI() {
		return getDefault();
	}

	@Override
	public DslCore getDslCore() {
		return XVisitorCore.getDefault();
	}

	@Override
	public void start(BundleContext context) throws Exception {
		plugin = this;
		super.start(context);
		assert (XVisitorCore.getDefault().getPluginId() != null);
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
	public String getDslLanguageName() {
		return DSL_NAME;
	}

	/**
	 * Returns an image descriptor for the image file at the given plug-in relative path
	 * 
	 * @param path the path
	 * @return the image descriptor
	 */
	public static ImageDescriptor getImageDescriptor(String path) {
		return imageDescriptorFromPlugin(PLUGIN_ID, path);
	}

	@Override
	public DslImages getImageProvider() {
		if (imageProvider == null) {
			imageProvider = new XVisitorImages();
		}
		return imageProvider;
	}

	/**
	 * Returns the text tools
	 */
	@Override
	public DslTextTools getTextTools() {
		if (textTools == null) {
			textTools = new XVisitorTextTools(true);
		}
		return textTools;
	}

	@Override
	public IDslFormatterFactory getFormatterFactory() {
		if (factory == null) {
			factory = new FormatterFactory();
		}
		return factory;
	}
}
