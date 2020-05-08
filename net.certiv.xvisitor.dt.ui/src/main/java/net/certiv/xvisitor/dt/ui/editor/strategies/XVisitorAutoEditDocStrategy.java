package net.certiv.xvisitor.dt.ui.editor.strategies;

import net.certiv.dsl.core.DslCore;
import net.certiv.dsl.ui.DslUI;
import net.certiv.dsl.ui.editor.text.AbstractMultilineCommentEditStrategy;
import net.certiv.xvisitor.dt.core.XVisitorCore;
import net.certiv.xvisitor.dt.ui.XVisitorUI;

public class XVisitorAutoEditDocStrategy extends AbstractMultilineCommentEditStrategy {

	public XVisitorAutoEditDocStrategy(String partitioning) {
		super(partitioning);
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