//23/09/17
import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;
class PrimePalindrome
{
	static void print_array(int[] A)
	{
		for(int i=0;i<A.length;i++)
		{
			System.out.print(A[i]+" ") ;
		}
		System.out.println() ;
	}
	
	public static void main(String args[])
	{
		
		Scanner in = new Scanner(System.in) ;
		
		
		String s = in.next() ;
		int sum = s.length() ;
		
			int len = s.length() ;
			boolean[][] M = new boolean[len][len] ;
			
			for(int i=0;i<len;i++)//initialization for length=1
			{
				M[i][i] = true ;
			}
			for(int i=0;i<len-1;i++)//initialization for length=2
			{
				if(s.charAt(i)==s.charAt(i+1))
				{
					M[i][i+1] = true ;
					//System.out.println("M["+i+"]["+(i+1)+"] = "+M[i][i+1]) ;
					sum++ ;
					
				}
			}
			for(int d=2;d<len;d++)//for subtrings of length >2
			{
				for(int i=0;i<len-d;i++)
				{
					int j = i+d ; 
					if(M[i+1][j-1] && s.charAt(i)==s.charAt(j))
					{
						M[i][j]=true ;
						sum++ ;
					}
				}
			}
			
			
		
		//print_array(max	);
		System.out.println(sum) ;
		
	}
}