package net.certiv.xvisitordt.ui.editor;

import java.util.ArrayList;
import java.util.List;

import net.certiv.dsl.core.model.DslModelManager;
import net.certiv.dsl.core.model.IDslElement;
import net.certiv.dsl.core.model.IStatement;
import net.certiv.dsl.ui.editor.hyperlink.DslHyperlinkDetector;
import net.certiv.xvisitordt.core.XVisitorCore;

public class XVisitorHyperlinkDetector extends DslHyperlinkDetector {

	public XVisitorHyperlinkDetector() {
		super();
	}

	/** Filter/modify the elements for display on the popup */
	@Override
	public IStatement[] selectOpenableElements(IStatement[] elements) {
		List<IStatement> stmts = new ArrayList<>();
		for (IStatement elem : elements) {
			if (elem.getKind() != IDslElement.MODULE) {
				stmts.add(elem);
			}
		}
		return stmts.toArray(new IStatement[stmts.size()]);
	}

	@Override
	public DslModelManager getModelMgr() {
		return XVisitorCore.getDefault().getModelManager();
	}
}
