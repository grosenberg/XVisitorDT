package net.certiv.xvisitordt.ui.editor.completion;

import java.util.List;

import org.antlr.v4.runtime.Token;
import org.eclipse.jface.viewers.StyledString;
import org.eclipse.swt.graphics.Image;

import net.certiv.dsl.core.DslCore;
import net.certiv.dsl.core.completion.CompletionProposal;
import net.certiv.dsl.core.model.DslModelException;
import net.certiv.dsl.core.model.ICodeUnit;
import net.certiv.dsl.ui.DslUI;
import net.certiv.dsl.ui.text.completion.CompletionLabelProvider;
import net.certiv.dsl.ui.text.completion.DslCollector;
import net.certiv.dsl.ui.text.completion.DslCompletionProposal;
import net.certiv.xvisitordt.core.XVisitorCore;
import net.certiv.xvisitordt.ui.XVisitorUI;
import net.certiv.xvisitordt.ui.editor.text.ScannerKeyWord;

public class XVisitorCollector extends DslCollector {

	public XVisitorCollector(ICodeUnit cu) {
		super(cu);
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
	protected CompletionLabelProvider createCompletionProposalLabelProvider() {
		return new XVisitorCompletionLabelProvider();
	}

	@Override
	protected DslCompletionProposal createDslCompletionProposal(String completion, int offset, int length, Image image,
			String label, int relevance) {
		return createDslCompletionProposal(completion, offset, length, image, new StyledString(label), relevance,
				false);
	}

	@Override
	protected DslCompletionProposal createDslCompletionProposal(String completion, int offset, int length, Image image,
			StyledString label, int relevance, boolean inDoc) {
		return new XVisitorCompletionProposal(completion, offset, length, image, label, relevance, inDoc);
	}

	@Override
	protected char[] getVarTrigger() {
		return VAR_TRIGGER;
	}

	/**
	 * @param offset invocation offset
	 */
	@Override
	public void prepareProposals(ICodeUnit unit, int offset) throws DslModelException {

		if (!parseValid()) return;

		// 1) handle lexer and parser rule names: captured as a list of tokens
		List<Token> rules = getDslCore().getModelManager().getCodeAssistElements(unit);
		for (Token rule : rules) {
			char[] name = rule.getText().toCharArray();
			int type = CompletionProposal.METHOD_REF; // parser
			if (Character.isUpperCase(name[0])) type = CompletionProposal.FIELD_REF; // lexer
			CompletionProposal proposal = CompletionProposal.create(type, offset);
			proposal.setName(name);
			proposal.setCompletion(name);
			accept(proposal);
		}

		// 2) handle keywords: exists as a static list
		for (String word : ScannerKeyWord.KEYWORDS) {
			CompletionProposal proposal = CompletionProposal.create(CompletionProposal.KEYWORD, offset);
			char[] name = word.toCharArray();
			proposal.setName(name);
			proposal.setCompletion(name);
			accept(proposal);
		}
	}
}
