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

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.resource.ImageDescriptor;

import net.certiv.dsl.core.DslCore;
import net.certiv.dsl.core.util.CoreUtil;
import net.certiv.dsl.ui.DslImageManager;
import net.certiv.dsl.ui.DslUI;
import net.certiv.dsl.ui.wizards.DslBaseWizard;
import net.certiv.xvisitor.dt.core.XVisitorCore;
import net.certiv.xvisitor.dt.ui.XVisitorUI;

public class NewXVisitorWizard extends DslBaseWizard {

	private NewXVisitorWizardPage newPage;

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
	public void addPages() {
		super.addPages();
		newPage = new NewXVisitorWizardPage(this, getSelection());
		newPage.setTitle("Grammar");
		newPage.setDescription("Create new XVisitor grammar");
		addPage(newPage);
	}

	@Override
	protected void finishPage(IProgressMonitor monitor) throws InterruptedException, CoreException {
		final String filename = newPage.getFileName();
		final IPath containerPath = newPage.getContainerFullPath();
		final String packageName = newPage.getPackageText();
		final String parserName = newPage.getParserClass();
		final String superclass = newPage.getSuperClass();
		final String importTxt = newPage.getImportTxt();

		monitor.beginTask("Creating " + filename, 2);
		IWorkspaceRoot root = CoreUtil.getWorkspaceRoot();
		IContainer container = (IContainer) root.findMember(containerPath);
		if (!container.exists() || !(container instanceof IContainer)) {
			throwCoreException("Container '" + containerPath + "' does not exist.");
			return;
		}

		final String name;
		int dot = filename.lastIndexOf('.');
		name = (dot != -1) ? filename.substring(0, dot) : filename;

		String content = ContentGenerator.newVisitor(name, packageName, parserName, superclass, importTxt);
		IFile file = saveFile(container, filename, content, monitor);
		monitor.worked(1);

		monitor.setTaskName("Opening file for editing...");
		openEditor(file);
		monitor.worked(1);
	}
}
