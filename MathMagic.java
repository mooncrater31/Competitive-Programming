import java.util.* ;
import java.io.BufferedReader ;
import java.io.InputStreamReader ;

public class MathMagic
{
    public static void main(String args[]) throws Exception
    {
        BufferedReader bro = new BufferedReader(new InputStreamReader(System.in)) ;
        int T = Integer.parseInt(bro.readLine()) ;
        for(int t=0;t<T;t++)
        {
            System.out.println((long)Math.pow(2,Integer.bitCount(Integer.parseInt(bro.readLine())))) ;
        }
    }
}