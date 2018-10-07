
class SeamCarving // clrs 15-8 Memoized Version 16-01-2017
{
	int[] generate(double[][] B)
	{
		
		int min_index=0 ;
		int m = B.length, n = B[0].length ;
		int S[] = new int[m] ;
		double min = B[m-1][0] ;
		int k=m-1 ;
		for(int i=0;i<n;i++)
		{
			if(B[m-1][i]<min)
			{
				min = B[m-1][i] ;
				min_index = i ;
			}
		}
		S[k] = min_index ;
		for(int i=m-1;i>=1;i--)
		{
			int j = S[k] ;
		  if(j==0)
		  {
		  if(B[i-1][0]<B[i-1][1])
			  S[k--] = 0 ; 	
          else S[k--] = 1 ;
		  }
		  else if(j==1)
		  {
          if(B[i-1][n-2]<B[i-1][n-1])
			  S[k--] = 	n-2 ;
		  else S[k--] = n-1 ;
		  }
		  else
		  {
			  if(B[i-1][j-1]>B[i-1][j])
			  {
				  if(B[i-1][j+1]>B[i-1][j-1])
				  {
					  S[k--] = j+1 ;
				  }
				  else S[k--] = j-1 ;  
			  }
			  else
			  {
				  if(B[i-1][j]>B[i-1][j+1])
				  {
					  S[k--] = j ;
				  }
				  else
					  S[k--] = j+1 ;
			  }
		  }
		  
		  
		}
		return S ;
	}
	double min(double a, double b)
	{
		return a>b?a:b ;
	}
	double min(double a, double b, double c)
	{
		return a>b?(b>c?a:(a>c?a:c)):(a>c?b:(b>c)?b:c) ;
	}
	double[][] carve(double[][] d,boolean[][] visited,double[][] B,int mi, int nj)
	{
		if(visited[mi][nj])
		{
			return B ;
		}
		visited[mi][nj] = true ;
		int m = mi+1, n = nj+1 ;
		for(int i=1;i<m;i++)
		{
			B = carve(d,visited,B,i-1,0) ;
			B = carve(d,visited,B,i-1,1) ;
			B = carve(d,visited,B,i-1,n-2) ;
			B = carve(d,visited,B,i-1,n-1) ;
			B[i][0] = min(B[i-1][0],B[i-1][1]) + d[i][1] ;
			B[i][n-1] = min(B[i-1][n-2],B[i-1][n-1]) + d[i][n-1] ;
			for(int j=1;j<n-1;j++)
			{
				B = carve(d,visited,B,i-1,j-1) ;
				B = carve(d,visited,B,i-1,j) ;
				B = carve(d,visited,B,i-1,j+1) ;
				B[i][j] = min(B[i-1][j-1],B[i-1][j],B[i-1][j+1]) + d[i][j] ;	
			}
			
		}
		return B ;
	}
	int[] sCarving(double[][] d)
	{
		double[][] B = new double[d.length][d[0].length] ;
		boolean[][] visited  = new boolean[d.length][d[0].length] ;
		int m = d.length ;
		int n = d[0].length ;
		for(int j = 0;j<n ;j++)
		{
			B[0][j] = d[0][j] ;
			visited[0][j] = true ;
		}
		B = carve(d,visited,B,m,n) ;
		int[] S = generate(B) ;
		return S ;
	}
	public static void main(String args[])
	{
		SeamCarving S = new SeamCarving() ;
		double[][] d = {{1,2,3,4,5},
			            {2,3,4,5,6},
						{3,4,5,6,7},
		                {4,5,6,7,8}} ;
		int ans[] = S.sCarving(d) ;
        for(int i=0;i<ans.length;i++)
		{
			System.out.print(ans[i]+" ") ;
		}
	}
}