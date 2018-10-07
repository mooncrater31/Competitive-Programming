import java.util.* ;
// import java.io.BufferedReader ;
// import java.io.InputStreamReader ;
//Implement Meet in the middle.
//UVa-11367
public class FullTank3
{
	private static final boolean debug = false ;
	public static void main(String args[]) throws Exception
	{
		// BufferedReader bro = new BufferedReader(new InputStreamReader(System.in)) ;
		// String[] S = bro.readLine().split(" ") ;
		// int n = Integer.parseInt(S[0]) ;
		// int m = Integer.parseInt(S[1]) ;
		// S = bro.readLine().split(" ") ;
		// int[] prices = new int[n] ;
		// for(int i=0;i<n;i++)
		// {
			// prices[i] = Integer.parseInt(S[i]) ;
		// }
		// int[][] M = new int[n][n] ;
		// for(int i=0;i<m;i++)
		// {
			// S = bro.readLine().split(" ") ;
			// int a = Integer.parseInt(S[0]) ;
			// int b = Integer.parseInt(S[1]) ;
			// int w = Integer.parseInt(S[2]) ;
			// M[a][b] = w ;
			// M[b][a] = w ;
		// }
		// int q = Integer.parseInt(bro.readLine()) ;
		// for(int i=0;i<q;i++)
		// {
			// S = bro.readLine().split(" ") ;
			// int cap = Integer.parseInt(S[0]) ;
			// int s = Integer.parseInt(S[1]) ;
			// int d = Integer.parseInt(S[2]) ;
			// int ans = modifiedDijkstra(cap,s,d,M,prices) ;
			// System.out.println((ans==Integer.MAX_VALUE?"impossible":ans)) ;
		// }
		Scanner in = new Scanner(System.in) ;
		int n = in.nextInt() ;
		int m = in.nextInt() ;
		int[] prices = new int[n] ;
		for(int i=0;i<n;i++)
		{
			prices[i] = in.nextInt() ;
		}
		int[][] M = new int[n][n] ;
		for(int i=0;i<n;i++)
		{
			int a = in.nextInt() ;
			int b = in.nextInt() ;
			int w = in.nextInt() ;
			M[a][b] = w ;
			M[b][a] = w ;
		}
		int q = in.nextInt() ;
		for(int i=0;i<q;i++)
		{
			int cap = in.nextInt() ;
			int s = in.nextInt() ;
			int d = in.nextInt() ;
			int ans = modifiedDijkstra(cap,s,d,M,prices) ;
			System.out.println((ans==Integer.MAX_VALUE?"impossible":ans)) ;
		}
	}
	static int modifiedDijkstra(int capacity,int source,int destination,int[][] M,int[] prices)
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
			
			int rate = prices[temp.node] ;
			if(temp.node==destination)
				return temp.price ;
			for(int temp_fuel = temp.fuel,temp_price = temp.price;temp_fuel<=capacity;temp_fuel++,temp_price+=rate)
			{//Same node, fuel is increased, price changes
				if(temp_price<A[temp.node][temp_fuel])
				{
					A[temp.node][temp_fuel] = temp_price ;
					pq.add(new Triplet(temp.node,temp_fuel,temp_price)) ;
				}
				else if(temp_price>A[temp.node][temp_fuel]) break;
			}
			for(int i=0;i<M[temp.node].length;i++)
			{//Different Node, Fuel decreases, Price remains the same.
				if(M[temp.node][i]!=0 && M[temp.node][i]<=temp.fuel && A[i][temp.fuel-M[temp.node][i]]>temp.price)
				{
					A[i][temp.fuel-M[temp.node][i]] = temp.price ;
					pq.add(new Triplet(i,temp.fuel-M[temp.node][i],temp.price)) ;
				}
			}
		
		}
		int minimum = Integer.MAX_VALUE ;
		return minimum ;
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

