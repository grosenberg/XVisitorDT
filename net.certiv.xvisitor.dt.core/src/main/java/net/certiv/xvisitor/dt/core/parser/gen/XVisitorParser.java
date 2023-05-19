// Generated from java-escape by ANTLR 4.11.1

	package net.certiv.xvisitor.dt.core.parser.gen;

import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast", "CheckReturnValue"})
public class XVisitorParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.11.1", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		INT=1, RBRACE=2, TEXT=3, DOC_COMMENT=4, BLOCK_COMMENT=5, LINE_COMMENT=6, 
		OPTIONS=7, LBRACE=8, GRAMMAR=9, XVISITOR=10, COLON=11, COMMA=12, SEMI=13, 
		ASSIGN=14, QUESTION=15, STAR=16, AT=17, ANY=18, SEP=19, DOT=20, OR=21, 
		NOT=22, ID=23, LITERAL=24, HORZ_WS=25, VERT_WS=26, ERRCHAR=27, ABLOCK_RBRACE=28, 
		ONENTRY=29, ONEXIT=30, REFERENCE=31;
	public static final int
		RULE_grammarSpec = 0, RULE_optionsSpec = 1, RULE_option = 2, RULE_optionValue = 3, 
		RULE_action = 4, RULE_xgroup = 5, RULE_xpath = 6, RULE_xpathSpec = 7, 
		RULE_actionBlock = 8, RULE_text = 9, RULE_reference = 10, RULE_separator = 11, 
		RULE_word = 12;
	private static String[] makeRuleNames() {
		return new String[] {
			"grammarSpec", "optionsSpec", "option", "optionValue", "action", "xgroup", 
			"xpath", "xpathSpec", "actionBlock", "text", "reference", "separator", 
			"word"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, null, null, null, null, null, null, "'options'", null, "'grammar'", 
			"'xvisitor'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, "INT", "RBRACE", "TEXT", "DOC_COMMENT", "BLOCK_COMMENT", "LINE_COMMENT", 
			"OPTIONS", "LBRACE", "GRAMMAR", "XVISITOR", "COLON", "COMMA", "SEMI", 
			"ASSIGN", "QUESTION", "STAR", "AT", "ANY", "SEP", "DOT", "OR", "NOT", 
			"ID", "LITERAL", "HORZ_WS", "VERT_WS", "ERRCHAR", "ABLOCK_RBRACE", "ONENTRY", 
			"ONEXIT", "REFERENCE"
		};
	}
	private static final String[] _SYMBOLIC_NAMES = makeSymbolicNames();
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
	public String getGrammarFileName() { return "java-escape"; }

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

	@SuppressWarnings("CheckReturnValue")
	public static class GrammarSpecContext extends ParserRuleContext {
		public TerminalNode XVISITOR() { return getToken(XVisitorParser.XVISITOR, 0); }
		public TerminalNode GRAMMAR() { return getToken(XVisitorParser.GRAMMAR, 0); }
		public TerminalNode ID() { return getToken(XVisitorParser.ID, 0); }
		public TerminalNode SEMI() { return getToken(XVisitorParser.SEMI, 0); }
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
		public List<XgroupContext> xgroup() {
			return getRuleContexts(XgroupContext.class);
		}
		public XgroupContext xgroup(int i) {
			return getRuleContext(XgroupContext.class,i);
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
			int _alt;
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
			_errHandler.sync(this);
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
			setState(42);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,2,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(39);
					xgroup();
					}
					} 
				}
				setState(44);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,2,_ctx);
			}
			setState(46); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(45);
				xpath();
				}
				}
				setState(48); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==ID );
			setState(50);
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

	@SuppressWarnings("CheckReturnValue")
	public static class OptionsSpecContext extends ParserRuleContext {
		public TerminalNode OPTIONS() { return getToken(XVisitorParser.OPTIONS, 0); }
		public TerminalNode LBRACE() { return getToken(XVisitorParser.LBRACE, 0); }
		public TerminalNode RBRACE() { return getToken(XVisitorParser.RBRACE, 0); }
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
			setState(52);
			match(OPTIONS);
			setState(53);
			match(LBRACE);
			setState(57);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==ID) {
				{
				{
				setState(54);
				option();
				}
				}
				setState(59);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(60);
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

	@SuppressWarnings("CheckReturnValue")
	public static class OptionContext extends ParserRuleContext {
		public TerminalNode ID() { return getToken(XVisitorParser.ID, 0); }
		public TerminalNode ASSIGN() { return getToken(XVisitorParser.ASSIGN, 0); }
		public OptionValueContext optionValue() {
			return getRuleContext(OptionValueContext.class,0);
		}
		public TerminalNode SEMI() { return getToken(XVisitorParser.SEMI, 0); }
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
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(62);
			match(ID);
			setState(63);
			match(ASSIGN);
			setState(64);
			optionValue();
			setState(65);
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

	@SuppressWarnings("CheckReturnValue")
	public static class OptionValueContext extends ParserRuleContext {
		public List<TerminalNode> ID() { return getTokens(XVisitorParser.ID); }
		public TerminalNode ID(int i) {
			return getToken(XVisitorParser.ID, i);
		}
		public List<TerminalNode> DOT() { return getTokens(XVisitorParser.DOT); }
		public TerminalNode DOT(int i) {
			return getToken(XVisitorParser.DOT, i);
		}
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
			setState(67);
			match(ID);
			setState(72);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==DOT) {
				{
				{
				setState(68);
				match(DOT);
				setState(69);
				match(ID);
				}
				}
				setState(74);
				_errHandler.sync(this);
				_la = _input.LA(1);
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

	@SuppressWarnings("CheckReturnValue")
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
			setState(75);
			match(AT);
			setState(76);
			match(ID);
			setState(77);
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

	@SuppressWarnings("CheckReturnValue")
	public static class XgroupContext extends ParserRuleContext {
		public Token name;
		public Token ID;
		public List<Token> rules = new ArrayList<Token>();
		public TerminalNode COLON() { return getToken(XVisitorParser.COLON, 0); }
		public TerminalNode SEMI() { return getToken(XVisitorParser.SEMI, 0); }
		public List<TerminalNode> ID() { return getTokens(XVisitorParser.ID); }
		public TerminalNode ID(int i) {
			return getToken(XVisitorParser.ID, i);
		}
		public List<TerminalNode> OR() { return getTokens(XVisitorParser.OR); }
		public TerminalNode OR(int i) {
			return getToken(XVisitorParser.OR, i);
		}
		public ActionBlockContext actionBlock() {
			return getRuleContext(ActionBlockContext.class,0);
		}
		public XgroupContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_xgroup; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof XVisitorParserListener ) ((XVisitorParserListener)listener).enterXgroup(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof XVisitorParserListener ) ((XVisitorParserListener)listener).exitXgroup(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof XVisitorParserVisitor ) return ((XVisitorParserVisitor<? extends T>)visitor).visitXgroup(this);
			else return visitor.visitChildren(this);
		}
	}

	public final XgroupContext xgroup() throws RecognitionException {
		XgroupContext _localctx = new XgroupContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_xgroup);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(79);
			((XgroupContext)_localctx).name = match(ID);
			setState(80);
			match(COLON);
			setState(81);
			((XgroupContext)_localctx).ID = match(ID);
			((XgroupContext)_localctx).rules.add(((XgroupContext)_localctx).ID);
			setState(86);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==OR) {
				{
				{
				setState(82);
				match(OR);
				setState(83);
				((XgroupContext)_localctx).ID = match(ID);
				((XgroupContext)_localctx).rules.add(((XgroupContext)_localctx).ID);
				}
				}
				setState(88);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(90);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==LBRACE) {
				{
				setState(89);
				actionBlock();
				}
			}

			setState(92);
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

	@SuppressWarnings("CheckReturnValue")
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
			setState(94);
			((XpathContext)_localctx).name = match(ID);
			setState(95);
			match(COLON);
			setState(96);
			xpathSpec();
			setState(100);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==LBRACE) {
				{
				{
				setState(97);
				actionBlock();
				}
				}
				setState(102);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(103);
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

	@SuppressWarnings("CheckReturnValue")
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
			setState(108); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(105);
				separator();
				setState(106);
				word();
				}
				}
				setState(110); 
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

	@SuppressWarnings("CheckReturnValue")
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
			setState(112);
			match(LBRACE);
			setState(114);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==ONENTRY || _la==ONEXIT) {
				{
				setState(113);
				_la = _input.LA(1);
				if ( !(_la==ONENTRY || _la==ONEXIT) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				}
			}

			setState(118); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				setState(118);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case TEXT:
					{
					setState(116);
					text();
					}
					break;
				case REFERENCE:
					{
					setState(117);
					reference();
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				}
				setState(120); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==TEXT || _la==REFERENCE );
			setState(122);
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

	@SuppressWarnings("CheckReturnValue")
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
			setState(125); 
			_errHandler.sync(this);
			_alt = 1;
			do {
				switch (_alt) {
				case 1:
					{
					{
					setState(124);
					match(TEXT);
					}
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				setState(127); 
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

	@SuppressWarnings("CheckReturnValue")
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
			setState(129);
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

	@SuppressWarnings("CheckReturnValue")
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
			setState(131);
			_la = _input.LA(1);
			if ( !(_la==ANY || _la==SEP) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			setState(133);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==NOT) {
				{
				setState(132);
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

	@SuppressWarnings("CheckReturnValue")
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
			setState(135);
			_la = _input.LA(1);
			if ( !(((_la) & ~0x3f) == 0 && ((1L << _la) & 25231360L) != 0) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
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
		"\u0004\u0001\u001f\u008a\u0002\u0000\u0007\u0000\u0002\u0001\u0007\u0001"+
		"\u0002\u0002\u0007\u0002\u0002\u0003\u0007\u0003\u0002\u0004\u0007\u0004"+
		"\u0002\u0005\u0007\u0005\u0002\u0006\u0007\u0006\u0002\u0007\u0007\u0007"+
		"\u0002\b\u0007\b\u0002\t\u0007\t\u0002\n\u0007\n\u0002\u000b\u0007\u000b"+
		"\u0002\f\u0007\f\u0001\u0000\u0001\u0000\u0001\u0000\u0001\u0000\u0001"+
		"\u0000\u0003\u0000 \b\u0000\u0001\u0000\u0005\u0000#\b\u0000\n\u0000\f"+
		"\u0000&\t\u0000\u0001\u0000\u0005\u0000)\b\u0000\n\u0000\f\u0000,\t\u0000"+
		"\u0001\u0000\u0004\u0000/\b\u0000\u000b\u0000\f\u00000\u0001\u0000\u0001"+
		"\u0000\u0001\u0001\u0001\u0001\u0001\u0001\u0005\u00018\b\u0001\n\u0001"+
		"\f\u0001;\t\u0001\u0001\u0001\u0001\u0001\u0001\u0002\u0001\u0002\u0001"+
		"\u0002\u0001\u0002\u0001\u0002\u0001\u0003\u0001\u0003\u0001\u0003\u0005"+
		"\u0003G\b\u0003\n\u0003\f\u0003J\t\u0003\u0001\u0004\u0001\u0004\u0001"+
		"\u0004\u0001\u0004\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001"+
		"\u0005\u0005\u0005U\b\u0005\n\u0005\f\u0005X\t\u0005\u0001\u0005\u0003"+
		"\u0005[\b\u0005\u0001\u0005\u0001\u0005\u0001\u0006\u0001\u0006\u0001"+
		"\u0006\u0001\u0006\u0005\u0006c\b\u0006\n\u0006\f\u0006f\t\u0006\u0001"+
		"\u0006\u0001\u0006\u0001\u0007\u0001\u0007\u0001\u0007\u0004\u0007m\b"+
		"\u0007\u000b\u0007\f\u0007n\u0001\b\u0001\b\u0003\bs\b\b\u0001\b\u0001"+
		"\b\u0004\bw\b\b\u000b\b\f\bx\u0001\b\u0001\b\u0001\t\u0004\t~\b\t\u000b"+
		"\t\f\t\u007f\u0001\n\u0001\n\u0001\u000b\u0001\u000b\u0003\u000b\u0086"+
		"\b\u000b\u0001\f\u0001\f\u0001\f\u0000\u0000\r\u0000\u0002\u0004\u0006"+
		"\b\n\f\u000e\u0010\u0012\u0014\u0016\u0018\u0000\u0003\u0001\u0000\u001d"+
		"\u001e\u0001\u0000\u0012\u0013\u0002\u0000\u0010\u0010\u0017\u0018\u008b"+
		"\u0000\u001a\u0001\u0000\u0000\u0000\u00024\u0001\u0000\u0000\u0000\u0004"+
		">\u0001\u0000\u0000\u0000\u0006C\u0001\u0000\u0000\u0000\bK\u0001\u0000"+
		"\u0000\u0000\nO\u0001\u0000\u0000\u0000\f^\u0001\u0000\u0000\u0000\u000e"+
		"l\u0001\u0000\u0000\u0000\u0010p\u0001\u0000\u0000\u0000\u0012}\u0001"+
		"\u0000\u0000\u0000\u0014\u0081\u0001\u0000\u0000\u0000\u0016\u0083\u0001"+
		"\u0000\u0000\u0000\u0018\u0087\u0001\u0000\u0000\u0000\u001a\u001b\u0005"+
		"\n\u0000\u0000\u001b\u001c\u0005\t\u0000\u0000\u001c\u001d\u0005\u0017"+
		"\u0000\u0000\u001d\u001f\u0005\r\u0000\u0000\u001e \u0003\u0002\u0001"+
		"\u0000\u001f\u001e\u0001\u0000\u0000\u0000\u001f \u0001\u0000\u0000\u0000"+
		" $\u0001\u0000\u0000\u0000!#\u0003\b\u0004\u0000\"!\u0001\u0000\u0000"+
		"\u0000#&\u0001\u0000\u0000\u0000$\"\u0001\u0000\u0000\u0000$%\u0001\u0000"+
		"\u0000\u0000%*\u0001\u0000\u0000\u0000&$\u0001\u0000\u0000\u0000\')\u0003"+
		"\n\u0005\u0000(\'\u0001\u0000\u0000\u0000),\u0001\u0000\u0000\u0000*("+
		"\u0001\u0000\u0000\u0000*+\u0001\u0000\u0000\u0000+.\u0001\u0000\u0000"+
		"\u0000,*\u0001\u0000\u0000\u0000-/\u0003\f\u0006\u0000.-\u0001\u0000\u0000"+
		"\u0000/0\u0001\u0000\u0000\u00000.\u0001\u0000\u0000\u000001\u0001\u0000"+
		"\u0000\u000012\u0001\u0000\u0000\u000023\u0005\u0000\u0000\u00013\u0001"+
		"\u0001\u0000\u0000\u000045\u0005\u0007\u0000\u000059\u0005\b\u0000\u0000"+
		"68\u0003\u0004\u0002\u000076\u0001\u0000\u0000\u00008;\u0001\u0000\u0000"+
		"\u000097\u0001\u0000\u0000\u00009:\u0001\u0000\u0000\u0000:<\u0001\u0000"+
		"\u0000\u0000;9\u0001\u0000\u0000\u0000<=\u0005\u0002\u0000\u0000=\u0003"+
		"\u0001\u0000\u0000\u0000>?\u0005\u0017\u0000\u0000?@\u0005\u000e\u0000"+
		"\u0000@A\u0003\u0006\u0003\u0000AB\u0005\r\u0000\u0000B\u0005\u0001\u0000"+
		"\u0000\u0000CH\u0005\u0017\u0000\u0000DE\u0005\u0014\u0000\u0000EG\u0005"+
		"\u0017\u0000\u0000FD\u0001\u0000\u0000\u0000GJ\u0001\u0000\u0000\u0000"+
		"HF\u0001\u0000\u0000\u0000HI\u0001\u0000\u0000\u0000I\u0007\u0001\u0000"+
		"\u0000\u0000JH\u0001\u0000\u0000\u0000KL\u0005\u0011\u0000\u0000LM\u0005"+
		"\u0017\u0000\u0000MN\u0003\u0010\b\u0000N\t\u0001\u0000\u0000\u0000OP"+
		"\u0005\u0017\u0000\u0000PQ\u0005\u000b\u0000\u0000QV\u0005\u0017\u0000"+
		"\u0000RS\u0005\u0015\u0000\u0000SU\u0005\u0017\u0000\u0000TR\u0001\u0000"+
		"\u0000\u0000UX\u0001\u0000\u0000\u0000VT\u0001\u0000\u0000\u0000VW\u0001"+
		"\u0000\u0000\u0000WZ\u0001\u0000\u0000\u0000XV\u0001\u0000\u0000\u0000"+
		"Y[\u0003\u0010\b\u0000ZY\u0001\u0000\u0000\u0000Z[\u0001\u0000\u0000\u0000"+
		"[\\\u0001\u0000\u0000\u0000\\]\u0005\r\u0000\u0000]\u000b\u0001\u0000"+
		"\u0000\u0000^_\u0005\u0017\u0000\u0000_`\u0005\u000b\u0000\u0000`d\u0003"+
		"\u000e\u0007\u0000ac\u0003\u0010\b\u0000ba\u0001\u0000\u0000\u0000cf\u0001"+
		"\u0000\u0000\u0000db\u0001\u0000\u0000\u0000de\u0001\u0000\u0000\u0000"+
		"eg\u0001\u0000\u0000\u0000fd\u0001\u0000\u0000\u0000gh\u0005\r\u0000\u0000"+
		"h\r\u0001\u0000\u0000\u0000ij\u0003\u0016\u000b\u0000jk\u0003\u0018\f"+
		"\u0000km\u0001\u0000\u0000\u0000li\u0001\u0000\u0000\u0000mn\u0001\u0000"+
		"\u0000\u0000nl\u0001\u0000\u0000\u0000no\u0001\u0000\u0000\u0000o\u000f"+
		"\u0001\u0000\u0000\u0000pr\u0005\b\u0000\u0000qs\u0007\u0000\u0000\u0000"+
		"rq\u0001\u0000\u0000\u0000rs\u0001\u0000\u0000\u0000sv\u0001\u0000\u0000"+
		"\u0000tw\u0003\u0012\t\u0000uw\u0003\u0014\n\u0000vt\u0001\u0000\u0000"+
		"\u0000vu\u0001\u0000\u0000\u0000wx\u0001\u0000\u0000\u0000xv\u0001\u0000"+
		"\u0000\u0000xy\u0001\u0000\u0000\u0000yz\u0001\u0000\u0000\u0000z{\u0005"+
		"\u0002\u0000\u0000{\u0011\u0001\u0000\u0000\u0000|~\u0005\u0003\u0000"+
		"\u0000}|\u0001\u0000\u0000\u0000~\u007f\u0001\u0000\u0000\u0000\u007f"+
		"}\u0001\u0000\u0000\u0000\u007f\u0080\u0001\u0000\u0000\u0000\u0080\u0013"+
		"\u0001\u0000\u0000\u0000\u0081\u0082\u0005\u001f\u0000\u0000\u0082\u0015"+
		"\u0001\u0000\u0000\u0000\u0083\u0085\u0007\u0001\u0000\u0000\u0084\u0086"+
		"\u0005\u0016\u0000\u0000\u0085\u0084\u0001\u0000\u0000\u0000\u0085\u0086"+
		"\u0001\u0000\u0000\u0000\u0086\u0017\u0001\u0000\u0000\u0000\u0087\u0088"+
		"\u0007\u0002\u0000\u0000\u0088\u0019\u0001\u0000\u0000\u0000\u000f\u001f"+
		"$*09HVZdnrvx\u007f\u0085";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}