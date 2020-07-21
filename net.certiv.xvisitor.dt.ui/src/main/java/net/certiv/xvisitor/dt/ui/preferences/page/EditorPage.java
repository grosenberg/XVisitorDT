package net.certiv.xvisitor.dt.ui.preferences.page;

import net.certiv.dsl.core.DslCore;
import net.certiv.dsl.core.preferences.PrefsDeltaManager;
import net.certiv.dsl.ui.DslUI;
import net.certiv.dsl.ui.preferences.blocks.DefaultEditorConfigBlock;
import net.certiv.dsl.ui.preferences.blocks.IPreferenceConfigBlock;
import net.certiv.dsl.ui.preferences.pages.AbstractPreferencePage;
import net.certiv.xvisitor.dt.core.XVisitorCore;
import net.certiv.xvisitor.dt.ui.XVisitorUI;

public class EditorPage extends AbstractPreferencePage {

	public EditorPage() {
		super();
	}

	@Override
	public DslUI getDslUI() {
		return XVisitorUI.getDefault();
	}

	@Override
	public DslCore getDslCore() {
		return XVisitorCore.getDefault();
	}

	@Override
	protected IPreferenceConfigBlock createConfigurationBlock(PrefsDeltaManager delta) {
		return new DefaultEditorConfigBlock(this, delta, getFormkit(), getColorRegistry());
	}
}
