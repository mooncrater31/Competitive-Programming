import java.util.* ;
import java.io.* ;

public class MinimizeSum
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
			for(int i=0;i<N;i++)
			{
				A[i] = Integer.parseInt(S[i]) ;
			}
			Arrays.sort(A) ;
			int sum = 0 ;
			for(int i=0;i<A.length/2;i++)
			{
				sum+=A[i]*A[A.length-i-1] ;
			}
			System.out.println(sum) ;
		}
	}
	
}