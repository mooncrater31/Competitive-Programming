import java.util.* ;
import java.io.BufferedReader ;
import java.io.InputStreamReader ;

public class BuyingItems
{
    private static final boolean debug = false ;
    private static final boolean debug2 = false ;
    public static void main(String args[]) throws Exception
    {
        BufferedReader bro = new BufferedReader(new InputStreamReader(System.in)) ;
        String[] S = bro.readLine().split(" ") ;
        int n = Integer.parseInt(S[0]) ;
        int m = Integer.parseInt(S[1]) ;
        if(n<=23)
        {
            long[] D = new long[1<<n] ;
            final int N = 1<<n; 
            Arrays.fill(D,(long)1e18) ;
            int[] M = new int[m] ;
            int[] C = new int[m] ;
            for(int i=0;i<m;i++)
            {
                S = bro.readLine().split(" ") ;
                for(int j=0;j<n;j++)
                {
                    int v = Integer.parseInt(S[j]) ;
                    M[i] = M[i]|(v*(1<<j)) ;
                }
                C[i] = Integer.parseInt(S[n]) ;
                
            }
            if(debug) System.out.println("M :"+Arrays.toString(M)) ;
            D[0] = 0 ;
            for(int mask = 0 ;mask<N;mask++)
            {
                for(int i=0;i<m;i++)
                {
                    D[M[i]|mask] = Math.min(D[M[i]|mask],D[mask]+C[i]) ;
                }
                if(debug) System.out.println(Arrays.toString(D)) ;
            }
            if(debug) 
            {
                System.out.println(Arrays.toString(D)) ;
            }
            if(D[N-1]!=(long)1e18)
                System.out.println(D[N-1]) ;
            else System.out.println("-1") ;
            
            
        }
        else
        {
            // boolean[] M = new boolean[m] ;
            long[] M = new long[m] ;
            long[] C = new long[m] ;
            for(int i=0;i<m;i++)
            {
                S = bro.readLine().split(" ") ;
                for(int j=0;j<n;j++)
                {
                    int v = Integer.parseInt(S[j]) ;
                    M[i] = M[i]|(v*(1L<<j)) ;
                }
                C[i] = Integer.parseInt(S[n]) ;
                
            }
            if(debug2) System.out.println("C :"+Arrays.toString(C)) ;
            long tM = 1L<<m ;
            long N = 1L<<n ;
            long ans = (long)1e18 ;
            for(int mask=0;mask<tM;mask++)
            {
                long cost = 0 ;
                long W = 0 ;
                for(int i=0;i<m;i++)
                {
                    if(((mask>>i)&1)>0)
                    {
                        W|=M[i] ;
                        cost+=C[i] ;
                        // if(debug2) System.out.println("cost :::::"+cost) ;
                    }
                    
                }
                if(debug) 
                {
                    System.out.println("W :"+W+" and N-1 :"+(N-1)) ;
                }
                if(W==N-1)
                {
                    if(debug2) System.out.println("cost :"+cost+" mask :"+mask+" W :"+W+" N :"+N+" n :"+n) ;
                    ans = Math.min(ans,cost) ;
                }
                
            }
            if(ans!=(long)1e18)
                System.out.println(ans) ;
            else System.out.println("-1") ;
        }
        
        
    }
    
}