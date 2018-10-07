import java.util.* ;
import java.io.BufferedReader ;
import java.io.InputStreamReader ;

public class EvenSubArrays
{
	public static void main(String args[]) throws Exception
	{
		BufferedReader bro = new BufferedReader(new InputStreamReader(System.in)) ;
		int T = Integer.parseInt(bro.readLine()) ;
		for(int t=0;t<T;t++)
		{
			int N = Integer.parseInt(bro.readLine()) ;
			int[] A = new int[N] ;
			String[] S = bro.readLine().split(" ") ;
			for(int i=0;i<A.length;i++)
			{
				A[i] = Integer.parseInt(S[i]) ;
			}
			System.out.println(solve(A)) ;
		}
	}
	static int solve(int[] A)
	{
		int[][] DP = new int[A.length][A.length] ;
		int evens = 0 ;
		for(int i=0;i<A.length;i++)
		{
			if(A[i]%2==0) evens++ ;
			DP[i][i] = A[i] ;
		}
		for(int d = 1 ; d<A.length;d++)
		{
			for(int i=0;i+d<A.length;i++)
			{
				DP[i][i+d] = DP[i][i+d-1]+DP[i+d][i+d] ;
				if(DP[i][i+d]%2==0) evens++ ;
			}
		}
		return evens ;
	}
}