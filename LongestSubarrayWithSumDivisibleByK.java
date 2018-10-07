import java.util.* ;
import java.io.BufferedReader ;
import java.io.InputStreamReader ;

public class LongestSubarrayWithSumDivisibleByK
{
	private static final boolean debug = false;
	public static void main(String args[]) throws Exception
	{
		BufferedReader bro = new BufferedReader(new InputStreamReader(System.in)) ;
		int T = Integer.parseInt(bro.readLine()) ;
		for(int t=0;t<T;t++)
		{
			String[] S = bro.readLine().split(" ") ;
			int N = Integer.parseInt(S[0]) ;
			int k = Integer.parseInt(S[1]) ;
			int[] A = new int[N] ;
			S = bro.readLine().split(" ") ;
			for(int i=0;i<A.length;i++)
			{
				A[i] = Integer.parseInt(S[i]) ;
			}
			System.out.println(solve(A,k)) ;
		}
	}
	static void arrayPrinter(int[] A)
	{
		for(int a:A)
		{
			System.out.print(a+" ") ;
		}
		System.out.println() ;
	}
	static int solve(int[] A,int k)
	{
		int[] mod_arr = new int[A.length] ;
		int init = A[0]%k ;
		mod_arr[0] = init<0?init+k:init ;
		for(int i=1;i<A.length;i++)
		{
			int temp = (mod_arr[i-1]+A[i])%k ;
			if(temp<0)
				mod_arr[i] = temp+k ;
			else mod_arr[i] = temp ;
		}
		if(debug)
		{
			System.out.println("mod_arr :") ;
			arrayPrinter(mod_arr) ;
		}
		HashMap<Integer,Integer> H = new HashMap<Integer,Integer>() ;
		for(int i=A.length-1;i>=0;i--)
		{
			H.put(mod_arr[i],i) ;
		}
		int max_len = Integer.MIN_VALUE ;
		for(int i=0;i<mod_arr.length;i++)
		{
			if(!H.containsKey(mod_arr[i]))
				H.put(mod_arr[i],i) ;
			else
			{
				int idx = H.get(mod_arr[i]) ;
				if(mod_arr[i]==0)
				{
					max_len = max_len<i+1?i+1:max_len ;
				}
				if(i-idx>max_len)
					max_len = i-idx ;
			}
		}
		return max_len==Integer.MIN_VALUE?0:max_len ;
	}
}