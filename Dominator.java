import java.util.* ;

public class Dominator
{
	
	static void dfs(int i, ArrayList<HashSet<Integer>> H,HashSet<Integer> path,int[][] A)
	{
		// System.out.println("Visited :"+i) ;
		if(path.contains(i))//in the case of a cycle or a loop.
			return ;
		path.add(i) ;
		if(H.get(i).isEmpty())//not created yet
			H.set(i,new HashSet<Integer>(path)) ;
			// H[i] = new HashSet<Integer>(path) ;
		else
		{
			// H[i].retainAll(path) ;
			H.get(i).retainAll(path) ;
		}
		for(int j=0;j<A[0].length;j++)
		{
			HashSet<Integer> tempPath = new HashSet<Integer>(path) ;
			if(A[i][j]==1)
				dfs(j,H,tempPath,A) ;
		}
	}
	static void arrayPrinter(int[][] A)
	{
		for(int i=0;i<A.length;i++)
		{
			for(int j=0;j<A[i].length;j++)
			{
				System.out.print(A[i][j]+" ") ;
			}
			System.out.println() ;
		}
	}
	public static void main(String args[])
	{
		Scanner in = new Scanner(System.in) ;
		int t = in.nextInt() ;
		boolean[][][] ans = new boolean[t][][] ;
		for(int i=0;i<t;i++)
		{
			int n = in.nextInt() ;
			int[][] A = new int[n][n] ;
			for(int j=0;j<n;j++)
			{
				for(int k=0;k<n;k++)
				{
					A[j][k] = in.nextInt() ;
				}
			}
			// arrayPrinter(A) ;
			// HashSet<Integer>[] H = new HashSet<Integer>[n] ;
			// HashSet<Integer>[] H = (HashSet<Integer>[]) new Object[n] ;
			ArrayList<HashSet<Integer>> H = new ArrayList<HashSet<Integer>>() ;
			for(int j=0;j<n;j++)//can there be any other method to do this? Is it necessary?
			{
				H.add(new HashSet<Integer>()) ;
			}
			HashSet<Integer> path = new HashSet<Integer>() ;
			dfs(0,H,path,A) ;
			ans[i] = new boolean[n][n] ;
			for(int j=0;j<n;j++)
			{
				for(int k=0;k<n;k++)
				{
					// ans[i][j][k] = H[j].contains(k) ;
					ans[i][j][k] = H.get(k).contains(j) ;
				}
			}
		}
		for(int i=0;i<t;i++)
		{
			System.out.println("Case "+(i+1)+":") ;
			
			for(int j=0;j<ans[i].length;j++)
			{
				System.out.print("+") ;
				for(int k=0;k<2*ans[i].length-1;k++)
					System.out.print("-") ;
				System.out.print("+\n") ;
				System.out.print("|") ;
				for(int k=0;k<ans[i][j].length;k++)
				{
					System.out.print(ans[i][j][k]?"Y|":"N|") ;
				}
				System.out.println() ;
			}
			System.out.print("+") ;
			for(int j=0;j<2*ans[i].length-1;j++)
				System.out.print("-") ;
			System.out.print("+\n") ;
		}
	}
}