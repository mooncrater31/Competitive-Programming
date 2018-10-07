import java.util.* ;
import java.io.BufferedReader ;
import java.io.InputStreamReader ;
// import Collections.sort ;
/*
Bugs:
#1: Splitting the "number of connected servers". See: https://stackoverflow.com/questions/9604945/string-splitpattern-throws-exception-due-to-in-pattern-java-util-regex-patt
Solved.
	
*/
public class CriticalLinks
{
	public static void main(String args[]) throws Exception
	{
		BufferedReader bro = new BufferedReader(new InputStreamReader(System.in)) ;
		for(String s = bro.readLine();!s.equals("")&& !s.equals(null);s = bro.readLine())
		{
			int n = Integer.parseInt(s) ;
			if(n==0)
			{
				System.out.println("0 critical links\n") ;
				bro.readLine() ;
				continue ;
			}
			int[][] M = new int[n][n] ;
			for(int i=0;i<n;i++)
			{
				String[] S1 = bro.readLine().split(" ") ;
				int a = Integer.parseInt(S1[0]) ;
				int m = numGetter(S1[1]) ;
				for(int j=0;j<m;j++)
				{
					int b = Integer.parseInt(S1[2+j]) ;
					M[a][b] = 1 ;
					M[b][a] = 1 ;
				}
			}
			CriticalLinks C = new CriticalLinks(n) ;
			HashSet<Pair> H = new HashSet<Pair>() ;
			for(int i=0;i<n;i++)
			{
				if(!C.visited[i])
				{
					H.addAll(C.articulationBridges(i,M)) ;
				}
				
			}
			System.out.println(H.size()+" critical links") ;
			List<Pair> lst = new ArrayList<Pair>(H) ;
			Collections.sort(lst) ;
			for(Pair p:lst)
			{
				System.out.println(p.a+" - "+p.b) ;
			}
			System.out.println() ;
			bro.readLine() ;
			
		}
	}
	static int numGetter(String S)
	{
		String[] s1  = S.split("\\(") ;
		String[] s2 = s1[1].split("\\)") ;
		return Integer.parseInt(s2[0]) ;
	}
	
	int dfs_iteration = 0 ;
	int[] dfs_low,dfs_num,dfs_parent ;
	boolean[] visited ;
	CriticalLinks(int n)
	{
		dfs_low = new int[n] ;
		dfs_num = new int[n] ;
		dfs_parent = new int[n] ;
		visited = new boolean[n] ;
	}
	HashSet<Pair> articulationBridges(int v,int[][] M)
	{
		HashSet<Pair> H = new HashSet<Pair>() ;
		visited[v] =  true ;
		dfs_num[v] = dfs_low[v] = dfs_iteration++ ;
		for(int i=0;i<M[v].length;i++)
		{
			if(M[v][i]==1)
			{
				if(visited[i])
				{
					if(dfs_parent[v]!=i)
						dfs_low[v] = Math.min(dfs_low[v],dfs_num[i]) ;
				}
				else
				{
					dfs_parent[i] = v ;
					H.addAll(articulationBridges(i,M)) ;
					if(dfs_low[i]>dfs_num[v])
					{
						
						if(i<v)
							H.add(new Pair(i,v));
						else
							H.add(new Pair(v,i)) ;
						// H.add(new Pair(v,i)) ;
					}
					dfs_low[v] = Math.min(dfs_low[v],dfs_low[i]) ;
				}
			}
		}
		return H ;
	}
}
class Pair implements Comparable<Pair>
{
	int a,b ;
	Pair(int aa,int bb)
	{
		this.a = aa ;
		this.b = bb ;
	}
	@Override
	public boolean equals(Object obj)
	{
		if(this==obj)
			return true ;
		if(obj==null)
			return false ;
		if(!(obj instanceof Pair))
			return false ;
		Pair p =  (Pair)obj ;
		return (p.a==this.a && p.b == this.b) ;
	}
	@Override
	public int hashCode()
	{
		return Objects.hash(this.a,this.b) ;
	}
	@Override
	public int compareTo(Pair p2)
	{
		return this.a - p2.a ;
	}
}
