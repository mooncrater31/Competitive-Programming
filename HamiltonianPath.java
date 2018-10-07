import java.util.* ;
import java.io.BufferedReader ;
import java.io.InputStreamReader ;

public class HamiltonianPath
{
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
            int a = Integer.parseInt(S[0]) ;
            int b = Integer.parseInt(S[1]) ;
            adj[a][b]=true ;
            adj[b][a]=true ;
            
        }
        System.out.println(heldKarp(adj)?"YES":"NO") ;
    }
    static boolean heldKarp(boolean[][] adj)
    {
        // int n = L.size() ;
        int n = adj.length ;
        boolean[][] dp = new boolean[n][1<<n] ;
        for(int i=0;i<n;i++) dp[i][1<<i] = true ;
        for(int i=0;i<(1<<n);i++)
        {
            for(int j=0;j<n;j++)
            {
                if((i&(1<<j))>0)
                {
                    for(int k=0;k<n;k++)
                    {
                        if(j!=k && (i&(1<<k))>0 && adj[k][j])
                            if(dp[k][i^(1<<j)])
                            {
                                dp[j][i]=true ;
                                break ;
                            }
                    }
                }
            }
        }
        for(int i=0;i<n;i++)
            if(dp[i][(1<<n)-1])
                return true ;
        return false ;
    }
}