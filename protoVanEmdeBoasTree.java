class Node
{
	int u ;
	Node[] cluster ;
	Node summary ;
	boolean[] A ;
	Node(int n)
	{
		u = n ;
		if(n==2)
		{
			A = new boolean[2] ;
		}
		else
		{
			cluster = new Node[(int)Math.sqrt(n)] ;
		}
	}
}
class protoVanEmdeBoasTree
{
	Node createProtoVEBTree(int u)
	{
		Node N = new Node(u) ;
		if(u!=2)
		{
			for(int i=0;i<N.cluster.length;i++)
			{
				N.cluster[i] = createProtoVEBTree((int)Math.sqrt(u)) ;
			}
			N.summary = createProtoVEBTree((int)Math.sqrt(u)) ;
		}
		return N ;
	}
	int high(int x,int u)
	{
		return x/(int)Math.sqrt(u) ;
	}
	int low(int x,int u)
	{
		return x % ((int)Math.sqrt(u)) ;
	}
	int index(int minCluster,int offset,int u)
	{
		return (minCluster*((int)Math.sqrt(u))+offset) ;
	}
	void protoVEBInsert(Node V,int x)
	{
		if(V.u==2)
		{
			V.A[x] = true ;
		}
		else
		{
			protoVEBInsert(V.cluster[high(x,V.u)],low(x,V.u)) ;
			protoVEBInsert(V.summary,high(x,V.u)) ;
		}
	}
	boolean protoVEBDelete(Node V,int x)
	{
		if(V.u == 2)
		{
			V.A[x] = false ;
			if(!V.A[0] && !V.A[1])
			{
				return false ;
			}
			return true ;
		}
		else 
		{
			boolean b1 = protoVEBDelete(V.cluster[high(x,V.u)],low(x,V.u)) ;
			if(!b1)
			{
				boolean b2 = protoVEBDelete(V.summary,high(x,V.u)) ;
				return b2 ;
			}
			return true ;
		}
	}
	void printVEBTree(Node V)
	{
		if(V.u == 2)
		{
			System.out.print((V.A[0]?1:0) + " "+ (V.A[1]?1:0)+" ") ;
		}
		else
		{
			for(int i=0;i<V.cluster.length;i++)
			{
				printVEBTree(V.cluster[i]) ;
			}
		}
	}
	int protoVEBMaximum(Node V)
	{
		if(V.u == 2)
		{
			if(V.A[1])
				return 1 ;
			else if(V.A[0])
				return 0 ;
			else return -1 ;
		}
		else
		{
			int maxCluster = protoVEBMaximum(V.summary) ;
			if(maxCluster == -1)
				return -1 ;
			else
			{
				int offset = protoVEBMaximum(V.cluster[maxCluster]) ;
				return index(maxCluster,offset,V.u) ;
			}
		}
	}
	int protoVEBMinimum(Node V)
	{
		if(V.u == 2)
		{
			if(V.A[0])
				return 0 ;
			else if(V.A[1])
				return 1 ;
			else
				return -1 ;
		}
		else
		{
			int minCluster  = protoVEBMinimum(V.summary) ;
			if(minCluster == -1)
				return -1 ;
			else
			{
				int offset  = protoVEBMinimum(V.cluster[minCluster]) ;
				return index(minCluster,offset,V.u) ;
			}
		}
	}
	int protoVEBPredecessor(Node V,int x)
	{
		if(V.u == 2)
		{
			if(x==1 && V.A[0])
				return 0 ;
			else return -1 ;
		}
		else
		{
			int 
		}
	}
	int protoVEBSuccessor(Node V,int x)
	{
		if(V.u == 2)
		{
			if(x == 0 && V.A[1])
				return 1 ;
			else return -1 ;
		}
		else
		{
			int offset = protoVEBSuccessor(V.cluster[high(x,V.u)],low(x,V.u)) ;
			if(offset!=-1)
				return index(high(x,V.u),offset,V.u) ;
			else
			{
				int succCluster = protoVEBSuccessor(V.summary,high(x,V.u)) ;
				if(succCluster == -1)
					return -1 ;
				else
				{
					offset = protoVEBMinimum(V.cluster[succCluster]) ;
					return index(succCluster,offset,V.u) ;
				}
			}
		}
	}
	boolean protoVEBMember(Node V,int x)
	{
		if(V.u == 2)
			return V.A[x] ;
		else return protoVEBMember(V.cluster[high(x,V.u)],low(x,V.u)) ;
	}
	public static void main(String args[])
	{
		protoVanEmdeBoasTree P = new protoVanEmdeBoasTree() ;
		Node V = P.createProtoVEBTree(16) ;
		int[] A = {0,0,1,1,1,1,0,1,0,0,0,0,0,0,1,1} ;
		for(int i=0;i<A.length;i++)
		{
			if(A[i]==1)
			{
				P.protoVEBInsert(V,i) ;
			}
		}
		P.printVEBTree(V) ;
		System.out.println("\nMinimum :"+P.protoVEBMinimum(V)) ;
		System.out.println("Successor of 7 is :"+P.protoVEBSuccessor(V,7)) ;
		System.out.println("8 is a member? :"+P.protoVEBMember(V,8) ) ;
		System.out.println("Maximum :"+P.protoVEBMaximum(V)) ;
		P.protoVEBDelete(V,15) ;
		System.out.println("Maximum :"+P.protoVEBMaximum(V)) ;
		P.protoVEBDelete(V,14) ;
		System.out.println("Maximum :"+P.protoVEBMaximum(V)) ;
	}
}