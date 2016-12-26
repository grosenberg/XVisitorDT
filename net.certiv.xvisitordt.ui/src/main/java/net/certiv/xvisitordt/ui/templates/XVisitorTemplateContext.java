package net.certiv.xvisitordt.ui.templates;

import net.certiv.dsl.core.model.ITranslationUnit;
import net.certiv.dsl.ui.templates.DslTemplateContext;

import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.templates.TemplateContextType;

public class XVisitorTemplateContext extends DslTemplateContext {

	protected XVisitorTemplateContext(TemplateContextType type, IDocument document, int completionOffset,
			int completionLength, ITranslationUnit sourceModule) {

		super(type, document, completionOffset, completionLength, sourceModule);
	}
}