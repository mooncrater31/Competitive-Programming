import java.util.* ;
import java.io.BufferedReader ;
import java.io.InputStreamReader ;

public class LongestSub
{
	public static void main(String args[]) throws Exception
	{
		BufferedReader bro = new BufferedReader(new InputStreamReader(System.in)) ;
		int T = Integer.parseInt(bro.readLine()) ;
		for(int t=0;t<T;t++)
		{
			String[] S = bro.readLine().split(" ") ;
			int N = Integer.parseInt(S[0]) ;
			int k = Integer.parseInt(S[1]) ;
			S = bro.readLine().split(" ") ;
			int[] A = new int[N] ;
			for(int i=0;i<N;i++)
			{
				A[i] = Integer.parseInt(S[i]) ;
			}
			System.out.println(solve(A,k)) ;
			
		}
		
	}
	static int solve(int[] A,int k)
	{
		int max_length = 0 ;
		int[][] DP = new int[A.length][A.length] ;
		for(int i=0;i<A.length;i++)
		{
			DP[i][i] = A[i] ;
			if(DP[i][i]==k) max_length = 1 ;
		}
		for(int d = 1;d<A.length;d++)
		{
			for(int i=0;i+d<A.length;i++)
			{
				DP[i][i+d] = DP[i][i+d-1]+DP[i+d][i+d] ;
				if(DP[i][i+d]==k) max_length = d+1 ;
			}
		}
		return max_length ;
	}
}