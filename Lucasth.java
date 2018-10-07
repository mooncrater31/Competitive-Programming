import java.util.* ;
import java.io.BufferedReader ;
import java.io.InputStreamReader ;

public class Lucasth
{
	private static final boolean debug = false ;
	public static void main(String args[]) throws Exception
	{
		BufferedReader bro = new BufferedReader(new InputStreamReader(System.in)) ;
		int T = Integer.parseInt(bro.readLine()) ;
		for(int t=0;t<T;t++)
		{
				
			String S = bro.readLine() ;
			String[] s = S.split(" ") ;
			int n = Integer.parseInt(s[0]),p = Integer.parseInt(s[1]) ;
			long[][] M = new long[n+1][n+1] ;
			
			// M[1][1] = 1 ;
			long mod = (long)1e9+7 ;
			for(int i=0;i<n+1;i++)
			{
				M[i][0] = 1 ;//Including M[0][0] = 1, as it'll help to generate M[1][1] 
			}
			// for(int i=2;i<n+1;i++)
			// {
				// M[i][0] = 1 ;//Given in the question
				// M[1][i] = (M[1][i-1]+i)%p ;//Experimental
			// }
			// for(int i=2;i<n+1;i++)
			// {
				// for(int j=2;j<=i;j++)
				// {
					// M[i][j] = (M[i-1][j-1]*i + M[i-1][j])%p ;//Experimental
				// }
			// }
			for(int i=1;i<n+1;i++)
			{
				for(int j=1;j<n+1;j++)
				{
					// M[i][j] = (M[i-1][j-1]*i + M[i-1][j]) ;
					M[i][j] = (M[i-1][j-1]*i + M[i-1][j])%p ;
				}
			}
			int count = 0 ;
			for(int j=0;j<n+1;j++)
			{
				if(debug)
					System.out.print(M[n][j]+" ") ;
				if(M[n][j]%p!=0)
					count++ ;
			}
			System.out.println(count%mod) ;
		}
	}
}