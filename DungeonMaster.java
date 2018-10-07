import java.util.* ;
import java.io.BufferedReader ;
import java.io.InputStreamReader ;
//UVa-532
/*
Bugs:
#1 :  Wrong output for the input :
3 4 5
S....
.###.
.##..
###.#

#####
#####
##.##
##...

#####
#####
#.###
####E

My answer:
Trapped!
Correct answer :
Escaped in 11 minute(s).
-> Solved. >=0 , not >0.
*/
public class DungeonMaster
{
	private static final boolean debug = false ;
	static int[] dr = {-1,0,1,0,0,0} ;
	static int[] dc = {0,-1,0,1,0,0} ;
	static int[] dl = {0,0,0,0,1,-1} ;
	public static void main(String args[]) throws Exception
	{
		BufferedReader bro = new BufferedReader(new InputStreamReader(System.in)) ;
		for(String S = bro.readLine();;S = bro.readLine())
		{
			String[] s = S.split(" ") ;
			int L = Integer.parseInt(s[0]) ;
			int N = Integer.parseInt(s[1]) ;
			int M = Integer.parseInt(s[2]) ;
			if(L==0 && N==0 && M==0)
				break ;
			Point source = null ,exit = null ;
			HashSet<Point> H = new HashSet<Point>() ;
			for(int l=0;l<L;l++)
			{
				for(int n=0;n<N;n++)
				{
					String s1 = bro.readLine() ;
					for(int m=0;m<M;m++)
					{
						Point tempte = new Point(l,n,m) ;
						if(s1.charAt(m)=='#')
							H.add(tempte) ;
						else if(s1.charAt(m)=='S')
							source = tempte ;
						else if(s1.charAt(m)=='E')
							exit = tempte ;
					}
				}
				bro.readLine() ;//to accumulate the newline
			}
			List<Point> they = bfs(source,H,L,N,M) ;
			for(int i=0;i<they.size();i++)
			{
				Point p = they.get(i) ;
				if(p.equals(exit))
				{
					exit.par = p.par ;
					break ;
				}
			}
			Point temp = exit ;
			int counter = 0 ;
			while(!temp.equals(source)&&!(temp.par == temp) )
			{
				counter++ ;
				temp = temp.par ;
			}
			if(temp == source)
				System.out.println("Escaped in "+counter+" minute(s).") ;
			else 
				System.out.println("Trapped!") ;
		}
	}
	static List<Point> bfs(Point source,HashSet<Point> H,int l,int n,int m)
	{
		boolean[][][] visited = new boolean[l][n][m] ;
		Queue<Point> Q = new ArrayDeque<Point>() ;
		List<Point> they = new ArrayList<Point>() ;
		Q.add(source) ;
		while(!Q.isEmpty())
		{
			Point p = Q.poll() ;
			if(visited[p.a][p.b][p.c] == true)
				continue ;
			if(debug) System.out.println("Point visited : ("+p.a+","+p.b+","+p.c+")") ;
			visited[p.a][p.b][p.c] = true ;
			for(int i=0;i<6;i++)
			{
				int ll = p.a+dl[i] ;
				int rr = p.b+dr[i] ;
				int cc = p.c+dc[i] ;
				if(debug) System.out.println("	Point checked : ("+ll+","+rr+","+cc+")") ;
				Point p_temp = new Point(ll,rr,cc) ;
				if(ll>=0 && rr>=0 && cc >=0 && ll<l && rr<n && cc < m && !H.contains(p_temp) && !visited[ll][rr][cc])
				{
					
					p_temp.par = p ;
					Q.add(p_temp) ;
				}
			}
			they.add(p) ;
		}
		return they ;
	}
	
	
	

}
class Point
{//a->level,b-> row, c->column
	int a,b,c ;
	Point par = this ;
	Point(int x,int y,int z)
	{
		a = x ;
		b = y ;
		c = z ;
	}
	@Override
	public boolean equals(Object obj)
	{
		if(this==obj)
			return true ;
		if(!(obj instanceof Point))
			return false ;
		if(obj == null)
			return false ;
		Point p = (Point)obj ;
		if(p.a==this.a && p.b == this.b && p.c == this.c)
			return true ;
		else return false ;
	}
	@Override
	public int hashCode()
	{
		return Objects.hash(this.a,this.b,this.c) ;
	}
}