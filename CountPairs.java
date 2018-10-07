/*package whatever //do not write package name here */

import java.util.*;

import java.io.BufferedReader;
import java.io.InputStreamReader ;
public class CountPairs
{
	public static void main (String[] args) throws Exception
	{
	    BufferedReader bro = new BufferedReader(new InputStreamReader(System.in)) ;
	    int T = Integer.parseInt(bro.readLine()) ;
	    for(int t=0;t<T;t++)
	    {
	        int N = Integer.parseInt(bro.readLine()) ;
	        String[] S = bro.readLine().split(" ") ;
	        int[] A = new int[N] ;
	        for(int i=0;i<N;i++)
	        {
	            A[i] = Integer.parseInt(S[i]) ;
	        }
	        System.out.println(solve(A)) ;
	    }
		
	}
	static int solve(int[] A)
	{
	    for(int i=0;i<A.length;i++)
	    {
	        A[i] = A[i]*i ;
	    }
	    int count=0;
	    for(int i=0;i<A.length;i++)
	    {
	        for(int j=i+1;j<A.length;j++)
	        {
	            if(A[i]>A[j]) count++ ;
	        }
	    }
	    return count ;
	}
}