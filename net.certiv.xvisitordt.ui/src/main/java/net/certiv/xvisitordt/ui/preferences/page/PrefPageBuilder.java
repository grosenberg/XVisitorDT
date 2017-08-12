package net.certiv.xvisitordt.ui.preferences.page;

import java.util.LinkedHashMap;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.jdt.core.IJavaElement;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.jface.preference.BooleanFieldEditor;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IFileEditorInput;
import org.eclipse.ui.IPartService;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;

import net.certiv.dsl.core.preferences.DslPrefsManagerDelta;
import net.certiv.dsl.core.util.Log;
import net.certiv.dsl.ui.preferences.BooleanFieldEditor2;
import net.certiv.dsl.ui.preferences.page.AbstractFieldEditorPreferencePage;
import net.certiv.xvisitordt.core.XVisitorCore;
import net.certiv.xvisitordt.core.preferences.PrefsKey;

public class PrefPageBuilder extends AbstractFieldEditorPreferencePage {

	private Combo projCombo;
	private BooleanFieldEditor2 builderEn;
	private Composite buf;
	private BooleanFieldEditor2 projRestriction;
	private BooleanFieldEditor2 curpathRestriction;

	private boolean enabled;

	private LinkedHashMap<String, IProject> projects; 	// all open projects with xvisitor nature
	private IProject active; 							// current has antlrdt xvisitor or null

	public PrefPageBuilder() {
		super(GRID);
		DslPrefsManagerDelta delta = XVisitorCore.getDefault().getPrefsManager().createDeltaManager();
		delta.setDefaultProject(null);
		setPreferenceStore(delta);

		projects = getActiveGrammarProjects();
		active = getActiveGrammarProject();
	}

	/** Creates the field editors. */
	public void createFieldEditors() {
		Composite parent = getFieldEditorParent();

		// ///////////////////////////////////////////////////////

		Group projGroup = new Group(parent, SWT.NONE);
		GridDataFactory.fillDefaults().indent(0, 6).grab(true, false).span(2, 1).applyTo(projGroup);
		GridLayoutFactory.fillDefaults().margins(6, 6).applyTo(projGroup);
		projGroup.setText("Project Selection");

		Composite projComp = new Composite(projGroup, SWT.NONE);
		GridDataFactory.fillDefaults().indent(0, 4).grab(true, false).applyTo(projComp);
		GridLayoutFactory.swtDefaults().numColumns(2).applyTo(projComp);

		new Label(projComp, SWT.NONE).setText("Builder options for project");
		projCombo = new Combo(projComp, SWT.DROP_DOWN | SWT.READ_ONLY);
		projCombo.setItems(projects.keySet().toArray(new String[projects.size()]));

		if (active != null) {
			int current = projCombo.indexOf(active.getName());
			projCombo.select(current);
		} else {
			projCombo.select(0);
		}

		projCombo.addSelectionListener(new SelectionAdapter() {

			public void widgetSelected(SelectionEvent e) {
				int sel = projCombo.getSelectionIndex();
				if (sel > 0) {
					active = projects.get(projCombo.getItem(sel));
				} else {
					active = null;
				}
				getDeltaMgr().setDefaultProject(active);
				initialize();
				checkState();
				updateEnables();
			}
		});

		// ///////////////////////////////////////////////////////

		Group group = new Group(parent, SWT.NONE);
		GridData gridData = new GridData(GridData.FILL_HORIZONTAL | GridData.GRAB_HORIZONTAL);
		gridData.verticalIndent = 10;
		gridData.horizontalSpan = 2;
		group.setLayoutData(gridData);

		GridLayout layout = new GridLayout(1, false);
		group.setLayout(layout);
		group.setText("Builder Options");

		buf = new Composite(group, SWT.NONE);
		gridData = new GridData(GridData.FILL_HORIZONTAL | GridData.GRAB_HORIZONTAL);
		buf.setLayoutData(gridData);

		layout = new GridLayout(1, false);
		buf.setLayout(layout);

		builderEn = new BooleanFieldEditor2(bind(PrefsKey.BUILDER_ENABLE), "Enable Builder", buf);
		enabled = getDeltaMgr().getBoolean(active, PrefsKey.BUILDER_ENABLE);
		builderEn.addSelectionListener(new SelectionAdapter() {

			public void widgetSelected(SelectionEvent e) {
				enabled = ((Button) e.getSource()).getSelection();
				projRestriction.setEnabled(enabled, buf);
				curpathRestriction.setEnabled(enabled, buf);
				buf.redraw();
			}
		});
		addField(builderEn);

		projRestriction = new BooleanFieldEditor2(bind(PrefsKey.BUILDER_RESTRICT_TO_PROJECT),
				"Restrict builds to current project", buf);
		projRestriction.addSelectionListener(new SelectionAdapter() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				boolean selected = ((Button) e.getSource()).getSelection();
				if (!selected) {
					curpathRestriction.setBooleanValue(selected);
				}
			}
		});
		addField(projRestriction);

		curpathRestriction = new BooleanFieldEditor2(bind(PrefsKey.BUILDER_RESTRICT_TO_PATH),
				"Restrict builds to active path", buf);
		curpathRestriction.addSelectionListener(new SelectionAdapter() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				boolean selected = ((Button) e.getSource()).getSelection();
				if (selected) {
					projRestriction.setBooleanValue(selected);
				}
			}
		});
		addField(curpathRestriction);

		// ///////////////////////////////////////////////////////

		Group postGroup = new Group(parent, SWT.NONE);
		GridData gdGroup = new GridData(GridData.FILL_HORIZONTAL | GridData.GRAB_HORIZONTAL);
		gdGroup.verticalIndent = 6;
		gdGroup.horizontalSpan = 2;
		postGroup.setLayoutData(gdGroup);

		GridLayout glGroup = new GridLayout(1, false);
		postGroup.setLayout(glGroup);
		postGroup.setText("Post-Build Actions");

		Composite inComp = new Composite(postGroup, SWT.NONE);
		GridData gdComp = new GridData(GridData.FILL_HORIZONTAL | GridData.GRAB_HORIZONTAL);
		// gdComp.horizontalIndent = 4;
		inComp.setLayoutData(gdComp);

		GridLayout glComp = new GridLayout(1, false);
		inComp.setLayout(glComp);

		addField(new BooleanFieldEditor(bind(PrefsKey.BUILDER_REFRESH), "Refresh project", inComp));
		addField(new BooleanFieldEditor(bind(PrefsKey.BUILDER_ORGANIZE), "Organize imports *", inComp));
		addField(new BooleanFieldEditor(bind(PrefsKey.BUILDER_FORMAT), "Format generated source *", inComp));
		Label notice = new Label(inComp, SWT.NONE);
		notice.setText("* requires the generated files to be on the classpath");
	}

	protected void updateEnables() {
		enabled = getDeltaMgr().getBoolean(active, bind(PrefsKey.BUILDER_ENABLE));
		curpathRestriction.setEnabled(enabled, buf);
		buf.redraw();
	}

	@Override
	protected void performDefaults() {
		super.performDefaults();
		updateEnables();
	}

	private LinkedHashMap<String, IProject> getActiveGrammarProjects() {
		LinkedHashMap<String, IProject> results = new LinkedHashMap<String, IProject>();
		results.put("<default>", null);
		IWorkspaceRoot root = ResourcesPlugin.getWorkspace().getRoot();
		for (IProject project : root.getProjects()) {
			if (isXVisitorProject(project)) {
				results.put(project.getName(), project);
			}
		}
		return results;
	}

	private IProject getActiveGrammarProject() {
		IProject project = findProjectFromEditor();
		if (isXVisitorProject(project)) return project;
		project = findProjectFromSelection();
		if (isXVisitorProject(project)) return project;
		return null;
	}

	private IProject findProjectFromEditor() {
		IWorkbenchWindow window = PlatformUI.getWorkbench().getActiveWorkbenchWindow();
		IPartService partService = window.getPartService();
		IWorkbenchPart part = partService.getActivePart();
		if (part instanceof IEditorPart) {
			IEditorPart editorPart = (IEditorPart) part;
			IEditorInput editorInput = editorPart.getEditorInput();
			if (editorInput instanceof IFileEditorInput) {
				return ((IFileEditorInput) editorInput).getFile().getProject();
			}
		}
		return null;
	}

	private IProject findProjectFromSelection() {
		IWorkbenchWindow window = PlatformUI.getWorkbench().getActiveWorkbenchWindow();
		ISelection selection = window.getSelectionService().getSelection();
		if (selection instanceof IStructuredSelection) {
			IStructuredSelection ssel = (IStructuredSelection) selection;
			Object element = ssel.getFirstElement();
			if (element == null) {
				Log.debug(this, "No project selected; setting default");
			} else {
				if (element instanceof IResource) {
					return ((IResource) element).getProject();
				} else if (element instanceof IJavaElement) {
					IJavaElement je = (IJavaElement) element;
					try {
						return (je.getCorrespondingResource()).getProject();
					} catch (JavaModelException e) {
						Log.debug(this, "Failed to resolve cu to project");
					}
				} else if (element instanceof IProject) {
					return ((IProject) element).getProject();
				} else {
					String name = element.getClass().getName();
					Log.debug(this, "Failed to resolve project [selection=" + name + "]");
				}
			}
		}
		return null;
	}

	// project must be open and an XVisitor project
	private boolean isXVisitorProject(IProject project) {
		try {
			if (project != null && project.isAccessible()
					&& project.isNatureEnabled(XVisitorCore.getDefault().getNatureId())) {
				return true;
			}
		} catch (CoreException e) {}
		return false;
	}

	public void init(IWorkbench workbench) {}
}
