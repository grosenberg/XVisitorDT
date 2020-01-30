package net.certiv.xvisitordt.core.parser;

import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.TerminalNode;

import net.certiv.antlr.runtime.xvisitor.Processor;
import net.certiv.dsl.core.model.ModelType;
import net.certiv.dsl.core.model.ModuleStmt;
import net.certiv.dsl.core.model.Statement;
import net.certiv.dsl.core.model.builder.ModelBuilder;
import net.certiv.xvisitordt.core.model.Specialization;
import net.certiv.xvisitordt.core.model.SpecializedType;
import net.certiv.xvisitordt.core.parser.gen.XVisitorParser.ActionContext;
import net.certiv.xvisitordt.core.parser.gen.XVisitorParser.GrammarSpecContext;
import net.certiv.xvisitordt.core.parser.gen.XVisitorParser.OptionContext;
import net.certiv.xvisitordt.core.parser.gen.XVisitorParser.OptionsSpecContext;
import net.certiv.xvisitordt.core.parser.gen.XVisitorParser.XgroupContext;
import net.certiv.xvisitordt.core.parser.gen.XVisitorParser.XpathContext;

/** Implementing functions for the outline tree walker. */
public abstract class StructureBuilder extends Processor {

	private ModelBuilder builder;
	private String name = "<Undefined>"; // typically, the source file name

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
		Specialization data = new Specialization(SpecializedType.GrammarType, ctx, name);
		ModuleStmt module = builder.module(ctx, name, data);
		builder.pushParent(module);
	}

	/** Called to begin the options block. */
	public void begOptionsBlock() {
		OptionsSpecContext ctx = (OptionsSpecContext) lastPathNode();
		Specialization data = new Specialization(SpecializedType.Options, ctx, "Options block");
		Statement stmt = builder.statement(ctx, ctx.OPTIONS(), data);
		builder.pushParent(stmt);
	}

	/** Called for each option within the options block. */
	public void processOptionStatement() {
		OptionContext ctx = (OptionContext) lastPathNode();
		Specialization data = new Specialization(SpecializedType.Option, ctx, ctx.ID().getText(), ctx.optionValue());
		builder.statement(ctx.ID(), ctx.ID(), data);
	}

	public void processAction() {
		ActionContext ctx = (ActionContext) lastPathNode();
		Specialization data = new Specialization(SpecializedType.AtAction, ctx, "", ctx.ID());
		builder.statement(ctx, ctx.ID(), data);
	}

	public void processGroupRule() {
		XgroupContext ctx = (XgroupContext) lastPathNode();
		TerminalNode id = ctx.ID().get(0);
		Specialization data = new Specialization(SpecializedType.GroupRule, ctx, id.getText());
		Statement stmt = builder.statement(ctx, id, data);
		builder.pushParent(stmt);
		for (Token rule : ctx.rules) {
			builder.field(ctx, rule, ModelType.LITERAL,
					new Specialization(SpecializedType.PathRule, ctx, rule.getText()));
		}
		builder.popParent();
	}

	public void processPathRule() {
		XpathContext ctx = (XpathContext) lastPathNode();
		Specialization data = new Specialization(SpecializedType.PathRule, ctx, ctx.ID().getText());
		Statement stmt = builder.statement(ctx, ctx.ID(), data);
		builder.pushParent(stmt);
	}

	public void endBlock() {
		builder.popParent();
	}
}
