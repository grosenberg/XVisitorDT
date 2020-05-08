package net.certiv.xvisitor.dt.ui.editor;

import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.IDocumentExtension3;
import org.eclipse.jface.text.IRegion;
import org.eclipse.jface.text.source.DefaultCharacterPairMatcher;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.texteditor.SourceViewerDecorationSupport;

import net.certiv.dsl.core.DslCore;
import net.certiv.dsl.core.preferences.consts.Editor;
import net.certiv.dsl.ui.DslUI;
import net.certiv.dsl.ui.editor.DslEditor;
import net.certiv.dsl.ui.editor.text.DslWordFinder;
import net.certiv.dsl.ui.editor.text.folding.IFoldingStructureProvider;
import net.certiv.xvisitor.dt.core.XVisitorCore;
import net.certiv.xvisitor.dt.ui.XVisitorUI;
import net.certiv.xvisitor.dt.ui.editor.folding.XVisitorFoldingStructureProvider;

public class XVisitorEditor extends DslEditor {

	public static final String EDITOR_ID = "net.certiv.xvisitor.dt.ui.editor.XVisitorEditor";
	public static final String EDITOR_CONTEXT = "#XVisitorEditorContext";
	public static final String RULER_CONTEXT = "#XVisitorRulerContext";

	private static final String[] EDITOR_KEY_SCOPE = new String[] { "net.certiv.xvisitor.dt.ui.xvisitorEditorScope" };
	private static final String MARK_OCCURRENCES_ANNOTATION_TYPE = "net.certiv.xvisitor.dt.ui.occurrences";

	private final DslWordFinder finder = new DslWordFinder();
	private final DefaultCharacterPairMatcher pairMatcher = new DefaultCharacterPairMatcher(STD_PAIRS,
			Partitions.PARTITIONING);

	private IFoldingStructureProvider foldingProvider;

	public XVisitorEditor() {
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
	public String getEditorId() {
		return EDITOR_ID;
	}

	@Override
	public char[] getBrackets() {
		return STD_PAIRS;
	}

	@Override
	protected void initializeEditor() {
		super.initializeEditor();
		setEditorContextMenuId(EDITOR_CONTEXT);
		setRulerContextMenuId(RULER_CONTEXT);
	}

	@Override
	protected void initializeKeyBindingScopes() {
		setKeyBindingScopes(EDITOR_KEY_SCOPE);
	}

	@Override
	public String getMarkOccurrencesAnnotationType() {
		return MARK_OCCURRENCES_ANNOTATION_TYPE;
	}

	@Override
	protected IRegion findWord(IDocument doc, int offset) {
		return finder.findWord(doc, offset);
	}

	@Override
	protected XVisitorOutlinePage doCreateOutlinePage() {
		return new XVisitorOutlinePage(getPreferenceStore());
	}

	@Override
	protected IFoldingStructureProvider createFoldingStructureProvider() {
		if (foldingProvider == null) {
			foldingProvider = new XVisitorFoldingStructureProvider();
		}
		return foldingProvider;
	}

	@Override
	protected void connectPartitioningToElement(IEditorInput input, IDocument document) {
		if (document instanceof IDocumentExtension3) {
			IDocumentExtension3 extension = (IDocumentExtension3) document;
			if (extension.getDocumentPartitioner(Partitions.PARTITIONING) == null) {
				DocumentSetupParticipant participant = new DocumentSetupParticipant();
				participant.setup(document);
			}
		}
	}

	@Override
	protected void configureSourceViewerDecorationSupport(SourceViewerDecorationSupport support) {
		support.setCharacterPairMatcher(pairMatcher);
		support.setMatchingCharacterPainterPreferenceKeys(bind(Editor.EDITOR_MATCHING_BRACKETS),
				bind(Editor.EDITOR_MATCHING_BRACKETS_COLOR));
		super.configureSourceViewerDecorationSupport(support);
	}
}
