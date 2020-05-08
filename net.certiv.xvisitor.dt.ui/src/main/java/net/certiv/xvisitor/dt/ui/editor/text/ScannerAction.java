package net.certiv.xvisitor.dt.ui.editor.text;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.text.rules.IRule;
import org.eclipse.jface.text.rules.IToken;
import org.eclipse.jface.text.rules.SingleLineRule;
import org.eclipse.jface.text.rules.WhitespaceRule;
import org.eclipse.jface.text.rules.WordRule;

import net.certiv.dsl.core.preferences.IPrefsManager;
import net.certiv.dsl.ui.editor.scanners.AbstractBufferedRuleBasedScanner;
import net.certiv.dsl.ui.editor.text.rules.CharsRule;
import net.certiv.dsl.ui.editor.text.rules.DslWordRule;
import net.certiv.xvisitor.dt.core.preferences.Prefs;

public class ScannerAction extends AbstractBufferedRuleBasedScanner {

	private static final String[] KEYWORDS = { "onEntry", "onExit" };
	private static final char[] SYMBOLS = { //
			':', ';', ',', '=', '+', '-', '!', '?', '*', //
			'~', '|', '&', //
			'(', ')', '{', '}', '[', ']' //
	};

	private String[] tokenProperties;

	public ScannerAction(IPrefsManager store) {
		super(store);
		initialize();
	}

	@Override
	protected String[] getTokenProperties() {
		if (tokenProperties == null) {
			tokenProperties = new String[] { bind(Prefs.EDITOR_KEYWORDS_COLOR), bind(Prefs.EDITOR_STRING_COLOR),
					bind(Prefs.EDITOR_ACTION_COLOR) };
		}
		return tokenProperties;
	}

	@Override
	protected List<IRule> createRules() {
		IToken keyword = getToken(bind(Prefs.EDITOR_KEYWORDS_COLOR));
		IToken string = getToken(bind(Prefs.EDITOR_STRING_COLOR));
		IToken action = getToken(bind(Prefs.EDITOR_ACTION_COLOR));

		DslWordRule keywordRule = new DslWordRule(new WordDetector());
		for (String word : KEYWORDS) {
			keywordRule.addWord(word, keyword);
		}

		List<IRule> rules = new ArrayList<IRule>();

		rules.add(keywordRule);
		rules.add(new CharsRule(SYMBOLS, keyword));
		rules.add(new WordRule(new RefWordDetector(), action, true)); // '$' words
		rules.add(new SingleLineRule("\"", "\"", string, '\\', true));
		rules.add(new SingleLineRule("'", "'", string, '\\', true));

		rules.add(new WhitespaceRule(new WhitespaceDetector()));
		return rules;
	}
}