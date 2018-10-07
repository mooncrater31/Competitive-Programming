import java.util.* ;
import java.io.BufferedReader ;
import java.io.InputStreamReader ;

public class SubarraysWithMaxValues2
{
	private static final boolean debug = false ;
	public static void main(String args[]) throws Exception
	{
		BufferedReader bro = new BufferedReader(new InputStreamReader(System.in)) ;
		int T = Integer.parseInt(bro.readLine()) ;
		for(int t=0;t<T;t++)
		{
			String[] S = bro.readLine().split(" ") ;
			int n = Integer.parseInt(S[0]) ;
			int l = Integer.parseInt(S[1]) ;
			int r = Integer.parseInt(S[2]) ;
			int[] A = new int[n] ;
			S = bro.readLine().split(" ") ;
			for(int i=0;i<n;i++)
			{
				A[i] = Integer.parseInt(S[i]) ;
			}
			System.out.println(solve(A,l,r)) ;
		}
	}
	static int numberOfSubarrays(int N)
	{
		return N*(N+1)/2 ;
	}
	static int solve(int[] A,int L,int R)
	{
		int res = 0,inc = 0,exc = 0 ;
		for(int i=0;i<A.length;i++)
		{
			if(A[i]>R)
			{
				res+=numberOfSubarrays(inc)-numberOfSubarrays(exc) ;
				inc = 0 ;
				exc = 0 ;
			}
			else if(A[i]<L)
			{
				inc++ ;
				exc++ ;
			}
			else
			{
				res-=numberOfSubarrays(exc) ;
				exc = 0 ;
				inc++ ;
			}
		}
		res+=numberOfSubarrays(inc)-numberOfSubarrays(exc) ;
		return res ;
	}
}