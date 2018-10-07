import java.util.* ;
import java.io.BufferedReader ;
import java.io.InputStreamReader ;

public class SaveGotham
{
	public static void main(String args[]) throws Exception
	{
		BufferedReader bro = new BufferedReader(new InputStreamReader(System.in)) ;
		int T = Integer.parseInt(bro.readLine()) ;
		for(int t=0;t<T;t++)
		{
			int N = Integer.parseInt(bro.readLine()) ;
			int[] A = new int[N] ;
			String[] S = bro.readLine().split(" ") ;
			for(int i=0;i<A.length;i++)
			{
				A[i] = Integer.parseInt(S[i]) ;
			}
			System.out.println(solve(A)) ;
		}
	}
	static long solve(int[] A)
	{
		int[] ans = new int[A.length] ;
		ans[A.length-1] = -1 ;
		for(int i=A.length-2;i>=0;i--)
		{
			if(A[i+1]>A[i])
				ans[i] = i+1 ;
			else
			{
				int ind = i+1 ;
				while(ans[ind]!=-1 && A[ans[ind]]<=A[i])
				{
					ind = ans[ind] ;
				}
				ans[i] = ans[ind] ;
			}
		}
		long sum = 0 ;
		for(int i=0;i<ans.length;i++)
		{
			sum+=ans[i]==-1?0:A[ans[i]] ;
		}
		return sum%1000000001 ;
	}
}