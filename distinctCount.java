import java.util.* ;
class node
{
	long value ;
	
	node left = null ;
	node right = null ;
	node(long v)
	{
		value = v ;
	}
}
class distinctCount
{
	int dmax = 1 ;
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
						if(temp.value!=val)
						{
							dmax++ ;
						}
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
						if(temp.value!=val)
						{
							dmax++ ;
						}
						break ;
					}
				}
			}
	}
	public static void main(String args[])
	{
		Scanner in = new Scanner(System.in) ;
		int tests = in.nextInt() ;
		int[] results = new int[tests] ;
		for(int i=0;i<tests;i++)
		{
			int N = in.nextInt() ;
			int V = in.nextInt() ;
			distinctCount d = new distinctCount() ;
			node root = new node(in.nextLong()) ;
			for(int j=1;j<N;j++)
			{
				d.addNode(root,in.nextLong()) ;
			}
			if(d.dmax==V)
			{
				results[i] = 0 ;//good
			}
			else if(d.dmax>V)
			{
				results[i] = 1 ;//average
			}
			else
			{
				results[i] = 2 ;//bad
			}
		}
		for(int i=0;i<results.length;i++)
		{
			if(results[i]==0)
				System.out.println("Good") ;
			else if(results[i]==1)
				System.out.println("Average") ;
			else
				System.out.println("Bad") ;
		}
	}
}
