package net.certiv.xvisitor.dt.ui.console;

import net.certiv.dsl.ui.console.StyledConsoleFactory;

public class XvConsoleFactory extends StyledConsoleFactory {

	private static XvConsoleFactory _factory;

	public static XvConsoleFactory getFactory() {
		if (_factory == null) {
			_factory = new XvConsoleFactory();
		}
		return _factory;
	}

	@Override
	public XvConsole getConsole() {
		return (XvConsole) super.getConsole();
	}

	@Override
	protected XvConsole createConsole() {
		return new XvConsole();
	}
}
