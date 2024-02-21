//
// Generated from net.certiv.xvisitor.dt.core.parser.gen
// by XVisitor 4.8.0
//
package net.certiv.xvisitor.dt.core.parser.gen ;
	import net.certiv.xvisitor.dt.core.model.StructureBuilder ;

import java.util.List;
import org.antlr.v4.runtime.tree.ParseTree;
import net.certiv.antlr.runtime.xvisitor.Processor;
import net.certiv.antlr.runtime.xvisitor.xpath.EType;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class StructureVisitor extends StructureBuilder {

	public static final String[] tokenNames = {
		"<INVALID>", "INT", "RBRACE", "TEXT", "DOC_COMMENT", "BLOCK_COMMENT", 
		"LINE_COMMENT", "OPTIONS", "LBRACE", "GRAMMAR", "XVISITOR", "COLON", "COMMA", 
		"SEMI", "ASSIGN", "QUESTION", "STAR", "AT", "ANY", "SEP", "DOT", "OR", 
		"NOT", "ID", "LITERAL", "HORZ_WS", "VERT_WS", "ERRCHAR", "ABLOCK_RBRACE", 
		"ONENTRY", "ONEXIT", "REFERENCE"
	};

	public static final int
		INT = 1, RBRACE = 2, TEXT = 3, DOC_COMMENT = 4, BLOCK_COMMENT = 5, LINE_COMMENT = 6, 
		OPTIONS = 7, LBRACE = 8, GRAMMAR = 9, XVISITOR = 10, COLON = 11, COMMA = 12, 
		SEMI = 13, ASSIGN = 14, QUESTION = 15, STAR = 16, AT = 17, ANY = 18, SEP = 19, 
		DOT = 20, OR = 21, NOT = 22, ID = 23, LITERAL = 24, HORZ_WS = 25, VERT_WS = 26, 
		ERRCHAR = 27, ABLOCK_RBRACE = 28, ONENTRY = 29, ONEXIT = 30, REFERENCE = 31;

	public static final String[] ruleNames = {
		"grammarSpec", "optionsSpec", "option", "optionValue", "action", "xgroup", 
		"xpath", "xpathSpec", "actionBlock", "text", "reference", "separator", 
		"word"
	};

	public static final int
		grammarSpec = 1000, optionsSpec = 1001, option = 1002, optionValue = 1003, 
		action = 1004, xgroup = 1005, xpath = 1006, xpathSpec = 1007, actionBlock = 1008, 
		text = 1009, reference = 1010, separator = 1011, word = 1012;

	public StructureVisitor(ParseTree tree) {
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
		mainRule("structure");

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

					createPathSpec("action");
						addElement(EType.Rule, false, false, "grammarSpec", 0); 
						addElement(EType.Rule, false, false, "action", 4); 
					completePathSpec(); 

					createPathSpec("groupRule");
						addElement(EType.Rule, true, false, "xgroup", 5); 
					completePathSpec(); 

					createPathSpec("pathRule");
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
					case "action":
						action();
						break;
					case "groupRule":
						groupRule();
						break;
					case "pathRule":
						pathRule();
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

	private void action() {
		if (entering()) { processAction(); }
	}

	private void groupRule() {
		if (entering()) { processGroupRule(); }
	}

	private void pathRule() {
		if (entering()) { processPathRule(); }
		if (exiting()) {  endBlock(); }
	}



}