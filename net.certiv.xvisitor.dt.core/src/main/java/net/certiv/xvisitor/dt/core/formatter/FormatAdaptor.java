package net.certiv.xvisitor.dt.core.formatter;

import org.antlr.v4.runtime.tree.ParseTree;

import net.certiv.antlr.runtime.xvisitor.Processor;

public abstract class FormatAdaptor extends Processor {

	protected XVisitorSourceFormatter helper;

	public FormatAdaptor(ParseTree tree) {
		super(tree);
	}

	public void setHelper(XVisitorSourceFormatter helper) {
		this.helper = helper;
	}
}
