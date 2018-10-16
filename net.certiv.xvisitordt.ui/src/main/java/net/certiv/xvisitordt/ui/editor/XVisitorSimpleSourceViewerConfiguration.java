package net.certiv.xvisitordt.ui.editor;

import org.eclipse.jface.text.IAutoEditStrategy;
import org.eclipse.jface.text.IInformationControlCreator;
import org.eclipse.jface.text.ITextHover;
import org.eclipse.jface.text.formatter.IContentFormatter;
import org.eclipse.jface.text.hyperlink.IHyperlinkDetector;
import org.eclipse.jface.text.information.IInformationPresenter;
import org.eclipse.jface.text.source.IAnnotationHover;
import org.eclipse.jface.text.source.ISourceViewer;
import org.eclipse.ui.texteditor.ITextEditor;

import net.certiv.dsl.core.color.IColorManager;
import net.certiv.dsl.core.preferences.IDslPrefsManager;

public class XVisitorSimpleSourceViewerConfiguration extends XVisitorSourceViewerConfiguration {

	public XVisitorSimpleSourceViewerConfiguration(IDslPrefsManager store, ITextEditor editor, String partitioning) {
		super(null, store, editor, partitioning);
	}

	public XVisitorSimpleSourceViewerConfiguration(IColorManager colorManager, IDslPrefsManager store,
			ITextEditor editor, String partitioning, boolean configureFormatter) {
		super(colorManager, store, editor, partitioning);
	}

	public IAutoEditStrategy[] getAutoEditStrategies(ISourceViewer sourceViewer, String contentType) {
		return null;
	}

	public IAnnotationHover getAnnotationHover(ISourceViewer sourceViewer) {
		return null;
	}

	public IAnnotationHover getOverviewRulerAnnotationHover(ISourceViewer sourceViewer) {
		return null;
	}

	public int[] getConfiguredTextHoverStateMasks(ISourceViewer sourceViewer, String contentType) {
		return null;
	}

	public ITextHover getTextHover(ISourceViewer sourceViewer, String contentType, int stateMask) {
		return null;
	}

	public ITextHover getTextHover(ISourceViewer sourceViewer, String contentType) {
		return null;
	}

	public IContentFormatter getContentFormatter(ISourceViewer sourceViewer) {
		return null;
	}

	public IInformationControlCreator getInformationControlCreator(ISourceViewer sourceViewer) {
		return null;
	}

	public IInformationPresenter getInformationPresenter(ISourceViewer sourceViewer) {
		return null;
	}

	public IInformationPresenter getOutlinePresenter(ISourceViewer sourceViewer, boolean doCodeResolve) {
		return null;
	}

	public IInformationPresenter getHierarchyPresenter(ISourceViewer sourceViewer, boolean doCodeResolve) {
		return null;
	}

	public IHyperlinkDetector[] getHyperlinkDetectors(ISourceViewer sourceViewer) {
		return null;
	}
}
