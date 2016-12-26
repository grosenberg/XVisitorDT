package net.certiv.xvisitordt.core;

import net.certiv.dsl.core.DslCore;
import net.certiv.dsl.core.DslNature;

public class XVisitorNature extends DslNature {

	@Override
	public DslCore getDslCore() {
		return XVisitorCore.getDefault();
	}
}
