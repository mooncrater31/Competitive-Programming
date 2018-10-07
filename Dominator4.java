import java.util.* ;
import java.io.BufferedReader ;
import java.io.InputStreamReader ;
public class Dominator4
{
	static boolean[] dfs(int i,int[][] A,boolean[] visited)
	{
		// System.out.println("Visited :"+i) ;
		if(visited[i])
			return visited ;
		visited[i] = true ;
		for(int j=0;j<A[i].length;j++)
		{
			if(!visited[j] && A[i][j]==1 )
				dfs(j,A,visited) ;
		}
		return visited ;
	}
	public static void main(String args[]) throws Exception
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)) ;
		// Scanner in  = new Scanner(System.in) ;
		int t = Integer.parseInt(br.readLine()) ;
		// boolean[][][] ANS = new boolean[t][][] ;
		for(int i=0;i<t;i++)
		{
			
			int n = Integer.parseInt(br.readLine()) ;
			boolean ans[][] = new boolean[n][n] ;
			// ANS[i] = new boolean[n][n] ;
			int[][] A = new int[n][n] ;
			for(int j=0;j<n;j++)
			{
				String s = br.readLine() ;
				String[] s1 = s.split(" ") ;
				for(int k=0;k<n;k++)
				{
					A[j][k] = Integer.parseInt(s1[k]) ;
				}
			}
			
			// boolean[][] ans = new boolean[n][n] ;
			boolean[] visited = new boolean[n] ;
			dfs(0,A,visited) ;
			// Arrays.fill(ANS[i][0],true) ;
			for(int j=0;j<n;j++)
			{
				ans[0][j] = visited[j] ;
			}
			
			for(int j=1;j<n;j++)
			{//selection of the vertex, whose outgoing edges will be deleted
				if(visited[j])
				{
					int[] rep = new int[n] ;//2
					int[] temp = A[j] ;//2
					A[j] = rep ;//2
					dfs(0,A,ans[j]) ;//2
					A[j] = temp ;//2
					for(int k=0;k<n;k++)
					{
						if(visited[k])//if the vertex is reachable from the source node
							ans[j][k] = !ans[j][k] ;
					}
							
					ans[j][j] = true ;//reverting it back to true
				}
				
			}
			System.out.println("Case "+(i+1)+":") ;

			String breakLine = "" ;
			for(int j=0;j<2*n+1;j++)
			{
				if(j==0||j==2*n)
				{ 	
					breakLine+="+" ;
				}
				else
				{
					breakLine+="-" ;
				}
			}
			for(int j=0;j<ans.length;j++)
			{
				System.out.println(breakLine) ;
				System.out.print("|") ;
				for(int k=0;k<ans[j].length;k++)
				{
					System.out.print(ans[j][k]?"Y|":"N|") ;
				}
				System.out.println() ;
			}
			System.out.println(breakLine) ;
		}
	}
}