import java.io.BufferedReader ;
import java.io.InputStreamReader ;

public class SimranStairs
{
    public static void main(String args[]) throws Exception
    {
        BufferedReader bro = new BufferedReader(new InputStreamReader(System.in)) ;
        int T = Integer.parseInt(bro.readLine()) ;
        dp = new int[T+1] ;
        System.out.println(rec(T)) ;
        
    }
    static int[] dp ;
    static int  rec(int N)
    {
        
        
        if(N==0)
            return 1 ;
        else if(N<0) return 0 ;
        else if(dp[N]!=0)
            return dp[N] ;
        else 
            return dp[N]=rec(N-1)+rec(N-2)+rec(N-3) ;
    }
}