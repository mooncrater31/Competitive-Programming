//23/09/17
import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;
class LongestPalindrome
{
	static void print_array(int[] A)
	{
		for(int i=0;i<A.length;i++)
		{
			System.out.print(A[i]+" ") ;
		}
		System.out.println() ;
	}
	static boolean isPrime(int n)
	{
		if(n==1)
			return false ;
		if(n==2)
			return true ;
		for(int i=2 ;i<n;i++)
		{
			if(n%i==0)
				return false ;
		}
		return true ;
	}
	public static void main(String args[])
	{
		Scanner in = new Scanner(System.in) ;
		int testCases = in.nextInt() ;
		String s[] = new String[testCases] ;
		for(int alpha=0;alpha<testCases;alpha++)
		{
			s[alpha] = in.next() ;
		}
		int[] max = new int[testCases];
		Arrays.fill(max,1) ;
		for(int k=0;k<testCases;k++)
		{
			int len = s[k].length() ;
			boolean[][] M = new boolean[len][len] ;
			
			for(int i=0;i<len;i++)//initialization for length=1
			{
				M[i][i] = true ;
			}
			for(int i=0;i<len-1;i++)//initialization for length=2
			{
				if(s[k].charAt(i)==s[k].charAt(i+1))
				{
					M[i][i+1] = true ;
					//System.out.println("M["+i+"]["+(i+1)+"] = "+M[i][i+1]) ;
					max[k]=2 ;
				}
			}
			for(int d=2;d<len;d++)//for subtrings of length >2
			{
				for(int i=0;i<len-d;i++)
				{
					int j = i+d ; 
					if(M[i+1][j-1] && s[k].charAt(i)==s[k].charAt(j))
					{
						M[i][j]=true ;
						max[k] = d+1 ;
					}
				}
			}
			
			
		}
		//print_array(max	);
		for(int k=0;k<testCases;k++)
		{
			
			if(isPrime(max[k]))
				System.out.println("PRIME") ;
			else
				System.out.println("NOT PRIME") ;
		}
		
	}
}