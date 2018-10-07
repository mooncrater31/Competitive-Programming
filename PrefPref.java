import java.io.BufferedReader ;
import java.io.InputStreamReader ;

public class PrefPref
{
    public static void main(String args[]) throws Exception
    {
        BufferedReader bro = new BufferedReader(new InputStreamReader(System.in)) ;
        String S = bro.readLine() ;
        String T = bro.readLine() ;
        int n = T.length() ;
        int m = S.length() ;
        int j=0 ;
        for(int i=0;i<n && j<m;i++)
        {
            if(T.charAt(i)==S.charAt(j)) j++ ;
        }
        System.out.println(j) ;
    }
}