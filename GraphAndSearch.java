package graph ;
class Node
{
	int num ;
	Node next ;
	Node(int n)
	{
		num = n ;
	}
}
class Vertex 
{
	int num ;
	int d ;// For BFS
	int disc ;//For DFS
	int fin ;// For DFS
	int c = 0;//0 for white,1 for gray, 2 for black
	int path = 0 ;
	boolean occ  = false ;
	Vertex p ;
}
/* enum colour {GRAY,BLACK,WHITE} ; */
class GraphAndSearch
{
	int r = 0 ;
	int top = -1 ;
	int time = 0;
	int head = -1,tail = - 1 ;
	int[] S ;
	int[] Q ;
	GraphAndSearch(int alpha)
	{
		Q = new int[alpha] ;
		S = new int[alpha] ;
	}
	Node[] createAL(int v)
	{
		Node[] N = new Node[v] ;// we have the first Vertex' index as 0
		return N ;
	}
	void insertIntoAL(Node[] N,int k,int num)
	{
		Node x = new Node(num) ;
		if(N[k]==null)
		{
			N[k] = x ;
		}
		else
		{
			x.next = N[k] ;
			N[k] = x ;
		}
	}
	void enq(int k)//circular queue for BFS
	{
		if(head == -1)
			head++ ;
		tail = (tail+1)%(Q.length) ;
		if(tail==head && tail!=0 && head !=0)
		{
			System.out.println("StackOverflow Bitch head :"+head+" tail "+tail) ;
		}
		else
		{
			Q[tail] = k ;
		}
	}
	int deq()
	{
		if(head == -1 && tail == -1)
		{
			System.out.println("EmptyShit!") ;
			return -1 ;
		}
		else
		{
			int v = Q[head] ;
			if(head == tail)
			{
				head = -1 ;
				tail = -1 ;
			}
			else
				head = (head+1)%Q.length ;
			return v ;
		}
	}
	boolean emptyQ()
	{
		if(head ==-1 && tail ==-1)
			return true ;
		return false ;
	}
	void BFS(Node[] N,Vertex[] V,int s)
	{
		V[s].c = 1 ;
		head = -1 ;
		tail = -1 ;
		V[s].p = null ;
		enq(s) ;
		System.out.println(s) ;
		while(!emptyQ())
		{
			int n = deq() ;
			for(Node temp = N[n];temp!=null ;temp = temp.next)
			{
				if(V[temp.num].c == 0)
				{
					enq(temp.num) ;
					V[temp.num].c = 1 ;
					V[temp.num].d = V[n].d+1 ;
					V[temp.num].p = V[n] ;
					System.out.println(temp.num) ;
				}
			}
			V[n].c = 2 ;
		}
	}
	void DFS(Node[] N,Vertex[] V)
	{
		time = 0;
		for(int i=0;i<V.length;i++)
		{
			if(V[i].c==0)
				DFSVisit(N,V,i) ;
		}
	}
	void DFSVisit (Node[] N,Vertex[] V,int i)
	{
		System.out.println(i) ;
		time++ ;
		V[i].disc = time ;
		V[i].c = 1 ;
		for(Node temp = N[i] ;temp!=null;temp = temp.next)
		{
			/* if(V[temp.num].c == 1) //used to determine the type of edge in DFS predecessor graph
				System.out.println(i+" to "+temp.num+" : BackEdge!") ;
			else if(V[temp.num].c == 2)
			{
				if(V[i].disc<V[temp.num].disc)
					System.out.println(i+" to "+temp.num+" : FrontEdge!") ;
				else
					System.out.println(i+" to "+temp.num+" : CrossEdge!") ;
			} */
			if(V[temp.num].c==0)
			{
				/* System.out.println(i+" to "+temp.num+" : TreeEdge!") ; */
				V[temp.num].p = V[i] ;
				DFSVisit(N,V,temp.num) ;
			}
		}
		V[i].c = 2 ;
		time++ ;
		V[i].fin = time ;
	}
	int simplePath(Node[] N,Vertex[] V,int s,int t)
	{
		if(s == t)
			return 1 ;
		else if(V[s].path!=0)
		{
			return V[s].path ;
		}
		else
		{
			for(Node temp = N[s] ;temp!=null;temp = temp.next)
			{
				V[s].path = V[s].path+simplePath(N,V,temp.num,t) ;
			}
			return V[s].path ;
		}
	}
	void printAL(Node[] N)
	{
		for(int i=0;i<N.length;i++)
		{
			System.out.print(i+" : ") ;
			for(Node temp = N[i];temp!=null;temp = temp.next)
			{
				System.out.print(temp.num+" ") ;
			}
			System.out.println() ;
		}
		
	}
	void push (int x)
		{
			if(top+1 == S.length)
			{
				System.out.println("StackOverflow") ;
			}
			else
			{
				top++ ;
				S[top] = x ;
			}
		}
		int pop()
		{
			if(top == -1)
			{
				System.out.println("StackUnderflow") ;
				return -1 ;
			}
			else
			{
				int r = S[top] ;
				top-- ;
				System.out.println(r) ;
				return r ;
			}
		}
		void DFSIterative(Node[] N,Vertex[] V)
		{
			time = 0;
			for(int i=0;i<V.length;i++)
			{
				if(V[i].c==0)
					DFSVisitIterative(N,V,i) ;
			}
		}
		boolean emptyStack()
		{
			if(top==-1)
				return true ;
			return false ;
		}
		void DFSVisitIterative(Node[] N,Vertex[] V,int i)
		{
			time++ ;
			V[i].disc = time ;
			V[i].c = 1 ;
			push(i) ;
			while(!emptyStack())
			{
				int k = pop() ;
				time++ ;
				V[k].c = 2;
				V[k].fin = time ;
				Node temp ;
				for(temp = N[k];temp != null && V[temp.num].c!=0;temp = temp.next) ;
				time++ ;
				if(temp!=null)
				{
					V[temp.num].c = 1 ;
					V[temp.num].disc = time ;
					push(temp.num) ;
				}
			}
		}
	boolean findCycle(Node[] N,Vertex[] V,int i)
	{
		if(!V[i].occ)
		{
			V[i].occ = true ;
			for(Node temp = N[i]; temp!=null;temp = temp.next)
			{
				findCycle(N,V,temp.num) ;
			}
			V[i].occ = false ;
			return true ;
		}
		else
			return false ;
	}
	
	public static void main(String args[])
	{
		GraphAndSearch G = new GraphAndSearch(8) ;
		Node[] N = G.createAL(8) ;
		G.insertIntoAL(N,0,4) ;
		G.insertIntoAL(N,0,1) ;
		G.insertIntoAL(N,1,0) ;
		G.insertIntoAL(N,1,5) ;
		G.insertIntoAL(N,2,5) ;
		G.insertIntoAL(N,2,6) ;
		G.insertIntoAL(N,2,3) ;
		G.insertIntoAL(N,3,2) ;
		G.insertIntoAL(N,3,6) ;
		G.insertIntoAL(N,3,7) ;
		G.insertIntoAL(N,4,0) ;
		G.insertIntoAL(N,5,1) ;
		G.insertIntoAL(N,5,2) ;
		G.insertIntoAL(N,5,6) ;
		G.insertIntoAL(N,6,5) ;
		G.insertIntoAL(N,6,2) ;
		G.insertIntoAL(N,6,3) ;
		G.insertIntoAL(N,6,7) ;
		G.insertIntoAL(N,7,3) ;
		G.insertIntoAL(N,7,6) ;
		/* G.insertIntoAL(N,0,1) ;
		G.insertIntoAL(N,0,3) ;
		G.insertIntoAL(N,1,4) ;
		G.insertIntoAL(N,2,5) ;
		G.insertIntoAL(N,2,4) ;
		G.insertIntoAL(N,3,1) ;
		G.insertIntoAL(N,4,3) ;
		G.insertIntoAL(N,5,5) ; */
		G.printAL(N) ;
		Vertex[] V = new Vertex[8] ;
		for(int i=0;i<V.length;i++)
		{
			V[i] = new Vertex() ;
		}
		System.out.println("BFS") ;
		G.BFS(N,V,0) ;
		System.out.println("DFS") ;
		Vertex[] V2 = new Vertex[8] ;
		for(int i=0;i<V2.length;i++)
		{
			V2[i] = new Vertex() ;
		}
		
		G.DFS(N,V2) ;
		Vertex[] V3 = new Vertex[8] ;
		for(int i=0;i<V3.length;i++)
		{
			V3[i] = new Vertex() ;
		}
		
		/* System.out.println("No. of paths from 0 to 7 "+G.simplePath(N,V,0,7) ) ; */
		System.out.println("DFSIterative") ;
		G.DFSIterative(N,V3) ;
		System.out.println("Cycles? :"+G.findCycle(N,V,0)) ;
	}
	
}