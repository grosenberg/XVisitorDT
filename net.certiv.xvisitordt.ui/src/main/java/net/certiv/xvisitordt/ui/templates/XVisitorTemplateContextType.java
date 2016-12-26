package net.certiv.xvisitordt.ui.templates;

import net.certiv.dsl.core.model.ITranslationUnit;
import net.certiv.dsl.ui.templates.DslTemplateContext;
import net.certiv.dsl.ui.templates.DslTemplateContextType;

import org.eclipse.jface.text.IDocument;

public class XVisitorTemplateContextType extends DslTemplateContextType {

	public static final String XVISITOR_CONTEXT_TYPE_ID = "net.certiv.xvisitordt.ui.DefaultContext";
	public static final String XVISITOR_CUSTOM_TEMPLATES_KEY = "net.certiv.xvisitordt.ui.CustomContext.templates";

	public static final String GRAMMAR_CONTEXT_TYPE_ID = "antlrGrammar";
	public static final String OPTIONS_CONTEXT_TYPE_ID = "antlrOptions";
	public static final String ACTIONS_CONTEXT_TYPE_ID = "java"; // the JDT context id
	public static final String JAVADOC_CONTEXT_TYPE_ID = "javadoc"; // the JDT context id

	public XVisitorTemplateContextType() {
		super();
	}

	public XVisitorTemplateContextType(String id) {
		super(id);
	}

	public XVisitorTemplateContextType(String id, String name) {
		super(id, name);
	}

	@Override
	public DslTemplateContext createContext(IDocument document, int completionPosition, int length,
			ITranslationUnit sourceModule) {

		return new XVisitorTemplateContext(this, document, completionPosition, length, sourceModule);
	}

	@Override
	protected void addDslResolvers() {}
}
