package net.certiv.xvisitordt.ui.preferences.page;

import org.eclipse.jface.text.IDocument;

import net.certiv.dsl.core.DslCore;
import net.certiv.dsl.core.color.IColorManager;
import net.certiv.dsl.core.preferences.DslPrefsManager;
import net.certiv.dsl.core.preferences.IDslPrefsManager;
import net.certiv.dsl.ui.DslUI;
import net.certiv.dsl.ui.editor.text.DslTextTools;
import net.certiv.dsl.ui.templates.CompletionManager;
import net.certiv.dsl.ui.templates.DslTemplatePreferencePage;
import net.certiv.xvisitordt.core.XVisitorCore;
import net.certiv.xvisitordt.ui.XVisitorUI;
import net.certiv.xvisitordt.ui.editor.Partitions;
import net.certiv.xvisitordt.ui.editor.XVisitorSimpleSourceViewerConfiguration;
import net.certiv.xvisitordt.ui.editor.XVisitorSourceViewerConfiguration;

public class TemplatesPage extends DslTemplatePreferencePage {

	public TemplatesPage() {
		super();
		DslPrefsManager delta = XVisitorCore.getDefault().getPrefsManager();
		delta.setDefaultProject(null);
		setPreferenceStore(delta);
	}

	@Override
	protected XVisitorSourceViewerConfiguration createSourceViewerConfiguration() {
		return new XVisitorSimpleSourceViewerConfiguration(getColorManager(), (IDslPrefsManager) getPreferenceStore(),
				null, Partitions.PARTITIONING, false);
	}

	@Override
	protected void setDocumentPartitioner(IDocument document) {
		getTextTools().setupDocumentPartitioner(document, Partitions.PARTITIONING);
	}

	@Override
	public DslUI getDslUI() {
		return XVisitorUI.getDefault();
	}

	@Override
	public DslCore getDslCore() {
		return XVisitorCore.getDefault();
	}

	private IColorManager getColorManager() {
		return XVisitorCore.getDefault().getColorManager();
	}

	private DslTextTools getTextTools() {
		return XVisitorUI.getDefault().getTextTools();
	}

	@Override
	protected CompletionManager getCompletionMgr() {
		return XVisitorUI.getDefault().getCompletionMgr();
	}
}
