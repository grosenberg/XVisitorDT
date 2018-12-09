package net.certiv.xvisitordt.core.parser;

import java.io.IOException;
import java.lang.reflect.Constructor;

import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.Parser;
import org.antlr.v4.runtime.TokenStream;

import net.certiv.dsl.core.DslCore;
import net.certiv.dsl.core.log.Log;
import net.certiv.dsl.core.log.Log.LogLevel;
import net.certiv.dsl.core.model.ICodeUnit;
import net.certiv.dsl.core.model.IStatement;
import net.certiv.dsl.core.model.builder.DslModelMaker;
import net.certiv.dsl.core.parser.DslParseRecord;
import net.certiv.dsl.core.parser.DslSourceParser;
import net.certiv.dsl.core.util.Strings;
import net.certiv.dsl.core.util.antlr.AntlrUtil;
import net.certiv.dsl.core.util.eclipse.DynamicLoader;
import net.certiv.xvisitordt.core.XVisitorCore;
import net.certiv.xvisitordt.core.model.ModelData;
import net.certiv.xvisitordt.core.model.ModelType;
import net.certiv.xvisitordt.core.model.ModelUtil;
import net.certiv.xvisitordt.core.parser.gen.StructureVisitor;
import net.certiv.xvisitordt.core.parser.gen.ValidityVisitor;
import net.certiv.xvisitordt.core.parser.gen.XVisitorLexer;
import net.certiv.xvisitordt.core.parser.gen.XVisitorParser;

public class XVisitorSourceParser extends DslSourceParser {

	private static final Class<?>[] parserParams = new Class[] { TokenStream.class };
	private static final XVisitorTokenFactory TokenFactory = new XVisitorTokenFactory();

	public XVisitorSourceParser(DslParseRecord record) {
		super(record);
		Log.setLevel(this, LogLevel.Debug);
	}

	@Override
	public DslCore getDslCore() {
		return XVisitorCore.getDefault();
	}

	@Override
	public boolean modelContributor() {
		return true;
	}

	@Override
	public void parse() {
		record.cs = CharStreams.fromString(record.doc.get(), record.unit.getFile().getName());
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
	}

	@Override
	public void analyzeStructure(DslModelMaker maker) {
		try {
			StructureVisitor visitor = new StructureVisitor(record.tree);
			visitor.setMaker(maker);
			visitor.setSourceName(record.unit.getFile().getName());
			visitor.findAll();
		} catch (IllegalArgumentException e) {
			getDslErrorListener().generalError("Model analysis: %s @%s:%s", e);
			return;
		}

		validate();
	}

	private void validate() {
		try {
			ValidityVisitor visitor = new ValidityVisitor(record.tree);
			visitor.setHelper(record.parser, resolveRefParser(), getDslErrorListener());
			visitor.findAll();
		} catch (IOException e) {
			Log.error(this, "Validation failed: " + e.getMessage());
		}
	}

	private Parser resolveRefParser() throws IOException {
		String pkg = AntlrUtil.resolvePackageName(record.unit);
		if (pkg == null) throw new IOException("Cannot determine package path.");

		String name = resolveParserClassname(pkg, record.unit);
		if (name == null) throw new IOException("Cannot resolve reference parser class.");

		ClassLoader parent = Thread.currentThread().getContextClassLoader();
		try (DynamicLoader loader = DynamicLoader.create(record.unit.getProject(), parent)) {
			Class<?> pc = loader.loadClass(name);
			Constructor<?> constructor = pc.getConstructor(parserParams);
			return (Parser) constructor.newInstance((TokenStream) null);

		} catch (Exception e) {
			String cause = "Cannot instantiate reference parser: %s (%s)";
			throw new IOException(String.format(cause, name, e.getMessage()));

		} finally {
			Thread.currentThread().setContextClassLoader(parent);
		}
	}

	private String resolveParserClassname(String pkg, ICodeUnit unit) {
		for (IStatement stmt : unit.getStatements()) {
			if (ModelUtil.getModelType(stmt) == ModelType.Option) {
				ModelData data = (ModelData) stmt.getData();
				if (data.key.equals("parserClass")) {
					return pkg + Strings.DOT + data.value.getText().trim();
				}
			}
		}
		return null;
	}
}
