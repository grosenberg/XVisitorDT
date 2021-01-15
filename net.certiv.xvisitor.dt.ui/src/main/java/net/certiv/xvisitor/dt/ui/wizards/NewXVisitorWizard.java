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
package net.certiv.xvisitor.dt.ui.wizards;

import org.eclipse.jface.resource.ImageDescriptor;

import net.certiv.dsl.core.DslCore;
import net.certiv.dsl.ui.DslImageManager;
import net.certiv.dsl.ui.DslUI;
import net.certiv.dsl.ui.wizard.DslFileWizard;
import net.certiv.xvisitor.dt.core.XVisitorCore;
import net.certiv.xvisitor.dt.ui.XVisitorUI;

public class NewXVisitorWizard extends DslFileWizard {

	private NewXVisitorWizardPage page;

	public NewXVisitorWizard() {
		super("XVNewWizard");
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
	public String getWindowShellTitle() {
		return "New XVisitor grammar";
	}

	@Override
	public ImageDescriptor getPageImageDescriptor() {
		DslImageManager imgMgr = getDslUI().getImageManager();
		return imgMgr.getDescriptor(imgMgr.IMG_WIZBAN_NEW_FILE);
	}

	@Override
	protected NewXVisitorWizardPage getMainPage() {
		if (page == null) {
			page = new NewXVisitorWizardPage(this, getSelection());
		}
		return page;
	}
}
