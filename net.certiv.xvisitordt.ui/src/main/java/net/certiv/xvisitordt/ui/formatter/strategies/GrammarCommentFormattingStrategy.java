package net.certiv.xvisitordt.ui.formatter.strategies;

import org.eclipse.jface.preference.IPreferenceStore;

import net.certiv.dsl.ui.formatter.strategies.CommentFormattingStrategy;
import net.certiv.xvisitordt.core.XVisitorCore;

public class GrammarCommentFormattingStrategy extends CommentFormattingStrategy {

	@Override
	public IPreferenceStore getFormatterPrefs() {
		return XVisitorCore.getDefault().getPrefsManager();
	}
}
