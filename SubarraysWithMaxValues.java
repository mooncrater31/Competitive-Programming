import java.util.* ;
import java.io.BufferedReader ;
import java.io.InputStreamReader ;

public class SubarraysWithMaxValues
{
	private static final boolean debug = false ;
	public static void main(String args[]) throws Exception
	{
		BufferedReader bro = new BufferedReader(new InputStreamReader(System.in)) ;
		int T = Integer.parseInt(bro.readLine()) ;
		for(int t=0;t<T;t++)
		{
			String[] S = bro.readLine().split(" ") ;
			int n = Integer.parseInt(S[0]) ;
			int l = Integer.parseInt(S[1]) ;
			int r = Integer.parseInt(S[2]) ;
			int[] A = new int[n] ;
			S = bro.readLine().split(" ") ;
			for(int i=0;i<n;i++)
			{
				A[i] = Integer.parseInt(S[i]) ;
			}
			int[] up = solve(A,n) ;
			int[] down = solve(reverse(A),-1) ;
			// for(int i=0;i<down.length;i++)
			// {//this can be removed
				// down[i] = down[i]==-1?-1:n-down[i]-1 ;
			// }
			if(debug) 
			{
				System.out.println("Changed down:") ;
				arrayPrinter(down) ;
			}
			// down = reverse(down) ;//this can be removed
			if(debug) 
			{
				System.out.println("Changed down:") ;
				arrayPrinter(down) ;
			}
			int sum = 0 ;
			for(int i=0;i<n;i++)
			{
				if(A[i]<=r && A[i]>=l)
				{
					if(debug) 
					{
						System.out.println("Encountered :"+A[i]+" at position "+i+" which has range from position :"+down[i]+" to :"+up[i]) ;
					}
					int dval = down[down.length-i-1]==-1?-1:n-down[down.length-i-1]-1;
					sum+=subArrayCounter(i-dval-1,up[i]-dval-1) ;
				}
			}
			System.out.println(sum) ;
			
			
		}
	}
	static void arrayPrinter(int[] A)
	{
		for(int a:A)
			System.out.print(a+" ") ;
		System.out.println() ;
	}
	static int subArrayCounter(int pos,int length)
	{
		return (pos+1)*(length-pos) ;
	}
	static int[] reverse(int[] A)
	{
		int[] B = new int[A.length] ;
		for(int i=0;i<A.length;i++)
		{
			B[B.length-i-1] = A[i] ;
		}
		return B ;
	}
	static int[] solve(int[] A,int oorv)//oorv = out of range value
	{
		if(debug)
		{
			System.out.println("Array sent to solve :") ;
			arrayPrinter(A) ;
		}
		int[] upLimit = new int[A.length] ;
		upLimit[upLimit.length-1] = oorv ;
		for(int i=upLimit.length-2;i>=0;i--)
		{
			if(A[i]<A[i+1])
				upLimit[i] = i+1 ;
			else
			{
				int temp = i+1 ;
				while(A[i]>A[temp] && upLimit[temp]!=oorv)
					temp = upLimit[temp] ;
				if(A[i]<A[temp])
					upLimit[i] = temp ;
				else if(upLimit[temp]==oorv)
					upLimit[i] = oorv ;
				
			}
		}
		if(debug)
		{
			arrayPrinter(upLimit) ;
		}
		
		return upLimit ;
	}
}