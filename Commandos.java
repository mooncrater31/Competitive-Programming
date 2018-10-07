//UVa - 11463
import java.util.* ;
import java.io.BufferedReader ;
import java.io.InputStreamReader ;

public class Commandos
{
	private static final boolean debug = false ;
	public static void main(String args[]) throws Exception
	{
		BufferedReader bro = new BufferedReader(new InputStreamReader(System.in)) ;
		int T = Integer.parseInt(bro.readLine()) ;
		for(int t=0;t<T;t++)
		{
			int N = Integer.parseInt(bro.readLine()) ;
			int M = Integer.parseInt(bro.readLine()) ;
			List<List<Integer>> L = new ArrayList<List<Integer>>() ;
			for(int i=0;i<N;i++)
			{
				L.add(new ArrayList<Integer>()) ;
			}
			for(int i=0;i<M;i++)
			{
				String[] S = bro.readLine().split(" ") ;
				int a = Integer.parseInt(S[0]) ;
				int b = Integer.parseInt(S[1]) ;
				L.get(a).add(b) ;
				L.get(b).add(a) ;
			}
			String[] S = bro.readLine().split(" ") ;
			int s = Integer.parseInt(S[0]) ;
			int d = Integer.parseInt(S[1]) ;
			int[] dist1 = bfs(L,s) ;
			int[] dist2 = bfs(L,d) ;
			if(debug)
			{
				System.out.println("dist1 :") ;
				arrayPrinter(dist1) ;
				System.out.println("dist2 :") ;
				arrayPrinter(dist2) ;
			}
			int max = Integer.MIN_VALUE ;
			for(int i=0;i<N;i++)
			{
				 max = max>dist1[i]+dist2[i]?max:dist1[i]+dist2[i] ;
			}
			System.out.println(max) ;
		}
	}
	static void arrayPrinter(int[] A)
	{
		for(int i=0;i<A.length;i++)
		{
			System.out.print(A[i]+" ") ;
		}
		System.out.println() ;
	}
	static int[] bfs(List<List<Integer>> L,int source)
	{
		int[] dist = new int[L.size()] ;
		Arrays.fill(dist,Integer.MAX_VALUE) ;
		dist[source] = 0 ;
		ArrayDeque<Node> DQ = new ArrayDeque<Node>() ;
		DQ.add(new Node(source,0)) ;
		while(!DQ.isEmpty())
		{
			Node temp = DQ.poll() ;
			for(int i=0;i<L.get(temp.vertex).size();i++)
			{
				int toVertex = L.get(temp.vertex).get(i) ;
				if(dist[toVertex]>1+temp.dist)
				{
					dist[toVertex] = 1+temp.dist ;
					DQ.add(new Node(toVertex,dist[toVertex])) ;
				}
			}
		}
		return dist ;
	}
}
class Node
{
	int vertex, dist ;
	Node(int v,int d)
	{
		this.vertex = v ;
		this.dist = d ;
	}
}