import java.util.* ;
import java.io.BufferedReader ;
import java.io.InputStreamReader ;

public class MaximumSumTorus
{
	public static void main(String args[]) throws Exception
	{
		BufferedReader bro = new BufferedReader(new InputStreamReader(System.in)) ;
		int T = Integer.parseInt(bro.readLine().trim()) ;
		for(int t=0;t<T;t++)
		{
			int N = Integer.parseInt(bro.readLine().trim()) ;
			int[][] A = new int[N][N] ;
			for(int i=0;i<N;i++)
			{
				String[] S = bro.readLine().split(" +") ;
				for(int j=0;j<N;j++)
				{
					A[i][j] = Integer.parseInt(S[j]) ;
				}
			}
			System.out.println(solve(A)) ;
		}
	}
	static int solve(int[][] M)
	{
		int[][] M2 = doubleMatrix(M) ;
		toRangeMatrix(M2) ;
		return(maxInMatrix(M2)) ;
	}
	static int[][] doubleMatrix(int[][] M)
	{
		int[][] M2 = new int[2*M.length][2*M.length] ;
		for(int i=0;i<M.length;i++)
		{
			for(int j=0;j<M[0].length;j++)
			{
				M2[i][j] = M[i][j] ;
				M2[i+M.length][j] = M[i][j] ;
				M2[i][j+M.length] = M[i][j] ;
				M2[i+M.length][j+M.length] = M[i][j] ;
			}
		}
		return M2 ;
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
	static int maxInMatrix(int[][] M)
	{
		int maxInMatrix = Integer.MIN_VALUE ;
		for(int i=0;i<M.length/2;i++)
		{
			for(int j=0;j<M.length/2;j++)
			{
				for(int k=i;k-i<M.length/2;k++)
				{
					for(int l=j;l-j<M.length/2;l++)
					{
						int subRect = M[k][l] ;
						if(i>0) subRect -= M[i-1][l] ;
						if(j>0) subRect -= M[k][j-1] ;
						if(i>0 && j>0) subRect+=M[i-1][j-1] ;
						maxInMatrix = Math.max(subRect,maxInMatrix) ;
					}
				}
			}
		}
		return maxInMatrix ;
	}
}
