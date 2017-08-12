//
// Generated from D:/DevFiles/Eclipse/Dsl/xvisitordt/net.certiv.xvisitordt.core/src/main/java/net/certiv/xvisitordt/core/parser/Outline.xv
// by XVisitor 4.5.3
//
package net.certiv.xvisitordt.core.parser.gen;

	import org.antlr.v4.runtime.tree.ParseTree;

import net.certiv.antlr.runtime.xvisitor.xpath.EType;
import net.certiv.xvisitordt.core.parser.OutlineAdaptor;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class OutlineVisitor extends OutlineAdaptor {

	public static final String[] tokenNames = {
		"<INVALID>", "TEXT", "RBRACE", "DOC_COMMENT", "BLOCK_COMMENT", "LINE_COMMENT", 
		"OPTIONS", "LBRACE", "GRAMMAR", "XVISITOR", "COLON", "COMMA", "SEMI", 
		"ASSIGN", "QUESTION", "STAR", "AT", "ANY", "SEP", "DOT", "OR", "NOT", 
		"ID", "LITERAL", "HORZ_WS", "VERT_WS", "ERRCHAR", "OPT_LBRACE", "OPT_RBRACE", 
		"OPT_ID", "OPT_LITERAL", "OPT_DOT", "OPT_ASSIGN", "OPT_SEMI", "OPT_STAR", 
		"OPT_INT", "ABLOCK_RBRACE", "ONENTRY", "ONEXIT", "REFERENCE"
	};

	public static final int
		TEXT = 1, RBRACE = 2, DOC_COMMENT = 3, BLOCK_COMMENT = 4, LINE_COMMENT = 5, 
		OPTIONS = 6, LBRACE = 7, GRAMMAR = 8, XVISITOR = 9, COLON = 10, COMMA = 11, 
		SEMI = 12, ASSIGN = 13, QUESTION = 14, STAR = 15, AT = 16, ANY = 17, SEP = 18, 
		DOT = 19, OR = 20, NOT = 21, ID = 22, LITERAL = 23, HORZ_WS = 24, VERT_WS = 25, 
		ERRCHAR = 26, OPT_LBRACE = 27, OPT_RBRACE = 28, OPT_ID = 29, OPT_LITERAL = 30, 
		OPT_DOT = 31, OPT_ASSIGN = 32, OPT_SEMI = 33, OPT_STAR = 34, OPT_INT = 35, 
		ABLOCK_RBRACE = 36, ONENTRY = 37, ONEXIT = 38, REFERENCE = 39;

	public static final String[] ruleNames = {
		"grammarSpec", "optionsSpec", "option", "optionValue", "action", "xmain", 
		"xpath", "xpathSpec", "actionBlock", "text", "reference", "separator", 
		"word"
	};

	public static final int
		grammarSpec = 1000, optionsSpec = 1001, option = 1002, optionValue = 1003, 
		action = 1004, xmain = 1005, xpath = 1006, xpathSpec = 1007, actionBlock = 1008, 
		text = 1009, reference = 1010, separator = 1011, word = 1012;

	public OutlineVisitor(ParseTree tree) {
		super(tree);
		init();
	}

	/** Entry point for finding all matches of the defined XPaths in the parse tree */
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

	/** Change the parse tree to match against. Implicitly performs a reset. */
	@Override
	public void setNewParseTree(ParseTree tree) {
		super.setNewParseTree(tree);
	}

	/** Clears state information developed in a prior find operation. */
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
					case "grammarSpec":
						grammarSpec();
						break;
					case "optionsBlock":
						optionsBlock();
						break;
					case "optionStatement":
						optionStatement();
						break;
					case "atAction":
						atAction();
						break;
					case "mainRule":
						mainRule();
						break;
					case "xpathRule":
						xpathRule();
						break;
		}
	}

	private void grammarSpec() {
		if (entering()) { grammarModule(); }
	}

	private void optionsBlock() {
		if (entering()) { begOptionsBlock(); }
		if (exiting()) {  endBlock(); }
	}

	private void optionStatement() {
		if (entering()) { processOptionStatement(); }
	}

	private void atAction() {
		if (entering()) { processAtAction(); }
	}

	private void mainRule() {
		if (entering()) { processMainRule(); }
	}

	private void xpathRule() {
		if (entering()) { processXPathRule(); }
	}



}