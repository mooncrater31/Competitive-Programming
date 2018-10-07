import java.util.* ;
public class Envy
{
	static void makeSet(Set x)
	{
		x.p = x ;
		x.rank = 0 ;
	}
	static void link(Set x,Set y)
	{
		if (x.rank>y.rank)
			y.p = x ;
		else if(x.rank==y.rank)
		{
			if(x.value<y.value)
			{
				x.p = y ;
				y.rank++ ;
			}
			else
			{
				y.p = x ;
				x.rank++ ;
			}
		}
		else
		{
			x.p = y ;
		}			
	}
	static Set findSet(Set x)
	{
		if(x!=x.p)
		{
			//System.out.println("		::"+x.value) ;
			x.p = findSet(x.p) ;
		}
		return x.p ;
	}
	public static void main(String args[])
	{
		Scanner in = new Scanner(System.in) ;
		int n = in.nextInt() ;
		int m = in.nextInt() ;
		int[][] E = new int[m][3] ;
		for(int i=0;i<m;i++)
		{
			E[i][0] = in.nextInt() ;//starting vertex
			E[i][1] = in.nextInt() ;//ending vertex
			E[i][2] = in.nextInt() ;// Weight of the edge
		}
		
		int[][] sortedE = new int[E.length][E[0].length];
		System.arraycopy(E,0,sortedE,0,E.length) ;
		Arrays.sort(sortedE,(a,b)->Integer.compare(a[2],b[2])) ;//important!!
		//System.out.println("	sortedE:") ;
		for(int i=0;i<sortedE.length;i++)
		{
			for(int j=0;j<sortedE[0].length;j++)
			{
				//System.out.print(sortedE[i][j]+" ") ;
			}
			//System.out.println() ;
		}
		int Q = in.nextInt() ;//Number of queries
		boolean[] ans = new boolean[Q] ;//answers
		for(int i=0;i<Q;i++)
		{
			Set[] V = new Set[n+1] ; //a set for each vertex. 0 represents no vertex 
			for(int j=1;j<=n;j++)
			{
				V[j] = new Set(j) ;//Object creation
				makeSet(V[j]) ;//initialization
			}
			//System.out.println("Query number :"+i) ;
			int nq = in.nextInt() ;
			int[] que = new int[nq] ;
			for(int j=0;j<nq;j++)
			{
				que[j] = in.nextInt()-1 ;
			}
			ans[i] = mstChecker(E,sortedE,V,que) ;
		}
		for(int i=0;i<Q;i++)
		{
			System.out.println(ans[i]?"YES":"NO") ;
		}
		
	}
	static boolean mstChecker(int[][] E,int[][] sortedE,Set[] V,int[] Q)
	{
		int[] weights = new int[Q.length] ;//weights of the edges in the query
		for(int i=0;i<Q.length;i++)
		{
			weights[i] = E[Q[i]][2] ;//filling "weights"
		}
		Arrays.sort(weights) ;//now "weights" is sorted
		//System.out.println("weights[0] is: "+weights[0]) ;
		//System.out.println("Initial Edges:") ;
		int weight = sortedE[0][2] ;
		for(int i=0; weight<weights[0];i++)
		{
			//System.out.println("weight=>"+weight) ;
			int a = sortedE[i][0] ;
			int b = sortedE[i][1] ;
			//System.out.println("	a:"+a+"("+V[a].rank+") b :"+b+"("+V[b].rank+")") ;
			Set s1 = findSet(V[a]) ;
			//System.out.println("	findSet for "+a+" is "+s1.value) ;
			Set s2 = findSet(V[b]) ;
			//System.out.println("	findSet for "+b+" is "+s2.value) ;
			//System.out.println("---------------") ;
			weight = sortedE[i+1][2] ;
			link(s1,s2);
			//System.out.println("	findSet for "+a+" is "+findSet(V[a]).value) ;
			//System.out.println("	findSet for "+b+" is "+findSet(V[b]).value) ;
		}
		//System.out.println("Query edges") ;
		for(int i=0;i<Q.length;i++)
		{
			int a = E[Q[i]][0] ;
			int b = E[Q[i]][1] ;
			//System.out.println("	a:"+a+"("+V[a].rank+") b :"+b+"("+V[b].rank+")") ;			
			Set s1 = findSet(V[a]) ;
			//System.out.println("	findSet for "+a+" is "+s1.value) ;
			Set s2 = findSet(V[b]) ;
			//System.out.println("	findSet for "+b+" is "+s2.value) ;
			if(s1!=s2)
			{
				link(s1,s2) ;
			}
			else
			{
				return false ;
			}
			//System.out.println("---------------") ;
			//System.out.println("	findSet for "+a+" is "+findSet(V[a]).value) ;
			//System.out.println("	findSet for "+b+" is "+findSet(V[b]).value) ;
			
		}
		return true ;
		
	}
	
}
class Set
{
	public Set(Set anotherSet)
	{
		anotherSet.value = this.value ;
		anotherSet.rank = this.value ;
		anotherSet.p = this.p ;
	}
	int value ;
	int rank  ;
	Set p ;
	Set(int v)
	{
		value = v ;
	}
}