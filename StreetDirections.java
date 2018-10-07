import java.util.* ;
import java.io.BufferedReader ;
import java.io.InputStreamReader ;
/*Bugs:
#1: Wrong answer when there is a bridge present.
	-> dfs_low has all 0s.
	Input :
	7 9
	1 2
	1 3
	1 4
	2 4
	3 4
	4 5
	5 6
	5 7
	7 6
	0 0
	
Doesn't recognize the bridge 4-5.
-> The returned HashSet wasn't being added to the current HashSet, inside the articulationBridges function.
-> contains method of the class HashSet checks for equality by "equals". So, when I construct one new pair, then it'll consider it as a new object.
-> Overrode the hashCode() and equals() methods.
Solved.
#2: 
	
*/
public class StreetDirections
{
	private static final boolean disconnected = true ;
	private static final boolean debug = false ;
	private static final boolean debug2 = false ;
	public static void main(String args[]) throws Exception
	{
		BufferedReader bro = new BufferedReader(new InputStreamReader(System.in)) ;
		int cases = 0 ;
		for(String[] s = bro.readLine().split(" ");;s = bro.readLine().split(" "))
		{
			cases++ ;
			int n = Integer.parseInt(s[0]) ;
			int m = Integer.parseInt(s[1]) ;
			if(n==0 && m==0)
				break ;
			int[][] M = new int[n+1][n+1] ;
			for(int i=0;i<m;i++)
			{
				String[] s1 = bro.readLine().split(" ") ;
				int a = Integer.parseInt(s1[0]) ;
				int b = Integer.parseInt(s1[1]) ;
				M[a][b] = 1; 
				M[b][a] = 1 ;
			}
			// if(debug) {System.out.println("The adjacency matrix :") ;ArrayPrinter(M) ;}
			StreetDirections Str = new StreetDirections(n) ;
			HashSet<Pair> H = new HashSet<Pair>();
			if(disconnected)
			{
				for(int i=1;i<=n;i++)
				{
					if(!Str.visited[i])
					{
						H.addAll(Str.articulationBridges(i,M)) ;
					}
				}
			}
			else
			{
				H = Str.articulationBridges(1,M) ;
			}
			if(debug)
			{
				System.out.println("dfs_num :") ;
				for(int i:Str.dfs_num)
					System.out.print(i+" ") ;
				System.out.println("\ndfs_low :") ;
				for(int i:Str.dfs_low)
					System.out.print(i+" ") ;
				System.out.println() ;
				
			}
			if(debug) 
			{
				System.out.println("HashSet.size() = "+H.size()) ;
				System.out.println("Inside the hashset :") ;
				Iterator itr = H.iterator() ;
				while(itr.hasNext())
				{
					Pair p = (Pair)itr.next() ;
					System.out.println(p.a+" "+p.b) ;
				}
				System.out.println("----------------") ;
			}
			int[][] temp = M.clone() ;
			if(debug2){ArrayPrinter(temp);}
			boolean[] visited = new boolean[n+1] ;
			if(disconnected)
			{
				for(int i=1;i<=n;i++)
				{
					if(!visited[i])
					{
						dfs(i,temp,H,visited) ;
					}
				}
			}
			else
			{
				dfs(1,temp,H,visited) ;
			}
			if(debug2){System.out.println("temp after the DFS :");ArrayPrinter(temp);}
			System.out.println(cases+"\n") ;
			for(int i=1;i<=n;i++)
			{
				for(int j=1;j<=n;j++)
				{
					if(temp[i][j]==1)
						System.out.println(i+" "+j) ;
				}
			}
			System.out.println("#") ;
		}
		
	}
	static void dfs(int i,int[][] M, HashSet<Pair> Bridges,boolean[] visited)
	{
		if(visited[i])
			return ;
		visited[i] = true ;
		
		for(int j=1;j<M[i].length;j++)
		{
			if(M[i][j]==1)
			{
				Pair p1 = new Pair(i,j) ;
				// Pair p2 = new Pair(j,i) ;
				if(!Bridges.contains(p1))//
				{
					M[j][i] = 0 ;
				}
				
				dfs(j,M,Bridges,visited) ;
			}
		}
		
	}
	int root_children=0, dfs_root,dfs_iteration= 1 ;
	int[] dfs_low,dfs_num, dfs_parent ;
	boolean[] visited ;
	StreetDirections(int n)
	{
		dfs_low = new int[n+1] ;
		dfs_num = new int[n+1] ;
		dfs_parent = new int[n+1] ;
		visited = new boolean[n+1] ;
	}
	HashSet<Pair> articulationBridges(int v,int[][] M)// This might be causing the problems.
	{
		HashSet<Pair> Bridges  = new HashSet<Pair>();
		visited[v] = true ;
		dfs_num[v] = dfs_low[v] = dfs_iteration++ ;
		if(debug) System.out.println("Visited :"+v+" and has dfs_low :"+dfs_low[v]) ;
		for(int i=1;i<M[v].length;i++)
		{
			if(M[v][i]==1)
			{
				if(debug) {System.out.println("("+v+","+i+") is visited");} 
				if(visited[i])
				{
					if(dfs_parent[v]!=i)
					{
						dfs_low[v] = Math.min(dfs_low[v],dfs_num[i]) ;
						if(debug) System.out.println("		dfs_low["+v+"] is :"+dfs_low[v]+", "+i+" is already visited") ;
					}
				}
				else
				{
					if(debug) System.out.println("		"+i+" is visited for "+v) ;
					dfs_parent[i] = v ;
					if(i==dfs_root)
						root_children++ ;
					Bridges.addAll(articulationBridges(i,M)) ;
					if(debug){System.out.println("			Back to :"+v+" dfs_low["+i+"] = "+dfs_low[i]+" dfs_num["+v+"] = "+dfs_num[v]);}
					if(dfs_low[i]>dfs_num[v])
					{
						Bridges.add(new Pair(i,v)) ;
						Bridges.add(new Pair(v,i)) ;
						if(debug){System.out.println("("+i+","+v+") was added to the HashSet.") ;}
					}
					dfs_low[v] = Math.min(dfs_low[v],dfs_low[i]) ;	
				}
			}
		}
		
		return Bridges ;
	}
	static void ArrayPrinter(int[][] A)
	{
		for(int i=0;i<A.length;i++)
		{
			for(int j=0;j<A[0].length;j++)
			{
				System.out.print(A[i][j]+" ") ;
			}
			System.out.println() ;
			
		}
	}
}
class Pair
{
	int a,b ;
	Pair(int ta,int tb)
	{
		this.a = ta ; 
		this.b = tb ;
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
		Pair p = (Pair)obj ;
		return (this.a==p.a && this.b == p.b) ;
	}
	@Override
	public int hashCode()
	{
		return Objects.hash(this.a,this.b) ;
	}
	
}
