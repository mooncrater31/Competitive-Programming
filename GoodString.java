import java.util.* ;
import java.io.BufferedReader ;
import java.io.InputStreamReader ;

public class GoodString
{
	public static void main(String args[]) throws Exception
	{
		BufferedReader bro = new BufferedReader(new InputStreamReader(System.in)) ;
		System.out.println(solve(bro.readLine())) ;
	}
	static int solve(String S)
	{
		S = S.replaceAll("[aeiou]","a") ;
		int maxLength = 0,currLen=0 ;
		for(int i=0;i<S.length();i++)
		{
			
			if(S.charAt(i)=='a')
			{
				currLen++ ;
				maxLength = maxLength<currLen?currLen:maxLength ;
				
			}
			else
			{
				currLen = 0;
			}
		}
		return maxLength ;
	}
}