import java.util.* ;
import java.io.BufferedReader ;
import java.io.InputStreamReader ;

public class k1tok2
{
    public static void main(String args[]) throws Exception
    {
        BufferedReader bro = new BufferedReader(new InputStreamReader(System.in)) ;
        int T = Integer.parseInt(bro.readLine()) ;
        for(int t=0;t<T;t++)
        {
            int N = Integer.parseInt(bro.readLine()) ;
            int[] A = new int[N] ;
            String[] S = bro.readLine().split(" ") ;
            for(int i=0;i<A.length;i++)
            {
                A[i] = Integer.parseInt(S[i]) ;
            }
            Arrays.sort(A) ;
            S = bro.readLine().split(" ") ;
            int a = Integer.parseInt(S[0])-1 ;
            int b = Integer.parseInt(S[1])-1 ;
            int sum = 0 ;
            for(int i=a+1;i<b;i++)
            {
                sum+=A[i] ;
            }
            System.out.println(sum) ;
        }
    }
}