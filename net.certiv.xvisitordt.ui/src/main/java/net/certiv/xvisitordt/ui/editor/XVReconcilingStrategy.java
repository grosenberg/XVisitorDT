package net.certiv.xvisitordt.ui.editor;

import org.eclipse.core.resources.IResource;
import org.eclipse.jface.text.Region;
import org.eclipse.jface.text.source.ISourceViewer;
import org.eclipse.ui.texteditor.ITextEditor;

import net.certiv.dsl.core.DslCore;
import net.certiv.dsl.ui.DslUI;
import net.certiv.dsl.ui.editor.reconcile.DslReconcilingStrategy;
import net.certiv.xvisitordt.core.XVisitorCore;
import net.certiv.xvisitordt.ui.XVisitorUI;

public class XVReconcilingStrategy extends DslReconcilingStrategy {

	public XVReconcilingStrategy(ITextEditor editor, ISourceViewer viewer) {
		super(editor, viewer, XVisitorCore.DSL_NAME);
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
	public void initialReconcile() {
		deleteMarkers(true, IResource.DEPTH_INFINITE);
		reconcile(new Region(0, getRecord().doc.getLength()));
	}

	@Override
	public boolean incremental() {
		return false;
	}
}
