import java.util.* ;
import java.io.BufferedReader ;
import java.io.InputStreamReader ;
/*Bugs:
#1: 0 for some cases.

*/
public class TouristGuide
{
	private static final boolean debug = false ;
	private static final boolean debug2 = false ;
	public static void main(String args[]) throws Exception
	{
		BufferedReader bro = new BufferedReader(new InputStreamReader(System.in)) ;
		int cases = 0  ;
		
		for(String s = bro.readLine();;s = bro.readLine())
		{
			
			int n = Integer.parseInt(s) ;
			if(n==0)
				break ;
			cases++ ;
			int[][] M = new int[n][n] ;
			HashMap<String,Integer> H = new HashMap<String, Integer>() ;
			String[] inp = new String[n] ;
			for(int i=0;i<n;i++)
			{
				inp[i] = bro.readLine() ;
				H.put(inp[i],i) ;
			}
			int m = Integer.parseInt(bro.readLine()) ;
			for(int i=0;i<m;i++)
			{
				String[] S = bro.readLine().split(" ") ;
				int a = H.get(S[0]),b = H.get(S[1]) ;
				M[a][b] = 1;
				M[b][a] = 1 ;
			}
			TouristGuide T = new TouristGuide(n) ;
			// T.articulation(0,M) ;//Assuming there is a route from one location to every other location.
			for(int i=0;i<n;i++)
			{
				if(!T.visited[i])
				{
					T.root_children = 0 ;
					T.dfs_root = i ;
					T.articulation(i,M) ;
					T.articulationPoints[T.dfs_root] = T.root_children>1?true:false ;
					if(debug2) System.out.println(" Visited :"+i) ;
				}
				
			}
			int count= 0 ;
			
			for(int i=0;i<n;i++)
			{
				if(T.articulationPoints[i]) count++ ;
			}
			String[] ans = new String[count] ;
			int temp= 0 ;
			for(int i=0;i<n;i++)
			{
				if(T.articulationPoints[i]) ans[temp++] = inp[i] ;
			}
			if(debug)
			{
				System.out.println("RootChildren :"+T.root_children+" DFS Root :"+T.dfs_root+" inp[dfs_root] "+inp[T.dfs_root]) ;
				
			}
			
			Arrays.sort(ans) ;
			System.out.println("City map #"+cases+": "+count+" camera(s) found") ;
			if(!debug2)
			for(String i:ans)
				System.out.println(i) ;
			System.out.println() ;
		}
	}
	TouristGuide(int n)
	{
		dfs_low = new int[n] ;
		dfs_num = new int[n] ;
		dfs_parent = new int[n] ;
		articulationPoints = new boolean[n] ;
		visited = new boolean[n] ;
	}
	int[] dfs_low,dfs_num,dfs_parent ;
	boolean[] articulationPoints, visited ;
	int dfs_iteration=0 ,root_children,dfs_root = 0 ;
	void articulation(int v,int[][] M)
	{
		visited[v] = true ;
		dfs_low[v] = dfs_num[v] = dfs_iteration++ ;
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
					if(v==dfs_root)
						root_children++ ;
					articulation(i,M) ;
					
					if(dfs_low[i]>=dfs_num[v])
						articulationPoints[v] =  true ;
					dfs_low[v] = Math.min(dfs_low[v],dfs_low[i]) ;
				}
			}
		}
	}
}
