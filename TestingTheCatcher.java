import java.util.* ;
import java.io.BufferedReader ;
import java.io.InputStreamReader ;

public class TestingTheCatcher
{
	private static final boolean debug = false ;
	public static void main(String args[]) throws Exception
	{
		BufferedReader bro = new BufferedReader(new InputStreamReader(System.in)) ;
		int t = 0 ;
		for(String S = bro.readLine().trim();!S.equals("-1");S = bro.readLine().trim())
		{
			t++ ;
			String temp = "" ;
			for(String S1 = S;!S1.equals("-1");S1 = bro.readLine().trim())
			{
				temp+=S1+" " ;
			}
			String[] nos = temp.trim().split(" ") ;
			int[] arr = new int[nos.length] ;
			for(int i=0;i<arr.length;i++)
			{
				arr[i] = Integer.parseInt(nos[i]) ;
			}
			System.out.println("Test #"+t+":") ;
			System.out.println("  maximum possible interceptions: "+solve(arr)) ;
			System.out.println() ;
		}
	}
	static int solve(int[] A)
	{
		int[] LIS = new int[A.length] ;
		// LIS[0] = 1;
		Arrays.fill(LIS,1) ;
		int max = 1 ;
		for(int i=1;i<LIS.length;i++)
		{
			for(int j=0;j<i;j++)
			{
				if(A[i]<=A[j])
					LIS[i] = Math.max(LIS[i],LIS[j]+1) ;
				
			}
			max = LIS[i]>max?LIS[i]:max ;
		}
		if(debug) System.out.println(Arrays.toString(LIS)) ;
		return max ;
	}
}