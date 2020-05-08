package net.certiv.xvisitor.dt.core.formatter;

import net.certiv.dsl.core.DslCore;
import net.certiv.dsl.core.formatter.DslCodeFormatter;
import net.certiv.xvisitor.dt.core.XVisitorCore;

public class XVisitorSourceFormatter extends DslCodeFormatter {

	public XVisitorSourceFormatter() {
		super();
	}

	@Override
	public DslCore getDslCore() {
		return XVisitorCore.getDefault();
	}
}
