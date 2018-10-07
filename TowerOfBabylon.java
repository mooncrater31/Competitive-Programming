import java.util.* ;
import java.io.BufferedReader ;
import java.io.InputStreamReader ;

public class TowerOfBabylon
{
	private static final boolean debug = false ;
	public static void main(String args[]) throws Exception
	{
		BufferedReader bro = new BufferedReader(new InputStreamReader(System.in)) ;
		int tests = 0 ;
		for(String S = bro.readLine().trim();!S.equals("0");S = bro.readLine().trim())
		{
			tests++ ;
			int N = Integer.parseInt(S) ;
			List<Box> L = new ArrayList<Box>() ;
			for(int i=0;i<N;i++)
			{
				String[] temp = bro.readLine().trim().split(" +") ;
				int b = Integer.parseInt(temp[0]) ;
				int w = Integer.parseInt(temp[1]) ;
				int h = Integer.parseInt(temp[2]) ;
				L.add(new Box(b,w,h)) ;
			}
			System.out.println("Case "+tests+": maximum height = "+solve(L)) ;
		}
	}
	static int solve(List<Box> L)
	{
		int len = L.size() ;
		for(int i=0;i<len;i++)
		{
			Box B = L.get(i) ;
			L.add(new Box(B.b,B.h,B.w)) ;
			L.add(new Box(B.w,B.b,B.h)) ;
			L.add(new Box(B.h,B.b,B.w)) ;
			L.add(new Box(B.h,B.w,B.b)) ;
			L.add(new Box(B.w,B.h,B.b)) ;
		}
		Collections.sort(L) ;
		if(debug) boxListPrinter(L) ;
		return LIS(L) ;
		
	}
	static int LIS(List<Box> L)
	{
		int[] lis = new int[L.size()] ;
		for(int i=0;i<lis.length;i++)
		{
			lis[i] = L.get(i).h ;
		}
		// lis[0] = L.get(0).h ;
		int max = L.get(0).h ;
		for(int i=1;i<L.size();i++)
		{
			for(int j=0;j<i;j++)
			{
				// if(L.get(i).compareTo(L.get(j))==1)  
				if(L.get(i).w>L.get(j).w && L.get(i).b>L.get(j).b)
					lis[i] = Math.max(lis[i],lis[j]+L.get(i).h) ;
			}
			max = max<lis[i]?lis[i]:max ;
		}
		if(debug) System.out.println(Arrays.toString(lis)) ;
		return max ;
	}
	static void boxListPrinter(List<Box> L)
	{
		System.out.println("--------------------") ;
		for(Box B:L)
		{
			System.out.println(B.b+" "+B.w+" "+B.h) ;
		}
		System.out.println("--------------------") ;
	}
}
class Box implements Comparable<Box>
{
	int b,w,h ;
	Box(int b,int w,int h)
	{
		this.b = b ;
		this.w = w ;
		this.h = h ;
	}
	public int compareTo(Box B)
	{
		if(this.b==B.b)
		{
			return this.w>B.w?1:-1 ;
		}
		else return this.b>B.b?1:-1 ;
	}
}
