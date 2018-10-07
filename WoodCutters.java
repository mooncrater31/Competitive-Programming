import java.util.* ;
import java.io.BufferedReader ;
import java.io.InputStreamReader ;

public class WoodCutters
{
    private static final boolean debug = true ;
    public static void main(String args[]) throws Exception
    {
        BufferedReader bro = new BufferedReader(new InputStreamReader(System.in)) ;
        int N = Integer.parseInt(bro.readLine()) ;
        int[] pos = new int[N] ;
        int[] h = new int[N] ;
        for(int i=0;i<N;i++)
        {
            String[] S = bro.readLine().split(" ") ;
            pos[i] = Integer.parseInt(S[0]) ;
            h[i] = Integer.parseInt(S[1]) ;
        }
        System.out.println(solve(pos,h)) ;
        
    }
    static int solve(int[] pos,int[] h)
    {
        int ways = 1,n = pos.length ;
        // boolean right = false ;//The first tree falls to it's left
        int last = pos[0] ; 
        for(int i=1;i<n-1;i++)
        {
            if(pos[i]-h[i]>last)
            {
                ways++ ;
                last = pos[i] ;
                // System.out.println("Selected :("+pos[i]+
            }
            else if(pos[i+1]-pos[i]>h[i])
            {
                ways++ ;
                last = pos[i]+h[i] ;
            }
            else
                last = pos[i] ;
            
            
        }
        return n==1?ways:ways+1 ; 
    }
}