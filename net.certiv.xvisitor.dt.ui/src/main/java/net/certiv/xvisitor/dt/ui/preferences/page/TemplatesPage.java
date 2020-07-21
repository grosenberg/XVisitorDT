package net.certiv.xvisitor.dt.ui.preferences.page;

import org.eclipse.jface.text.IDocument;

import net.certiv.dsl.core.DslCore;
import net.certiv.dsl.core.color.DslColorRegistry;
import net.certiv.dsl.core.preferences.IPrefsManager;
import net.certiv.dsl.core.preferences.PrefsManager;
import net.certiv.dsl.ui.DslUI;
import net.certiv.dsl.ui.editor.text.DslTextTools;
import net.certiv.dsl.ui.preferences.pages.DslTemplatePreferencePage;
import net.certiv.dsl.ui.templates.CompletionManager;
import net.certiv.xvisitor.dt.core.XVisitorCore;
import net.certiv.xvisitor.dt.ui.XVisitorUI;
import net.certiv.xvisitor.dt.ui.editor.Partitions;
import net.certiv.xvisitor.dt.ui.editor.XVisitorSimpleSourceViewerConfiguration;
import net.certiv.xvisitor.dt.ui.editor.XVisitorSourceViewerConfiguration;

public class TemplatesPage extends DslTemplatePreferencePage {

	public TemplatesPage() {
		super();
		PrefsManager delta = XVisitorCore.getDefault().getPrefsManager();
		setPreferenceStore(delta);
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
	protected XVisitorSourceViewerConfiguration createSourceViewerConfiguration() {
		return new XVisitorSimpleSourceViewerConfiguration(getColorManager(), (IPrefsManager) getPreferenceStore(),
				null, Partitions.PARTITIONING, false);
	}

	@Override
	protected void setDocumentPartitioner(IDocument document) {
		getTextTools().setupDocumentPartitioner(document, Partitions.PARTITIONING);
	}

	@Override
	protected CompletionManager getCompletionMgr() {
		return XVisitorUI.getDefault().getCompletionMgr();
	}

	private DslColorRegistry getColorManager() {
		return XVisitorCore.getDefault().getColorRegistry();
	}

	private DslTextTools getTextTools() {
		return XVisitorUI.getDefault().getTextTools();
	}
}
