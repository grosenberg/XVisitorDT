package net.certiv.xvisitordt.core.parser;

import java.util.Collection;

import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.RecognitionException;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.xpath.XPath;

import net.certiv.dsl.core.DslCore;
import net.certiv.dsl.core.parser.DslParseErrorListener;
import net.certiv.dsl.core.parser.DslSourceParser;
import net.certiv.dsl.core.util.Log;
import net.certiv.dsl.core.util.Log.LogLevel;
import net.certiv.dsl.core.util.Strings;
import net.certiv.xvisitordt.core.XVisitorCore;
import net.certiv.xvisitordt.core.parser.gen.CodeAssistVisitor;
import net.certiv.xvisitordt.core.parser.gen.StructureVisitor;
import net.certiv.xvisitordt.core.parser.gen.ValidityVisitor;
import net.certiv.xvisitordt.core.parser.gen.XVisitorLexer;
import net.certiv.xvisitordt.core.parser.gen.XVisitorParser;
import net.certiv.xvisitordt.core.parser.gen.XVisitorParser.ActionContext;
import net.certiv.xvisitordt.core.parser.gen.XVisitorParser.GrammarSpecContext;

public class XVisitorSourceParser extends DslSourceParser {

	private String packageName;

	public XVisitorSourceParser() {
		super();
		Log.setLevel(this, LogLevel.Info);
	}

	@Override
	public DslCore getDslCore() {
		return XVisitorCore.getDefault();
	}

	/**
	 * Builds a ParseTree for the given content representing the source of a corresponding module
	 */
	@Override
	public ParseTree parse(String name, String content, DslParseErrorListener errListener)
			throws RecognitionException, Exception {
		Log.debug(this, "Parse [name=" + name + "]");

		input = CharStreams.fromString(content, name);
		XVisitorLexer lexer = new XVisitorLexer(input);
		XVisitorTokenFactory factory = new XVisitorTokenFactory(input);
		lexer.setTokenFactory(factory);
		tokens = new CommonTokenStream(lexer);

		parser = new XVisitorParser(tokens);
		parser.setTokenFactory(factory);
		parser.removeErrorListeners();
		parser.addErrorListener(errListener);
		GrammarSpecContext tree = ((XVisitorParser) parser).grammarSpec();

		ValidityVisitor walker = new ValidityVisitor(tree);
		walker.setHelper(parser, errListener);
		walker.findAll();

		return tree;
	}

	/** Make the internal element structure. */
	@Override
	public void buildStructure() {
		try {
			StructureVisitor visitor = new StructureVisitor(tree);
			visitor.setMaker(this);
			visitor.findAll();
		} catch (IllegalArgumentException e) {
			Log.error(this, "Model - Outline processing error", e);
		}
	}

	/**
	 * Tree pattern matcher used to identify the code elements that may be signficant in CodeAssist
	 * operations
	 */
	@Override
	public void buildCodeAssist() {
		try {
			CodeAssistVisitor walker = new CodeAssistVisitor(tree);
			walker.setHelper(this);
			walker.findAll();
		} catch (Exception e) {
			Log.error(this, "CodeAssist - Tree walk error", e);
		}
	}

	// /////////////////////////////////////////////////////////////////////////////////

	public String resolvePackageName() {
		if (packageName == null) {
			extractPackage();
		}
		return packageName;
	}

	/**
	 * Tree pattern matcher used to identify package defining statements
	 */
	private void extractPackage() {

		try {
			Collection<ParseTree> actions = XPath.findAll(tree, "/grammarSpec/action", parser);
			for (ParseTree action : actions) {
				ActionContext ctx = (ActionContext) action;
				if (ctx.ID().getText().equals("header")) {
					String content = Strings.deQuote(ctx.actionBlock().getText().trim());
					String[] lines = content.split("\\n");
					for (String line : lines) {
						if (line.trim().startsWith("package")) {
							int beg = line.indexOf("package") + "package".length() + 1;
							int end = line.lastIndexOf(';');
							packageName = line.substring(beg, end).trim();
							Log.debug(this, "ExtractPackage [parser=" + packageName + "]");
							break;
						}
					}
				}
			}
		} catch (Exception e) {
			Log.error(this, "ExtractPackage - Tree walk error", e);
		}
	}
}
