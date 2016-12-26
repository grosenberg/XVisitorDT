grammar WhiteSpacePreview;

options { 
	/* Assign custom label type
	 */
	TokenLabelType = CommonTree;
}


declaration:statement* EOF ;
statement @init {Stack s = new Stack();} @after{s == null;}
    :   '{' statement* '}'
    |   parameter ASSIGN (ID|INT)
	;
parameter
    :   type ID
    |   type ID '[]'
    ;
type:   'int'    |   'char'    |   'boolean'    |   'void'    |   ID    ;



ID  :   LETTER (LETTER | '0'..'9')* ;

fragment LETTER
    :   ('a'..'z' | 'A'..'Z')
    ;
ASSIGN:'=';
INT :   '0'..'9'+ ;
WS  :   (' '|'\r'|'\t'|'\n') -> channel(HIDDEN) ;
COMMENT : (ML_COMMENT
		| SL_COMMENT)	-> channel(HIDDEN)
		;

fragment ML_COMMENT : '/*' .*? '*/' ;
fragment
SL_COMMENT
    :   '//' ~('\r'|'\n')* '\r'? '\n' 
    ;
