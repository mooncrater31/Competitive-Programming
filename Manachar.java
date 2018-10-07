import java.util.* ;
import java.io.BufferedReader ;
import java.io.InputStreamReader ;

public class Manachar
{
    public static void main(String args[]) throws Exception
    {
        BufferedReader bro = new BufferedReader(new InputStreamReader(System.in)) ;
        String S = bro.readLine() ;
        if(S.length()%2==0)
        {
            System.out.println(manachar(addHash(S.toCharArray()))) ;
        }
        else System.out.println(manacha`r(S.toCharArray())) ;
    }
    static char[] addHash(char[] S)
    {
        char[] arr = new char[2*S.length+1] ;
        for(int i=0;i<arr.length;i++)
        {
            if(i%2==0)
            {
                arr[i] = '#' ;
            }
            else
            {
                arr[i] = S[i/2] ;
            }
        }
        return arr ;
    }
    static int manachar(char[] S)
    {
        int c=0,r=0 ;
        int[] P = new int[S.length] ;
        for(int i=1;i<S.length;i++)
        {
            int iMirror = 2*c-i ;
            if(i<r)
            {
                P[i] = Math.min(r-i,P[iMirror]) ;
            }
            while(S[i+1+P[i]]==S[i-1-P[i]])
            {
                P[i]++ ;
            }
            if(i+P[i]>r)
            {
                c = i ;
                r = i+P[i] ;
            }
        }
        int max = 0 ;
        for(int i=0;i<P.length;i++)
        {
            if(P[i]>max)
            {
                max = P[i] ;
            }
        }
        return max ;
    }
}