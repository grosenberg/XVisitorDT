package net.certiv.xvisitordt.core.parser;

import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.Lexer;

import net.certiv.dsl.core.DslCore;
import net.certiv.dsl.core.model.builder.DslModelMaker;
import net.certiv.dsl.core.parser.DslParseRecord;
import net.certiv.dsl.core.parser.DslSourceParser;
import net.certiv.dsl.core.util.Log;
import net.certiv.dsl.core.util.Log.LogLevel;
import net.certiv.xvisitordt.core.XVisitorCore;
import net.certiv.xvisitordt.core.parser.gen.StructureVisitor;
import net.certiv.xvisitordt.core.parser.gen.ValidityVisitor;
import net.certiv.xvisitordt.core.parser.gen.XVisitorLexer;
import net.certiv.xvisitordt.core.parser.gen.XVisitorParser;

public class XVisitorSourceParser extends DslSourceParser {

	private static final XVisitorTokenFactory TokenFactory = new XVisitorTokenFactory();

	public XVisitorSourceParser(DslParseRecord record) {
		super(record);
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
		lexer.setTokenFactory(TokenFactory);
		lexer.removeErrorListeners();
		lexer.addErrorListener(getDslErrorListener());

		record.ts = new CommonTokenStream(lexer);
		record.parser = new XVisitorParser(record.ts);
		record.parser.setTokenFactory(TokenFactory);
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

	@Override
	public boolean modelContributor() {
		return true;
	}
}
