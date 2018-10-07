import java.util.* ;
import java.io.BufferedReader ;
import java.io.InputStreamReader ;

public class AveAndSubstrings
{
	public static void main(String args[]) throws Exception
	{
		BufferedReader bro = new BufferedReader(new InputStreamReader(System.in)) ;
		int T = Integer.parseInt(bro.readLine()) ;
		for(int t=0;t<T;t++)
		{
			String S = bro.readLine() ;
			int n = S.length() ;
			int[] b = kmpPreprocess(S.toCharArray()) ;
			// System.out.println(((b.length-1)-(b[b.length-1]))) ;
			int val = n-b[n] ;
			if(val<=n/2)
			{
				System.out.println(val) ;
			}
			else
			{
				System.out.println(n) ;
			}
		}
	}
	static int[] kmpPreprocess(char[] P)
	{
		int[] b = new int[P.length+1] ;
		int i=0,j=-1;
		b[0]=-1 ;
		while(i<P.length)
		{
			while(j>=0&&P[i]!=P[j])j = b[j] ;
			i++;j++;
			b[i] = j ;
		}
		return b ;
	}
	
		
}