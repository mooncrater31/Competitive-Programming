import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;
class BinoSum
{
 static long theSum(int n,int p)
 {
	// if(n<p)
	// {
		// System.out.println("pfft!") ;
		// return -1 ;
	// }
		
	if(p==0)
		return 1; 
	long[] table = new long[p+1] ;
	table[0] = 1 ;
	long sum = 1 ;
	for(int i=1;i<p+1;i++)
	{
		table[i] = table[i-1]*(n-i+1)/i ;
		sum = sum+table[i] ;
		//System.out.println("table["+i+"] = "+table[i]) ;
	}
	return sum ;
 }
 public static void main(String args[])
 {
	 Scanner in  = new Scanner(System.in) ;
	 /*int tests = in.nextInt() ;
	 long[] ans = new long[tests] ;
	 for(int i=0;i<tests;i++)
	 {
		 int n = in.nextInt() ;
		 int p = in.nextInt() ;
		 ans[i] = (theSum(n,p)%1000000007) ;
		 
	 }
	 for(int i=0;i<ans.length;i++)
	 {
		 System.out.println(ans[i]) ;
	 } */
	 // tester-1 tests theSum()
	 System.out.println(theSum(in.nextInt(),in.nextInt())) ;
	 
	 
 }
}
