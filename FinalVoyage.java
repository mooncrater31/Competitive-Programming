import java.util.* ;
class FinalVoyage
{
	static int[] included(int[][] VM,int[] W)
	{
		int N = VM.length;
		int maxLoad = VM[0].length ;
		
	}
	static void matrixPrinter(int[][] M)
	{
		for(int i=0;i<M.length;i++)
		{
			for(int j=0;j<M[0].length;j++)
			{
				System.out.print(M[i][j]+" ") ;
			}
			System.out.println() ;
		}
	}
	static int max(int a,int b)
	{
		return a>b?a:b ;
	}
	
	static int finder(int N,int maxLoad,int[] W,int[] V)
	{
		int[][] VM = new int[N][maxLoad+1] ;
		//VM[0][w] = 0 already
		for(int w=1;w<=maxLoad;w++)
		{
			if(w>=W[0])
			{
				VM[0][w] = V[0] ;
			}
		}
		
		for(int i=1;i<N;i++)
		{
			for(int w=1;w<=maxLoad;w++)
			{
				if(W[i]>w)
				{
					VM[i][w] = VM[i-1][w] ;
				}
				else
				{
					VM[i][w] = max(VM[i-1][w],VM[i-1][w-W[i]]+V[i]) ;
				}
			}
		}
		matrixPrinter(VM) ;
		//included(VM,W) ;
		return VM[N-1][maxLoad] ;
	}
	public static void main(String args[])
	{
		int N = 5 ;
		int maxLoad = 100 ;
		int[] W = {40,40,50,20,50} ;
        
		int[] V ={100,50,40,60,100} ;
		System.out.println(finder(N,maxLoad,W,V)) ;
		// Scanner in  = new Scanner(System.in) ;
		// int tests = in.nextInt() ;
		// int[] results = new int[tests] ;
		// for(int i=0;i<tests;i++)
		// {
			// int N = in.nextInt() ;
			// if(N==0)
			// {
				// results[i] = 0;
				// continue ;
			// }
			// int maxLoad = in.nextInt() ;
			// int[] W = new int[N] ;
			// int[] V = new int[N] ;
			// for(int j=0;j<N;j++)
			// {
				// W[j] = in.nextInt() ;
			// }
			// for(int j=0;j<N;j++)
			// {
				// V[j] = in.nextInt() ;
			// }
			// results[i] = finder(N,maxLoad,W,V) ;
		// }
		// for(int i=0;i<tests;i++)
		// {
			// System.out.println(results[i]) ;
		// }
	}
}