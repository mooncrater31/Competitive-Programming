import java.util.* ;
import java.io.BufferedReader ;
import java.io.InputStreamReader ;

public class ManyEqualSubstrings
{
    public static void main(String args[]) throws Exception
    {
        BufferedReader bro = new BufferedReader(new InputStreamReader(System.in)) ;
        // String S = "abababa" ;
        // System.out.println(Arrays.toString(kmpPreprocess(S.toCharArray()))) ;
        String[] S = bro.readLine().split(" ") ;
        int n = Integer.parseInt(S[0]) ;
        int k = Integer.parseInt(S[1]) ;
        String t = bro.readLine() ;
        if(k==1)
            System.out.println(t) ;
        else
        {
            int pos = kmpPreprocess(t.toCharArray())[n] ;
            if(pos==-1)
            {
                for(int i=0;i<k;i++) System.out.print(t) ;
            }
            else
            {
                System.out.print(t) ;
                for(int i=0;i<k-1;i++)
                {
                    System.out.print(t.substring(pos,t.length())) ;
                }
            }
        }
        
    }
    static int[] kmpPreprocess(char[] P)
    {
        int i=0,j=-1 ;
        int m = P.length; 
        int[] b = new int[m+1] ;
        b[0]=-1 ;
        while(i<m)
        {
            while(j>=0 && P[i]!=P[j]) j = b[j] ;
            i++;j++;
            b[i] = j ;
        }
        return b ;
    }
}