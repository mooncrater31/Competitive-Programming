import java.util.* ;
import java.io.BufferedReader ;
import java.io.InputStreamReader ;
class Pair
{
    int cost,profit;
    Pair(int cost,int profit)
    {
        this.cost = cost ;
        this.profit = profit ;
    }
}
public class RoyAndFlowerFarm
{
    private static final boolean debug = false ;
    
    public static void main(String args[]) throws Exception
    {
        BufferedReader bro = new BufferedReader(new InputStreamReader(System.in)) ;
        int T= Integer.parseInt(bro.readLine()) ;
        for(int t=0;t<T;t++)
        {
            String[] S =  bro.readLine().split(" ") ;
            int N = Integer.parseInt(S[0]) ;
            int P = Integer.parseInt(S[1]) ;
            int[] C = new int[N] ;
            int[] SP = new int[N] ;
            for(int i=0;i<N;i++)
            {
                S = bro.readLine().split(" ") ;
                C[i] = Integer.parseInt(S[1]) ;
                SP[i] = Integer.parseInt(S[0]) ;
            }
            Pair p = solve(C,SP,P) ;
            System.out.println(p.cost+" "+(P+p.profit)) ;
        }
        
    }
    static Pair solve(int[] C,int[] S,int P)
    {
        int n = C.length ;
        int[] profit = new int[C.length] ;
        for(int i=0;i<C.length;i++)
        {
            profit[i] = S[i]-C[i] ;
        }
        int[][] dp = new int[C.length][P+1] ;
        for(int j=0;j<dp[0].length;j++)
        {
            dp[0][j] = j>=C[0]?profit[0]:0 ;
        }
        for(int i=1;i<dp.length;i++)
        {
            for(int j=0;j<dp[0].length;j++)
            {
                if(j-C[i]>=0 && i>0) dp[i][j] = Math.max(dp[i-1][j],profit[i]+dp[i-1][j-C[i]]  ) ;
                else if(i>0) dp[i][j] = dp[i-1][j] ;
            }
        }
        if(debug) 
        {
            System.out.println("dp:") ;
            printMatrix(dp) ;
        }
        int x = dp.length-1,y = dp[0].length-1 ;
        int sum = 0 ;
        while(x>0)
        {
            if(dp[x][y]==dp[x-1][y])
                x-- ;
            else
            {
                
                y = y-C[x] ;
                sum+=C[x] ;
                x-- ;
            }
        }
        if(x==0 && y-C[0]>=0)
            sum+=C[0] ;
        int ind ;
        for(ind = dp[0].length-1;ind>=0 && dp[dp.length-1][ind]==dp[dp.length-1][dp[0].length-1] ;ind--)
        {  
        }
        return new Pair(ind+1,dp[dp.length-1][dp[0].length-1]) ;
        // return sum ;
    }
    static void printMatrix(int[][] A)
    {
        for(int i=0;i<A.length;i++)
        {
            System.out.println(Arrays.toString(A[i])) ;
        }
    }
}