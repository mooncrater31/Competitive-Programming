import java.util.* ;
class node
{
	int value ;
	node parent = null ;
	node right = null ;
	node left = null ;
	node(int n)
	{
		value = n ;
	}
}
class MirrorImage
{
	static void arrayPrinter(int[] A)
	{
		for(int i=0;i<A.length;i++)
		{
			System.out.print(A[i]+" ") ;
		}
		System.out.println() ;
	}
	public static void main(String args[])
	{
		Scanner in = new Scanner(System.in) ;
		int N = in.nextInt() ;
		int Q = in.nextInt() ;
		int[] q = new int[Q] ;
		int[] results = new int[Q] ;
		Map<Integer,node> map = new HashMap<Integer,node>() ;
		//creation of the root node
		node root = new node(1) ;
		map.put(new Integer(1),root) ;
		
		for(int i=0;i<N-1;i++)// N-1 because, there are N-1 edges in an N node Binary Tree.
		{// basically making the tree.
			
			int par =  in.nextInt() ;
			int chi = in.nextInt() ;
			char direction = in.next().charAt(0) ;
			node child = new node(chi) ;
			map.put(new Integer(chi),child) ;// putting the node's info in the hashmap
			node parent = map.get(par) ;// we get the parent of the current node
			child.parent = parent ;
			if(direction=='R')//new node is a right child
			{
				//parent node must exist in the hashmap
				parent.right = child ;
			}
			else// new node is a left child
			{
				parent.left =  child ;
			}
		}
		for(int i=0;i<Q;i++)
		{
			q[i] = in.nextInt() ;
		}
		//making an integer array, 0->nada, 1-> left , 2->right
		for(int i=0;i<Q;i++)
		{
			if(q[i]==1)
				results[i] = 1 ;
			else
			{
				//System.out.println("Array for "+q[i]);
				int[] what = new int[N] ;//already initialized to 0
				node temp = map.get(q[i]) ;
				int t = 0 ;
				while(temp.parent!=null)
				{
					if(temp==temp.parent.left)
						what[t++] = 1 ;
					else
						what[t++] = 2 ;
					temp = temp.parent ;
				}
				//arrayPrinter(what) ;
				// System.out.println("\nDFS:") ;
				// dfs d = new dfs() ;
				// d.dfsSearch(temp) ;
				// System.out.println("BFS:") ;
				// bfs b = new bfs() ;
				// b.bfsSearch(temp) ;
				//now, t is the (distance of the node from the root -1 )
				//here temp is the root node 
				//here we'll go left if what[i] is 2,  and go right if what[i] is 1
				//System.out.println("Value of t: "+t) ;
				for(int j=t-1;j>=0;j--)
				{
					if(what[j]==2)
					{
						if(temp.left == null)
							results[i] = -1 ;
						else
						{
							temp = temp.left ;
						}
					}
					else //what[j] = 1
					{
						if(temp.right == null)
							results[i] = -1 ;
						else
						{
							temp = temp.right ;
						}
					}
				}
				if(results[i]!=-1)
					results[i] = temp.value ;		
			}
		}
		
		for(int i=0;i<Q;i++)
		{
			System.out.println(results[i]) ;
		}
	}
}
