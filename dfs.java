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
public class dfs
{
	void dfsSearch(node root)
	{
		if(root==null)
			return ;
		else
		{
			System.out.print(root.value+" ") ;
			dfsSearch(root.left) ;
			dfsSearch(root.right) ;
		}
		
	}		
}