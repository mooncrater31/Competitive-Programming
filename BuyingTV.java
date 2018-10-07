import java.util.* ;
import java.io.BufferedReader ;
import java.io.InputStreamReader ;

public class BuyingTV
{
    public static void main(String args[]) throws Exception
    {
        BufferedReader bro = new BufferedReader(new InputStreamReader(System.in)) ;
        String[] S = bro.readLine().split(" ") ;
        long a = Long.parseLong(S[0]) ;
        long b = Long.parseLong(S[1]) ;
        long x = Long.parseLong(S[2]) ;
        long y = Long.parseLong(S[3]) ;
        long g = gcd(x,y) ;
        long alpha = x/g,beta=y/g ;
        System.out.println(Math.min(a/alpha,b/beta)) ;
    }
    static long gcd(long a,long b)
    {
        if(a==0) return b ;
        return gcd(b%a,a) ;
    }
}