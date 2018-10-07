import java.util.*;
import java.io.BufferedReader ;
import java.io.InputStreamReader ;

public class EqualZerosAndOnes
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
			System.out.println(solve(A)) ;
		}
	}
	static int solve(int[] A)
	{
		int count = 0 ;
		for(int i=0;i<A.length;i++)
		{
			if(A[i]==0) A[i] = -1 ;
		}
		for(int i=0;i<A.length-1;i++)
		{
			
			int sum = A[i]+A[i+1] ;
			if(sum==0) count++ ;
			for(int j=i+2;j<A.length-1;j+=2)
			{
				sum+=A[j]+A[j+1] ;
				if(sum==0) count++ ;
			}
			
		}
		return count ;
	
	}
}