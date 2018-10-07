//UVa 10389
import java.util.* ;
import java.io.BufferedReader ;
import java.io.InputStreamReader ;
/* Bugs :
1. Wasn't putting points of subways with only one point(makes no sense), into the HashMap and the p2i list.
-> Solved.
2. Error for the input :
1

9208 2898 9589 5220
5579 9930 5042 8529 6241 8484 4165 1710 8254 3479 7932 5160 2874 7813 5861 6543 2266 1602 4210 8839 63 9177 3532 7214 2203 8190 -1 -1
6439 1054 8389 1043 6711 3854 8863 1038 6195 6829 2998 6395 5098 4002 9497 9006 3187 3389 5150 460 9833 3314 9154 3259 2535 6744 -1 -1
*/
public class Subway
{
	private static final boolean debug = false ;
	private static final boolean debug2 = false ;
	private static final boolean debug3 = true  ;
	public static void main(String args[]) throws Exception
	{
		BufferedReader bro = new BufferedReader(new InputStreamReader(System.in)) ;
		int T = Integer.parseInt(bro.readLine()) ;
		bro.readLine() ;
		String Serror = "" ;
		for(int t=0;t<T;t++)
		{
			try
			{
				if(debug3) System.out.println("t = "+t) ;
				Serror = bro.readLine() ;
				String[] S = Serror.split(" ") ;
				int sa = Integer.parseInt(S[0]) ;
				int sb = Integer.parseInt(S[1]) ;
				int da = Integer.parseInt(S[2]) ;
				int db = Integer.parseInt(S[3]) ;
				Point source = new Point(sa,sb) ;
				Point destination = new Point(da,db) ;
				List<List<Point>> M = new ArrayList<List<Point>>(); 
				int listCount = 0,size=0 ;
				for(String[] S1 = bro.readLine().split(" ");!S1[0].equals("");S1 = bro.readLine().split(" "))
				{
					M.add(new ArrayList<Point>()) ;
					int length = (S1.length/2) -1;
					for(int i=0;i<length;i++)
					{
						int a = Integer.parseInt(S1[2*i]) ;
						int b = Integer.parseInt(S1[2*i+1]) ;
						M.get(listCount).add(new Point(a,b)) ;
						size++; 
					}
					listCount++ ;
				}
				if(debug) System.out.println("Input Completed.") ;
				size+=2 ;//for the source and the destination
				double[][] A = new double[size][size] ;
				List<Point> p2i = new ArrayList<Point>() ;
				HashMap<Point,Integer> H = new HashMap<Point,Integer>() ;
				p2i.add(source) ;
				p2i.add(destination) ;
				H.put(source,0) ;
				H.put(destination,1) ;
				for(int i=0;i<M.size();i++)
				{//Connecting the subway
					if(M.get(i).size()==1)
					{
						Point p1 = M.get(i).get(0) ;
						if(!H.containsKey(p1))
						{
							p2i.add(p1) ;
							H.put(p1,p2i.size()-1) ;
						}
					}
					for(int j=0;j<M.get(i).size()-1;j++)
					{
						Point p1 = M.get(i).get(j) ;
						Point p2 = M.get(i).get(j+1) ;
						if(!H.containsKey(p1))
						{//new point 
							p2i.add(p1) ;
							H.put(p1,p2i.size()-1) ;
						}
						if(!H.containsKey(p2))
						{
							p2i.add(p2) ;
							H.put(p2,p2i.size()-1) ;
						}
						int m = H.get(p1), n = H.get(p2) ;
						double time = 1.5*(distance(p1,p2)/1000) ;
						A[m][n] = time ;
						A[n][m] = time ;
					}
				}
				for(int i=0;i<A.length;i++)
				{
					for(int j=i+1;j<A[i].length;j++)
					{
						if(A[i][j]==0)
						{
							Point p1 = p2i.get(i) ;
							Point p2 = p2i.get(j) ;
							double time = 6*(distance(p1,p2)/1000) ;
							A[i][j] = time ;
							A[j][i] = time ;
						}
					}
				}
				if(debug2) arrayPrinter(A) ;
				System.out.println(dijkstra(H.get(source),H.get(destination),A)) ;
				System.out.println() ;
			}
			catch( Exception e)
			{
				System.out.println("For the input number  :"+t) ;
				System.out.println("String :"+Serror) ;
				System.out.println(e) ;
				
			}
		}
	}
	static void arrayPrinter(double[][] A)
	{
		for(int i=0;i<A.length;i++)
		{
			for(int j=0;j<A[i].length;j++)
			{
				System.out.print(A[i][j]+" ") ;
			}
			System.out.println() ;
		}
	}
	static void arrayPrinter(double[] A)
	{
		for(int i=0;i<A.length;i++)
		{
			System.out.print(A[i]+" ") ;
		}
		System.out.println() ;
	}
	static int dijkstra(int s,int d,double[][] A)
	{
		
		PriorityQueue<Pair> pq = new PriorityQueue<Pair>() ;
		pq.add(new Pair(s,0)) ;
		double[] dist = new double[A.length] ;
		
		Arrays.fill(dist,Double.MAX_VALUE) ;
		dist[s] = 0 ;	
		while(!pq.isEmpty())
		{
			Pair temp = pq.poll() ;
			if(temp.t>dist[temp.v])
				continue ;
			for(int i=0;i<A[temp.v].length;i++)
			{
				if(dist[i]>dist[temp.v]+A[temp.v][i])
				{
					dist[i] = temp.t+A[temp.v][i] ;
					pq.add(new Pair(i,dist[i])) ;
				}
			}
		}
		if(debug2) arrayPrinter(dist) ;
		return (int)Math.round(dist[d]) ;
	}
	static double distance(Point p1,Point p2)
	{
		return Math.sqrt(Math.pow(p1.x-p2.x,2)+Math.pow(p1.y-p2.y,2)) ;
	}
}
class Pair implements Comparable<Pair>
{
	int v ;
	double t ;
	Pair(int v,double t)
	{
		this.v = v ;
		this.t = t ;
	}
	@Override
	public int compareTo(Pair p)
	{
		return (int)(this.t-p.t) ;
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
		Point temp = (Point)o ;
		if(temp.x==this.x && temp.y == this.y)
			return true ;
		return false ;
	}
	@Override
	public int hashCode()
	{
		return Objects.hash(this.x,this.y) ;
	}
}