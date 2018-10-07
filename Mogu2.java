import java.util.* ;
import java.io.BufferedReader ;
import java.io.InputStreamReader ;
//Prime counting using Sieve of Eratosthenes and NavigableSet
public class Mogu2
{	
	private static final boolean debug = false ;
	private static final boolean time = true ;
	public static void main(String args[]) throws Exception
	{
		long startTime = System.nanoTime() ;
		BufferedReader bro = new BufferedReader(new InputStreamReader(System.in)) ;
		NavigableSet<Integer> prime = new TreeSet<Integer>() ;
		segmentedSieve(0,1000000,prime) ;
		long t1 = System.nanoTime() ;
		int T = Integer.parseInt(bro.readLine()) ;
		for(int t=0;t<T;t++)
		{
			String[] S = bro.readLine().split(" ") ;
			int l = Integer.parseInt(S[0]) ;
			int r = Integer.parseInt(S[1]) ;
			int temp = l<r?l:r ;
			r = l<r?r:l ;
			l = temp ;
			System.out.println((prime.subSet(l,r).size()+(prime.contains(r)?1:0))) ;
			if(debug)
			{
				System.out.println("prime.size() :"+prime.size()) ;
				System.out.println("prime.contains(3) :"+prime.contains(3)) ;
			}
		}
		long t2 = System.nanoTime() ;
		if(time) System.out.println("Total time :"+((t2-startTime)/1000)+" Time to make the set "+((t1-startTime)/1000)) ;
	}
	static void sieveOfEratosthenes(int l,int r,List<Integer> prime)
	{
		boolean[] mark = new boolean[r+1] ;
		
		Arrays.fill(mark,true ) ;
		
		for(int i=2;i<r+1;i++)
		{
			if(mark[i])
			{
				if(i>=l)
				{
					
					prime.add(i) ;
					
				}
				for(int j=i*2;i*i<=r && j<r+1;j+=i)
					mark[j] = false ;
			}
		}
	
	}
	static void segmentedSieve(int l,int n,NavigableSet<Integer> p)
	{
		int limit = (int)Math.sqrt(n)+1 ;
		List<Integer> prime = new ArrayList<Integer>() ;
		sieveOfEratosthenes(0,limit,prime) ;
		int low = limit ;
		int high = limit*2 ;
		// int count = 0 ;
		while(low<n)
		{
			if(high>n)
				high = n ;
			boolean mark[] = new boolean[limit+1] ;
			Arrays.fill(mark,true ) ;
			for(int i=0;i<prime.size();i++)
			{
				int loLim = (int)(Math.ceil((float)low/prime.get(i)))*prime.get(i) ;
				for(int j=loLim;j<=high;j+=prime.get(i))
				{
					mark[j-low] = false ;
				}
			}
			for(int i = 0;i<mark.length;i++)
				if(mark[i] && (i+low>=l)&& (i+low<=high))
				{
					
					// count++ ;
					p.add(i+low) ;
				}
			low+=limit ;
			high+=limit ;
		}
		if(l<=Math.sqrt(n)+1)
		{
			for(int i=0;i<prime.size();i++)
				if(prime.get(i)>=l)
					p.add(prime.get(i)	) ;
					// count++ ;
		}
		// return count ;
		
	}
	
}
