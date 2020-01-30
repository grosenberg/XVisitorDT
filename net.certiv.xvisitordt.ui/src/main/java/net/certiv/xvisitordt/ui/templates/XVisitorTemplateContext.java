package net.certiv.xvisitordt.ui.templates;

import org.eclipse.jface.text.IRegion;

import net.certiv.dsl.ui.editor.text.completion.CompletionContext;
import net.certiv.dsl.ui.editor.text.completion.DslTemplateContext;

public class XVisitorTemplateContext extends DslTemplateContext {

	protected XVisitorTemplateContext(XVisitorTemplateContextType type, CompletionContext context, IRegion region) {
		super(type, context, region);
	}
}
