import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;
public   class Balls{
	static void print_array(int[] A)
	{
		for(int i=0;i<A.length;i++)
		{
			System.out.print(A[i]+" ") ;
		}
		System.out.println() ;
	}
	static void matrix_printer(int[][] A)
	{
		for(int i=0;i<A.length;i++)
		{
			System.out.println() ;
			for(int j=0;j<A[0].length;j++)
			{
				System.out.print(A[i][j]+" ") ;
			}
		}
		System.out.println() ;

	}
	static int[] consecutive_removal(int[] A)
	{
		int first = -1 ;
		boolean consec = false ;
		for(int i=0;i<A.length-1;i++)
		{
			if(A[i]==A[i+1])
			{
				first = i ;
				consec = true ;
				break ;
			}
			
		}
		if(consec)
		{
		int[] A2 = new int[A.length-2] ;
		int j=0 ;
		for(int i=0;i<A.length;i++)
		{
			if(i!=first && i!=(first+1))
			{
				A2[j++] = A[i] ;
			}
		}
		return consecutive_removal(A2) ;
		}
		else
			return A ;
		
		
	}
	public static void main(String args[])
	{
		Scanner in = new Scanner(System.in) ;
		int n = in.nextInt() ;
		int[] A = new int[n] ;
		for(int i=0;i<n;i++)
		{
			A[i] = in.nextInt() ;
		}
		A = consecutive_removal(A) ;
		//System.out.println("New Array:" );
		//print_array(A) ;
		n = A.length ;
		if(n!=0)
		{
			int[][] M = new int[n][n] ;
			for(int i=0;i<n;i++)
			{
				M[i][i] = 1 ;
				
			}
		
			for(int d = 1 ;d<n;d++)
			{
				for(int i=0;i<n-d;i++)
				{
					int j = i+d ;
					M[i][j] = Integer.MAX_VALUE ; //initialization 
					for(int k=i;k<j;k++)
					{
						int sum = M[i][k]+M[k+1][j] ;
						if(sum<M[i][j])
							M[i][j] = sum ;
					}
					/* Older code
					// System.out.println("i = "+i+"\nj = "+j) ;
					if(M[i][j-1]>M[i+1][j])// since one more element is added into consideration
					{
						M[i][j] = M[i+1][j]+1 ;
						// System.out.println("-----Case-1------") ;
						
					}
					else
					{
						M[i][j] = M[i][j-1]+1 ;
						// System.out.println("-----Case-2------") ;
					}*/
					if(A[i]==A[j] && M[i+1][j-1]<M[i][j])
					{
						M[i][j] = M[i+1][j-1] ;
						// System.out.println("-----Case-3------") ;
					}
					
				}
			}
			System.out.println(M[0][n-1]) ;
			//matrix_printer(M) ;
		}
		else
			System.out.println(0) ;
		}
		
		
	}

	
	