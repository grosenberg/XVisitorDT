package net.certiv.xvisitordt.ui.editor.text;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.text.rules.IRule;
import org.eclipse.jface.text.rules.IToken;
import org.eclipse.jface.text.rules.WhitespaceRule;

import net.certiv.dsl.core.preferences.IDslPrefsManager;
import net.certiv.dsl.ui.editor.text.AbstractBufferedRuleBasedScanner;
import net.certiv.dsl.ui.editor.text.rules.CharsRule;
import net.certiv.dsl.ui.editor.text.rules.DslWordRule;
import net.certiv.xvisitordt.core.preferences.PrefsKey;

public class ScannerDefault extends AbstractBufferedRuleBasedScanner {

	public static final String[] KEYWORDS = { "grammar", "xvisitor", "options", "@header", "@members" };
	public static final char[] OPERATORS = { '/', '!', '*', '=', '{', '}', '.', ':', ';', '|' };

	private String[] tokenProperties;

	public ScannerDefault(IDslPrefsManager store) {
		super(store);
		initialize();
	}

	@Override
	protected String[] getTokenProperties() {
		if (tokenProperties == null) {
			tokenProperties = new String[] { bind(PrefsKey.EDITOR_KEYWORDS_COLOR), bind(PrefsKey.EDITOR_STRING_COLOR) };
		}
		return tokenProperties;
	}

	@Override
	protected List<IRule> createRules() {
		IToken keyToken = getToken(bind(PrefsKey.EDITOR_KEYWORDS_COLOR));

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
