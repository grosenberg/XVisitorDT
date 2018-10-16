package net.certiv.xvisitordt.ui.wizards;

import org.eclipse.jdt.core.IPackageFragment;
import org.eclipse.jdt.core.IType;
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
import net.certiv.dsl.ui.wizards.DslContainerWizardPage;
import net.certiv.xvisitordt.core.XVisitorCore;
import net.certiv.xvisitordt.ui.XVisitorUI;

/**
 * The class <code>NewXVisitorWizardPage</code> contains controls and validation routines for a 'New
 * DSL WizardPage'.
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
	 * Creates a new <code>NewTypeWizardPage</code>.
	 *
	 * @param pageName the wizard page's name
	 */
	public NewXVisitorWizardPage(String pageName, IStructuredSelection selection) {
		super(pageName, selection);
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
					IPackageFragment result = choosePackage();
					pkgField.setText(result.getElementName());
					break;
				case PARSER:
					IType pType = chooseSuperClass();
					parserField.setText(pType.getElementName());
					break;
				case SUPER:
					IType sType = chooseSuperClass();
					superField.setText(sType.getElementName());
					importTxt = sType.getFullyQualifiedName();
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
