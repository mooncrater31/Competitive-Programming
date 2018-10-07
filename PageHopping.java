import java.util.* ;
import java.io.BufferedReader ;
import java.io.InputStreamReader ;
import java.text.DecimalFormat ;
//UVa-821
public class PageHopping
{
	private static final boolean debug = false ;
	public static void main(String[] args) throws Exception
	{
		BufferedReader bro = new BufferedReader(new InputStreamReader(System.in)) ;
		int t = 0 ;
		for(String S = bro.readLine();!S.equals("0 0");S = bro.readLine())
		{
			t++ ;
			String[] S1 = S.split(" ") ;//This might be a tab too.
			int max = Integer.MIN_VALUE ;
			for(String s:S1)
				if(Integer.parseInt(s)>max) max = Integer.parseInt(s) ;
			int[][] M = new int[max+1][max+1] ;
			int edges = 0 ;
			for(int i=0;i<S1.length/2;i++)
			{
				int a = Integer.parseInt(S1[2*i]) ;
				int b = Integer.parseInt(S1[2*i+1]) ;
				M[a][b] = 1 ;
				// edges++ ;
			}
			int[][] DP = floydWarshall(M) ;
			if(debug)
			{
				System.out.println("edges = "+edges) ;
				System.out.println("M:") ;
				arrayPrinter(M) ;
				System.out.println("DP:") ;
				arrayPrinter(DP) ;
			}
			int sum = 0 ;
			for(int i=1;i<DP.length;i++)
			{
				for(int j=1;j<DP.length;j++)
				{
					// sum+=(DP[i][j]==Integer.MAX_VALUE/4)?0:DP[i][j] ;
					if(DP[i][j]!=0 && DP[i][j]!=Integer.MAX_VALUE/4)
					{
						edges++ ;
						sum+=DP[i][j] ;
					}
				}
				
			}
			// System.out.println("Case "+t+": average length between pages = "+String.format("%.3f",(float)sum/edges)+" clicks") ;
			DecimalFormat df = new DecimalFormat("#.000") ;
			System.out.println("Case "+t+": average length between pages = "+df.format((double)sum/edges)+" clicks") ;
			
		}
	}
	static void arrayPrinter(int[][] A)
	{
		for(int i=0;i<A.length;i++)
		{
			for(int j=0;j<A[i].length;j++)
			{
				System.out.print(A[i][j]+" ") ;
			}
			System.out.println() ;
		}
	}
	static int[][] floydWarshall(int[][] M)
	{
		int[][] DP = new int[M.length][M.length] ;
		for(int i=1;i<DP.length;i++)
		{
			for(int j=1;j<DP.length;j++)
			{
				DP[i][j] = M[i][j]==0?Integer.MAX_VALUE/4:M[i][j] ;
				if(i==j) DP[i][j] = 0 ;
			}
		}
		if(debug)
		{
			System.out.println("THIS :") ;
			arrayPrinter(DP) ;
		}
		for(int k=1;k<DP.length;k++)
		{
			for(int i=1;i<DP.length;i++)
			{
				for(int j=1;j<DP.length;j++)
				{
					DP[i][j] = Math.min(DP[i][j],DP[i][k]+DP[k][j]) ;
				}
			}
		}
		return DP ;
	}
}