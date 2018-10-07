import java.util.* ;
import java.io.BufferedReader ;
import java.io.InputStreamReader ;

public class SaveWinterfell
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
			ArrayList<ArrayList<Integer>> M = new ArrayList<ArrayList<Integer>>() ;
			for(int i=0;i<=N;i++)
			{
				M.add(new ArrayList<Integer>()) ;
				for(int j=0;j<=N;j++)
				{
					M.get(i).add(new Integer(0)) ;
				}
			}
			for(int i=0;i<N-1;i++)
			{
				String[] S = bro.readLine().split(" ") ;
				int a = Integer.parseInt(S[0]) ;
				int b = Integer.parseInt(S[1]) ;
				int w = Integer.parseInt(S[2]) ;
				// M[a][b] = w ;
				// M[b][a] = w ;
				M.get(a).set(b,w) ;
				M.get(b).set(a,w) ;
			}
			System.out.println(solve(M)) ;
		}
	}
	static void arrayPrinter(ArrayList<ArrayList<Integer>> A)
	{
		for(int i=0;i<A.size();i++)
		{
			for(int j=0;j<A.get(i).size();j++)
			{
				System.out.print(A.get(i).get(j)+" ") ;
			}
			System.out.println() ;
		}
	}
	static int solve(ArrayList<ArrayList<Integer>> M)
	{
		// int[][] DP = new int[M.length][M.length] ;
		ArrayList<ArrayList<Integer>> DP = new ArrayList<ArrayList<Integer>>() ;
		
		for(int i=0;i<M.size();i++)
		{
			DP.add(new ArrayList<Integer>()) ;
			for(int j=0;j<M.get(i).size();j++)
			{
				// DP[i][j] = M[i][j]==0?Integer.MAX_VALUE:M[i][j] ;
				DP.get(i).add(M.get(i).get(j)==0?Integer.MAX_VALUE:M.get(i).get(j)) ;
			}
		}
		
		for(int k=0 ;k<DP.size();k++)
		{
			for(int i=0;i<DP.size();i++)
			{
				for(int j=0;j<DP.size();j++)
				{
					// DP[i][j] = Math.min(DP[i][j],(DP[i][k]==Integer.MAX_VALUE||DP[k][j]==Integer.MAX_VALUE)?Integer.MAX_VALUE:DP[i][k]+DP[k][j]) ;
					DP.get(i).set(j,Math.min(DP.get(i).get(j),(DP.get(i).get(k)==Integer.MAX_VALUE||DP.get(k).get(j)==Integer.MAX_VALUE)?Integer.MAX_VALUE:DP.get(i).get(k)+DP.get(k).get(j))) ;
					// if(i==j) DP[i][j] = Integer.MAX_VALUE 
				}
			}
			if(debug)
			{
				System.out.println("K :"+k) ;
				arrayPrinter(DP) ;
			}
		}
		int max = Integer.MIN_VALUE ;
		for(int i=0;i<DP.size();i++)
		{
			for(int j=0;j<DP.get(i).size();j++)
			{
				if(DP.get(i).get(j)!=Integer.MAX_VALUE && DP.get(i).get(j)>max)
					max = DP.get(i).get(j) ;
			}
		}
		if(debug)
		{
			System.out.println("M :") ;
			arrayPrinter(M) ;
			System.out.println("DP :") ;
			arrayPrinter(DP) ;
			
		}
		return max ;
	}
}