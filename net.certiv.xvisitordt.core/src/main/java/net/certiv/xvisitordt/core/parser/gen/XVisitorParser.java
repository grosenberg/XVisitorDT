// Generated from D:/DevFiles/EclipseOxy/net.certiv.xvisitordt.core/src/main/java/net/certiv/xvisitordt/core/parser/XVisitorParser.g4 by ANTLR 4.5.3

	package net.certiv.xvisitordt.core.parser.gen;

	import java.util.List;

import org.antlr.v4.runtime.NoViableAltException;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.RecognitionException;
import org.antlr.v4.runtime.RuntimeMetaData;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.Vocabulary;
import org.antlr.v4.runtime.VocabularyImpl;
import org.antlr.v4.runtime.atn.ATN;
import org.antlr.v4.runtime.atn.ATNDeserializer;
import org.antlr.v4.runtime.atn.ParserATNSimulator;
import org.antlr.v4.runtime.atn.PredictionContextCache;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.tree.ParseTreeListener;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;
import org.antlr.v4.runtime.tree.TerminalNode;

import net.certiv.xvisitordt.core.parser.ParserAdaptor;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class XVisitorParser extends ParserAdaptor {
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
	public static final int
		RULE_grammarSpec = 0, RULE_optionsSpec = 1, RULE_option = 2, RULE_optionValue = 3, 
		RULE_action = 4, RULE_xmain = 5, RULE_xpath = 6, RULE_xpathSpec = 7, RULE_actionBlock = 8, 
		RULE_text = 9, RULE_reference = 10, RULE_separator = 11, RULE_word = 12;
	public static final String[] ruleNames = {
		"grammarSpec", "optionsSpec", "option", "optionValue", "action", "xmain", 
		"xpath", "xpathSpec", "actionBlock", "text", "reference", "separator", 
		"word"
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

	@Override
	public String getGrammarFileName() { return "XVisitorParser.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public XVisitorParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}
	public static class GrammarSpecContext extends ParserRuleContext {
		public TerminalNode XVISITOR() { return getToken(XVisitorParser.XVISITOR, 0); }
		public TerminalNode GRAMMAR() { return getToken(XVisitorParser.GRAMMAR, 0); }
		public TerminalNode ID() { return getToken(XVisitorParser.ID, 0); }
		public TerminalNode SEMI() { return getToken(XVisitorParser.SEMI, 0); }
		public XmainContext xmain() {
			return getRuleContext(XmainContext.class,0);
		}
		public TerminalNode EOF() { return getToken(XVisitorParser.EOF, 0); }
		public OptionsSpecContext optionsSpec() {
			return getRuleContext(OptionsSpecContext.class,0);
		}
		public List<ActionContext> action() {
			return getRuleContexts(ActionContext.class);
		}
		public ActionContext action(int i) {
			return getRuleContext(ActionContext.class,i);
		}
		public List<XpathContext> xpath() {
			return getRuleContexts(XpathContext.class);
		}
		public XpathContext xpath(int i) {
			return getRuleContext(XpathContext.class,i);
		}
		public GrammarSpecContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_grammarSpec; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof XVisitorParserListener ) ((XVisitorParserListener)listener).enterGrammarSpec(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof XVisitorParserListener ) ((XVisitorParserListener)listener).exitGrammarSpec(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof XVisitorParserVisitor ) return ((XVisitorParserVisitor<? extends T>)visitor).visitGrammarSpec(this);
			else return visitor.visitChildren(this);
		}
	}

	public final GrammarSpecContext grammarSpec() throws RecognitionException {
		GrammarSpecContext _localctx = new GrammarSpecContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_grammarSpec);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(26);
			match(XVISITOR);
			setState(27);
			match(GRAMMAR);
			setState(28);
			match(ID);
			setState(29);
			match(SEMI);
			setState(31);
			_la = _input.LA(1);
			if (_la==OPTIONS) {
				{
				setState(30);
				optionsSpec();
				}
			}

			setState(36);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==AT) {
				{
				{
				setState(33);
				action();
				}
				}
				setState(38);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(39);
			xmain();
			setState(41); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(40);
				xpath();
				}
				}
				setState(43); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==ID );
			setState(45);
			match(EOF);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class OptionsSpecContext extends ParserRuleContext {
		public TerminalNode OPTIONS() { return getToken(XVisitorParser.OPTIONS, 0); }
		public TerminalNode OPT_LBRACE() { return getToken(XVisitorParser.OPT_LBRACE, 0); }
		public TerminalNode OPT_RBRACE() { return getToken(XVisitorParser.OPT_RBRACE, 0); }
		public List<OptionContext> option() {
			return getRuleContexts(OptionContext.class);
		}
		public OptionContext option(int i) {
			return getRuleContext(OptionContext.class,i);
		}
		public OptionsSpecContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_optionsSpec; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof XVisitorParserListener ) ((XVisitorParserListener)listener).enterOptionsSpec(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof XVisitorParserListener ) ((XVisitorParserListener)listener).exitOptionsSpec(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof XVisitorParserVisitor ) return ((XVisitorParserVisitor<? extends T>)visitor).visitOptionsSpec(this);
			else return visitor.visitChildren(this);
		}
	}

	public final OptionsSpecContext optionsSpec() throws RecognitionException {
		OptionsSpecContext _localctx = new OptionsSpecContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_optionsSpec);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(47);
			match(OPTIONS);
			setState(48);
			match(OPT_LBRACE);
			setState(50); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(49);
				option();
				}
				}
				setState(52); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==OPT_ID );
			setState(54);
			match(OPT_RBRACE);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class OptionContext extends ParserRuleContext {
		public List<TerminalNode> OPT_ID() { return getTokens(XVisitorParser.OPT_ID); }
		public TerminalNode OPT_ID(int i) {
			return getToken(XVisitorParser.OPT_ID, i);
		}
		public TerminalNode OPT_SEMI() { return getToken(XVisitorParser.OPT_SEMI, 0); }
		public TerminalNode OPT_ASSIGN() { return getToken(XVisitorParser.OPT_ASSIGN, 0); }
		public OptionValueContext optionValue() {
			return getRuleContext(OptionValueContext.class,0);
		}
		public List<TerminalNode> OPT_DOT() { return getTokens(XVisitorParser.OPT_DOT); }
		public TerminalNode OPT_DOT(int i) {
			return getToken(XVisitorParser.OPT_DOT, i);
		}
		public OptionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_option; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof XVisitorParserListener ) ((XVisitorParserListener)listener).enterOption(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof XVisitorParserListener ) ((XVisitorParserListener)listener).exitOption(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof XVisitorParserVisitor ) return ((XVisitorParserVisitor<? extends T>)visitor).visitOption(this);
			else return visitor.visitChildren(this);
		}
	}

	public final OptionContext option() throws RecognitionException {
		OptionContext _localctx = new OptionContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_option);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(56);
			match(OPT_ID);
			setState(65);
			switch (_input.LA(1)) {
			case OPT_ASSIGN:
				{
				setState(57);
				match(OPT_ASSIGN);
				setState(58);
				optionValue();
				}
				break;
			case OPT_DOT:
				{
				setState(61); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(59);
					match(OPT_DOT);
					setState(60);
					match(OPT_ID);
					}
					}
					setState(63); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( _la==OPT_DOT );
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			setState(67);
			match(OPT_SEMI);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class OptionValueContext extends ParserRuleContext {
		public TerminalNode OPT_ID() { return getToken(XVisitorParser.OPT_ID, 0); }
		public TerminalNode OPT_LITERAL() { return getToken(XVisitorParser.OPT_LITERAL, 0); }
		public TerminalNode OPT_INT() { return getToken(XVisitorParser.OPT_INT, 0); }
		public TerminalNode OPT_STAR() { return getToken(XVisitorParser.OPT_STAR, 0); }
		public OptionValueContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_optionValue; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof XVisitorParserListener ) ((XVisitorParserListener)listener).enterOptionValue(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof XVisitorParserListener ) ((XVisitorParserListener)listener).exitOptionValue(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof XVisitorParserVisitor ) return ((XVisitorParserVisitor<? extends T>)visitor).visitOptionValue(this);
			else return visitor.visitChildren(this);
		}
	}

	public final OptionValueContext optionValue() throws RecognitionException {
		OptionValueContext _localctx = new OptionValueContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_optionValue);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(69);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << OPT_ID) | (1L << OPT_LITERAL) | (1L << OPT_STAR) | (1L << OPT_INT))) != 0)) ) {
			_errHandler.recoverInline(this);
			} else {
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ActionContext extends ParserRuleContext {
		public TerminalNode AT() { return getToken(XVisitorParser.AT, 0); }
		public TerminalNode ID() { return getToken(XVisitorParser.ID, 0); }
		public ActionBlockContext actionBlock() {
			return getRuleContext(ActionBlockContext.class,0);
		}
		public ActionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_action; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof XVisitorParserListener ) ((XVisitorParserListener)listener).enterAction(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof XVisitorParserListener ) ((XVisitorParserListener)listener).exitAction(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof XVisitorParserVisitor ) return ((XVisitorParserVisitor<? extends T>)visitor).visitAction(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ActionContext action() throws RecognitionException {
		ActionContext _localctx = new ActionContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_action);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(71);
			match(AT);
			setState(72);
			match(ID);
			setState(73);
			actionBlock();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class XmainContext extends ParserRuleContext {
		public Token name;
		public TerminalNode COLON() { return getToken(XVisitorParser.COLON, 0); }
		public List<TerminalNode> ID() { return getTokens(XVisitorParser.ID); }
		public TerminalNode ID(int i) {
			return getToken(XVisitorParser.ID, i);
		}
		public TerminalNode SEMI() { return getToken(XVisitorParser.SEMI, 0); }
		public List<TerminalNode> OR() { return getTokens(XVisitorParser.OR); }
		public TerminalNode OR(int i) {
			return getToken(XVisitorParser.OR, i);
		}
		public ActionBlockContext actionBlock() {
			return getRuleContext(ActionBlockContext.class,0);
		}
		public XmainContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_xmain; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof XVisitorParserListener ) ((XVisitorParserListener)listener).enterXmain(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof XVisitorParserListener ) ((XVisitorParserListener)listener).exitXmain(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof XVisitorParserVisitor ) return ((XVisitorParserVisitor<? extends T>)visitor).visitXmain(this);
			else return visitor.visitChildren(this);
		}
	}

	public final XmainContext xmain() throws RecognitionException {
		XmainContext _localctx = new XmainContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_xmain);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(75);
			((XmainContext)_localctx).name = match(ID);
			setState(76);
			match(COLON);
			setState(77);
			match(ID);
			setState(82);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==OR) {
				{
				{
				setState(78);
				match(OR);
				setState(79);
				match(ID);
				}
				}
				setState(84);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(86);
			_la = _input.LA(1);
			if (_la==LBRACE) {
				{
				setState(85);
				actionBlock();
				}
			}

			setState(88);
			match(SEMI);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class XpathContext extends ParserRuleContext {
		public Token name;
		public TerminalNode COLON() { return getToken(XVisitorParser.COLON, 0); }
		public XpathSpecContext xpathSpec() {
			return getRuleContext(XpathSpecContext.class,0);
		}
		public TerminalNode SEMI() { return getToken(XVisitorParser.SEMI, 0); }
		public TerminalNode ID() { return getToken(XVisitorParser.ID, 0); }
		public List<ActionBlockContext> actionBlock() {
			return getRuleContexts(ActionBlockContext.class);
		}
		public ActionBlockContext actionBlock(int i) {
			return getRuleContext(ActionBlockContext.class,i);
		}
		public XpathContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_xpath; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof XVisitorParserListener ) ((XVisitorParserListener)listener).enterXpath(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof XVisitorParserListener ) ((XVisitorParserListener)listener).exitXpath(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof XVisitorParserVisitor ) return ((XVisitorParserVisitor<? extends T>)visitor).visitXpath(this);
			else return visitor.visitChildren(this);
		}
	}

	public final XpathContext xpath() throws RecognitionException {
		XpathContext _localctx = new XpathContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_xpath);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(90);
			((XpathContext)_localctx).name = match(ID);
			setState(91);
			match(COLON);
			setState(92);
			xpathSpec();
			setState(96);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==LBRACE) {
				{
				{
				setState(93);
				actionBlock();
				}
				}
				setState(98);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(99);
			match(SEMI);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class XpathSpecContext extends ParserRuleContext {
		public List<SeparatorContext> separator() {
			return getRuleContexts(SeparatorContext.class);
		}
		public SeparatorContext separator(int i) {
			return getRuleContext(SeparatorContext.class,i);
		}
		public List<WordContext> word() {
			return getRuleContexts(WordContext.class);
		}
		public WordContext word(int i) {
			return getRuleContext(WordContext.class,i);
		}
		public XpathSpecContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_xpathSpec; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof XVisitorParserListener ) ((XVisitorParserListener)listener).enterXpathSpec(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof XVisitorParserListener ) ((XVisitorParserListener)listener).exitXpathSpec(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof XVisitorParserVisitor ) return ((XVisitorParserVisitor<? extends T>)visitor).visitXpathSpec(this);
			else return visitor.visitChildren(this);
		}
	}

	public final XpathSpecContext xpathSpec() throws RecognitionException {
		XpathSpecContext _localctx = new XpathSpecContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_xpathSpec);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(104); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(101);
				separator();
				setState(102);
				word();
				}
				}
				setState(106); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==ANY || _la==SEP );
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ActionBlockContext extends ParserRuleContext {
		public TerminalNode LBRACE() { return getToken(XVisitorParser.LBRACE, 0); }
		public TerminalNode RBRACE() { return getToken(XVisitorParser.RBRACE, 0); }
		public List<TextContext> text() {
			return getRuleContexts(TextContext.class);
		}
		public TextContext text(int i) {
			return getRuleContext(TextContext.class,i);
		}
		public List<ReferenceContext> reference() {
			return getRuleContexts(ReferenceContext.class);
		}
		public ReferenceContext reference(int i) {
			return getRuleContext(ReferenceContext.class,i);
		}
		public TerminalNode ONENTRY() { return getToken(XVisitorParser.ONENTRY, 0); }
		public TerminalNode ONEXIT() { return getToken(XVisitorParser.ONEXIT, 0); }
		public ActionBlockContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_actionBlock; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof XVisitorParserListener ) ((XVisitorParserListener)listener).enterActionBlock(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof XVisitorParserListener ) ((XVisitorParserListener)listener).exitActionBlock(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof XVisitorParserVisitor ) return ((XVisitorParserVisitor<? extends T>)visitor).visitActionBlock(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ActionBlockContext actionBlock() throws RecognitionException {
		ActionBlockContext _localctx = new ActionBlockContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_actionBlock);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(108);
			match(LBRACE);
			setState(110);
			_la = _input.LA(1);
			if (_la==ONENTRY || _la==ONEXIT) {
				{
				setState(109);
				_la = _input.LA(1);
				if ( !(_la==ONENTRY || _la==ONEXIT) ) {
				_errHandler.recoverInline(this);
				} else {
					consume();
				}
				}
			}

			setState(114); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				setState(114);
				switch (_input.LA(1)) {
				case TEXT:
					{
					setState(112);
					text();
					}
					break;
				case REFERENCE:
					{
					setState(113);
					reference();
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				}
				setState(116); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==TEXT || _la==REFERENCE );
			setState(118);
			match(RBRACE);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class TextContext extends ParserRuleContext {
		public List<TerminalNode> TEXT() { return getTokens(XVisitorParser.TEXT); }
		public TerminalNode TEXT(int i) {
			return getToken(XVisitorParser.TEXT, i);
		}
		public TextContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_text; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof XVisitorParserListener ) ((XVisitorParserListener)listener).enterText(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof XVisitorParserListener ) ((XVisitorParserListener)listener).exitText(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof XVisitorParserVisitor ) return ((XVisitorParserVisitor<? extends T>)visitor).visitText(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TextContext text() throws RecognitionException {
		TextContext _localctx = new TextContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_text);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(121); 
			_errHandler.sync(this);
			_alt = 1;
			do {
				switch (_alt) {
				case 1:
					{
					{
					setState(120);
					match(TEXT);
					}
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				setState(123); 
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,13,_ctx);
			} while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER );
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ReferenceContext extends ParserRuleContext {
		public TerminalNode REFERENCE() { return getToken(XVisitorParser.REFERENCE, 0); }
		public ReferenceContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_reference; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof XVisitorParserListener ) ((XVisitorParserListener)listener).enterReference(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof XVisitorParserListener ) ((XVisitorParserListener)listener).exitReference(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof XVisitorParserVisitor ) return ((XVisitorParserVisitor<? extends T>)visitor).visitReference(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ReferenceContext reference() throws RecognitionException {
		ReferenceContext _localctx = new ReferenceContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_reference);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(125);
			match(REFERENCE);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class SeparatorContext extends ParserRuleContext {
		public TerminalNode ANY() { return getToken(XVisitorParser.ANY, 0); }
		public TerminalNode SEP() { return getToken(XVisitorParser.SEP, 0); }
		public TerminalNode NOT() { return getToken(XVisitorParser.NOT, 0); }
		public SeparatorContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_separator; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof XVisitorParserListener ) ((XVisitorParserListener)listener).enterSeparator(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof XVisitorParserListener ) ((XVisitorParserListener)listener).exitSeparator(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof XVisitorParserVisitor ) return ((XVisitorParserVisitor<? extends T>)visitor).visitSeparator(this);
			else return visitor.visitChildren(this);
		}
	}

	public final SeparatorContext separator() throws RecognitionException {
		SeparatorContext _localctx = new SeparatorContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_separator);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(127);
			_la = _input.LA(1);
			if ( !(_la==ANY || _la==SEP) ) {
			_errHandler.recoverInline(this);
			} else {
				consume();
			}
			setState(129);
			_la = _input.LA(1);
			if (_la==NOT) {
				{
				setState(128);
				match(NOT);
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class WordContext extends ParserRuleContext {
		public TerminalNode ID() { return getToken(XVisitorParser.ID, 0); }
		public TerminalNode STAR() { return getToken(XVisitorParser.STAR, 0); }
		public TerminalNode LITERAL() { return getToken(XVisitorParser.LITERAL, 0); }
		public WordContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_word; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof XVisitorParserListener ) ((XVisitorParserListener)listener).enterWord(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof XVisitorParserListener ) ((XVisitorParserListener)listener).exitWord(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof XVisitorParserVisitor ) return ((XVisitorParserVisitor<? extends T>)visitor).visitWord(this);
			else return visitor.visitChildren(this);
		}
	}

	public final WordContext word() throws RecognitionException {
		WordContext _localctx = new WordContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_word);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(131);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << STAR) | (1L << ID) | (1L << LITERAL))) != 0)) ) {
			_errHandler.recoverInline(this);
			} else {
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static final String _serializedATN =
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\3)\u0088\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\4\f\t\f\4\r\t\r\4\16\t\16\3\2\3\2\3\2\3\2\3\2\5\2\"\n\2\3\2\7\2%\n"+
		"\2\f\2\16\2(\13\2\3\2\3\2\6\2,\n\2\r\2\16\2-\3\2\3\2\3\3\3\3\3\3\6\3\65"+
		"\n\3\r\3\16\3\66\3\3\3\3\3\4\3\4\3\4\3\4\3\4\6\4@\n\4\r\4\16\4A\5\4D\n"+
		"\4\3\4\3\4\3\5\3\5\3\6\3\6\3\6\3\6\3\7\3\7\3\7\3\7\3\7\7\7S\n\7\f\7\16"+
		"\7V\13\7\3\7\5\7Y\n\7\3\7\3\7\3\b\3\b\3\b\3\b\7\ba\n\b\f\b\16\bd\13\b"+
		"\3\b\3\b\3\t\3\t\3\t\6\tk\n\t\r\t\16\tl\3\n\3\n\5\nq\n\n\3\n\3\n\6\nu"+
		"\n\n\r\n\16\nv\3\n\3\n\3\13\6\13|\n\13\r\13\16\13}\3\f\3\f\3\r\3\r\5\r"+
		"\u0084\n\r\3\16\3\16\3\16\2\2\17\2\4\6\b\n\f\16\20\22\24\26\30\32\2\6"+
		"\4\2\37 $%\3\2\'(\3\2\23\24\4\2\21\21\30\31\u0089\2\34\3\2\2\2\4\61\3"+
		"\2\2\2\6:\3\2\2\2\bG\3\2\2\2\nI\3\2\2\2\fM\3\2\2\2\16\\\3\2\2\2\20j\3"+
		"\2\2\2\22n\3\2\2\2\24{\3\2\2\2\26\177\3\2\2\2\30\u0081\3\2\2\2\32\u0085"+
		"\3\2\2\2\34\35\7\13\2\2\35\36\7\n\2\2\36\37\7\30\2\2\37!\7\16\2\2 \"\5"+
		"\4\3\2! \3\2\2\2!\"\3\2\2\2\"&\3\2\2\2#%\5\n\6\2$#\3\2\2\2%(\3\2\2\2&"+
		"$\3\2\2\2&\'\3\2\2\2\')\3\2\2\2(&\3\2\2\2)+\5\f\7\2*,\5\16\b\2+*\3\2\2"+
		"\2,-\3\2\2\2-+\3\2\2\2-.\3\2\2\2./\3\2\2\2/\60\7\2\2\3\60\3\3\2\2\2\61"+
		"\62\7\b\2\2\62\64\7\35\2\2\63\65\5\6\4\2\64\63\3\2\2\2\65\66\3\2\2\2\66"+
		"\64\3\2\2\2\66\67\3\2\2\2\678\3\2\2\289\7\36\2\29\5\3\2\2\2:C\7\37\2\2"+
		";<\7\"\2\2<D\5\b\5\2=>\7!\2\2>@\7\37\2\2?=\3\2\2\2@A\3\2\2\2A?\3\2\2\2"+
		"AB\3\2\2\2BD\3\2\2\2C;\3\2\2\2C?\3\2\2\2DE\3\2\2\2EF\7#\2\2F\7\3\2\2\2"+
		"GH\t\2\2\2H\t\3\2\2\2IJ\7\22\2\2JK\7\30\2\2KL\5\22\n\2L\13\3\2\2\2MN\7"+
		"\30\2\2NO\7\f\2\2OT\7\30\2\2PQ\7\26\2\2QS\7\30\2\2RP\3\2\2\2SV\3\2\2\2"+
		"TR\3\2\2\2TU\3\2\2\2UX\3\2\2\2VT\3\2\2\2WY\5\22\n\2XW\3\2\2\2XY\3\2\2"+
		"\2YZ\3\2\2\2Z[\7\16\2\2[\r\3\2\2\2\\]\7\30\2\2]^\7\f\2\2^b\5\20\t\2_a"+
		"\5\22\n\2`_\3\2\2\2ad\3\2\2\2b`\3\2\2\2bc\3\2\2\2ce\3\2\2\2db\3\2\2\2"+
		"ef\7\16\2\2f\17\3\2\2\2gh\5\30\r\2hi\5\32\16\2ik\3\2\2\2jg\3\2\2\2kl\3"+
		"\2\2\2lj\3\2\2\2lm\3\2\2\2m\21\3\2\2\2np\7\t\2\2oq\t\3\2\2po\3\2\2\2p"+
		"q\3\2\2\2qt\3\2\2\2ru\5\24\13\2su\5\26\f\2tr\3\2\2\2ts\3\2\2\2uv\3\2\2"+
		"\2vt\3\2\2\2vw\3\2\2\2wx\3\2\2\2xy\7\4\2\2y\23\3\2\2\2z|\7\3\2\2{z\3\2"+
		"\2\2|}\3\2\2\2}{\3\2\2\2}~\3\2\2\2~\25\3\2\2\2\177\u0080\7)\2\2\u0080"+
		"\27\3\2\2\2\u0081\u0083\t\4\2\2\u0082\u0084\7\27\2\2\u0083\u0082\3\2\2"+
		"\2\u0083\u0084\3\2\2\2\u0084\31\3\2\2\2\u0085\u0086\t\5\2\2\u0086\33\3"+
		"\2\2\2\21!&-\66ACTXblptv}\u0083";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}