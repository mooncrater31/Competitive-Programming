import java.util.* ;
import java.io.BufferedReader ;
import java.io.InputStreamReader ;

public class LetMeCountTheWays
{
    private static final boolean debug = false ;
    public static void main(String args[]) throws Exception
    {
        BufferedReader bro = new BufferedReader(new InputStreamReader(System.in)) ;
        for(String S = bro.readLine();!S.equals("");S = bro.readLine())
        {
            int V = Integer.parseInt(S) ;
            int[] C = {1,5,10,25,50} ;
            long ans = ways(C,V) ;
            System.out.println("There "+(ans==1?"is only ":"are ")+ans+(ans==1?" way ":" ways ")+"to produce "+V+" cents change.") ;
        }
        
    }
    static long ways(int[] C,int V)
    {
        long[][] dp = new long[V+1][C.length] ;
        Arrays.fill(dp[0],1) ;
        for(int i=1;i<dp.length;i++)//Value
        {
            for(int j=0;j<dp[0].length;j++)//Coins
            {
                long x = 0 , y = 0 ;
                if(i-C[j]>=0) x = dp[i-C[j]][j] ;
                if(j>0) y = dp[i][j-1] ;
                dp[i][j] = x+y ;
            }
        }
        return dp[V][C.length-1] ;
    }
}