/**
 * Heading JavaDoc block
 */
grammar FormatPreview;
options {
  superClass = PreviewProcessor ; // base class
  TokenLabelType = PreviewToken;
}

tokens {
  METHOD_DECL, // function definition
  ARG_DECL,    // parameter
  BLOCK,
  ASSIGN,
  DEREF,           // *p dereference pointer
  ADD,
	MEMBER
}


@members {
	@Override
	protected Object recoverFromMismatchedToken(IntStream input, int ttype, BitSet follow)
		throws RecognitionException {
			throw new MismatchedTokenException(ttype, input);
	}
	@Override
	public Object recoverFromMismatchedSet(IntStream input, RecognitionException e, BitSet follow)
		throws RecognitionException {
			return super.recoverFromMismatchedSet(input, e, follow);
	}
}

// START: method
compilationUnit
@after {setUnknownTokenBoundaries();}
    :   (   classDeclaration | methodDeclaration 
        )+
    ;

classDeclaration
    :   'class' ID  '{' classMember+ '}' ';'
    ;

classMember
    :   varDeclaration
    |   methodDeclaration
    ;   
varDeclaration : type ID EQ primary;

methodDeclaration
    :   type ID '(' formalParameters? ')' block
    ;

formalParameters
    :   parameter (',' parameter)* 
    ;
    
parameter
    :   type ID      
    |   type ID '[]' 
    |   type '*' ID  
    ;

type:   primitiveType
    |   ID
    ;

primitiveType
    :   'int'
    |   'char'
    |   'boolean'
    |   'void'
    ;

block
    :   '{' statement* '}' 
    ;

statement
// options {backtrack=true;}
    :   block
	;

primary
    :   ID
    |   INT
    |   CHAR
    |   'true'
    |   'false'
    |   '(' expr ')' 
    ;

expr
	: expr PLUS expr
	| primary
	;

// LEXER RULES
EQ:'=';
PLUS:'+';

ID  :   LETTER (LETTER | INT )*
    ;

fragment
LETTER
    :   ('a'..'z' | 'A'..'Z')
    ;

CHAR:   '\'' . '\'' ;

INT :   '0'..'9'+ ;
    
WS  :   (' '|'\r'|'\t'|'\n') -> channel(HIDDEN) ;

SL_COMMENT
    :   '//' ~('\r'|'\n')* '\r'? '\n' -> channel(HIDDEN)
    ;
