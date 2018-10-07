import java.util.* ;
import java.io.BufferedReader ;
import java.io.InputStreamReader ;
/* Bugs
#1 Wrong answer when no edges, one vertex.
-> Changed the ans to Math.max(Math.min(ones,minus_ones),1) 
Solved.
#2 Wrong answer for:
1
13 10
0 5
5 4
5 3
5 2
5 1
5 6
7 8
7 9
7 10
7 11
Should be 3, coming 5.
-> Was counting ones and minus_ones after everything was done. One connected component's one isn't related to another's one. Therefore, had to sum the minimum
of ones and minus_ones for every connected component.
Solved.

*/
public class PlaceTheGaurds
{
	private static final boolean debug = false ;
	static int bfs_coloring(int[][] M)
	{
		int n = M.length ;
		int[] color = new int[n] ;
		int min = 0 ;
		
		HashSet<Integer> H = new HashSet<Integer>() ;
		for(int i=0;i<n;i++)
			H.add(i) ;
		while(!H.isEmpty())
		{
			Iterator itr = H.iterator() ;
			int init = (int)itr.next() ;
			int ones = 0 ,minus_ones = 0 ;
			Queue<Integer> Q = new ArrayDeque<Integer>() ;
			Q.add(init) ;
			H.remove(init) ;
			if(debug) System.out.println("init :"+init) ;
			color[init] = 1 ;
			boolean bipartite = true ;
			while(!Q.isEmpty() && bipartite)
			{
				int val = Q.poll() ;
				if(color[val]==1)
					ones++ ;
				else
					minus_ones++ ;
				if(debug) System.out.println("ones :"+ones+" minus_ones :"+minus_ones) ;
				H.remove(val) ;
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
			min = min +Math.max(1,Math.min(ones,minus_ones)) ;
			if(!bipartite)
			{
				return -1 ;
			}
			// int ones = 0,minus_ones = 0 ;
			
			
		}
		// for(int i=0;i<n;i++)
		// {
			// if(color[i]==1)
				// ones++ ;
			// else if(color[i]==-1)
				// minus_ones++ ;
		// }
		if(debug)
		{
			// System.out.println("ones + minus_ones = "+(ones+minus_ones)+" and n = "+n) ;
			ArrayPrinter(color) ;
		}
		return min ;
	}
	static void ArrayPrinter(int[] A)
	{
		for(int i:A)
			System.out.print(i+" ") ;
	}
	public static void main(String args[]) throws Exception
	{
		BufferedReader bro = new BufferedReader(new InputStreamReader(System.in)) ;
		int T = Integer.parseInt(bro.readLine()) ;
		for(int t=0;t<T;t++)
		{
			String[] S = bro.readLine().split(" ") ;
			int n = Integer.parseInt(S[0]),m = Integer.parseInt(S[1]) ;
			int[][] M = new int[n][n] ;
			for(int i=0;i<m;i++)
			{
				String[] S1 = bro.readLine().split(" ") ;
				int a = Integer.parseInt(S1[0]),b = Integer.parseInt(S1[1]) ;
				M[a][b] = 1 ;
				M[b][a] = 1 ;
			}
			System.out.println(bfs_coloring(M)) ;
		}
		
	}
}