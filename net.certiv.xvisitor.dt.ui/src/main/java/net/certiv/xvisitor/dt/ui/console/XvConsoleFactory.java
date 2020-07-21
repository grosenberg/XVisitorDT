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
