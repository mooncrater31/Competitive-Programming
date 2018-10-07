import java.util.* ;
class node
{
	node left = null ;
	node right = null ;
	int value ;
	node(int v)
	{
		value = v ;
	}
}
class SillySnail
{
	int A[],k=0 ;
	SillySnail(int length)
	{
		A = new int[length] ;
	}
	static void arrayPrinter(int[] arr)
	{
		for(int i=0;i<arr.length;i++)
		{
			System.out.print(arr[i]+" ") ;
		}
		System.out.println() ;
	}
	void inOrder(node root)
	{
		if(root==null)
			return ;
		A[k++] = root.value ;
		//arrayPrinter(A) ;
		inOrder(root.left)  ;
		inOrder(root.right) ;
	}
	public static void main(String args[])
	{
		Scanner in = new Scanner(System.in) ;
		int tests = in.nextInt() ;
		int[][] results = new int[tests][] ;
		for(int i=0;i<tests;i++)
		{
			int N = in.nextInt() ;//gives us the number of relations. 
			int n =2*N + 1 ;
			if(N==0)
			{
				results[i] = new int[1] ;
				results[i][0] = 1 ;
			}
			else
			{
				Map<Integer,node> map = new HashMap<Integer,node>() ;
				node root = null ;
				for(int j=0;j<N;j++)
				{
					int parent = in.nextInt() ;
					int lChild = in.nextInt() ;
					int rChild = in.nextInt() ;
					node lch = null ;
					node rch = null ;
					if(lChild==0)
						n-- ;
					else
						lch = new node(lChild) ;
					if(rChild==0)
						n-- ;
					else
						rch = new node(rChild) ;
					if(map.get(parent)==null)
					{
						node par = new node(parent) ;
						map.put(new Integer(parent),par) ;
						if(parent==1)
							root = par ;
						par.left = lch ;
						par.right = rch ;
					}
					else
					{
						node par = map.get(parent) ;
						par.left = lch ;
						par.right = rch ;
					}
					
					map.put(new Integer(lChild),lch) ;
					map.put(new Integer(rChild),rch) ;
					
				}
				//creation of the Tree is completed.
				SillySnail S = new SillySnail(n) ;
				S.inOrder(root) ;
				// for(int j=0;j<N;j++)
				// {
					// results[i][j] = S.A[j] ;
				// }
				results[i] = S.A ;
			}
		}
		for(int i=0;i<results.length;i++)
		{
			for(int j=0;j<results[i].length;j++)
			{
				System.out.print(results[i][j]+" ") ;
			}
			System.out.println() ;
		}
		
		
	}
	
}