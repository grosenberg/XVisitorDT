package net.certiv.xvisitordt.ui.editor.text;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.text.rules.IRule;
import org.eclipse.jface.text.rules.IToken;
import org.eclipse.jface.text.rules.WhitespaceRule;

import net.certiv.dsl.core.preferences.IDslPrefsManager;
import net.certiv.dsl.ui.editor.scanners.AbstractBufferedRuleBasedScanner;
import net.certiv.dsl.ui.editor.text.rules.CharsRule;
import net.certiv.dsl.ui.editor.text.rules.DslWordRule;
import net.certiv.xvisitordt.core.preferences.Prefs;

public class ScannerKeyword extends AbstractBufferedRuleBasedScanner {

	public static final String[] KEYWORDS = { "grammar", "xvisitor", "options", "@header", "@members" };
	public static final char[] OPERATORS = { '/', '!', '*', '=', '{', '}', '.', ':', ';', '|' };

	private String[] tokenProperties;

	public ScannerKeyword(IDslPrefsManager store) {
		super(store);
		initialize();
	}

	@Override
	protected String[] getTokenProperties() {
		if (tokenProperties == null) {
			tokenProperties = new String[] { bind(Prefs.EDITOR_KEYWORDS_COLOR), bind(Prefs.EDITOR_STRING_COLOR) };
		}
		return tokenProperties;
	}

	@Override
	protected List<IRule> createRules() {
		IToken keyToken = getToken(bind(Prefs.EDITOR_KEYWORDS_COLOR));

		DslWordRule keywordRule = new DslWordRule(new WordDetector());
		for (String element : KEYWORDS) {
			keywordRule.addWord(element, keyToken);
		}

		List<IRule> rules = new ArrayList<IRule>();
		rules.add(new WhitespaceRule(new WhitespaceDetector()));
		rules.add(new CharsRule(OPERATORS, keyToken));
		rules.add(keywordRule);
		return rules;
	}
}
