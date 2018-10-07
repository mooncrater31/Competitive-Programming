//1/03/2017 Harshit
class Node
{
	int info ;
	Node left = null ;
	Node right = null ;
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
		root =  alpha.alphaBalancedTree(0,A.length-1) ;
		/* System.out.println("root->left->left->left"+root.left.left.left.info) ; */
		
		
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
		System.out.println("p is "+p+" and q is "+q) ;
		if(p>q)
			return null ;
		if(p==q)
		{
			A[p].left = null;
			A[p].right = null;
			return A[p] ;
		}
		int root_index = (p+q)/2 ;
		A[root_index].left = alphaBalancedTree(p,root_index-1) ;
		A[root_index].right = alphaBalancedTree(root_index+1,q) ;
		return A[root_index] ;
	}
}