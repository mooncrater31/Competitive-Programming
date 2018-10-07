import java.util.* ;
import java.io.BufferedReader ;
import java.io.InputStreamReader ;

public class MinimumSumPartitioning
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
            System.out.println(minDiff(A)) ;
        }
        
    }
    static int minDiff(int[] A)
    {
        int sum = 0 ;
        for(int el:A)
            sum+=el ;
        boolean[][] dp = new boolean[A.length+1][sum+1] ;
        for(int i=0;i<=A.length;i++)
        {
            dp[i][0] = true ;
        }
        // for(int i=1;i<=sum;i++)
        // {
            // dp[0][i] = false ;
        // }
        for(int i=1;i<=A.length;i++)
        {
            for(int j=1;j<=sum;j++)
            {
                dp[i][j] = dp[i-1][j] ;//Not taking A[i]
                if(A[i]<=j)
                {
                    dp[i][j] |= dp[i-1][j-A[i]] ;//Taking A[i] 
                }
            }
        }
        int diff = Integer.MAX_VALUE ;
        int n = A.length ;
        for(int i=sum/2;i>=0;i--)
        {
            if(dp[n][i])
            {
                diff = sum-j*2 ;
                break ;
            }
        }
        return diff ;
    }
}