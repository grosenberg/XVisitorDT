package net.certiv.xvisitordt.core.preferences;

import org.eclipse.swt.graphics.RGB;

import net.certiv.dsl.core.DslCore;
import net.certiv.dsl.core.preferences.DslPrefsInit;
import net.certiv.xvisitordt.core.XVisitorCore;

/**
 * Initializer for the preferences unique to this plugin.
 */
public class PrefsInitializer extends DslPrefsInit {

	public PrefsInitializer() {
		super();
	}

	@Override
	public DslCore getDslCore() {
		return XVisitorCore.getDefault();
	}

	@Override
	public void initializeDefaultPreferences() {
		super.initializeDefaultPreferences();

		setRGB(PrefsKey.EDITOR_ACTION_COLOR, new RGB(70, 150, 170));
		setRGB(PrefsKey.EDITOR_ACTION_NAMED_COLOR, new RGB(220, 0, 0));

		setBool(PrefsKey.EDITOR_FOLDING_COMMENT_ML, true);
		setBool(PrefsKey.EDITOR_FOLDING_COMMENT_JD, false);
		setBool(PrefsKey.EDITOR_FOLDING_ACTION, false);
		setBool(PrefsKey.EDITOR_FOLDING_TOKENS, false);
		setBool(PrefsKey.EDITOR_FOLDING_OPTIONS, false);
		setBool(PrefsKey.EDITOR_FOLDING_INIT, false);
		setBool(PrefsKey.EDITOR_FOLDING_AFTER, false);
		setBool(PrefsKey.EDITOR_FOLDING_HEADER, false);
		setBool(PrefsKey.EDITOR_FOLDING_MEMBERS, false);
		setBool(PrefsKey.EDITOR_FOLDING_SCOPE, false);
		setBool(PrefsKey.EDITOR_FOLDING_RULECATCH, false);
		setBool(PrefsKey.EDITOR_FOLDING_CATCH, false);
		setBool(PrefsKey.EDITOR_FOLDING_FINALLY, false);

		setString(PrefsKey.BUILDER_USE, PrefsKey.BUILDER_USE_CURRENT);
		setString(PrefsKey.BUILDER_REL_PATH, "./gen/");
		setString(PrefsKey.BUILDER_ABS_PATH, "");

		// Initialize formatter preferences
		setString(PrefsKey.SPACE_COLON, PrefsKey.AFTER);
		setString(PrefsKey.SPACE_EQ_OPT, PrefsKey.AROUND);
		setString(PrefsKey.SPACE_ALT_OR, PrefsKey.AROUND);
		setString(PrefsKey.SPACE_LBRACKET, PrefsKey.AFTER);
		setString(PrefsKey.SPACE_RBRACKET, PrefsKey.BEFORE);
		setString(PrefsKey.SPACE_COMMA, PrefsKey.AFTER);
		setString(PrefsKey.SPACE_SEMI_RULE, PrefsKey.AROUND);
		setString(PrefsKey.SPACE_LBRACE, PrefsKey.AROUND);
		setString(PrefsKey.SPACE_RBRACE, PrefsKey.AFTER);
		setString(PrefsKey.SPACE_LBRACE_AT, PrefsKey.AROUND);
		setString(PrefsKey.SPACE_RBRACE_AT, PrefsKey.AROUND);

		setString(PrefsKey.WRAP_FRAGMENT, PrefsKey.AFTER);
		setString(PrefsKey.WRAP_MODIFIER, PrefsKey.AFTER);
		setString(PrefsKey.WRAP_COLON, PrefsKey.BEFORE);
		setString(PrefsKey.WRAP_ALT_OR, PrefsKey.BEFORE);
		setString(PrefsKey.WRAP_EQ, PrefsKey.NONE);
		setString(PrefsKey.WRAP_COMMA, PrefsKey.AFTER);
		setString(PrefsKey.WRAP_SEMI, PrefsKey.AFTER);
		setString(PrefsKey.WRAP_SEMI_RULE, PrefsKey.AFTER);
		setString(PrefsKey.WRAP_LBRACE, PrefsKey.AFTER);
		setString(PrefsKey.WRAP_RBRACE, PrefsKey.AROUND);
		setString(PrefsKey.WRAP_AT, PrefsKey.BEFORE);
		setString(PrefsKey.WRAP_LBRACE_AT, PrefsKey.NONE);
		setString(PrefsKey.WRAP_RBRACE_AT, PrefsKey.NONE);
		setString(PrefsKey.WRAP_LBRACE_OT, PrefsKey.AFTER);
		setString(PrefsKey.WRAP_RBRACE_OT, PrefsKey.AROUND);

		setString(PrefsKey.INDENT_AT, PrefsKey.BEFORE);
		setString(PrefsKey.LIST_ID, PrefsKey.AFTER);

		setBool(PrefsKey.FORMATTER_COMMENT_HEADER_ENABLE, false);
		setBool(PrefsKey.FORMATTER_COMMENT_FORMAT, false);
		setBool(PrefsKey.FORMATTER_INDENT_COMMENT_MULTILINE, false);
		setBool(PrefsKey.FORMATTER_INDENT_COMMENT_SINGLELINE, false);
	}
}
