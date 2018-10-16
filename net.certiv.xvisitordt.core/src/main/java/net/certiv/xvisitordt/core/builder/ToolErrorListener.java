package net.certiv.xvisitordt.core.builder;

import org.antlr.v4.runtime.Token;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IMarker;

import net.certiv.antlr.xvisitor.tool.IToolListener;
import net.certiv.antlr.xvisitor.tool.Messages;
import net.certiv.antlr.xvisitor.tool.Messages.ToolMessage;
import net.certiv.dsl.core.parser.DslProblem;
import net.certiv.v4.runtime.dsl.MsgUtil;

public class ToolErrorListener implements IToolListener {

	private IFile file;
	private String prefix;

	public ToolErrorListener(IFile file, String prefix) {
		super();
		this.file = file;
		this.prefix = prefix;
	}

	public void error(ToolMessage msg) {
		this.error((Messages) msg);
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
		String type = DslProblem.makeType(prefix, IMarker.SEVERITY_INFO);
		apply(type, IMarker.SEVERITY_INFO, msg, 1, 0, 0);
	}

	private void report(int severity, Messages msg) {
		String type = DslProblem.makeType(prefix, severity);
		Token token = MsgUtil.offendingToken(msg);
		String cause = MsgUtil.displayMessage(msg, token);
		int line = token.getLine();
		int begOffset = token.getStartIndex();
		int endOffset = token.getStopIndex();
		apply(type, severity, cause, line, begOffset, endOffset);
	}

	private void apply(String type, int severity, String cause, int line, int begOffset, int endOffset) {
		try {
			IMarker marker = file.createMarker(type);
			marker.setAttribute(IMarker.SEVERITY, severity);
			marker.setAttribute(IMarker.PRIORITY, IMarker.PRIORITY_NORMAL);
			marker.setAttribute(IMarker.MESSAGE, cause);
			marker.setAttribute(IMarker.LINE_NUMBER, String.valueOf(line));
			marker.setAttribute(IMarker.CHAR_START, String.valueOf(begOffset));
			marker.setAttribute(IMarker.CHAR_END, String.valueOf(endOffset));
		} catch (Exception e) {}
	}
}
