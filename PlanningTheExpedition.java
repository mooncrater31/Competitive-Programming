import java.util.* ;
import java.io.BufferedReader ;
import java.io.InputStreamReader ;

public class PlanningTheExpedition
{
    public static void main(String args[]) throws Exception
    {
        BufferedReader bro = new BufferedReader(new InputStreamReader(System.in)) ;
        String[] S = bro.readLine().split(" ") ;
        int N = Integer.parseInt(S[0]) ;
        int M = Integer.parseInt(S[1]) ;
        int[] A = new int[M] ;
        String[] S1 = bro.readLine().split(" ") ;
        HashSet<Integer> H = new HashSet<Integer>() ;
        
        for(int i=0;i<M;i++)
        {
            A[i] = Integer.parseInt(S1[i]) ;
            H.add(A[i]) ;
        }
        Arrays.sort(A) ;
        int[] C = new int[H.size()] ;
        int idx = 0 ;
        C[0] = 1 ;
        for(int i=1;i<A.length;i++)
        {
            if(A[i]==A[i-1])
                C[idx]++ ;
            else C[++idx]++ ;
        }
        System.out.println(solve(C,N,M)) ;
    }
    static int solve(int[] c,int n,int m)
    {
        for(int i=m;i>0;i--)
        {
            int k = 0 ;
            for(int j=0;j<c.length;j++)
            {
                k+=(c[j]/i) ;
            }
            if(k>=n)
                return i ;
        }
        return 0 ;
    }
}