import java.util.* ;
import java.io.BufferedReader ;
import java.io.InputStreamReader ;
/*
Bugs
#1: ArrayIndexOutOfBoundsException for findSet.
-> Was taking the input wrongly.
#2: Taking only one input.

*/
public class ComputerSites
{
	private static final boolean debug = false ;
	public static void main(String args[]) throws Exception
	{
		BufferedReader bro = new BufferedReader(new InputStreamReader(System.in)) ;
		for(String S = bro.readLine();!S.equals("");S = bro.readLine())
		{
			int n = Integer.parseInt(S) ;
			int orig_sum = 0 ;
			for(int i=0;i<n-1;i++)
			{
				String[] s = bro.readLine().split(" ") ;
				orig_sum+=Integer.parseInt(s[2]) ;
			}
			List<triplet> EdgeList = new ArrayList<triplet>() ;
			int m = Integer.parseInt(bro.readLine()) ;
			for(int i=0;i<m;i++)
			{
				String[] s = bro.readLine().split(" ") ;
				int w = Integer.parseInt(s[2]) ;
				int a = Integer.parseInt(s[0]) ;
				int b = Integer.parseInt(s[1]) ;
				EdgeList.add(new triplet(w,a,b)) ;
			}
			int edges = Integer.parseInt(bro.readLine()) ;
			for(int i=0;i<edges;i++)
			{
				String[] s = bro.readLine().split(" ") ;
				int w = Integer.parseInt(s[2]) ;
				int a = Integer.parseInt(s[0]) ;
				int b = Integer.parseInt(s[1]) ;
				EdgeList.add(new triplet(w,a,b)) ;
			}
			System.out.println(orig_sum) ;
			System.out.println(kruskal(EdgeList)) ;
			System.out.println() ;
			
		}
	}
	static int kruskal(List<triplet> EdgeList)
	{
		Collections.sort(EdgeList) ;
		int mst_cost = 0 ;
		if(debug) System.out.println(" EdgeList.size() : "+EdgeList.size()) ;
		UnionFind U = new UnionFind(EdgeList.size()) ;
		
		for(int i=0;i<EdgeList.size();i++)
		{
			triplet t = EdgeList.get(i) ;
			if(debug) System.out.println("Between :"+t.a+" and "+t.b) ;
			if(!U.isSameSet(t.a,t.b))
			{
				mst_cost+=t.w ;
				U.unionSet(t.a,t.b) ;
			}
		}
		return mst_cost ;
	}
}

class triplet implements Comparable<triplet>
{
	int w,a,b ;
	triplet(int wt,int at,int bt)
	{
		w = wt ;
		a = at ;
		b = bt ;
	}
	@Override
	public int compareTo(triplet t)
	{
		return this.w-t.w ;
	}
		
}
class UnionFind
{
	private static final boolean debug = false;
	private int[] p,rank ;
	UnionFind(int n)
	{
		p = new int[n] ;
		rank = new int[n] ;
		for(int i=0;i<n;i++)
		{
			p[i] = i ;
		}
	}
	int findSet(int i)
	{
		return (p[i]==i)?i:(p[i]= findSet(p[i])) ; 
	}
	boolean isSameSet(int i,int j)
	{
		return findSet(i) == findSet(j) ;
	}
	void unionSet(int i,int j)
	{
		int x = findSet(i),y = findSet(j) ;
		if(rank[x]>rank[y]) 
			p[y] = x ;
		else
		{
			p[x] = y ;
			if(rank[x]==rank[y]) 
				rank[y]++ ;
		}
	}
		
}