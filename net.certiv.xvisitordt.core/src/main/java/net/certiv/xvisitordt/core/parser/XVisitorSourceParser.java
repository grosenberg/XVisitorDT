package net.certiv.xvisitordt.core.parser;

import java.util.Collection;

import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.xpath.XPath;

import net.certiv.dsl.core.DslCore;
import net.certiv.dsl.core.model.builder.DslModelMaker;
import net.certiv.dsl.core.parser.DslSourceParser;
import net.certiv.dsl.core.util.Log;
import net.certiv.dsl.core.util.Log.LogLevel;
import net.certiv.dsl.core.util.Strings;
import net.certiv.xvisitordt.core.XVisitorCore;
import net.certiv.xvisitordt.core.parser.gen.StructureVisitor;
import net.certiv.xvisitordt.core.parser.gen.ValidityVisitor;
import net.certiv.xvisitordt.core.parser.gen.XVisitorLexer;
import net.certiv.xvisitordt.core.parser.gen.XVisitorParser;
import net.certiv.xvisitordt.core.parser.gen.XVisitorParser.ActionContext;

public class XVisitorSourceParser extends DslSourceParser {

	private static final XVisitorTokenFactory Factory = new XVisitorTokenFactory();

	private String packageName;

	public XVisitorSourceParser() {
		super();
		Log.setLevel(this, LogLevel.Info);
	}

	@Override
	public DslCore getDslCore() {
		return XVisitorCore.getDefault();
	}

	@Override
	public void parse() {
		record.cs = CharStreams.fromString(record.doc.get());
		Lexer lexer = new XVisitorLexer(record.cs);
		lexer.setTokenFactory(Factory);
		lexer.removeErrorListeners();
		lexer.addErrorListener(getDslErrorListener());

		record.ts = new CommonTokenStream(lexer);
		record.parser = new XVisitorParser(record.ts);
		record.parser.setTokenFactory(Factory);
		record.parser.removeErrorListeners();
		record.parser.addErrorListener(getDslErrorListener());
		record.tree = ((XVisitorParser) record.parser).grammarSpec();

		ValidityVisitor visitor = new ValidityVisitor(record.tree);
		visitor.setHelper(record.parser, getDslErrorListener());
		visitor.findAll();
	}

	@Override
	public void analyzeStructure(DslModelMaker maker) {
		try {
			StructureVisitor visitor = new StructureVisitor(record.tree);
			visitor.setMaker(maker);
			visitor.findAll();
		} catch (IllegalArgumentException e) {
			getDslErrorListener().generalError("Model analysis: %s @%s:%s", e);
		}
	}

	// /////////////////////////////////////////////////////////////////////////////////

	public String resolvePackageName() {
		if (packageName == null) extractPackage();
		return packageName;
	}

	// Tree pattern matcher used to identify package defining statements
	private void extractPackage() {

		try {
			Collection<ParseTree> actions = XPath.findAll(record.tree, "/grammarSpec/action", record.parser);

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
							break;
						}
					}
				}
			}
		} catch (Exception e) {
			Log.error(this, "ExtractPackage - Tree walk error", e);
		}
	}

	@Override
	public void build() {}

	@Override
	public boolean modelContributor() {
		return false;
	}
}
