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

import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.TerminalNode;

import net.certiv.antlr.runtime.xvisitor.Processor;
import net.certiv.dsl.core.model.ModelType;
import net.certiv.dsl.core.model.Statement;
import net.certiv.dsl.core.model.builder.ModelBuilder;
import net.certiv.dsl.core.util.Strings;
import net.certiv.dsl.core.util.antlr.GrammarUtil;
import net.certiv.xvisitor.dt.core.parser.gen.XVisitorParser;
import net.certiv.xvisitor.dt.core.parser.gen.XVisitorParser.ActionContext;
import net.certiv.xvisitor.dt.core.parser.gen.XVisitorParser.GrammarSpecContext;
import net.certiv.xvisitor.dt.core.parser.gen.XVisitorParser.OptionContext;
import net.certiv.xvisitor.dt.core.parser.gen.XVisitorParser.OptionsSpecContext;
import net.certiv.xvisitor.dt.core.parser.gen.XVisitorParser.XgroupContext;
import net.certiv.xvisitor.dt.core.parser.gen.XVisitorParser.XpathContext;

/** Implementing functions for the outline tree walker. */
public abstract class StructureBuilder extends Processor {

	private ModelBuilder builder;
	private String name = ModelBuilder.UNKNOWN;

	public StructureBuilder(ParseTree tree) {
		super(tree);
	}

	public void setBuilder(ModelBuilder builder) {
		this.builder = builder;
	}

	public void setSourceName(String name) {
		this.name = name;
	}

	public void grammarModule() {
		GrammarSpecContext ctx = (GrammarSpecContext) lastPathNode();
		Specialization data = new Specialization(SpecializedType.GrammarType, ruleName(ctx), ctx, name);
		builder.module(ctx, name, data);
	}

	/** Called to begin the options block. */
	public void begOptionsBlock() {
		OptionsSpecContext ctx = (OptionsSpecContext) lastPathNode();
		Specialization data = new Specialization(SpecializedType.Options, ruleName(ctx), ctx, "Options block");
		Statement stmt = builder.statement(ModelType.BLOCK, ctx, ctx.OPTIONS(), data);
		builder.pushParent(stmt);
	}

	/** Called for each option within the options block. */
	public void processOptionStatement() {
		OptionContext ctx = (OptionContext) lastPathNode();
		Specialization data = new Specialization(SpecializedType.Option, ruleName(ctx), ctx, ctx.ID().getText(),
				ctx.optionValue());
		builder.statement(ModelType.EXPRESSION, ctx.ID(), ctx.ID(), data);
	}

	public void processAction() {
		ActionContext ctx = (ActionContext) lastPathNode();
		String name = ruleName(ctx);
		Specialization data = new Specialization(SpecializedType.AtAction, name, ctx, Strings.EMPTY, ctx.ID());
		Statement stmt = builder.statement(ModelType.NATIVE, ctx, ctx.ID(), data);

		builder.pushParent(stmt);
		String actionContent = GrammarUtil.getRuleText(ctx.actionBlock().text()).trim();
		actionContent = Strings.ellipsize(actionContent, 32);
		builder.field(ModelType.NATIVE, ctx, ctx, new Specialization(SpecializedType.Value, name, ctx, actionContent));
		builder.popParent();
	}

	public void processGroupRule() {
		XgroupContext ctx = (XgroupContext) lastPathNode();
		TerminalNode id = ctx.ID().get(0);
		Specialization data = new Specialization(SpecializedType.GroupRule, ruleName(ctx), ctx, id.getText());
		Statement stmt = builder.statement(ModelType.BLOCK, ctx, id, data);

		builder.pushParent(stmt);
		for (Token rule : ctx.rules) {
			builder.field(ModelType.FUNC, ctx, rule,
					new Specialization(SpecializedType.PathRule, ruleName(ctx), ctx, rule.getText()));
		}
		builder.popParent();
	}

	public void processPathRule() {
		XpathContext ctx = (XpathContext) lastPathNode();
		Specialization data = new Specialization(SpecializedType.PathRule, ruleName(ctx), ctx, ctx.ID().getText());
		Statement stmt = builder.statement(ModelType.FUNC, ctx, ctx.ID(), data);
		builder.pushParent(stmt);
	}

	public void endBlock() {
		builder.popParent();
	}

	private String ruleName(ParserRuleContext ctx) {
		return XVisitorParser.ruleNames[ctx.getRuleIndex()];
	}
}
