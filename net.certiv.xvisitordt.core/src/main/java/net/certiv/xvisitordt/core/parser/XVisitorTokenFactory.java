package net.certiv.xvisitordt.core.parser;

import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.TokenFactory;
import org.antlr.v4.runtime.TokenSource;
import org.antlr.v4.runtime.misc.Pair;

import net.certiv.xvisitordt.core.parser.gen.XVisitorLexer;

public class XVisitorTokenFactory implements TokenFactory<XVisitorToken> {

	public CharStream input;

	public XVisitorTokenFactory(CharStream input) {
		this.input = input;
	}

	@Override
	public XVisitorToken create(int type, String text) {
		return new XVisitorToken(type, text);
	}

	@Override
	public XVisitorToken create(Pair<TokenSource, CharStream> source, int type, String text, 
			int channel, int start,	int stop, int line, int charPositionInLine) {
		XVisitorToken token = new XVisitorToken(source, type, channel, start, stop);
		token.setLine(line);
		token.setCharPositionInLine(charPositionInLine);
		TokenSource tsrc = token.getTokenSource();
		token.setMode(((XVisitorLexer) tsrc)._mode);
		return token;
	}
}
