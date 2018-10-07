import java.util.* ;
import java.io.BufferedReader ;
import java.io.InputStreamReader ;

public class QuantitativeCoefficient
{
    static long mod = (int)1e9+7 ;
    public static void main(String args[]) throws Exception
    {
        BufferedReader bro = new BufferedReader(new InputStreamReader(System.in)) ;
        int T = Integer.parseInt(bro.readLine()) ;
        for(int t=0;t<T;t++)
        {
            String[] S = bro.readLine().split(" ") ;
            int n = Integer.parseInt(S[0]) ;
            int m = Integer.parseInt(S[1]) ;
            List<List<Triplet>> L = new ArrayList<List<Triplet>>() ;
            for(int i=0;i<n;i++)L.add(new ArrayList<Triplet>()) ;
            for(int i=0;i<m;i++)
            {
                S = bro.readLine().split(" ") ;
                int a = Integer.parseInt(S[0])-1 ;
                int b = Integer.parseInt(S[1])-1 ;
                long c = Long.parseLong(S[2]) ;
                L.get(a).add(new Triplet(c,b,a)) ;
                L.get(b).add(new Triplet(c,a,b)) ;
            }
            System.out.println(prim(L)) ;
        }
        
    }
    static long prim(List<List<Triplet>> L)
    {
        PriorityQueue<Triplet> pq = new PriorityQueue<Triplet>() ;
        int n = L.size() ;
        boolean[] visited = new boolean[n] ;
        pq.add(new Triplet(1,0,0)) ;
        long mst = 1 ;
        while(!pq.isEmpty())
        {
            Triplet t = pq.poll() ;
            
            if(!visited[t.b])
            {   
                mst = (mst*t.a+mod)%mod ;
                visited[t.b] = true ;
                for(int i=0;i<L.get(t.b).size();i++)
                {
                    Triplet ne = L.get(t.b).get(i) ;
                    pq.add(ne) ;
                }
            }
        }
        return mst ;
    }
}
class Triplet implements Comparable<Triplet>
{
    long a;
    int b,c ;
    Triplet(long a,int b,int c)
    {
        this.a = a ;
        this.b = b ;
        this.c = c ;
    }
    @Override
    public int compareTo(Triplet t){return this.a>t.a?1:(this.a<t.a?-1:0);}
}