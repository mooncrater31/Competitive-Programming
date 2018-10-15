import java.util.* ;
import java.io.BufferedReader ;
import java.io.InputStreamReader ;

public class OhThosePalindromes
{
    public static void main(String args[]) throws Exception
    {
        BufferedReader bro = new BufferedReader(new InputStreamReader(System.in)) ;
        int L = Integer.parseInt(bro.readLine()) ;
        char[] arr = new char[L] ;
        String S = bro.readLine() ;
        for(int i=0;i<L;i++) arr[i] = S.charAt(i) ;
        Arrays.sort(arr);
        System.out.println(new String(arr)) ;
    }

}