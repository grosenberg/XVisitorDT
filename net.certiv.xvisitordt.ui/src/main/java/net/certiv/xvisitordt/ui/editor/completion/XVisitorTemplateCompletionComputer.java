package net.certiv.xvisitordt.ui.editor.completion;

import net.certiv.dsl.ui.editor.text.completion.DslContentAssistInvocationContext;
import net.certiv.dsl.ui.editor.text.completion.tmpl.DslTemplateCompletionProposalComputer;
import net.certiv.dsl.ui.editor.text.completion.tmpl.DslTemplateEngine;

public class XVisitorTemplateCompletionComputer extends DslTemplateCompletionProposalComputer {

	@Override
	protected DslTemplateEngine computeCompletionEngine(DslContentAssistInvocationContext context) {
		return null;
	}
}
