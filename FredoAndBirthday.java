import java.io.BufferedReader ;
import java.io.InputStreamReader ;
import java.util.* ;

public class FredoAndBirthday
{
    private static final boolean debug = true ;
    public static void main(String args[]) throws Exception
    {
        BufferedReader bro = new BufferedReader(new InputStreamReader(System.in)) ;
        int T = Integer.parseInt(bro.readLine()) ;
        for(int t=0;t<T;t++)
        {
            String[] S = bro.readLine().split(" ") ;
            int n = Integer.parseInt(S[0]) ;
            int m = Integer.parseInt(S[1]) ;
            boolean[][] adj = new boolean[n][n] ;
            for(int i=0;i<m;i++)
            {
                S = bro.readLine().split(" ") ;
                int a = Integer.parseInt(S[0])-1 ;
                int b = Integer.parseInt(S[1])-1 ;
                adj[a][b]=true ;
                adj[b][a]=true ;
            }
            int[] ans = solve(adj) ;
            for(int i=0;i<n;i++) System.out.print(ans[i]+" ") ;
            System.out.println() ;
        }
        
    }
    static int noOfBits(int n)
    {
        int count=0 ;
        while(n!=0)
        {
            n&=(n-1) ;
            count++ ;
        }
        return count ;
    }
    static int[] solve(boolean[][] adj)
    {
        int n = adj.length ;
        int[] ans = new int[n] ;
        boolean[][] dp = new boolean[n][1<<n] ;
        for(int i=0;i<n;i++) dp[i][1<<i]=true ;
        for(int i=0;i<(1<<n);i++)
        {
            for(int j=0;j<n;j++)
            {
                if((i&(1<<j))>0)
                {
                    for(int k=0;k<n;k++)
                    {
                        if(((i&(1<<k))>0) && k!=j && adj[k][j] && dp[k][i^(1<<j)])
                        {
                            dp[j][i]=true ;
                            ans[j]=Math.max(ans[j],noOfBits(i)-1) ;
                        }
                    }
                }
            }
        }
            if(debug) 
        {
            for(int i=0;i<n;i++) System.out.println(Arrays.toString(dp[i])) ;
        }
        return ans ;
    }
}
