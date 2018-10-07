import java.util.* ;
import java.io.BufferedReader ;
import java.io.InputStreamReader ;

public class ExtendToPalindrome2
{
	private static final boolean debug = false ;
	public static void main(String args[]) throws Exception
	{
		BufferedReader bro = new BufferedReader(new InputStreamReader(System.in)) ;
		for(String in = bro.readLine(); !in.equals("");in = bro.readLine())
		{
			System.out.println(solve(in)) ;
		}
	}
	static String solve(String S)
	{
		int x = S.length() ;
		char[] mod = (reverse(S)+"~"+S).toCharArray() ;
		int[] b = kmpPreprocess(mod) ;
		for(int i = b[mod.length-1]+1;i<x;i++)
			S+=String.valueOf(mod[i]) ;
		return S ;
	}
	static int[] kmpPreprocess(char[] P)
	{
		int[] b = new int[P.length+1] ;
		int i=0,j=-1 ;
		b[0] = -1 ;
		while(i<P.length)
		{
			while(j>=0 && P[i]!=P[j]) j = b[j] ;
			i++;j++ ;
			b[i] = j ;
		}
		return b ;
			
	}
static String reverse(String input)
{
    char[] in = input.toCharArray();
    int begin=0;
    int end=in.length-1;
    char temp;
    while(end>begin)
	{
        temp = in[begin];
        in[begin]=in[end];
        in[end] = temp;
        end--;
        begin++;
    }
    return new String(in);
}
}