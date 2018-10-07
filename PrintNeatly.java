import java.util.* ;
class PrintNeatly
{
	int[] PNeatly(int M,int[] l)
	{
		int n = l.length ;
		int[] res = new int[n] ;
		Arrays.fill(res,-1) ;
		int[][] sum = new int[n][n] ;
		sum[0][0] = l[0] ;
		for(int i=1;i<n;i++)
		{
			sum[0][i] = sum[0][i-1]+l[i] ;
		}
		for(int i=1;i<n;i++)
		{
			for(int j=i;j<n;j++)
			{
				sum[i][j] = sum[i-1][j] - l[i-1] ;
			}
		}
		int j  ;
		int i = 0;
		int k = 0 ;
		while(i!=n-1)
		{
			j = i ;
			int spacesum = 0 ;
			while(spacesum>=0)
			{
				spacesum = M-j+i-sum[i][j] ;
				j++ ;
				
			}
			i = j-1 ;
			res[k++] = j-2 ;
		}
		res[k] = n-1 ;
		return res ;
	}
	public static void main(String args[])
	{
		PrintNeatly P = new PrintNeatly() ;
		int[] l = {3,5,5,3,2,2,4,5} ;
		int[] res = P.PNeatly(9,l) ;
		int i=0 ;
		while(res[i]!=-1 && i != res.length-1)
		{
			System.out.print(res[i]+" ") ;
			i++ ;
		}
	}
}