import java.util.* ;
import java.io.BufferedReader ;
import java.io.InputStreamReader ;

public class Dollars
{
    private static final boolean debug = false ;
    public static void main(String args[]) throws Exception
    {
        BufferedReader bro = new BufferedReader(new InputStreamReader(System.in)) ;
        for(String S = bro.readLine().trim();!S.equals("0.00");S = bro.readLine().trim())
        {
            int[] C = {5,10,20,50,100,200,500,1000,2000,5000,10000} ;
            float f = Float.parseFloat(S) ;
            int V = (int)(Math.round(100*f)) ;
            if(debug) System.out.println("V :"+V+"f :"+Math.round(f*100)) ;
            long ans = ways(C,V) ;
            System.out.println(String.format("%6s%17d",S,ans)) ;
        }
    }
    static long ways(int[] C,int V)
    {
        long[][] dp = new long[V+1][C.length] ;
        Arrays.fill(dp[0],1) ;
        for(int i=1;i<dp.length;i++)
        {
            for(int j=0;j<dp[0].length;j++)
            {
                long x=0,y=0 ;
                if(i-C[j]>=0) x = dp[i-C[j]][j] ;
                if(j>0) y = dp[i][j-1] ;
                dp[i][j] = x+y ;
            }
        }
        return dp[V][C.length-1] ;
    }
}