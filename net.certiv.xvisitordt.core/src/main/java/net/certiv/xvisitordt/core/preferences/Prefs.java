package net.certiv.xvisitordt.core.preferences;

import net.certiv.dsl.core.preferences.DslPrefsKey;

/**
 * Preference keys that are unique to this Dsl plugin. Prefix with the Dsl plugin ID just to ensure.
 */
public class Prefs extends DslPrefsKey {

	// @formatter:off
	public static final String EDITOR_ACTION_COLOR = "{DSL_ID}" + ".action";
	public static final String EDITOR_ACTION_BOLD = EDITOR_ACTION_COLOR + EDITOR_BOLD_SUFFIX;
	public static final String EDITOR_ACTION_ITALIC = EDITOR_ACTION_COLOR + EDITOR_ITALIC_SUFFIX;
	public static final String EDITOR_ACTION_STRIKETHROUGH = EDITOR_ACTION_COLOR + EDITOR_STRIKETHROUGH_SUFFIX;
	public static final String EDITOR_ACTION_UNDERLINE = EDITOR_ACTION_COLOR + EDITOR_UNDERLINE_SUFFIX;

	public static final String EDITOR_ACTION_NAMED_COLOR = "{DSL_ID}" + ".actionNamed";
	public static final String EDITOR_ACTION_NAMED_BOLD = EDITOR_ACTION_NAMED_COLOR + EDITOR_BOLD_SUFFIX;
	public static final String EDITOR_ACTION_NAMED_ITALIC = EDITOR_ACTION_NAMED_COLOR + EDITOR_ITALIC_SUFFIX;
	public static final String EDITOR_ACTION_NAMED_STRIKETHROUGH = EDITOR_ACTION_NAMED_COLOR  + EDITOR_STRIKETHROUGH_SUFFIX;
	public static final String EDITOR_ACTION_NAMED_UNDERLINE = EDITOR_ACTION_NAMED_COLOR + EDITOR_UNDERLINE_SUFFIX;
	// @formatter:on

	public static final String EDITOR_FOLDING_COMMENT_ML = "{DSL_ID}" + ".editorFoldingCommentML";
	public static final String EDITOR_FOLDING_COMMENT_JD = "{DSL_ID}" + ".editorFoldingCommentJD";
	public static final String EDITOR_FOLDING_ACTION = "{DSL_ID}" + ".editorFoldingActions";
	public static final String EDITOR_FOLDING_TOKENS = "{DSL_ID}" + ".editorFoldingTokens";
	public static final String EDITOR_FOLDING_OPTIONS = "{DSL_ID}" + ".editorFoldingOptions";
	public static final String EDITOR_FOLDING_INIT = "{DSL_ID}" + ".editorFoldingInit";
	public static final String EDITOR_FOLDING_AFTER = "{DSL_ID}" + ".editorFoldingAfter";
	public static final String EDITOR_FOLDING_HEADER = "{DSL_ID}" + ".editorFoldingHeader";
	public static final String EDITOR_FOLDING_MEMBERS = "{DSL_ID}" + ".editorFoldingMembers";
	public static final String EDITOR_FOLDING_SCOPE = "{DSL_ID}" + ".editorFoldingScope";
	public static final String EDITOR_FOLDING_RULECATCH = "{DSL_ID}" + ".editorFoldingRulecatch";
	public static final String EDITOR_FOLDING_CATCH = "{DSL_ID}" + ".editorFoldingCatch";
	public static final String EDITOR_FOLDING_FINALLY = "{DSL_ID}" + ".editorFoldingFinally";

	// custom formatter prefixes
	private static final String FORMAT_SPACE = "{DSL_ID}" + ".formatSpace";
	private static final String FORMAT_WRAP = "{DSL_ID}" + ".formatWrap";
	private static final String FORMAT_INDENT = "{DSL_ID}" + ".formatIndent";
	private static final String FORMAT_LIST = "{DSL_ID}" + ".formatList";
	private static final String FORMAT_STYLE = "{DSL_ID}" + ".formatStyle";

	// custom formatter options

	// header
	public static final String SPACE_SEMI_HDR = FORMAT_SPACE + "SemiHdr";

	// options
	public static final String SPACE_SEMI_OPT = FORMAT_SPACE + "SemiOptions";
	public static final String SPACE_EQ_OPT = FORMAT_SPACE + "Eq";

	// tokens
	public static final String SPACE_LBRACE_TOK = FORMAT_SPACE + "OBraceTok";
	public static final String SPACE_RBRACE_TOK = FORMAT_SPACE + "CBraceTok";
	public static final String SPACE_COMMA_TOK = FORMAT_SPACE + "CommaTok";

	// rules

	public static final String SPACE_COLON = FORMAT_SPACE + "Colon";
	public static final String SPACE_ALT_OR = FORMAT_SPACE + "AltOr";
	public static final String SPACE_LBRACKET = FORMAT_SPACE + "OBracket";
	public static final String SPACE_RBRACKET = FORMAT_SPACE + "CBracket";
	public static final String SPACE_COMMA = FORMAT_SPACE + "Comma";
	public static final String SPACE_SEMI_RULE = FORMAT_SPACE + "SemiRule";

	public static final String SPACE_LBRACE = FORMAT_SPACE + "OBrace";
	public static final String SPACE_RBRACE = FORMAT_SPACE + "CBrace";

	public static final String SPACE_LBRACE_AT = FORMAT_SPACE + "OBraceAt";
	public static final String SPACE_RBRACE_AT = FORMAT_SPACE + "CBraceAt";

	public static final String WRAP_FRAGMENT = FORMAT_WRAP + "Fragment";
	public static final String WRAP_MODIFIER = FORMAT_WRAP + "Modifier";
	public static final String WRAP_COLON = FORMAT_WRAP + "Colon";
	public static final String WRAP_ALT_OR = FORMAT_WRAP + "AltOR";
	public static final String WRAP_EQ = FORMAT_WRAP + "Eq";
	public static final String WRAP_COMMA = FORMAT_WRAP + "Comma";
	public static final String WRAP_SEMI = FORMAT_WRAP + "Semi";
	public static final String WRAP_SEMI_RULE = FORMAT_WRAP + "SemiRule";
	public static final String WRAP_LBRACE = FORMAT_WRAP + "OBrace";
	public static final String WRAP_RBRACE = FORMAT_WRAP + "CBrace";
	public static final String WRAP_AT = FORMAT_WRAP + "At";
	public static final String WRAP_LBRACE_AT = FORMAT_WRAP + "OBraceAt";
	public static final String WRAP_RBRACE_AT = FORMAT_WRAP + "CBraceAt";
	public static final String WRAP_LBRACE_OT = FORMAT_WRAP + "OBraceOT";
	public static final String WRAP_RBRACE_OT = FORMAT_WRAP + "CBraceOT";

	public static final String INDENT_AT = FORMAT_INDENT + "At";
	public static final String LIST_ID = FORMAT_LIST + "Id";

	public static final String STYLE_RULE_LEAD = FORMAT_STYLE + "RuleLead";
	public static final String STYLE_RULE_TRAIL = FORMAT_STYLE + "RuleTrail";

	// These are for the core/builder
	public static final String BUILDER_USE = "builderUse";
	public static final String BUILDER_USE_GRAMMAR = "builderLocGrammar";
	public static final String BUILDER_USE_RELATIVE = "builderLocRelative";
	public static final String BUILDER_USE_ABSOLUTE = "builderLocAbsolute";
	public static final String BUILDER_USE_CURRENT = "builderLocCurrent";
	public static final String BUILDER_REL_PATH = "builderRelPath";
	public static final String BUILDER_ABS_PATH = "builderAbsPath";

}
