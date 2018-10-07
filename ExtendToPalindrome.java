import java.util.* ;
import java.io.BufferedReader ;
import java.io.InputStreamReader ;

public class ExtendToPalindrome
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
		if(isPalindrome(S))
		{
			if(debug) System.out.println("It's a palindrome!") ;
			return S ;
		}
		List<Integer> L = positionsIn(S.substring(0,S.length()-1),S.charAt(S.length()-1));
		if(debug)
		{
			System.out.println("L.size() :"+L.toString()) ;
		}
		int ind = -1;
		for(int i=0;i<L.size();i++)
		{
			if(isPalindrome(S.substring(L.get(i),S.length())))
			{
				ind = L.get(i) ;
				break ;
			}
		}
		if(ind==-1)
		{
			S+=reverse(S.substring(0,S.length()-1)) ;
		}
		else
		{
			String first = S.substring(0,ind) ;
			if(debug) System.out.println("ind :"+ind+" first :"+first) ;
			S+=reverse(first) ;
			
		}
		return S ;
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
	static List<Integer> positionsIn(String S,char c)
	{
		List<Integer> L = new ArrayList<Integer>() ;
		for(int i=0;i<S.length();i++)
		{
			if(S.charAt(i)==c)
				L.add(i) ;
		}
		return L ;
	}
	static boolean isPalindrome(String S)
	{
		for(int i=0;i<S.length()/2;i++)
		{
			if(S.charAt(i)!=S.charAt(S.length()-i-1))
				return false;
		}
		return true ;
	}
}