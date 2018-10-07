import java.util.* ;
class Friend
{
	int popularity ;
	Friend next = null ;
	Friend before = null ;
	Friend(int n)
	{
		popularity = n ;
	}
	
}
class RemoveFriends
{
	Friend head ;// pointer to the first friend object in the linked list
	Friend tail ;//pointer to the last Friend object int the linked list
	// RemoveFriends(Friend start)
	// {
		// head = start ;
	// }
	void deleteFriend(Friend F)
	{
		if(F==head && F==tail)// if the list contains only one object
		{
			head = null ;
			tail = null ;
		}
		else if(F==head)// F is the first object
		{
			head = F.next ;
			F.next.before = null ;
		}
		else if(F==tail)// F is the last object
		{
			F.before.next = null ;
			tail = F.before ;
		}
		else
		{
			F.before.next = F.next ;
			F.next.before = F.before ;
		}
		
	}
	void addToList(Friend F)// can't put it infront of the list because of the algorithm
	{
		if(head==null&&tail==null)//empty list
		{
			head = F ;
			tail = F ;
		}
		else
		{
			tail.next = F ;
			F.before = tail ;
			tail = F ;
		}
	}
	Friend friendRemover(Friend start)//this function removes one Object, and returns the predecessor of the removed object
	{
		boolean flag = false ;
		Friend temp = start;
		while( temp.next!=null)
		{
			if(temp.popularity<temp.next.popularity)
			{
				flag = true ;
				deleteFriend(temp) ;//this Friend object still contains pointers to it's predecessor and successor, even though it is removed from the list
				return temp.before ;
			}
				temp = temp.next ;
		}
		if(!flag)
		{
			deleteFriend(tail) ;
			return tail ;
		}
		return null ;
	}
	public static void main(String args[])
	{
		
		Scanner in  = new Scanner(System.in) ;
		int tests = in.nextInt() ;
		Friend[] F = new Friend[tests] ;
		for(int i=0;i<tests;i++)
		{
			int N = in.nextInt() ;
			int K = in.nextInt() ;
			RemoveFriends R = new RemoveFriends() ;
			for(int j=0;j<N;j++)
			{
				Friend juari = new Friend(in.nextInt()) ;
				R.addToList(juari) ;
				//now R.head and R.tail have the pointers of the first and the last objects respectively
			}
			Friend start = R.head ;
			for(int j=0;j<K;j++)
			{
				if(start==null)
					start = R.friendRemover(R.head) ;
				else
					start = R.friendRemover(start) ;
			}
			F[i] = R.head ;
			
		}
		for(int i=0;i<tests;i++)
		{
			Friend temp = F[i] ;//temp now contains the pointer to the first object of the list
			while(temp!=null)
			{
				System.out.print(temp.popularity+" ") ;
				temp = temp.next ;
			}
			System.out.println() ;
		}
	}
	
}