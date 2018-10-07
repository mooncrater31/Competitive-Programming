import java.util.* ;
import java.io.BufferedReader ;
import java.io.InputStreamReader ;

public class Area
{
	private static final boolean debug = false ;
	public static void main(String args[]) throws Exception
	{
		BufferedReader bro = new BufferedReader(new InputStreamReader(System.in)) ;
		int T = Integer.parseInt(bro.readLine()) ;
		for(int t=0;t<T;t++)
		{
			String[] S = bro.readLine().split(" ") ;
			int N = Integer.parseInt(S[0]) ;
			int M = Integer.parseInt(S[1]) ;
			int K = Integer.parseInt(S[2]) ;
			int[][] A = new int[N][M] ;
			for(int i=0;i<N;i++)
			{
				String[] S1 = bro.readLine().split(" ") ;
				for(int j=0;j<M;j++)
				{
					A[i][j] = Integer.parseInt(S1[j]) ;
				}
			}
			toRangeMatrix(A) ;
			if(debug) printMatrix(A) ;
			pair p = max2D(A,K) ;
			System.out.println("Case #"+(t+1)+": "+p.a+" "+p.b) ;
		}
	}
	static void printMatrix(int[][] M)
	{
		for(int[] I:M)
		{
			System.out.println(Arrays.toString(I)) ;
		}
	}
	static void toRangeMatrix(int[][] M)
	{
		for(int i=0;i<M.length;i++)
		{
			for(int j=0;j<M[i].length;j++)
			{
				if(i>0) M[i][j]+=M[i-1][j] ;
				if(j>0) M[i][j]+=M[i][j-1] ;
				if(i>0 && j>0) M[i][j]-=M[i-1][j-1] ;
			}
		}
	}
	static pair max2D(int[][] M,int budget)
	{
		long maxA = Long.MIN_VALUE ;
		long minPrice = Long.MAX_VALUE ;
		for(int i=0;i<M.length;i++)
		{
			for(int j=0;j<M[0].length;j++)
			{
				for(int k=i;k<M.length;k++)
				{
					for(int l=j;l<M[0].length;l++)
					{
						int price = M[k][l] ;
						if(i>0) price -= M[i-1][l] ;
						if(j>0) price -= M[k][j-1] ;
						if(i>0 && j>0) price += M[i-1][j-1] ;
						int area = (k-i+1)*(l-j+1) ;
						if(price<=budget)
						{
							if(area>maxA)
							{
								maxA = area ;
								minPrice = price ;
								if(debug) System.out.println("i :"+i+" j :"+j+" k :"+k+" l :"+l+" price :"+price	) ;
							}
							else if(area==maxA && price<minPrice)
							{
								minPrice = price ;
							}
						}
					}
				}
			}
		}
		return new pair(maxA==Long.MIN_VALUE?0:maxA,minPrice==Long.MAX_VALUE?0:minPrice) ;
	}
}
class pair
{
	long a,b ;
	pair(long a,long b)
	{
		this.a = a ;
		this.b = b ;
	}
}