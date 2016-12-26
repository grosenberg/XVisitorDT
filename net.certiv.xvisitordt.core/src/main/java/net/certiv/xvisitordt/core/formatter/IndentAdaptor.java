package net.certiv.xvisitordt.core.formatter;

import net.certiv.antlr.runtime.xvisitor.Processor;

import org.antlr.v4.runtime.tree.ParseTree;

public abstract class IndentAdaptor extends Processor {

	protected XVisitorSourceFormatter helper;

	public IndentAdaptor(ParseTree tree) {
		super(tree);
	}

	public void setHelper(XVisitorSourceFormatter helper) {
		this.helper = helper;
	}
}
