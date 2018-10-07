import java.util.* ;

public class PrimeGiftNaive
{
	public static void main(String args[])
	{
		Scanner in = new Scanner(System.in) ;
		int n = in.nextInt() ;
		long[] P = new long[n] ;
		for(int i=0;i<n;i++)
		{
			P[i] = in.nextLong() ;
		}
		int k = in.nextInt() ;
		System.out.println(solve(P,k)) ;
		
	}
	static long solve(long[] P,long k)
	{
		PriorityQueue<Long> Q = new PriorityQueue<Long>() ;
		// HashSet<Integer> H = new HashSet<Integer>() ;
		Q.add(new Long(1)) ;
		for(long i=0;i<k-1;i++)
		{
			long val = Q.poll() ;
			// System.out.println("Extraction #"+i+" = "+val) ;
			for(int j=0;j<P.length;j++)
			{
				if(!Q.contains(val*P[j]))
				{
					if(val*P[j]>0)
						Q.add(val*P[j]) ;
				}
			}
			Iterator itr = Q.iterator() ;
			// while(itr.hasNext())
			// {
				// Object o = itr.next() ;
				// System.out.print(o+" ") ;
			// }
			// System.out.println() ;
		}
		return Q.poll() ;
	}
}