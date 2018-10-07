import java.util.* ;
import java.io.BufferedReader ;
import java.io.InputStreamReader ;

public class HistoryGrading
{
	public static void main(String args[]) throws Exception
	{
		BufferedReader bro = new BufferedReader(new InputStreamReader(System.in)) ;
		for(String S = bro.readLine() ; !S.equals("");S = bro.readLine())
		{
			int N = Integer.parseInt(S) ;
			
			int[] seq = new int[N+1] ;
			String[] S1 = bro.readLine().split(" ") ;
			for(int i=0;i<S1.length;i++)
			{
				seq[Integer.parseInt(S1[i])] = i+1 ;
			}
		}
	}
	static int LIS(int[] A)
	{
		int[] L = new int[A.length] ;
		L[0] = 1 ;
		int max = Integer.MIN_VALUE ;
		for(int i=1;i<L.length;i++)
		{
			for(int j=0;j<i;j++)
			{
				if(A[j]<=A[i])
				{
					L[i] = Math.max(L[i],1+L[j]) ;
				}
			}
			if(L[i]>max)
				max = L[i] ;
		}
		return max ;
	}
}