import java.io.BufferedReader ;
import java.io.InputStreamReader ;
import java.util.* ;

public class MicroAndCoins
{
    static final boolean debug = false ;
    public static void main(String args[]) throws Exception
    {
        BufferedReader bro = new BufferedReader(new InputStreamReader(System.in)) ;
        int T = Integer.parseInt(bro.readLine()) ;
        for(int t=0;t<T;t++)
        {
            String[] S = bro.readLine().split(" ") ;
            int n = Integer.parseInt(S[0]) ;
            int m = Integer.parseInt(S[1]) ;
            boolean[][] adj = new boolean[n][n]  ;
            for(int i=0;i<m;i++)
            {
                S = bro.readLine().split(" ") ;
                int a = Integer.parseInt(S[0])-1 ; 
                int b = Integer.parseInt(S[1])-1 ;
                adj[a][b]=true ;
                adj[b][a]=true ;
                
                
            }
            System.out.println(solve(adj)?"Yes":"No") ;
        }
    }
    static boolean solve(boolean[][] adj)
    {
        int n = adj.length ;
        boolean[][] dp = new boolean[n][(1<<n)] ;
        for(int i=0;i<n;i++) {dp[i][1<<i]=true ;}
        for(int i=0;i<(1<<n);i++)
        {
            for(int j=0;j<n;j++)
            {
                if((i&(1<<j))>0)
                {
                    
                    for(int k=0;k<n;k++)
                    {
                        if(debug)
                        {
                            if(i==3 && j==0 && k==1)
                            {
                                System.out.println("(i&(1<<k))==1) :"+((i&(1<<k))>0)+"  adj[k][j] :"+adj[k][j]+" dp[k][(i^(1<<j))] :"+dp[k][(i^(1<<j))]) ;
                            }
                        }
                        if(((i&(1<<k))>0) && adj[k][j] && j!=k && dp[k][(i^(1<<j))]) 
                        {
                            dp[j][i]=true ;
                            if(debug) System.out.println("  dp["+j+"]["+i+"] was made true.") ;
                            break;
                        }
                    }
                }
            }
        }
        if(debug) 
        {
            for(int i=0;i<n;i++) System.out.println(Arrays.toString(dp[i])) ;
        }
        for(int i=0;i<n;i++)
        {
            if(dp[i][(1<<n)-1]) return true ;
        }
        return false ;
    }
}
