/*package whatever //do not write package name here */

import java.util.*;

import java.io.BufferedReader;
import java.io.InputStreamReader ;

public class CountOfSmallerElements
{
	public static void main (String[] args) throws Exception
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
			int val = Integer.parseInt(bro.readLine()) ;
			System.out.println(solve(A,val)) ;
		}
	}
	static int solve(int[] A,int val)
	{
	    int pos = binarySearch(A,val) ;
	    int temp = pos ;
	    while(A[temp+1]==A[temp])
	        temp++ ;
	    return temp+1 ;     
	}
	static int binarySearch(int[] A,int val)
	{
	    int m=0,n=A.length-1 ;
		int mid = 0 ;
	    while(m<n)
	    {
	        mid = (m+n)/2 ;
	        if(A[mid]==val )
	            return mid ;
	        if(A[mid]<val)     
	        {
	            m = mid+1 ;
	        }
	        else n = mid-1 ;
	    }
	    return mid ;
	}
	
}