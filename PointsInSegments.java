import java.util.* ;
import java.io.BufferedReader ;
import java.io.InputStreamReader ;

public class PointsInSegments
{
    public static void main(String args[]) throws Exception
    {
        BufferedReader bro = new BufferedReader(new InputStreamReader(System.in)) ;
        String[] S = bro.readLine().split(" ") ;
        int N = Integer.parseInt(S[0]) ;
        int M = Integer.parseInt(S[1]) ;
        HashSet<Integer> H = new HashSet<Integer>() ;
        for(int i=1;i<=M;i++)
            H.add(i) ;
        for(int i=0;i<N;i++)
        {
            S = bro.readLine().split(" ") ;
            int a = Integer.parseInt(S[0]) ;
            int b = Integer.parseInt(S[1]) ;
            for(int j=a;j<=b;j++)
            {
                H.remove(j) ;
            }
            
        }
        List<Integer> L = new ArrayList<Integer>(H) ;
        System.out.println(L.size()) ;
        // Collections.sort(L) ;
        for(int i=0;i<L.size();i++)
        {
            System.out.print(L.get(i)+" ") ;
        }
        
    }
    
}