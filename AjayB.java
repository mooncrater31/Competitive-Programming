class node
{
	int value ;
	node left = null ;
	node right = null ;
	node(int v)
	{
		value = v ;
		
	}
}
class AjayB
{
	Map<Integer,Integer> m1 = new HashMap<Integer,node>() ;
	Map<Integer,Integer> m2 = new HashMap<node,Integer>() ;
	
	int[] A ;
	AjaB(int n)
	{
		A = new int[n] ;
	}
	void addNode(node root,int a)
	{
		int c = 0 ;
		node N = new node(a) ;
		node temp = root ;
		node par ;
		while(temp!=null)
		{
			par = temp ;
			if(a<=temp.value)
			{
				temp = temp.left ;
			}
			else
			{
				temp = temp.right ;
			}
		}
		if(par.value <a)
		{
			par.right = N ;
		}
		else
		{
			par.left = N ;
		}
		
	}
	void inOrderTraversal(node root)
	{
		if(root!=null)
		{
			inOrderTraversal(root.left) ;
			A[c] = root.value ;
			m1.put(root.value,c) ;
			m2.put(c,root.value) ;
			inOrderTraversal(root.right) ;
		}
	}
	int nLargestBST(node root, int relp)
	{
		inOrderTraversal(root) ;
		
	}
	
	
}