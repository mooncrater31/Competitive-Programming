import java.util.*;
import java.io.BufferedReader ;
import java.io.InputStreamReader ;

public class SantaBanta2
{
	private static final boolean debug = true ;
	public static void main(String args[]) throws Exception
	{
		BufferedReader bro = new BufferedReader(new InputStreamReader(System.in)) ;
		int T = Integer.parseInt(bro.readLine()); 
		HashMap<Integer,Integer> H = primes() ;
		for(int t=0;t<T;t++)
		{
			String[] S = bro.readLine().split(" ") ;
			int n = Integer.parseInt(S[0]) ;
			int m = Integer.parseInt(S[1]) ;
			UnionFind U = new UnionFind(n+1) ;
			for(int i=0;i<m;i++)
			{
				S = bro.readLine().split(" ") ;
				int a = Integer.parseInt(S[0]) ;
				int b = Integer.parseInt(S[1]) ;
				U.unionSet(a,b) ;
			}
			int max_size = 0 ;
			for(int i=0;i<U.setSize.length;i++)
			{
				
				if(U.setSize[i]>max_size) max_size = U.setSize[i] ;
			}
			if(debug) 
			{
				System.out.println("max_size :"+max_size) ;
				// arrayPrinter(U.setSize) ;
			}
			System.out.println(max_size==0?"-1":H.get(max_size)) ;
			
		}
	}
	static void arrayPrinter(int[] A)
	{
		for(int a:A) System.out.print(a+" ") ;
		System.out.println() ;
	}
	static HashMap<Integer,Integer> primes()
	{//assuming the maximum prime number needed will not exceed 1e5 
		int n=400000 ;
		HashMap<Integer,Integer> H = new HashMap<Integer,Integer>() ;
		boolean[] notPrime = new boolean[n+1] ;//A false value means that the index i is prime.
		for(int i=2;i<notPrime.length;i++)
		{//Sieve of Eratosthenes
			if(!notPrime[i])
			{
				for(int j=2*i;j<100000;j+=i)
				{
					notPrime[j] = true ;
				}
			}
		}
		int count = 1 ;
		for(int i=2;i<notPrime.length;i++)
		{
			if(!notPrime[i]) 
				H.put(count++,i) ;
		}
		return H ;
	}
}
class UnionFind
{
	int[] p,rank,setSize ;
	UnionFind(int N)
	{
		p = new int[N] ;
		rank = new int[N] ;
		setSize = new int[N] ;
		Arrays.fill(setSize,1) ;
		setSize[0] = 0 ;
		for(int i=0;i<N;i++) p[i] = i ;
		
	}
	int findSet(int i) 
	{
		return (p[i]==i)?i:(p[i] = findSet(p[i])) ;
	}
	boolean isSameSet(int i,int j)
	{
		return findSet(i)==findSet(j) ;
	}
	void unionSet(int i,int j)
	{
		if(!isSameSet(i,j))
		{
			int x = findSet(i),y = findSet(j) ;
			if(rank[x]>rank[y]) 
			{
				p[y] =x ;
				setSize[x]+=setSize[y] ;
				setSize[y] = 0 ;
				
			}
			else
			{
				p[x] = y ;
				if(rank[x]==rank[y]) rank[y]++ ;
				setSize[y]+=setSize[x] ;
				setSize[x] = 0 ;
			}
		}
	}
}