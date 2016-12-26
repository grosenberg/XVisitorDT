package net.certiv.xvisitordt.ui.editor.text;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.text.rules.IRule;
import org.eclipse.jface.text.rules.IToken;
import org.eclipse.jface.text.rules.SingleLineRule;
import org.eclipse.jface.text.rules.WhitespaceRule;

import net.certiv.dsl.core.preferences.IDslPrefsManager;
import net.certiv.dsl.ui.text.AbstractBufferedRuleBasedScanner;
import net.certiv.xvisitordt.core.preferences.PrefsKey;

public class ScannerString extends AbstractBufferedRuleBasedScanner {

	private String[] fgTokenProperties;

	public ScannerString(IDslPrefsManager store) {
		super(store);
		initialize();
	}

	@Override
	protected String[] getTokenProperties() {
		if (fgTokenProperties == null) {
			fgTokenProperties = new String[] { bind(PrefsKey.EDITOR_STRING_COLOR) };
		}
		return fgTokenProperties;
	}

	@Override
	protected List<IRule> createRules() {
		IToken token = getToken(bind(PrefsKey.EDITOR_STRING_COLOR));
		setDefaultReturnToken(token);

		List<IRule> rules = new ArrayList<IRule>();
		rules.add(new SingleLineRule("\"", "\"", token, '\\', true));
		rules.add(new SingleLineRule("'", "'", token, '\\', true));
		rules.add(new WhitespaceRule(new WhitespaceDetector()));
		return rules;
	}
}
