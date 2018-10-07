import java.io.BufferedReader ;
import java.io.InputStreamReader ;
import java.util.* ;
public class MicroAndPermutations
{
    static final boolean debug = true ;
    public static void main(String args[]) throws Exception
    {
        BufferedReader bro = new BufferedReader(new InputStreamReader(System.in)) ;
        String[] S = bro.readLine().split(" ") ;
        int n = Integer.parseInt(S[0]) ;
        int m = Integer.parseInt(S[1]) ;
        boolean[][] adj = new boolean[n][n] ;
        for(int i=0;i<m;i++)
        {
            S = bro.readLine().split(" ") ;
            int a = Integer.parseInt(S[0]) -1;
            int b = Integer.parseInt(S[1]) -1;
            adj[a][b]=true ;
            adj[b][a]=true ;
            
        }
        System.out.println(solve(adj)) ;
        
    }
    static int solve(boolean[][] adj)
    {
        int n = adj.length ;
        int[][] dp = new int[n][(1<<n)] ;
        for(int i=0;i<n;i++) dp[i][(1<<i)]=1 ;
        for(int i=0;i<(1<<n);i++)
        {
            for(int j=0;j<n;j++)
            {
                if((i&(1<<j))>0)
                {
                    for(int k=0;k<n;k++)
                    {
                        if((i&(1<<k))>0 && j!=k && adj[k][j] && dp[k][i^(1<<j)]>0) {dp[j][i]+=dp[k][i^(1<<j)];}
                    }
                }
            }
        }
        if(debug) 
        {
            for(int i=0;i<n;i++) System.out.println(Arrays.toString(dp[i])) ;
        }
        int count = 0 ;
        for(int i=0;i<n;i++)
            if(dp[i][(1<<n)-1]>0)count+=dp[i][(1<<n)-1];
        return count ;
        
    }
}