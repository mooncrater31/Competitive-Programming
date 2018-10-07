import java.util.* ;
import java.io.BufferedReader ;
import java.io.InputStreamReader ;

class MonkAndMonster
{
	private static final boolean debug = true ;
	public static void main(String args[]) throws Exception
	{
		BufferedReader bro = new BufferedReader(new InputStreamReader(System.in)) ;
		int T = Integer.parseInt(bro.readLine()) ;
		for(int t=0;t<T;t++)
		{
			String P = bro.readLine() ;
			String Q = bro.readLine() ;
			int N = Integer.parseInt(bro.readLine());
			int[] cost = new int[N] ;
			String[] S = bro.readLine().split(" ") ;
			for(int i=0;i<S.length;i++)
			{
				cost[i] = Integer.parseInt(S[i]) ;
			}
			System.out.println(solve(P,Q,cost)) ;
		}
	}
	static int solve(String P,String Q,int[] cost)
	{
		
		int n1 = Q.length() ;//Q is the pattern 
		boolean[] canDo = kmpSearch(Q.toCharArray(),P.toCharArray(),kmpPreprocess(Q.toCharArray())) ;
		int n = P.length() ;
		int[] dp = new int[P.length()+1] ;
		for(int i=n-1;i>=0;i--)
		{
			dp[i] = dp[i+1] ;
			if(canDo[i])
				dp[i] = Math.max(dp[i],cost[i]+dp[i+n1]) ;
		}
		return dp[0] ;
		
	}
	static int[] kmpPreprocess(char[] P)
	{
		int i=0,j=-1;
		int[] b = new int[P.length+1] ;
		b[0] = -1 ;
		int m = P.length ;
		while(i<m)
		{
			while(j>=0 && P[j]!=P[i]) j = b[j] ;
			i++;j++;
			b[i] = j ;
		}
		return b ;
	}
	static boolean[] kmpSearch(char[] P,char[] T,int[] b)
	{
		int i=0,j=0 ;
		
		int n = T.length ;
		boolean[] canDo = new boolean[n] ;
		int m = P.length ;
		while(i<n)
		{
			while(j>=0 && T[i]!=P[j]) j = b[j] ;
			i++;j++;
			if(j==m)
			{
				canDo[i-j] = true ;
				j = b[j] ;
			}
		}
		return canDo ;
	}
}
