package net.certiv.xvisitordt.core.parser;

import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.TerminalNode;

import net.certiv.antlr.runtime.xvisitor.Processor;
import net.certiv.dsl.core.model.IDslElement;
import net.certiv.dsl.core.model.IStatement.Form;
import net.certiv.dsl.core.model.IStatement.Realm;
import net.certiv.dsl.core.model.IStatement.Type;
import net.certiv.dsl.core.model.builder.DslModelMaker;
import net.certiv.xvisitordt.core.parser.gen.XVisitorParser.ActionContext;
import net.certiv.xvisitordt.core.parser.gen.XVisitorParser.GrammarSpecContext;
import net.certiv.xvisitordt.core.parser.gen.XVisitorParser.OptionContext;
import net.certiv.xvisitordt.core.parser.gen.XVisitorParser.OptionsSpecContext;
import net.certiv.xvisitordt.core.parser.gen.XVisitorParser.XgroupContext;
import net.certiv.xvisitordt.core.parser.gen.XVisitorParser.XpathContext;

/** Implementing functions for the outline tree walker. */
public abstract class StructureBuilder extends Processor {

	private DslModelMaker maker;

	public StructureBuilder(ParseTree tree) {
		super(tree);
	}

	public void setMaker(DslModelMaker maker) {
		this.maker = maker;
	}

	public void grammarModule() {
		GrammarSpecContext ctx = (GrammarSpecContext) lastPathNode();
		String name = ctx.ID().getText() + " [xvisitor]";
		ModelData data = new ModelData(ModelType.GrammarType, ctx, name);
		maker.module(ctx, ctx.ID().getText(), data); // XXX: hacked; need to fix grammar
		addRefName(ctx.ID(), ModelType.Value, Type.LITERAL, Form.DECLARATION, Realm.GLOBAL);
	}

	/** Called to begin the options block. */
	public void begOptionsBlock() {
		OptionsSpecContext ctx = (OptionsSpecContext) lastPathNode();
		ModelData data = new ModelData(ModelType.Options, ctx, "Options block");
		maker.statement(ctx, ctx.OPTIONS(), data);
		maker.block(IDslElement.BEG_BLOCK, ctx.LBRACE(), ctx.RBRACE(), null);
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
		maker.statement(ctx, id, data);
	}

	public void processPathRule() {
		XpathContext ctx = (XpathContext) lastPathNode();
		ModelData data = new ModelData(ModelType.PathRule, ctx, ctx.ID().getText());
		maker.statement(ctx, ctx.ID(), data);
	}

	public void endBlock() {
		ParserRuleContext ctx = (ParserRuleContext) lastPathNode();
		if (ctx instanceof OptionsSpecContext) {
			OptionsSpecContext cty = (OptionsSpecContext) ctx;
			maker.block(IDslElement.END_BLOCK, cty.LBRACE(), cty.RBRACE(), null);
		}
	}

	private void addRefName(ParseTree ctx, ModelType mType, Type type, Form form, Realm realm) {
		maker.field(ctx, ctx, type, form, realm, new ModelData(mType, ctx, ctx.getText()));
	}
}
