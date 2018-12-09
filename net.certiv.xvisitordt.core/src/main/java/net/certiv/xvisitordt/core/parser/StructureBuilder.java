package net.certiv.xvisitordt.core.parser;

import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.TerminalNode;

import net.certiv.antlr.runtime.xvisitor.Processor;
import net.certiv.dsl.core.model.IStatement.Form;
import net.certiv.dsl.core.model.IStatement.Realm;
import net.certiv.dsl.core.model.IStatement.Type;
import net.certiv.dsl.core.model.ModuleStmt;
import net.certiv.dsl.core.model.Statement;
import net.certiv.dsl.core.model.builder.DslModelMaker;
import net.certiv.xvisitordt.core.model.ModelData;
import net.certiv.xvisitordt.core.model.ModelType;
import net.certiv.xvisitordt.core.parser.gen.XVisitorParser.ActionContext;
import net.certiv.xvisitordt.core.parser.gen.XVisitorParser.GrammarSpecContext;
import net.certiv.xvisitordt.core.parser.gen.XVisitorParser.OptionContext;
import net.certiv.xvisitordt.core.parser.gen.XVisitorParser.OptionsSpecContext;
import net.certiv.xvisitordt.core.parser.gen.XVisitorParser.XgroupContext;
import net.certiv.xvisitordt.core.parser.gen.XVisitorParser.XpathContext;

/** Implementing functions for the outline tree walker. */
public abstract class StructureBuilder extends Processor {

	private DslModelMaker maker;
	private String name = "<Undefined>"; // typically, the source file name

	public StructureBuilder(ParseTree tree) {
		super(tree);
	}

	public void setMaker(DslModelMaker maker) {
		this.maker = maker;
	}

	public void setSourceName(String name) {
		this.name = name;
	}

	public void grammarModule() {
		GrammarSpecContext ctx = (GrammarSpecContext) lastPathNode();
		ModelData data = new ModelData(ModelType.GrammarType, ctx, name);
		ModuleStmt module = maker.module(ctx, name, data);
		maker.pushParent(module);
	}

	/** Called to begin the options block. */
	public void begOptionsBlock() {
		OptionsSpecContext ctx = (OptionsSpecContext) lastPathNode();
		ModelData data = new ModelData(ModelType.Options, ctx, "Options block");
		Statement stmt = maker.statement(ctx, ctx.OPTIONS(), data);
		maker.pushParent(stmt);
	}

	/** Called for each option within the options block. */
	public void processOptionStatement() {
		OptionContext ctx = (OptionContext) lastPathNode();
		ModelData data = new ModelData(ModelType.Option, ctx, ctx.ID().getText(), ctx.optionValue());
		maker.statement(ctx.ID(), ctx.ID(), data);
	}

	public void processAction() {
		ActionContext ctx = (ActionContext) lastPathNode();
		ModelData data = new ModelData(ModelType.AtAction, ctx, "", ctx.ID());
		maker.statement(ctx, ctx.ID(), data);
	}

	public void processGroupRule() {
		XgroupContext ctx = (XgroupContext) lastPathNode();
		TerminalNode id = ctx.ID().get(0);
		ModelData data = new ModelData(ModelType.GroupRule, ctx, id.getText());
		Statement stmt = maker.statement(ctx, id, data);
		maker.pushParent(stmt);
		for (Token rule : ctx.rules) {
			maker.field(ctx, rule, Type.LITERAL, Form.DECLARATION, Realm.GLOBAL,
					new ModelData(ModelType.PathRule, ctx, rule.getText()));
		}
		maker.popParent();
	}

	public void processPathRule() {
		XpathContext ctx = (XpathContext) lastPathNode();
		ModelData data = new ModelData(ModelType.PathRule, ctx, ctx.ID().getText());
		Statement stmt = maker.statement(ctx, ctx.ID(), data);
		maker.pushParent(stmt);
	}

	public void endBlock() {
		maker.popParent();
	}
}
