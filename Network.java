import java.util.* ;
import java.io.BufferedReader ;
import java.io.InputStreamReader ;
/*Bugs:
#1: Stack Over flow.
-> placed the visited[v] at the top.
-> Made the condition "if(visited[i] && dfs_parent[u]!=i){}" to "if(visited[i]){if(dfs_parent[u]!=i){}}".
Solved.
#2: Wrong answer for:
5
5 1 2 3 4
0
0
Gives 2. Should be 1.
Solved: Was counting the source too.
*/
public class Network
{
	private static final boolean debug = true ;
	private static final boolean debug2 = true  ;
	private static final int version = 1 ;
	int[] dfs_parent,dfs_num,dfs_min ;
	int dfs_iteration = 1,dfs_root=1,root_children ;
	boolean[] visited,articulationPoint ;
	static void ArrayPrinter(int[] A)
	{
		for(int i=1;i<A.length;i++)
			System.out.print(i+" : "+A[i]+" ") ;
		System.out.println() ;
	}
	Network(int n)
	{
		dfs_parent = new int[n+1] ;
		dfs_num = new int[n+1] ;
		dfs_min = new int[n+1] ;
		visited = new boolean[n+1] ;
		articulationPoint = new boolean[n+1] ;
	}
	
	public static void main(String args[]) throws Exception
	{
		BufferedReader bro = new BufferedReader(new InputStreamReader(System.in)) ;
		for(String s = bro.readLine();;s = bro.readLine())
		{
			int n = Integer.parseInt(s) ;
			if(n==0)
				break ;
			int[][] M = new int[n+1][n+1] ;
			for(String S = bro.readLine();;S = bro.readLine())
			{//Taking the input
				String[] S1 = S.split(" ") ;
				int m = Integer.parseInt(S1[0]) ;
				if(m==0)
					break ;
				for(String temp:S1)
				{
					int var = Integer.parseInt(temp) ;
					M[m][var] = 1 ;
					M[var][m] = 1 ;
				}	
			}
			Network N = new Network(n) ;//This
			//No loop required, since each location is connected
			N.articulationPoints(1,M) ;
			N.articulationPoint[1] = N.root_children>1?true:false ;
			int counter=0; 
			for(int i=1;i<=n;i++) if(N.articulationPoint[i]){ counter++ ; if(debug) System.out.println("Articulation point at :"+i) ;}
			System.out.println(counter) ;
			if(debug2) 
			{
				System.out.println("dfs_min : ") ;
				ArrayPrinter(N.dfs_min) ;
				System.out.println("dfs_num :") ;
				ArrayPrinter(N.dfs_num) ;
				System.out.println("dfs_parent :") ;
				ArrayPrinter(N.dfs_parent) ;
			}
		}
	}
	void articulationPoints(int v,int[][] M)//And this
	{
		if(version==1)
		{
			visited[v] = true ;
			if(debug) System.out.println(v+" is visited.") ;
			dfs_num[v] = dfs_min[v] = dfs_iteration++ ;
			for(int i=0;i<M[v].length;i++)
			{
				if(M[v][i]==1)
				{
					if(visited[i])//point1
					{
						if(dfs_parent[v]!=i)
						{
							dfs_min[v] = Math.min(dfs_min[v],dfs_num[i]) ;
							if(debug2) 
								System.out.println("VISITED dfs_min["+v+"] = "+dfs_min[v]) ;
						}
					}
					else
					{
						
						dfs_parent[i] = v ;
						if(v==dfs_root)
							root_children++ ;
						articulationPoints(i,M) ;
						
						if(dfs_min[i]>=dfs_num[v])
						{
							if(debug) System.out.println(v+" was tried to be made into an articulation point.") ;
							articulationPoint[v] = true ;
						}
						dfs_min[v] = Math.min(dfs_min[v],dfs_min[i]) ;
						if(debug2){System.out.println("dfs_min["+v+"] = "+dfs_min[v]) ;}
					}
				}
			}
		}
		
		
	}
	
}