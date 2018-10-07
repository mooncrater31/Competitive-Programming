import java.util.* ;
import java.io.BufferedReader ;
import java.io.InputStreamReader ;

public class CreatingTheContest
{
    private static final boolean debug = false ;
    public static void main(String args[]) throws Exception
    {
        BufferedReader bro = new BufferedReader(new InputStreamReader(System.in)) ;
        int N = Integer.parseInt(bro.readLine()) ;
        long[] A = new long[N] ;
        String[] S = bro.readLine().split(" ") ;
        for(int i=0;i<N;i++) A[i] = Long.parseLong(S[i]) ;
        int count = 1,maxC = 1 ;
        for(int i=0;i<N-1;i++)
        {
            if(A[i+1]<=2*A[i])
            {
                if(debug) System.out.println("For :"+A[i]+" "+A[i+1]) ;
                count++ ;
                
            }
            else
            {
                count = 1 ;
            }                
            maxC = Math.max(maxC,count) ;
        }
        System.out.println(maxC) ;
    }
}