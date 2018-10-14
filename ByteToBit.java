import java.util.* ;
import java.io.BufferedReader ;
import java.io.InputStreamReader ;

public class Main
{
    public static void main(String args[]) throws Exception
    {
        BufferedReader bro = new BufferedReader(new InputStreamReader(System.in)) ;
        int T = Integer.parseInt(bro.readLine()) ;
        for(int t=0;t<T;t++)
        {
            int N = Integer.parseInt(bro.readLine()) ;
            int pow = N/26 ;
            long num = (long)Math.pow(2,pow) ;
            if(N%26<3) System.out.println(num+" 0 0") ;
            else if(N%26<11) System.out.println("0 "+num+" 0") ;
            else System.out.println("0 0 "+num) ;
        }
    }
}
