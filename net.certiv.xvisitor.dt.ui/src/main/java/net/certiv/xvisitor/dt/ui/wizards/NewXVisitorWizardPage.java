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

import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Composite;

import net.certiv.dsl.core.DslCore;
import net.certiv.dsl.ui.DslUI;
import net.certiv.dsl.ui.fields.ITextButtonAdapter;
import net.certiv.dsl.ui.fields.TextButtonField;
import net.certiv.dsl.ui.wizards.DslBaseWizard;
import net.certiv.dsl.ui.wizards.DslContainerWizardPage;
import net.certiv.xvisitor.dt.core.XVisitorCore;
import net.certiv.xvisitor.dt.ui.XVisitorUI;

/**
 * The class {@code NewXVisitorWizardPage} contains controls and validation routines
 * for a 'New DSL WizardPage'.
 */
public class NewXVisitorWizardPage extends DslContainerWizardPage {

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
	public NewXVisitorWizardPage(DslBaseWizard wizard, IStructuredSelection selection) {
		super("XVNewWizardPage", wizard, selection);
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
	public void createControl(Composite parent) {
		Composite container = new Composite(parent, SWT.NONE);
		GridDataFactory.fillDefaults().grab(true, true).applyTo(container);
		GridLayoutFactory.fillDefaults().spacing(6, 9).margins(6, 6).applyTo(container);

		setFileName("Visitor");
		setFileExtension("xv");
		createContainerControl(container);
		createSubControls(container);

		validatePage();
		setErrorMessage(null);
		setMessage(null);
		setControl(container);
	}

	private void createSubControls(Composite parent) {
		Composite container = new Composite(parent, SWT.NONE);
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
	public String getPackageText() {
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
}
