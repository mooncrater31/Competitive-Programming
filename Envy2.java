import java.util.* ;
public class Envy2
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
			x.p = findSet(x.p) ;
		}
		return x.p ;
	}
	public static void main(String args[])
	{
		
		HashSet<Integer> Rep = new HashSet<Integer>() ;
		Scanner in = new Scanner(System.in) ;
		int n = in.nextInt() ;
		int m = in.nextInt() ;
		int[][][] M = new int[n+1][n+1][2] ;//adjacency matrix
		arr[] A = new arr[m] ;
		for(int i=0;i<M.length;i++)
		{
			for(int j=0;j<M[0].length;j++)
			{
				M[i][j][0] = -1 ;
			}
		}
		int[][] E = new int[m][3] ;
		for(int i=0;i<m;i++)
		{
			
			E[i][0] = in.nextInt() ;//starting vertex
			E[i][1] = in.nextInt() ;//ending vertex
			E[i][2] = in.nextInt() ;// Weight of the edge
			A[i] = new arr(E[i],i) ;
			if(M[E[i][0]][E[i][1]][0]==-1)
			{
				M[E[i][0]][E[i][1]][0] = E[i][2] ;
				M[E[i][1]][E[i][0]][0] = E[i][2] ;
				M[E[i][0]][E[i][1]][1] = i ;
				M[E[i][1]][E[i][0]][1] = i ;
			}
			else
			{
				if(M[E[i][0]][E[i][1]][0]>E[i][2])
				{
					Rep.add(M[E[i][0]][E[i][1]][1]) ;//adding the redundant edge into the hashset
					M[E[i][0]][E[i][1]][0] = E[i][2] ;
					M[E[i][1]][E[i][0]][0] = E[i][2] ;
					M[E[i][0]][E[i][1]][1] = i ;
					M[E[i][1]][E[i][0]][1] = i ;
				}
				else
				{
					Rep.add(i) ;
				}
			}
		}
		
		// int[][] sortedE = new int[E.length][E[0].length];
		// System.arraycopy(E,0,sortedE,0,E.length) ;
		// Arrays.sort(sortedE,(a,b)->Integer.compare(a[2],b[2])) ;//important!!
		Arrays.sort(A) ;
		// System.out.println("Before A:") ;
		// for(int i=0;i<A.length;i++)
		// {
			// System.out.println(i+"  "+A[i].E[0]+" "+A[i].E[1]+" "+A[i].E[2]+"::"+A[i].index) ;
		// }
		int q = in.nextInt() ;//Number of queries
		boolean[] ans = new boolean[q] ;//answers
		breakOut:
		for(int i=0;i<q;i++)
		{
			HashSet<Integer> Q = new HashSet<Integer>() ;
			Set[] V = new Set[n+1] ; //a set for each vertex. 0 represents no vertex 
			for(int j=1;j<=n;j++)
			{
				V[j] = new Set(j) ;//Object creation
				makeSet(V[j]) ;//initialization
			}
			int nq = in.nextInt() ;
			for(int j=0;j<nq;j++)
			{
			
				int temp = in.nextInt() -1 ;
				Q.add(temp) ;
				if(Rep.contains(temp))
				{
					ans[i] = false ;
					for(int k=0;k<nq-j-1;k++)
					{
						in.nextInt() ;
					}
					continue breakOut ;
					
				}
			}
			ans[i] = mstChecker(A,V,Q) ;
		}
		for(int i=0;i<q;i++)
		{
			System.out.println(ans[i]?"YES":"NO") ;
		}
		
	}
	static boolean mstChecker(arr[] A,Set[] V,HashSet<Integer> Q)
	{
		for(int i=A.length-1;i>=0;i--)
		{
			HashSet<Integer> H = new HashSet<Integer>() ;
			int j=i ;
			while(j>=0 &&A[j].c==A[i].c)
			{
				
				if(Q.contains(A[j].index))
				{
					H.add(j) ;
					
				}
				j-- ;
			}
			for(int k=j+1;k<=i;k++)
			{
				if(Q.contains(A[k].index))
					H.remove(k) ;
				else
				{
					Iterator itr = H.iterator() ;
					if(itr.hasNext())
					{
						int index = (int)itr.next() ;
						arr temp = A[k] ;
						A[k] = A[index] ;
						A[index] = temp ;
						H.remove(index) ;
					}
				}
			}
			
		}
		// System.out.println("After A:") ;
		// for(int i=0;i<A.length;i++)
		// {
			// System.out.println(i+"::"+A[i].E[0]+" "+A[i].E[1]+" "+A[i].E[2]+"::"+A[i].index) ;
		// }
		for(int i=0;i<A.length;i++)
		{
			int a = A[i].E[0] ;
			int b = A[i].E[1] ;
			Set s1 = findSet(V[a]) ;
			Set s2 = findSet(V[b]) ;
			if(Q.contains(A[i].index))
			{
				if(s1!=s2)
				{
					link(s1,s2) ;
				}
				else return false ;
			}
			else 
			{
				link(s1,s2) ;
			}
			
		}
		return true ;
	}
	
}
class arr implements Comparable<arr>
{
	public int c ;
	public int[] E ;
	public int index ;
	arr(int[] e,int i)
	{
		this.E = e ;
		this.c = e[e.length-1] ;
		this.index = i ;
	}
	public int compareTo(arr that)
	{
		return (this.c-that.c) ;
	}
	
}
class Set
{
	int value ;
	int rank  ;
	Set p ;
	Set(int v)
	{
		value = v ;
	}
}