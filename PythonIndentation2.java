import java.util.* ;
import java.io.* ;

public class PythonIndentation2
{
	public static void main(String args[])
	{
		// Scanner in = new Scanner(System.in) ;
		// int n = in.nextInt() ;
		
		// for(int i=0;i<n;i++)
		// {
			// lst[i] = (in.next().equals("s"))?false:true ;
		// }
		// long startTime = System.nanoTime() ;
		// System.out.println(dp(lst)) ;
		// long endTime = System.nanoTime() ;
		// System.out.println("Time taken :"+(endTime-startTime)+" ns.") ;
		// startTime = System.nanoTime() ;
		// System.out.println(dp2(lst)) ;
		// endTime = System.nanoTime() ;
		// System.out.println("Time taken :"+(endTime-startTime)+" ns.") ;
		Random r = new Random() ;
		// int length = 3662 ;
		int max = 10000 ;
		
		long[] runTime1 = new long[max] ;
		long[] runTime2 = new long[max] ;
		
		for(int i=0;i<max;i++)
		{
			int length = 1000 ;
			boolean[] lst = new boolean[length] ;
			for(int j=0;j<length;j++)
			{
				lst[j] = r.nextBoolean() ;
			}
			long startTime = System.nanoTime() ;
			dp(lst) ;
			long p2 = System.nanoTime() ;
			dp2(lst) ;
			long p3 = System.nanoTime() ;
			runTime1[i] = p2-startTime ;
			runTime2[i] = p3-p2 ;
		}
		long sum1 = 0,sum2 = 0;
		for(int i=0;i<max;i++)
		{
			sum1 = sum1 + runTime1[i] ;
			sum2 = sum2 + runTime2[i] ;
			
		}
		System.out.println("1:: "+(sum1/(max*1000000.0))+"ms and 2:: "+(sum2/(max*1000000.0))+" ms") ;
	}
	static int dp2(boolean[] lst)
	{//false in lst means an "s" (simple statement), and true a "f"(for loop)
		int[][] dp = new int[2][lst.length] ;
		dp[0][0] = 1 ;
		for(int i=1;i<lst.length;i++)
		{
			
			for(int j=0;j<lst.length;j++)
			{
				if(lst[i-1])//(i-1)st statement is a for loop 
				{
					if(j==0)
						dp[i%2][j] = 0 ;
					else
						dp[i%2][j] = dp[(i-1)%2][j-1] ;
				}
			
				else//i-1 st statement is a simple statement
				{
					if(j==0)
					{
						int temp = 0 ;
						for(int k=0;k<lst.length;k++)
							temp = (temp+dp[(i-1)%2][k])%1000000007 ;
						dp[i%2][j] = temp ;
					}
					else
						dp[i%2][j] = (dp[i%2][j-1]-dp[(i-1)%2][j-1])%1000000007 ;
				}
			}
		}
		int ans = 0 ;
		for(int i=0;i<lst.length;i++)
		{
			ans = (ans + dp[(lst.length-1)%2][i])%1000000007 ;
		}
		if(ans<0)
			ans = ans + 1000000007 ;
		
		return ans ;
	}
	static long dp(boolean[] lst)
	{//false in lst means an "s" (simple statement), and true a "f"(for loop)
		long[][] dp = new long[lst.length][lst.length] ;
		dp[0][0] = 1 ;
		for(int i=1;i<lst.length;i++)
		{
			
			for(int j=0;j<lst.length;j++)
			{
				if(lst[i-1])//(i-1)st statement is a for loop 
				{
					if(j==0)
						dp[i][j] = 0 ;
					else
						dp[i][j] = dp[i-1][j-1] ;
				}
			
				else//i-1 st statement is a simple statement
				{
					if(j==0)
					{
						for(int k=0;k<lst.length;k++)
							dp[i][j] = (dp[i][j]+dp[i-1][k])%1000000007 ;
					}
					else
						dp[i][j] = (dp[i][j-1]-dp[i-1][j-1])%1000000007 ;
				}
			}
		}
		long ans = 0 ;
		for(int i=0;i<lst.length;i++)
		{
			ans = (ans + dp[lst.length-1][i])%1000000007 ;
		}
		if(ans<0)
			ans = ans + 1000000007 ;
		return ans ;
	}
}