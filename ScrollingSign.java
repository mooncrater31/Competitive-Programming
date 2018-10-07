import java.util.* ;
import java.io.BufferedReader ;
import java.io.InputStreamReader ;

public class ScrollingSign
{
	public static void main(String args[]) throws Exception
	{
		BufferedReader bro = new BufferedReader(new InputStreamReader(System.in)) ;
		int T = Integer.parseInt(bro.readLine()) ;
		for(int t=0;t<T;t++)
		{
			String[] S = bro.readLine().split(" ") ;
			int k = Integer.parseInt(S[0]) ;
			int m = Integer.parseInt(S[1]) ;
			
			String[] str = new String[m];
			for(int i=0;i<m;i++)
			{
				str[i] = bro.readLine() ;
			}
			System.out.println(k+solve(str)) ;
			
			
		}
	}
	static int solve(String[] S)
	{
		int totalChars = 0 ;
		for(int i=0;i<S.length-1;i++)
		{
			String temp = S[i+1]+"~"+S[i] ;
			int[] b = kmpPreprocess(temp.toCharArray()) ;
			int val = S[i].length()-b[temp.length() ] ;
			// if(debug) System.out.println("val :"+val) ;
			totalChars+=val ;
		}
		return totalChars ;
	}
	static int[] kmpPreprocess(char[] P)
	{
		int[] b = new int[P.length+1] ;
		int i=0,j=-1 ;
		b[0] = -1 ;
		while(i<P.length)
		{
			while(j>=0 && P[i]!=P[j]) j = b[j] ;
			i++;j++;
			b[i] = j ;
		}
		return b ;
	}
	
}