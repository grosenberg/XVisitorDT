package net.certiv.xvisitordt.ui.preferences.formatter;

import static net.certiv.xvisitordt.core.preferences.PrefsKey.*;

import java.net.URL;

import org.eclipse.swt.widgets.Composite;

import net.certiv.dsl.core.DslCore;
import net.certiv.dsl.ui.DslUI;
import net.certiv.dsl.ui.formatter.FormatterModifyTabPage;
import net.certiv.dsl.ui.formatter.IFormatterModifyDialog;
import net.certiv.dsl.ui.preferences.IControlCreationManager;
import net.certiv.xvisitordt.core.XVisitorCore;
import net.certiv.xvisitordt.ui.XVisitorUI;

public class TabPageWhiteSpace extends FormatterModifyTabPage implements IFormatterFieldLabels {

	public TabPageWhiteSpace(IFormatterModifyDialog dialog) {
		super(dialog);
	}

	@Override
	public DslUI getDslUI() {
		return XVisitorUI.getDefault();
	}

	@Override
	public DslCore getDslCore() {
		return XVisitorCore.getDefault();
	}

	protected void createOptions(final IControlCreationManager manager, Composite parent) {

		Composite header = createOptionGroup(parent, "Grammar header", 2);
		manager.createCombo(header, bind(SPACE_SEMI_HDR), LBL_SEMI, BfNoValues, BfNoLabels);

		// //////////////////////////////////////////////////////////////////////////////

		Composite options = createOptionGroup(parent, "Options", 2);
		manager.createCombo(options, bind(SPACE_EQ_OPT), LBL_ASSIGN, AllValues, AllLabels);
		manager.createCombo(options, bind(SPACE_SEMI_OPT), LBL_SEMI, BfNoValues, BfNoLabels);

		// //////////////////////////////////////////////////////////////////////////////

		Composite tokens = createOptionGroup(parent, "Tokens", 2);
		manager.createCombo(tokens, bind(SPACE_LBRACE_TOK), LBL_BRC_OPEN, AllValues, AllLabels);
		manager.createCombo(tokens, bind(SPACE_RBRACE_TOK), LBL_BRC_CLOSE, BfNoValues, BfNoLabels);
		manager.createCombo(tokens, bind(SPACE_COMMA_TOK), LBL_SEMI, AllValues, AllLabels);

		// //////////////////////////////////////////////////////////////////////////////

		Composite rule = createOptionGroup(parent, "Rules", 2);

		manager.createCombo(rule, bind(SPACE_COLON), LBL_COLON, AllValues, AllLabels);
		manager.createCombo(rule, bind(SPACE_ALT_OR), LBL_ALT, AllValues, AllLabels);
		manager.createCombo(rule, bind(SPACE_SEMI_RULE), LBL_SEMI, AllValues, AllLabels);

		manager.createCombo(rule, bind(INDENT_AT), LBL_AT, BfNoValues, BfNoLabels);
		manager.createCombo(rule, bind(SPACE_LBRACE_AT), "@ " + LBL_BRC_OPEN, AllValues, AllLabels);
		manager.createCombo(rule, bind(SPACE_RBRACE_AT), "@ " + LBL_BRC_CLOSE, AllValues, AllLabels);

		manager.createCombo(rule, bind(SPACE_LBRACKET), LBL_BRK_OPEN, AllValues, AllLabels);
		manager.createCombo(rule, bind(SPACE_RBRACKET), LBL_BRK_CLOSE, AllValues, AllLabels);
		manager.createCombo(rule, bind(LIST_ID), LBL_COMMA, AllValues, AllLabels);

		manager.createCombo(rule, bind(SPACE_LBRACE), "Action " + LBL_BRC_OPEN, AllValues, AllLabels);
		manager.createCombo(rule, bind(SPACE_RBRACE), "Action " + LBL_BRC_CLOSE, AllValues, AllLabels);

	}

	protected URL getPreviewContent() {
		return getClass().getResource("WhiteSpacePreview.g4"); //$NON-NLS-1$
	}
}
