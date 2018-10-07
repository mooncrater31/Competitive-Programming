import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Coins {

    long[] table ;
    Coins(int n,int least)// n->amount to be paid in coins, least->the amount of the coin with the least value (5)
    {
        table = new long[n+1] ;// so that table[n] can be accessed
        Arrays.fill(table,-1) ;//initialization to -1 (1)
		for(long i=0;i<least;i++)
		{
			table[(int)i]=0 ;
		}
		System.out.println("In da constructor.") ;
    }
     long getWays(int n, int[] c)
    {
		System.out.println(n) ;
         if(table[(int)n]==-1)//(7)
         {// to implement (4) and(2), we'll need an array(say q) of the  size  n. Where q[i] will represent the coin i. If there's no such coin as i, then too
	 // q[i] will be incremented by one. Later if q[c[i]] == 1 then we will not perform the recursion.
	 
	        boolean[] used = new boolean[n] ; 
			Arrays.fill(used,false) ;
            long counter = 0;
            for(int i=0;i<c.length && c[i]<=n;i++)// the second condition implements (6)
            {
                   if(c[i]==n)
                {
                       counter++ ;
                    break ;
                }
				if(!used[c[i]])// (4) 
				{
					long temp = getWays(n-c[i],c) ;//the coin c[i] is taken
					counter = counter + temp ;
					used[c[i]]=true ;
					used[n-c[i]]=true ;
				}
            }
             table[(int)n] = counter ;
             
         }
        
         return table[(int)n] ;
       
    }
	void printArray(long[] A)
	{
		for(int i=0;i<A.length;i++)
		{
			System.out.print(A[i]+" ") ;
		}
		System.out.print("\n") ;
	}
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int m = in.nextInt();
        int[] c = new int[m];
        for(int c_i=0; c_i < m; c_i++){
            c[c_i] = in.nextInt();
        }
        // Print the number of ways of making change for 'n' units using coins having the values given by 'c'
        Arrays.sort(c) ;
        Coins S = new Coins(n,c[0]) ;
        long ways = S.getWays(n, c)  ;
        System.out.println("Ways:"+ways);
		System.out.println("The table:") ;
		S.printArray(S.table) ;
    }
}
