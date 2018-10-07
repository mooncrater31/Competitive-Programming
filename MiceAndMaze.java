import java.util.* ;
import java.io.BufferedReader ;
import java.io.InputStreamReader ;
//UVa-01112 - Mice and Maze
public class MiceAndMaze
{
	private static final boolean debug = false ;
	public static void main(String args[]) throws Exception
	{
		BufferedReader bro = new BufferedReader(new InputStreamReader(System.in)) ;
		int T1 = Integer.parseInt(bro.readLine()) ;
		bro.readLine() ;
		for(int t=0;t<T1;t++)
		{
			int N = Integer.parseInt(bro.readLine()) ;
			int D = Integer.parseInt(bro.readLine()) ;
			int T = Integer.parseInt(bro.readLine()) ;
			int M = Integer.parseInt(bro.readLine()) ;
			int[][] A = new int[N+1][N+1] ;
			for(int i=0;i<M;i++)
			{
				String[] S = bro.readLine().split(" ") ;
				int a = Integer.parseInt(S[0]) ;
				int b = Integer.parseInt(S[1]) ;
				int w = Integer.parseInt(S[2]) ;
				// A[a][b] = w ;
				A[b][a] = w ;//Reversal of edges, in order to allow reverse traversal from the destination
			}
			int[] dist = dijkstra(A,D) ;
			if(debug) arrayPrinter(dist) ;
			int sum = 0 ;
			for(int i=0;i<=N;i++)
			{
				if(dist[i]<=T) sum++ ;
			}
			System.out.print(sum+"\n\n") ;
			// System.out.println() ;
			bro.readLine() ;
			
		}
	}
	static void arrayPrinter(int[] A)
	{
		for(int i=0;i<A.length;i++)
			System.out.print(A[i]+" ") ;
		System.out.println() ;
	}
	static int[] dijkstra(int[][] M,int S)
	{
		int dist[] = new int[M.length] ;
		Arrays.fill(dist,Integer.MAX_VALUE) ;
		dist[S] = 0 ;
		PriorityQueue<Pair> pq = new PriorityQueue<Pair>(100,new Comparator<Pair>()
		{
			@Override
			public int compare(Pair a,Pair b)
			{
				return a.distance-b.distance ;
			}
		}) ;
		pq.add(new Pair(S,0)) ;
		while(!pq.isEmpty())
		{
			Pair p = pq.poll() ;
			if(p.distance>dist[p.vertex]) continue ;
			for(int i=0;i<M[p.vertex].length;i++)
			{
				if(M[p.vertex][i]!=0)
				{
					int edge_weight = M[p.vertex][i] ;
					if(edge_weight+dist[p.vertex]<dist[i])
					{
						dist[i] = edge_weight+dist[p.vertex] ;
						pq.add(new Pair(i,dist[i])) ;
					}
				}
				
			}
		}
		return dist ;
	}
}
class Pair
{
	int vertex,distance ;
	Pair(int v,int d)
	{
		this.vertex = v ;
		this.distance = d ;
	}
}