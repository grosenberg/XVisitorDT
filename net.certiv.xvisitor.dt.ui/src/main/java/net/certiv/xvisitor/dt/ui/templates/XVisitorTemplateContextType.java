package net.certiv.xvisitor.dt.ui.templates;

import net.certiv.dsl.ui.editor.text.completion.DslTemplateContextType;

public class XVisitorTemplateContextType extends DslTemplateContextType {

	public static final String CONTEXT_TYPE_ID = "net.certiv.xvisitor.dt.ui.DefaultContext";
	public static final String XVISITOR_CUSTOM_TEMPLATES_KEY = "net.certiv.xvisitor.dt.ui.CustomContext.templates";

	public static final String GRAMMAR_CONTEXT_TYPE_ID = "xvisitorGrammar";
	public static final String OPTIONS_CONTEXT_TYPE_ID = "xvisitorOptions";

	public XVisitorTemplateContextType(String id) {
		super(id);
	}

	public XVisitorTemplateContextType(String id, String name) {
		super(id, name);
	}
}
