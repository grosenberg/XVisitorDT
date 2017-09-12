package net.certiv.xvisitordt.ui.editor;

import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.IDocumentExtension3;
import org.eclipse.jface.text.IRegion;
import org.eclipse.jface.text.source.DefaultCharacterPairMatcher;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.texteditor.SourceViewerDecorationSupport;

import net.certiv.dsl.core.DslCore;
import net.certiv.dsl.core.preferences.DslPrefsKey;
import net.certiv.dsl.core.util.Log;
import net.certiv.dsl.ui.DslUI;
import net.certiv.dsl.ui.editor.DslEditor;
import net.certiv.dsl.ui.text.DslWordFinder;
import net.certiv.dsl.ui.text.folding.IFoldingStructureProvider;
import net.certiv.xvisitordt.core.XVisitorCore;
import net.certiv.xvisitordt.ui.XVisitorUI;
import net.certiv.xvisitordt.ui.editor.folding.XVisitorFoldingStructureProvider;

public class XVisitorEditor extends DslEditor {

	public static final String EDITOR_ID = "net.certiv.xvisitordt.ui.XVisitorEditor";
	public static final String EDITOR_CONTEXT = "#XVisitorEditorContext";
	public static final String RULER_CONTEXT = "#XVisitorRulerContext";

	private static final String[] EDITOR_KEY_SCOPE = new String[] { "net.certiv.xvisitordt.ui.xvisitorEditorScope" };
	private static final String MARK_OCCURRENCES_ANNOTATION_TYPE = "net.certiv.xvisitordt.ui.occurrences";

	private DefaultCharacterPairMatcher pairMatcher;
	private IFoldingStructureProvider foldingProvider;
	private DslWordFinder finder;

	public XVisitorEditor() {
		super();
		// must init on construction
		pairMatcher = new DefaultCharacterPairMatcher(XVisitorTextTools.PAIRS, Partitions.XVISITOR_PARTITIONING);
	}

	@Override
	protected void initializeEditor() {
		super.initializeEditor();
		Log.debug(this, "XVisitor editor started");
		setEditorContextMenuId(EDITOR_CONTEXT);
		setRulerContextMenuId(RULER_CONTEXT);
		finder = new DslWordFinder();
	}

	@Override
	public String getEditorId() {
		return EDITOR_ID;
	}

	@Override
	public String getMarkOccurrencesAnnotationType() {
		return MARK_OCCURRENCES_ANNOTATION_TYPE;
	}

	// //////////////////////////////////////////////////////////////////////////

	@Override
	public DslUI getDslUI() {
		return XVisitorUI.getDefault();
	}

	@Override
	public DslCore getDslCore() {
		return XVisitorCore.getDefault();
	}

	@Override
	public char[] getBrackets() {
		return XVisitorTextTools.PAIRS;
	}

	@Override
	protected IRegion findWord(IDocument doc, int offset) {
		return finder.findWord(doc, offset);
	}

	@Override
	protected IFoldingStructureProvider createFoldingStructureProvider() {
		if (foldingProvider == null) {
			foldingProvider = new XVisitorFoldingStructureProvider();
		}
		return foldingProvider;
	}

	// /////////////////////////////////////////////////////////////////////////

	@Override
	protected XVisitorOutlinePage doCreateOutlinePage() {
		return new XVisitorOutlinePage(this, getPreferenceStore());
	}

	@Override
	protected void connectPartitioningToElement(IEditorInput input, IDocument document) {
		if (document instanceof IDocumentExtension3) {
			IDocumentExtension3 extension = (IDocumentExtension3) document;
			if (extension.getDocumentPartitioner(Partitions.XVISITOR_PARTITIONING) == null) {
				DocumentSetupParticipant participant = new DocumentSetupParticipant();
				participant.setup(document);
			}
		}
	}

	// //////////////////////////////////////////////////////////////////////////

	@Override
	protected void initializeKeyBindingScopes() {
		setKeyBindingScopes(EDITOR_KEY_SCOPE);
	}

	@Override
	protected void configureSourceViewerDecorationSupport(SourceViewerDecorationSupport support) {
		support.setCharacterPairMatcher(pairMatcher);
		support.setMatchingCharacterPainterPreferenceKeys(
				getDslCore().getPrefsManager().bind(DslPrefsKey.EDITOR_MATCHING_BRACKETS),
				getDslCore().getPrefsManager().bind(DslPrefsKey.EDITOR_MATCHING_BRACKETS_COLOR));
		super.configureSourceViewerDecorationSupport(support);
	}
}
