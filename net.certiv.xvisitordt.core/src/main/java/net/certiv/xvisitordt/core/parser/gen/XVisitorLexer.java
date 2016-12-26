// Generated from D:/DevFiles/Eclipse/Dsl/xvisitordt/net.certiv.xvisitordt.core/src/main/java/net/certiv/xvisitordt/core/parser/XVisitorLexer.g4 by ANTLR 4.5.3

	package net.certiv.xvisitordt.core.parser.gen;

	import net.certiv.xvisitordt.core.parser.LexerAdaptor;

import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class XVisitorLexer extends LexerAdaptor {
	static { RuntimeMetaData.checkVersion("4.5.3", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		TEXT=1, RBRACE=2, DOC_COMMENT=3, BLOCK_COMMENT=4, LINE_COMMENT=5, OPTIONS=6, 
		LBRACE=7, GRAMMAR=8, XVISITOR=9, COLON=10, COMMA=11, SEMI=12, ASSIGN=13, 
		QUESTION=14, STAR=15, AT=16, ANY=17, SEP=18, DOT=19, OR=20, NOT=21, ID=22, 
		LITERAL=23, HORZ_WS=24, VERT_WS=25, ERRCHAR=26, OPT_LBRACE=27, OPT_RBRACE=28, 
		OPT_ID=29, OPT_LITERAL=30, OPT_DOT=31, OPT_ASSIGN=32, OPT_SEMI=33, OPT_STAR=34, 
		OPT_INT=35, ABLOCK_RBRACE=36, ONENTRY=37, ONEXIT=38, REFERENCE=39;
	public static final int Options = 1;
	public static final int ActionBlock = 2;
	public static String[] modeNames = {
		"DEFAULT_MODE", "Options", "ActionBlock"
	};

	public static final String[] ruleNames = {
		"DOC_COMMENT", "BLOCK_COMMENT", "LINE_COMMENT", "OPTIONS", "LBRACE", "GRAMMAR", 
		"XVISITOR", "COLON", "COMMA", "SEMI", "ASSIGN", "QUESTION", "STAR", "AT", 
		"ANY", "SEP", "DOT", "OR", "NOT", "ID", "LITERAL", "HORZ_WS", "VERT_WS", 
		"ERRCHAR", "OPT_LBRACE", "OPT_RBRACE", "OPT_ID", "OPT_LITERAL", "OPT_DOT", 
		"OPT_ASSIGN", "OPT_SEMI", "OPT_STAR", "OPT_INT", "OPT_DOC_COMMENT", "OPT_BLOCK_COMMENT", 
		"OPT_LINE_COMMENT", "OPT_HORZ_WS", "OPT_VERT_WS", "ABLOCK_LBRACE", "ABLOCK_RBRACE", 
		"ABLOCK_DOC_COMMENT", "ABLOCK_BLOCK_COMMENT", "ABLOCK_LINE_COMMENT", "ONENTRY", 
		"ONEXIT", "ABLOCK_STRING", "ABLOCK_CHAR", "REFERENCE", "ABLOCK_TEXT", 
		"ABLOCK_OTHER", "Colon", "Comma", "Semi", "Assign", "Question", "Star", 
		"At", "Any", "Sep", "Bang", "Dot", "LBrace", "RBrace", "Or", "Not", "Dollar", 
		"Hws", "Vws", "DocComment", "BlockComment", "LineComment", "NameChar", 
		"NameStartChar", "SglQuoteLiteral", "DblQuoteLiteral", "Int", "HexDigit", 
		"EscSeq", "EscUnicode"
	};

	private static final String[] _LITERAL_NAMES = {
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, "TEXT", "RBRACE", "DOC_COMMENT", "BLOCK_COMMENT", "LINE_COMMENT", 
		"OPTIONS", "LBRACE", "GRAMMAR", "XVISITOR", "COLON", "COMMA", "SEMI", 
		"ASSIGN", "QUESTION", "STAR", "AT", "ANY", "SEP", "DOT", "OR", "NOT", 
		"ID", "LITERAL", "HORZ_WS", "VERT_WS", "ERRCHAR", "OPT_LBRACE", "OPT_RBRACE", 
		"OPT_ID", "OPT_LITERAL", "OPT_DOT", "OPT_ASSIGN", "OPT_SEMI", "OPT_STAR", 
		"OPT_INT", "ABLOCK_RBRACE", "ONENTRY", "ONEXIT", "REFERENCE"
	};
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}


	public XVisitorLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "XVisitorLexer.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public String[] getModeNames() { return modeNames; }

	@Override
	public ATN getATN() { return _ATN; }

	@Override
	public void action(RuleContext _localctx, int ruleIndex, int actionIndex) {
		switch (ruleIndex) {
		case 39:
			ABLOCK_RBRACE_action((RuleContext)_localctx, actionIndex);
			break;
		}
	}
	private void ABLOCK_RBRACE_action(RuleContext _localctx, int actionIndex) {
		switch (actionIndex) {
		case 0:
			 handleRightTerminator(TEXT, RBRACE); 
			break;
		}
	}

	public static final String _serializedATN =
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\2)\u021c\b\1\b\1\b"+
		"\1\4\2\t\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n"+
		"\t\n\4\13\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21"+
		"\4\22\t\22\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30"+
		"\4\31\t\31\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37"+
		"\4 \t \4!\t!\4\"\t\"\4#\t#\4$\t$\4%\t%\4&\t&\4\'\t\'\4(\t(\4)\t)\4*\t"+
		"*\4+\t+\4,\t,\4-\t-\4.\t.\4/\t/\4\60\t\60\4\61\t\61\4\62\t\62\4\63\t\63"+
		"\4\64\t\64\4\65\t\65\4\66\t\66\4\67\t\67\48\t8\49\t9\4:\t:\4;\t;\4<\t"+
		"<\4=\t=\4>\t>\4?\t?\4@\t@\4A\tA\4B\tB\4C\tC\4D\tD\4E\tE\4F\tF\4G\tG\4"+
		"H\tH\4I\tI\4J\tJ\4K\tK\4L\tL\4M\tM\4N\tN\4O\tO\4P\tP\3\2\3\2\3\2\3\2\3"+
		"\3\3\3\3\3\3\3\3\4\3\4\3\4\3\4\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5"+
		"\3\6\3\6\3\6\3\6\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\b\3\b\3\b\3\b\3\b\3"+
		"\b\3\b\3\b\3\b\3\t\3\t\3\n\3\n\3\13\3\13\3\f\3\f\3\r\3\r\3\16\3\16\3\17"+
		"\3\17\3\20\3\20\3\21\3\21\3\22\3\22\3\23\3\23\3\24\3\24\3\25\3\25\7\25"+
		"\u00e9\n\25\f\25\16\25\u00ec\13\25\3\26\3\26\5\26\u00f0\n\26\3\27\6\27"+
		"\u00f3\n\27\r\27\16\27\u00f4\3\27\3\27\3\30\6\30\u00fa\n\30\r\30\16\30"+
		"\u00fb\3\30\3\30\3\31\3\31\3\31\3\31\3\32\3\32\3\33\3\33\3\33\3\33\3\34"+
		"\3\34\7\34\u010c\n\34\f\34\16\34\u010f\13\34\3\35\3\35\5\35\u0113\n\35"+
		"\3\36\3\36\3\37\3\37\3 \3 \3!\3!\3\"\3\"\3#\3#\3#\3#\3#\3$\3$\3$\3$\3"+
		"$\3%\3%\3%\3%\3%\3&\6&\u012f\n&\r&\16&\u0130\3&\3&\3&\3\'\6\'\u0137\n"+
		"\'\r\'\16\'\u0138\3\'\3\'\3\'\3(\3(\3(\3(\3(\3)\3)\3)\3*\3*\3*\3*\3+\3"+
		"+\3+\3+\3,\3,\3,\3,\3-\3-\7-\u0154\n-\f-\16-\u0157\13-\3-\3-\3-\3-\3-"+
		"\3-\3-\3-\3-\3.\3.\7.\u0164\n.\f.\16.\u0167\13.\3.\3.\3.\3.\3.\3.\3.\3"+
		".\3/\3/\3/\3/\3\60\3\60\3\60\3\60\3\61\3\61\6\61\u017b\n\61\r\61\16\61"+
		"\u017c\3\61\3\61\6\61\u0181\n\61\r\61\16\61\u0182\5\61\u0185\n\61\3\62"+
		"\6\62\u0188\n\62\r\62\16\62\u0189\3\62\3\62\3\63\3\63\3\63\3\63\3\64\3"+
		"\64\3\65\3\65\3\66\3\66\3\67\3\67\38\38\39\39\3:\3:\3;\3;\3;\3<\3<\3="+
		"\3=\3>\3>\3?\3?\3@\3@\3A\3A\3B\3B\3C\3C\3D\3D\3E\3E\3F\3F\3F\3F\3F\7F"+
		"\u01bc\nF\fF\16F\u01bf\13F\3F\3F\3F\3G\3G\3G\3G\7G\u01c8\nG\fG\16G\u01cb"+
		"\13G\3G\3G\3G\3H\3H\7H\u01d2\nH\fH\16H\u01d5\13H\3H\3H\7H\u01d9\nH\fH"+
		"\16H\u01dc\13H\3H\3H\7H\u01e0\nH\fH\16H\u01e3\13H\7H\u01e5\nH\fH\16H\u01e8"+
		"\13H\3I\3I\5I\u01ec\nI\3J\3J\3K\3K\3K\7K\u01f3\nK\fK\16K\u01f6\13K\3K"+
		"\3K\3L\3L\3L\7L\u01fd\nL\fL\16L\u0200\13L\3L\3L\3M\6M\u0205\nM\rM\16M"+
		"\u0206\3N\3N\3O\3O\3O\5O\u020e\nO\3P\3P\3P\3P\3P\5P\u0215\nP\5P\u0217"+
		"\nP\5P\u0219\nP\5P\u021b\nP\4\u01bd\u01c9\2Q\5\5\7\6\t\7\13\b\r\t\17\n"+
		"\21\13\23\f\25\r\27\16\31\17\33\20\35\21\37\22!\23#\24%\25\'\26)\27+\30"+
		"-\31/\32\61\33\63\34\65\35\67\369\37; =!?\"A#C$E%G\2I\2K\2M\2O\2Q\2S&"+
		"U\2W\2Y\2[\'](_\2a\2c)e\2g\2i\2k\2m\2o\2q\2s\2u\2w\2y\2{\2}\2\177\2\u0081"+
		"\2\u0083\2\u0085\2\u0087\2\u0089\2\u008b\2\u008d\2\u008f\2\u0091\2\u0093"+
		"\2\u0095\2\u0097\2\u0099\2\u009b\2\u009d\2\u009f\2\u00a1\2\5\2\3\4\13"+
		"\5\2\13\13\17\17\"\"\4\2\f\f\16\16\3\2\f\f\7\2\62;aa\u00b9\u00b9\u0302"+
		"\u0371\u2041\u2042\17\2C\\c|\u00c2\u00d8\u00da\u00f8\u00fa\u0301\u0372"+
		"\u037f\u0381\u2001\u200e\u200f\u2072\u2191\u2c02\u2ff1\u3003\ud801\uf902"+
		"\ufdd1\ufdf2\uffff\4\2))^^\4\2$$^^\3\2\62;\5\2\62;CHch\u021d\2\5\3\2\2"+
		"\2\2\7\3\2\2\2\2\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2\2\2\2\17\3\2\2\2\2\21"+
		"\3\2\2\2\2\23\3\2\2\2\2\25\3\2\2\2\2\27\3\2\2\2\2\31\3\2\2\2\2\33\3\2"+
		"\2\2\2\35\3\2\2\2\2\37\3\2\2\2\2!\3\2\2\2\2#\3\2\2\2\2%\3\2\2\2\2\'\3"+
		"\2\2\2\2)\3\2\2\2\2+\3\2\2\2\2-\3\2\2\2\2/\3\2\2\2\2\61\3\2\2\2\2\63\3"+
		"\2\2\2\3\65\3\2\2\2\3\67\3\2\2\2\39\3\2\2\2\3;\3\2\2\2\3=\3\2\2\2\3?\3"+
		"\2\2\2\3A\3\2\2\2\3C\3\2\2\2\3E\3\2\2\2\3G\3\2\2\2\3I\3\2\2\2\3K\3\2\2"+
		"\2\3M\3\2\2\2\3O\3\2\2\2\4Q\3\2\2\2\4S\3\2\2\2\4U\3\2\2\2\4W\3\2\2\2\4"+
		"Y\3\2\2\2\4[\3\2\2\2\4]\3\2\2\2\4_\3\2\2\2\4a\3\2\2\2\4c\3\2\2\2\4e\3"+
		"\2\2\2\4g\3\2\2\2\5\u00a3\3\2\2\2\7\u00a7\3\2\2\2\t\u00ab\3\2\2\2\13\u00af"+
		"\3\2\2\2\r\u00b9\3\2\2\2\17\u00bd\3\2\2\2\21\u00c5\3\2\2\2\23\u00ce\3"+
		"\2\2\2\25\u00d0\3\2\2\2\27\u00d2\3\2\2\2\31\u00d4\3\2\2\2\33\u00d6\3\2"+
		"\2\2\35\u00d8\3\2\2\2\37\u00da\3\2\2\2!\u00dc\3\2\2\2#\u00de\3\2\2\2%"+
		"\u00e0\3\2\2\2\'\u00e2\3\2\2\2)\u00e4\3\2\2\2+\u00e6\3\2\2\2-\u00ef\3"+
		"\2\2\2/\u00f2\3\2\2\2\61\u00f9\3\2\2\2\63\u00ff\3\2\2\2\65\u0103\3\2\2"+
		"\2\67\u0105\3\2\2\29\u0109\3\2\2\2;\u0112\3\2\2\2=\u0114\3\2\2\2?\u0116"+
		"\3\2\2\2A\u0118\3\2\2\2C\u011a\3\2\2\2E\u011c\3\2\2\2G\u011e\3\2\2\2I"+
		"\u0123\3\2\2\2K\u0128\3\2\2\2M\u012e\3\2\2\2O\u0136\3\2\2\2Q\u013d\3\2"+
		"\2\2S\u0142\3\2\2\2U\u0145\3\2\2\2W\u0149\3\2\2\2Y\u014d\3\2\2\2[\u0155"+
		"\3\2\2\2]\u0165\3\2\2\2_\u0170\3\2\2\2a\u0174\3\2\2\2c\u0178\3\2\2\2e"+
		"\u0187\3\2\2\2g\u018d\3\2\2\2i\u0191\3\2\2\2k\u0193\3\2\2\2m\u0195\3\2"+
		"\2\2o\u0197\3\2\2\2q\u0199\3\2\2\2s\u019b\3\2\2\2u\u019d\3\2\2\2w\u019f"+
		"\3\2\2\2y\u01a2\3\2\2\2{\u01a4\3\2\2\2}\u01a6\3\2\2\2\177\u01a8\3\2\2"+
		"\2\u0081\u01aa\3\2\2\2\u0083\u01ac\3\2\2\2\u0085\u01ae\3\2\2\2\u0087\u01b0"+
		"\3\2\2\2\u0089\u01b2\3\2\2\2\u008b\u01b4\3\2\2\2\u008d\u01b6\3\2\2\2\u008f"+
		"\u01c3\3\2\2\2\u0091\u01cf\3\2\2\2\u0093\u01eb\3\2\2\2\u0095\u01ed\3\2"+
		"\2\2\u0097\u01ef\3\2\2\2\u0099\u01f9\3\2\2\2\u009b\u0204\3\2\2\2\u009d"+
		"\u0208\3\2\2\2\u009f\u020a\3\2\2\2\u00a1\u020f\3\2\2\2\u00a3\u00a4\5\u008d"+
		"F\2\u00a4\u00a5\3\2\2\2\u00a5\u00a6\b\2\2\2\u00a6\6\3\2\2\2\u00a7\u00a8"+
		"\5\u008fG\2\u00a8\u00a9\3\2\2\2\u00a9\u00aa\b\3\2\2\u00aa\b\3\2\2\2\u00ab"+
		"\u00ac\5\u0091H\2\u00ac\u00ad\3\2\2\2\u00ad\u00ae\b\4\2\2\u00ae\n\3\2"+
		"\2\2\u00af\u00b0\7q\2\2\u00b0\u00b1\7r\2\2\u00b1\u00b2\7v\2\2\u00b2\u00b3"+
		"\7k\2\2\u00b3\u00b4\7q\2\2\u00b4\u00b5\7p\2\2\u00b5\u00b6\7u\2\2\u00b6"+
		"\u00b7\3\2\2\2\u00b7\u00b8\b\5\3\2\u00b8\f\3\2\2\2\u00b9\u00ba\5\177?"+
		"\2\u00ba\u00bb\3\2\2\2\u00bb\u00bc\b\6\4\2\u00bc\16\3\2\2\2\u00bd\u00be"+
		"\7i\2\2\u00be\u00bf\7t\2\2\u00bf\u00c0\7c\2\2\u00c0\u00c1\7o\2\2\u00c1"+
		"\u00c2\7o\2\2\u00c2\u00c3\7c\2\2\u00c3\u00c4\7t\2\2\u00c4\20\3\2\2\2\u00c5"+
		"\u00c6\7z\2\2\u00c6\u00c7\7x\2\2\u00c7\u00c8\7k\2\2\u00c8\u00c9\7u\2\2"+
		"\u00c9\u00ca\7k\2\2\u00ca\u00cb\7v\2\2\u00cb\u00cc\7q\2\2\u00cc\u00cd"+
		"\7t\2\2\u00cd\22\3\2\2\2\u00ce\u00cf\5i\64\2\u00cf\24\3\2\2\2\u00d0\u00d1"+
		"\5k\65\2\u00d1\26\3\2\2\2\u00d2\u00d3\5m\66\2\u00d3\30\3\2\2\2\u00d4\u00d5"+
		"\5o\67\2\u00d5\32\3\2\2\2\u00d6\u00d7\5q8\2\u00d7\34\3\2\2\2\u00d8\u00d9"+
		"\5s9\2\u00d9\36\3\2\2\2\u00da\u00db\5u:\2\u00db \3\2\2\2\u00dc\u00dd\5"+
		"w;\2\u00dd\"\3\2\2\2\u00de\u00df\5y<\2\u00df$\3\2\2\2\u00e0\u00e1\5}>"+
		"\2\u00e1&\3\2\2\2\u00e2\u00e3\5\u0083A\2\u00e3(\3\2\2\2\u00e4\u00e5\5"+
		"\u0085B\2\u00e5*\3\2\2\2\u00e6\u00ea\5\u0095J\2\u00e7\u00e9\5\u0093I\2"+
		"\u00e8\u00e7\3\2\2\2\u00e9\u00ec\3\2\2\2\u00ea\u00e8\3\2\2\2\u00ea\u00eb"+
		"\3\2\2\2\u00eb,\3\2\2\2\u00ec\u00ea\3\2\2\2\u00ed\u00f0\5\u0097K\2\u00ee"+
		"\u00f0\5\u0099L\2\u00ef\u00ed\3\2\2\2\u00ef\u00ee\3\2\2\2\u00f0.\3\2\2"+
		"\2\u00f1\u00f3\5\u0089D\2\u00f2\u00f1\3\2\2\2\u00f3\u00f4\3\2\2\2\u00f4"+
		"\u00f2\3\2\2\2\u00f4\u00f5\3\2\2\2\u00f5\u00f6\3\2\2\2\u00f6\u00f7\b\27"+
		"\2\2\u00f7\60\3\2\2\2\u00f8\u00fa\5\u008bE\2\u00f9\u00f8\3\2\2\2\u00fa"+
		"\u00fb\3\2\2\2\u00fb\u00f9\3\2\2\2\u00fb\u00fc\3\2\2\2\u00fc\u00fd\3\2"+
		"\2\2\u00fd\u00fe\b\30\2\2\u00fe\62\3\2\2\2\u00ff\u0100\13\2\2\2\u0100"+
		"\u0101\3\2\2\2\u0101\u0102\b\31\5\2\u0102\64\3\2\2\2\u0103\u0104\5\177"+
		"?\2\u0104\66\3\2\2\2\u0105\u0106\5\u0081@\2\u0106\u0107\3\2\2\2\u0107"+
		"\u0108\b\33\6\2\u01088\3\2\2\2\u0109\u010d\5\u0095J\2\u010a\u010c\5\u0093"+
		"I\2\u010b\u010a\3\2\2\2\u010c\u010f\3\2\2\2\u010d\u010b\3\2\2\2\u010d"+
		"\u010e\3\2\2\2\u010e:\3\2\2\2\u010f\u010d\3\2\2\2\u0110\u0113\5\u0099"+
		"L\2\u0111\u0113\5\u0097K\2\u0112\u0110\3\2\2\2\u0112\u0111\3\2\2\2\u0113"+
		"<\3\2\2\2\u0114\u0115\5}>\2\u0115>\3\2\2\2\u0116\u0117\5o\67\2\u0117@"+
		"\3\2\2\2\u0118\u0119\5m\66\2\u0119B\3\2\2\2\u011a\u011b\5s9\2\u011bD\3"+
		"\2\2\2\u011c\u011d\5\u009bM\2\u011dF\3\2\2\2\u011e\u011f\5\u008dF\2\u011f"+
		"\u0120\3\2\2\2\u0120\u0121\b#\7\2\u0121\u0122\b#\2\2\u0122H\3\2\2\2\u0123"+
		"\u0124\5\u008fG\2\u0124\u0125\3\2\2\2\u0125\u0126\b$\7\2\u0126\u0127\b"+
		"$\2\2\u0127J\3\2\2\2\u0128\u0129\5\u0091H\2\u0129\u012a\3\2\2\2\u012a"+
		"\u012b\b%\b\2\u012b\u012c\b%\2\2\u012cL\3\2\2\2\u012d\u012f\5\u0089D\2"+
		"\u012e\u012d\3\2\2\2\u012f\u0130\3\2\2\2\u0130\u012e\3\2\2\2\u0130\u0131"+
		"\3\2\2\2\u0131\u0132\3\2\2\2\u0132\u0133\b&\t\2\u0133\u0134\b&\2\2\u0134"+
		"N\3\2\2\2\u0135\u0137\5\u008bE\2\u0136\u0135\3\2\2\2\u0137\u0138\3\2\2"+
		"\2\u0138\u0136\3\2\2\2\u0138\u0139\3\2\2\2\u0139\u013a\3\2\2\2\u013a\u013b"+
		"\b\'\n\2\u013b\u013c\b\'\2\2\u013cP\3\2\2\2\u013d\u013e\5\177?\2\u013e"+
		"\u013f\3\2\2\2\u013f\u0140\b(\13\2\u0140\u0141\b(\4\2\u0141R\3\2\2\2\u0142"+
		"\u0143\5\u0081@\2\u0143\u0144\b)\f\2\u0144T\3\2\2\2\u0145\u0146\5\u008d"+
		"F\2\u0146\u0147\3\2\2\2\u0147\u0148\b*\13\2\u0148V\3\2\2\2\u0149\u014a"+
		"\5\u008fG\2\u014a\u014b\3\2\2\2\u014b\u014c\b+\13\2\u014cX\3\2\2\2\u014d"+
		"\u014e\5\u0091H\2\u014e\u014f\3\2\2\2\u014f\u0150\b,\13\2\u0150Z\3\2\2"+
		"\2\u0151\u0154\5\u0089D\2\u0152\u0154\5\u008bE\2\u0153\u0151\3\2\2\2\u0153"+
		"\u0152\3\2\2\2\u0154\u0157\3\2\2\2\u0155\u0153\3\2\2\2\u0155\u0156\3\2"+
		"\2\2\u0156\u0158\3\2\2\2\u0157\u0155\3\2\2\2\u0158\u0159\7q\2\2\u0159"+
		"\u015a\7p\2\2\u015a\u015b\7G\2\2\u015b\u015c\7p\2\2\u015c\u015d\7v\2\2"+
		"\u015d\u015e\7t\2\2\u015e\u015f\7{\2\2\u015f\u0160\7<\2\2\u0160\\\3\2"+
		"\2\2\u0161\u0164\5\u0089D\2\u0162\u0164\5\u008bE\2\u0163\u0161\3\2\2\2"+
		"\u0163\u0162\3\2\2\2\u0164\u0167\3\2\2\2\u0165\u0163\3\2\2\2\u0165\u0166"+
		"\3\2\2\2\u0166\u0168\3\2\2\2\u0167\u0165\3\2\2\2\u0168\u0169\7q\2\2\u0169"+
		"\u016a\7p\2\2\u016a\u016b\7G\2\2\u016b\u016c\7z\2\2\u016c\u016d\7k\2\2"+
		"\u016d\u016e\7v\2\2\u016e\u016f\7<\2\2\u016f^\3\2\2\2\u0170\u0171\5\u0099"+
		"L\2\u0171\u0172\3\2\2\2\u0172\u0173\b/\13\2\u0173`\3\2\2\2\u0174\u0175"+
		"\5\u0097K\2\u0175\u0176\3\2\2\2\u0176\u0177\b\60\13\2\u0177b\3\2\2\2\u0178"+
		"\u017a\5\u0087C\2\u0179\u017b\5\u0093I\2\u017a\u0179\3\2\2\2\u017b\u017c"+
		"\3\2\2\2\u017c\u017a\3\2\2\2\u017c\u017d\3\2\2\2\u017d\u0184\3\2\2\2\u017e"+
		"\u0180\5}>\2\u017f\u0181\5\u0093I\2\u0180\u017f\3\2\2\2\u0181\u0182\3"+
		"\2\2\2\u0182\u0180\3\2\2\2\u0182\u0183\3\2\2\2\u0183\u0185\3\2\2\2\u0184"+
		"\u017e\3\2\2\2\u0184\u0185\3\2\2\2\u0185d\3\2\2\2\u0186\u0188\5\u0093"+
		"I\2\u0187\u0186\3\2\2\2\u0188\u0189\3\2\2\2\u0189\u0187\3\2\2\2\u0189"+
		"\u018a\3\2\2\2\u018a\u018b\3\2\2\2\u018b\u018c\b\62\13\2\u018cf\3\2\2"+
		"\2\u018d\u018e\13\2\2\2\u018e\u018f\3\2\2\2\u018f\u0190\b\63\13\2\u0190"+
		"h\3\2\2\2\u0191\u0192\7<\2\2\u0192j\3\2\2\2\u0193\u0194\7.\2\2\u0194l"+
		"\3\2\2\2\u0195\u0196\7=\2\2\u0196n\3\2\2\2\u0197\u0198\7?\2\2\u0198p\3"+
		"\2\2\2\u0199\u019a\7A\2\2\u019ar\3\2\2\2\u019b\u019c\7,\2\2\u019ct\3\2"+
		"\2\2\u019d\u019e\7B\2\2\u019ev\3\2\2\2\u019f\u01a0\7\61\2\2\u01a0\u01a1"+
		"\7\61\2\2\u01a1x\3\2\2\2\u01a2\u01a3\7\61\2\2\u01a3z\3\2\2\2\u01a4\u01a5"+
		"\7#\2\2\u01a5|\3\2\2\2\u01a6\u01a7\7\60\2\2\u01a7~\3\2\2\2\u01a8\u01a9"+
		"\7}\2\2\u01a9\u0080\3\2\2\2\u01aa\u01ab\7\177\2\2\u01ab\u0082\3\2\2\2"+
		"\u01ac\u01ad\7~\2\2\u01ad\u0084\3\2\2\2\u01ae\u01af\7#\2\2\u01af\u0086"+
		"\3\2\2\2\u01b0\u01b1\7&\2\2\u01b1\u0088\3\2\2\2\u01b2\u01b3\t\2\2\2\u01b3"+
		"\u008a\3\2\2\2\u01b4\u01b5\t\3\2\2\u01b5\u008c\3\2\2\2\u01b6\u01b7\7\61"+
		"\2\2\u01b7\u01b8\7,\2\2\u01b8\u01b9\7,\2\2\u01b9\u01bd\3\2\2\2\u01ba\u01bc"+
		"\13\2\2\2\u01bb\u01ba\3\2\2\2\u01bc\u01bf\3\2\2\2\u01bd\u01be\3\2\2\2"+
		"\u01bd\u01bb\3\2\2\2\u01be\u01c0\3\2\2\2\u01bf\u01bd\3\2\2\2\u01c0\u01c1"+
		"\7,\2\2\u01c1\u01c2\7\61\2\2\u01c2\u008e\3\2\2\2\u01c3\u01c4\7\61\2\2"+
		"\u01c4\u01c5\7%\2\2\u01c5\u01c9\3\2\2\2\u01c6\u01c8\13\2\2\2\u01c7\u01c6"+
		"\3\2\2\2\u01c8\u01cb\3\2\2\2\u01c9\u01ca\3\2\2\2\u01c9\u01c7\3\2\2\2\u01ca"+
		"\u01cc\3\2\2\2\u01cb\u01c9\3\2\2\2\u01cc\u01cd\7%\2\2\u01cd\u01ce\7\61"+
		"\2\2\u01ce\u0090\3\2\2\2\u01cf\u01d3\7%\2\2\u01d0\u01d2\n\4\2\2\u01d1"+
		"\u01d0\3\2\2\2\u01d2\u01d5\3\2\2\2\u01d3\u01d1\3\2\2\2\u01d3\u01d4\3\2"+
		"\2\2\u01d4\u01e6\3\2\2\2\u01d5\u01d3\3\2\2\2\u01d6\u01da\7\f\2\2\u01d7"+
		"\u01d9\5\u0089D\2\u01d8\u01d7\3\2\2\2\u01d9\u01dc\3\2\2\2\u01da\u01d8"+
		"\3\2\2\2\u01da\u01db\3\2\2\2\u01db\u01dd\3\2\2\2\u01dc\u01da\3\2\2\2\u01dd"+
		"\u01e1\7%\2\2\u01de\u01e0\n\4\2\2\u01df\u01de\3\2\2\2\u01e0\u01e3\3\2"+
		"\2\2\u01e1\u01df\3\2\2\2\u01e1\u01e2\3\2\2\2\u01e2\u01e5\3\2\2\2\u01e3"+
		"\u01e1\3\2\2\2\u01e4\u01d6\3\2\2\2\u01e5\u01e8\3\2\2\2\u01e6\u01e4\3\2"+
		"\2\2\u01e6\u01e7\3\2\2\2\u01e7\u0092\3\2\2\2\u01e8\u01e6\3\2\2\2\u01e9"+
		"\u01ec\5\u0095J\2\u01ea\u01ec\t\5\2\2\u01eb\u01e9\3\2\2\2\u01eb\u01ea"+
		"\3\2\2\2\u01ec\u0094\3\2\2\2\u01ed\u01ee\t\6\2\2\u01ee\u0096\3\2\2\2\u01ef"+
		"\u01f4\7)\2\2\u01f0\u01f3\5\u009fO\2\u01f1\u01f3\n\7\2\2\u01f2\u01f0\3"+
		"\2\2\2\u01f2\u01f1\3\2\2\2\u01f3\u01f6\3\2\2\2\u01f4\u01f2\3\2\2\2\u01f4"+
		"\u01f5\3\2\2\2\u01f5\u01f7\3\2\2\2\u01f6\u01f4\3\2\2\2\u01f7\u01f8\7)"+
		"\2\2\u01f8\u0098\3\2\2\2\u01f9\u01fe\7$\2\2\u01fa\u01fd\5\u009fO\2\u01fb"+
		"\u01fd\n\b\2\2\u01fc\u01fa\3\2\2\2\u01fc\u01fb\3\2\2\2\u01fd\u0200\3\2"+
		"\2\2\u01fe\u01fc\3\2\2\2\u01fe\u01ff\3\2\2\2\u01ff\u0201\3\2\2\2\u0200"+
		"\u01fe\3\2\2\2\u0201\u0202\7$\2\2\u0202\u009a\3\2\2\2\u0203\u0205\t\t"+
		"\2\2\u0204\u0203\3\2\2\2\u0205\u0206\3\2\2\2\u0206\u0204\3\2\2\2\u0206"+
		"\u0207\3\2\2\2\u0207\u009c\3\2\2\2\u0208\u0209\t\n\2\2\u0209\u009e\3\2"+
		"\2\2\u020a\u020d\7^\2\2\u020b\u020e\5\u00a1P\2\u020c\u020e\13\2\2\2\u020d"+
		"\u020b\3\2\2\2\u020d\u020c\3\2\2\2\u020e\u00a0\3\2\2\2\u020f\u021a\7w"+
		"\2\2\u0210\u0218\5\u009dN\2\u0211\u0216\5\u009dN\2\u0212\u0214\5\u009d"+
		"N\2\u0213\u0215\5\u009dN\2\u0214\u0213\3\2\2\2\u0214\u0215\3\2\2\2\u0215"+
		"\u0217\3\2\2\2\u0216\u0212\3\2\2\2\u0216\u0217\3\2\2\2\u0217\u0219\3\2"+
		"\2\2\u0218\u0211\3\2\2\2\u0218\u0219\3\2\2\2\u0219\u021b\3\2\2\2\u021a"+
		"\u0210\3\2\2\2\u021a\u021b\3\2\2\2\u021b\u00a2\3\2\2\2&\2\3\4\u00ea\u00ef"+
		"\u00f4\u00fb\u010d\u0112\u0130\u0138\u0153\u0155\u0163\u0165\u017c\u0182"+
		"\u0184\u0189\u01bd\u01c9\u01d3\u01da\u01e1\u01e6\u01eb\u01f2\u01f4\u01fc"+
		"\u01fe\u0206\u020d\u0214\u0216\u0218\u021a\r\2\3\2\7\3\2\7\4\2\b\2\2\6"+
		"\2\2\t\6\2\t\7\2\t\32\2\t\33\2\t\3\2\3)\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}