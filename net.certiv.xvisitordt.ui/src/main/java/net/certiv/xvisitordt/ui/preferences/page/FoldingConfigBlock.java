package net.certiv.xvisitordt.ui.preferences.page;

import java.util.List;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.forms.widgets.FormToolkit;

import net.certiv.dsl.core.color.DslColorManager;
import net.certiv.dsl.core.preferences.DslPrefsManagerDelta;
import net.certiv.dsl.ui.preferences.blocks.AbstractFoldingConfigBlock;
import net.certiv.dsl.ui.preferences.pages.IDslPreferencePage;
import net.certiv.xvisitordt.core.preferences.Prefs;

public class FoldingConfigBlock extends AbstractFoldingConfigBlock {

	public FoldingConfigBlock(IDslPreferencePage page, DslPrefsManagerDelta delta, FormToolkit formkit,
			DslColorManager colorMgr) {
		super(page, delta, formkit, colorMgr);
	}

	@Override
	public List<String> getDeltaFoldingKeys(List<String> keys) {

		keys.add(Prefs.EDITOR_FOLDING_COMMENT_ML);
		keys.add(Prefs.EDITOR_FOLDING_COMMENT_JD);

		keys.add(Prefs.EDITOR_FOLDING_OPTIONS);
		keys.add(Prefs.EDITOR_FOLDING_TOKENS);
		keys.add(Prefs.EDITOR_FOLDING_HEADER);
		keys.add(Prefs.EDITOR_FOLDING_MEMBERS);
		keys.add(Prefs.EDITOR_FOLDING_RULECATCH);
		keys.add(Prefs.EDITOR_FOLDING_SCOPE);
		keys.add(Prefs.EDITOR_FOLDING_INIT);
		keys.add(Prefs.EDITOR_FOLDING_AFTER);
		keys.add(Prefs.EDITOR_FOLDING_ACTION);
		keys.add(Prefs.EDITOR_FOLDING_CATCH);
		keys.add(Prefs.EDITOR_FOLDING_FINALLY);

		return keys;
	}

	@Override
	public void addCustomControls(Composite initialFolding) {
		addCheckBox(initialFolding, "Options", Prefs.EDITOR_FOLDING_OPTIONS, 0);
		addCheckBox(initialFolding, "Scope", Prefs.EDITOR_FOLDING_SCOPE, 0);
		addCheckBox(initialFolding, "Tokens", Prefs.EDITOR_FOLDING_TOKENS, 0);
		addCheckBox(initialFolding, "Init", Prefs.EDITOR_FOLDING_INIT, 0);

		addCheckBox(initialFolding, "Header", Prefs.EDITOR_FOLDING_HEADER, 0);
		addCheckBox(initialFolding, "After", Prefs.EDITOR_FOLDING_AFTER, 0);
		addCheckBox(initialFolding, "Members", Prefs.EDITOR_FOLDING_MEMBERS, 0);
		addCheckBox(initialFolding, "Action", Prefs.EDITOR_FOLDING_ACTION, 0);

		addCheckBox(initialFolding, "RuleCatch", Prefs.EDITOR_FOLDING_RULECATCH, 0);

		addCheckBox(initialFolding, "Catch", Prefs.EDITOR_FOLDING_CATCH, 0);
		addCheckBox(initialFolding, "JavaDoc Comments", Prefs.EDITOR_FOLDING_COMMENT_JD, 0);
		addCheckBox(initialFolding, "Finally", Prefs.EDITOR_FOLDING_FINALLY, 0);
		addCheckBox(initialFolding, "Multi-Line Comments", Prefs.EDITOR_FOLDING_COMMENT_ML, 0);
	}
}
