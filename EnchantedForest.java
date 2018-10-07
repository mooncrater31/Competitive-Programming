import java.util.* ;
import java.io.BufferedReader ;
import java.io.InputStreamReader ;

public class EnchantedForest
{
	static int[] dr = {-1,0,1,0} ;
	static int[] dc = {0,1,0,-1} ;
	private static final boolean debug = false ;
	public static void main(String args[]) throws Exception
	{
		BufferedReader bro = new BufferedReader(new InputStreamReader(System.in)) ;
		for(String S = bro.readLine();;S = bro.readLine())
		{
			String[] s = S.split(" ") ;
			int n = Integer.parseInt(s[0]) ;
			int m = Integer.parseInt(s[1]) ;
			
			if(n==0 && m==0)
				break ;
			int blocked = Integer.parseInt(bro.readLine()) ;
			HashSet<Point> H = new HashSet<Point>() ;
			for(int i=0;i<blocked;i++)
			{
				String s1[] = bro.readLine().split(" ") ;
				int a = Integer.parseInt(s1[0]),b = Integer.parseInt(s1[1]) ;
				H.add(new Point(a-1,b-1)) ;
			}
			int jb = Integer.parseInt(bro.readLine()) ;
			for(int i=0;i<jb;i++)
			{
				String s1[] = bro.readLine().split(" ") ;
				int a = Integer.parseInt(s1[0]) ;
				int b = Integer.parseInt(s1[1]) ;
				int d = Integer.parseInt(s1[2]) ;
				jigglypuffArea(a-1,b-1,d,n,m,H) ;
			}
			Point source = new Point(0,0) ;
			List<Point> they = bfs(source,H,n,m) ;
			Point destination = new Point(n-1,m-1) ;
			for(int i=0;i<they.size();i++)
			{
				Point pe = they.get(i) ;
				if(pe.equals(destination))
				{
					destination.par = pe.par ;
					
				}
			}
			Point temp = destination ;
			int counter = 0;
			while(!(temp.equals(source) || temp.par == temp))
			{
				counter++ ;
				temp = temp.par ;
			}
			System.out.println((temp.equals(source)?counter:"Impossible.")) ;
		}
	}
	static void jigglypuffArea(int x,int y,int loud,int n,int m,HashSet<Point> H)
	{
		//Now make a 2d times 2d  imaginary matrix.
		for(int i=x-loud;i<=x+loud;i++)
		{
			for(int j=y-loud;j<=y+loud;j++)
			{
				if(i>=0 && j>=0 && i<n && j<m)
				{
					if(inCircle(i,j,x,y,loud))
						H.add(new Point(i,j)) ;
				}
			}
		}
		
	}
	static boolean inCircle(int a,int b,int x,int y,int r)//(x,y)--> Circle's center
	{
		
		return (Math.pow(a-x,2)+Math.pow(b-y,2)-Math.pow(r,2)<=0?true:false) ;
	}
	static List<Point> bfs(Point source,HashSet<Point> H,int n,int m)
	{
		boolean[][] visited = new boolean[n][m] ;
		List<Point> they = new ArrayList<Point>() ;
		Queue<Point> Q = new ArrayDeque<Point>() ;
		Q.add(source) ;
		while(!Q.isEmpty())
		{
			Point temp = Q.poll() ;
			
			if(visited[temp.a][temp.b])
				continue ;
			if(debug) 
			{
				System.out.println("Visited :"+temp.a+","+temp.b) ; 
			}
			visited[temp.a][temp.b] = true ;
			for(int i=0;i<4;i++)
			{
				int r = temp.a+dr[i] ;
				int c = temp.b+dc[i] ;
				Point pt = new Point(r,c) ;
				if(r>=0 && c>=0 && r<n && c<m && !H.contains(pt) && !visited[r][c])
				{
					pt.par = temp ;
					Q.add(pt) ;
					if(debug) System.out.println("	Added ("+pt.a+","+pt.b+") to the Queue.") ;
				}
			}
			they.add(temp) ;
		}
		return they ;
	}
}

class Point
{
	int a,b ;
	Point par = this ;
	Point(int x,int y)
	{
		a = x ;
		b = y ;
	}
	@Override
	public boolean equals(Object obj)
	{
		if(this==obj)
			return true ;
		if(obj==null)
			return false ;
		if(!(obj instanceof Point))
			return true ;
		Point p = (Point)obj ;
		if(p.a == this.a && p.b==this.b)
			return true ;
		else return false ;
	}
	@Override
	public int hashCode()
	{
		return Objects.hash(this.a,this.b) ;
	}
}
