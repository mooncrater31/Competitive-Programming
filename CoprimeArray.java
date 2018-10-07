/*package whatever //do not write package name here */

import java.util.*;
import java.lang.*;
import java.io.*;

class CoprimeArray
{
	private static final boolean debug = true ;
	public static void main (String[] args) throws Exception
	{
		BufferedReader bro = new BufferedReader(new InputStreamReader(System.in)) ;
		int T = Integer.parseInt(bro.readLine()) ;
		for(int t=0;t<T;t++)
		{
		    int N = Integer.parseInt(bro.readLine()) ;
		    int[] A = new int[N] ;
		    String[] S = bro.readLine().split(" ") ;
		    for(int i=0;i<A.length;i++)
		    {
		        A[i] = Integer.parseInt(S[i]) ;
		    }
		    int count = 0 ;
		    for(int i=0;i<N-1;i++)
		    {
				int val = gcd(A[i],A[i+1]) ;
				if(debug)
				{
					System.out.println("gcd("+A[i]+","+A[i+1]+") is :"+val) ;
				}
		        if(val!=1)
		            count++ ;
		    }
		    System.out.println(count) ;
		}
	}
	static int gcd(int a,int b)
	{
	    int temp = a ;
	    a = a<b?a:b ;
	    b = temp==a?b:temp ;
	    while(a!=0)
	    {
	        b = b%a ;
	        int temp2 = a ;
	        a = b ;
	        b = temp2 ;
	    }
	    return b ;
	}
}