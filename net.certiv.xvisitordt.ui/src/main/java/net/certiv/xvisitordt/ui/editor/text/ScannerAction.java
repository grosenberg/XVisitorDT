package net.certiv.xvisitordt.ui.editor.text;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.text.rules.IRule;
import org.eclipse.jface.text.rules.IToken;
import org.eclipse.jface.text.rules.SingleLineRule;
import org.eclipse.jface.text.rules.WhitespaceRule;
import org.eclipse.jface.text.rules.WordRule;

import net.certiv.dsl.core.preferences.IDslPrefsManager;
import net.certiv.dsl.ui.editor.text.AbstractBufferedRuleBasedScanner;
import net.certiv.dsl.ui.editor.text.rules.CharsRule;
import net.certiv.dsl.ui.editor.text.rules.DslWordRule;
import net.certiv.xvisitordt.core.preferences.PrefsKey;

public class ScannerAction extends AbstractBufferedRuleBasedScanner {

	private static final String[] KEYWORDS = { "onEntry", "onExit" };
	private static final char[] SYMBOLS = { //
			':', ';', ',', '=', '+', '-', '!', '?', '*', //
			'~', '|', '&', //
			'(', ')', '{', '}', '[', ']' //
	};

	private String[] tokenProperties;

	public ScannerAction(IDslPrefsManager store) {
		super(store);
		initialize();
	}

	@Override
	protected String[] getTokenProperties() {
		if (tokenProperties == null) {
			tokenProperties = new String[] { bind(PrefsKey.EDITOR_KEYWORDS_COLOR), bind(PrefsKey.EDITOR_STRING_COLOR),
					bind(PrefsKey.EDITOR_ACTION_COLOR) };
		}
		return tokenProperties;
	}

	@Override
	protected List<IRule> createRules() {
		IToken keyword = getToken(bind(PrefsKey.EDITOR_KEYWORDS_COLOR));
		IToken string = getToken(bind(PrefsKey.EDITOR_STRING_COLOR));
		IToken action = getToken(bind(PrefsKey.EDITOR_ACTION_COLOR));

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
