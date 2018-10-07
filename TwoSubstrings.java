import java.util.* ;
import java.io.BufferedReader ;
import java.io.InputStreamReader ;

public class TwoSubstrings
{
    public static void main(String args[]) throws Exception
    {
        BufferedReader bro = new BufferedReader(new InputStreamReader(System.in)) ;
        System.out.println(solve(bro.readLine())?"YES":"NO") ;
    }
    static boolean solve(String S)
    {
        int l = S.length() ;
        boolean AB = false,BA = false ;
        for(int i=0;i<l-1;i++)
        {
            if(!AB && S.charAt(i)=='A' && S.charAt(i+1)=='B')
            {
                i++ ;
                AB = true ;
            }
            else if(AB && !BA && S.charAt(i)=='B' && S.charAt(i+1)=='A')
            {
                i++ ;
                BA = true ;
            }

        }
        boolean AB2 = false,BA2 = false ;
        for(int i=0;i<l-1;i++)
        {
            if(!BA2 && S.charAt(i)=='B' && S.charAt(i+1)=='A')
            {
                i++ ;
                BA2 = true ;
            }
            else if(BA2 && !AB2 && S.charAt(i)=='A' && S.charAt(i+1)=='B')
            {
                i++ ;
                AB2 = true ;
            }
            

        }
        return (AB && BA) || (BA2 && AB2) ;
    }
}