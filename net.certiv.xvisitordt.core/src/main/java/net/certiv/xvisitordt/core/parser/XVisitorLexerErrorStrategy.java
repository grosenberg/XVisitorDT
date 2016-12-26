package net.certiv.xvisitordt.core.parser;

import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.LexerNoViableAltException;

import net.certiv.xvisitordt.core.parser.gen.XVisitorLexer;

/**
 * Base class for the Lexer. Combines functionality for <br>
 * <ul>
 * <li>lexer error strategy</li>
 * <li>various helper routines</li>
 * <ul>
 * 
 * @author Gbr
 */
public class XVisitorLexerErrorStrategy extends XVisitorLexer {

	// ///// Error strategy /////////////////////////////
	public XVisitorLexerErrorStrategy(CharStream input) {
		super(input);
	}

	public void recover(LexerNoViableAltException e) {
		// throw new RuntimeException(e); // Bail out
		super.recover(e);
	}

	// ///// Parse stream management ////////////////////

}
