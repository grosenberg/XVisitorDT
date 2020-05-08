package net.certiv.xvisitor.dt.ui.formatter.strategies;

import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.IDocument;

import net.certiv.dsl.core.formatter.IndentManipulation;
import net.certiv.dsl.core.log.Log;
import net.certiv.dsl.core.preferences.PrefsManager;
import net.certiv.dsl.jdt.ui.formatter.strategies.JavaFormattingStrategy;
import net.certiv.dsl.ui.editor.text.LineRegion;
import net.certiv.xvisitor.dt.core.XVisitorCore;

public class ActionCodeFormattingStrategy extends JavaFormattingStrategy {

	/**
	 * ActionCodeFormattingStrategy indents relative to the beginning of the line if the open
	 * brace follows text. If the open brace has no leading text, then indent is relative to
	 * the indent level of the open brace.
	 */
	@Override
	protected int determineIndentLevel(PrefsManager prefs, IDocument document, int offset) {
		int tabWidth = IndentManipulation.getTabWidth(prefs);
		try {
			int line = document.getLineOfOffset(offset);
			LineRegion region = new LineRegion(document, line);
			String lineText = region.getLineIndent();
			int indentLevel = IndentManipulation.measureIndentUnits(lineText, tabWidth);
			return indentLevel;
		} catch (BadLocationException e) {
			Log.error(this, "Could not determine indent level", e);
		}
		return 0;
	}

	@Override
	public PrefsManager getFormatterPrefs() {
		return XVisitorCore.getDefault().getPrefsManager();
	}
}
