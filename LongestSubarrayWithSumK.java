import java.util.* ;
import java.io.BufferedReader ;
import java.io.InputStreamReader ;

public class LongestSubarrayWithSumK
{
	private static final boolean debug = false ;
	public static void main(String args[]) throws Exception
	{
		BufferedReader bro = new BufferedReader(new InputStreamReader(System.in)) ;
		int T = Integer.parseInt(bro.readLine()) ;
		for(int t=0;t<T;t++)
		{
			String[] S = bro.readLine().split(" ") ;
			int N = Integer.parseInt(S[0]) ;
			int k = Integer.parseInt(S[1]) ;
			// if(debug) System.out.println("k :"+k) ;
			int[] A = new int[N] ;
			S = bro.readLine().split(" ") ;
			for(int i=0;i<N;i++)//This might raise problems, due to negative numbers
			{
				A[i] = Integer.parseInt(S[i]) ;
			}
			
			int ans1 = solve(A,k) ;
			Collections.reverse(Arrays.asList(A)) ;
			if(debug) arrayPrinter(A) ;
			int ans2 = solve(A,k) ;
			System.out.println(Math.max(ans1,ans2)) ;
		}
	}
	static void arrayPrinter(int[] A)
	{
		for(int i=0;i<A.length;i++)
		{
			System.out.print(A[i]+" ") ;
		}
		System.out.println() ;
	}
	static int solve(int[] A,int k)
	{
		HashMap<Integer,Integer> H = new HashMap<Integer,Integer>() ;
		int sum = 0,maxLen = 0  ;
		for(int i=0;i<A.length;i++)
		{
			sum +=A[i] ;
			if(sum==k) maxLen = i+1 ;
			if(!H.containsKey(sum)) H.put(sum,i) ;
			if(H.containsKey(sum-k) && maxLen<(i-H.get(sum-k))) 
			{
				maxLen = i-H.get(sum-k) ;
			}
		}
		return maxLen ;
	}
}
	