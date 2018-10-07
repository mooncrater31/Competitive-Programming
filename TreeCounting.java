import java.util.* ;
class TreeCounting
{
	public static void main(String args[])
	{
		Scanner in = new Scanner(System.in) ;
		int N = in.nextInt() ;
		long K = in.nextLong() ;
		int[] a = new int[N+1] ;
		for(int i=1;i<=N;i++)
		{
			a[i] = in.nextInt() ;
		}
		int[] par = new int[N+1] ;//since parent of the second element is to be found.
		for(int i=2;i<=N;i++)
		{
			par[i] = in.nextInt() ;
		}
		//input completed
		boolean[][] anc = new boolean[N+1][N+1] ;//anc[x][i]:: x is an ancestor of i 
		for(int i=2;i<=N;i++)
		{
			int x = i ;
			while(x!=1)
			{
				x = par[x] ;
				//System.out.println(x) ;
				anc[x][i] = true ;
			}
		}
		long ans = 0 ;
		for(int i=1; i<=N; i++)
			for(int j=1; j<=N; j++)
				for(int k=1; k<=N; k++)
				{
					if(i == j || j == k || i == k)
						continue;
					if(anc[i][j] && anc[i][k] && (a[i]+a[j]+a[k] >= K))
					{
						ans++;
					}	
				}
		System.out.println(ans/2) ;
		
		
	}
}