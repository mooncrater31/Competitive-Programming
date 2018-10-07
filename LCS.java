import java.util.* ;
import java.io.BufferedReader ;
import java.io.InputStreamReader ;

public class LCS
{
    public static void main(String args[]) throws Exception
    {
        BufferedReader bro = new BufferedReader(new InputStreamReader(System.in)) ;
        int T = Integer.parseInt(bro.readLine()) ;
        for(int t=0;t<T;t++)
        {
            bro.readLine() ;
            String A = bro.readLine() ;
            String B = bro.readLine() ;
            System.out.println(lcs(A,B)) ;
            
        }
    }
    static int lcs(String A,String B)
    {
        int[][] dp = new int[A.length()+1][B.length()+1] ;
        for(int i=1;i<=A.length();i++)
        {
            for(int j=1;j<=B.length();j++)
            {
                if(A.charAt(i-1)==B.charAt(j-1))
                    dp[i][j] = 1+dp[i-1][j-1] ;
                else 
                    dp[i][j] = Math.max(dp[i-1][j],dp[i][j-1]) ;
            }
        }
        return dp[A.length()][B.length()] ;
    }
}
