import java.util.* ;
import java.io.BufferedReader ;
import java.io.InputStreamReader ;

public class WalkingBetweenHouses
{
    public static void main(String args[]) throws Exception
    {
        BufferedReader bro = new BufferedReader(new InputStreamReader(System.in)) ;
        String[] S = bro.readLine().split(" ") ;
        int n = Integer.parseInt(S[0]) ;
        int K = Integer.parseInt(S[1]) ;
        long s = Long.parseLong(S[2]) ;
        if(K>s || (double)s/(n-1)>K)
            System.out.println("NO") ;
        else
        {
            int last = 1 ;
            System.out.println("YES") ;
            // String ans = 
            for(int k=K;k>=1;k--)
            {
                long dist = Math.min(n-1,s-k+1) ;
                if(last-dist<=0)
                    last+=dist ;
                else
                    last-=dist ;
                s-=dist ;
                
                System.out.print(last+" ") ;
            }
        }
        
    }
}