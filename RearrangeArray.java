import java.util.* ;
import java.io.BufferedReader ;
import java.io.InputStreamReader ;

public class RearrangeArray
{
	private static final boolean debug = false  ;
	public static void main(String args[]) throws Exception
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
			A = solve(A) ;
			for(int i=0;i<A.length;i++)
			{
				System.out.print((A[i]-A.length)+" ") ;
			}
			System.out.println() ;
		}
	}
	static int[] solve(int[] A)
	{
		int a=0 ,b=-1 ;
		for(int i=0;i<A.length;)
		{
			if( b==-1)
			{
				if(A[i]<A.length)
				{
					a = A[i] ;
					b = i ;
					A[i] = A[a]+A.length ;
					if(debug) System.out.println("Put "+a+" in A["+i+"]") ;
					i++ ;
				}
				else i++ ;
			}
			else
			{
				
				int ind = search(A,b,i) ;
				
				if(ind!=-1)
				{
					int temp = a ;
					a = A[ind] ;
					b = ind ;
					if(a>=A.length)
					{
						b = -1 ;
					}
					else 
					{
						A[ind] = temp+A.length ;
						if(debug) System.out.println("Put "+a+" in A["+ind+"]") ;
					}
				}
				else 
					b = -1 ;
				
			}
			if(a==b)
				b= -1 ;
		}
		return A ;
	}
	static int search(int[] A,int e,int index)
	{
		for(int i=index;i<A.length;i++)
		{
			if(A[i]==e)
				return i ;
		}
		return -1 ;
	}
}