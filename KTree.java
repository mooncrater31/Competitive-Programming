import java.util.* ;
import java.io.BufferedReader ;
import java.io.InputStreamReader ;

public class KTree
{
    private static final boolean debug = false ;
    public static void main(String args[]) throws Exception
    {
        BufferedReader bro = new BufferedReader(new InputStreamReader(System.in)) ;
        String[] S = bro.readLine().split(" ") ;
        int N = Integer.parseInt(S[0]) ;
        int K = Integer.parseInt(S[1]) ;
        int D = Integer.parseInt(S[2]) ;
        System.out.println(solve2(K,N,D)) ;
    }
    static void printMatrix(long[][] A)
    {
        for(int i=0;i<A.length;i++)
        {
            System.out.println(Arrays.toString(A[i])) ;
        }
    }
    
    static int solve(int K,int n,int d)
    {
        int[] C = new int[K] ;
        for(int i=0;i<K;i++)
        {
            C[i] = i+1 ;
        }
        long[][] dp = new long[K][n+1] ;
        for(int i=0;i<dp.length;i++)
        {
            dp[i][0] = 1 ;//Value zero can be made in one way
        }
        if(debug)
        {
            printMatrix(dp) ;
        }
        
        for(int i=0;i<dp.length;i++)
        {
            for(int j=1;j<dp[0].length;j++)
            {
                long x=0,y=0 ;
                if(j-C[i]>=0) x= dp[i][j-C[i]] ;
                if(i-1>=0) y=dp[i-1][j] ;
                if(debug)
                {
                    System.out.println("i,j :("+i+","+j+") x = "+x+" y = "+y) ;
                }
                dp[i][j]=x+y ;
            }
        }
        long ans = (d==1)?dp[K-1][n]:dp[K-1][n]-dp[d-2][n] ;
        if(debug)
        {
            System.out.println("ans :"+ans) ;
            printMatrix(dp) ;   
        }
        return (int)(ans%((int)(1e9)+7)) ;
        
    }
    static long solve2(int K,int n,int d)
    {
        int[] C = new int[K] ;
        for(int i=0;i<K;i++)
        {
            C[i] = i+1 ;
        }
        long[] dp = new long[n+1] ;
        long[] dp2 = new long[n+1] ;
        dp[0] = 1 ;
        dp2[0] = 1 ;
        for(int i=1;i<n+1;i++)
        {
            for(int j=0;j<C.length;j++)
            {
                if(i-C[j]>=0)
                {
                    dp[i]+=dp[i-C[j]]%((int)1e9+7) ;
                    if(d>1 && j<d-1)
                    {
                        dp2[i]+=(dp2[i-C[j]])%((int)1e9+7) ;
                    }
                }
                
            }
        }
        long ans = (d==1)?dp[n]:dp[n]-dp2[n] ;
        ans = ans%((int)1e9+7) ;
        if(ans<0)
        {
            // System.out.println("ans :"+ans) ;
            ans+=(int)1e9+7 ;
            // System.out.println("ans :"+ans) ;
        }
        // ans  = ans<0?(ans+(int)1e9+7):(ans%((int)1e9+7)) ;
        if(debug)
        {
            System.out.println("dp[n] :"+dp[n]+" dp2[n] :"+dp2[n]+" their distance :"+(dp[n]-dp2[n])) ;
            System.out.println("ans :"+ans+" ans2 :"+(ans+(int)1e9+7)) ;
        }
       
        return  ans;
    }
        
}