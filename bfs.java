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
public class bfs
{
	int n = 100 ;
	node[] queue = new node[n] ;
	int head = -1 ;
	int tail = -1 ;
	
	
	
		
	void enq(node N)
	{
		if(N==null)
			return ;
		if(head == -1 && tail == -1)
		{
			tail = 0 ;
		}
		head = (head+1)%n ;
		queue[head] = N ;
		
	}
	node deq()
	{
		if(head==tail)
		{
			head = -1 ;
			tail = -1 ;
		}
		node temp =  queue[tail] ;
		
		tail = (tail+1)%n ;
		return temp ;
		
	}
	void bfsSearch(node root)
	{
		enq(root) ;
		while(head != -1 || tail != -1)//i.e the queue isn't empty
		{
			node temp = deq() ;
			System.out.print(temp.value+" ") ;
			enq(temp.left) ;
			enq(temp.right) ;
		}
	}
	
}