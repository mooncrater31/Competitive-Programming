import java.io.BufferedReader ;
import java.io.InputStreamReader ;
import java.util.* ;

public class BoozeFirst
{
    public static void main(String args[]) throws Exception
    {
        BufferedReader bro = new BufferedReader(new InputStreamReader(System.in)) ;
        String[] S = bro.readLine().split(" ") ;
        int n = Integer.parseInt(S[0]) ;
        int m = Integer.parseInt(S[1]) ;
        List<List<Pair>> L = new ArrayList<List<Pair>>() ;
        for(int i=0;i<n+1;i++) L.add(new ArrayList<Pair>()) ;
        for(int i=0;i<m;i++)
        {
            S = bro.readLine().split(" ") ;
            int a = Integer.parseInt(S[0]) ;
            int b = Integer.parseInt(S[1]) ;
            int c = Integer.parseInt(S[2]) ;
            L.get(a).add(new Pair(c,b)) ;
            L.get(b).add(new Pair(c,a)) ;
            
        }
        int B = Integer.parseInt(bro.readLine()) ;
        S = bro.readLine().split(" ") ;
        for(int i=0;i<B;i++)
        {
            int b = Integer.parseInt(S[i]) ;
            L.get(0).add(new Pair(0,b)) ;
            L.get(b).add(new Pair(0,0)) ;
            
        }
        long[] dist = dijkstra(L,0) ;
        for(int i=1;i<n+1;i++)
            System.out.println(dist[i]) ;
        
    }
    static long[] dijkstra(List<List<Pair>> L,int s)
    {
        PriorityQueue<Pair> pq = new PriorityQueue<Pair>() ;
        int n = L.size() ;
        boolean[] visited = new boolean[n+1] ;
        long[] dist = new long[n] ;
        Arrays.fill(dist,Long.MAX_VALUE) ;
        dist[s] = 0 ;
        pq.add(new Pair(0,s)) ;
        while(!pq.isEmpty())
        {
            Pair u = pq.poll() ;
            if(!visited[u.b])
            {
                visited[u.b] = true ;
                if(u.a>dist[u.b]) continue ;
                for(int i=0;i<L.get(u.b).size();i++)
                {
                    Pair v = L.get(u.b).get(i) ;
                    if(v.a+dist[u.b]<dist[v.b])
                    {
                        dist[v.b] = dist[u.b]+v.a ;
                        pq.add(new Pair(dist[v.b],v.b)) ;
                    }
                }
            }
        }
        return dist ;
    }
}
class Pair implements Comparable<Pair>
{
    long a ;
    int b ;
    Pair(long a,int b)
    {
        this.a = a ;
        this.b = b ;
    }
    @Override
    public int compareTo(Pair p)
    {
        return this.a>p.a?1:(this.a<p.a?-1:0) ;
    }
}