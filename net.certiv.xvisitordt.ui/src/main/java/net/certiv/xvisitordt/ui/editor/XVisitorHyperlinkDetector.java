package net.certiv.xvisitordt.ui.editor;

import net.certiv.dsl.core.model.DslModel;
import net.certiv.dsl.ui.editor.hyperlink.DslHyperlinkDetector;
import net.certiv.xvisitordt.core.XVisitorCore;

public class XVisitorHyperlinkDetector extends DslHyperlinkDetector {

	public XVisitorHyperlinkDetector() {
		super();
	}

	@Override
	public DslModel getDslModel() {
		return XVisitorCore.getDefault().getDslModel();
	}
}
