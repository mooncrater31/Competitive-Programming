import java.util.* ;
import java.io.BufferedReader ;
import java.io.InputStreamReader ;

public class MinimumCOins
{
    public static void main(String args[]) throws Exception
    {
        BufferedReader bro = new BufferedReader(new InputStreamReader(System.in)) ;
            
    }
    static int coins(int[] W,int[] C,int V)
    {
        if(V==0) return 0 ;
        else if(V<0) return Integer.MAX_VALUE ;
        if(W[V]==-1)
        {
                for(int i=0;i<C.length;i++)
                {
                    int temp = coins(W,C,V-C[i]) ;
                    W[V] = W[V]>1+temp?1+temp:W[V] ;
                }
            return W[V] ;
        }
        else return W[V] ;
    }
    static int coins(int V,int[] C)
    {
        int[] dp = new int[V+1] ;
        dp[0] = 0 ;
        for(int i=1;i<=V;i++)
        {
            for(int j=0;j<C.length;j++)
            {
                if(i-C[j]>=0)
                {
                    dp[i] = Math.min(dp[i],1+dp[i-C[j]]) ;
                }
            }
        }
        return dp ;
    }
}