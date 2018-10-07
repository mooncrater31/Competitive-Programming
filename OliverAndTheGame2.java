import java.util.* ;
import java.io.BufferedReader ;
import java.io.InputStreamReader ;

class nodeA
{
	int value ;
	int size ;//keeps track of the number of nodes after nodeA
	nodeA next = null ;
	nodeA(int v)
	{
		value = v ;
	}
}
class nodeB
{
	int value ;
	boolean visited = false ;
	HashSet<Integer> H = new HashSet<Integer>() ;
	nodeB(int v)
	{
		value = v ;
	}
}
class OliverAndTheGame2
{
	int counter = 0;
	Map<Integer,nodeA> toList = new HashMap<Integer,nodeA>() ;//adjacency List
	// Map<Integer,nodeB> toNode = new HashMap<Integer,nodeB>() ;
	nodeB[] toNode ;
	OliverAndTheGame2(int n)
	{
		toNode = new nodeB[n+1] ;
	}
	
	void addToLinkedList(int a,nodeA N,nodeA x)
	{
		x.next = N ;
		toList.put(new Integer(a),x) ;//x replaces N as the first node
		
	}
	void addToList(int a,int x)// 
	{
		if(toNode[a]==null)
		{
			//System.out.println(a+" was added") ;
			toNode[a] = new nodeB(a) ;
		}
		if(toNode[x]==null)
		{
			// System.out.println(x+" was added");
			toNode[x] = new nodeB(x) ;
		}
		if(toList.containsKey(a))
		{
			nodeA X = new nodeA(x) ;
			nodeA N = toList.get(a) ;
			X.size = N.size+1 ;
			addToLinkedList(a,N,X) ;
		}
		else
		{
			nodeA N = new nodeA(x) ;
			N.size = 1 ;
			toList.put(new Integer(a),N) ;
		}
	}
	void modifiedDFS(int a)
	{
		counter++ ;
		if(toList.containsKey(a))
		{
			// System.out.println("Ohh Yeah "+a) ;
			nodeA Na = toList.get(a) ;
			nodeB Nb = toNode[a] ;
			
			for(nodeA tempA = Na;tempA!= null;tempA = tempA.next)
			{
				nodeB tempB = toNode[tempA.value] ;
				tempB.H.addAll(Nb.H) ;
				tempB.H.add(tempA.value) ;
				
				// System.out.println("tempB.H contains "+tempA.value+":"+tempB.H.contains(tempA.value)) ;
				modifiedDFS(tempA.value) ;
			}
		}
	}
	boolean onWay(int awayFK,int hide,int start)
	{
		// System.out.println("-->"+awayFK+" "+hide+" "+start) ;
		if(awayFK==0)//towards the king
		{
			nodeB N = toNode[start] ;
			if(N.H.contains(hide))
				return true ; 
			else 
				return false ;
				
		}
		else//away from the king1
		{
			nodeB N = toNode[hide] ;
			if(N.H.contains(start))
				return true ;
			else
				return false ;
			
		}
	}
	public static void main(String args[]) throws Exception 
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String line = br.readLine() ;
		int N = Integer.parseInt(line) ;
		
		OliverAndTheGame2 O = new OliverAndTheGame2(N) ;
		for(int i=0;i<N-1;i++)
		{
			line = br.readLine() ;
			O.addToList(Integer.parseInt(line.split(" ")[0]),Integer.parseInt(line.split(" ")[1])) ;//adding to adjacency list O(1)
		}
		O.toNode[1].H.add(1) ;
		O.modifiedDFS(1) ;
		int Q = Integer.parseInt(br.readLine()) ;
		boolean[] results = new boolean[Q] ;
		for(int i=0;i<Q;i++)
		{
			line = br.readLine() ;
			results[i] = O.onWay(Integer.parseInt(line.split(" ")[0]),Integer.parseInt(line.split(" ")[1]),Integer.parseInt(line.split(" ")[2])) ;
		}
		for(int i=0;i<Q;i++)
		{
			System.out.println(results[i]?"YES":"NO") ;
		}
		// System.out.println("Counter :"+O.counter) ;
		
		
	}
	
}