package net.certiv.xvisitordt.core.parser;

import net.certiv.dsl.core.formatter.IDslCodeFormatter;
import net.certiv.dsl.core.parser.DslSourceParser;
import net.certiv.dsl.core.parser.DslSourceParserFactory;
import net.certiv.xvisitordt.core.formatter.XVisitorSourceFormatter;

public class XVisitorSourceParserFactory extends DslSourceParserFactory {

	public XVisitorSourceParserFactory() {
		super();
	}

	@Override
	public DslSourceParser createSourceParser() {
		return new XVisitorSourceParser();
	}

	@Override
	public IDslCodeFormatter createCodeFormatter() {
		return new XVisitorSourceFormatter();
	}
}
