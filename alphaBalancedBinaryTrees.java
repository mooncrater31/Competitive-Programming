class Node
{
	int info ;
	Node left ;
	Node right ;
	Node(int i)
	{
		this.info = i ;
	}
} 
class AlphaBalancedBinaryTree
{
    static 	Node[] A ;
	static int k ;
    AlphaBalancedBinaryTree(int n)
	{
	    A = new Node[n] ;
	    k = 0 ;
	}
	public static void main(String args[])
	{
		int[] ins = {5,1,0,2,3,6,4,-1} ; 
		AlphaBalancedBinaryTree alpha = new AlphaBalancedBinaryTree(ins.length) ;
		BinaryTree B = new BinaryTree() ;
		
		Node root = null ;
		for(int i=0;i<ins.length;i++)
		{
			root = B.insert(root,ins[i]) ;
		}
		
		A = alpha.AFVPOT(root) ;
		System.out.println("The Array of nodes:") ;
		for(int i=0;i<A.length;i++)
		{
			System.out.print(A[i].info+" ") ;
		}
		System.out.println() ;
		 k = 0; 
		root =  alpha.alphaBalancedTree(0,A.length-1 ) ;
		B.preTraversal(root) ; 
		
		
	}
	Node[] AFVPOT(Node root)//ArrayFormViaPreOrderTraversal
	{
		if(root==null)
			return null ;
		AFVPOT(root.left) ;
		A[k++] = root ;
		AFVPOT(root.right) ;
		return A ;
	}
	Node alphaBalancedTree(int p,int q)
	{
		if(p==q)
			return null ;
		int root_index = (p+q)/2 ;
		A[root_index].left = alphaBalancedTree(p,root_index-1) ;
		A[root_index].right = alphaBalancedTree(root_index+1,q) ;
		/* Node left =null;
		Node right =null;
		if(alphaBalancedTree(p,root_index-1)!=null)
		    left = alphaBalancedTree(p,root_index-1) ;
		if(alphaBalancedTree(root_index+1,q) !=null)
		    right = alphaBalancedTree(root_index+1,q) ;
		if(left!=null)
			A[root_index].left = left ;
		if(right!=null)
			A[root_index].right = right ; */
		return A[root_index] ;
	}
}