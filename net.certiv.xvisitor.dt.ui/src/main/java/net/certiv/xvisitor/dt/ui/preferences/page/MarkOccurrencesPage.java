/*******************************************************************************
 * Copyright (c) 2012, 2020 Certiv Analytics.
 *
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package net.certiv.xvisitor.dt.ui.preferences.page;

import java.util.List;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.forms.widgets.FormToolkit;

import net.certiv.dsl.core.DslCore;
import net.certiv.dsl.core.color.DslColorRegistry;
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
		return new MarkOccurrencesConfigBlock(this, delta, getFormkit(), getColorRegistry());
	}

	public class MarkOccurrencesConfigBlock extends AbstractMarkOccurrencesConfigBlock {

		public MarkOccurrencesConfigBlock(IDslPreferencePage page, PrefsDeltaManager delta, FormToolkit formkit,
				DslColorRegistry reg) {
			super(page, delta, formkit, reg);
		}

		@Override
		public List<String> getMarkingKeys(List<String> keys) {
			return keys;
		}

		@Override
		protected void addCustomFoldingControls(Composite parent) {}
	}
}
