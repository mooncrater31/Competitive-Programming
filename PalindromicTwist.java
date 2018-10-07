import java.util.* ;
import java.io.BufferedReader ;
import java.io.InputStreamReader ;

public class PalindromicTwist
{
    public static void main(String args[]) throws Exception
    {
        BufferedReader bro = new BufferedReader(new InputStreamReader(System.in)) ;
        int T = Integer.parseInt(bro.readLine()) ;
        for(int t=0;t<T;t++)
        {
            int n = Integer.parseInt(bro.readLine()) ;
            String S  = bro.readLine() ;
            System.out.println(solve(S.toCharArray())) ;
        }
    }
    static String solve(char[] C)
    {
        if(C.length%2!=0)
            return "NO" ;
        else
        {
            int count = 0 ;
            for(int i=0;i<C.length/2;i++)
            {
                char c1 = C[i], c2 = C[C.length-i-1] ;
                int c1n = (char)((int)c1+1) ;
                int c2n = (char)((int)c2+1) ;
                int c1b = (char)((int)c1-1) ;
                int c2b = (char)((int)c2-1) ;
                if(c1n==c2n || c1n == c2b || c1b==c2b || c1b== c2n)
                    count++ ;
                else
                    return "NO" ;
                
            }
            return "YES" ;
        }
    }
}