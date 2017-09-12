/*******************************************************************************
 * Copyright (c) 2010-2015 Gerald Rosenberg & others. All rights reserved.
 *
 * This program and the accompanying materials are made available under the
 * terms of the standard 3-clause BSD License.  A copy of the License
 * is provided with this distribution in the License.txt file.
 *******************************************************************************/
/* ANTLR v4 XVisitor grammar */

parser grammar XVisitorParser;

options {
	superClass = ParserAdaptor	;
	tokenVocab = XVisitorLexer	;
}

@header {
	package net.certiv.xvisitordt.core.parser.gen;

	import net.certiv.xvisitordt.core.parser.ParserAdaptor;
}

grammarSpec
    :   XVISITOR GRAMMAR ID SEMI 
    	optionsSpec?
    	action?
    	xmain+
    	xpath+
    	EOF
    ;

optionsSpec
	:	OPTIONS LBRACE option* RBRACE
	;

option
    :   ID ASSIGN optionValue SEMI
 	;
 	
optionValue
    :   ID ( DOT ID )*
    ;

action
	:	AT ID actionBlock 
	;

xmain
	:	name=ID COLON ID ( OR ID )* actionBlock? SEMI
	;

xpath
	:	name=ID COLON xpathSpec actionBlock* SEMI
	; 

xpathSpec
	:	( separator word )+
	;

actionBlock
	:	LBRACE ( ONENTRY | ONEXIT )? ( text | reference )+ RBRACE
	;

text 
	: TEXT+
	;

reference 
	: REFERENCE
	;

separator
	:	( ANY | SEP ) NOT?
	;

word
	:	ID
	|	STAR
	|	LITERAL
	;

