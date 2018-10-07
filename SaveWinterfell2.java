import java.util.* ;
import java.io.BufferedReader ;
import java.io.InputStreamReader ;

public class SaveWinterfell2
{
	private static final boolean debug = false ;
	public static void main(String args[]) throws Exception
	{
		BufferedReader bro = new BufferedReader(new InputStreamReader(System.in)) ;
		int T = Integer.parseInt(bro.readLine()) ;
		for(int t=0;t<T;t++)
		{
			int N = Integer.parseInt(bro.readLine()) ;
			// int[][] M = new int[N+1][N+1] ;
			ArrayList<ArrayList<Pair>> M = new ArrayList<ArrayList<Pair>>() ;
			for(int i=0;i<=N;i++)
			{
				M.add(new ArrayList<Pair>()) ;
				//for(int j=0;j<=N;j++)
				//{
				//	M.get(i).add(new Integer(0)) ;
				//}
			}
			for(int i=0;i<N-1;i++)
			{
				String[] S = bro.readLine().split(" ") ;
				int a = Integer.parseInt(S[0]) ;
				int b = Integer.parseInt(S[1]) ;
				int w = Integer.parseInt(S[2]) ;
				// M[a][b] = w ;
				// M[b][a] = w ;
				//M.get(a).set(b,w) ;
				M.get(a).add(new Pair(b,w)) ;
				//M.get(b).set(a,w) ;
				M.get(b).add(new Pair(a,w)) ;
			}
			// System.out.println(solve(M)) ;
			Pair largest = solve(M,1) ;
			System.out.println(solve(M,largest.num).dist) ;
			
		}
	}
	static Pair solve(ArrayList<ArrayList<Pair>> A,int src)
	{
		ArrayDeque<Pair> DQ = new ArrayDeque<Pair>() ;
		boolean[] visited = new boolean[A.size()] ;
		DQ.add(new Pair(src,0)) ;
		visited[src] = true ;
		Pair largest = new Pair(-1,Integer.MIN_VALUE) ;
		while(!DQ.isEmpty())
		{
			Pair temp = DQ.poll() ;
			// if(visited[temp.num])
				// continue ;
			visited[temp.num] = true ;
			if(largest.dist<temp.dist)
			{
				largest = temp ;
			}
			for(int i=0;i<A.get(temp.num).size();i++)
			{
				// int val = A.get(temp.num).get(i) ;
				// if(val!=0 && !visited[i])
				// {
					// DQ.add(new Pair(i,temp.dist+val)) ;
				// }
				Pair p = A.get(temp.num).get(i) ;
				if(!visited[p.num])
				{
					DQ.add(new Pair(p.num,p.dist+temp.dist)) ;
				}
				
			}
		}
		return largest ;
	}
}
class Pair
{
	int num,dist ;
	Pair(int n,int d)
	{
		this.num = n ;
		this.dist = d ;
	}
}