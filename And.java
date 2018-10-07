import java.util.* ;
import java.io.BufferedReader ;
import java.io.InputStreamReader ;

public class And
{
    public static void main(String args[]) throws Exception
    {
        BufferedReader bro = new BufferedReader(new InputStreamReader(System.in)) ;
        String[] S = bro.readLine().split(" ") ;
        int N = Integer.parseInt(S[0]) ;
        int K = Integer.parseInt(S[1]) ;
        S = bro.readLine().split(" ") ;
        int[] A = new int[N] ;
        HashSet<Integer> H1 = new HashSet<Integer>() ;
        HashSet<Integer> H2 = new HashSet<Integer>() ;
        boolean found = false ;
        int ways = -1 ;
        for(int i=0;i<N;i++)
        {
            A[i] = Integer.parseInt(S[i]) ;
            if(H1.contains(A[i]))
            {
                // System.out.println("0") ;
                ways = 0 ;
                found = true ;
                break ;
            }
            H1.add(A[i]) ;
            // H2.add(A[i]&K) ;
            if(H2.contains(A[i]&K))
            {
                ways = 2 ;
                
            }
            else H2.add(A[i]&K) ;
        }
        if(!found)
        {
            for(int i=0;i<A.length;i++)
            {
                if((A[i]&K)!=A[i] && H1.contains(A[i]&K))
                {
                    ways = 1 ;
                }
                
            }
        }
        System.out.println(ways) ;
        ////Input k,A
        
    }
}

