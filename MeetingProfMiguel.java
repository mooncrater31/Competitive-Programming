import java.util.* ;
import java.io.BufferedReader ;
import java.io.InputStreamReader ;

public class MeetingProfMiguel
{
	public static void main(String args[]) throws Exception
	{
		BufferedReader bro = new BufferedReader(new InputStreamReader(System.in)) ;
		for(String S = bro.readLine();;S = bro.readLine())
		{
			int N = Integer.parseInt(S) ;
			int[][] Y = new int[26][26] ;
			int[][] M = new int[26][26] ;
			for(int i=0;i<26;i++)
			{
				for(int j=0;j<26;j++)
				{
					Y[i][j] = Integer.MAX_VALUE ;
					M[i][j] = Integer.MAX_VALUE ;
				}
			}
			if(N==0) break ;
			for(int i=0;i<N;i++)
			{
				String[] temp = bro.readLine().split(" ") ;
				int a = (int)temp[2].charAt(0)-65 ;
				int b = (int)temp[3].charAt(0)-65 ;
				if(temp[0].equals("Y"))
				{
					Y[a][b] = Integer.parseInt(temp[4]) ;
					if(temp[1].equals("B")) ;
						Y[b][a] = Integer.parseInt(temp[4]) ;
				}
				else
				{
					M[a][b] = Integer.parseInt(temp[4]) ;
					if(temp[1].equals("B")) ;
						M[b][a] = Integer.parseInt(temp[4]) ;
				}
			}
			String[] temp2 = bro.readLine().split(" ") ;
			int So = (int)temp2[0].charAt(0)-65 ;
			int D = (int)temp2[1].charAt(0)-65 ;
			
			Pair p = solve(M,Y,So,D) ;
			System.out.println(p.index==-1?"You will never meet.":p.energy+" "+p.index) ;
		}
	}
	static Pair solve(int[][] M,int[][] Y,int S,int D)
	{
		int[][] DP = new int[M.length][M.length] ;
		int[][] DP2 = new int[M.length][M.length] ;
		for(int i=0;i<DP.length;i++)
		{
			for(int j=0;j<DP.length;j++)
			{
				DP[i][j] = M[i][j] ;
				DP2[i][j] = Y[i][j] ;
			}
		}
		for(int k=0;k<DP.length;k++)
		{
			for(int i=0;i<DP.length;i++)
			{
				for(int j=0;j<DP.length;j++)
				{
					DP[i][j] = Math.min(DP[i][j],(DP[i][k]==Integer.MAX_VALUE||DP[k][j]==Integer.MAX_VALUE)?Integer.MAX_VALUE:DP[i][k]+DP[k][j]) ;
					DP2[i][j] = Math.min(DP2[i][j],(DP2[i][k]==Integer.MAX_VALUE||DP2[k][j]==Integer.MAX_VALUE)?Integer.MAX_VALUE:DP2[i][k]+DP2[k][j]) ;
				}
			}
		}
		int min=Integer.MIN_VALUE,index = -1 ;
		for(int k=0;k<DP.length;k++)
		{
			if(DP[S][k]+DP2[D][k]<min)
			{
				min = DP[S][k]+DP2[D][k] ;
				index = k ;
			}
		}
		return new Pair(min,index) ;
	}
}
class Pair
{
	int energy,index ;
	Pair(int e,int i)
	{
		this.energy = e ;
		this.index = i ;
	}
}
