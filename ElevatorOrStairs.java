import java.util.* ;
import java.io.BufferedReader ;
import java.io.InputStreamReader ;

public class ElevatorOrStairs
{
    public static void main(String args[]) throws Exception
    {
        BufferedReader bro = new BufferedReader(new InputStreamReader(System.in)) ;
        String[] S = bro.readLine().split(" ") ;
        int x = Integer.parseInt(S[0]) ;
        int y = Integer.parseInt(S[1]) ;
        int z = Integer.parseInt(S[2]) ;
        int t1 = Integer.parseInt(S[3]) ;
        int t2 = Integer.parseInt(S[4]) ;
        int t3 = Integer.parseInt(S[5]) ;
        int el = (Math.abs(z-x)+Math.abs(y-x))*t2+3*t3 ;
        int st = Math.abs(y-x)*t1 ;
        if(el<=st)
            System.out.println("YES") ;
        else System.out.println("NO");
    }
}