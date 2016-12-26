package net.certiv.xvisitordt.ui.editor.completion;

import org.eclipse.jface.text.contentassist.ContentAssistant;
import org.eclipse.ui.IEditorPart;

import net.certiv.dsl.core.DslCore;
import net.certiv.dsl.ui.DslUI;
import net.certiv.dsl.ui.templates.DslTemplateContext;
import net.certiv.dsl.ui.text.completion.CompletionLabelProvider;
import net.certiv.dsl.ui.text.completion.DslCompletionProcessor;
import net.certiv.dsl.ui.text.completion.DslCompletionProposal;
import net.certiv.xvisitordt.core.XVisitorCore;
import net.certiv.xvisitordt.ui.XVisitorUI;

public class XVisitorCompletionProcessor extends DslCompletionProcessor {

	public XVisitorCompletionProcessor(IEditorPart editor, ContentAssistant assistant, String partition) {
		super(editor, assistant, partition);
	}

	@Override
	public DslUI getDslUI() {
		return XVisitorUI.getDefault();
	}

	@Override
	public DslCore getDslCore() {
		return XVisitorCore.getDefault();
	}

	@Override
	protected CompletionLabelProvider getProposalLabelProvider() {
		return new XVisitorCompletionLabelProvider();
	}

	@Override
	public void createCategories() {
		addCategory(DslCompletionProposal.ID, DslCompletionProposal.NAME, new XVisitorCodeCompletionComputer());
		addCategory(DslTemplateContext.ID, DslTemplateContext.NAME, new XVisitorTemplateCompletionComputer());
	}
}
