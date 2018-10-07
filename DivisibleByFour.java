import java.util.* ;
import java.io.BufferedReader ;
import java.io.InputStreamReader ;

public class DivisibleByFour
{
	public static void main(String args[]) throws Exception
	{
		BufferedReader bro  = new BufferedReader(new InputStreamReader(System.in)) ;
		int T = Integer.parseInt(bro.readLine()) ;
		for(int t=0;t<T;t++)
		{
			int N = Integer.parseInt(bro.readLine()) ;
			int[] A = new int[N] ;
			String[] S = bro.readLine().split(" ") ;
			for(int i=0;i<N;i++)
			{
				A[i] = Integer.parseInt(S[i]) ;
			}
			System.out.println(solve(A)) ;
		}
	}
	static int solve(int[] A)
	{
		int[] freq = new int[4] ;
		for(int i=0;i<A.length;i++)
		{
			int j = A[i]%4 ;
			freq[j]++ ;
		}
		return ((freq[0]*(freq[0]-1)/2 + freq[2]*(freq[2]-1)/2 + freq[1]*freq[3])) ;
	}
}