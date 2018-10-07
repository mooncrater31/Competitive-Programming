import java.util.* ;
class Friend
{
	int popularity ;
	Friend before = null;
	Friend next = null;
	Friend(int n)
	{
		popularity = n ;
	}
	
}
class RemoveFriends2
{
	Friend head ;
	Friend tail ;
	// void printer(Friend[] F)
	// {
		// for(int i=0;i<F.length;i++)
		// {
			// System.out.print(F[i].popularity+" ") ;
		// }
		// System.out.println() ;
	// }
	Friend[] listMaker(Friend[] F)
	{
		//printer(F) ;
		head = F[0];
		tail = F[F.length-1] ;
		for(int i=0;i<F.length-1;i++)
		{
			F[i].next = F[i+1] ;
			F[i+1].before = F[i] ;
		}
		return F ;
	}
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
	void friendRemover()
	{
		//whenever we delete an object, we have to make sure that the object's predecessor and successor follow that predecessor.popularity>successor.popularity
		//, otherwise, we'll have to delete the predecessor
		//---------------
		//So, we'll have to move back once we delete the object
		
		boolean flag = false ;
		Friend temp = head;
		while( temp.next!=null)
		{
			if(temp.popularity<temp.next.popularity)
			{
				flag = true ;
				deleteFriend(temp) ;//this Friend object still contains pointers to it's predecessor and successor, even though it is removed from the list
				break ;
			}
				temp = temp.next ;
		}
		if(!flag)
		{
			deleteFriend(tail) ;
		}
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
			Friend[] M = new Friend[N] ;
			RemoveFriends2 R = new RemoveFriends2() ;
			for(int j=0;j<N;j++)
			{
				M[j] = new Friend(in.nextInt()) ;
			}
			M = R.listMaker(M) ;
			for(int j=0;j<K;j++)
			{
				R.friendRemover() ;
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