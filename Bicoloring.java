import java.util.* ;
import java.io.BufferedReader ;
import java.io.InputStreamReader ;
/*Bugs*
#1 Input Mismatch.
-> n = Integer.parseInt(s), and put it above everything else.

*/
public class Bicoloring
{
	private static final boolean debug = false ;
	static boolean bfs_coloring(int[][] M)
	{
		int n = M.length ;
		int[] color = new int[n] ;
		Queue<Integer> Q = new ArrayDeque<Integer>() ;
		Q.add(0) ;
		color[0] = 1 ;
		boolean bipartite = true ;
		while(!Q.isEmpty() && bipartite)
		{
			int val = Q.poll() ;
			for(int i=0;i<n;i++)
			{
				if(M[val][i]==1)
				{
					if(color[val]==color[i])
					{
						bipartite = false ;
						break ;
					}
					else if(color[i]==0)
					{//is Visited for the first time.
						color[i] = -1*color[val] ;
						Q.add(i) ;
					}
				}
			}
		}
		return bipartite ;
	}
	public static void main(String args[]) throws Exception
	{
		BufferedReader bro = new BufferedReader(new InputStreamReader(System.in)) ;
		for(String s = bro.readLine();;s = bro.readLine())
		{
			// String[] S = s.split(" ") ;
			int n = Integer.parseInt(s) ;
			if(debug) System.out.println("n is :"+n) ;
			if(n==0)
				break ;
			int e = Integer.parseInt(bro.readLine()) ;
			int[][] M = new int[n][n] ;
			for(int i=0;i<e;i++)
			{
				String[] S1 = bro.readLine().split(" ") ;
				int a = Integer.parseInt(S1[0]) ;
				int b = Integer.parseInt(S1[1]) ;
				M[a][b] = 1 ;
				M[b][a] = 1 ;
			}
			System.out.println(bfs_coloring(M)?"BICOLORABLE.":"NOT BICOLORABLE.") ;
		}
		
	}
	
}