package net.certiv.xvisitordt.core.parser;

import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.TerminalNode;

import net.certiv.antlr.runtime.xvisitor.Processor;
import net.certiv.dsl.core.parser.IModelAssembly;
import net.certiv.xvisitordt.core.parser.gen.XVisitorParser.ActionContext;
import net.certiv.xvisitordt.core.parser.gen.XVisitorParser.GrammarSpecContext;
import net.certiv.xvisitordt.core.parser.gen.XVisitorParser.OptionContext;
import net.certiv.xvisitordt.core.parser.gen.XVisitorParser.OptionsSpecContext;
import net.certiv.xvisitordt.core.parser.gen.XVisitorParser.XmainContext;
import net.certiv.xvisitordt.core.parser.gen.XVisitorParser.XpathContext;

/** Implementing functions for the outline tree walker. */
public abstract class OutlineAdaptor extends Processor {

	private IModelAssembly helper;

	private String gTypeName;
	private ParserRuleContext rootNode;

	public OutlineAdaptor(ParseTree tree) {
		super(tree);
	}

	public void setHelper(IModelAssembly helper) {
		this.helper = helper;
	}

	public void grammarModule() {
		GrammarSpecContext ctx = (GrammarSpecContext) lastPathNode();
		rootNode = ctx;
		captureGrammarType(ctx);
		gTypeName += ctx.ID().getText();
		ModelData data = new ModelData(ModelType.GrammarType, rootNode, gTypeName);
		helper.module(rootNode, data);
	}

	private void captureGrammarType(GrammarSpecContext ctx) {
		if (ctx.XVISITOR() != null) {
			gTypeName = "xvisitor grammar ";
		} else {
			gTypeName = "<xvisitor> grammar ";
		}
	}

	public void begOptionsBlock() {
		OptionsSpecContext ctx = (OptionsSpecContext) lastPathNode();
		ModelData data = new ModelData(ModelType.Options, ctx, "Options block");
		helper.statement(ctx, ctx.OPTIONS(), data);
		helper.blockBeg();
	}

	public void processOptionStatement() {
		OptionContext ctx = (OptionContext) lastPathNode();
		TerminalNode id = ctx.OPT_ID().get(0);
		ModelData data = new ModelData(ModelType.Option, ctx, id.getText(), ctx.optionValue());
		helper.statement(id, id, data);
	}

	public void processAtAction() {
		ActionContext ctx = (ActionContext) lastPathNode();
		ModelData data = new ModelData(ModelType.AtAction, ctx, "", ctx.ID());
		helper.statement(ctx, ctx.ID(), data);
	}

	public void processMainRule() {
		XmainContext ctx = (XmainContext) lastPathNode();
		TerminalNode id = ctx.ID().get(0);
		ModelData data = new ModelData(ModelType.ParserRule, ctx, id.getText());
		helper.statement(id, id, data);
	}

	public void processXPathRule() {
		XpathContext ctx = (XpathContext) lastPathNode();
		ModelData data = new ModelData(ModelType.LexerRule, ctx, ctx.ID().getText());
		helper.statement(ctx, ctx.ID(), data);
	}

	public void endBlock() {
		helper.blockEnd();
	}
}
