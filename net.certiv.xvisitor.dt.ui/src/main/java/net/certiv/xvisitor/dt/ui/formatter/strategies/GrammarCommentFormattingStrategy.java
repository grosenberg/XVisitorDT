package net.certiv.xvisitor.dt.ui.formatter.strategies;

import net.certiv.dsl.core.preferences.PrefsManager;
import net.certiv.dsl.jdt.ui.formatter.strategies.CommentFormattingStrategy;
import net.certiv.xvisitor.dt.core.XVisitorCore;

public class GrammarCommentFormattingStrategy extends CommentFormattingStrategy {

	@Override
	public PrefsManager getFormatterPrefs() {
		return XVisitorCore.getDefault().getPrefsManager();
	}
}
