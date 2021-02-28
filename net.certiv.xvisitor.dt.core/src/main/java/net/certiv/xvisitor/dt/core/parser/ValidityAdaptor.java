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
package net.certiv.xvisitor.dt.core.parser;

import java.io.File;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.antlr.v4.runtime.BaseErrorListener;
import org.antlr.v4.runtime.CommonToken;
import org.antlr.v4.runtime.Parser;
import org.antlr.v4.runtime.Recognizer;
import org.antlr.v4.runtime.RuleContext;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.tree.ParseTree;

import net.certiv.antlr.runtime.xvisitor.Processor;
import net.certiv.common.stores.TreeMultimap;
import net.certiv.common.util.Reflect;
import net.certiv.common.util.Strings;
import net.certiv.dsl.core.parser.DslErrorListener;
import net.certiv.xvisitor.dt.core.parser.gen.XVisitorParser.GrammarSpecContext;
import net.certiv.xvisitor.dt.core.parser.gen.XVisitorParser.ReferenceContext;
import net.certiv.xvisitor.dt.core.parser.gen.XVisitorParser.WordContext;
import net.certiv.xvisitor.dt.core.parser.gen.XVisitorParser.XgroupContext;
import net.certiv.xvisitor.dt.core.parser.gen.XVisitorParser.XpathContext;

/** Implementing functions for grammar validity walker. */
public abstract class ValidityAdaptor extends Processor {

	private Parser parser;
	private BaseErrorListener errListener;

	private List<String> ruleNames;
	private List<String> termNames;

	private static final Comparator<Token> comp = new Comparator<Token>() {

		@Override
		public int compare(Token o1, Token o2) {
			return o1.getText().compareTo(o2.getText());
		}
	};

	private final TreeMultimap<String, Token> xruleMap = new TreeMultimap<>(null, comp);
	private final Set<String> xpaths = new HashSet<>();

	public ValidityAdaptor(ParseTree tree) {
		super(tree);
	}

	@SuppressWarnings("deprecation")
	public void setHelper(Parser parser, Recognizer<?, ?> ref, DslErrorListener errListener) {
		this.parser = parser;
		this.errListener = errListener;

		this.ruleNames = Arrays.asList(ref.getRuleNames());
		this.termNames = Arrays.asList(ref.getTokenNames());

	}

	public void checkXVisitor() {
		GrammarSpecContext ctx = (GrammarSpecContext) lastPathNode();
		String sourceName = ctx.ID().getSymbol().getTokenSource().getSourceName();
		String filename = new File(sourceName).getName();
		int dot = filename.indexOf('.');
		if (dot > -1) {
			filename = filename.substring(0, dot);
		}
		if (!ctx.ID().getText().equals(filename)) {
			reportNameMismatch(ctx.ID().getSymbol(), sourceName);
		}
	}

	public void checkXGroup() {
		XgroupContext ctx = (XgroupContext) lastPathNode();
		String mainName = ctx.name.getText();
		if (xruleMap.containsKey(mainName)) {
			reportDupXMain(ctx.name);
		} else {
			for (Token xpathRef : ctx.rules) {
				if (xruleMap.contains(mainName, xpathRef)) {
					reportDupXPath(xpathRef);
				} else {
					xruleMap.put(mainName, xpathRef);
				}
			}
		}
	}

	public void checkXPath() {
		XpathContext ctx = (XpathContext) lastPathNode();
		String xpath = ctx.name.getText();
		if (xpaths.contains(xpath)) {
			reportDupXPath(ctx.name);
		} else {
			xpaths.add(xpath);
		}
	}

	public void checkWord() {
		WordContext ctx = (WordContext) lastPathNode();
		if (ctx.ID() != null) {
			eval(ctx, ctx.ID().getSymbol());
		} else if (ctx.LITERAL() != null) {
			CommonToken literal = (CommonToken) ctx.LITERAL().getSymbol();
			String text = literal.getText();
			literal.setText(Strings.deQuote(text));
			eval(ctx, literal);
		}
	}

	public void checkReference() {
		ReferenceContext ctx = (ReferenceContext) lastPathNode();
		eval(ctx, ctx.REFERENCE().getSymbol());
	}

	public void completeChecks() {
		for (String xmain : xruleMap.keySet()) {
			for (Token xpathRef : xruleMap.get(xmain)) {
				if (!xpaths.contains(xpathRef.getText())) {
					reportMissingXPath(xpathRef);
				}
			}
		}
	}

	// check token content against valid rule, terminal, and label names
	private void eval(RuleContext ctx, Token token) {
		boolean isReference = false;

		String name = token.getText();
		int dot = name.indexOf(0);
		if (dot > -1) name = name.substring(0, dot); // removes .text, etc.
		if (name.startsWith("$")) {
			isReference = true;
			name = name.substring(1);
		}

		boolean lower = Character.isLowerCase(name.charAt(0));
		if (lower && ruleNames.contains(name)) return;
		if (!lower && termNames.contains(name)) return;
		if (Reflect.hasField(ctx, name)) return;

		// no matching rule, terminal, or label found
		String errType = !isReference ? "path element" : "or inaccessible reference";
		String errMsg = String.format("Unknown %s: %s", errType, name);
		errListener.syntaxError(parser, token, token.getLine(), token.getCharPositionInLine() + 1, errMsg, null);
	}

	private void reportNameMismatch(Token token, String filename) {
		String errMsg = String.format("Grammar name '%s' does not match file name '%s'", token.getText(), filename);
		errListener.syntaxError(parser, token, token.getLine(), token.getCharPositionInLine() + 1, errMsg, null);
	}

	private void reportDupXMain(Token token) {
		String errMsg = String.format("Duplicate main rule: %s", token.getText());
		errListener.syntaxError(parser, token, token.getLine(), token.getCharPositionInLine() + 1, errMsg, null);
	}

	private void reportDupXPath(Token token) {
		String errMsg = String.format("Duplicate xpath rule: %s", token.getText());
		errListener.syntaxError(parser, token, token.getLine(), token.getCharPositionInLine() + 1, errMsg, null);
	}

	private void reportMissingXPath(Token token) {
		String errMsg = String.format("Missing XPath rule: %s", token.getText());
		errListener.syntaxError(parser, token, token.getLine(), token.getCharPositionInLine() + 1, errMsg, null);
	}
}
