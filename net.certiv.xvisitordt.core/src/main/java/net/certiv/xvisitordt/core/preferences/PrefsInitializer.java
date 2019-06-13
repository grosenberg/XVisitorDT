package net.certiv.xvisitordt.core.preferences;

import org.eclipse.swt.graphics.RGB;

import net.certiv.dsl.core.DslCore;
import net.certiv.dsl.core.preferences.DslPrefsInit;
import net.certiv.dsl.core.preferences.consts.Formatter;
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
		setString(Prefs.SPACE_COLON, Formatter.AFTER);
		setString(Prefs.SPACE_EQ_OPT, Formatter.AROUND);
		setString(Prefs.SPACE_ALT_OR, Formatter.AROUND);
		setString(Prefs.SPACE_LBRACKET, Formatter.AFTER);
		setString(Prefs.SPACE_RBRACKET, Formatter.BEFORE);
		setString(Prefs.SPACE_COMMA, Formatter.AFTER);
		setString(Prefs.SPACE_SEMI_RULE, Formatter.AROUND);
		setString(Prefs.SPACE_LBRACE, Formatter.AROUND);
		setString(Prefs.SPACE_RBRACE, Formatter.AFTER);
		setString(Prefs.SPACE_LBRACE_AT, Formatter.AROUND);
		setString(Prefs.SPACE_RBRACE_AT, Formatter.AROUND);

		setString(Prefs.WRAP_FRAGMENT, Formatter.AFTER);
		setString(Prefs.WRAP_MODIFIER, Formatter.AFTER);
		setString(Prefs.WRAP_COLON, Formatter.BEFORE);
		setString(Prefs.WRAP_ALT_OR, Formatter.BEFORE);
		setString(Prefs.WRAP_EQ, Formatter.NONE);
		setString(Prefs.WRAP_COMMA, Formatter.AFTER);
		setString(Prefs.WRAP_SEMI, Formatter.AFTER);
		setString(Prefs.WRAP_SEMI_RULE, Formatter.AFTER);
		setString(Prefs.WRAP_LBRACE, Formatter.AFTER);
		setString(Prefs.WRAP_RBRACE, Formatter.AROUND);
		setString(Prefs.WRAP_AT, Formatter.BEFORE);
		setString(Prefs.WRAP_LBRACE_AT, Formatter.NONE);
		setString(Prefs.WRAP_RBRACE_AT, Formatter.NONE);
		setString(Prefs.WRAP_LBRACE_OT, Formatter.AFTER);
		setString(Prefs.WRAP_RBRACE_OT, Formatter.AROUND);

		setString(Prefs.INDENT_AT, Formatter.BEFORE);
		setString(Prefs.LIST_ID, Formatter.AFTER);

		// formatter

		setString(Formatter.FORMATTER_CORPUS_LANGUAGE, "xvisitor");
		setBool(Formatter.FORMATTER_INDENT_COMMENT_MULTILINE, false);
		setBool(Formatter.FORMATTER_INDENT_COMMENT_SINGLELINE, false);
		setBool(Formatter.FORMATTER_NATIVE_ENABLE, false);

	}
}
