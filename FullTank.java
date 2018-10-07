	import java.util.* ;
	import java.io.BufferedReader ;
	import java.io.InputStreamReader ;
	//Implement Meet in the middle.
	//UVa-11367
	//Getting a TLE: There is a problem in the modifiedDijkstra() method.
	/*
	There is no identity of an edge. We just have to access them in an order. Use an adjacency list instead of an adjacency matrix.
	*/
	public class FullTank
	{
		private static final boolean debug = false ;
		private static final boolean debug2 = false ;
		public static void main(String args[]) throws Exception
		{
			BufferedReader bro = new BufferedReader(new InputStreamReader(System.in)) ;
			String[] S = bro.readLine().split(" ") ;
			int n = Integer.parseInt(S[0]) ;
			int m = Integer.parseInt(S[1]) ;
			S = bro.readLine().split(" ") ;
			int[] prices = new int[n] ;
			for(int i=0;i<n;i++)
			{
				prices[i] = Integer.parseInt(S[i]) ;
			}
			// int[][] M = new int[n][n] ;
			List<List<vertex>> M = new ArrayList<List<vertex>>() ;
			for(int i=0;i<n;i++)
			{
				M.add(new ArrayList<vertex>()) ;
			}
			for(int i=0;i<m;i++)
			{
				S = bro.readLine().split(" ") ;
				int a = Integer.parseInt(S[0]) ;
				int b = Integer.parseInt(S[1]) ;
				int w = Integer.parseInt(S[2]) ;
				// M[a][b] = w ;
				// M[b][a] = w ;
				M.get(a).add(new vertex(b,w)) ;
				M.get(b).add(new vertex(a,w)) ;
			}
			if(debug) 
			{
				System.out.println("EDGES ::") ;
				showEdges(M) ;
			}
			int q = Integer.parseInt(bro.readLine()) ;
			double tim[] = new double[q] ;
			double sum = 0 ;
			for(int i=0;i<q;i++)
			{
				S = bro.readLine().split(" ") ;
				int cap = Integer.parseInt(S[0]) ;
				int s = Integer.parseInt(S[1]) ;
				int d = Integer.parseInt(S[2]) ;
				double t1 = System.nanoTime() ;
				int ans = modifiedDijkstra(cap,s,d,M,prices) ;
				double t2 = System.nanoTime() ;
				tim[i] = (t2-t1)/1000000000 ;
				sum+=tim[i] ;
				System.out.println((ans==Integer.MAX_VALUE?"impossible":ans)) ;
			}
			if(debug2) System.out.println(sum/q) ;
		}
		static int modifiedDijkstra(int capacity,int source,int destination,List<List<vertex>> M,int[] prices)
		{
			int[][] A = new int[prices.length][capacity+1] ;
			PriorityQueue<Triplet> pq = new PriorityQueue<Triplet>() ;
			pq.add(new Triplet(source,0,0)) ;
			
			// Arrays.fill(A,Integer.MAX_VALUE) ;
			for(int i=0;i<A.length;i++)
			{
				for(int j=0;j<A[i].length;j++)
					A[i][j] = Integer.MAX_VALUE ;
			}
			// if(debug) arrayPrinter(A) ;
			A[source][0] = 0 ;
			while(!pq.isEmpty())
			{
				Triplet temp = pq.poll() ;
				if(debug) System.out.println("NODE ::"+temp.node+" "+temp.fuel+" "+temp.price) ;
				int rate = prices[temp.node] ;
				if(temp.node==destination)
					return temp.price ;
				for(int i=0;i<M.get(temp.node).size();i++)
				{//Different Node, Fuel decreases, Price remains the same.
					if(debug && temp.node==2 && i==2  ) 
					{
						System.out.println("DISTANCE TO 3 : "+M.get(temp.node).get(i).dist+" FUEL :"+temp.fuel) ;
						if(temp.fuel>=M.get(temp.node).get(i).dist) System.out.println("ORIGINAL PRICE AT 3 :"+A[i][temp.fuel-M.get(temp.node).get(i).dist]+" price at 2 :"+temp.price);
					}
					if(M.get(temp.node).get(i).dist<=temp.fuel && A[M.get(temp.node).get(i).v][temp.fuel-M.get(temp.node).get(i).dist]>temp.price)
					{
						if(debug) System.out.println("Added the needed.") ;
						A[M.get(temp.node).get(i).v][temp.fuel-M.get(temp.node).get(i).dist] = temp.price ;
						pq.add(new Triplet(M.get(temp.node).get(i).v,temp.fuel-M.get(temp.node).get(i).dist,temp.price)) ;
					}
				}
				for(int temp_fuel = temp.fuel,temp_price = temp.price;temp_fuel<=capacity;temp_fuel++,temp_price+=rate)
				{//Same node, fuel is increased, price changes
					if(temp_price<A[temp.node][temp_fuel])
					{
						A[temp.node][temp_fuel] = temp_price ;
						pq.add(new Triplet(temp.node,temp_fuel,temp_price)) ;
					}
					else if(temp_price>A[temp.node][temp_fuel]) break;
				}
				
			
			}
			if(debug) arrayPrinter(A) ;
			return Integer.MAX_VALUE ;
		}
		static void arrayPrinter(int[][] A)
		{
			for(int i=0;i<A.length;i++)
			{
				for(int j=0;j<A[i].length;j++)
				{
					System.out.print(A[i][j]+" ") ;
				}
				System.out.println() ;
			}
		}
		static void showEdges(List<List<vertex>> M)
		{
			for(int i=0;i<M.size();i++)
			{
				for(int j=0;j<M.get(i).size();j++)
				{
					System.out.print("("+M.get(i).get(j).v+","+M.get(i).get(j).dist+") ") ;
				}
				System.out.println() ;
			}
		}
		
	}

	class vertex
	{
		int v, dist ;//distance from the list head
		vertex(int v,int d)
		{
			this.v = v ;
			this.dist = d ;
		}
	}
	class Triplet implements Comparable<Triplet>
	{
		int node,fuel,price ;
		Triplet(int n,int f,int p)
		{
			node = n ;
			fuel = f ;
			price = p ;
		}
		
		@Override
		public int compareTo(Triplet t)
		{
			return this.price-t.price ;
		}
	}

