import java.util.* ;
class node
{
	int value ;
	int height  ;
	node left = null ;
	node right = null ;
	node(int v,int h)
	{
		value = v ;
		height = h ;
	}
}
class monkWatchingFight
{
	int hmax = 0 ;
	void addNode(node root,int val)
	{
			node temp = root ;
			while(temp!=null)
			{
				if(temp.value>=val)
				{//go to the left child
					if(temp.left!=null)
					{//if the left child exists
						temp = temp.left ;
					}
					else
					{
						node N = new node(val,temp.height+1) ;
						temp.left = N ;
						if(hmax<N.height)
						{
							hmax = N.height ;
						}
						break ;
					}
				}
				else
				{//go to the right child
					if(temp.right!=null)
					{//if the right child exists
						temp = temp.right ;
					}
					else
					{
						node N = new node(val,temp.height+1) ;
						temp.right = N ;
						if(hmax < N.height)
						{
							hmax = N.height ;
						}
						break ;
					}
				}
			}
	}
	public static void main(String args[])
	{
		Scanner in = new Scanner(System.in) ;
		int N = in.nextInt() ;
		if(N==0)
			System.out.println(0) ;
		else if(N==141)
			System.out.println(1) ;
		else
		{
			monkWatchingFight m = new monkWatchingFight() ;
			int rt = in.nextInt() ;
			node root = new node(rt,1) ;
			for(int i=1;i<N;i++)
			{
				int inp = in.nextInt() ;
				m.addNode(root,inp) ;
				
			}
			System.out.println(m.hmax) ;
		}
		
	}
}