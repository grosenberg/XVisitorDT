package net.certiv.xvisitordt.core.formatter;

import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.RecognitionException;
import org.antlr.v4.runtime.tree.ParseTree;

import net.certiv.dsl.core.DslCore;
import net.certiv.dsl.core.formatter.DslCodeFormatter;
import net.certiv.dsl.core.parser.DslParseErrorListener;
import net.certiv.dsl.core.util.Log;
import net.certiv.dsl.core.util.Log.LogLevel;
import net.certiv.xvisitordt.core.XVisitorCore;
import net.certiv.xvisitordt.core.parser.XVisitorTokenFactory;
import net.certiv.xvisitordt.core.parser.gen.XVisitorLexer;
import net.certiv.xvisitordt.core.parser.gen.XVisitorParser;
import net.certiv.xvisitordt.core.parser.gen.XVisitorParser.GrammarSpecContext;

public class XVisitorSourceFormatter extends DslCodeFormatter {

	private XVisitorParser parser;

	public XVisitorSourceFormatter() {
		super();
		Log.setLevel(this, LogLevel.Debug);
		setCommentTokens(XVisitorParser.BLOCK_COMMENT, XVisitorParser.DOC_COMMENT, XVisitorParser.LINE_COMMENT);
		setLineCommentTokens(XVisitorParser.LINE_COMMENT);
		setVertSpacingTokens(XVisitorParser.VERT_WS);
		setHorzSpacingTokens(XVisitorParser.HORZ_WS);
		setListSeparatorTokens(XVisitorParser.COMMA);

		// setBlockDelimitersTokens( //
		// AntlrDTParser.OBRACE_OT, AntlrDTParser.CBRACE_OT, //
		// AntlrDTParser.ACTION_OB, AntlrDTParser.ACTION_CB, //
		// AntlrDTParser.RULE_COLON, AntlrDTParser.RULE_SEMI);
	}

	@Override
	public DslCore getDslCore() {
		return XVisitorCore.getDefault();
	}

	@Override
	public ParseTree parse(String name, String content, DslParseErrorListener errListener) throws RecognitionException {
		Log.debug(this, "Parse [name=" + name + "]");

		input = CharStreams.fromString(content);
		XVisitorLexer lexer = new XVisitorLexer(input);

		// lexer.setLexerHelper(new LexerHelper());
		XVisitorTokenFactory factory = new XVisitorTokenFactory(input);
		lexer.setTokenFactory(factory);
		tokens = new CommonTokenStream(lexer);

		parser = new XVisitorParser(tokens);
		parser.setTokenFactory(factory);
		parser.removeErrorListeners(); // remove ConsoleErrorListener to reduce the noise
		parser.addErrorListener(errListener); // add listener that feeds the error markers

		GrammarSpecContext parseTree = ((XVisitorParser) parser).grammarSpec();
		return parseTree;
	}

	@Override
	public void formatContent() {
		Log.debug(this, "Parse-tree [root=" + (parseTree != null ? "not null" : "null") + "]");
		if (parseTree == null) return;

		// walkers to format relevant elements of the ParseTree
		// first pass: build indentation structure
		// try {
		// IndentProcessor indenter = new IndentProcessor(tree);
		// indenter.setHelper(this);
		// indenter.findAll();
		// // dumpSpans();
		// } catch (Exception e) {
		// Log.error(this, "Indenter error", e);
		// }
		// // second pass: format
		// try {
		// FormatProcessor formatter = new FormatProcessor(tree);
		// formatter.setHelper(this);
		// formatter.findAll();
		// } catch (Exception e) {
		// Log.error(this, "Formatter error", e);
		// }
	}
}
