import java.io.BufferedReader ;
import java.io.InputStreamReader ;
import java.util.* ;

public class FunctionHeight
{
    public static void main(String args[]) throws Exception
    {
        BufferedReader bro = new BufferedReader(new InputStreamReader(System.in)) ;
        String[] S = bro.readLine().split(" ") ;
        long a = Long.parseLong(S[0]) ;
        long b = Long.parseLong(S[1]) ;
        System.out.println((b%a==0?b/a:b/a+1)) ;
    }
}