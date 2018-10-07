import java.util.* ;
import java.io.BufferedReader ;
import java.io.InputStreamReader ;

public class EqualZerosAndOnes2
{
	private static final boolean debug = false ;
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
	static int ways(int n)
	{
		return (n)*(n-1)/2 ;
	}
	static int solve(int[] A)
	{
		HashMap<Integer,Integer> H = new HashMap<Integer,Integer>() ;
		for(int i=0;i<A.length;i++)
			if(A[i]==0) A[i] = -1 ;
		int sum = 0 ;
		for(int i=0;i<A.length;i++) 
		{
			sum+=A[i] ;
			if(!H.containsKey(sum))
				H.put(sum,1) ;
			else H.put(sum,H.get(sum)+1) ;
		}
		int count = 0 ;
		if(debug)
		{
			for(int k:H.keySet())
			{
				System.out.println("Key :"+k+" value :"+H.get(k)) ;
			}
		}
		
		for(int v:H.values())
		{
			count+=ways(v) ;
		}
		count+=H.containsKey(0)?H.get(0):0 ;
		return count ;
	}
}
