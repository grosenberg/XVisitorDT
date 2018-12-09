package net.certiv.xvisitordt.ui;

import org.osgi.framework.BundleContext;

import net.certiv.dsl.core.DslCore;
import net.certiv.dsl.core.log.Log;
import net.certiv.dsl.core.log.Log.LogLevel;
import net.certiv.dsl.ui.DslImageManager;
import net.certiv.dsl.ui.DslUI;
import net.certiv.dsl.ui.editor.text.DslTextTools;
import net.certiv.xvisitordt.core.XVisitorCore;
import net.certiv.xvisitordt.ui.editor.XVisitorEditor;
import net.certiv.xvisitordt.ui.editor.XVisitorTextTools;
import net.certiv.xvisitordt.ui.templates.XVisitorTemplateContextType;

public class XVisitorUI extends DslUI {

	private static XVisitorUI plugin;

	private XVImageManager imgMgr;
	private DslTextTools textTools;

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
	protected String getEditorId() {
		return XVisitorEditor.EDITOR_ID;
	}

	@Override
	protected String[] getDslContextTypes() {
		return new String[] { XVisitorTemplateContextType.GRAMMAR_CONTEXT_TYPE_ID,
				XVisitorTemplateContextType.OPTIONS_CONTEXT_TYPE_ID, //
				XVisitorTemplateContextType.ACTIONS_CONTEXT_TYPE_ID, //
				XVisitorTemplateContextType.JAVADOC_CONTEXT_TYPE_ID //
		};
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
		return XVisitorCore.DSL_NAME;
	}

	@Override
	public DslImageManager getImageManager() {
		if (imgMgr == null) {
			imgMgr = new XVImageManager();
		}
		return imgMgr;
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
}
