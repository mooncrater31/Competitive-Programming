/*package whatever //do not write package name here */

import java.util.*;
import java.io.*;

class PairArray {
	public static void main (String[] args) throws Exception
	{
		BufferedReader bro = new BufferedReader(new InputStreamReader(System.in)) ;
		int T = Integer.parseInt(bro.readLine()) ;
		for(int t=0;t<T;t++)
		{
		    int N = Integer.parseInt(bro.readLine()) ;
		    int[] A = new int[N] ;
		    String[] S = bro.readLine().split(" ") ;
			int twos = 0 ;
		    for(int i=0;i<N;i++)
		    {
		        A[i] = Integer.parseInt(S[i]) ;
				if(A[i]==2)
					twos++ ;
		    }
		    Arrays.sort(A) ;
		    int ind = N-1;
		    for(int i=0;i<N;i++)
		        if(A[i]!=1)
		        {
		            ind = i ;
		            break ;
		        }
		    int val = N-ind ;
		    System.out.println((val*(val-1)/2-twos*(twos-1)/2)) ;
		}
	}
}