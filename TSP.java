import java.io.BufferedReader ;
import java.io.InputStreamReader ;

public class TSP
{
    static int[][] dp,dist ;
    // static int n ;
    public static void main(String args[]) throws Exception
    {
        BufferedReader bro = new BufferedReader(new InputStreamReader(System.in)) ;
        String[] S = bro.readLine().split(" ") ;
        int n = Integer.parseInt(S[0]) ;
        int m = Integer.parseInt(S[1]) ;
        dist = new int[n][n] ;
        for(int i=0;i<m;i++)
        {
            S = bro.readLine().split(" ") ;
            int a = Integer.parseInt(S[0])-1 ;
            int b = Integer.parseInt(S[1])-1 ;
            int c = Integer.parseInt(S[2]) ;
            dist[a][b]=c ;
            dist[b][a]=c ;
        }
        dp = new int[1<<n][n] ;
        for(int i=0;i<(1<<n);i++)
        {
            for(int j=0;j<n;j++)
            {
                dp[i][j]=-1 ;
            }
        }
        System.out.println(tsp(1,0,n)) ;
    }
    static int tsp(int mask,int pos,int n)
    {
        if(mask==(1<<n)-1) return dist[pos][0] ;
        if(dp[mask][pos]!=-1) return dp[mask][pos] ;
        int ans = Integer.MAX_VALUE ;
        for(int i=0;i<n;i++)
        {
            if((mask&(1<<i))==0 && dist[pos][i]!=0) 
            {
                int newAns = dist[pos][i]+tsp(mask|(1<<i),i,n) ;
                ans = ans<newAns?ans:newAns ;
            }
        }
        return dp[mask][pos]=ans ;
    }
}