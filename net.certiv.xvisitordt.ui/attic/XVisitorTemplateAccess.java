package net.certiv.xvisitordt.ui.templates;

import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.text.templates.ContextTypeRegistry;

import net.certiv.dsl.ui.templates.DslTemplateAccess;
import net.certiv.xvisitordt.core.XVisitorCore;

/**
 * Provides access to the template delta.
 */
public class XVisitorTemplateAccess extends DslTemplateAccess {

	private static XVisitorTemplateAccess instance;

	public static XVisitorTemplateAccess getInstance() {
		if (instance == null) {
			instance = new XVisitorTemplateAccess();
		}
		return instance;
	}

	protected String getContextTypeId() {
		return XVisitorTemplateContextType.XVISITOR_CONTEXT_TYPE_ID;
	}

	protected String getCustomTemplatesKey() {
		return XVisitorTemplateContextType.XVISITOR_CUSTOM_TEMPLATES_KEY;
	}

	protected IPreferenceStore getPreferenceStore() {
		return XVisitorCore.getDefault().getPrefsManager();
	}

	@Override
	public ContextTypeRegistry getContextTypeRegistry() {
		if (fRegistry == null) {
			super.getContextTypeRegistry();
			fRegistry.addContextType(XVisitorTemplateContextType.GRAMMAR_CONTEXT_TYPE_ID);
			fRegistry.addContextType(XVisitorTemplateContextType.OPTIONS_CONTEXT_TYPE_ID);
			fRegistry.addContextType(XVisitorTemplateContextType.ACTIONS_CONTEXT_TYPE_ID);
			fRegistry.addContextType(XVisitorTemplateContextType.JAVADOC_CONTEXT_TYPE_ID);
		}
		return fRegistry;
	}
}
