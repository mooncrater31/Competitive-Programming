/*package whatever //do not write package name here */

import java.util.*;
import java.lang.*;
import java.io.BufferedReader;
import java.io.InputStreamReader ;

class PathsToReachOrigin {
	public static void main (String[] args) throws Exception
	{
		BufferedReader bro = new BufferedReader(new InputStreamReader(System.in)) ;
		int T = Integer.parseInt(bro.readLine()) ;
		for(int t=0;t<T;t++)
		{
		    String[] S = bro.readLine().split(" ") ;
		    int a = Integer.parseInt(S[0]) ;
		    int b = Integer.parseInt(S[1]) ;
		    System.out.println((factorial(a+b)/(factorial(a)*factorial(b)))) ;
		}
	}
	static long factorial(int n)
	{
	   if(n==0 || n==1)
            return 1 ;
        long val = 1 ;    
       for(int i=1;i<=n;i++)
            val= val*i ;
        return val ;    
	}
}