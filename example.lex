class example
{
	public static void main(String argv[]) throws java.io.IOException
	{
		Yylex yy = new Yylex(System.in) ;
		Integer t ;
		while((t = yy.yylex())!=null)
		{
			System.out.println("Another one!") ;
			System.out.println(t) ;
		}
	}
}
%%
%{
	int nestedCommentCount = 0 ;
    boolean stringTooLong=false;
    boolean nullInString=false;
    boolean stringError=false ;
    boolean eof_encountered=false;
	StringBuffer string_buf = new StringBuffer();
	private int curr_lineno = 1;
	static int MAX_STR_CONST = 1025;
	boolean temp_skip = false ;
	final boolean debug = false ;
%}
%eofval{
    switch(yy_lexical_state) 
	{
		case YYINITIAL:
			break;
		case STRING:
		  eof_encountered=true;
		  System.out.println("EOF encountered in String.") ;
		  break ;
		case BLOCK_COMMENT:
		  eof_encountered=true;
		  System.out.println("EOF encountered in block comment.") ;
    }
%eofval}

DIGIT = [0-9]
ALPHA = [a-zA-Z]
notAB = [^\\]
opening = "\(\*"
closing = "\*\)"
STRINGTEXT = \(\*(([^\(*]|\(([^*])|\*([^\)]))*)\*\)
%state BLOCK_COMMENT
%state STRING

%type Integer
%%
<YYINITIAL> <-											{System.out.println(yytext()) ;}
<YYINITIAL> [\{] 										{System.out.println(yytext()) ;}
<YYINITIAL> [\}] 				 						{System.out.println(yytext()) ;}
<YYINITIAL> [cC][lL][aA][sS][sS] 						{System.out.println(yytext()) ;}

<YYINITIAL> [eE][lL][sS][eE]							{System.out.println(yytext()) ;}
<YYINITIAL> [lL][eE][tT]								{System.out.println(yytext()) ;}
<YYINITIAL> [lL][oO][oO][pP]							{System.out.println(yytext()) ;}
<YYINITIAL> [pP][oO][oO][lL]							{System.out.println(yytext()) ;}
<YYINITIAL> [tT][hH][Ee][nN]							{System.out.println(yytext()) ;}
<YYINITIAL> [wW][hH][iI][lL][eE]						{System.out.println(yytext()) ;}
<YYINITIAL> [oO][fF]									{System.out.println(yytext()) ;}

<YYINITIAL> [nN][eE][wW]								{System.out.println(yytext()) ;}
<YYINITIAL> [iI][sS][vV][oO][iI][dD]					{System.out.println(yytext()) ;}
<YYINITIAL> [sS][tT][rR][_][cC][oO][nN][sS][tT]			{System.out.println(yytext()) ;}
<YYINITIAL> [iI][nN][tT][_][cC][oO][nN][sS][tT]			{System.out.println(yytext()) ;}
<YYINITIAL> [bB][oO][oO][lL][_][cC][oO][nN][sS][tT]		{System.out.println(yytext()) ;}
<YYINITIAL> [tT][yY][pP][eE][iI][dD]					{System.out.println(yytext()) ;}
<YYINITIAL> [oO][bB][jJ][eE][cC][tT][iI][dD]			{System.out.println(yytext()) ;}
<YYINITIAL> [aA][sS][sS][iI][gG][nN]					{System.out.println(yytext()) ;}
<YYINITIAL> [nN][oO][tT]								{System.out.println(yytext()) ;}
<YYINITIAL> [lL][eE]									{System.out.println(yytext()) ;}
<YYINITIAL> [eE][rR][rR][oO][rR]						{System.out.println(yytext()) ;}
<YYINITIAL> [lL][eE][tT][_][sS][tT][mM][tT]				{System.out.println(yytext()) ;}
<YYINITIAL> [cC][aA][sS][eE] 							{System.out.println(yytext()) ;}
<YYINITIAL> [eE][sS][aA][cC] 							{System.out.println(yytext()) ;}
<YYINITIAL> [iI][fF] 									{System.out.println(yytext()) ;}
<YYINITIAL> [fF][iI] 									{System.out.println(yytext()) ;}
<YYINITIAL> [iI][nN] 									{System.out.println(yytext()) ;}
<YYINITIAL> [iI][nN][hH][eE][rR][iI][tT][sS] 			{System.out.println(yytext()) ;}
<YYINITIAL> [iI][nN][tT] 								{System.out.println(yytext()) ;}
<YYINITIAL> [bB][oO][oO][lL] 							{System.out.println(yytext()) ;}
<YYINITIAL> [sS][tT][rR][iI][nN][gG]					{System.out.println(yytext()) ;}

<YYINITIAL> [*]   {System.out.println(yytext()) ;}
<YYINITIAL> [\(]  {System.out.println(yytext()) ;}
<YYINITIAL> [;]   {System.out.println(yytext()) ;}
<YYINITIAL> [-]   {System.out.println(yytext()) ;}
<YYINITIAL> [\)]  {System.out.println(yytext()) ;}
<YYINITIAL> [<]   {System.out.println(yytext()) ;}
<YYINITIAL> [,]   {System.out.println(yytext()) ;}
<YYINITIAL> [/]   {System.out.println(yytext()) ;}
<YYINITIAL> [+]   {System.out.println(yytext()) ;}
<YYINITIAL> [=]   {System.out.println(yytext()) ;}
<YYINITIAL> [.]   {System.out.println(yytext()) ;}
<YYINITIAL> <=  {System.out.println(yytext()) ;}
<YYINITIAL> <-  {System.out.println(yytext()) ;}
<YYINITIAL> ==  {System.out.println(yytext()) ;}
<YYINITIAL> [:]   {System.out.println(yytext()) ;}
<YYINITIAL> [\{]  {System.out.println(yytext()) ;}
<YYINITIAL> [\}]  {System.out.println(yytext()) ;}
<YYINITIAL> [@]   {System.out.println(yytext()) ;}
<YYINITIAL> [~]   {System.out.println(yytext()) ;}
<YYINITIAL> "--".* { /* End of Line comment: Do nothing */ }
<YYINITIAL> [ ]    { /* Get rid of whitespace */ }
<YYINITIAL> \n   { }
<YYINITIAL> \t   { }
<YYINITIAL> \v   { }
<YYINITIAL> \r   { }
<YYINITIAL> \013 { }
<YYINITIAL> \t 											{System.out.println("There is a tab in here :"+yytext()) ;}
<YYINITIAL> {DIGIT}+ 									{System.out.println("This is a digit :"+yytext()) ;}
<YYINITIAL> [a-z][a-zA-Z0-9_]*							{System.out.println("An objectid  :"+yytext()); }
<YYINITIAL> [A-Z][a-zA-Z0-9_]*							{System.out.println("An typeid :"+yytext());}
<YYINITIAL> \"   { 
  string_buf.delete(0, string_buf.length());
  yybegin(STRING); 
  stringTooLong=false;
  nullInString=false;
  stringError=false;
  temp_skip = false ;
 }
<STRING> \" 
{ // If an escape character appears before a quote, we must put a quote into the string
  if(stringTooLong)
  {
    // return new Symbol(TokenConstants.ERROR, "String too long");
	System.out.println("String too long") ;
  }
  else if(nullInString)
  {
    // return new Symbol(TokenConstants.ERROR, "Null character appeared in string");
	// System.out.println()
	System.out.println("Null character appeared in string") ;
  }
  else if(stringError)
  {
    // return new Symbol(TokenConstants.ERROR, "Error occurred while parsing string");
	System.out.println("Error occurred while parsing string") ;
  }

  if(!temp_skip && string_buf.length()>0 && string_buf.charAt(string_buf.length()-1)=='\\') 
  {
    string_buf.setCharAt(string_buf.length()-1, '\"') ;
  } else 
  {
    yybegin(YYINITIAL); 
	System.out.println("String found :"+string_buf.toString()) ;
    // return new Symbol(TokenConstants.STR_CONST, AbstractTable.stringtable.addString(string_buf.toString())); 
  }
}
<STRING> \r
{
	
	if(string_buf.length()==0 || string_buf.charAt(string_buf.length()-1)!='\\')
	{
		System.out.println("Unescaped linefeed character in string: line "+(curr_lineno-1)) ;
	}
	else
	{
		string_buf.setCharAt(string_buf.length()-1,'\r') ;
	}
}

<STRING> \n 
{ // Code for newline characters
  // If a newline appears in a string, it must be escaped, else error
  
  if(string_buf.length()==0 || temp_skip|| (string_buf.charAt(string_buf.length()-1)!='\\' && string_buf.charAt(string_buf.length()-1)!='\r'))
  {
    curr_lineno++;
    // return new Symbol(TokenConstants.ERROR, "Unescaped Newline character in String: Line " + (curr_lineno-1));
	System.out.println("Unescaped Newline character in String: Line " + (curr_lineno-1)) ;
  }
  else 
  {
    // Replace '\' character in string buffer with newline
    string_buf.setCharAt(string_buf.length()-1, '\n');
  }
}

<STRING> [^\n\"] 
{ 
	// System.out.println("MEWATCHA") ;
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
		  // System.out.println("yytext inside all= "+(int)yytext().charAt(0)) ;
		if(debug) System.out.println("temp_skip :"+this.temp_skip+" for :"+yytext().charAt(0)+" : "+(int)yytext().charAt(0)) ;  
		if(this.temp_skip)
		{
			string_buf.append(yytext()) ;
			if(debug) System.out.println("Skipped :"+(int)yytext().charAt(0)) ;
			this.temp_skip = false ;
		}
		else
		{
			switch(yytext().charAt(0)) 
			{
				
				case 'b':
				  string_buf.setCharAt(length-1, '\b');
				  break;
				case 't':
				  string_buf.setCharAt(length-1, '\t');
				  break;
				case 'n':
				  string_buf.setCharAt(length-1, '\n');
				  break;
				case 'f':
				  string_buf.setCharAt(length-1, '\f');
				  break;
				case '\\':
				  string_buf.setCharAt(length-1, '\\');
				  if(debug) System.out.println("temp_skip is made true.") ;
				  this.temp_skip = true ;
				  break;
				default:
				  string_buf.setCharAt(length-1,yytext().charAt(0)) ;
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
<BLOCK_COMMENT> \n  {  }
<BLOCK_COMMENT> [^\n\)\*] { /* Do Nothing */ }
<BLOCK_COMMENT> \*  { /* Do nothing */ }
<BLOCK_COMMENT> \)  { /* Do nothing */ }
<BLOCK_COMMENT> \*\) {
  if(nestedCommentCount!=0) {
    nestedCommentCount--;
  } else {
    // yybegin(YYINITIAL);
	System.out.println("Comment completed!") ;
	nestedCommentCount = 0 ;
	yybegin(YYINITIAL) ;
  }
} 	
<YYINITIAL> . {System.out.println("ERROR: Not recognized.") ;}




