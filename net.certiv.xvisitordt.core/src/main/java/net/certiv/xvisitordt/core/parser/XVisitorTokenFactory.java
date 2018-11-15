package net.certiv.xvisitordt.core.parser;

import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.TokenFactory;
import org.antlr.v4.runtime.TokenSource;
import org.antlr.v4.runtime.misc.Pair;

public class XVisitorTokenFactory implements TokenFactory<XVisitorToken> {

	public XVisitorTokenFactory() {}

	@Override
	public XVisitorToken create(int type, String text) {
		return new XVisitorToken(type, text);
	}

	@Override
	public XVisitorToken create(Pair<TokenSource, CharStream> source, int type, String text, int channel, int start,
			int stop, int line, int charPositionInLine) {

		XVisitorToken token = new XVisitorToken(source, type, channel, start, stop);
		token.setText(text);
		token.setLine(line);
		token.setCharPositionInLine(charPositionInLine);
		token.setMode(((Lexer) source.a)._mode);
		return token;
	}
}
