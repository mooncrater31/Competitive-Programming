import java.util.* ;
import java.io.BufferedReader ;
import java.io.InputStreamReader ;

public class Photo
{
    public static void main(String args[]) throws Exception
    {
        BufferedReader bro  = new BufferedReader(new InputStreamReader(System.in)) ;
        int N = Integer.parseInt(bro.readLine()) ;
        int[] A = new int[N] ;
        String[] S = bro.readLine().split(" ") ;
        for(int i=0;i<N;i++)
        {
            A[i] = Integer.parseInt(S[i]) ;
        }
        Arrays.sort(A) ;
        int a = A[0],d = A[N-1],c = A[N/2-1],b = A[N/2] ;
        System.out.println((d-b)*(c-a)) ;
    }
}