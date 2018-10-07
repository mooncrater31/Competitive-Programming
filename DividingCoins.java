import java.util.* ;
import java.io.BufferedReader ;
import java.io.InputStreamReader ;

public class DividingCoins
{
    private static final boolean debug = false ;
    // public static void main(String args[]) throws Exception
    // {
        // BufferedReader bro = new BufferedReader(new InputStreamReader(System.in)) ;
        // int T = Integer.parseInt(bro.readLine()) ;
        // for(int t=0;t<T;t++)
        // {
            // int N = Integer.parseInt(bro.readLine()) ;
            // int[] C = new int[N] ;
            // String[] S = bro.readLine().trim().split(" +") ;
            // for(int i=0;i<N;i++)
            // {
                // C[i] = Integer.parseInt(S[i]) ;
            // }
            // System.out.println(solve(C)) ;
        // }
    // }
    public static void main(String args[]) throws Exception
    {
        Scanner in = new Scanner(System.in) ;
        int T = in.nextInt() ;
        for(int t=0;t<T;t++)
        {
            int N = in.nextInt() ;
            int[] A = new int[N] ;
            for(int i=0;i<N;i++)
            {
                A[i] = in.nextInt() ;
            }
            System.out.println(solve(A)) ;
        }
    }
    static int solve(int[] c)
    {
        int[] dp = new int[c.length] ;
        int sum = 0 ;
        for(int el:c)
            sum+=el ;
        dp[0] = c[0]<=sum/2?c[0]:0 ;
        int max = dp[0] ;
        for(int i=1;i<c.length;i++)
        {
            dp[i] = c[i]<=sum/2?c[i]:0 ;
            for(int j=0;j<i;j++)
            {
                int temp = dp[j]+c[i] ;
                dp[i] = (temp<=sum/2) && temp>dp[i]?temp:dp[i] ;
                max = Math.max(max,dp[i]) ;
            }
        }
        if(debug) 
        {
            System.out.println("sum :"+sum) ;
            System.out.println("dp :"+Arrays.toString(dp)) ;
        }
        
        return sum-2*max ;
    }
}
