import java.util.* ;
/*  1-> Brown Bear
	2-> Polar Bear
	3-> Brown or Polar Bear
	4-> Brown, Grizzly or Polar bear
	*/
class Bear
{
	char[][] M ;
	boolean[][] V ;
	int[][] type ;
	Bear(int n)
	{
		M = new char[n][n] ;
		V = new boolean[n][n] ;
		type = new int[n][n] ;
		
	}
	/*static void matrixPrinter(int[][] M)
	{
		for(int i=0;i<M.length;i++)
		{
			for(int j=0;j<M[0].length;j++)
			{
				System.out.print(M[i][j]+" ") ;
			}
			System.out.println() ;
		}
	}*/
	void dfs(int x, int y, int n)
	{
		if(V[x][y])
			return  ;
		else if(M[x][y]=='?')
		{
			boolean neighbour = false ;
			V[x][y] = true ;
			int[] a1 = {0,1,0,-1} ;
			int[] a2 = {-1,0,1,0} ;
			for(int i=0;i<4;i++)
			{
				int X = a1[i]+x ;
				int Y = a2[i]+y ;
				if(X>=0 && X<n && Y>=0 && Y<n)
				{
					if(M[X][Y]=='?')
					{
						neighbour = true ;
						type[X][Y] = 3 ;
						dfs(X,Y,n) ;
					}
				}
			}
			if(!neighbour)
			{
				type[x][y] = 4 ;
			}
		}
		
	}
	long counter (int n)
	{
		int twos=0,threes=0 ;
		for(int i=0;i<n;i++)
		{
			for(int j=0;j<n;j++)
			{
				if(M[i][j]=='B' || M[i][j]=='P' || M[i][j]=='G')
				{
					int x = dfs2(i,j,n) ;// this would change M
					if(x ==-1)
						return 0;
				}
			}
		}
		for(int i=0;i<n;i++)
		{
			for(int j=0;j<n;j++)
			{
				if(!V[i][j])
				{
					dfs(i,j,n) ;
					if(type[i][j] == 4)
						threes++ ;
					else if(type[i][j] == 3)
						twos++ ;
					
				}
			}
		}
		return ((long)(Math.pow(3,threes)* Math.pow(2,twos))%1000000007) ;
	}
	 int dfs2(int x,int y,int n)
	{
		if(V[x][y])
			return 0 ;
		else
		{
			V[x][y] = true ;
			int[] a1 = {0,1,0,-1} ;
			int[] a2 = {-1,0,1,0} ;
			for(int i=0;i<4;i++)
			{
				
				int X = a1[i]+x ;
				int Y = a2[i]+y ;
				if(X>=0 && X<n && Y>=0 && Y<n)
				{
					if(M[X][Y]=='G' && (M[x][y]=='P' || M[x][y]=='B' || M[x][y]=='G'))
						return -1 ;
					else if((M[x][y]=='P' && M[X][Y]=='B')||(M[x][y]=='B' && M[X][Y]=='P'))
						return -1;
					else if(M[X][Y]=='?' && M[x][y]=='B')
					{
						M[X][Y] = 'B' ;
						dfs2(X,Y,n) ;
						
					}
					else if(M[X][Y]== '?' && M[x][y]=='P')
					{
						M[X][Y] = 'P' ;
						dfs2(X,Y,n) ;
					}
				}
			}
			return 0 ;
		}
	}
	public static void main(String args[])
	{	
		Scanner in = new Scanner(System.in) ;
		int tests = in.nextInt() ;
		long[] results = new long[tests] ;
		for(int i=0;i<tests;i++)
		{
			int n = in.nextInt() ; //size of the grid ;
			Bear B = new Bear(n) ;
			for(int k=0;k<n;k++)
			{
				String S = in.next() ; //takes the whole line as an input
				for(int j=0;j<n;j++)
				{
					B.M[k][j] = S.charAt(j) ;
				}
			}
			results[i] = B.counter(n) ;
			//matrixPrinter(B.type) ;
		}
		for(int i=0;i<tests;i++)
		{
			System.out.println(results[i]) ;
		}
	}
}