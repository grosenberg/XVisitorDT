package net.certiv.xvisitor.dt.ui.console;

import java.util.List;

import net.certiv.dsl.core.console.ConsoleRecordFactory.ConsoleRecord;
import net.certiv.dsl.core.console.CS;
import net.certiv.dsl.ui.console.StyledConsole;
import net.certiv.xvisitor.dt.core.console.Aspect;
import net.certiv.xvisitor.dt.ui.XVisitorUI;

public class XvConsole extends StyledConsole {

	public static final String CONSOLE_TYPE = "xv_console"; //$NON-NLS-1$

	public XvConsole() {
		super(XVisitorUI.getDefault(), "XVisitor Console", CONSOLE_TYPE, null, true);
	}

	@Override
	public void append(List<ConsoleRecord> records, boolean clear) {
		super.append(records, clear);
		append(Aspect.GENERAL, CS.NORMAL, null, "%s\n", BREAK);
	}
}
