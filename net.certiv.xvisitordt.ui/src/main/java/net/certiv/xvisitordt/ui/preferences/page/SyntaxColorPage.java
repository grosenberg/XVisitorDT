package net.certiv.xvisitordt.ui.preferences.page;

import net.certiv.dsl.core.DslCore;
import net.certiv.dsl.core.preferences.DslPrefsManagerDelta;
import net.certiv.dsl.ui.DslUI;
import net.certiv.dsl.ui.preferences.blocks.IPreferenceConfigBlock;
import net.certiv.dsl.ui.preferences.pages.AbstractPreferencePage;
import net.certiv.xvisitordt.core.XVisitorCore;
import net.certiv.xvisitordt.ui.XVisitorUI;

public class SyntaxColorPage extends AbstractPreferencePage {

	@Override
	public DslUI getDslUI() {
		return XVisitorUI.getDefault();
	}

	@Override
	public DslCore getDslCore() {
		return XVisitorCore.getDefault();
	}

	@Override
	protected IPreferenceConfigBlock createConfigurationBlock(DslPrefsManagerDelta delta) {
		return new SyntaxColorConfigBlock(this, delta, getFormkit(), getColorMgr());
	}

	@Override
	protected void setDescription() {
		setDescription("XVisitor Syntax Presentation Preferences");
	}

	@Override
	protected String getHelpId() {
		return null;
	}
}