package net.certiv.xvisitordt.ui.formatter.strategies;

import net.certiv.dsl.core.preferences.DslPrefsManager;
import net.certiv.dsl.jdt.ui.formatter.strategies.CommentFormattingStrategy;
import net.certiv.xvisitordt.core.XVisitorCore;

public class GrammarCommentFormattingStrategy extends CommentFormattingStrategy {

	@Override
	public DslPrefsManager getFormatterPrefs() {
		return XVisitorCore.getDefault().getPrefsManager();
	}
}
