//Completed on : 27/03/2017
package DisjointSetTrees ;
class Node
{
	int key ;
	int rank = 0;
	Node p = null ;
	Node(int x)
	{
		key = x ;
	}
}
class Set
{
	Node r ;
	Set next = null ;
	Set prev = null ;
	int no ;
}
class cont
{
	Set[] repr ;
	TandH T ;
	Node[] N ;
	cont(TandH t,Node[] n,Set[] r)
	{
		T = t ;
		N = n ;
		repr = r ;
	}
}
class TandH
 {
	 Set tail ;
	 Set start ;
	 TandH(Set s,Set t)
	 {
		 start = s ;
   	 	 tail = t ;
 	 }
 }
 class DisjointSetTree
 {
 TandH insertInList(Set start,Set tail,Set x)
 {
	 if(tail!=null)//Non-empty list
	 {
		 tail.next = x ;
		 x.prev = tail ;
		 x.no = tail.no+1 ;
		 tail = x ;
		 
	 }
	 else//emptyList
	 {
		 start = x ;
		 tail = x ;
		 start.no = 0 ;
	 }
	 TandH T = new TandH(start,tail) ;
	 return T ;
 }
 TandH deleteFromList(Set start,Set tail,Set x)
 {		 
	 if(x== start && x == tail)
	 {
		 start = null ;
		 tail = null ;	
	 }
	 else if(x == tail)
	 {
		 x.prev.next = null ;
		 tail = x.prev ; 	
	 }
	 else if(x == start)
	 {
		 x.next.prev = null;
		 start = x.next ;	
	 }
	 else
	 {	
		 x.prev.next = x.next ;
		 x.next.prev = x.prev ;
 	 }
	 TandH T = new TandH(start,tail) ;
	 return T ;
  }

	Set makeSet(int x)
	{
		Set S = new Set() ;
		S.r = new Node(x) ;
		S.r.p = S.r ;	
		S.r.rank = 1 ;
		return S ;
	}
	Set link(Set x,Set y,Set[] repr)
	{
		if(x.r==null)
			return y ;
		else if(y.r == null)
			return x ;
		if(x.r.rank>y.r.rank)//x.r.rank>=y.r.rank+1
		{
			y.r.p = x.r ;
			y = null ;
			repr[x.r.key] = x ;
			return x ;
		}
		else
		{
			x.r.p = y.r ;
			if(x.r.rank == y.r.rank)
				y.r.rank++ ;
			repr[y.r.key] = y ;
			x = null ;
			return y ;
		}
	}
	void link(Set x,Set y)//linked list linker
	{
		if(x.r==null)
		{
			x.r = y.r ;
		}
		else if(y.r == null)
		{
			y.r = x.r ;
		}
		
				
		if(x.r.rank>y.r.rank)//x.r.rank>=y.r.rank+1
		{
			y.r.p = x.r ;
			y.r = x.r ;// since we don't know which set is to be deleted, and which is to be kept
		}
		else
		{
			x.r.p = y.r ;
			x.r = y.r ;
			if(x.r.rank == y.r.rank)
				y.r.rank++ ;
		}
	}
	Node findSet(Node x)
	{
		if(x!=x.p)
			x.p = findSet(x.p) ;
		return x.p ;
	}
	// Off-line Minimum problem
	Set buildSet(int[] A,int i,int j,Node[] N,Set[] repr)
	{
		
		Set[] S = new Set[j-i+1] ;
		for(int k = i;k<=j;k++)
		{
			S[k-i] = makeSet(A[k]) ;
			N[A[k]] = S[k-i].r ;
		}
		for(int k=1;k<S.length;k++)
		{   
			
			S[0] = link(S[0],S[k],repr) ;
		}
		if(j-i+1==1)
			repr[A[j]] = S[0] ;	
		return S[0] ;
	}
	cont insertion(int[] A,int m,int n)//m is the number of extractMin requests runtime: O(n) 
	{// an infinity means that an extractMin is requested
		Node[] N = new Node[n+1] ;
		Integer max = Integer.MAX_VALUE ;
		Set[] repr = new Set[n+1] ; //maintains the set number of every representative
		int last = -1 ;
		Set S ;
		TandH T = null;
		Set start = null ;
		Set tail = null ;
	    int k = 0 ;
		for(int i=0;i<=A.length;i++)
		{
			
		if(i==A.length || A[i]==max  )
			{
				if(last+1 == i)//empty set
				{
					S = new Set() ;
					S.r = null ;
					T = insertInList(start,tail,S) ;
					start = T.start ;
					tail = T.tail ;
				}
				else
				{
					S = buildSet(A,last+1,i-1,N,repr) ;
					T = insertInList(start,tail,S) ;
					start = T.start ;
					tail = T.tail ;
				}						
				last = i ;
			}
		}
		T.start = start ;
		T.tail = tail ;
		cont c = new cont(T,N,repr) ;
		return c ;
	}
	int[] offlineMinimum(Set start,Set tail,int n,int m,Node[] N,Set[] repr)
	{
		TandH T ;
		int[] extracted = new int[m] ;//not using extract[0]
		for(int i=1;i<=n;i++)
		{
			
			Set j = repr[findSet(N[i]).key] ;//gives wrong value for 5
			if (j.no!=m)
			{
				extracted[j.no] = i ;	
				Set l = j.next ; 
				link(j,l) ;
				repr[l.r.key] = l ;
				T = deleteFromList(start,tail,j) ;
				j = null ;
				start = T.start ;
				tail = T.tail;
			}
		}
		return extracted ;
	}
	void printArray(int[] A)
	{
		for(int i=0;i<A.length;i++)
		{
			System.out.print(A[i]+" ") ;
		}
		System.out.println() ;
	}
	public static void main(String args[])
	{
		int m = 6,n = 9 ;
		Integer e = Integer.MAX_VALUE ;
		int[] A = {4,8,e,3,e,9,2,6,e,e,e,1,7,e,5} ;
		DisjointSetTree D = new DisjointSetTree() ;
		cont c = D.insertion(A,m,n) ;
		int[] extracted = D.offlineMinimum(c.T.start,c.T.tail,n,m,c.N,c.repr) ;
		D.printArray(extracted) ;
		
	}
}