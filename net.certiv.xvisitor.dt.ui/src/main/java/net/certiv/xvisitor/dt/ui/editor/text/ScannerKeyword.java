package net.certiv.xvisitor.dt.ui.editor.text;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.text.rules.IRule;
import org.eclipse.jface.text.rules.IToken;
import org.eclipse.jface.text.rules.WhitespaceRule;

import net.certiv.dsl.core.preferences.IPrefsManager;
import net.certiv.dsl.core.preferences.consts.Editor;
import net.certiv.dsl.ui.editor.scanners.DslRuleBasedScanner;
import net.certiv.dsl.ui.editor.semantic.StylesManager;
import net.certiv.dsl.ui.editor.text.rules.CharsRule;
import net.certiv.dsl.ui.editor.text.rules.DslWordRule;

public class ScannerKeyword extends DslRuleBasedScanner {

	public static final String[] KEYWORDS = { "grammar", "xvisitor", "options", "@header", "@members" };
	public static final char[] OPERATORS = { '/', '!', '*', '=', '{', '}', '.', ':', ';', '|' };

	private String[] tokenProperties;

	public ScannerKeyword(IPrefsManager store, StylesManager stylesMgr) {
		super(store, stylesMgr);
		initialize();
	}

	@Override
	protected String[] getTokenProperties() {
		if (tokenProperties == null) {
			tokenProperties = new String[] { bind(Editor.EDITOR_KEYWORDS_COLOR), bind(Editor.EDITOR_STRING_COLOR) };
		}
		return tokenProperties;
	}

	@Override
	protected List<IRule> createRules() {
		IToken keyToken = getToken(bind(Editor.EDITOR_KEYWORDS_COLOR));

		DslWordRule keywordRule = new DslWordRule(new WordDetector());
		for (String element : KEYWORDS) {
			keywordRule.addWord(element, keyToken);
		}

		List<IRule> rules = new ArrayList<>();
		rules.add(new WhitespaceRule(new WhitespaceDetector()));
		rules.add(new CharsRule(OPERATORS, keyToken));
		rules.add(keywordRule);
		return rules;
	}
}
