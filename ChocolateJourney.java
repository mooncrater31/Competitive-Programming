import java.io.BufferedReader ;
import java.io.InputStreamReader ;
import java.util.* ;

public class ChocolateJourney
{
    public static void main(String args[]) throws Exception
    {
        BufferedReader bro = new BufferedReader(new InputStreamReader(System.in)) ;
        String[] S = bro.readLine().split(" ") ;
        int n = Integer.parseInt(S[0]) ;
        int m = Integer.parseInt(S[1]) ;
        int k = Integer.parseInt(S[2]) ;
        int x = Integer.parseInt(S[3]) ;
        List<List<Pair>> L = new ArrayList<List<Pair>>() ;
        for(int i=0;i<n;i++) L.add(new ArrayList<Pair>()) ;
        int[] impCities = new int[k] ;
        S = bro.readLine().split(" ") ;
        for(int i=0;i<k;i++)
            impCities[i] = Integer.parseInt(S[i])-1 ;
        for(int i=0;i<m;i++)
        {
            S = bro.readLine().split(" ") ;
            int a = Integer.parseInt(S[0])-1 ;
            int b = Integer.parseInt(S[1])-1 ;
            int c = Integer.parseInt(S[2]) ;
            L.get(a).add(new Pair(c,b)) ;
            L.get(b).add(new Pair(c,a)) ;
            
        }
        S = bro.readLine().split(" ") ;
        int A = Integer.parseInt(S[0])-1 ;
        int B = Integer.parseInt(S[1])-1 ;
        int[] d1 = dijkstra(A,L) ;
        int[] d2 = dijkstra(B,L) ;
        int min = Integer.MAX_VALUE ;
        
        for(int i=0;i<k;i++)
        {
            int city = impCities[i] ;
            if(d1[city]!=Integer.MAX_VALUE && d2[city]!=Integer.MAX_VALUE)
            {
                if(d2[city]<=x)
                {
                    if(min>d1[city]+d2[city]) min = d1[city]+d2[city] ;
                }
            }
        }
        System.out.println(min==Integer.MAX_VALUE?-1:min) ;
    }
    static int[] dijkstra(int source,List<List<Pair>> L)
    {
        int n = L.size() ;
        PriorityQueue<Pair> pq = new PriorityQueue<Pair>() ;
        int[] dist = new int[n] ;
        dist[source] = 0 ;
        Arrays.fill(dist,Integer.MAX_VALUE) ;
        pq.add(new Pair(0,source)) ;
        while(!pq.isEmpty())
        {
            Pair p = pq.poll() ;
            if(p.a>dist[p.b]) continue ;
            for(int i=0;i<L.get(p.b).size();i++)
            {
                Pair v = L.get(p.b).get(i) ;
                if(v.a+dist[p.b]<dist[v.b])
                {
                    dist[v.b] = v.a+dist[p.b] ;
                    pq.add(new Pair(dist[v.b],v.b)) ;
                }
            }
        }
        // for(int i=0;i<n;i++)
            // if(dist[i]==Integer.MAX_VALUE)
                // dist[i]==-1 ;
            
        return dist ;
    }
}
class Pair implements Comparable<Pair>//Used for both graph representation and PriorityQueue
{
    int a,b ;
    Pair(int a,int b)
    {
        this.a = a;
        this.b = b ;
    }
    @Override
    public int compareTo(Pair p)
    {
        if(p.a>this.a) return -1 ;
        else if(p.a<this.a) return 1 ;
        else return 0 ;
    }
}