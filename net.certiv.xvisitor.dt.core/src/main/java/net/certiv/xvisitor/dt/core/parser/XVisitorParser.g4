/*******************************************************************************
 * Copyright (c) 2010-2024 Gerald Rosenberg. All rights reserved.
 *
 * This program and the accompanying materials are made available under the
 * terms of the standard 3-clause BSD License.  A copy of the License
 * is provided with this distribution in the License.txt file.
 *******************************************************************************/
parser grammar XVisitorParser;

options {
	tokenVocab = XVisitorLexer	;
}

@header {
	package net.certiv.xvisitor.dt.core.parser.gen;
}

grammarSpec 
    :   declaration 
    	optionsSpec?
    	action*
    	xgroup*
    	xpath+
    	EOF
    ;

declaration
	: XVISITOR GRAMMAR ID SEMI
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

xgroup
	:	name=ID COLON rules+=ID ( OR rules+=ID )* actionBlock? SEMI
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

