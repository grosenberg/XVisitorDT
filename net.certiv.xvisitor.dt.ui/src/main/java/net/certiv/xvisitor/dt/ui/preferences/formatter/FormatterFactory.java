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
package net.certiv.xvisitor.dt.ui.preferences.formatter;

import java.net.URL;

import net.certiv.dsl.core.DslCore;
import net.certiv.dsl.core.formatter.IDslCodeFormatter;
import net.certiv.dsl.ui.DslUI;
import net.certiv.dsl.ui.formatter.DslFormatterFactory;
import net.certiv.dsl.ui.formatter.IFormatterModifyDialog;
import net.certiv.dsl.ui.formatter.IFormatterModifyDialogOwner;
import net.certiv.xvisitor.dt.core.XVisitorCore;
import net.certiv.xvisitor.dt.core.formatter.XVisitorSourceFormatter;
import net.certiv.xvisitor.dt.ui.XVisitorUI;

public class FormatterFactory extends DslFormatterFactory {

	private IDslCodeFormatter formatter;

	public FormatterFactory() {
		super();
	}

	@Override
	public URL getPreviewContent() {
		return getClass().getResource("Preview.xv");
	}

	@Override
	public IFormatterModifyDialog createDialog(IFormatterModifyDialogOwner dialogOwner) {
		return new ModifyDialog(dialogOwner, this);
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
	public IDslCodeFormatter createFormatter() {
		if (formatter == null) {
			formatter = new XVisitorSourceFormatter();
		}
		return formatter;
	}
}
