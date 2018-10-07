import java.util.* ;
import java.io.BufferedReader ;
import java.io.InputStreamReader ;
//UVa - 11049
/*
Suggestions:
#1 : Do the bfs only until the destination node is found.
#2 : Can I use stringBuffer here?
*/
/* Bugs:
#1 Only printed "S" for the sample input.
-> Solved. SOurce and destination points were made with reversed coordinates.

*/
public class BasicMaze
{
	static int[] dr = {-1,0,1,0} ;
	static int[] dc = {0,1,0,-1} ;
	public static void main(String args[]) throws Exception
	{
		BufferedReader bro = new BufferedReader(new InputStreamReader(System.in)) ;
		for(String S = bro.readLine();;S = bro.readLine())
		{
			String[] s = S.split(" ") ;
			int a = Integer.parseInt(s[0]) ;
			int b = Integer.parseInt(s[1]) ;
			if(a==0 && b==0)
				break ;
			String[] s1 = bro.readLine().split(" ") ;
			int c = Integer.parseInt(s1[0]) ;
			int d = Integer.parseInt(s1[1]) ;
			HashSet<Quadruple> H = new HashSet<Quadruple>() ;
			Point source = new Point(b,a); 
			Point destination = new Point(d,c) ;
			for(int i=0;i<3;i++)
			{
				String[] s2 = bro.readLine().split(" ") ;
				int alpha = Integer.parseInt(s2[0]) ;
				int beta = Integer.parseInt(s2[1]) ;
				int gamma = Integer.parseInt(s2[2]) ;
				int delta = Integer.parseInt(s2[3]) ;
				notAllowed(alpha,beta,gamma,delta,H) ;
			}
			List<Point> they  = bfs(source,H) ;
			
			for(int i=0;i<they.size();i++)
			{
				Point ptt = they.get(i) ;
				if(destination.equals(ptt))
				{
					destination.p = ptt.p ;
				}
			}
			System.out.println(directions(destination,source)) ;
			
		}
	}
	static String reverse(String s)
	{
		char[] in = new char[s.length()] ;
		for(int i=0;i<s.length();i++)
		{
			in[i] = s.charAt(s.length()-i-1) ;
		}
		return new String(in) ;
		
	}
	static String directions(Point destination,Point source) 
	{
		Point temp = destination ;
		String S = "" ;
		while(!temp.equals(source))
		{
			Point par = temp.p ;
			
			if(par.a == temp.a)
			{
				if(par.b>temp.b)
				{
					S+="W" ;
				}
				else S+="E" ;
			}
			else if(par.b == temp.b)
			{
				if(par.a >temp.a)
					S+="N" ;
				else S+="S" ;
			}
			temp = temp.p ;
		}
		return reverse(S) ;
	}
	static void notAllowed(int a,int b,int c,int d,HashSet<Quadruple> H)
	{
		//The plan: if a == c, thus same column (question convention), so we'll add (a,b+1::a+1,b+1),(a,b+2::a+1,b+2),...,(a,d::a+1,d)
		//If b==d, then like (1,2) to (5,2). (2,2::2,3),(3,2::3,3),(4,2::4,3),(5,2::5,3)
		// Invert the co-ordinates though.
		if(a==c)
		{
			for(int i=b+1;i<=d;i++)
			{
				if( a+1>0 && a+1<7 && i>0 && i<7)
				{
					Point p1 = new Point(i,a) ;
					Point p2 = new Point(i,a+1) ;
					H.add(new Quadruple(p1,p2)) ;
					H.add(new Quadruple(p2,p1)) ;
				}
			}
		}
		else if(b==d)
		{
			for(int i=a+1;i<=c;i++)
			{
				if(b+1>0 && b+1<7 && i>0 && i<7)
				{
					Point p1 = new Point(b,i) ;
					Point p2 = new Point(b+1,i) ;
					H.add(new Quadruple(p1,p2)) ;
					H.add(new Quadruple(p2,p1)) ;
				}
			}
		}
		
	}
	static List<Point> bfs(Point source,HashSet<Quadruple> H)
	{
		boolean[][] visited = new boolean[7][7] ;
		Queue<Point> Q = new ArrayDeque<Point>() ;
		List<Point> they = new ArrayList<Point>() ;
		Q.add(source) ;
		while(!Q.isEmpty())
		{
			Point p = Q.poll() ;
			visited[p.a][p.b] = true ;
			for(int i=0;i<4;i++)
			{
				int r = p.a + dr[i] ;
				int c = p.b + dc[i] ;
				Point pt = new Point(r,c) ;
				
				if(!H.contains(new Quadruple(p,pt)) && !H.contains(new Quadruple(pt,p)) && r>0 && r<7 && c>0 && c<7 && !visited[r][c]  )
				{
					pt.p = p ;
					Q.add(pt) ;
				}
			}
			they.add(p) ;
		}
		return they ;
	}
}
class Point
{
	int a,b ;
	Point p ;
	Point(int at,int bt)
	{
		a = at ;
		b = bt ;
	}
	@Override
	public boolean equals(Object obj)
	{
		if(this == obj)
			return true ;
		if(!(obj instanceof Point))
			return false ;
		if(obj == null)
			return false ;
		Point p = (Point)obj ;
		if(this.a == p.a && this.b == p.b)
			return true ;
		else return false ;
	}
	@Override
	public int hashCode()
	{
		return Objects.hash(this.a,this.b) ;
	}
}
class Quadruple
{
	Point p1,p2 ;
	Quadruple(Point p1t,Point p2t)
	{
		p1 = p1t ;
		p2 = p2t ;
	}
	@Override
	public boolean equals(Object obj)
	{
		if(this==obj)
			return true ;
		if(obj==null)
			return false ;
		if(!(obj instanceof Quadruple))
			return false ;
		Quadruple q = (Quadruple)obj ;
		if(this.p1.a == q.p1.a && this.p1.b == q.p1.b && this.p2.a ==this.p2.a && this.p2.b == this.p2.b)
		{
			return true ;
		}
		else return false ;
	}
	@Override
	public int hashCode()
	{
		return Objects.hash(this.p1.a,this.p1.b,this.p2.a,this.p2.b) ;
	}
}
