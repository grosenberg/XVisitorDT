package net.certiv.xvisitordt.ui.editor;

import org.eclipse.jface.text.source.ISourceViewer;
import org.eclipse.ui.texteditor.ITextEditor;

import net.certiv.dsl.core.DslCore;
import net.certiv.dsl.ui.DslUI;
import net.certiv.dsl.ui.editor.reconcile.ReconcilingStrategy;
import net.certiv.xvisitordt.core.XVisitorCore;
import net.certiv.xvisitordt.core.XvLangManager;
import net.certiv.xvisitordt.ui.XVisitorUI;

public class XVReconcilingStrategy extends ReconcilingStrategy {

	public XVReconcilingStrategy(ITextEditor editor, ISourceViewer viewer) {
		super(editor, viewer, XvLangManager.DSL_NAME);
	}

	@Override
	public DslUI getDslUI() {
		return XVisitorUI.getDefault();
	}

	@Override
	public DslCore getDslCore() {
		return XVisitorCore.getDefault();
	}
}
