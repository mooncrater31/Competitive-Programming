import java.util.* ;
import java.io.BufferedReader ;
import java.io.InputStreamReader ;

public class BeautifulSubsequence
{
	private static final boolean debug = true ;
	public static void main(String args[]) throws Exception
	{
		BufferedReader bro = new BufferedReader(new InputStreamReader(System.in)) ;
		int T = Integer.parseInt(bro.readLine()) ;
		for(int t=0;t<T;t++)
		{
			int N = Integer.parseInt(bro.readLine()) ;
			int[] A = new int[N] ;
			String[] S = bro.readLine().split(" ") ;
			for(int i=0;i<N;i++)
			{
				A[i] = Integer.parseInt(S[i]) ;
			}
			System.out.println(solve(A)) ;
		}
	}
	static int solve(int[] A)
	{
		Arrays.sort(A) ;
		List<Cont> L = new ArrayList<Cont>() ;
		L.add(new Cont(A[0],A[0])) ;
		boolean wasAdded = false ;
		for(int i=1;i<A.length;i++)
		{
			for(int j=0;j<L.size();j++)
			{
				double temp = (Math.pow(A[i],L.get(j).H.size())/L.get(j).product) ;
				if(temp%1==0)//temp is an integer
				{
					if(debug) System.out.println("This was executed!") ;
					// L.get(j).H.add(A[j]) ;
					// L.get(j).product = L.get(j).product*(int)temp ;
					HashSet<Integer> tempH = new HashSet<Integer>(L.get(j).H) ;
					tempH.add(A[j]) ;
					
					L.add(new Cont(tempH,L.get(j).product*(int)temp)) ;
					wasAdded = true ;
				}
			}
			if(!wasAdded)
			{
				L.add(new Cont(A[i],A[i])) ;
			}
		}
		int maxSize = 0 ;
		for(int i=0;i<L.size();i++)
		{
			if(L.get(i).H.size()>maxSize)
				maxSize = L.get(i).H.size() ;
			
		}
		return maxSize ;
		
	}
}
class Cont
{
	HashSet<Integer> H = new HashSet<Integer>() ;
	long product ;
	Cont(int a,int b)
	{
		H.add(a) ;
		product = b ;
	}
	Cont(HashSet HN,int p)
	{
		H = new HashSet<Integer>(HN) ;
		product = p ;
	}
	
}