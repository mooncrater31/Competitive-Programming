import java.util.* ;
import java.io.BufferedReader ;
import java.io.InputStreamReader ;

public class BeautifulSubsequence2
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
	static int solve(int[] A)
	{
		int[] B = new int[A.length] ;
		Arrays.sort(A) ;
		Arrays.fill(B,1) ;
		
		for(int i=1;i<B.length;i++)
		{
			for(int j=0;j<i;j++)
			{
				if(A[j]%A[i]==0 || A[i]%A[j]==0)
				{
					B[i] = Math.max(B[j]+1,B[i]) ;
				}
			}
		}
		if(debug) System.out.println(Arrays.toString(B)) ;
		int max=0;
		for(int x:B)
			if(x>max)
				max = x;
			
		return max==1?-1:max;	
		
	}
}