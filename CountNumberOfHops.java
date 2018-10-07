import java.util.* ;
import java.io.BufferedReader ;
import java.io.InputStreamReader ;

public class CountNumberOfHops
{
    public static void main(String args[]) throws Exception
    {
        BufferedReader bro = new BufferedReader(new InputStreamReader(System.in)) ;
        int T = Integer.parseInt(bro.readLine()) ;
        for(int t=0;t<T;t++)
        {
            int N = Integer.parseInt(bro.readLine()) ;
            System.out.println(hops(N)) ;
        }
    }
    static int hops(int n)
    {
        int[] C = {1,2,3} ;
        if(n==0)
            return 1 ;
        else if(n==1)
            return 1 ;
        else if(n==2)
            return 2 ;
        else
        {
            int[] dp = new int[n+1] ;
            dp[0] = 1 ;
            dp[1] = 1 ;
            dp[2] = 2 ;
            for(int i=3;i<=n;i++)
            {
                dp[i] = dp[i-1]+dp[i-2]+dp[i-3] ;
            }
            return dp[n] ;
        }
        return  0 ;
        
        
        
        
        return dp[n][C.length-1] ;
    }
}