import java.util.* ;
import java.io.BufferedReader ;
import java.io.InputStreamReader ;

public class MrModuloAndPairs
{
	private static final boolean debug = true ;
	public static void main(String args[]) throws Exception
	{
		BufferedReader bro = new BufferedReader(new InputStreamReader(System.in)) ;
		int T = Integer.parseInt(bro.readLine()) ;
		for(int t=0;t<T;t++)
		{
			String[] S = bro.readLine().split(" ") ;
			int N = Integer.parseInt(S[0]) ;
			int k = Integer.parseInt(S[1]) ;
			S = bro.readLine().split(" ") ;
			HashSet<Integer> H = new HashSet<Integer>() ;
			for(int i=0;i<N;i++)
			{
				H.add(Integer.parseInt(S[i])) ;
			}
			System.out.println(solve(H,k)) ;
		}
	}
	static List<Integer> possibilities(int O,int R)
	{
		int root = (int)Math.sqrt(O-R)+1 ; 
		List<Integer> divisors = new ArrayList<Integer>() ;
		for(int i=1;i<root;i++)
		{
			if(O%i==R)
			{
				divisors.add(i) ;
			}
			if(O%((O-R)/i)==R)
			{
				divisors.add((O-R)/i) ;
			}
		}
		return divisors ;
	}
	static void listPrinter(List<Integer> L)
	{
		for(int i=0;i<L.size();i++)
		{
			System.out.print(L.get(i)+" ") ;
		}
		System.out.println() ;
	}
	static int solve(HashSet<Integer> H,int k)
	{
		int count = 0 ;
		Iterator<Integer> itr = H.iterator() ;
		while(itr.hasNext())
		{
			Integer val = itr.next() ;
			List<Integer> divisors = possibilities(val,k) ;//This might raise an error.
			if(debug)
			{
				System.out.println("val = "+val) ;
				System.out.println("DIVISORS:") ;
				listPrinter(divisors) ;
			}
			for(int i=0;i<divisors.size();i++)
			{
				if(H.contains(divisors.get(i)))
				{
					count++ ;
					if(H.contains(val/divisors.get(i)))
						H.remove(val/divisors.get(i)) ;
				}					
			}
		}
		return count ;
	}
}