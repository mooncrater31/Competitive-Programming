import java.util.* ;
class node
{
	long value ;
	node parent = null;
	node left = null;
	node right = null;
	node(long v)
	{
		value = v ;
	}
}
class MonkAndCursedTree
{
	Map<Long,node> H = new HashMap<Long,node>() ;
	void addNode(node root,long val)
	{
			node temp = root ;
			while(temp!=null)
			{
				if(temp.value>=val)
				{//go to the left child
					if(temp.left!=null)
					{//if the left child exists
						temp = temp.left ;
					}
					else
					{
						node N = new node(val) ;
						temp.left = N ;
						N.parent = temp ;
						H.put(new Long(val),N) ;
						break ;
					}
				}
				else
				{//go to the right child
					if(temp.right!=null)
					{//if the right child exists
						temp = temp.right ;
					}
					else
					{
						node N = new node(val) ;
						temp.right = N ;
						N.parent = temp ;
						H.put(new Long(val),N) ;
						break ;
					}
				}
			}
	}
	long maxInPath(long a,long b,node root)
	{
		long max = Long.MIN_VALUE ;
		long alpha = a<b?a:b ;
		long beta = a>b?a:b ;
		node Na = H.get(alpha) ;
		node Nb = H.get(beta) ;
		node temp = Na ;
		while(temp.parent!=null && temp.value <= beta && temp.parent.value <= beta)
		{
			if(max<temp.value)
				max = temp.value ;
			temp = temp.parent ;
		}
		node commonNode = temp ;
		temp = Nb ;
		while(temp!=commonNode)
		{
			if(max<temp.value)
				max = temp.value ;
			temp = temp.parent ;
		}
		return max ;
		
	}
	public static void main(String args[])
	{
		Scanner in = new Scanner(System.in) ;
		int N = in.nextInt() ;
		MonkAndCursedTree M = new MonkAndCursedTree() ;
		long rt = in.nextLong() ;
		node root = new node(rt) ;
		M.H.put(new Long(rt),root); 
		for(int i=1;i<N;i++)
		{
			M.addNode(root,in.nextLong()) ;
		}
		long a = in.nextLong() ;
		long b = in.nextLong() ;
		System.out.println(M.maxInPath(a,b,root)) ;
	}
}