import java.util.* ;
import java.io.BufferedReader ;
import java.io.InputStreamReader ;

public class CoinChange
{
    public static void main(String args[]) throws Exception
    {
        BufferedReader bro = new BufferedReader(new InputStreamReader(System.in)) ;
        for(String S = bro.readLine();!S.equals("");S = bro.readLine())
        {
            int N = Integer.parseInt(S) ;
            System.out.println(solve(N)) ;
        }
    }
    static int solve(int n)
    {
        int[] C = {1,5,10,25,50} ;
        int[][] dp = new int[5][n+1] ;
        // Arrays.fill(dp[0],1) ;
        for(int i=0;i<5;i++)
        {
            dp[i][0] = 1 ;
        }
        for(int sum=1;sum<n+1;sum++)
        {
            for(int c=0;c<5;c++)
            {
                if(sum-C[c]>=0) dp[c][sum] += dp[c][sum-C[c]] ;
                if(c>0) dp[c][sum] += dp[c-1][sum] ;
                
            }
        }
        return dp[4][n] ;
    }
}