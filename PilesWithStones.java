import java.util.* ;
import java.io.BufferedReader ;
import java.io.InputStreamReader ;

public class PilesWithStones
{
    public static void main(String args[]) throws Exception
    {
        Scanner in = new Scanner(System.in) ;
        int N = in.nextInt() ;
        
        int sumA = 0,sumB = 0 ;
        for(int i=0;i<N;i++)
        {
            sumA+=in.nextInt() ;
        }
        for(int i=0;i<N;i++)
        {
            sumB+=in.nextInt() ;
            
        }
        
        System.out.println(sumA>=sumB?"Yes":"No") ;
    }
    
    
}