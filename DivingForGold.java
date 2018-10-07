import java.util.* ;
import java.io.BufferedReader;
import java.io.InputStreamReader ;

public class DivingForGold
{
    public static void main(String args[]) throws Exception
    {
        
    }
    static List<Integer> reconstruct(int[][] dp)
    {
        int x=dp.length-1,y = dp[0].length-1 ;
        
    }
    static Obj solve(int t,int w,int[] depth,int[] gold)
    {
        int[] timeToDive = new int[depth.length] ;
        int[][] dp = new int[gold.depth][t+1] ;
        for(int i=0;i<depth.length;i++)
        {
            timeToDive[i] = 3*depth[i] ;
        }
        for(int i=0;i<dp[0].length;i++)
        {
            if(i>=timeToDive[0])
                dp[0][i] = gold[0] ;
        }
        for(int i=1;i<dp.length;i++)
        {
            for(int j=0;j<dp[i].length;j++)
            {
                dp[i][j] = Math.max(dp[i-1][j],gold[i]+dp[i][j-timeToDive[j]]) ;
            }
        }
        List<Integer> L = reconstruct(dp) ;
        Collections.reverse(L) ;
        System.out.println(dp[gold.depth-1][t]) ;
        System.out.println(L.size()) ;
        for(int i=0;i<L.size();i++)
        {
            System.out.println(depth[L.get(i)]+" "+gold[L.get(i)]) ;
        }
    }
}