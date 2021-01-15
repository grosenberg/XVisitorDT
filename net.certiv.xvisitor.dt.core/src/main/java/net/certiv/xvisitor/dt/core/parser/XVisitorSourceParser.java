/*******************************************************************************
 * Copyright (c) 2012, 2020 Certiv Analytics.
 *
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package net.certiv.xvisitor.dt.core.parser;

import java.lang.reflect.Constructor;

import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.Parser;
import org.antlr.v4.runtime.TokenStream;
import org.apache.logging.log4j.Level;

import org.eclipse.core.resources.IResourceStatus;

import net.certiv.dsl.core.DslCore;
import net.certiv.dsl.core.log.Log;
import net.certiv.dsl.core.model.ModelException;
import net.certiv.dsl.core.model.builder.ModelBuilder;
import net.certiv.dsl.core.parser.DslErrorListener;
import net.certiv.dsl.core.parser.DslParseRecord;
import net.certiv.dsl.core.parser.DslSourceParser;
import net.certiv.dsl.core.parser.Origin;
import net.certiv.dsl.core.util.Strings;
import net.certiv.dsl.jdt.util.DynamicLoader;
import net.certiv.xvisitor.dt.core.XVisitorCore;
import net.certiv.xvisitor.dt.core.builder.BuildUtil;
import net.certiv.xvisitor.dt.core.parser.gen.StructureVisitor;
import net.certiv.xvisitor.dt.core.parser.gen.ValidityVisitor;
import net.certiv.xvisitor.dt.core.parser.gen.XVisitorLexer;
import net.certiv.xvisitor.dt.core.parser.gen.XVisitorParser;

public class XVisitorSourceParser extends DslSourceParser {

	private static final Class<?>[] parserParams = new Class[] { TokenStream.class };
	private static final XVisitorTokenFactory TokenFactory = new XVisitorTokenFactory();

	public XVisitorSourceParser(DslParseRecord record) {
		super(record);
		Log.setLevel(this, Level.DEBUG);
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
		DslErrorListener auditor = getErrorListener();
		try {
			record.setCharStream(CharStreams.fromString(getContent(), record.unit.getFile().getName()));
			Lexer lexer = new XVisitorLexer(record.getCharStream());
			lexer.setTokenFactory(TokenFactory);
			lexer.removeErrorListeners();
			lexer.addErrorListener(auditor);
			record.setTokenStream(new CommonTokenStream(lexer));

			XVisitorParser parser = new XVisitorParser(record.getTokenStream());
			parser.setTokenFactory(TokenFactory);
			parser.removeErrorListeners();
			parser.addErrorListener(auditor);
			record.setParser(parser);
			record.setTree(parser.grammarSpec());

			return null;

		} catch (Exception | Error e) {
			auditor.generalError(Origin.GENERAL, ERR_PARSER, e);
			return e;
		}
	}

	@Override
	public Throwable analyze(ModelBuilder builder) {
		try {
			StructureVisitor visitor = new StructureVisitor(record.getTree());
			visitor.setSourceName(record.unit.getPackageName());
			visitor.setBuilder(builder);
			builder.beginAnalysis();
			visitor.findAll();
			builder.endAnalysis();
			return null;

		} catch (Exception | Error e) {
			getErrorListener().generalError(Origin.ANALYSIS, ERR_ANALYSIS, e);
			return e;
		}
	}

	@Override
	public Throwable validate() {
		try {
			Parser ref = resolveRefParser();
			if (ref != null) {
				ValidityVisitor visitor = new ValidityVisitor(record.getTree());
				visitor.setHelper(record.getParser(), ref, getErrorListener());
				visitor.findAll();
			}
			return null;

		} catch (Exception | Error e) {
			getErrorListener().generalError(Origin.VALIDATE, ERR_VALIDATE, e);
			return e;
		}
	}

	private Parser resolveRefParser() throws ModelException {
		String pkg = BuildUtil.grammarDefinedPackage(record);
		if (pkg == null) {
			String msg = "Cannot determine package path for " + record.unit.getElementName();
			Log.warn(this, msg);
			throw new ModelException(IResourceStatus.INVALID_RESOURCE_NAME, msg);
		}

		String refName = BuildUtil.resolveGrammarDefinedOptionValue(record, "parserClass");
		if (refName == null) {
			String msg = "Cannot determine parserClassname for " + record.unit.getElementName();
			Log.warn(this, msg);
			throw new ModelException(IResourceStatus.INVALID_RESOURCE_NAME, msg);
		}
		refName = pkg + Strings.DOT + refName;

		ClassLoader parent = Thread.currentThread().getContextClassLoader();
		try (DynamicLoader loader = DynamicLoader.create(record.unit.getProject(), parent)) {
			Class<?> pc = loader.loadClass(refName);
			Constructor<?> constructor = pc.getConstructor(parserParams);
			return (Parser) constructor.newInstance((TokenStream) null);

		} catch (Exception e) {
			String msg = String.format("Cannot instantiate reference parser: %s (%s)", refName, e.getMessage());
			throw new ModelException(IResourceStatus.OPERATION_FAILED, msg);

		} finally {
			Thread.currentThread().setContextClassLoader(parent);
		}
	}
}
