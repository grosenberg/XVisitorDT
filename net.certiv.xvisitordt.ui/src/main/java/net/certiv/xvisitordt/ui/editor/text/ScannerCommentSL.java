package net.certiv.xvisitordt.ui.editor.text;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.text.rules.EndOfLineRule;
import org.eclipse.jface.text.rules.IRule;
import org.eclipse.jface.text.rules.IToken;
import org.eclipse.jface.text.rules.WhitespaceRule;

import net.certiv.dsl.core.preferences.IDslPrefsManager;
import net.certiv.dsl.ui.editor.text.AbstractBufferedRuleBasedScanner;
import net.certiv.xvisitordt.core.preferences.PrefsKey;

public class ScannerCommentSL extends AbstractBufferedRuleBasedScanner {

	private String[] tokenProperties;

	public ScannerCommentSL(IDslPrefsManager store) {
		super(store);
		initialize();
	}

	@Override
	protected String[] getTokenProperties() {
		if (tokenProperties == null) {
			tokenProperties = new String[] { bind(PrefsKey.EDITOR_COMMENT_SL_COLOR) };
		}
		return tokenProperties;
	}

	@Override
	protected List<IRule> createRules() {
		List<IRule> rules = new ArrayList<IRule>();
		IToken token = getToken(bind(PrefsKey.EDITOR_COMMENT_SL_COLOR));
		setDefaultReturnToken(token);

		rules.add(new EndOfLineRule("#", token, '\\'));
		rules.add(new WhitespaceRule(new WhitespaceDetector()));
		return rules;
	}
}
