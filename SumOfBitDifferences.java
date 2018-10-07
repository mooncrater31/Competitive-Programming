import java.util.* ;
import java.io.BufferedReader ;
import java.io.InputStreamReader ;

public class SumOfBitDifferences
{
	private static final boolean debug = false ;
	public static void main(String args[]) throws Exception
	{
		
		BufferedReader bro = new BufferedReader(new InputStreamReader(System.in)) ;
		int T = Integer.parseInt(bro.readLine()) ;
		for(int t=0;t<T;t++)
		{
			int N = Integer.parseInt(bro.readLine()) ;
			int[] A = new int[N] ;
			String[] S = bro.readLine().split(" "); 
			for(int i=0;i<N;i++)
			{
				A[i] = Integer.parseInt(S[i]) ;
			}
			int sum = 0 ;
			for(int i=0;i<N;i++)
			{
				for(int j=i+1;j<N;j++)
				{
					int val = xorSum(A[i],A[j]) ;
					if(debug) System.out.println("XORSUM between : "+A[i]+" and "+A[j]+" = "+val) ;
					sum+=val ;
				}
			}
			System.out.println(2*sum) ;
		}
	}
	static int xorSum(int a,int b)
	{
		return Integer.bitCount(a^b) ;
	}
}