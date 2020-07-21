package net.certiv.xvisitor.dt.ui.templates;

import org.eclipse.jface.text.templates.TemplateVariableResolver;

import net.certiv.dsl.ui.editor.text.completion.DslTemplateContextType;

public class XVisitorContextType extends DslTemplateContextType {

	public XVisitorContextType(String id, TemplateVariableResolver... resolvers) {
		super(id, resolvers);
	}
}
