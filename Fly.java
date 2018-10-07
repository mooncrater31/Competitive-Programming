import java.util.* ;
import java.io.BufferedReader ;
import java.io.InputStreamReader ;

public class Fly
{
    public static void main(String args[]) throws Exception
    {
        BufferedReader bro = new BufferedReader(new InputStreamReader(System.in)) ;
        int N = Integer.parseInt(bro.readLine()) ;
        int W = Integer.parseInt(bro.readLine()) ;
        String[] S = bro.readLine().split(" ") ;
        int[] T = new int[N] ;
        int[] L = new int[N] ;
        for(int i=0;i<N;i++)
        {
            T[i] = Integer.parseInt(S[i]) ;
        }
        S = bro.readLine().split(" ") ;
        for(int i=0;i<N;i++)
        {
            L[i] = Integer.parseInt(S[i]) ;
        }
        double ans = solve(T,L,W) ;
        System.out.println(ans==-1.0?-1:ans-W) ;
    }
    static double solve(int[] T,int[] L,int w)
    {
        // List<Integer> L = new ArrayList<Integer>() ;
        int[] A = new int[2*T.length] ;
        for(int i=0;i<A.length;i+=2)
        {
            A[i] = T[i/2] ;
        }
        for(int i=1;i<L.length;i++)
        {
            A[2*i-1] = L[i] ;
        }
        A[A.length-1] = L[0] ;
        double sum = w ;
        for(int i=0;i<A.length;i++)
        {
            if(A[i]==1)
                return -1 ;
            sum+=(double)(sum/(A[i]-1)) ;
        }
        return sum ;
    }
}