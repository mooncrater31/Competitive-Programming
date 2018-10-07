import java.util.* ;
import java.io.BufferedReader ;
import java.io.InputStreamReader ;

public class SantaBanta
{
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
			ArrayList<ArrayList<Integer>> M = new ArrayList<ArrayList<Integer>>() ;
			for(int i=0;i<=n;i++)
				M.add(new ArrayList<Integer>()) ;
			for(int i=0;i<m;i++)
			{
				S = bro.readLine().split(" ") ;
				int a = Integer.parseInt(S[0]) ;
				int b = Integer.parseInt(S[1]) ;
				M.get(a).add(b) ;
				M.get(b).add(a) ;
			}
			int num = solve(M) ;
			System.out.println(m==0?"-1":H.get(num)) ;
		}
	}
	static int solve(ArrayList<ArrayList<Integer>> M)
	{//will do a bfs, to find the largest connected component
		boolean[] visited = new boolean[M.size()] ;
		int max_size = Integer.MIN_VALUE ;
		for(int i=0;i<M.size();i++)
		{
			
			if(!visited[i])
			{
				int size = 0 ;
				ArrayDeque<Integer> DQ = new ArrayDeque<Integer>() ;
				// int src = i ;
				DQ.add(i) ;
				while(!DQ.isEmpty())
				{
					
					int temp = DQ.poll() ;
					if(visited[temp]) continue ;
					visited[temp] = true ;
					size++ ;
					for(int j=0;j<M.get(temp).size();j++)
					{
						int val = M.get(temp).get(j) ;
						if(!visited[val])
							DQ.add(val) ;
					}
				}
				if(size>max_size)
					max_size = size==1?max_size:size ;
			}
		}
		return max_size ;
		
	}
	static HashMap<Integer,Integer> primes()
	{//assuming the maximum prime number needed will not exceed 1e5 
		int n=100000 ;
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
