import java.util.* ;
import java.io.* ;
public class MaximumSubsequence
{
	static long pow(long n,long p)
	{
		long  result = 1;
		if(p==0)
		   return 1;
		if (p==1)
		    return n;
		while(p!=0)
		{
		    if(p%2==1)
		        result *= n;	    
		    p >>=1;
		    n*=n;	    
		}
		return result;
	}
	static long modifiedBinarySearch(Long[] A,long k)
	{
		
		int from = 0, to = A.length-1,ans=-1 ;
		while(from<=to )
		{
			int m = (from+to)/2 ;
			// System.out.println("from: "+from+" to :"+to+"m :"+m) ;
			if(A[m]==k)
				return A[m] ;
			if(A[m]<k)
			{
				from = m+1 ;
				ans=m;
			}
			else
			{
				to = m-1 ;
			}
		}
		// return A[from] ;
		if(ans==-1)
			return 0;
		else
			return A[ans];
		
	}
	static HashSet<Long> setChecker(long[] A,long m)
	{
		// boolean[] M = new boolean[m] ;
		HashSet<Long> H = new HashSet<Long>() ;
		int set_size = A.length ;
		long power_set_size = pow(2,set_size) ;
		for(int i=0;i<power_set_size;i++)
		{
			long sum = 0 ;
			for(int j=0;j<set_size;j++)
			{
				if((i & (1<<j))!=0)
					sum = sum + A[j] ;
			}
			H.add((sum%m)) ;
		}
		return H ;
	}
	static void arrayPrinter(Long[] A)
	{
		for(int i=0;i<A.length;i++)
		{
			System.out.print(A[i]+" ") ;
		}
		System.out.println() ;
	}
	static void arrayPrinter(long[] A)
	{
		for(int i=0;i<A.length;i++)
		{
			System.out.print(A[i]+" ") ;
		}
		System.out.println() ;
	}
	static long maxPossibleValue(long[] A,long m)
	{
		if(m==1)
			return 0 ;
		int half_length = A.length/2 ;
		long[] first_half = new long[half_length] ;
		long[] second_half  = new long[A.length-half_length] ;
		
		for(int i=0;i<half_length;i++)
		{
			first_half[i] = A[i] ;
		}
		for(int i=half_length;i<second_half.length+half_length;i++)
		{
			second_half[i-half_length] = A[i] ;
		}
		// System.out.println("first_half") ;
		// arrayPrinter(first_half) ;
		// System.out.println("\nsecond_half") ;
		// arrayPrinter(second_half) ;
		// System.out.println("A:") ;
		// arrayPrinter(A) ;
		HashSet<Long> H1 = setChecker(first_half,m) ;
		HashSet<Long> H2  = setChecker(second_half,m) ;
		Long[] A1 = H1.toArray(new Long[H1.size()]) ;
		Long[] A2 = H2.toArray(new Long[H2.size()]) ;
		// System.out.println("Before sorting:") ;
		
		// arrayPrinter(A1) ;
		// arrayPrinter(A2) ;
		Arrays.sort(A1) ;
		Arrays.sort(A2) ;
		// System.out.println("After sorting:") ;
		// arrayPrinter(A1) ;
		// arrayPrinter(A2) ;
		long maxPV = 0 ;
		for(int i=0;i<A1.length;i++)
		{
			long ans = modifiedBinarySearch(A2,m-A1[i]-1) ;
			// System.out.println("Searched for :"+(m-A1[i]-1)+" and mBS returned ans :"+ans) ;
			if(maxPV<(A1[i]+ans)%m)
			{
				maxPV = (A1[i]+ans)%m ;
				// System.out.println("maxPV is :"+maxPV) ;
			}
		}
		// for(int i=0;i<A1.length;i++)
		// {
			// for(int j=0;j<A2.length;j++)
			// {
				// if((A1[i]+A2[j])%m > maxPV)
					// maxPV = (A1[i]+A2[j])%m ;
			// }
		// }
		// System.out.println() ;
		
			
		return maxPV ;
	}
	public static void main(String args[]) throws Exception
	{
		
		Scanner in = new Scanner(System.in) ;
		int size = in.nextInt() ;
		
		long m = in.nextLong() ;
		long[] A = new long[size] ;
		long maxPV = 0 ;
		for(int i=0;i<size;i++)
		{
			A[i] = in.nextLong() ;
			// if(maxPV<A[i]%m)
				// maxPV = A[i]%m ;
		}
		long ans1 = maxPossibleValue(A,m) ;
		long ans = ans1>maxPV?ans1:maxPV ;
		System.out.println(ans) ;
		
		// int k = in.nextInt() ;
		// System.out.println(modifiedBinarySearch(A,k)) ;
			
	}
}