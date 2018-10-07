import java.io.BufferedReader ;
import java.io.InputStreamReader ;
import java.util.* ;

public class DiagonalWalking
{
    public static void main(String args[]) throws Exception
    {
        BufferedReader bro = new BufferedReader(new InputStreamReader(System.in)) ;
        int Q = Integer.parseInt(bro.readLine()) ;
        for(int i=0;i<Q;i++)
        {
            String[] S = bro.readLine().split(" ") ;
            long a = Long.parseLong(S[0]) ;
            long b = Long.parseLong(S[1]) ;
            long k = Long.parseLong(S[2]) ;
            long max = Math.max(a,b) ;
            if(k<max)
                System.out.println("-1") ;
            else
            {
                boolean even = ((a%2+b%2)%2==0) ;
                if(even) //Diagonal reachable
                {
                    if(max%2==k%2)
                        System.out.println(k) ;
                    else System.out.println(k-2) ;
                }
                else //Non-reachable points
                {
                    System.out.println(k-1) ;
                }
            }
        }
    }
}