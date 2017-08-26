package net.certiv.xvisitordt.ui.editor;

import org.eclipse.jface.text.IAutoEditStrategy;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.ITextDoubleClickStrategy;
import org.eclipse.jface.text.contentassist.ContentAssistant;
import org.eclipse.jface.text.formatter.IContentFormatter;
import org.eclipse.jface.text.formatter.MultiPassContentFormatter;
import org.eclipse.jface.text.presentation.IPresentationReconciler;
import org.eclipse.jface.text.presentation.PresentationReconciler;
import org.eclipse.jface.text.source.ISourceViewer;
import org.eclipse.jface.util.PropertyChangeEvent;
import org.eclipse.ui.texteditor.ITextEditor;

import net.certiv.dsl.core.DslCore;
import net.certiv.dsl.core.IColorManager;
import net.certiv.dsl.core.preferences.IDslPrefsManager;
import net.certiv.dsl.core.util.Strings;
import net.certiv.dsl.core.util.TabStyle;
import net.certiv.dsl.ui.DslUI;
import net.certiv.dsl.ui.editor.DoubleClickStrategy;
import net.certiv.dsl.ui.text.DslPresentationReconciler;
import net.certiv.dsl.ui.text.DslSourceViewerConfiguration;
import net.certiv.xvisitordt.core.XVisitorCore;
import net.certiv.xvisitordt.ui.XVisitorUI;
import net.certiv.xvisitordt.ui.editor.strategies.XVisitorAutoEditActionStrategy;
import net.certiv.xvisitordt.ui.editor.strategies.XVisitorAutoEditDocStrategy;
import net.certiv.xvisitordt.ui.editor.strategies.XVisitorAutoEditStrategy;
import net.certiv.xvisitordt.ui.editor.strategies.XVisitorAutoEditStringStrategy;
import net.certiv.xvisitordt.ui.editor.text.ScannerAction;
import net.certiv.xvisitordt.ui.editor.text.ScannerCommentJD;
import net.certiv.xvisitordt.ui.editor.text.ScannerCommentML;
import net.certiv.xvisitordt.ui.editor.text.ScannerCommentSL;
import net.certiv.xvisitordt.ui.editor.text.ScannerKeyWord;
import net.certiv.xvisitordt.ui.editor.text.ScannerString;
import net.certiv.xvisitordt.ui.formatter.strategies.ActionCodeFormattingStrategy;
import net.certiv.xvisitordt.ui.formatter.strategies.GrammarCommentFormattingStrategy;

public class XVisitorSourceViewerConfiguration extends DslSourceViewerConfiguration {

	private DoubleClickStrategy doubleClickStrategy;
	private ScannerCommentJD commentJDScanner;
	private ScannerCommentML commentMLScanner;
	private ScannerCommentSL commentSLScanner;
	private ScannerKeyWord keyScanner;
	private ScannerString stringScanner;
	private ScannerAction actionScanner;

	public XVisitorSourceViewerConfiguration(IColorManager colorManager, IDslPrefsManager store, ITextEditor editor,
			String partitioning) {
		super(colorManager, store, editor, partitioning);
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
	public String[] getConfiguredContentTypes(ISourceViewer sourceViewer) {
		return Partitions.getContentTypes(IDocument.DEFAULT_CONTENT_TYPE);
	}

	/**
	 * Loads content formatters into the SourceViewer for execution on receipt of a ISourceViewer.FORMAT
	 * command.
	 * <p>
	 * The master strategy utilizes the DSL formatter tree grammar to drive formatting of the default
	 * partition. The slave strategies are executed to format particular non-default partitions.
	 * </p>
	 * <p>
	 * Two built-in non-default partition strategies are provided:
	 * <code>CommentFormattingStrategy()</code> and <code>JavaFormattingStrategy()</code> that use the
	 * JDT formatter and global JDT formatting preferences. The comment strategy can format stand-alone
	 * single-line, mutiple-line, and JavaDoc-style comments. The JavaCode strategy can format discrete
	 * blocks of otherwise standard Java code, including embedded comments.
	 * </p>
	 *
	 * @param sourceViewer
	 *            the viewer that will contain the content to format
	 * @return the content formatter
	 */
	@Override
	public IContentFormatter getContentFormatter(ISourceViewer sourceViewer) {
		MultiPassContentFormatter formatter = (MultiPassContentFormatter) super.getContentFormatter(sourceViewer);
		// if (getDslCore().getPrefs().getBoolean(
		// net.certiv.antlrdt4.core.preferences.PrefsKey.JAVA_FORMATTING_ENABLE))
		// {
		formatter.setSlaveStrategy(new ActionCodeFormattingStrategy(), Partitions.ACTION);
		formatter.setSlaveStrategy(new GrammarCommentFormattingStrategy(), Partitions.COMMENT_JD);
		formatter.setSlaveStrategy(new GrammarCommentFormattingStrategy(), Partitions.COMMENT_ML);
		formatter.setSlaveStrategy(new GrammarCommentFormattingStrategy(), Partitions.COMMENT_SL);
		// }
		return formatter;
	}

	@Override
	public ITextDoubleClickStrategy getDoubleClickStrategy(ISourceViewer sourceViewer, String contentType) {
		if (doubleClickStrategy == null) {
			doubleClickStrategy = new DoubleClickStrategy();
		}
		return doubleClickStrategy;
	}

	@Override
	public String[] getIndentPrefixes(ISourceViewer sourceViewer, String contentType) {
		if (XVisitorCore.getDefault().getPrefsManager().getTabStyle() == TabStyle.SPACES) {
			return new String[] {
					Strings.getNSpaces(XVisitorCore.getDefault().getPrefsManager().getIndentationSize()) };
		} else {
			return new String[] { "\t" };
		}
	}

	@Override
	protected void initializeScanners() {
		IDslPrefsManager store = getPrefStore();
		commentJDScanner = new ScannerCommentJD(store);
		commentMLScanner = new ScannerCommentML(store);
		commentSLScanner = new ScannerCommentSL(store);
		keyScanner = new ScannerKeyWord(store);
		stringScanner = new ScannerString(store);
		actionScanner = new ScannerAction(store);
	}

	@Override
	public IPresentationReconciler getPresentationReconciler(ISourceViewer sourceViewer) {
		PresentationReconciler reconciler = new DslPresentationReconciler();
		reconciler.setDocumentPartitioning(getConfiguredDocumentPartitioning(sourceViewer));

		buildRepairer(reconciler, commentJDScanner, Partitions.COMMENT_JD);
		buildRepairer(reconciler, commentMLScanner, Partitions.COMMENT_ML);
		buildRepairer(reconciler, commentSLScanner, Partitions.COMMENT_SL);
		buildRepairer(reconciler, keyScanner, IDocument.DEFAULT_CONTENT_TYPE);
		buildRepairer(reconciler, stringScanner, Partitions.STRING);
		buildRepairer(reconciler, actionScanner, Partitions.ACTION);

		return reconciler;
	}

	/**
	 * Adapts the behavior of the contained components to the change encoded in the given event.
	 *
	 * @param event
	 *            the event to which to adapt
	 */
	@Override
	public void handlePropertyChangeEvent(PropertyChangeEvent event) {
		if (commentJDScanner.affectsBehavior(event)) commentJDScanner.adaptToPreferenceChange(event);
		if (commentMLScanner.affectsBehavior(event)) commentMLScanner.adaptToPreferenceChange(event);
		if (commentSLScanner.affectsBehavior(event)) commentSLScanner.adaptToPreferenceChange(event);
		if (keyScanner.affectsBehavior(event)) keyScanner.adaptToPreferenceChange(event);
		if (stringScanner.affectsBehavior(event)) stringScanner.adaptToPreferenceChange(event);
		if (actionScanner.affectsBehavior(event)) actionScanner.adaptToPreferenceChange(event);
	}

	/**
	 * Determines whether the preference change encoded by the given event changes the behavior of one
	 * of its contained components.
	 *
	 * @param event
	 *            the event to be investigated
	 * @return <code>true</code> if event causes a behavioral change
	 */
	@Override
	public boolean affectsTextPresentation(PropertyChangeEvent event) {
		return keyScanner.affectsBehavior(event) //
				|| actionScanner.affectsBehavior(event) //
				|| stringScanner.affectsBehavior(event) //
				|| commentJDScanner.affectsBehavior(event) //
				|| commentMLScanner.affectsBehavior(event) //
				|| commentSLScanner.affectsBehavior(event);
	}

	@Override
	public IAutoEditStrategy[] getAutoEditStrategies(ISourceViewer sourceViewer, String contentType) {
		String partitioning = getConfiguredDocumentPartitioning(sourceViewer);
		IAutoEditStrategy strategy;
		if (Partitions.COMMENT_JD.equals(contentType) //
				|| Partitions.COMMENT_ML.equals(contentType)) {
			strategy = new XVisitorAutoEditDocStrategy(partitioning);
		} else if (Partitions.STRING.equals(contentType)) {
			strategy = new XVisitorAutoEditStringStrategy(partitioning);
		} else if (Partitions.ACTION.equals(contentType)) {
			strategy = new XVisitorAutoEditActionStrategy(partitioning);
		} else {
			strategy = new XVisitorAutoEditStrategy(partitioning);
		}
		return new IAutoEditStrategy[] { strategy };
	}

	// @Override
	// public IHyperlinkDetector getDslElementHyperlinkDetector(ITextEditor textEditor) {
	// return new XVisitorHyperlinkDetector(textEditor);
	// }

	@Override
	protected String getCommentPrefix() {
		return "#";
	}

	// protected void initializeQuickOutlineContexts(InformationPresenter
	// presenter,
	// IInformationProvider provider) {
	// presenter.setInformationProvider(provider, Partitions.COMMENT_JD);
	// presenter.setInformationProvider(provider, Partitions.COMMENT_ML);
	// presenter.setInformationProvider(provider, Partitions.ACTION);
	// }

	@Override
	protected void alterContentAssistant(ContentAssistant assistant) {
		// DslCompletionProcessor processor;
		// processor = new CompletionProcessor(getEditor(), assistant,
		// IDocument.DEFAULT_CONTENT_TYPE);
		// assistant.setContentAssistProcessor(processor, IDocument.DEFAULT_CONTENT_TYPE);
		// processor = new CompletionProcessor(getEditor(), assistant, Partitions.ACTION);
		// assistant.setContentAssistProcessor(processor, Partitions.ACTION);
		// processor = new CompletionProcessor(getEditor(), assistant, Partitions.COMMENT_JD);
		// assistant.setContentAssistProcessor(processor, Partitions.COMMENT_JD);
	}
}
