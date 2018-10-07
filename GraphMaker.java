import java.util.* ;
import java.io.BufferedReader ;
import java.io.InputStreamReader ;
/*
Format = 1
n m
and edges (a,b) as "a b" until -1 -1.
*/
public class GraphMaker
{
	private static final int format = 1 ;
	public static void main(String args[]) throws Exception
	{
		int[][] M ;
		BufferedReader bro = new BufferedReader(new InputStreamReader(System.in)) ;
		if(format==1)
		{
				
				String[] s = bro.readLine().split(" ") ;
				int n = Integer.parseInt(s[0]) ;
				int m = Integer.parseInt(s[1]) ;
				// if(n==-1 && m==-1)
					// break ;
				
				M = new int[n][n] ;
				for(String S1 = bro.readLine();;S1 = bro.readLine())
				{
					String[] s1 = S1.split(" ") ;
					int a = Integer.parseInt(s1[0]) ;
					int b = Integer.parseInt(s1[1]) ;
					if(a==-1 && b==-1)
						break ;
					M[a][b] = 1;
					M[b][a] = 1;					
				}
			
			for(int i=0;i<M.length;i++)
			{
				for(int j=0;j<M[i].length;j++)
				{
					System.out.print(M[i][j]+(j==M[i].length-1?"":",")) ;
				}
				System.out.println() ;
			}
		}
	}
}