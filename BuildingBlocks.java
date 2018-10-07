import java.util.* ;
import java.io.BufferedReader ;
import java.io.InputStreamReader ;
/*
Sort.
Find the first element that differs from it's previous element.(prev and next)
You have the number of columns that have the same value.(k)
Find the blocks needed to make these columns = to the next unequal element. (k*(next-prev)) 
Check if that much blocks are available or not.
If yes, allocate that, and repeat the cycle(with major optimizations.)
If no, then prev+(avail/k*(next-prev)) is the answer.

*/

public class BuildingBlocks
{
	public static void main(String args[]) throws Exception 
	{
		BufferedReader bro = new BufferedReader(new InputStreamReader(System.in)) ;
		int T = Integer.parseInt(bro.readLine()) ;
		for(int t=0;t<T;t++)
		{
			String[] S = bro.readLine().split(" ") ;
			int N = Integer.parseInt(S[0]) ;
			int K = Integer.parseInt(S[1]) ;
			int[] A = new int[N] ;
			S = bro.readLine().split(" ") ;
			for(int i=0;i<N;i++)
			{
				A[i] = Integer.parseInt(S[i]) ;
			}				
			System.out.println(solve(A,K)) ;
			
		}
	}
	static int solve(int[] A,int blocks)
	{
		Arrays.sort(A) ;
		// int init = A[0] ;
		int blocks_avail = blocks ;
		for(int i=1;i<A.length ;i++)
		{
			if(A[i]!=A[i-1])
			{//Different 
				int diff = A[i]-A[i-1] ;
				int blocks_needed = diff*i ;
				if(blocks_needed<blocks_avail)
				{
					for(int j=0;j<i;j++)
					{
						A[j] = A[i] ;
					}
					blocks_avail = blocks_avail-blocks_needed ;
				}
				else
				{
					return A[0]+(blocks_avail/i) ;
				}
			}
		}
		return A[0]+(blocks_avail/A.length) ;
	}
}