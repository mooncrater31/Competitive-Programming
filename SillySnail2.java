import java.util.* ;
class node
{
	node prev = null ;
	node next = null ;
	int value ;
	node(int v)
	{
		value = v ;
	}
}
class SillySnail2
{
	
	node head  = new node(1);
	void addToList(node par,node a,node b)
	{
		//System.out.print("par: "+par.value+" a: "+a.value+" b: "+b.value) ;
		a.next = b ;
		a.prev = par ;
		if(par.next!=null)
			b.next = par.next ;
		b.prev = a ;
		if(par.next!=null)
			par.next.prev = b ;
		par.next = a ;
	}
	public static void main(String args[])
	{
		Scanner in = new Scanner(System.in) ;
		int tests = in.nextInt() ;
		node[] results = new node[tests] ;
		for(int i=0;i<tests;i++)
		{
			int N = in.nextInt() ;
			Map<Integer,node> map = new HashMap<Integer,node>() ;
			SillySnail2 S = new SillySnail2()  ;
			
			map.put(new Integer(1),S.head) ;
			for(int j=0;j<N;j++)
			{
				int par = in.nextInt() ;
				int lChild = in.nextInt() ;
				int rChild = in.nextInt() ;
				//System.out.print("par: "+par+" lChild: "+lChild+" rChild: "+rChild) ;
				node lCh = new node(lChild) ;
				node rCh = new node(rChild) ;
				map.put(new Integer(lChild),lCh) ;
				map.put(new Integer(rChild),rCh) ;
				S.addToList(map.get(par),lCh,rCh) ;
				
			}
			results[i] = S.head ;
			
		}			
		for(int i=0;i<tests;i++)
		{
			node temp = results[i] ;
			while(temp!=null)
			{
				if(temp.value!=0)
					System.out.print(temp.value+" ") ;
				temp = temp.next ;
			}
			System.out.println() ;
		}
	}
	
}