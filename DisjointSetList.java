class Node
{
	Set s = null;
	int key ;
	Node next =null ;
	Node(int x)
	{
		key = x ;
	}
}
class Set 
{
	int n ;
	Node head = null ;
	Node tail = null ;
}
class DisjointSetList
{
	Set makeSet(int x)
	{
		Node N = new Node(x) ;
		Set s = new Set() ;
		s.head = N ;
		s.tail = N ;
		s.n = 1;
		N.s = s ;
		return s ;
	}
	Set findSet(Node x)
	{
		return x.s ;
	}
	Set findSet(Set S[],int x)
	{
		for(int i=0;i<S.length;i++)
		{
			for(Node temp = S[i].head;temp!=null;temp=temp.next)
			{
				if(temp.key == x)
				{
					return S[i] ;
				}
			}
		}
		return null ;
	} 
	Set union(Set s1,Set s2) 
	{
		
		//if s1 has larger number of elements then, s2 is merged into s1 and s2 is deleted, otherwise vice versa
		if(s1.n<s2.n)
		{
			s1.tail.next = s2.head ;
			s1.tail = s2.tail ;
			s1.n = s1.n+s2.n ;
			Node temp  = s2.head ;
			while(temp!=null)
			{
				temp.s = s1 ;
				temp = temp.next ;
			}
			return s1 ;
		}
		else
		{
			s2.tail.next = s1.head ;
			s2.tail = s1.tail ;
			s2.n = s1.n+s2.n ;
			Node temp = s1.head ;
			while(temp!=null)
			{
				temp.s =s2 ;
				temp = temp.next ;
			}
			return s2 ;
		}
	}
	Set buildSet(int[] A)
	{
		Set[] S = new Set[A.length] ;//a set array to have the one-element Sets
		for(int i=0;i<A.length;i++)
		{
			S[i] = makeSet(A[i]) ;
		}
		for(int i=1;i<S.length;i++)//Unifies the Set S[0] to the current Set
		{
			S[0] = union(S[0],S[i]) ;
		}
		return S[0] ;
	}
	void printSet(Set S)
	{
		
		for(Node temp = S.head;temp!=null;temp = temp.next)
		{
			System.out.print(temp.key+" ") ;
		}
		System.out.println() ;
	}
	public static void main(String args[])
	{
		DisjointSetList D = new DisjointSetList() ;
		
		int[] s1arr = {15,25,86,31,8} ;
		int[] s2arr = {20,30,40,50,60} ;
		int[] s3arr = {23,55,1,24,5} ;
		int[] s4arr = {50,12,44,12,66} ;
		int[] s5arr = {50,21,26,70,86} ; 
		
		Set s1 = D.buildSet(s1arr) ;
		Set s2 = D.buildSet(s2arr) ;
		Set s3 = D.buildSet(s3arr) ;
		Set s4 = D.buildSet(s4arr) ;
		Set s5 = D.buildSet(s5arr) ; 
 		
		D.printSet(s1) ;
		D.printSet(s2) ;
		D.printSet(s3) ;
		D.printSet(s4) ;
		D.printSet(s5) ; 
		
	}
	
}