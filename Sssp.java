import java.util.* ;
import java.io.BufferedReader ;
import java.io.InputStreamReader ;

public class Sssp
{
    private static final boolean debug = false ;
    public static void main(String args[]) throws Exception
    {
        BufferedReader bro = new BufferedReader(new InputStreamReader(System.in)) ;
        String[] S = bro.readLine().split(" ") ;
        int n = Integer.parseInt(S[0]) ;
        int m = Integer.parseInt(S[1]) ;
        List<List<Pair>> L = new ArrayList<List<Pair>>() ;
        for(int i=0;i<n;i++)
            L.add(new ArrayList<Pair>()) ;
        for(int i=0;i<m;i++)
        {
            S = bro.readLine().split(" ") ;
            int a = Integer.parseInt(S[0])-1 ;
            int b = Integer.parseInt(S[1])-1 ;
            int c = Integer.parseInt(S[2]) ;
            L.get(a).add(new Pair(c,b)) ;
            // L.get(b).add(new Pair(c,a)) ;
        }
        // System.out.println(Arrays.toString(dijkstra(L))) ;
        int[] d = dijkstra(L) ;
        for(int i=1;i<n;i++)
            System.out.print(d[i]+" ") ;
    }
    static int[] dijkstra(List<List<Pair>> L)
    {
        int n = L.size() ;
        int[] d = new int[n] ;
        Arrays.fill(d,(int)1e9);
        // PriorityQueue<Integer> pq = new PriorityQueue<Integer>() ;
        PriorityQueue<Pair> pq = new PriorityQueue<Pair>() ;
        pq.add(new Pair(0,0)) ;
        d[0] = 0 ;
        while(!pq.isEmpty())
        {
            Pair p = pq.poll();
            int dist = p.dist, u = p.val ;
            if(dist>d[u]) continue ;
            for(int i=0;i<L.get(u).size();i++)
            {
                Pair v = L.get(u).get(i) ;
                if(d[u]+v.dist<d[v.val]) 
                {
                    if(debug)
                    {
                        System.out.println("Seasons change.") ;
                    }
                    d[v.val] = d[u]+v.dist ;
                    pq.add(new Pair(d[v.val],v.val)) ;
                }
            }
            
        }
        return d ;
    }
}
class Pair implements Comparable<Pair>
{
    int dist,val ;
    Pair(int d,int v)
    {
        this.dist = d ;
        this.val = v ;
    }
    @Override
    public int compareTo(Pair p)
    {
        if(p.dist>this.dist) return -1 ;
        else if(p.dist<this.dist) return 1 ;
        else return 0 ;
    }
    
    
}
