import java.util.* ;
import java.io.BufferedReader ;
import java.io.InputStreamReader ;

public class MinimumPlatforms
{
	public static void main(String args[]) throws Exception
	{
		BufferedReader bro = new BufferedReader(new InputStreamReader(System.in)) ;
		int T = Integer.parseInt(bro.readLine()) ;
		for(int t=0;t<T;t++)
		{
			int N = Integer.parseInt(bro.readLine().split(" ")[0]) ;
			int[] A = new int[N] ;
			int[] B = new int[N] ;
			String[] S = bro.readLine().split(" ") ;
			for(int i=0,j=0;i<S.length;i++)
			{
				if(!S[i].equals(""))
				{
					A[j++] = Integer.parseInt(S[i]) ;
				}
			}
			S = bro.readLine().split(" ") ;
			for(int i=0;i<S.length;i++)
			{
				B[i] = Integer.parseInt(S[i]) ;
			}
			System.out.println(solve(A,B)) ;
		}
	}
	static int solve(int[] A,int[] B)
	{
		List<Pair> L = new ArrayList<Pair>() ;
		for(int i=0;i<A.length;i++)
		{
			L.add(new Pair(i,A[i])) ;
			L.add(new Pair(i,B[i])) ;
		}
		Collections.sort(L)  ;
		boolean[] checked = new boolean[A.length] ;
		// for(int i=0;A.length;i++)//For all trains
		// {
			
		// }
		int max = 1 ;
		for(int i=0;i<L.size();i++)
		{
			if(!checked[L.get(i).trainNo])
			{
				checked[L.get(i).trainNo] = true ;
				HashSet<Integer> H = new HashSet<Integer>() ;
				for(int j=i+1;j<L.size() && L.get(j).trainNo!=L.get(i).trainNo ;j++)
				{
					if(H.contains(L.get(j).trainNo))
						checked[L.get(j).trainNo] = true ;
					else H.add(L.get(j).trainNo) ;
				}
				if(max<H.size()+1) max = H.size()+1 ;
			}
		}
		return max ;
	}
}
class Pair implements Comparable<Pair>
{
	
	int trainNo,time ;
	Pair(int tr,int t)
	{
		trainNo = tr ;
		time = t ;
	}
	@Override
	public int compareTo(Pair other)
	{
		return this.time-other.time ;
	}
}