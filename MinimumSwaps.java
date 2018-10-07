import java.util.* ;
import java.io.BufferedReader ;
import java.io.InputStreamReader ;

public class MinimumSwaps
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
			for(int i=0;i<N;i++)
			{
				A[i] = Integer.parseInt(S[i]) ;
			}
			int K = Integer.parseInt(bro.readLine()) ;
			System.out.println(swaps(A,K)) ;
		}
	}
	static int swaps(int[] A,int K)
	{
		int win_size = 0 ;
		for(int i=0;i<A.length;i++)
		{
			if(A[i]<=K) win_size++ ;
		}
		int ini=0,fin=win_size-1,density=0,max_density=0 ;
		for(int i=0;i<win_size;i++)
		{
			if(A[i]<=K) density++ ;
		}
		max_density = density ;
		
		while(++fin<A.length)
		{
			if(A[fin]<=K) 
			{
				density++ ;
			}
			if(A[ini++]<=K)
			{
				density-- ;
			}
			if(density>max_density)
			{
				max_density = density ;
				// md_i = ini ;
				// md_f = fin ;
			}
		}
		return (win_size-max_density) ;
	}
}