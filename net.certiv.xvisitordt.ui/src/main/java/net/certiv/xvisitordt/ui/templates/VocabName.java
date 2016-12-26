package net.certiv.xvisitordt.ui.templates;

import net.certiv.dsl.core.model.ITranslationUnit;
import net.certiv.dsl.ui.templates.DslTemplateContext;

import org.eclipse.jface.text.templates.TemplateContext;
import org.eclipse.jface.text.templates.TemplateVariableResolver;

public class VocabName extends TemplateVariableResolver {

	public VocabName() {}

	public VocabName(String type, String description) {
		super(type, description);
	}

	@Override
	protected boolean isUnambiguous(TemplateContext context) {
		return resolve(context) != null;
	}

	@Override
	protected String resolve(TemplateContext context) {
		ITranslationUnit tu = ((DslTemplateContext) context).getSourceModule();
		String vocabName = null;
		if (tu != null) {
			vocabName = tu.getElementName();
			if (vocabName != null && vocabName.lastIndexOf('.') > 0) {
				vocabName = vocabName.substring(0, vocabName.lastIndexOf('.'));
			}
		}
		return vocabName;
	}
}