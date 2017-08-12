package net.certiv.xvisitordt.core.parser;

import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CommonToken;
import org.antlr.v4.runtime.TokenSource;
import org.antlr.v4.runtime.misc.Pair;

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

	@Override
	public String toString() {
		String chanStr = "chan=" + channel;
		if (channel == 0) chanStr = "chan=Default";
		if (channel == 1) chanStr = "chan=Hidden";
		String modeStr = "mode=" + XVisitorLexer.modeNames[_mode];
		if (_mode == 0) modeStr = "mode=Default";
		String txt = getText();
		if (txt != null) {
			txt = txt.replaceAll("\n", "\\n");
			txt = txt.replaceAll("\r", "\\r");
			txt = txt.replaceAll("\t", "\\t");
		} else {
			txt = "<no text>";
		}
		String tokenName = "<EOF>";
		if (type > -1) tokenName = XVisitorLexer.VOCABULARY.getDisplayName(type);
		return "[@" + getTokenIndex() + ", <" + start + ":" + stop + "> " + tokenName + "(" + type + ")='" + txt + "'"
				+ ", " + chanStr + ", " + modeStr + ", " + line + ":" + getCharPositionInLine() + "]";
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
}
