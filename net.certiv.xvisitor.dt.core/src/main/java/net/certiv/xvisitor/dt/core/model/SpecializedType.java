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
package net.certiv.xvisitor.dt.core.model;

import net.certiv.dsl.core.model.builder.ISpecializedType;
import net.certiv.dsl.core.util.Strings;

public enum SpecializedType implements ISpecializedType {

	GrammarType("Grammar Type", Strings.EMPTY),
	Options("Options", Strings.EMPTY),
	Option("Option", Strings.EMPTY),
	AtAction("At Action", Strings.EMPTY),
	GroupRule("Group Rule", Strings.EMPTY),
	PathRule("Path Rule", Strings.EMPTY),
	ActionBlock("Action Block", Strings.EMPTY),
	Block("Block", Strings.EMPTY),
	Key("Key", Strings.EMPTY),
	Value("Value", Strings.EMPTY),

	Unknown("Unknown", Strings.EMPTY),

	;

	public final String name;
	public final String css;

	SpecializedType(String name, String css) {
		this.name = name;
		this.css = css;
	}

	@Override
	public String getStyle() {
		return css;
	}

	@Override
	public String toString() {
		return name;
	}
}
