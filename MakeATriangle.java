import java.util.* ;
import java.io.BufferedReader ;
import java.io.InputStreamReader ;

public class MakeATriangle
{
    public static void main(String args[]) throws Exception
    {
        BufferedReader bro = new BufferedReader(new InputStreamReader(System.in)) ;
        String[] S = bro.readLine().split(" ") ;
        int a = Integer.parseInt(S[0]) ;
        int b = Integer.parseInt(S[1]) ;
        int c = Integer.parseInt(S[2]) ;
        int min = Math.min(a,Math.min(b,c)) ;
        int max = Math.max(a,Math.max(b,c)) ;
        int mid = a*b*c/(min*max) ;
        if(max>=mid+min)
            System.out.println((max-(mid+min)+1)) ;
        else System.out.println(0) ;
    }
}