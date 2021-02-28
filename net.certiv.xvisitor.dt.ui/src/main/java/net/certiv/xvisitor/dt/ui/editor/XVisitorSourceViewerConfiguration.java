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
package net.certiv.xvisitor.dt.ui.editor;

import static net.certiv.dsl.ui.editor.text.completion.engines.IPrefixStops.*;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.eclipse.jface.text.IAutoEditStrategy;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.ITextDoubleClickStrategy;
import org.eclipse.jface.text.contentassist.ContentAssistant;
import org.eclipse.jface.text.formatter.IContentFormatter;
import org.eclipse.jface.text.formatter.MultiPassContentFormatter;
import org.eclipse.jface.text.presentation.IPresentationReconciler;
import org.eclipse.jface.text.source.ISourceViewer;
import org.eclipse.jface.util.PropertyChangeEvent;
import org.eclipse.swt.graphics.Image;

import net.certiv.common.util.Chars;
import net.certiv.common.util.Strings;
import net.certiv.dsl.core.DslCore;
import net.certiv.dsl.core.color.DslColorRegistry;
import net.certiv.dsl.core.preferences.IPrefsManager;
import net.certiv.dsl.core.util.eclipse.TabStyle;
import net.certiv.dsl.ui.DslImageManager;
import net.certiv.dsl.ui.DslUI;
import net.certiv.dsl.ui.editor.DoubleClickStrategy;
import net.certiv.dsl.ui.editor.DslEditor;
import net.certiv.dsl.ui.editor.DslSourceViewerConfiguration;
import net.certiv.dsl.ui.editor.reconcile.PresentationReconciler;
import net.certiv.dsl.ui.editor.semantic.StylesManager;
import net.certiv.dsl.ui.editor.text.completion.CompletionCategory;
import net.certiv.dsl.ui.editor.text.completion.CompletionProcessor;
import net.certiv.dsl.ui.editor.text.completion.engines.FieldEngine;
import net.certiv.dsl.ui.editor.text.completion.engines.ICompletionEngine;
import net.certiv.dsl.ui.editor.text.completion.engines.KeywordEngine;
import net.certiv.dsl.ui.editor.text.completion.engines.TemplateEngine;
import net.certiv.xvisitor.dt.core.XVisitorCore;
import net.certiv.xvisitor.dt.ui.XVisitorUI;
import net.certiv.xvisitor.dt.ui.editor.strategies.SmartAutoEditStrategy;
import net.certiv.xvisitor.dt.ui.editor.strategies.XVisitorAutoEditDocStrategy;
import net.certiv.xvisitor.dt.ui.editor.text.ScannerAction;
import net.certiv.xvisitor.dt.ui.editor.text.ScannerCommentJD;
import net.certiv.xvisitor.dt.ui.editor.text.ScannerCommentML;
import net.certiv.xvisitor.dt.ui.editor.text.ScannerCommentSL;
import net.certiv.xvisitor.dt.ui.editor.text.ScannerKeyword;
import net.certiv.xvisitor.dt.ui.editor.text.ScannerString;
import net.certiv.xvisitor.dt.ui.formatter.strategies.ActionCodeFormattingStrategy;

public class XVisitorSourceViewerConfiguration extends DslSourceViewerConfiguration {

	private DoubleClickStrategy doubleClickStrategy;
	private ScannerCommentJD commentJDScanner;
	private ScannerCommentML commentMLScanner;
	private ScannerCommentSL commentSLScanner;
	private ScannerKeyword defaultScanner;
	private ScannerString stringScanner;
	private ScannerAction actionScanner;

	public XVisitorSourceViewerConfiguration(DslColorRegistry reg, IPrefsManager store, DslEditor editor,
			String partitioning) {
		super(XVisitorCore.getDefault(), reg, store, editor, partitioning);
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
	protected void initializeScanners() {
		IPrefsManager store = getPrefStore();
		StylesManager mgr = getDslUI().getStylesManager();

		commentJDScanner = new ScannerCommentJD(store, mgr);
		commentMLScanner = new ScannerCommentML(store, mgr);
		commentSLScanner = new ScannerCommentSL(store, mgr);
		stringScanner = new ScannerString(store, mgr);
		actionScanner = new ScannerAction(store, mgr);
		defaultScanner = new ScannerKeyword(store, mgr);
	}

	@Override
	public String[] getConfiguredContentTypes(ISourceViewer sourceViewer) {
		return Partitions.getAllContentTypes();
	}

	// protected void initializeQuickOutlineContexts(InformationPresenter
	// presenter,
	// IInformationProvider provider) {
	// presenter.setInformationProvider(provider, Partitions.COMMENT_JD);
	// presenter.setInformationProvider(provider, Partitions.COMMENT_ML);
	// presenter.setInformationProvider(provider, Partitions.ACTION);
	// }

	@Override
	public void specializeContentAssistant(ContentAssistant assistant) {
		XVisitorStatementLabelProvider provider = new XVisitorStatementLabelProvider();
		DslImageManager imgMgr = getDslUI().getImageManager();
		Image imgKeyword = imgMgr.get(imgMgr.IMG_OBJS_KEYWORD);
		Set<Character> stops = new HashSet<>(Arrays.asList(LBRACE, LBRACE, LPAREN, COLON, COMMA, SEMI, PIPE, AT));

		ICompletionEngine keywords = new KeywordEngine(imgKeyword, stops, ScannerKeyword.KEYWORDS);
		ICompletionEngine fields = new FieldEngine(provider, stops);
		ICompletionEngine templates = new TemplateEngine(provider, stops);

		CompletionCategory lang = new CompletionCategory("XVisitor", true, false, keywords, fields);
		CompletionCategory tmpl = new CompletionCategory("XVisitor Templates", false, true, templates);
		CompletionProcessor proc = new CompletionProcessor(getDslUI(), assistant, lang, tmpl);
		assistant.setContentAssistProcessor(proc, IDocument.DEFAULT_CONTENT_TYPE);
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
			return new String[] { Strings.dup(XVisitorCore.getDefault().getPrefsManager().getTabWidth(), Chars.SP) };
		} else {
			return new String[] { Strings.TAB };
		}
	}

	@Override
	public IPresentationReconciler getPresentationReconciler(ISourceViewer sourceViewer) {
		PresentationReconciler reconciler = new PresentationReconciler(getDslUI());
		reconciler.setDocumentPartitioning(getConfiguredDocumentPartitioning(sourceViewer));

		buildRepairer(reconciler, commentJDScanner, Partitions.COMMENT_JD);
		buildRepairer(reconciler, commentMLScanner, Partitions.COMMENT_ML);
		buildRepairer(reconciler, commentSLScanner, Partitions.COMMENT_SL);
		buildRepairer(reconciler, stringScanner, Partitions.STRING);
		buildRepairer(reconciler, actionScanner, Partitions.ACTION);
		buildRepairer(reconciler, defaultScanner, IDocument.DEFAULT_CONTENT_TYPE);

		return reconciler;
	}

	/**
	 * Adapts the behavior of the contained components to the change encoded in the given
	 * event.
	 *
	 * @param event the event to which to adapt
	 */
	@Override
	public void handlePropertyChangeEvent(PropertyChangeEvent event) {
		if (commentJDScanner.affectsBehavior(event)) commentJDScanner.adaptToPreferenceChange(event);
		if (commentMLScanner.affectsBehavior(event)) commentMLScanner.adaptToPreferenceChange(event);
		if (commentSLScanner.affectsBehavior(event)) commentSLScanner.adaptToPreferenceChange(event);
		if (stringScanner.affectsBehavior(event)) stringScanner.adaptToPreferenceChange(event);
		if (actionScanner.affectsBehavior(event)) actionScanner.adaptToPreferenceChange(event);
		if (defaultScanner.affectsBehavior(event)) defaultScanner.adaptToPreferenceChange(event);
	}

	/**
	 * Determines whether the preference change encoded by the given event changes the
	 * behavior of one of its contained components.
	 *
	 * @param event the event to be investigated
	 * @return {@code true} if event causes a behavioral change
	 */
	@Override
	public boolean affectsTextPresentation(PropertyChangeEvent event) {
		return defaultScanner.affectsBehavior(event) //
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
		switch (contentType) {
			case Partitions.COMMENT_JD:
			case Partitions.COMMENT_ML:
				strategy = new XVisitorAutoEditDocStrategy(partitioning);
				break;
			default:
				strategy = new SmartAutoEditStrategy(partitioning);
		}
		return new IAutoEditStrategy[] { strategy };
	}

	/**
	 * Loads content formatters into the SourceViewer for execution on receipt of a
	 * ISourceViewer.FORMAT command.
	 * <p>
	 * The master strategy utilizes the DSL formatter tree grammar to drive formatting of
	 * the default partition. The slave strategies are executed to format particular
	 * non-default partitions.
	 * <p>
	 * Two built-in non-default partition strategies are provided:
	 * {@code CommentFormattingStrategy()} and {@code JavaFormattingStrategy()} that use
	 * the JDT formatter and global JDT formatting preferences. The comment strategy can
	 * format stand-alone single-line, mutiple-line, and JavaDoc-style comments. The
	 * JavaCode strategy can format discrete blocks of otherwise standard Java code,
	 * including embedded comments.
	 *
	 * @param sourceViewer the viewer that will contain the content to format
	 * @return the content formatter
	 */
	@Override
	public IContentFormatter getContentFormatter(ISourceViewer sourceViewer) {
		MultiPassContentFormatter formatter = (MultiPassContentFormatter) super.getContentFormatter(sourceViewer);
		formatter.setSlaveStrategy(new ActionCodeFormattingStrategy(), Partitions.ACTION);

		// formatter.setSlaveStrategy(new GrammarCommentFormattingStrategy(),
		// Partitions.COMMENT_JD);
		// formatter.setSlaveStrategy(new GrammarCommentFormattingStrategy(),
		// Partitions.COMMENT_ML);
		// formatter.setSlaveStrategy(new GrammarCommentFormattingStrategy(),
		// Partitions.COMMENT_SL);
		return formatter;
	}

	// @Override
	// public IHyperlinkDetector getDslElementHyperlinkDetector(ITextEditor
	// textEditor) {
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

}
