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
