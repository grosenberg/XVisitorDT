package net.certiv.xvisitor.dt.core.formatter;

import org.antlr.v4.runtime.tree.ParseTree;

import net.certiv.antlr.runtime.xvisitor.Processor;

public abstract class IndentAdaptor extends Processor {

	protected XVisitorSourceFormatter helper;

	public IndentAdaptor(ParseTree tree) {
		super(tree);
	}

	public void setHelper(XVisitorSourceFormatter helper) {
		this.helper = helper;
	}
}
