import java.util.* ;
import java.io.InputStreamReader ;
import java.io.BufferedReader ;

public class Uncompress
{
	private static final boolean debug = false ;
	static LList head = null ;
	public static void main(String args[]) throws Exception
	{
		BufferedReader bro = new BufferedReader(new InputStreamReader(System.in)) ;
		String megaString = "" ;
		for(String S = bro.readLine();!S.equals("0");S = bro.readLine()) 
		{
			megaString+="\n" + S ;
		}
		// String backup = megaString ;//Not needed 
		List<pair> ans = solve(megaString) ;
		for(int i=0;i<ans.size();i++)
		{
			megaString = megaString.replaceFirst(Integer.toString(ans.get(i).number),ans.get(i).S) ;
		}
		System.out.print(megaString) ;
	}
	static void listPrinter(List<pair> L)
	{
		for(pair p:L)
		{
			System.out.println(p.number+":"+p.S) ;
		}
	}
	static List<pair> solve(String S)
	{
		S = S.replaceAll("[^a-zA-Z0-9]"," ") ;
		S = S.replaceAll(" +"," ").trim() ;
		String[] arr = S.split(" ") ;
		if(debug)
		{
			System.out.println("arr:") ;
			for(String s:arr)
				System.out.print(s+" ") ;
		}
		head = new LList(arr[0]) ;
		HashMap<String,LList> H = new HashMap<String,LList>() ;
		List<pair> ans = new ArrayList<pair>() ;
		H.put(arr[0],head) ;
		for(int i=1;i<arr.length;i++)
		{
			if(arr[i].matches("[0-9]+"))
			{
				LList atIthPos = getNth(Integer.parseInt(arr[i])) ;
				delete(atIthPos) ;
				addAtFront(atIthPos) ;
				ans.add(new pair(Integer.parseInt(arr[i]),atIthPos.S)) ;
			}
			else
			{
				if(H.containsKey(arr[i]))
				{
					LList temp = H.get(arr[i]) ;
					delete(temp) ;
					addAtFront(temp) ;
					
				}
				else
				{
					LList temp = new LList(arr[i]) ;
					addAtFront(temp) ;
					if(debug) 
					{
						System.out.println(arr[i]+" was added at the front.") ;
					}
				}
			}
			if(debug) traverseList(head) ;
		}
		if(debug) listPrinter(ans) ;
		return ans ;
	}
	static void traverseList(LList head)
	{
		LList temp = head ;
		System.out.println("Here's the list:\n	") ;
		while(temp!=null)
		{
			System.out.print(temp.S+"-->") ;
			temp = temp.next ;
		}
		System.out.println() ;
	}
	static LList getNth(int n)
	{
		int count = 1;
		LList temp = head;
		while(count<n)
		{
			temp = temp.next ;
			count++ ;
		}
		return temp ;
	}
	static void addAtFront(LList obj)
	{
		// LList temp = new LList(S) ;
		
		obj.next = head ;
		head.prev = obj ;
		head = obj ;
	}
	static void delete(LList obj)
	{
		if(head==obj)
		{
			if(head.next!=null)
			{
				head = head.next ;
				head.prev = null ;
			}
			else
			{
				
				if(debug) 
				{
					System.out.println("head String :"+head.S) ;
				}
				head = null ;
			}
		}
		else
		{
			if(obj.next!=null)
				obj.next.prev = obj.prev ;
			obj.prev.next = obj.next ;
		}
	}
	
}
class LList
{
	String S ;
	LList next = null ;
	LList prev = null ;
	LList(String S)
	{
		this.S = S ;
	}
	
}
class pair
{
	int number ;
	String S ;
	pair(int n,String t)
	{
		this.number = n ;
		this.S = t ;
	}
}
