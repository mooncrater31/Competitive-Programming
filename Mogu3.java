import java.util.* ;
import java.io.BufferedReader ;
import java.io.InputStreamReader ;
//Prime counting using Sieve of Eratosthenes and NavigableSet
public class Mogu3
{	
	public static void main(String args[]) throws Exception
	{
		BufferedReader bro = new BufferedReader(new InputStreamReader(System.in)) ;
		int[] prime = new int[(int)1e6+1] ;
		segmentedSieve(1000000,prime) ;
		for(int i=1;i<prime.length;i++)
		{
			prime[i] = prime[i]+prime[i-1] ;
		}
		
		int T = Integer.parseInt(bro.readLine()) ;
		StringBuilder sb = new StringBuilder() ;
		for(int t=0;t<T;t++)
		{
			String[] S = bro.readLine().split(" ") ;
			int l = Integer.parseInt(S[0]) ;
			int r = Integer.parseInt(S[1]) ;
			int temp = l<r?l:r ;
			r = l<r?r:l ;
			l = temp ;
			//System.out.println((prime[r]-prime[l-1])) ;
			sb.append((prime[r]-prime[l-1])+"\n") ;
		}
		System.out.println(sb) ;
		
	}
	static void sieveOfEratosthenes(int r,List<Integer> prime)
	{
		boolean[] mark = new boolean[r+1] ;
		Arrays.fill(mark,true ) ;
		for(int i=2;i<r+1;i++)
		{
			if(mark[i])
			{
				prime.add(i) ;	
				for(int j=i*2;i*i<=r && j<r+1;j+=i)
					mark[j] = false ;
			}
		}
	
	}
	static void segmentedSieve(int n,int[] p)
	{
		int limit = (int)Math.sqrt(n)+1 ;
		List<Integer> prime = new ArrayList<Integer>() ;
		sieveOfEratosthenes(limit,prime) ;
		int low = limit ;
		int high = limit*2 ;
		while(low<n)
		{
			if(high>n)
				high = n ;
			boolean mark[] = new boolean[limit+1] ;
			//Arrays.fill(mark,true ) ;
			for(int i=0;i<prime.size();i++)
			{
				int loLim = (int)(Math.ceil((float)low/prime.get(i)))*prime.get(i) ;
				for(int j=loLim;j<=high;j+=prime.get(i))
				{
					mark[j-low] = true ;
				}
			}
			for(int i = 0;i<mark.length;i++)
				if(!mark[i] && (i+low<=high))
				{
					p[i+low] = 1 ;
				}
			low+=limit ;
			high+=limit ;
		}
		
		for(int i=0;i<prime.size();i++)
			p[prime.get(i)] = 1 ;
	}
	
}