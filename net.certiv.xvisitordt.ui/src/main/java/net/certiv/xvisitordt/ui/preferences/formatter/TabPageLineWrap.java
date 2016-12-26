package net.certiv.xvisitordt.ui.preferences.formatter;

import java.net.URL;

import org.eclipse.swt.widgets.Composite;

import net.certiv.dsl.core.DslCore;
import net.certiv.dsl.ui.DslUI;
import net.certiv.dsl.ui.formatter.FormatterModifyTabPage;
import net.certiv.dsl.ui.formatter.IFormatterModifyDialog;
import net.certiv.dsl.ui.preferences.IControlCreationManager;
import net.certiv.xvisitordt.core.XVisitorCore;
import net.certiv.xvisitordt.core.preferences.PrefsKey;
import net.certiv.xvisitordt.ui.XVisitorUI;

public class TabPageLineWrap extends FormatterModifyTabPage {

	public TabPageLineWrap(IFormatterModifyDialog dialog) {
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

		Composite opTok = createOptionGroup(parent, "Options/Tokens", 2);

		manager.createCombo(opTok, bind(PrefsKey.WRAP_LBRACE_OT), "Block open ( \'{\' )", AllValues, AllLabels);
		manager.createCombo(opTok, bind(PrefsKey.WRAP_RBRACE_OT), "Block close ( \'}\' )", AllValues, AllLabels);

		manager.createCombo(opTok, bind(PrefsKey.WRAP_EQ), "Assignment op ( \'=\' )", AllValues, AllLabels);
		manager.createCombo(opTok, bind(PrefsKey.WRAP_SEMI), "Semicolon ( \';\' )", AllValues, AllLabels);

		// ///////////////////////////////////////////////////////////////////////////////////////

		Composite rule = createOptionGroup(parent, "Lexer/Parser Rules", 2);

		manager.createCombo(rule, bind(PrefsKey.WRAP_FRAGMENT), "Lexer Fragment key word ", AfNoValues, AfNoLabels);
		manager.createCombo(rule, bind(PrefsKey.WRAP_MODIFIER), "Parser Modifier key word ", AfNoValues, AfNoLabels);

		manager.createCombo(rule, bind(PrefsKey.WRAP_COLON), "Colon ( \':\' )", AllValues, AllLabels);
		manager.createCombo(rule, bind(PrefsKey.WRAP_ALT_OR), "Alt separator ( \'|\' )", AllValues, AllLabels);
		manager.createCombo(rule, bind(PrefsKey.WRAP_SEMI_RULE), "Semicolon ( \';\' )", AllValues, AllLabels);

		manager.createCombo(rule, bind(PrefsKey.WRAP_AT), "AT statement ( \'@\' )", BfNoValues, BfNoLabels);
		manager.createCombo(rule, bind(PrefsKey.WRAP_LBRACE_AT), "AT open brace ( \'{\' )", AllValues, AllLabels);
		manager.createCombo(rule, bind(PrefsKey.WRAP_RBRACE_AT), "AT close brace ( \'}\' )", AllValues, AllLabels);

		manager.createCombo(rule, bind(PrefsKey.WRAP_LBRACE), "Action block open ( \'{\' )", AllValues, AllLabels);
		manager.createCombo(rule, bind(PrefsKey.WRAP_RBRACE), "Action block close ( \'}\' )", AllValues, AllLabels);
	}

	protected URL getPreviewContent() {
		return getClass().getResource("LineWrapPreview.g4"); //$NON-NLS-1$
	}
}
