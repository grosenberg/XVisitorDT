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
