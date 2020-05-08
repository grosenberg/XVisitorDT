package net.certiv.xvisitor.dt.ui.handlers;

import net.certiv.dsl.ui.handlers.ToggleCommentHandler;

public class XVisitorToggleCommentsHandler extends ToggleCommentHandler {

	public XVisitorToggleCommentsHandler() {
		super();
	}

	@Override
	public String getSingleLineMark() {
		return "#";
	}

	@Override
	public String getOpenCommentMark() {
		return "/#";
	}

	@Override
	public String getCloseCommentMark() {
		return "#/";
	}
}
