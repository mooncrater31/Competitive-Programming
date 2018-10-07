//Starts and ends with 0
//Number of 1s are less than number of 0s
//Find the section which has more 0s than 1s, of maximum length
//0s-1s should be maximum
//Need the sum of all 1s.
import java.util.* ;
// import java.io.BufferedReader ;
// import java.io.InputStreamReader ;
//Remember for : one zero, no zeros
public class FlippingGame
{
    private static final boolean debug = false ;
    private static final boolean debug2 = false ;
    static void printMatrix(int[][] M)
    {
        for(int i=0;i<M.length;i++)
            System.out.println(Arrays.toString(M[i])) ;
    }
    public static void main(String args[]) throws Exception
    {
        Scanner in = new Scanner(System.in) ;
        int N = in.nextInt() ;
        int[] A = new int[N] ;
        for(int i=0;i<N;i++)
        {
            A[i] = in.nextInt() ;
        }
        System.out.println(solve(A)) ;
        
    }
    static int solve(int[] A)
    {
        List<Integer> L = new ArrayList<Integer>() ;
        int n = A.length; 
        for(int i=0;i<n;i++)
        {
            if(A[i]==0)
                L.add(i) ;
        }
        int m = L.size() ;
        if(m==0)
            return n-1 ;
        else if(m==1)
            return n ;
        int[][] dp = new int[m][m] ;
        int k=0,ones=0 ;
        int max = 0;//maxi = -1,maxj = -1  ;
        for(int i=L.get(0)+1;i<n;i++)
        {
            if(A[i]==1)
                ones++ ;
            if(k<L.size()-1 && i==L.get(k+1))
            {
                dp[k][k+1] = 2-ones ;
                if(max<dp[k][k+1])
                {
                    max = dp[k][k+1] ;
                    // maxi = k ;
                    // maxj = k+1 ;
                }
                ones = 0 ;
                k++ ;
            }
        }
        
        int diff = 2; 
            for(int i=0;i+diff<m;i++)
            {
                int j = i+diff ;
                dp[i][i+diff] = dp[i+1][i+diff]+dp[i][i+diff-1] -1 ;
                if(max<dp[i][i+diff])
                {
                    max = dp[i][i+diff] ;
                    // maxi = i ;
                    // maxj = i+diff ;
                }
            }
        for(diff=3;diff<m;diff++)
        {
            for(int i=0;i+diff<m;i++)
            {
            dp[i][i+diff] = dp[i+diff-1][i+diff]+dp[i][i+diff-1]-1 ;
            if(max<dp[i][i+diff])
                {
                    max = dp[i][i+diff] ;
                    // maxi = i ;
                    // maxj = i+diff ;
                }
            }
        }
        if(debug) 
        {
            System.out.println("max :"+max) ;
        }
        if(debug2) 
        {
            System.out.println("dp :") ;
            printMatrix(dp) ;
        }
        
        return n-m+max ;
        
    }
}
