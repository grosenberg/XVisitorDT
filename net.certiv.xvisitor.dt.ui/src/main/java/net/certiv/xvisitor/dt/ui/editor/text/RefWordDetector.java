package net.certiv.xvisitor.dt.ui.editor.text;

import org.eclipse.jface.text.rules.IWordDetector;

public class RefWordDetector implements IWordDetector {

	@Override
	public boolean isWordStart(char c) {
		return c == '$';
	}

	@Override
	public boolean isWordPart(char c) {
		return Character.isLetterOrDigit((int) c) || c == '_' || c == '.';
	}
}
