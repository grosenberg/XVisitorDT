package net.certiv.xvisitordt.core.builder;

import org.antlr.v4.runtime.Token;
import org.eclipse.core.resources.IMarker;

import net.certiv.antlr.xvisitor.tool.IToolListener;
import net.certiv.antlr.xvisitor.tool.Messages;
import net.certiv.antlr.xvisitor.tool.Messages.ToolMessage;
import net.certiv.dsl.core.parser.DslParseRecord;
import net.certiv.dsl.core.parser.DslProblem;
import net.certiv.v4.runtime.dsl.MsgUtil;

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
		// Log.info(this, msg);
	}

	private void report(int severity, Messages msg) {
		Token token = MsgUtil.offendingToken(msg);
		String cause = MsgUtil.displayMessage(msg, token);
		DslProblem problem = new DslProblem(record, severity, cause, token);
		if (record.collector != null) record.collector.accept(problem);
	}
}
