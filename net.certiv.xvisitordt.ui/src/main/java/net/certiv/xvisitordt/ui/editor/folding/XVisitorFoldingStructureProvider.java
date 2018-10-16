package net.certiv.xvisitordt.ui.editor.folding;

import net.certiv.dsl.core.DslCore;
import net.certiv.dsl.ui.DslUI;
import net.certiv.dsl.ui.editor.text.folding.AbstractDslFoldingStructureProvider;
import net.certiv.xvisitordt.core.XVisitorCore;
import net.certiv.xvisitordt.ui.XVisitorUI;
import net.certiv.xvisitordt.ui.editor.Partitions;

public class XVisitorFoldingStructureProvider extends AbstractDslFoldingStructureProvider {

	public XVisitorFoldingStructureProvider() {
		super();
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
	protected String getPartitioning() {
		return Partitions.PARTITIONING;
	}

	@Override
	protected String getMultiLineCommentPartition() {
		return Partitions.COMMENT_ML;
	}

	@Override
	protected String getSingleLineCommentPartition() {
		return Partitions.COMMENT_SL;
	}
}
