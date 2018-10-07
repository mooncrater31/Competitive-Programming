import java.util.* ;
import java.io.BufferedReader ;
import java.io.InputStreamReader ;
//UVa- 11631
public class DarkRoads 
{
	public static void main(String args[]) throws Exception
	{
		BufferedReader bro = new BufferedReader(new InputStreamReader(System.in)) ;
		for(String S = bro.readLine();;S = bro.readLine())
		{
			String[] s = S.split(" ") ;
			int n = Integer.parseInt(s[0]) ;
			int m = Integer.parseInt(s[1]) ;
			if(n==0 && m==0)
				break ;
			List<Triplet> EdgeList = new ArrayList<Triplet>() ;
			int sum= 0 ;
			for(int i=0;i<m;i++)
			{
				String[] s1 = bro.readLine().split(" ") ;
				int a = Integer.parseInt(s1[0]),b = Integer.parseInt(s1[1]),w = Integer.parseInt(s1[2]) ;
				EdgeList.add(new Triplet(a,b,w)) ;
				sum+=w ;
			}
			int ans = kruskal(EdgeList,n) ;
			System.out.println(sum-ans) ;
		}
	}
	static int kruskal(List<Triplet> EdgeList,int n)
	{
		Collections.sort(EdgeList) ;
		int mst_weight = 0 ;
		UnionFind U = new UnionFind(n) ;
		for(int i=0;i<EdgeList.size();i++)
		{
			Triplet t = EdgeList.get(i) ;
			if(!(U.findSet(t.a)==U.findSet(t.b)))
			{
				mst_weight+=t.w ;
				U.unionSet(t.a,t.b) ;
			}
		}
		return mst_weight ;
	}
}
class UnionFind
{
	private int[] rank,p ;
	UnionFind(int n)
	{
		rank = new int[n] ;
		p = new int[n] ;
		for(int i=0;i<n;i++)
		{
			p[i] = i ;
		}
	}
	boolean isSameSet(int i,int j)
	{
		return findSet(i)==findSet(j) ;
	}
	int findSet(int i)
	{
		return (p[i]==i?i:(p[i] = findSet(p[i]))) ;
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
class Triplet implements Comparable<Triplet>
{
	int a,b,w ;
	Triplet(int at,int bt,int wt)
	{
		a = at ;
		b = bt ;
		w = wt ;
	}
	@Override
	public int compareTo(Triplet t)
	{
		return this.w-t.w ;
	}
}