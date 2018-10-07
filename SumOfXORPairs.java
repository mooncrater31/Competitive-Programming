import java.util.* ;
import java.io.BufferedReader ;
import java.io.InputStreamReader ;

public class SumOfXORPairs
{
	public static void main(String args[]) throws Exception
	{
		BufferedReader bro = new BufferedReader(new InputStreamReader(System.in)) ;
		int T = Integer.parseInt(bro.readLine()) ;
		for(int t=0;t<T;t++)
		{
			int n = Integer.parseInt(bro.readLine()) ;
			int[] A = new int[n] ;
			String[] S = bro.readLine().split(" ") ;
			for(int i=0;i<n;i++)
			{
				A[i] = Integer.parseInt(S[i]) ;
			}
			System.out.println(solve(A)) ;
		}
	}
	static int solve(int[] A)
	{
		int minLeadingZeros = Integer.MAX_VALUE ;
		for(int i=0;i<A.length;i++)
		{
			int val = Integer.numberOfLeadingZeros(A[i]) ;
			if(val<minLeadingZeros) minLeadingZeros = val ;
		}
		int sum = 0 ;
		for(int i=0;i<32-minLeadingZeros;i++)
		{
			int zeros = 0 ,ones = 0 ;
			for(int j=0;j<A.length;j++)
			{
				if((A[j]&1)==0) zeros++;
				else ones++ ;
				A[j] = A[j]>>1 ;
			}
			sum+=(zeros*ones)*(int)Math.pow(2,i) ;
		}
		return sum ;
	}
}