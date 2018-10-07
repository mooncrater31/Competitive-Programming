//UVa - 10986 - Sending Email
import java.util.* ;
import java.io.BufferedReader ;
import java.io.InputStreamReader ;

public class SendingEmail
{
	private static final boolean debug = false ;
	public static void main(String args[]) throws Exception
	{
		BufferedReader bro = new BufferedReader(new InputStreamReader(System.in)) ;
		int T = Integer.parseInt(bro.readLine()) ;
		for(int t=0;t<T;t++)
		{
			
			String[] S = bro.readLine().split(" ") ;
			int N = Integer.parseInt(S[0]) ;
			int M = Integer.parseInt(S[1]) ;
			int SS = Integer.parseInt(S[2]) ;
			int D = Integer.parseInt(S[3]) ;
			List<List<Pair>> L = new ArrayList<List<Pair>>() ;
			for(int i=0;i<N;i++)
				L.add(new ArrayList<Pair>()) ;
			for(int i=0;i<M;i++)
			{
				String[] S1 = bro.readLine().split(" ") ;
				int a = Integer.parseInt(S1[0]) ;
				int b = Integer.parseInt(S1[1]) ;
				int w = Integer.parseInt(S1[2]) ;
				L.get(a).add(new Pair(b,w)) ;
				L.get(b).add(new Pair(a,w)) ;	
			}
			long ans = dijkstra(L,SS,D) ;
			System.out.println("Case #"+(t+1)+": "+(ans==-1?"unreachable":ans)) ;
		}
	}
	static long dijkstra(List<List<Pair>> L,int S,int D)
	{
		long[] dist = new long[L.size()] ;
		Arrays.fill(dist,Integer.MAX_VALUE) ;
		dist[S] = 0 ;
		PriorityQueue<Pair> pq = new PriorityQueue<Pair>() ;
		pq.add(new Pair(S,0)) ;//Source vertex with zero distance from the source
		while(!pq.isEmpty())
		{
			Pair p = pq.poll() ;
			if(p.vertex == D) 
				return p.distance ;
			if(dist[p.vertex]<p.distance)
				continue ;
			for(int i=0;i<L.get(p.vertex).size();i++)
			{
				Pair neigh = L.get(p.vertex).get(i) ;
				if(neigh.distance+dist[p.vertex]<dist[neigh.vertex])
				{
					dist[neigh.vertex] = neigh.distance+dist[p.vertex] ;
					pq.add(new Pair(neigh.vertex,dist[neigh.vertex])) ;
				}
			}
		}
		if(debug) 
		{
			System.out.print("DIST :") ;
			arrayPrinter(dist) ;
		}
		return -1 ;
	}
	
}
class Pair implements Comparable<Pair>
{
	int vertex; 
	long distance ;
	Pair(int v,long d)
	{
		this.vertex = v ;
		this.distance = d ;
	}
	@Override
	public int compareTo(Pair p)
	{
		return (int)(this.distance-p.distance) ;
	}
}

