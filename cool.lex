/*
 *  The scanner definition for COOL.
 */

import java_cup.runtime.Symbol;

%%
%{

/*  Stuff enclosed in %{ %} is copied verbatim to the lexer class
 *  definition, all the extra variables/functions you want to use in the
 *  lexer actions should go here.  Don't remove or modify anything that
 *  was there initially.  */

    // Integer for counting nested comments
    int nestedCommentCount=0;

    // Boolean value for determining if string is too long
    boolean stringTooLong=false;

    // Boolean value for determining if null character occurs in string
    boolean nullInString=false;

    // Boolean value for some error occuring in string
    boolean stringError=false;

    // Boolean for if an EOF is encountered
    boolean eof_encountered=false;
    
    // Max size of string constants
    static int MAX_STR_CONST = 1025;

    // For assembling string constants
    StringBuffer string_buf = new StringBuffer();

    private int curr_lineno = 1;
    int get_curr_lineno() {
	return curr_lineno;
    }
	// for special purposes
	boolean skip = false ;
    private AbstractSymbol filename;

    void set_filename(String fname) {
	filename = AbstractTable.stringtable.addString(fname);
    }

    AbstractSymbol curr_filename() {
	return filename;
    }
	public interface Scanner 
	{
		public Symbol next_token() throws java.lang.Exception;
	}
%}

%init{

/*  Stuff enclosed in %init{ %init} is copied verbatim to the lexer
 *  class constructor, all the extra initialization you want to do should
 *  go here.  Don't remove or modify anything that was there initially. */

    // empty for now
%init}

%eofval{

/*  Stuff enclosed in %eofval{ %eofval} specifies java code that is
 *  executed when end-of-file is reached.  If you use multiple lexical
 *  states and want to do something special if an EOF is encountered in
 *  one of those states, place your code in the switch statement.
 *  Ultimately, you should return the EOF symbol, or your lexer won't
 *  work.  */

    if(eof_encountered) return new Symbol(TokenConstants.EOF);
    switch(yy_lexical_state) 
	{
		case YYINITIAL:
	/* nothing special to do in the initial state */
			break;
	/* If necessary, add code for other states here, e.g:
	   case COMMENT:
	   ...
	   break;
	*/
		case STRING:
		  eof_encountered=true;
		  return new Symbol(TokenConstants.ERROR, "EOF in string constant");
		case BLOCK_COMMENT:
		  eof_encountered=true;
		  return new Symbol(TokenConstants.ERROR, "EOF in comment");
    }
    return new Symbol(TokenConstants.EOF);
%eofval}
%class CoolLexer
%cup
%state STRING
%state BLOCK_COMMENT
%%

<YYINITIAL> "=>"			{ /* Sample lexical rule for "=>" arrow.
                                     Further lexical rules should be defined
                                     here, after the last %% separator */
                                  return new Symbol(TokenConstants.DARROW); }

<STRING> \" 
{ // If an escape character appears before a quote, we must put a quote into the string
  if(stringTooLong)
  {
    return new Symbol(TokenConstants.ERROR, "String constant too long");
  }
  else if(nullInString)
  {
    return new Symbol(TokenConstants.ERROR, "Null character appeared in string");
  }
  else if(stringError)
  {
    return new Symbol(TokenConstants.ERROR, "Error occurred while parsing string");
  }

  if(!skip && string_buf.length()>0 && string_buf.charAt(string_buf.length()-1)=='\\') 
  {
    string_buf.setCharAt(string_buf.length()-1, '\"');
  } else 
  {
    yybegin(YYINITIAL); 
    return new Symbol(TokenConstants.STR_CONST, AbstractTable.stringtable.addString(string_buf.toString())); 
  }
}
<STRING> \n { // Code for newline characters
  // If a newline appears in a string, it must be escaped, else error
  if(string_buf.length()==0 || skip || (string_buf.charAt(string_buf.length()-1)!='\\' && (string_buf.charAt(string_buf.length()-1)!='\r')))
  {
    curr_lineno++;
    return new Symbol(TokenConstants.ERROR, "Unterminated string constant");
  }
  else 
  {
    // Replace '\' character in string buffer with newline
    string_buf.setCharAt(string_buf.length()-1, '\n');
  }
 }

<STRING> [^\n\"] { 
  // Code for handling other characters encountered in strings
  // including special cases for escaped characters
  if(string_buf.length()+1>=MAX_STR_CONST) 
  {
    stringTooLong=true;
  }
  else if(string_buf.length()==0) 
  {
    string_buf.append(yytext());
  }
  else 
  {  
    // Check if null character appears in input stream
    if(yytext().charAt(0)=='\0' || nullInString) {
      nullInString=true;
    }
	else 
	{
      int length=string_buf.length();
      if(string_buf.charAt(string_buf.length()-1)=='\\') 
	  {
		  if(skip)
		  {
			  string_buf.append(yytext()) ;
			  skip = false ;
		  }
		  else
		  {
			switch(yytext().charAt(0)) 
			{
				case '\b':
				  string_buf.setCharAt(length-1, '\b');
				  break;
				case '\t':
				  string_buf.setCharAt(length-1, '\t');
				  break;
				case '\n':
				  string_buf.setCharAt(length-1, '\n');
				  break;
				case '\f':
				  string_buf.setCharAt(length-1, '\f');
				  break;
				case '\r':
				  string_buf.setCharAt(length-1,'\r') ;
				  break ;
				case '\\':
				  string_buf.setCharAt(length-1, '\\');
				  skip = true ;
				  break;
				default:
				  string_buf.setCharAt(length-1,yytext.charAt(0)) ;
			}
		  }
		
      } 
	  else 
	  {
		string_buf.append(yytext());
      }
    }
  }
 }
<YYINITIAL> "(*" { yybegin(BLOCK_COMMENT); }
<BLOCK_COMMENT> \(\*  { nestedCommentCount++; }
<BLOCK_COMMENT> \n  { curr_lineno++; }
<BLOCK_COMMENT> [^\n\)\*] { /* Do Nothing */ }
<BLOCK_COMMENT> \*  { /* Do nothing */ }
<BLOCK_COMMENT> \*\) {
  if(nestedCommentCount!=0) {
    nestedCommentCount--;
  } else {
    yybegin(YYINITIAL);
  }
 }

<YYINITIAL> [t][rR][uU][eE]                  { return new Symbol(TokenConstants.BOOL_CONST, new Boolean(true)); }
<YYINITIAL> [f][aA][lL][sS][eE]              { return new Symbol(TokenConstants.BOOL_CONST, new Boolean(false)); }
<YYINITIAL> [cC][aA][sS][eE]                 { return new Symbol(TokenConstants.CASE); }
<YYINITIAL> [cC][lL][aA][sS][sS]             { return new Symbol(TokenConstants.CLASS); }
<YYINITIAL> [eE][lL][sS][eE]                 { return new Symbol(TokenConstants.ELSE); }
<YYINITIAL> [fF][iI]                         { return new Symbol(TokenConstants.FI); }
<YYINITIAL> [iI][fF]                         { return new Symbol(TokenConstants.IF); }
<YYINITIAL> [iI][nN]                         { return new Symbol(TokenConstants.IN); }
<YYINITIAL> [iI][nN][hH][eE][rR][iI][tT][sS] { return new Symbol(TokenConstants.INHERITS); }
<YYINITIAL> [iI][sS][vV][oO][iI][dD]         { return new Symbol(TokenConstants.ISVOID); }
<YYINITIAL> [lL][eE][tT]                     { return new Symbol(TokenConstants.LET); }
<YYINITIAL> [lL][oO][oO][pP]                 { return new Symbol(TokenConstants.LOOP); }
<YYINITIAL> [pP][oO][oO][lL]                 { return new Symbol(TokenConstants.POOL); }
<YYINITIAL> [tT][hH][eE][nN]                 { return new Symbol(TokenConstants.THEN); }
<YYINITIAL> [wW][hH][iI][lL][eE]             { return new Symbol(TokenConstants.WHILE); }
<YYINITIAL> [eE][sS][aA][cC]                 { return new Symbol(TokenConstants.ESAC); }
<YYINITIAL> [nN][eE][wW]                     { return new Symbol(TokenConstants.NEW); }
<YYINITIAL> [oO][fF]                         { return new Symbol(TokenConstants.OF); }
<YYINITIAL> [nN][oO][tT]                     { return new Symbol(TokenConstants.NOT); }
<YYINITIAL> <-  { return new Symbol(TokenConstants.ASSIGN); }
<YYINITIAL> [*]   { return new Symbol(TokenConstants.MULT);   }
<YYINITIAL> [\(]  { return new Symbol(TokenConstants.LPAREN); }
<YYINITIAL> [;]   { return new Symbol(TokenConstants.SEMI);   }
<YYINITIAL> [-]   { return new Symbol(TokenConstants.MINUS);  }
<YYINITIAL> [\)]  { return new Symbol(TokenConstants.RPAREN); }
<YYINITIAL> [<]   { return new Symbol(TokenConstants.LT);     }
<YYINITIAL> [,]   { return new Symbol(TokenConstants.COMMA);  }
<YYINITIAL> [/]   { return new Symbol(TokenConstants.DIV);    }
<YYINITIAL> [+]   { return new Symbol(TokenConstants.PLUS);   }
<YYINITIAL> [=]   { return new Symbol(TokenConstants.EQ); }
<YYINITIAL> [.]   { return new Symbol(TokenConstants.DOT);    }
<YYINITIAL> <=  { return new Symbol(TokenConstants.LE);     }
<YYINITIAL> [:]   { return new Symbol(TokenConstants.COLON);  }
<YYINITIAL> [\{]  { return new Symbol(TokenConstants.LBRACE); }
<YYINITIAL> [\}]  { return new Symbol(TokenConstants.RBRACE); }
<YYINITIAL> [@]   { return new Symbol(TokenConstants.AT);     }
<YYINITIAL> [~]   { return new Symbol(TokenConstants.NEG);    }
<YYINITIAL> "--".* { /* End of Line comment: Do nothing */ }

<YYINITIAL> [ ]    { /* Get rid of whitespace */ }
<YYINITIAL> \n   { }	
<YYINITIAL> \t   { }
<YYINITIAL> \v   { }
<YYINITIAL> \r   { }
<YYINITIAL> \013 { }

<YYINITIAL> [a-z][a-zA-Z0-9_]* { return new Symbol(TokenConstants.OBJECTID, AbstractTable.idtable.addString(yytext())); }
<YYINITIAL> [A-Z][a-zA-Z0-9_]* { return new Symbol(TokenConstants.TYPEID, AbstractTable.idtable.addString(yytext())); }

<YYINITIAL> [0-9]*            { return new Symbol(TokenConstants.INT_CONST, AbstractTable.inttable.addString(yytext())); }

<YYINITIAL> \"   { 
  string_buf.delete(0, string_buf.length());
  yybegin(STRING); 
  stringTooLong=false;
  nullInString=false;
  stringError=false;
 }
.                               { /* This rule should be the very last
                                     in your lexical specification and
                                     will match match everything not
                                     matched by other lexical rules. */
                                  System.err.println("LEXER BUG - UNMATCHED: " + yytext()); 
                                  return new Symbol(TokenConstants.ERROR, yytext()); }

								  


