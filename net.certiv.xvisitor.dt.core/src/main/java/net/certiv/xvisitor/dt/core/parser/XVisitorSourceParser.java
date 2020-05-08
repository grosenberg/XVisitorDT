package net.certiv.xvisitor.dt.core.parser;

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
import net.certiv.dsl.core.model.builder.ModelBuilder;
import net.certiv.dsl.core.parser.DslParseRecord;
import net.certiv.dsl.core.parser.DslSourceParser;
import net.certiv.dsl.core.util.Strings;
import net.certiv.dsl.core.util.antlr.AntlrUtil;
import net.certiv.dsl.jdt.util.DynamicLoader;
import net.certiv.xvisitor.dt.core.XVisitorCore;
import net.certiv.xvisitor.dt.core.parser.gen.StructureVisitor;
import net.certiv.xvisitor.dt.core.parser.gen.ValidityVisitor;
import net.certiv.xvisitor.dt.core.parser.gen.XVisitorLexer;
import net.certiv.xvisitor.dt.core.parser.gen.XVisitorParser;

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
			lexer.addErrorListener(getErrorListener());

			record.ts = new CommonTokenStream(lexer);
			record.parser = new XVisitorParser(record.ts);
			record.parser.setTokenFactory(TokenFactory);
			record.parser.removeErrorListeners();
			record.parser.addErrorListener(getErrorListener());
			record.tree = ((XVisitorParser) record.parser).grammarSpec();
			return null;

		} catch (Exception | Error e) {
			getErrorListener().generalError(ERR_PARSER, e);
			return e;
		}
	}

	@Override
	public Throwable analyze(ModelBuilder builder) {
		try {
			StructureVisitor visitor = new StructureVisitor(record.tree);
			visitor.setSourceName(record.unit.getPackageName());
			visitor.setBuilder(builder);
			builder.beginAnalysis();
			visitor.findAll();
			builder.endAnalysis();
			return null;

		} catch (Exception | Error e) {
			getErrorListener().generalError(ERR_ANALYSIS, e);
			return e;
		}
	}

	@Override
	public Throwable validate() {
		try {
			Parser ref = resolveRefParser();
			if (ref != null) {
				ValidityVisitor visitor = new ValidityVisitor(record.tree);
				visitor.setHelper(record.parser, ref, getErrorListener());
				visitor.findAll();
			}
			return null;

		} catch (Exception | Error e) {
			getErrorListener().generalError(ERR_VALIDATE, e);
			return e;
		}
	}

	private Parser resolveRefParser() throws IOException {
		String pkg = AntlrUtil.resolveGrammarPackage(record.unit);
		if (pkg == null) {
			String msg = "Cannot determine package path for " + record.unit.getElementName();
			Log.warn(this, msg);
			throw new IOException(msg);
		}

		String name = AntlrUtil.resolveOptionValue(record.unit, "parserClass");
		if (name == null) {
			String msg = "Cannot determine parserClassname for " + record.unit.getElementName();
			Log.warn(this, msg);
			throw new IOException(msg);
		}
		name = pkg + Strings.DOT + name;

		ClassLoader parent = Thread.currentThread().getContextClassLoader();
		try (DynamicLoader loader = DynamicLoader.create(record.unit.getProject(), parent)) {
			Class<?> pc = loader.loadClass(name);
			Constructor<?> constructor = pc.getConstructor(parserParams);
			return (Parser) constructor.newInstance((TokenStream) null);

		} catch (Exception e) {
			String msg = String.format("Cannot instantiate reference parser: %s (%s)", name, e.getMessage());
			throw new IOException(msg);

		} finally {
			Thread.currentThread().setContextClassLoader(parent);
		}
	}
}
