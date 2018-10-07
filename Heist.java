import java.util.* ;
import java.io.BufferedReader ;
import java.io.InputStreamReader ;

public class Heist
{
    public static void main(String args[]) throws Exception
    {
        BufferedReader bro = new BufferedReader(new InputStreamReader(System.in)) ;
        int N = Integer.parseInt(bro.readLine()) ;
        String[] S = bro.readLine().split(" ") ;
        int[] A = new int[N] ;
        int min=Integer.MAX_VALUE,max = Integer.MIN_VALUE ;
        for(int i=0;i<N;i++)
        {
            A[i] = Integer.parseInt(S[i]) ;
            min = Math.min(min,A[i]) ;
            max = Math.max(max,A[i]) ;
        }
        System.out.println(max-min+1-N) ;
    }
}