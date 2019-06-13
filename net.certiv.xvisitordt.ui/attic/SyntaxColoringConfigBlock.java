package net.certiv.xvisitordt.ui.preferences.page;

import java.io.InputStream;

import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.preference.PreferencePage;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.source.IOverviewRuler;
import org.eclipse.jface.text.source.IVerticalRuler;
import org.eclipse.jface.text.source.projection.ProjectionViewer;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.texteditor.ITextEditor;

import net.certiv.dsl.core.DslCore;
import net.certiv.dsl.core.color.IColorManager;
import net.certiv.dsl.core.preferences.DslPrefsManagerDelta;
import net.certiv.dsl.core.preferences.IDslPrefsManager;
import net.certiv.dsl.ui.DslUI;
import net.certiv.dsl.ui.editor.DslSourceViewer;
import net.certiv.dsl.ui.preferences.blocks.AbstractEditorColoringConfigurationBlock;
import net.certiv.dsl.ui.preferences.blocks.IPreferenceConfigurationBlock;
import net.certiv.xvisitordt.core.XVisitorCore;
import net.certiv.xvisitordt.core.preferences.PrefsKey;
import net.certiv.xvisitordt.ui.XVisitorUI;
import net.certiv.xvisitordt.ui.editor.DocumentSetupParticipant;
import net.certiv.xvisitordt.ui.editor.Partitions;
import net.certiv.xvisitordt.ui.editor.XVisitorSimpleSourceViewerConfiguration;
import net.certiv.xvisitordt.ui.editor.XVisitorSourceViewerConfiguration;

public class SyntaxColoringConfigBlock extends AbstractEditorColoringConfigurationBlock
		implements IPreferenceConfigurationBlock {

	private static final String PREVIEW_FILE_NAME = "PrefsColorSample.g4";

	// { "Action Block Names", PrefsKey.EDITOR_ACTION_NAMED_COLOR, coreCategory }
	private static final String[][] fSyntaxColorListModel = new String[][] {
			{ "JavaDoc-style Comment", PrefsKey.EDITOR_COMMENT_DC_COLOR, CmtsCat },
			{ "Multi-line Comment", PrefsKey.EDITOR_COMMENT_BL_COLOR, CmtsCat },
			{ "Single line Comment", PrefsKey.EDITOR_COMMENT_LN_COLOR, CmtsCat },
			{ "Keywords", PrefsKey.EDITOR_KEYWORDS_COLOR, CoreCat },
			{ "Strings", PrefsKey.EDITOR_STRING_COLOR, CoreCat },
			{ "Action Blocks", PrefsKey.EDITOR_ACTION_COLOR, CoreCat }, };

	public SyntaxColoringConfigBlock(DslPrefsManagerDelta delta, PreferencePage page) {
		super(delta, page);
	}

	@Override
	protected String[][] getSyntaxColorListModel() {
		return fSyntaxColorListModel;
	}

	@Override
	protected ProjectionViewer createPreviewViewer(Composite parent, IVerticalRuler verticalRuler,
			IOverviewRuler overviewRuler, boolean showAnnotationsOverview, int styles, IPreferenceStore store) {
		return new DslSourceViewer(this, parent, verticalRuler, overviewRuler, showAnnotationsOverview, styles, store);
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
	protected XVisitorSourceViewerConfiguration createSimpleSourceViewerConfiguration(IColorManager colorManager,
			IPreferenceStore store, ITextEditor editor, boolean configureFormatter) {
		return new XVisitorSimpleSourceViewerConfiguration(colorManager, (IDslPrefsManager) store, editor,
				Partitions.XVISITOR_PARTITIONING, configureFormatter);
	}

	@Override
	protected void setDocumentPartitioning(IDocument document) {
		DocumentSetupParticipant participant = new DocumentSetupParticipant();
		participant.setup(document);
	}

	@Override
	protected InputStream getPreviewContentReader() {
		return getClass().getResourceAsStream(PREVIEW_FILE_NAME);
	}
}
