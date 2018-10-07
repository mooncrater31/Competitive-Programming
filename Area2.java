import java.util.* ;
import java.io.BufferedReader ;
import java.io.InputStreamReader ;

public class Area2
{
	private static final boolean debug = true ;
	public static void main(String args[]) throws Exception
	{
		BufferedReader bro = new BufferedReader(new InputStreamReader(System.in)) ;
		int T = Integer.parseInt(bro.readLine()) ;
		for(int t=0;t<T;t++)
		{
			String[] S = bro.readLine().split(" ") ;
			int N = Integer.parseInt(S[0]) ;
			int M = Integer.parseInt(S[1]) ;
			int K = Integer.parseInt(S[2]) ;
			int[][] A = new int[N][M] ;
			for(int i=0;i<N;i++)
			{
				String[] S1 = bro.readLine().split(" ") ;
				for(int j=0;j<M;j++)
				{
					A[i][j] = Integer.parseInt(S1[j]) ;
				}
			}
			long[] res = solve(A,K) ;
			System.out.println("Case #"+(t+1)+": "+res[0]+" "+res[1]) ;
		}
	}
	static void printMatrix(int[][] M)
	{
		for(int[] I:M)
		{
			System.out.println(Arrays.toString(I)) ;
		}
	}
	static long[] solve(int[][] M,int budget)
	{
		long minSum = Long.MAX_VALUE;
		int maxA = 0 ;
        int left = 0;
        int top = 0;
        int right = 0;
        int bottom = 0;
		int rows = M.length,cols = M[0].length ;
		for(int i = 0 ;i<cols;i++)
		{
			int[] temp = new int[rows] ;
			for(int j=i;j<cols;j++)
			{
				for(int k=0;k<rows;k++)
				{
					temp[k]+=M[k][j] ;
				}
				long[] currResult = kadane(temp) ;
				minSum = currResult[0] ;
				
				long area = (j-i)*(currResult[2]-currResult[1]) ;
				if(budget>=currResult[0])
				{
					if(area>maxA)
					{
						area = maxA ;
						minSum = currResult[0] ;
						if(debug) System.out.println("left :"+i+" top :"+currResult[1]+" right :"+j+" bottom :"+currResult[2]) ;
					}
					else if(area==maxA && currResult[0]<minSum)
					{
						minSum = currResult[0] ;
					}
				}
			}
		}
		long[] ret = {maxA,minSum} ;
		return ret ; 
	}
	static long[] kadane(int[] A)
	{
		long[] result = {Long.MAX_VALUE,-1,-1} ;
		long minSum = Long.MAX_VALUE ;
		long sum = 0 ;
		int count = 0,start = 0,end = 0 ;
		for(int i=0;i<A.length;i++)
		{
			count++ ;
			sum+=A[i] ;
			if(minSum>sum)
			{
				minSum = sum ;
				end = i+1 ;
				start = end-count ;
			}
			if(sum<0)
			{
				sum = 0 ;
				count = 0 ;
			}
			
			
		}
		result[0] = minSum ;
		result[1] = start ;
		result[2] = end ;
		return result ;
	}
}