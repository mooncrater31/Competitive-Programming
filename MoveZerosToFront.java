


class MoveZerosToFront{
	public static void main(String args[]) throws Exception
	{
		int[] A = {5,0,6,6,2,2,8,10,8,4,8,8,8,1,1,5,0,4,6,4} ;
		Node head = new Node(A[0]) ;
		for(int i=1;i<A.length;i++)
		{
			head = addToList(head,new Node(A[i])) ;
		}
		printList(head) ;
		head = moveZeroes(head) ;
		printList(head) ;
	}
	static void printList(Node head)
	{
		Node temp = head ;
		while(temp!=null)
		{
			System.out.print(temp.data+" ") ;
			temp = temp.next ;
		}
		System.out.println() ;
	}
	static Node addToList(Node head,Node toAdd)
	{
		toAdd.next = head ;
		head = toAdd ;
		return head ;
	}
    static Node moveZeroes(Node head)
    {
        head = reverse(head) ;
		printList(head) ;
        Node temp = head ;
        Node par = null ;
        while(temp!=null)
        {
            if(temp.data==0)
            {
                if(temp!=head)
                {
					Node temp2 = par ;
                    par.next = temp.next ;
                    temp.next = head ;
                    head = temp ;
                    temp = temp2 ;
                }
            }
			System.out.println("temp.data = "+temp.data) ;
            par = temp ;
            temp = temp.next ;
        }
        return head ;
    }
    static Node reverse(Node head)
    {
        Node temp = head ;
        Node par = null ;
        Node nxt = null ;
        while(temp!=null)
        {
            nxt = temp.next ;
            temp.next = par ;
            par = temp ;
            temp = nxt ;
        }
        head = par ;
        return head ;
    }
}
class Node
{
	Node next ;
	int data ;
	Node(int d)
	{
		this.data = d ;
	}
}