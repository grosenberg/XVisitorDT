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

import org.eclipse.jface.preference.IPreferenceStore;

import net.certiv.dsl.core.DslCore;
import net.certiv.dsl.core.color.DslColorRegistry;
import net.certiv.dsl.core.preferences.IPrefsManager;
import net.certiv.dsl.ui.DslUI;
import net.certiv.dsl.ui.editor.DslEditor;
import net.certiv.dsl.ui.editor.DslSourceViewerConfiguration;
import net.certiv.dsl.ui.formatter.IDslFormatterFactory;
import net.certiv.dsl.ui.preferences.pages.DslFormatterPreferencePage;
import net.certiv.xvisitor.dt.core.XVisitorCore;
import net.certiv.xvisitor.dt.ui.XVisitorUI;
import net.certiv.xvisitor.dt.ui.editor.Partitions;
import net.certiv.xvisitor.dt.ui.editor.XVisitorSimpleSourceViewerConfiguration;
import net.certiv.xvisitor.dt.ui.preferences.formatter.FormatterFactory;

/** Preference page for formatting */
public class FormatterPage extends DslFormatterPreferencePage {

	private FormatterFactory factory;

	@Override
	protected DslSourceViewerConfiguration createSimpleSourceViewerConfiguration(DslColorRegistry colorManager,
			IPreferenceStore preferenceStore, DslEditor editor, boolean configureFormatter) {

		return new XVisitorSimpleSourceViewerConfiguration(colorManager, (IPrefsManager) preferenceStore, editor,
				Partitions.PARTITIONING, configureFormatter);
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
	protected IDslFormatterFactory getFormatterFactory() {
		if (factory == null) {
			factory = new FormatterFactory();
		}
		return factory;
	}
}
