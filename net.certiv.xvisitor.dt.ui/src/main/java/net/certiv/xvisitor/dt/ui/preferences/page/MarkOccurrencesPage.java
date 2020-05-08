package net.certiv.xvisitor.dt.ui.preferences.page;

import java.util.List;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.forms.widgets.FormToolkit;

import net.certiv.dsl.core.DslCore;
import net.certiv.dsl.core.color.DslColorManager;
import net.certiv.dsl.core.preferences.PrefsDeltaManager;
import net.certiv.dsl.ui.DslUI;
import net.certiv.dsl.ui.preferences.blocks.AbstractMarkOccurrencesConfigBlock;
import net.certiv.dsl.ui.preferences.blocks.IPreferenceConfigBlock;
import net.certiv.dsl.ui.preferences.pages.AbstractPreferencePage;
import net.certiv.dsl.ui.preferences.pages.IDslPreferencePage;
import net.certiv.xvisitor.dt.core.XVisitorCore;
import net.certiv.xvisitor.dt.ui.XVisitorUI;

public class MarkOccurrencesPage extends AbstractPreferencePage {

	public MarkOccurrencesPage() {
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
		return new MarkOccurrencesConfigBlock(this, delta, getFormkit(), getColorMgr());
	}

	public class MarkOccurrencesConfigBlock extends AbstractMarkOccurrencesConfigBlock {

		public MarkOccurrencesConfigBlock(IDslPreferencePage page, PrefsDeltaManager delta, FormToolkit formkit,
				DslColorManager colorMgr) {
			super(page, delta, formkit, colorMgr);
		}

		@Override
		public List<String> getMarkingKeys(List<String> keys) {
			return keys;
		}

		@Override
		protected void addCustomFoldingControls(Composite parent) {}
	}
}
