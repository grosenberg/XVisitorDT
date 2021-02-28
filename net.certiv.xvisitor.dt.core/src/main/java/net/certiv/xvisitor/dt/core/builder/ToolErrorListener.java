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
package net.certiv.xvisitor.dt.core.builder;

import org.antlr.v4.runtime.Token;
import org.eclipse.core.resources.IMarker;

import net.certiv.antlr.xvisitor.IToolListener;
import net.certiv.antlr.xvisitor.tool.Messages;
import net.certiv.antlr.xvisitor.tool.Messages.ToolMessage;
import net.certiv.common.log.Log;
import net.certiv.dsl.core.parser.DslParseRecord;
import net.certiv.dsl.core.parser.problems.DslProblem;
import net.certiv.v4.runtime.dsl.Antlr3Util;

public class ToolErrorListener implements IToolListener {

	private DslParseRecord record;

	public ToolErrorListener(DslParseRecord record) {
		this.record = record;
	}

	public void error(ToolMessage msg) {
		error((Messages) msg);
	}

	@Override
	public void error(Messages msg) {
		report(IMarker.SEVERITY_ERROR, msg);
	}

	@Override
	public void warn(Messages msg) {
		report(IMarker.SEVERITY_WARNING, msg);
	}

	@Override
	public void info(String msg) {
		Log.info(this, msg);
	}

	private void report(int severity, Messages msg) {
		Token token = Antlr3Util.offendingToken(msg);
		String cause = Antlr3Util.displayMessage(msg, token);
		DslProblem problem = new DslProblem(record, severity, cause, token);
		record.getCollector().accept(problem);
	}
}
