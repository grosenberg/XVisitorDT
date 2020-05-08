package net.certiv.xvisitor.dt.ui;

import org.eclipse.jface.text.IAutoEditStrategy;

import net.certiv.dsl.core.model.IStatement;
import net.certiv.dsl.ui.DslUI;
import net.certiv.dsl.ui.editor.text.DefaultAutoEditSemicolonStrategy;
import net.certiv.dsl.ui.templates.CompletionManager;

public class XvCompletionManager extends CompletionManager {

	private static final String GRAMMAR = "grammar";
	private static final String OPTIONS = "options";
	private static final String RULES = "rules";

	public XvCompletionManager(DslUI ui, String editorId) {
		super(ui, editorId, MODULE, IMPORT, GRAMMAR, OPTIONS, RULES);
	}

	@Override
	public String getContentAssistScope(IStatement stmt) {
		switch (stmt.getModelType()) {
			case MODULE:
				return MODULE;

			case IMPORT:
				return IMPORT;

			case DECLARATION:
				return GRAMMAR;

			case STATEMENT:
				if (stmt.getElementName().equals(OPTIONS)) return OPTIONS;
				return RULES;

			case FIELD:
			case BEG_BLOCK:
			case END_BLOCK:
			default:
				return null;
		}
	}

	@Override
	public IAutoEditStrategy getSmartTriggerStrategy(String contentType) {
		String partitioning = ui.getTextTools().getDocumentPartitioning();
		// TODO: customize the strategy
		return new DefaultAutoEditSemicolonStrategy(partitioning, mgr);
	}
}
