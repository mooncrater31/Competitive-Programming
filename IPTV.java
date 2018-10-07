import java.util.* ;
import java.io.BufferedReader ;
import java.io.InputStreamReader ;

public class IPTV
{
	public static void main(String args[]) throws Exception
	{
		BufferedReader bro = new BufferedReader(new InputStreamReader(System.in)) ;
		int t = Integer.parseInt(bro.readLine()) ;
		bro.readLine() ;
		for(int i=0;i<t;i++)
		{
			int n = Integer.parseInt(bro.readLine()) ;
			String[] A = new String[n] ;
			int ptr = 0 ;
			int m = Integer.parseInt(bro.readLine()) ;
			List<triplet> EdgeList = new ArrayList<triplet>() ;
			HashMap<String,Integer> H = new HashMap<String,Integer>() ;
			for(int j=0;j<m;j++)
			{
				String[] s = bro.readLine().split(" ") ;
				int a,b ;
				if(!H.containsKey(s[0]))
				{
					a = ptr ;
					A[ptr] = s[0] ;
					H.put(s[0],ptr) ;
					ptr++ ;
				}
				else
				{
					a = H.get(s[0]) ;
				}
				if(!H.containsKey(s[1]))
				{
					b = ptr ;
					A[ptr] = s[1] ;
					H.put(s[1],ptr) ;
					ptr++ ;
				}
				else
				{
					b = H.get(s[1]) ;
				}
				
				EdgeList.add(new triplet(a,b,Integer.parseInt(s[2])));
			}
			bro.readLine() ;//empty line
			System.out.println(kruskal(EdgeList)) ;
			System.out.println() ;
		}
	}
	static int kruskal(List<triplet> EdgeList)
	{
		Collections.sort(EdgeList) ;
		UnionFind U = new UnionFind(EdgeList.size()) ;
		int mst_cost = 0  ;
		for(int i=0;i<EdgeList.size();i++)
		{
			triplet t = EdgeList.get(i) ;
			if(!U.isSameSet(t.a,t.b) )
			{
				mst_cost+=t.w ;
				U.unionSet(t.a,t.b) ;
			}
		}
		return mst_cost ;
		
	}
}
class UnionFind
{
	private int[] p,rank ;
	UnionFind(int n)
	{
		p = new int[n] ;
		rank =  new int[n] ;
		for(int i=0;i<p.length;i++)
			p[i] = i ;
	}
	int  findSet(int i)
	{
		return (p[i]==i)?i:(p[i] = findSet(p[i])) ;
	}
	boolean isSameSet(int i,int j)
	{
		return (findSet(i)==findSet(j)) ;
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
class triplet implements Comparable<triplet>
{
	int a,b,w ;
	triplet(int at,int bt,int wt)
	{
		a = at ;
		b = bt ;
		w = wt ;
	}
	@Override
	public int compareTo(triplet t)
	{
		 return this.w-t.w ;
		//return (this.w-t.w)==0?(this.a-t.a):(this.w-t.w) ;//assuming a is the low
	}
	
}
