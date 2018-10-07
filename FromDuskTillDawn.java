import java.util.* ;
import java.io.BufferedReader ;
import java.io.InputStreamReader ;
//UVa - 10187 - From Dusk Till Dawn
/*Bugs :
#1: Problem for the inputs:
1
13
pmxzkplcyxrngwso mvvvryzydooqjlc 3 3
pmxzkplcyxrngwso rlunirnjtvpesdgyeacs 4 2
q rmjasjwernaylz 22 8
mvvvryzydooqjlc jcbkyomkxfbpbvpsy 5 1
rlunirnjtvpesdgyeacs hlpifovtlkvm 19 6
q jcbkyomkxfbpbvpsy 20 8
rmjasjwernaylz tmenwffsduemfgzj 19 10
tmenwffsduemfgzj q 5 1
tmenwffsduemfgzj mvvvryzydooqjlc 18 2
pmxzkplcyxrngwso q 20 1
hlpifovtlkvm rmjasjwernaylz 5 1
pmxzkplcyxrngwso rmjasjwernaylz 1 4
jcbkyomkxfbpbvpsy pmxzkplcyxrngwso 3 3
jcbkyomkxfbpbvpsy rlunirnjtvpesdgyeacs
Gives answer: 1.
Correct answer : 2.
-------------------------------------------
1
13
p m 3 3
p r 4 2
q rm 22 8
m j 5 1 
r h 19 6
q j 20 8
rm t 19 10
t q 5 1
t m 18 2
p q 20 1
h rm 5 1
p rm 1 4
j p 3 3 
j r 
------------------------------------------
and 

1
37
brsqfu jhwmfnxblzqwlzhjrqiykeobl 20 2
ieimmiuffv rbjdglhdzcgsmjghefluzuzxpzydcwih 1 4
gvamjfbutyhwghpxthlkmw hysmygimnzfontdymamwkzqwxwza 19 2
hysmygimnzfontdymamwkzqwxwza xm 19 8
nkrluzzsrxvufjf zgivvwkmvbxtp 2 4
rtluirjsn brsqfu 19 7
rbjdglhdzcgsmjghefluzuzxpzydcwih yhpziliufe 3 1
fr vl 4 2
xrzpjachayfiqlhafwqiffnyaobmmeh xm 20 8
rbjdglhdzcgsmjghefluzuzxpzydcwih gvamjfbutyhwghpxthlkmw 20 10
gp rtluirjsn 20 10
sxsvcchhdotmzavumm ieimmiuffv 2 3
xrzpjachayfiqlhafwqiffnyaobmmeh gp 1 4
fr sxsvcchhdotmzavumm 22 3
nkrluzzsrxvufjf fr 19 10
gvamjfbutyhwghpxthlkmw rbjdglhdzcgsmjghefluzuzxpzydcwih 2 2
fr ikpbgbpqcukbr 19 6
ieimmiuffv brsqfu 2 4
rtluirjsn gvamjfbutyhwghpxthlkmw 2 1
ikpbgbpqcukbr xrzpjachayfiqlhafwqiffnyaobmmeh 5 1
jnrqlbeuyhvyl sxsvcchhdotmzavumm 4 7
zgivvwkmvbxtp sxsvcchhdotmzavumm 5 1
gp yhpziliufe 1 3
xrzpjachayfiqlhafwqiffnyaobmmeh yhpziliufe 18 9
rtluirjsn nkrluzzsrxvufjf 5 1
jnrqlbeuyhvyl nkrluzzsrxvufjf 2 4
jhwmfnxblzqwlzhjrqiykeobl rtluirjsn 3 3
rtluirjsn nkrluzzsrxvufjf 5 1
rtluirjsn jhwmfnxblzqwlzhjrqiykeobl 18 12
yhpziliufe jnrqlbeuyhvyl 21 7
xm vl 4 2
vl hysmygimnzfontdymamwkzqwxwza 18 11
yhpziliufe gvamjfbutyhwghpxthlkmw 5 1
fr vl 4 2
sxsvcchhdotmzavumm ieimmiuffv 20 5
rbjdglhdzcgsmjghefluzuzxpzydcwih fr 19 4
brsqfu xm 4 2
zgivvwkmvbxtp brsqfu
Gives answer : 2.
Correct answer: 1.
-----------------------------------------
1
37
b j 20 2
i r 1 4
g h 19 2
h x 19 8
n z 2 4
rt b 19 7
r y 3 1
f v 4 2
xr x 20 8
r g 20 10
gp rt 20 10
s i 2 3
xr gp 1 4
f s 22 3
n f 19 10
g r 2 2
f ik 19 6
i b 2 4
rt g 2 1
ik xr 5 1
jn s 4 7
z s 5 1
gp y 1 3
xr y 18 9
rt n 5 1
jn n 2 4
j rt 3 3
rt n 5 1
rt j 18 12
y jn 21 7
x v 4 2
v h 18 11
y g 5 1
f v 4 2
s i 20 5
r f 19 4
b x 4 2
z b 

*/
class FromDuskTillDawn
{
	private static final boolean debug = false ;
	private static final boolean debug2 = false ;
	public static void main(String args[]) throws Exception
	{
		BufferedReader bro = new BufferedReader(new InputStreamReader(System.in)) ;
		int T = Integer.parseInt(bro.readLine()) ;
		for(int t=0;t<T;t++)
		{
			int M = Integer.parseInt(bro.readLine()) ;
			if(t==62 && debug2) System.out.println("M::"+M) ;
			HashMap<String,Integer> H = new HashMap<String,Integer>() ;
			List<List<NodeEdge>> L = new ArrayList<List<NodeEdge>>() ;
			for(int i=0;i<M;i++)
			{
				String[] S = bro.readLine().split(" ") ;
				int startTime = Integer.parseInt(S[2])%24 ;
				int duration = Integer.parseInt(S[3])%24 ;
				if((startTime>=18 || startTime<=6) && ((startTime+duration)%24<=6 || (startTime+duration)%24>=18) && duration<=12)
				{
					if(!H.containsKey(S[0]))
					{
						L.add(new ArrayList<NodeEdge>()) ;
						if(debug) System.out.println(S[0]+" was added to the HashMap.") ;
						H.put(S[0],L.size()-1) ;
					}
					if(!H.containsKey(S[1]))
					{
						L.add(new ArrayList<NodeEdge>()) ;
						if(debug) System.out.println(S[1]+" was added to the HashMap.") ;
						H.put(S[1],L.size()-1) ;
					}
					int a = H.get(S[0]),b = H.get(S[1]) ;
					L.get(a).add(new NodeEdge(b,startTime,(startTime+duration)%24)) ;
					
				}
				if(t==62 && debug2)
				{
					System.out.println(S[0]+" "+S[1]+" "+S[2]+" "+S[3]) ;
				}
			}
			String[] S = bro.readLine().split(" ") ;
            if(t==62 && debug2) System.out.println(S[0]+" "+S[1]) ;

			int ans = -1 ;
			if(H.containsKey(S[0]) && H.containsKey(S[1]))
			{
				int Source = H.get(S[0]),D = H.get(S[1]);
				if(debug) 
				{
					System.out.println("From #"+Source+" to #"+D) ;
				}
				ans = dijkstra(L,Source,D) ;
			}
			System.out.println("Test Case "+(t+1)+".") ;
			System.out.println(ans==-1?"There is no route Vladimir can take.":"Vladimir needs "+ans+" litre(s) of blood.") ;
			
		}
	}
	static void listPrinter(List<List<NodeEdge>> L)
	{
		// List<List<NodeEdge>> L = new List<List<NodeEdge>>() ;
		for(int i=0;i<L.size();i++)
		{
			System.out.print("List #"+i+" : ") ;
			for(int j=0;j<L.get(i).size();j++)
			{
				NodeEdge temp = L.get(i).get(j) ;
				System.out.print("("+temp.toVertex+","+temp.startTime+","+temp.endTime+") ") ;
			}
			System.out.println() ;
		}
		System.out.println() ;
		
	}
	static int dijkstra(List<List<NodeEdge>> L,int S,int D)
	{
		int[] blood = new int[L.size()] ;
		Arrays.fill(blood,Integer.MAX_VALUE) ;
		if(debug)
		{
			System.out.println("THE INPUT LIST :") ;
			listPrinter(L) ;
		}
		blood[S] = 0 ;
		PriorityQueue<Node> pq = new PriorityQueue<Node>() ;
		pq.add(new Node(S,0,0)) ;
		while(!pq.isEmpty())
		{
			
			Node n = pq.poll() ;
			if(debug) System.out.println("Visited :"+n.v) ;
			if(n.v==D)
				return n.b ;
			if(n.b>blood[n.v]) continue ;
			for(int i=0;i<L.get(n.v).size() ;i++)
			{
			    
				NodeEdge temp = L.get(n.v).get(i); 
				if(n.v==S)
			        n.curr_time = temp.startTime ;
				int newCost = n.b ;
				int temp_curr_time = n.curr_time<=6?n.curr_time+24:n.curr_time ;
				int temp_startTime = temp.startTime<=6?temp.startTime+24:temp.startTime ;
				
				if(temp_curr_time>temp_startTime )
					newCost++ ;
				if(blood[temp.toVertex]>newCost)
				{
					blood[temp.toVertex] = newCost ;
					pq.add(new Node(temp.toVertex,newCost,temp.endTime)) ;
				}
			}
		}
		return -1 ;
	}
}
class Node implements Comparable<Node>//for the priorityQueue.
{
	int v,b,curr_time  ;
	Node(int v,int b,int c)
	{
		this.v = v ;
		this.b = b ;
		// this.d = d ;
		this.curr_time = c ;
	}
	@Override
	public int compareTo(Node n)
	{
		return this.b-n.b ;
	}
}
class NodeEdge 
{
	int toVertex,startTime,endTime ;
	NodeEdge(int v,int s,int e)
	{
		toVertex = v ;
		startTime = s ;
		endTime = e ;
	}
}
