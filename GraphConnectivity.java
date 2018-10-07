import java.util.* ;
import java.io.BufferedReader ;
import java.io.InputStreamReader ;
public class GraphConnectivity
{
	static void dfs(int i,boolean[] visited,int[][] A)
	{
		if(visited[i])
			return ;
		visited[i] = true ;
		for(int j=0;j<A[i].length;j++)
		{
			if(!visited[j] && A[i][j]==1)
				dfs(j,visited,A) ;
		}
	}
	public static void main(String args[]) throws Exception
	{ 
		BufferedReader bro = new BufferedReader(new InputStreamReader(System.in)) ;
		int T = Integer.parseInt(bro.readLine()) ;
		bro.readLine() ;
		for(int t=0;t<T;t++)
		{
			// bro.readLine() ;//dummy line
			int n = (int)bro.readLine().charAt(0)-64 ;//Number of vertices
			int A[][] =  new int[n][n] ;
			for(String s = bro.readLine();!s.equals("") ;s = bro.readLine())//#1
			{
				// String[] temp = s.split("") ;
				int a = (int)s.charAt(0)-65,b = (int)s.charAt(1)-65 ;
				
				A[a][b] = 1 ;
				A[b][a] = 1 ;
			}
			boolean[] visited = new boolean[n] ;
			int count = 0 ;
			for(int i=0;i<n;i++)
			{
				
				if(!visited[i])
				{
					//System.out.println("Not visited :"+i) ;
					count++ ;
					dfs(i,visited,A) ;
				}
			}
			System.out.println(count) ;
			System.out.println() ;
		}
	}
}