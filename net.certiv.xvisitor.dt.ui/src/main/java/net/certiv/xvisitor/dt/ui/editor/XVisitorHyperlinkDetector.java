package net.certiv.xvisitor.dt.ui.editor;

import net.certiv.dsl.core.model.DslModel;
import net.certiv.dsl.ui.editor.hyperlink.DslHyperlinkDetector;
import net.certiv.xvisitor.dt.core.XVisitorCore;

public class XVisitorHyperlinkDetector extends DslHyperlinkDetector {

	public XVisitorHyperlinkDetector() {
		super();
	}

	@Override
	public DslModel getDslModel() {
		return XVisitorCore.getDefault().getDslModel();
	}

	@Override
	protected boolean isDslLikeFilename(String name) {
		return XVisitorCore.getDefault().getLangManager().isDslLikeFilename(name);
	}
}
