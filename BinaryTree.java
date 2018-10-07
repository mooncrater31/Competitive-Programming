/* 28/01/2017 */
class Node// Problem occurs when an element repeats in the input sequence
{
	int info ;
	Node left = null ;
	Node right = null;
	Node(int i)
	{
		this.info = i ;
	}
}
class BinaryTree
{
	Node insert(Node root,int value)
	{
		
		Node x  = root ;
		Node y = new Node(value)  ;
		Node z  = x ;
		if(root==null)
		{
			root = y ;
			return root ;
		}
		
		 while(x!=null)
		 {
			 System.out.print(x.info+" ") ;
			z = x ;
		 	if(x.info>value)
				x = x.left ;
		 	else
		 		x = x.right ;
			
		 }
		 System.out.println() ;
		 if(z.info<value)
		     z.right = y ;
	     else
		   	z.left = y ;
		
		
		return root ;
	}
	void preTraversal(Node root)
	{
		if(root == null)
			return ;
		preTraversal(root.left) ;
		System.out.print(root.info+ " ") ;	
		preTraversal(root.right) ;
	}
	public static void main(String args[])
	{
		BinaryTree B = new BinaryTree() ;
		int[] ins = {5,1,0,2,3,6,4,-1} ;
		Node root = null ;
		for(int i=0;i<ins.length;i++)
		{
			root = B.insert(root,ins[i]) ;
		}
		B.preTraversal(root) ;
	}
}