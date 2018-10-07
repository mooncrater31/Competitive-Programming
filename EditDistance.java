import java.util.* ;
import java.io.BufferedReader ;
import java.io.InputStreamReader ;

public class EditDistance
{
    public static void main(String args[]) throws Exception
    {
        BufferedReader bro = new BufferedReader(new InputStreamReader(System.in)) ;
        int T = Integer.parseInt(bro.readLine()) ;
        for(int t=0;t<T;t++)
        {
            bro.readLine() ;
            String[] S = bro.readLine().split(" ") ;
            System.out.println(editDistance(S[0],S[1])) ;
        }
    }
    static int editDistance(String A,String B)
    {
        int[][] dp = new int[A.length+1][B.length+1] ;
        for(int i=0;i<=A.length;i++)
        {
            for(int j=0;j<=B.length;j++)
            {
                if(i==0)
                    dp[i][j] = j ;//Insert j times
                else if(j==0)
                    dp[i][j] = i ;//remove i times
                else if(A.charAt(i-1)==B.charAt(j-1))
                    dp[i][j] = dp[i-1][j-1] ;
                else 
                    dp[i][j] = 1+Math.min(dp[i][j-1],Math.min(dp[i-1][j],dp[i-1][j-1])) ;
            }
        }
        return dp[A.length][B.length] ;
        
    }
}