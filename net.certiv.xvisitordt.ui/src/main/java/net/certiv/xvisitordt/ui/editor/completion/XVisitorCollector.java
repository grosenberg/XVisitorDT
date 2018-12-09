package net.certiv.xvisitordt.ui.editor.completion;

import org.eclipse.jface.viewers.StyledString;
import org.eclipse.swt.graphics.Image;

import net.certiv.dsl.core.DslCore;
import net.certiv.dsl.core.model.DslModelException;
import net.certiv.dsl.core.model.ICodeUnit;
import net.certiv.dsl.ui.DslUI;
import net.certiv.dsl.ui.editor.text.completion.CompletionLabelProvider;
import net.certiv.dsl.ui.editor.text.completion.DslCompletionProposal;
import net.certiv.dsl.ui.editor.text.completion.DslCompletionProposalCollector;
import net.certiv.xvisitordt.core.XVisitorCore;
import net.certiv.xvisitordt.ui.XVisitorUI;

public class XVisitorCollector extends DslCompletionProposalCollector {

	public XVisitorCollector(ICodeUnit unit) {
		super(unit);
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
	protected CompletionLabelProvider createProposalLabelProvider() {
		return new XVisitorCompletionLabelProvider();
	}

	@Override
	protected DslCompletionProposal createDslProposal(String completion, int offset, int length, Image image,
			String displayString, int relevance) {
		return createDslProposal(completion, offset, length, image, new StyledString(displayString), relevance, false);
	}

	@Override
	protected DslCompletionProposal createDslProposal(String completion, int offset, int length, Image image,
			StyledString displayString, int relevance, boolean inDoc) {
		return new XVisitorCompletionProposal(completion, offset, length, image, displayString, relevance, inDoc);
	}

	@Override
	protected char[] getVarTrigger() {
		return VAR_TRIGGER;
	}

	@Override
	public void prepareProposals(ICodeUnit unit, int offset) throws DslModelException {

		// // 1) handle lexer and parser rule names: captured as a list of tokens
		// Set<IStatement> rules = getDslCore().getModelManager().getCodeAssistElements(unit);
		// for (IStatement rule : rules) {
		// char[] name = rule.getElementName().toCharArray();
		// int mType = CompletionProposal.METHOD_REF; // parser
		// if (Character.isUpperCase(name[0])) mType = CompletionProposal.FIELD_REF; // lexer
		// CompletionProposal proposal = CompletionProposal.create(mType, offset);
		// proposal.setName(name);
		// proposal.setCompletion(name);
		// accept(proposal);
		// }
		//
		// // 2) handle keywords: exists as a static list
		// for (String word : ScannerDefault.KEYWORDS) {
		// CompletionProposal proposal = CompletionProposal.create(CompletionProposal.KEYWORD, offset);
		// char[] name = word.toCharArray();
		// proposal.setName(name);
		// proposal.setCompletion(name);
		// accept(proposal);
		// }
	}
}
