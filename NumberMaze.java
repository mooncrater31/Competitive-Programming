//UVa-929
import java.util.* ;
import java.io.BufferedReader ;
import java.io.InputStreamReader ;
/*
This one is slow. Possible changes :
1. Use an array instead of a HashMap to save the "point--distance from source" mapping.
-> Yup. Worked. For the biggest test case at uDebug, the runtime was reduced to less than half.
*/
public class NumberMaze
{
	static int dr[] = {0,1,0,-1} ;
	static int dc[] = {1,0,-1,0} ;
	private static final boolean debug = true ;
	static int polls = 0 ;
	public static void main(String args[]) throws Exception
	{
		BufferedReader bro =  new BufferedReader(new InputStreamReader(System.in)) ;
		int T = Integer.parseInt(bro.readLine()) ;
		double t1 = System.nanoTime() ;
		for(int t=0;t<T;t++)
		{
			int R = Integer.parseInt(bro.readLine()) ;
			int C = Integer.parseInt(bro.readLine()) ;
			int[][] M = new int[R][C] ;
			for(int i=0;i<R;i++)
			{
				String[] S = bro.readLine().split(" ") ;
				for(int j=0;j<C;j++)
				{
					M[i][j] = Integer.parseInt(S[j]) ;
				}
			}
			System.out.println(dijkstra(M)) ;
		}
		double t2 = System.nanoTime() ;
		if(debug) {
			System.out.println((t2-t1)/1000000000) ;
			System.out.println("Total adds : "+polls) ;
		}
	}
	static int dijkstra(int[][] M)
	{
		int cols = M[0].length ;
		// HashMap<Point,Integer> H = new HashMap<Point,Integer>() ;
		int[] dist = new int[M.length*(M[0].length)] ;
		// for(int i=0;i<M.length;i++)
		// {
			// for(int j=0;j<M[i].length;j++)
			// {
				// H.put(new Point(i,j),Integer.MAX_VALUE) ;
			// }
		// }
		Arrays.fill(dist,Integer.MAX_VALUE) ;
		// H.put(new Point(0,0),M[0][0]) ;
		dist[0] = M[0][0] ;
		PriorityQueue<Triplet> pq = new PriorityQueue<Triplet>() ;
		pq.add(new Triplet(new Point(0,0),M[0][0])) ;
		while(!pq.isEmpty())
		{
			Triplet t = pq.poll() ;
			if(t.pt.equals(new Point(M.length-1,M[0].length-1)))
				return t.p ;
			// if(t.p>H.get(t.pt))
				// continue ;
			if(t.p > dist[t.pt.x*cols+t.pt.y])
				continue ;
			for(int i=0;i<dr.length;i++)
			{
				int r = t.pt.x + dr[i] ;
				int c = t.pt.y + dc[i] ;
				Point temp = new Point(r,c) ;
				// if(0<=r && 0<=c && r<M.length && c<M[0].length && M[r][c]+H.get(t.pt)<H.get(temp))
				// {
					// H.put(temp,M[r][c]+H.get(t.pt)) ;
					// pq.add(new Triplet(temp,H.get(temp))) ;
					// if(debug) polls++ ;
				// }
				if(0<=r && 0<=c && r<M.length && c<M[0].length && M[r][c]+dist[t.pt.x*cols+t.pt.y]<dist[temp.x*cols+temp.y])
				{
					dist[temp.x*cols+temp.y] = M[r][c]+dist[t.pt.x*cols+t.pt.y] ;
					pq.add(new Triplet(temp,dist[temp.x*cols+temp.y])) ;
					if(debug) polls++ ;
				}
			}
		}
		return -1 ;
	}
}
class Triplet implements Comparable<Triplet>
{
	Point pt ;
	int p ;
	Triplet(Point pt,int p)
	{
		this.pt = pt ;
		this.p = p ;
	}
	@Override
	public int compareTo(Triplet t)
	{
		return this.p-t.p ;
	}
}
class Point 
{
	int x,y ;
	Point(int x,int y)
	{
		this.x = x ;
		this.y = y ;
	}
	@Override
	public boolean equals(Object o)
	{
		if(o==null)
			return false ;
		if(!(o instanceof Point))
			return false ;
		Point p = (Point)o ;
		if(this.x==p.x && this.y==p.y)
			return true ;
		return false ;
	}
	@Override
	public int hashCode()
	{
		return Objects.hash(this.x,this.y) ;
	}
}