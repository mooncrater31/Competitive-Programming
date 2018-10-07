import java.util.* ;
import java.io.BufferedReader ;
import java.io.InputStreamReader ;
//Code for input = 0
public class PayThePrice
{
    public static void main(String args[]) throws Exception
    {
        BufferedReader bro = new BufferedReader(new InputStreamReader(System.in)) ;
        for(String S = bro.readLine();!S.equals("");S = bro.readLine())
        {
            String[] S1 = S.split(" ") ;
            int V = Integer.parseInt(S1[0]) ;
            if(V==0)
                System.out.println("1") ;
            else if(S1.length==1)
            {
                System.out.println(solve(V)) ;
            }
            else if(S1.length==2)
            {
                int totalCoins = Integer.parseInt(S1[1]) ;
                System.out.println(solve(V,totalCoins)) ;
            }
            else if(S1.length==3)
            {
                int totalCoins = Integer.parseInt(S1[2]) ;
                int minCoins = Integer.parseInt(S1[1]) ;
                System.out.println(solve(V,minCoins,totalCoins)) ;
            }
        }
    }
    static long solve(int V,int totalCoins)
    {
        totalCoins = totalCoins<V?totalCoins:V ;
        long[][][] dp = new long[V][V+1][totalCoins+1] ;//Coins,Value,totalCoins allowed
        for(int alCoins = 0 ;alCoins<totalCoins+1;alCoins++)
        {
            for(int i=0;i<dp.length;i++)
            {
                dp[i][0][alCoins] = 1 ;
            }
            if(alCoins>0)
            {
                for(int sum=1;sum<V+1;sum++)
                {
                    for(int c=0;c<V;c++)
                    {
                        if (sum-c-1>=0 && alCoins>=1) dp[c][sum][alCoins] += dp[c][sum-c-1][alCoins-1] ;
                        if(c>=1) dp[c][sum][alCoins] += dp[c-1][sum][alCoins] ;
                    }
                }
            }
        }
        return dp[V-1][V][totalCoins] ;
    }
    static long solve(int V,int minCoins,int totalCoins)
    {
        totalCoins = totalCoins<V?totalCoins:V ;
        if(minCoins>V) return 0 ;
        long[][][] dp = new long[V][V+1][totalCoins+1] ;//Coins,Value,totalCoins allowed
        for(int alCoins = 0 ;alCoins<totalCoins+1;alCoins++)
        {
            for(int i=0;i<dp.length;i++)
            {
                dp[i][0][alCoins] = 1 ;
            }
            if(alCoins>0)
            {
                for(int sum=1;sum<V+1;sum++)
                {
                    for(int c=0;c<V;c++)
                    {
                        if (sum-c-1>=0 && alCoins>=1) dp[c][sum][alCoins] += dp[c][sum-c-1][alCoins-1] ;
                        if(c>=1) dp[c][sum][alCoins] += dp[c-1][sum][alCoins] ;
                    }
                }
            }
        }
        return dp[V-1][V][totalCoins]-dp[V-1][V][minCoins-1] ;
    }
    static long solve(int V)
    {
        
        long[][] dp = new long[V][V+1] ;
        for(int i=0;i<dp.length;i++)
        {
            dp[i][0] = 1 ;
        }
        for(int sum=1;sum<V+1;sum++)
        {
            for(int c=0;c<V;c++)
            {
                    if (sum-c-1>=0) dp[c][sum] += dp[c][sum-c-1] ;
                    if(c>=1) dp[c][sum] += dp[c-1][sum] ;
            }
        }
        return dp[V-1][V] ;
    }
    
}