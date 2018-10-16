package net.certiv.xvisitordt.ui.templates;

import org.eclipse.jface.text.IDocument;

import net.certiv.dsl.core.model.ICodeUnit;
import net.certiv.dsl.ui.editor.text.completion.tmpl.DslTemplateContext;
import net.certiv.dsl.ui.editor.text.completion.tmpl.DslTemplateContextType;

public class XVisitorTemplateContextType extends DslTemplateContextType {

	public static final String CONTEXT_TYPE_ID = "net.certiv.xvisitordt.ui.DefaultContext";
	public static final String XVISITOR_CUSTOM_TEMPLATES_KEY = "net.certiv.xvisitordt.ui.CustomContext.templates";

	public static final String GRAMMAR_CONTEXT_TYPE_ID = "xvisitorGrammar";
	public static final String OPTIONS_CONTEXT_TYPE_ID = "xvisitorOptions";
	public static final String ACTIONS_CONTEXT_TYPE_ID = "java"; // the JDT context id
	public static final String JAVADOC_CONTEXT_TYPE_ID = "javadoc"; // the JDT context id

	public XVisitorTemplateContextType(String id) {
		super(id);
	}

	public XVisitorTemplateContextType(String id, String name) {
		super(id, name);
	}

	@Override
	public DslTemplateContext createContext(IDocument document, int completionPosition, int length,
			ICodeUnit sourceModule) {

		return new XVisitorTemplateContext(this, document, completionPosition, length, sourceModule);
	}

	@Override
	protected void addDslResolvers() {}
}
