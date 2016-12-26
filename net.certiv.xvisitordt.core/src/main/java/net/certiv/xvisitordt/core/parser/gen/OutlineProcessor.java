//
// Generated from D:/DevFiles/Eclipse/Dsl/net.certiv.antlrdt4/net.certiv.xvisitordt.core/src/main/java/net/certiv/antlrdt4/xv/core/parser/Outline.xv by ANTLR-XVisitor 4.5.2
//
package net.certiv.xvisitordt.core.parser.gen;

	import org.antlr.v4.runtime.tree.ParseTree;

import net.certiv.antlr.runtime.xvisitor.xpath.EType;
import net.certiv.xvisitordt.core.parser.OutlineAdaptor;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class OutlineProcessor extends OutlineAdaptor {

	public static final String[] tokenNames = {
		"<INVALID>", "TEXT", "RBRACE", "DOC_COMMENT", "BLOCK_COMMENT", "LINE_COMMENT", 
		"OPTIONS", "LBRACE", "GRAMMAR", "XVISITOR", "COLON", "COMMA", "SEMI", 
		"ASSIGN", "QUESTION", "STAR", "AT", "ANY", "SEP", "DOT", "OR", "NOT", 
		"ID", "LITERAL", "HORZ_WS", "VERT_WS", "ERRCHAR", "OPT_LBRACE", "OPT_RBRACE", 
		"OPT_ID", "OPT_LITERAL", "OPT_DOT", "OPT_ASSIGN", "OPT_SEMI", "OPT_STAR", 
		"OPT_INT", "ABLOCK_RBRACE", "ONENTRY", "ONEXIT", "REFERENCE"
	};

	public static final String[] ruleNames = {
		"grammarSpec", "optionsSpec", "option", "optionValue", "action", "xmain", 
		"xpath", "xpathSpec", "actionBlock", "text", "reference", "separator", 
		"word"
	};

	public OutlineProcessor(ParseTree tree) {
		super(tree);
		init();
	}

	/**
	 * Entry point for finding all matches of the defined XPaths in the parse tree
	 */
	@Override
	public void findAll() {
		super.findAll();
	}

	/**
	 * Entry point for finding all matches of a set of one or more named XPaths in the parse tree. The name of an XPath
	 * is the rulename used in the tree grammar to define the XPath.
	 */
	@Override
	public void find(String... names) {
		super.find(names);
	}

	/**
	 * Change the parse tree to match against. Implicitly performs a reset.
	 */
	@Override
	public void setNewParseTree(ParseTree tree) {
		super.setNewParseTree(tree);
	}

	/**
	 * Clears state information developed in a prior find operation.
	 */
	@Override
	public void reset() {
		super.reset();
	}

	protected String[] getTokenNames() {
		return tokenNames;
	}

	protected String[] getRuleNames() {
		return ruleNames;
	}

	private void init() {
		mainRule("outline");

					createPathSpec("grammarSpec");
						addElement(EType.Rule, false, false, "grammarSpec", 0); 
					completePathSpec(); 

					createPathSpec("optionsBlock");
						addElement(EType.Rule, true, false, "optionsSpec", 1); 
					completePathSpec(); 

					createPathSpec("optionStatement");
						addElement(EType.Rule, true, false, "optionsSpec", 1); 
						addElement(EType.Rule, false, false, "option", 2); 
					completePathSpec(); 

					createPathSpec("atAction");
						addElement(EType.Rule, false, false, "grammarSpec", 0); 
						addElement(EType.Rule, false, false, "action", 4); 
					completePathSpec(); 



					createPathSpec("mainRule");
						addElement(EType.Rule, true, false, "xmain", 5); 
					completePathSpec(); 

					createPathSpec("xpathRule");
						addElement(EType.Rule, true, false, "xpath", 6); 
					completePathSpec(); 
	}


	@Override
	public void executeActionBlock(String name) {
		switch (name) {
					case "optionsBlock":
						optionsBlock();
						break;
					case "atAction":
						atAction();
						break;
					case "optionStatement":
						optionStatement();
						break;
					case "grammarSpec":
						grammarSpec();
						break;
					case "mainRule":
						mainRule();
						break;
					case "xpathRule":
						xpathRule();
						break;
		}
	}

	private void optionsBlock() {
		if (entering()) { begOptionsBlock(); }
		if (exiting()) {  endBlock(); }
	}

	private void atAction() {
		if (entering()) { processAtAction(); }
	}

	private void optionStatement() {
		if (entering()) { processOptionStatement(); }
	}

	private void grammarSpec() {
		if (entering()) { grammarModule(); }
	}

	private void mainRule() {
		if (entering()) { processMainRule(); }
	}

	private void xpathRule() {
		if (entering()) { processXPathRule(); }
	}



}