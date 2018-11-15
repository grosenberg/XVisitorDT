package net.certiv.xvisitordt.core.parser;

import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CommonToken;
import org.antlr.v4.runtime.TokenSource;
import org.antlr.v4.runtime.misc.Pair;

import net.certiv.dsl.core.util.Strings;
import net.certiv.xvisitordt.core.parser.gen.XVisitorLexer;

public class XVisitorToken extends CommonToken {

	private int _mode;
	private boolean hasStyles;
	private boolean hasBody;

	public XVisitorToken(int type, String text) {
		super(type, text);
	}

	public XVisitorToken(Pair<TokenSource, CharStream> source, int type, int channel, int start, int stop) {
		super(source, type, channel, start, stop);
	}

	public void setMode(int mode) {
		_mode = mode;
	}

	public void styles(boolean hasStyles) {
		this.hasStyles = hasStyles;
	}

	public void body(boolean hasBody) {
		this.hasBody = hasBody;
	}

	public boolean styles() {
		return hasStyles;
	}

	public boolean body() {
		return hasBody;
	}

	public boolean isTag() {
		char c = getText().charAt(0);
		return Character.isUpperCase(c);
	}

	@Override
	public String toString() {
		String text = Strings.encode(getText());
		String tname = XVisitorLexer.VOCABULARY.getDisplayName(type);
		String mname = _mode == 0 ? "default" : XVisitorLexer.modeNames[_mode];
		String chan = channel == 0 ? "DEFAULT" : XVisitorLexer.channelNames[channel];

		String msg = "%-2d %2d:%-2d %s (%s:%s:%s) '%s'";
		return String.format(msg, index, line, charPositionInLine, tname, type, mname, chan, text);
	}
}
