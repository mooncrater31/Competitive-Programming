//fault can be in the 1) printing method or 2) the insert method or 3) the creation of the tree
class node
{
	vanEmdeBoasTrees V = new vanEmdeBoasTrees() ;
	int u ;
	int min = -1 ;
	int max = -1 ;
	node[] cluster ;
	node summary ;
	node(int n)
	{
		u = n ;
		if(n!=2)
		{
			cluster = new node[V.upRootU(u)] ;
		}
	}
	
}
class vanEmdeBoasTrees
{
	
	double log2(double u)
	{
		return (Math.log(u)/Math.log(2)) ;
	}
	int downRootU(int u)
	{
		return (int)Math.pow(2,(int)(log2(u)/2)) ;
	}
	int upRootU(int u)
	{
		return u/downRootU(u) ;
	}
	node createVEBTree(int u)
	{
		node N = new node(u) ;
		if(u!=2)
		{
			for(int i=0;i<N.cluster.length;i++)
			{
				N.cluster[i] = createVEBTree(downRootU(u)) ;
			}
			N.summary = createVEBTree(upRootU(u)) ;
		}
		return N ;
	}
	void emptyVEBInsert(node V,int x)
	{
		V.min = x ;
		V.max = x ;
	}
	int high(int x,int u)
	{
		return (int)(x/downRootU(u)) ;
	}
	int low(int x,int u)
	{
		return x % (downRootU(u)) ;
	}
	int index(int minCluster,int offset,int u)
	{
		return (minCluster*(downRootU(u))+offset) ;
	}
	void VEBInsert(node V,int x)
	{
		if(V.min == -1)
			emptyVEBInsert(V,x) ;
		else if(x<V.min)
		{
			int c = x ;
			x = V.min ;
			V.min = c ;
		}
		if(V.u>2)
		{
			if(V.cluster[high(x,V.u)].min==-1)//empty cluster.
		    {
				VEBInsert(V.summary,high(x,V.u)) ;
				emptyVEBInsert(V.cluster[high(x,V.u)],low(x,V.u)) ;//when there's only one element then V.max isn't inserted into the cluster.
		    }
		    else VEBInsert(V.cluster[high(x,V.u)],low(x,V.u)) ;
		}
		if(x>V.max)
			V.max = x ;
	}
	int[] VEBPrinter(node V)
	{
		if(V.min == -1)//V.u can be more than 2, and there is no element present in the tree
		{
			int[] arr = new int[V.u] ;
			return arr ;
		}
		
		if(V.u == 2)
		{
			int arr[] = new int[2] ;
			if(V.min == -1)//no element
			{
				/* arr = {0,0} ; */
				arr[0] = 0 ;
				arr[1] = 0 ;
				return arr ;
			}
			else if(V.min !=V.max)//two elements
			{
				/* arr = {1,1} ; */
				arr[0] = 1 ;
				arr[1] = 1 ;
				return arr ;
			}
				
			else//one element
			{
				if(V.min == 0)//V.min = V.max here. First element is present
				{
					/* arr = {1,0} ; */
					arr[0] = 1 ;
					arr[1] = 0 ;
					return arr ;
				}
					
				else//V.min = V.max here. Second element is present here.
				{
					/* arr = {0,1} ; */
					arr[0] = 0 ;
					arr[1] = 1 ;
					return arr ;
				}
					
			}
		}
		else//More than two elements present.
		{
			int[] arr = VEBPrinter(V.cluster[0]) ;
			for(int i=1;i<V.cluster.length;i++)//returns the whole of the array(without the minimum in it)
			{
				arr = merge(arr,VEBPrinter(V.cluster[i])) ;//merges the previous arr with  the returned cluster array 
			}
			 arr[V.min] = 1  ;//inserts the minimum element in it.
		    return arr ;
		}
	}
	int[] merge(int[] a,int[] b)
	{
		int[] c = new int[a.length+b.length] ;
		int i;
		for(i=0;i<a.length;i++)
		{
			c[i] = a[i] ;
		}
		for(int j=0;j<b.length;j++)
		{
			c[i++] = b[j] ;
		}
		return c ;
	}
	void VEBPrint(node V)
	{
		/* int A[] = VEBPrinter(V) ; */
		for(int i=0;i<V.u;i++)
		{
			System.out.print((VEBMember(V,i)?1:0)+" ") ;
		}
		
	}
	boolean VEBMember(node V,int x)
	{
		if(x == V.min || x == V.max)
		{
			return true ;
		}
		else if(V.u==2)
		{
			return false ;
		}
		else return VEBMember(V.cluster[high(x,V.u)],low(x,V.u)) ;
	}
	public static void main(String args[])
	{
		int A[] = {0,0,1,1,1,1,0,1,0,0,0,0,0,0,1,1} ;
		vanEmdeBoasTrees VEB = new vanEmdeBoasTrees() ;
		node V = VEB.createVEBTree(16) ;
		System.out.println("V.u is "+V.u) ;
		for(int i=0;i<A.length;i++)
		{
			if(A[i]==1)
			{
				VEB.VEBInsert(V,i) ;
			}
		}
		VEB.VEBPrint(V) ; 
		
	}
}