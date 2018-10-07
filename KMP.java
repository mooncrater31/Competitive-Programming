import java.io.BufferedReader ;
import java.io.InputStreamReader ;

public class KMP
{
    public static void main(String args[]) throws Exception
    {
        BufferedReader bro = new BufferedReader(new InputStreamReader(System.in)) ;
        String P = bro.readLine() ;
        String T = bro.readLine() ;
        int[] b = kmpPreprocess(P.toCharArray()) ;
        System.out.println(kmp(P.toCharArray(),T.toCharArray(),b)) ;
    }
    static int[] kmpPreprocess(char[] P)
    {
        int n = P.length ;
        int[] b = new int[n+1] ;
        int i=0,j=-1 ;
        b[0]=-1 ;
        while(i<n)
        {
            while(j>=0 && P[i]!=P[j]) j=b[j] ;
            i++;j++;
            b[i]=j ;
        }
        return b ;
    }
    static int kmp(char[] P,char[] T,int[] b)
    {
        int n = T.length ;
        int m = P.length ;
        int i=0,j=0 ;
        int count = 0 ;
        while(i<n)
        {
            while(j>=0 && P[j]!=T[i]) j=b[j] ;
            i++;j++ ;
            if(j==m)
            {
                count++;
                j=b[j];
            }
        }
        return count ;
    }
}