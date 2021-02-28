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

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.IPath;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Composite;

import net.certiv.common.util.Chars;
import net.certiv.dsl.ui.fields.ITextButtonAdapter;
import net.certiv.dsl.ui.fields.TextButtonField;
import net.certiv.dsl.ui.wizard.DslFileWizard;
import net.certiv.dsl.ui.wizard.DslFileWizardPage;

/**
 * The class {@code NewXVisitorWizardPage} contains controls and validation routines for a
 * 'New DSL WizardPage'.
 */
public class NewXVisitorWizardPage extends DslFileWizardPage {

	protected final static String PACKAGE = "package";	//$NON-NLS-1$
	protected final static String PARSER = "parser";	//$NON-NLS-1$
	protected final static String SUPER = "superclass";	//$NON-NLS-1$

	private TextButtonField pkgField;
	private TextButtonField superField;
	private TextButtonField parserField;
	private String importTxt = "";

	/**
	 * Creates a new {@code NewTypeWizardPage}.
	 *
	 * @param pageName the wizard page's name
	 */
	public NewXVisitorWizardPage(DslFileWizard wizard, IStructuredSelection selection) {
		super("XVNewWizardPage", wizard, selection);

		setTitle("Grammar");
		setDescription("Create new XVisitor grammar");
		setFilename("Visitor");
		setFileExtension("xv");
	}

	@Override
	protected void createCustomGroup(Composite topLevel) {
		Composite container = new Composite(topLevel, SWT.NONE);
		GridDataFactory.fillDefaults().grab(true, true).applyTo(container);
		GridLayoutFactory.fillDefaults().numColumns(3).margins(6, 6).applyTo(container);

		FieldsAdapter adapter = new FieldsAdapter();

		pkgField = new TextButtonField(container, SWT.NONE, PACKAGE, "Package:", 3, "Select", adapter);
		parserField = new TextButtonField(container, SWT.NONE, PARSER, "Parser:", 3, "Select", adapter);
		superField = new TextButtonField(container, SWT.NONE, SUPER, "Super class:", 3, "Select", adapter);
	}

	private class FieldsAdapter implements ITextButtonAdapter {

		@Override
		public void buttonPressed(TextButtonField field, String id, SelectionEvent e) {
			switch (id) {
				case PACKAGE:
					String result = choosePackage();
					pkgField.setText(result);
					break;
				case PARSER:
					String pType = chooseSuperClass();
					parserField.setText(pType);
					break;
				case SUPER:
					String sType = chooseSuperClass();
					superField.setText(sType);
					importTxt = sType;
					break;
			}
		}
	}

	/** Returns the text of the package input field. */
	public String getPackageName() {
		return pkgField.getText();
	}

	/** Returns the text of the parser class input field. */
	public String getParserClass() {
		return parserField.getText();
	}

	/** Returns the content of the superclass input field. */
	public String getSuperClass() {
		return superField.getText();
	}

	/** Returns the text of for the import */
	public String getImportTxt() {
		return importTxt;
	}

	@Override
	public IFile createNewFile() {
		IPath base = getContainerFullPath();
		String filename = getFilename();
		String packageName = getPackageName();
		String parserName = getParserClass();
		String superclass = getSuperClass();
		String importTxt = getImportTxt();

		int dot = filename.lastIndexOf(Chars.DOT);
		String name = (dot != -1) ? filename.substring(0, dot) : filename;

		String content = ContentGenerator.newVisitor(name, packageName, parserName, superclass, importTxt);
		return createNewFile(base, filename, getInitialContentStream(content));
	}

	@Override
	protected String getInitialContents() {
		return null;
	}
}
