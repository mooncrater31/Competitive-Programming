import java.util.* ;
import java.io.BufferedReader ;
import java.io.InputStreamReader ;
//1 1
//a
public class Stages
{
    private static final boolean debug = false ;
    public static void main(String args[]) throws Exception
    {
        BufferedReader bro = new BufferedReader(new InputStreamReader(System.in)) ;
        String[] S = bro.readLine().split(" ") ;
        int N = Integer.parseInt(S[0]) ;
        int K = Integer.parseInt(S[1]) ;
        char[] C = new char[N] ;
        String S2 = bro.readLine() ;
        for(int i=0;i<S2.length();i++)
        {
            C[i] = S2.charAt(i) ;
        }
        System.out.println(solve(K,C)) ;
    }
    static int solve(int k,char[] C) 
    {
        Arrays.sort(C) ;
        if(debug) System.out.println(Arrays.toString(C)) ;
        char last = C[0] ;
        int count = 1,sum = (int)C[0]-(int)'a'+1;
        for(int i=1;i<C.length;i++)
        {
            if(C[i]-last>1)
            {
                if(count==k)
                    break ;
                if(debug) System.out.println("last :"+last) ;
                last = C[i] ;
                count++ ;
                sum+=(int)C[i]-(int)'a'+1 ;
                
            }
        }
        if(count<k)
            return -1 ;
        else return sum ;
        
    }
    
}