import java.util.* ;
import java.io.BufferedReader ;
import java.io.InputStreamReader ;
import java.math.BigDecimal ;
import java.math.RoundingMode ;
//UVa 10034-Freckles :: Mooncrater
public class Freckles
{
	public static void main(String args[]) throws Exception
	{
		BufferedReader bro = new BufferedReader(new InputStreamReader(System.in)) ;
		int T = Integer.parseInt(bro.readLine()) ;
		bro.readLine() ;
		for(int t=0;t<T;t++)
		{
			int n = Integer.parseInt(bro.readLine()) ;
			double[][] freckles = new double[n][2] ;
			for(int i=0;i<n;i++)
			{
				String[] s = bro.readLine().split(" ") ;
				freckles[i][0] = Double.parseDouble(s[0]) ;
				freckles[i][1] = Double.parseDouble(s[1]) ;
			}
			List<Triplet> EdgeList = new ArrayList<Triplet>() ;
			for(int i=0;i<n;i++)
			{
				for(int j=i+1;j<n;j++)
				{
					EdgeList.add(new Triplet(i,j,distance(freckles[i][0],freckles[i][1],freckles[j][0],freckles[j][1]))) ;
					
				}
			}
			// System.out.printf("%.2f\n\n",kruskal(EdgeList,n)) ;	
			System.out.printf("%.2f\n\n",round(kruskal(EdgeList,n),2)) ;
			// System.out.println() ;
			bro.readLine() ;
			
		}
		
		
	}
	public static double round(double value, int places) 
	{
		if (places < 0) throw new IllegalArgumentException();

		BigDecimal bd = new BigDecimal(value);
		bd = bd.setScale(places, RoundingMode.HALF_UP);
		return bd.doubleValue();
	}
	static double distance(double a,double b,double c,double d)
	{
		return (double)(Math.sqrt((a-c)*(a-c)+(b-d)*(b-d))) ;
	}
	static double kruskal(List<Triplet> EdgeList,int n)
	{
		Collections.sort(EdgeList) ;
		double mst_weight = 0 ;
		UnionFind U = new UnionFind(n) ;
		int found = 0 ;
		for(int i=0;i<EdgeList.size();i++)
		{
			Triplet t = EdgeList.get(i) ;
			
			if(!(U.findSet(t.a)==U.findSet(t.b)))
			{
				mst_weight+=t.w ;
				U.unionSet(t.a,t.b) ;
				found++ ;
				if(found==n-1)
					break ;
			}
		}
		return mst_weight ;
		
		
	}
	
}
class UnionFind
{
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
	boolean isSameSet(int i,int j)
	{
		return findSet(i)==findSet(j) ;
	}
	int findSet(int i)
	{
		return ((p[i]==i)?i:(p[i] = findSet(p[i]))) ;
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
			{
				rank[y]++ ;
			}
		}
		
	}
}
class Triplet implements Comparable<Triplet>
{
	int a,b ;
	double w ;
	Triplet(int at,int bt,double wt)
	{
		a = at ;
		b = bt ;
		w = wt ;
	}
	@Override
	public int compareTo(Triplet t)
	{
		if(this.w>t.w)
			return 1 ;
		else if(this.w==t.w)
			return 0 ;
		else 
			return -1 ;
		
	}
}