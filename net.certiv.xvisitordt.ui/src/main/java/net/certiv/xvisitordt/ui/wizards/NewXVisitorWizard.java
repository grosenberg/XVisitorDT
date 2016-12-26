package net.certiv.xvisitordt.ui.wizards;

import java.lang.reflect.InvocationTargetException;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.jface.resource.ImageDescriptor;

import net.certiv.dsl.core.DslCore;
import net.certiv.dsl.core.util.CoreUtil;
import net.certiv.dsl.ui.DslUI;
import net.certiv.dsl.ui.wizards.DslBaseWizard;
import net.certiv.xvisitordt.core.XVisitorCore;
import net.certiv.xvisitordt.ui.XVisitorUI;

public class NewXVisitorWizard extends DslBaseWizard {

	private NewXVisitorWizardPage newPage;

	public NewXVisitorWizard() {
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
	public String getWindowShellTitle() {
		return "New XVisitor grammar";
	}

	@Override
	public ImageDescriptor getPageImageDescriptor() {
		return getDslUI().getImageProvider().DESC_WIZBAN_NEW_FILE;
	}

	public void addPages() {
		super.addPages();
		newPage = new NewXVisitorWizardPage("NewPage", getSelection());
		newPage.setTitle("Grammar");
		newPage.setDescription("Create new XVisitor grammar");
		addPage(newPage);
	}

	public boolean performFinish() {
		final String filename = newPage.getFileName();
		final IPath container = newPage.getContainerFullPath();
		final String packageName = newPage.getPackageText();
		final String parserName = newPage.getParserClass();
		final String superclass = newPage.getSuperClass();
		final String importTxt = newPage.getImportTxt();

		IRunnableWithProgress op = new IRunnableWithProgress() {

			public void run(IProgressMonitor monitor) throws InvocationTargetException {
				try {
					doFinish(filename, container, packageName, parserName, superclass, importTxt, monitor);
				} catch (CoreException e) {
					throw new InvocationTargetException(e);
				} finally {
					monitor.done();
				}
			}
		};
		try {
			getContainer().run(true, false, op);
		} catch (InterruptedException e) {
			return false;
		} catch (InvocationTargetException e) {
			Throwable realException = e.getTargetException();
			MessageDialog.openError(getShell(), "Error", realException.getMessage());
			return false;
		}
		return true;
	}

	private void doFinish(String filename, IPath containerPath, String packageName, String parserName,
			String superclass, String importTxt, IProgressMonitor monitor) throws CoreException {

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
