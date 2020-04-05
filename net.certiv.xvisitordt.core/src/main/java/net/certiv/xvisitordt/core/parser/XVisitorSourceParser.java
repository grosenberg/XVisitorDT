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
import net.certiv.dsl.core.model.builder.ModelBuilder;
import net.certiv.dsl.core.parser.DslParseRecord;
import net.certiv.dsl.core.parser.DslSourceParser;
import net.certiv.dsl.core.util.Chars;
import net.certiv.dsl.jdt.util.DynamicLoader;
import net.certiv.xvisitordt.core.XVisitorCore;
import net.certiv.xvisitordt.core.model.ModelUtil;
import net.certiv.xvisitordt.core.model.Specialization;
import net.certiv.xvisitordt.core.model.SpecializedType;
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
	public boolean doAnalysis() {
		return true;
	}

	@Override
	public boolean doValidate() {
		return true;
	}

	@Override
	public Throwable parse() {
		try {
			record.cs = CharStreams.fromString(getContent(), record.unit.getFile().getName());
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
			return null;

		} catch (Exception | Error e) {
			getDslErrorListener().generalError(ERR_PARSER, e);
			return e;
		}
	}

	@Override
	public Throwable analyze(ModelBuilder builder) {
		try {
			if (record.hasTree()) {
				StructureVisitor visitor = new StructureVisitor(record.tree);
				visitor.setBuilder(builder);
				visitor.setSourceName(record.unit.getFile().getName());
				builder.beginAnalysis();
				visitor.findAll();
				builder.endAnalysis();
			}
			return null;

		} catch (Exception | Error e) {
			getDslErrorListener().generalError(ERR_ANALYSIS, e);
			return e;
		}
	}

	@Override
	public Throwable validate() {
		try {
			Parser ref = resolveRefParser();
			if (ref != null) {
				ValidityVisitor visitor = new ValidityVisitor(record.tree);
				visitor.setHelper(record.parser, ref, getDslErrorListener());
				visitor.findAll();
			}
			return null;

		} catch (Exception | Error e) {
			getDslErrorListener().generalError(ERR_VALIDATE, e);
			return e;
		}
	}

	private Parser resolveRefParser() throws IOException {
		String pkg = getDslCore().getLangManager().resolveGrammarPackage(record.unit);
		if (pkg == null) {
			Log.warn(this, "Cannot determine package path for " + record.unit.getElementName());
			return null;
		}

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
			if (ModelUtil.getSpecializedType(stmt) == SpecializedType.Option) {
				Specialization data = (Specialization) stmt.getData();
				if (data.name.equals("parserClass")) {
					return pkg + Chars.DOT + data.value.getText().trim();
				}
			}
		}
		return null;
	}
}
