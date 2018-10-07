import java.util.* ;
import java.io.BufferedReader ;
import java.io.InputStreamReader ;

public class SongsCompression
{
    public static void main(String args[]) throws Exception
    {
        BufferedReader bro = new BufferedReader(new InputStreamReader(System.in)) ;
        String[] S = bro.readLine().split(" ") ;
        int N = Integer.parseInt(S[0]) ;
        long M = Long.parseLong(S[1]) ;
        List<Pair> L = new ArrayList<Pair>() ;
        long comSum = 0,normSum = 0 ;
        for(int i=0;i<N;i++)
        {
            S = bro.readLine().split(" ") ;
            int a = Integer.parseInt(S[0]) ;
            int b = Integer.parseInt(S[1]) ;
            normSum+=a ;
            comSum+=b ;
            L.add(new Pair(a,b)) ;
        }
        if(normSum<=M)
        {
            System.out.println("0") ;
        }
        else if(comSum>M)
        {
            System.out.println("-1") ;
            
        }
        else
        {
            Collections.sort(L) ;
            long sum = 0 ;
            for(int i=L.size()-1;i>=0;i--)
            {
                sum += L.get(i).a-L.get(i).b ;
                if(normSum-sum<=M)
                {
                    System.out.println(L.size()-i) ;
                    break ;
                }
            }
        }
        
        
    }

}
class Pair implements Comparable<Pair>
{
    int a,b ;
    Pair(int a,int b)
    {
        this.a = a ;
        this.b = b ;
    }
    public int compareTo(Pair p)
    {
        return (this.a-this.b)-(p.a-p.b) ;
    }
}
