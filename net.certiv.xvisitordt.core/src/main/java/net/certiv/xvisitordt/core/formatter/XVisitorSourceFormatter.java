package net.certiv.xvisitordt.core.formatter;

import net.certiv.dsl.core.DslCore;
import net.certiv.dsl.core.formatter.DslCodeFormatter;
import net.certiv.xvisitordt.core.XVisitorCore;

public class XVisitorSourceFormatter extends DslCodeFormatter {

	public XVisitorSourceFormatter() {
		super();
	}

	@Override
	public DslCore getDslCore() {
		return XVisitorCore.getDefault();
	}
}
