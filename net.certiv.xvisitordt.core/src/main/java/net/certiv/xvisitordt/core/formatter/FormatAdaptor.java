package net.certiv.xvisitordt.core.formatter;

import net.certiv.antlr.runtime.xvisitor.Processor;

import org.antlr.v4.runtime.tree.ParseTree;

public abstract class FormatAdaptor extends Processor {

	protected XVisitorSourceFormatter helper;

	public FormatAdaptor(ParseTree tree) {
		super(tree);
	}

	public void setHelper(XVisitorSourceFormatter helper) {
		this.helper = helper;
	}
}
