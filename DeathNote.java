import java.util.* ;
import java.io.BufferedReader ;
import java.io.InputStreamReader ;

public class DeathNote
{
    public static void main(String args[]) throws Exception
    {
        BufferedReader bro = new BufferedReader(new InputStreamReader(System.in)) ;
        String[] S = bro.readLine().split(" ") ;
        int n = Integer.parseInt(S[0]) ;
        int m = Integer.parseInt(S[1]) ;
        int[] A = new int[n] ;
        S = bro.readLine().split(" ") ;
        for(int i=0;i<n;i++)
        {
            A[i] = Integer.parseInt(S[i]) ;
        }
        long sum = 0,res = 0  ;
        List<Long> L = new ArrayList<Long>() ;
        for(int i=0;i<n;i++)
        {
            long val = A[i]+res ; 
            L.add(val/m) ;
            res = val%m ;
        }
        for(int i=0;i<n;i++)
        {
            System.out.print(L.get(i)+" ") ;
        }
        
    }
    
}