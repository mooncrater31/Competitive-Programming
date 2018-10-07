class example2
{
	public static void main(String argv[]) throws java.io.IOException
	{
		Yylex yy = new Yylex(System.in) ;
		Integer t ;
		while((t = yy.yylex())!=null)
			System.out.println(t) ;
	}
}
%%
DIGIT = [0-9]
ALPHA = [a-zA-Z]
notAB = [^\\]
opening = "\(\*"
closing = "\*\)"
STRINGTEXT = \(\*(([^\(*]|\(($|[^*])|\*($|[^\(]))*)\*\)
Second = \(\*([^\(*]|\(($|[^*]))*\*\)

%type Integer
%%
<YYINITIAL> 5$ {System.out.println("A dollar.") ;}
