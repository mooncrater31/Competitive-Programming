import java.util.* ;
import java.io.BufferedReader ;
import java.io.InputStreamReader ;

public class MaximumSum
{
	public static void main(String args[]) throws Exception
	{
		BufferedReader bro = new BufferedReader(new InputStreamReader(System.in)) ;
		int N = Integer.parseInt(bro.readLine()) ;
		int[][] M = new int[N][N] ;
		int count = 0 ;
		for(String S = bro.readLine();count+1!=N*N  && !S.equals("");S = bro.readLine())
		{
			String[] S1 = S.split(" +") ;
			for(int i=0;i<S1.length;i++)
			{
				int x = (count+i)/N ;
				int y = (count+i)%N ;
				M[x][y] = Integer.parseInt(S1[i]) ;
			}
			count+=S1.length ;
		}
		System.out.println(solve(M)) ;
	}
	static int solve(int[][] M)
	{
		toRangeMatrix(M) ;
		int maxSubRect = -127*100000 ;
		for(int i = 0;i<M.length;i++)
		{
			for(int j=0;j<M.length;j++)
			{
				for(int k=i;k<M.length;k++)
				{
					for(int l=j;l<M.length;l++)
					{
						int subRect = M[k][l] ;
						if(i>0) subRect-= M[i-1][l] ;
						if(j>0) subRect-= M[k][j-1] ;
						if(i>0 && j>0) subRect+=M[i-1][j-1] ;
						maxSubRect = Math.max(maxSubRect,subRect) ;
					}
				}
			}
		}
		return maxSubRect ;
	}
	static void toRangeMatrix(int[][] M)
	{
		for(int i=0;i<M.length;i++)
		{
			for(int j=0;j<M[0].length;j++)
			{
				if(i>0) M[i][j]+=M[i-1][j] ;
				if(j>0) M[i][j]+=M[i][j-1] ;
				if(i>0 && j>0) M[i][j]-=M[i-1][j-1] ;
			}
		}
	}
}