class Node
{
	boolean visited = false; 
	boolean mark ;
	int degree ;
	int key ;
	Node prev ;
	Node next ;
	Node child ;
	Node p ;
	int rank = 0;
	Node(int x)
	{
		key = x ;
		
	}
	
}
class Heap
{
	Node min ;
	int n ;
}
class FibonacciHeaps
{
	void insert(Heap H,Node x)
	{
		if(H.min!=null)
		{
			x.next = H.min.next ;
			x.prev = H.min.prev ;
			x.p = null ;
			H.n++ ;
			if(H.min.key>x.key)
				H.min = x ;
		}
		else
		{
			H.min = x ;
			H.n++ ;
		}
	}
	void addListToRootList(Heap H,Node b) 
	{
		Node z = H.min ;
		z.prev.next = b ;
		b.prev.next = z ;
		z.prev = b.prev ;
		b.prev = z.prev ;
		Node temp = H.min ;
		do
		{
			temp = temp.next ;
			temp.p = null ;
		}while(temp!=H.min) ;
	}
	void fibLink(Heap H,Node y,Node x)
	{
		y.prev = y.prev.next ;
		y.next = y.next.prev ;
		x.child.next.prev = y ;
		y.next = x.child.next ;
		y.prev = x.child ;
		x.child.next  = y ;
		x.degree++ ;
		y.mark = false ;
	}
	void consolidate(Heap H)
	{
		int n = H.n ;
		int l = (int)(Math.log(H.n)/Math.log(2)) +1 ;
		Node[] = new Node[l] ;
		for(Node temp = H.min;!temp.visited;temp=temp.next)
		{
			temp.visited = true ;
			Node x = temp ;
			int d = x.degree ;
			while(Node[d]!=null)
			{
				Node y = Node[d] ;
				if(x.key>y.key)
				{
					Node  c = x ;
					x = y ;
					y = c ;
				}
				fibLink(H,y,x) ;
				A[d] = null ;
				d++ ;
			}
			A[d] = x ;
		}
		H.min = null ;
		for(int i=0;i<=l;i++)
		{
			if(A[i]!=null)
			{
				if(H.min==null)
				{
					H = new Heap() ;
					H.min = A[i] ;
					H.n = n ;
				}
				else
				{
					addToRootList(H,A[i]) ;
					if(A[i].key<H.min.key)
					{
						H.min = A[i] ;
					}
				}
			}
		}
		
	}

	Heap extractMin(Heap H)
	{
		Node z = H.min ;
		if(H.min.rank!=0)
		{
			addListToRootList(H,z.child) ;//adds z's children to the root list and changes their parent
			
		}
		if(z.next == z)//single root node in the list, without any children
			return null ;
		else//atleast two nodes in the root list
		{
			z.prev.next = z.next ;
			z.next.prev = z.prev ;
		}
		H.min = z.right ;//H.min now points to the next Node of the root list
		consolidate(H) ;//does it's work
		H.n-- ;
		return H ;
	}
	void printChildren(Node N)
	{
		System.out.print(N.key+ " ") ;
		if(N.child!=null)
		{
			System.out.print(N.child.key+" ") ;
			for(Node temp = N.child.next;temp!=N.child;temp = temp.next)
			{
				printChildren(temp) ;
			}
		}
	}
	void printHeap(Heap H)
	{
		for(Node temp = H.min.next;temp!=H.min;temp = temp.next) 
		{
			printChildren(temp) ;
		}
	}
	Heap makeHeap()
	{
		Heap H = new Heap() ;
	}
	public static void main(String args[])
	{
		FibobonacciHeap F = new FibonacciHeap() ;
		H = makeHeap() ;
		int[] A = {2,3,5,8,9,1,0} ;
		for(int i=0;i<A.length;i++)
		{
			Node N = new Node(A[i]) ;
			F.insert(H,N) ;
		}
		F.printHeap(H) ;
	}
}