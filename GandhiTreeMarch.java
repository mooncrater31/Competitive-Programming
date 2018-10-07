import java.util.* ;
class header
{
	int col ;
	node head = null ;
	node tail = null ;
	header(int c)
	{
		col = c ;
	}
}
class node
{
	char val ;
	int col ;
	node next = null ;
	node prev = null ;
	node(char v)
	{
		val = v ;	
	}
}
class GandhiTreeMarch
{
	Map<Integer,header> hmap = new HashMap<Integer,header>() ;
	
	int endParFinder(String S)
	{
		int count = 1 ;
		for(int i=0;i<S.length();i++)
		{
			if(S.charAt(i)==')')
				count-- ;
			if(S.charAt(i)=='(')
				count++ ;
			if(count==0)
				return i ;
		}
		return -1 ;
	}
	void listAdder(header h,node c)
	{
		node temp = h.head ;
		if(h.head==null)
		{
			h.head = c ;
			h.tail = c ;
		}
		else
		{
			while(temp!=null && c.val>temp.val)
			{
				temp = temp.next ;
			}
			if(temp!=null)
			{
				if(temp.prev!=null)
				{
					temp.prev.next = c ;
					c.prev = temp.prev ; 
				}
				else
				{
					h.head = c ;
				}
				c.next = temp ;
				temp.prev = c ;
			}
			else
			{
				h.tail.next = c ;
				c.prev = h.tail ;
				h.tail = c ;
			}
		}
		
			
	}
	
	void stringID(String S,int parCol)
	{// the first character is the parent whose column number is identified already.
		//System.out.println("Parent is :"+S.charAt(0)) ;
		//after the parent, there will be an opening parenthesis
		if(S.charAt(2)=='.' && S.charAt(S.length()-2)=='.')// no children
		{
			return ;
		}
		if(S.charAt(2)=='.')//if the leftchild doesn't exist, but the right child does exist
		{
			header h ;
			if(hmap.get(parCol+1)==null)//that column hasn't been made yet
			{
				h = new header(parCol+1) ;
				hmap.put(parCol+1,h) ;
			}
			else//that column has already been created, we'll put the current node into the list.
			{
				h = hmap.get(parCol+1) ;
			}
			node N = new node(S.charAt(3)) ;
			//System.out.println("	right child: "+S.charAt(3)) ;
			listAdder(h,N) ;
			int end = endParFinder(S.substring(5))+5 ;//because index 4 will be a parenthesis
			stringID(S.substring(3,end+1),parCol+1) ;
		}
		else if(S.charAt(S.length()-2)=='.')//if the right child doesn't exist, but the left child does exist
		{
			header h ;
			if(hmap.get(parCol-1)==null)//that column hasn't been made yet
			{
				h = new header(parCol-1) ;
				hmap.put(parCol-1,h) ;
			}
			else //that column has already been created, we'll put the current node into that list.
			{
				h = hmap.get(parCol-1) ;
			}
			node N = new node(S.charAt(2)) ;
			//System.out.println("	left child: "+S.charAt(2)) ;
			listAdder(h,N) ;
			int end = endParFinder(S.substring(4))+4 ;//because index 3 will be a parenthesis.
			stringID(S.substring(2,end+1),parCol-1) ;
		}
		else//both the children exist
		{
			//We know for sure that S.charAt(2) is the left child, thus S.charAt(3) is '(', and after that we'll apply endParFinder, to find where it ends
			// Let it be j. Then we know fo' sure that S.char(j+1) is the right Child. 
			header hl,hr ;
			int lChild = 2 ;
			int rChild = endParFinder(S.substring(4))+5 ;
			//checking for headers
			if(hmap.get(parCol-1)==null)
			{
				hl = new header(parCol-1) ;
				hmap.put(parCol-1,hl) ;
			}
			else
			{
				hl = hmap.get(parCol-1) ;
			}
			if(hmap.get(parCol+1)==null)
			{
				hr = new header(parCol+1) ;
				hmap.put(parCol+1,hr) ;
			}
			else
			{
				hr = hmap.get(parCol+1) ;
			}
			node L = new node(S.charAt(lChild)) ;
			node R = new node(S.charAt(rChild)) ;
			//System.out.println("	right child: "+S.charAt(rChild)) ;
			//System.out.println("	left child: "+S.charAt(lChild)) ;
			listAdder(hl,L) ;
			listAdder(hr,R) ;
			stringID(S.substring(2,rChild),parCol-1) ;
			stringID(S.substring(rChild,S.length()-1),parCol+1) ;
		}
	}
	public static void main(String args[])
	{
		Scanner in = new Scanner(System.in) ;
		int tests = in.nextInt() ;
		header[] H = new header[tests] ;
		for(int i=0;i<tests;i++)
		{
			int C = in.nextInt() ;
			String S = in.next() ;
			//System.out.println(C+" "+" S is: "+ S) ;
			GandhiTreeMarch G = new GandhiTreeMarch() ;
			node temp = new node(S.charAt(0)) ;
			header h = new header(0) ;
			G.hmap.put(0,h) ;
			G.listAdder(h,temp) ;
			G.stringID(S,0) ;
			H[i] = G.hmap.get(C) ;
			
		} 
		for(int i=0;i<tests;i++)
		{
			if(H[i]==null)
			{
				System.out.println("Common Gandhijee!") ;	
			}				
			else
			{
				node temp = H[i].head ;
				while(temp!=null)
				{
					System.out.print(temp.val) ;
					temp = temp.next ;
				}
				System.out.println() ;
			}				
		}			
	}
}