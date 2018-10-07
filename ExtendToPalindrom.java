import java.util.* ;
import java.io.BufferedReader ;
import java.io.InputStreamReader ;

public class ExtendToPalindrome
{
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
			return S ;
		List<Integer> L = positionsIn(S,S.charAt(S.length()-1)); 
		int ind = -1;
		for(int i=0;i<L.size();i++)
		{
			if(isPalindrome(S.substring(L.get(i),S.length())))
			{
				ind = i ;
				break ;
			}
		}
		if(i==-1)
			S+=reverse(S) ;
		else
		{
			String first = S.substring(0,ind) ;
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