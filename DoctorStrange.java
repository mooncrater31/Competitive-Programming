import java.util.* ;
import java.io.BufferedReader ;
import java.io.InputStreamReader ;

public class DoctorStrange
{
	private static final boolean debug = false ;
	static int[] dfs_low,dfs_num,dfs_parent ;
	static boolean[] articulation_point,visited ;
	static int dfs_counter,root,rootChildren ;
	DoctorStrange(int n)
	{
		dfs_low = new int[n] ;
		dfs_num = new int[n] ;
		dfs_parent = new int[n] ;
		articulation_point = new boolean[n] ;
		visited = new boolean[n] ;
	}
	public static void main(String args[]) throws Exception
	{
		BufferedReader bro = new BufferedReader(new InputStreamReader(System.in)) ;
		int T = Integer.parseInt(bro.readLine()) ;
		for(int t=0;t<T;t++)
		{
			String[] S = bro.readLine().split(" ") ;
			int n = Integer.parseInt(S[0]) ;
			int m = Integer.parseInt(S[1]) ;
			List<ArrayList<Integer>> L = new ArrayList<ArrayList<Integer>>() ;
			for(int i=0;i<n;i++)
			{
				L.add(new ArrayList<Integer>()) ;
			}
			// int[][] M = new int[n][n] ;
			for(int i=0;i<m;i++)
			{
				S = bro.readLine().split(" ") ;
				int a = Integer.parseInt(S[0])-1 ;
				int b = Integer.parseInt(S[1])-1 ;
				// M[a][b] = 1 ;
				// M[b][a] = 1 ;
				L.get(a).add(b) ;
				L.get(b).add(a) ;
			}
			DoctorStrange D = new DoctorStrange(n) ;
			dfs_counter = 0 ;
			for(int i=0;i<n;i++)
			{
				if(!visited[i])
				{
					root = i ;
					rootChildren = 0 ;
					visited[root] = true ;
					D.solve(L,i) ;
					articulation_point[i] = rootChildren>1 ;
				}
			}
			if(debug)
			{
				System.out.println(Arrays.toString(articulation_point)) ;
				System.out.println(Arrays.toString(dfs_low)) ;
				System.out.println(Arrays.toString(dfs_num)) ;
				System.out.println(Arrays.toString(dfs_parent)) ;
			}
			int counter = 0;
			for(boolean a:articulation_point)
			{
				if(a)
					counter++ ;
			}
			System.out.println(counter) ;
			
		}
	}
	void solve(List<ArrayList<Integer>> L,int u)//using an adjacency list
	{
		dfs_low[u] = dfs_counter ;
		dfs_num[u] = dfs_counter ;
		if(debug) System.out.println("dfs_counter :"+dfs_counter+" u:"+u) ;
		dfs_counter++ ;
		for(int j=0;j<L.get(u).size();j++)
		{
			int i = L.get(u).get(j) ;
			if(!visited[i])
			{
				visited[i] = true ;
				dfs_parent[i] = u ;
				if(u==root) rootChildren++ ;
				solve(L,i) ;
				if(dfs_low[i]>=dfs_num[u])
					articulation_point[u] = true ;
				dfs_low[u] = Math.min(dfs_low[u],dfs_low[i]) ;
			}
			else
			{
				dfs_low[u] = Math.min(dfs_low[u],dfs_num[i]) ;
			}
			
			
		}
	}
	
	
	
}
