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

		setRGB(Prefs.EDITOR_ACTION_COLOR, new RGB(70, 150, 170));
		setRGB(Prefs.EDITOR_ACTION_NAMED_COLOR, new RGB(220, 0, 0));

		setBool(Prefs.EDITOR_FOLDING_COMMENT_ML, true);
		setBool(Prefs.EDITOR_FOLDING_COMMENT_JD, false);
		setBool(Prefs.EDITOR_FOLDING_ACTION, false);
		setBool(Prefs.EDITOR_FOLDING_TOKENS, false);
		setBool(Prefs.EDITOR_FOLDING_OPTIONS, false);
		setBool(Prefs.EDITOR_FOLDING_INIT, false);
		setBool(Prefs.EDITOR_FOLDING_AFTER, false);
		setBool(Prefs.EDITOR_FOLDING_HEADER, false);
		setBool(Prefs.EDITOR_FOLDING_MEMBERS, false);
		setBool(Prefs.EDITOR_FOLDING_SCOPE, false);
		setBool(Prefs.EDITOR_FOLDING_RULECATCH, false);
		setBool(Prefs.EDITOR_FOLDING_CATCH, false);
		setBool(Prefs.EDITOR_FOLDING_FINALLY, false);

		setString(Prefs.BUILDER_USE, Prefs.BUILDER_USE_CURRENT);
		setString(Prefs.BUILDER_REL_PATH, "./gen/");
		setString(Prefs.BUILDER_ABS_PATH, "");

		// Initialize formatter preferences
		setString(Prefs.SPACE_COLON, Prefs.AFTER);
		setString(Prefs.SPACE_EQ_OPT, Prefs.AROUND);
		setString(Prefs.SPACE_ALT_OR, Prefs.AROUND);
		setString(Prefs.SPACE_LBRACKET, Prefs.AFTER);
		setString(Prefs.SPACE_RBRACKET, Prefs.BEFORE);
		setString(Prefs.SPACE_COMMA, Prefs.AFTER);
		setString(Prefs.SPACE_SEMI_RULE, Prefs.AROUND);
		setString(Prefs.SPACE_LBRACE, Prefs.AROUND);
		setString(Prefs.SPACE_RBRACE, Prefs.AFTER);
		setString(Prefs.SPACE_LBRACE_AT, Prefs.AROUND);
		setString(Prefs.SPACE_RBRACE_AT, Prefs.AROUND);

		setString(Prefs.WRAP_FRAGMENT, Prefs.AFTER);
		setString(Prefs.WRAP_MODIFIER, Prefs.AFTER);
		setString(Prefs.WRAP_COLON, Prefs.BEFORE);
		setString(Prefs.WRAP_ALT_OR, Prefs.BEFORE);
		setString(Prefs.WRAP_EQ, Prefs.NONE);
		setString(Prefs.WRAP_COMMA, Prefs.AFTER);
		setString(Prefs.WRAP_SEMI, Prefs.AFTER);
		setString(Prefs.WRAP_SEMI_RULE, Prefs.AFTER);
		setString(Prefs.WRAP_LBRACE, Prefs.AFTER);
		setString(Prefs.WRAP_RBRACE, Prefs.AROUND);
		setString(Prefs.WRAP_AT, Prefs.BEFORE);
		setString(Prefs.WRAP_LBRACE_AT, Prefs.NONE);
		setString(Prefs.WRAP_RBRACE_AT, Prefs.NONE);
		setString(Prefs.WRAP_LBRACE_OT, Prefs.AFTER);
		setString(Prefs.WRAP_RBRACE_OT, Prefs.AROUND);

		setString(Prefs.INDENT_AT, Prefs.BEFORE);
		setString(Prefs.LIST_ID, Prefs.AFTER);
	}
}
