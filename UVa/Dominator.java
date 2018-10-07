import java.util.* ;
class node
{
	int value ;
	boolean visited = false ;
	node(int v)
	{
		value = v ;
	}
	HashSet<Integer> H = new HashSet<Integer>() ;
}
class Dominator
{
	int[][] AM ;
	node[] NA ;
	Dominator(int n)
	{
		AM = new int[n][n] ;
		NA = new node[n] ;
		for(int i=0;i<n;i++)
		{
			NA[i] = new node(i) ;
		}
	}
	void modifiedDFS(int row)
	{
		for(int i=0;i<AM[row].length;i++)
		{
			
			if(AM[row][i]==1)
			{
				if(!NA[i].visited)
				{
					NA[i].visited = true ;
					NA[i].H.addAll(NA[row].H) ;
					NA[i].H.add(i) ;
				}
				else
				{
					HashSet<Integer> h = new HashSet<Integer>(NA[row].H) ;
					h.add(i) ;
					if(NA[i].H.size()>2)
						NA[i].H.retainAll(h) ;
				}
				modifiedDFS(i) ;
			}
			
		}
	}
	public static void main(String args[])
	{
		Scanner in = new Scanner(System.in) ;
		int tests = in.nextInt() ;
		boolean[][][] R  = new boolean[tests][][] ;
		for(int i=0;i<tests;i++)
		{
			
			int N = in.nextInt() ;
			boolean[][] results = new boolean[N][N] ;
			if(N==1)
			{
				in.nextInt() ;
				results[0][0] = true ;
			}
			else
			{
				Dominator D = new Dominator(N) ;
				for(int j=0;j<N;j++)
				{
					for(int k=0;k<N;k++)
					{
						D.AM[j][k] = in.nextInt() ;
					}
				}
				D.NA[0].H.add(new Integer(0)) ;
				D.NA[0].visited = true ;
				D.modifiedDFS(0) ;
				for(int j=0;j<N;j++)
				{
					results[0][j] = true ;
				}
				for(int j=1;j<N;j++)
				{
					for(int k=0;k<N;k++)
					{
						if(j==k)
							results[j][k] = true ;
						else//check in the set of k
							results[j][k] = D.NA[k].H.contains(j) ;
					}
				}
			}
			
			R[i] = results ;
		}
		for(int i=0;i<tests;i++)
		{
			System.out.println("Case "+(i+1)+":") ;
			for(int j=0;j<R[i].length;j++)
			{
				System.out.print("+") ;
				for(int k=0;k<2*R[i][j].length-1;k++)
					System.out.print("-") ;
				System.out.print("+\n|") ;
				for(int k=0;k<R[i][j].length;k++)
				{
					System.out.print(R[i][j][k]?"Y|":"N|")  ;
				}
				
				System.out.println() ;
			}
			System.out.print("+") ;
				for(int k=0;k<2*R[i].length-1;k++)
					System.out.print("-") ;
				System.out.print("+\n") ;
			
		}
	}
}