import java.util.* ;
import java.io.BufferedReader ;
import java.io.InputStreamReader ;

public class Cipher
{
	public static void main(String args[]) throws Exception
	{
		BufferedReader bro = new BufferedReader(new InputStreamReader(System.in)) ;
		int 
	}
	static char[] solve(char[] S,int n)
	{
		if(n%2!=0)
		{
			char temp = S[1] ;
			S[1] = S[4] ;
			S[4] = temp ;
			temp = S[5] ;
			S[5] = S[7] ;
			S[7] = temp ;
			temp = S[8] ;
			S[8] = S[9] ;
			S[9] = temp ;
		}
		if(n%3==1)
		{
			char temp = S[0] ;
			S[0] = S[6] ;
			S[6] = S[3] ;
			S[3] = temp ;
		}
		else if(n%3==2)
		{
			char temp = S[3] ;
			S[3] = S[6] ;
			S[6] = S[0] ;
			S[0] = temp ;
		}
		return S ;
	}
}