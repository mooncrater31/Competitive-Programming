import java.util.* ;
import java.io.BufferedReader ;
import java.io.InputStreamReader ;

public class CountTheSubarrays
{
	public static void main(String args[]) throws Exception
	{
		BufferedReader bro = new BufferedReader(new InputStreamReader(System.in)) ;
		int T = Integer.parseInt(bro.readLine()) ;
		for(int t=0;t<T;t++)
		{
			String[] S = bro.readLine().split(" ") ;
			int N = Integer.parseInt(S[0]) ;
			long K = Long.parseLong(S[1]) ;
			int[] A = new int[N] ;
			S = bro.readLine().split(" ") ;
			for(int i=0;i<N;i++)
			{
				A[i] = Integer.parseInt(S[i]) ;
			}
			System.out.println(solve(A,K)) ;
		}
	}
	static long solve(int[] A,long k)
	{
		int start = 0,end = 0 ;
		long p = A[0],sum = 0 ;
		
		while(true)
		{
			if(p<k)
			{
				sum+=1+(end-start) ;
				if(end==A.length-1)
					break ;
				p *= A[++end] ;
			}
			else//p>=k
			{
				p/=A[start++] ;
			}
		}
		return sum ;
	}
	
}