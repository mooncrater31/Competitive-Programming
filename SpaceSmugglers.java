import java.util.* ;
import java.io.BufferedReader ;
import java.io.InputStreamReader ;

public class SpaceSmugglers
{
    private static final boolean debug = false ;
    public static void main(String args[]) throws Exception
    {
        BufferedReader  bro = new BufferedReader(new InputStreamReader(System.in)) ;
        String[] S = bro.readLine().split(" ") ;
        int n = Integer.parseInt(S[0]) ;
        int m = Integer.parseInt(S[1]) ;
        int s = Integer.parseInt(S[2])-1 ;
        int d = Integer.parseInt(S[3])-1 ;
        List<List<Pair>> L = new ArrayList<List<Pair>>() ;
        List<List<Pair>> RL = new ArrayList<List<Pair>>() ;
        for(int i=0;i<n;i++) {L.add(new ArrayList<Pair>()) ;RL.add(new ArrayList<Pair>()) ;}
        for(int i=0;i<m;i++)
        {
            S = bro.readLine().split(" ") ;
            int a = Integer.parseInt(S[0])-1 ;
            int b = Integer.parseInt(S[1])-1 ;
            int c = Integer.parseInt(S[2]) ;
            L.get(a).add(new Pair(c,b)) ;
            RL.get(b).add(new Pair(c,a)) ;
        }
        int[] dA = dijkstra(L,s) ;
        int[] dB = dijkstra(L,d) ;
        int[] rdA = dijkstra(RL,s) ;
        int[] rdB = dijkstra(RL,d) ;
        if(debug)
        {
            System.out.println("L :"+L.toString()) ;
            System.out.println("RL :"+RL.toString()) ;
            System.out.println("dA :"+Arrays.toString(dA)) ;
            System.out.println("dB :"+Arrays.toString(dB)) ;
            System.out.println("rdA :"+Arrays.toString(rdA)) ;
            System.out.println("rdB :"+Arrays.toString(rdB)) ;
        }
        int ans = (int)1e8 ;
        for(int i=0;i<n;i++)
        {
            if(i!=s && i!=d && dA[i]!=(int)1e8 && dB[i]!=(int)1e8 && rdA[i]!=(int)1e8 && rdB[i]!=(int)1e8)
            {
                ans = Math.min(ans,dA[i]+dB[i]+rdA[i]+rdB[i]) ;
            }
        }
        System.out.println(ans==(int)1e9?-1:ans) ;
        
    }
    static int[] dijkstra(List<List<Pair>> L,int s)
    {
        int n = L.size() ;
        boolean[] visited = new boolean[n] ;
        PriorityQueue<Pair> pq = new PriorityQueue<Pair>() ;
        int[] dist = new int[n] ;
        Arrays.fill(dist,(int)1e9) ;
        dist[s] = 0 ;
        pq.add(new Pair(0,s)) ;
        if(debug){System.out.println("Source :"+s) ;}
        while(!pq.isEmpty())
        {
            
            Pair p = pq.poll() ;
            if(debug) {System.out.println("     Visited :"+p.b) ;}
            if(visited[p.b])
                continue ;
            else
            {
                visited[p.b] = true ;
                if(p.a>dist[p.b])
                    continue ;
                for(int i=0;i<L.get(p.b).size();i++)
                {
                    Pair v = L.get(p.b).get(i) ;
                    if(dist[p.b]+v.a<dist[v.b] && !visited[v.b])
                    {
                        dist[v.b] = dist[p.b]+v.a ;
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
    int a,b ;
    Pair(int a,int b)
    {
        this.a = a ;
        this.b = b ;
    }
    @Override
    public int compareTo(Pair p)
    {
        return this.a>p.a?1:(this.a<p.a?-1:0) ;
    }
    @Override
    public String toString()
    {
        return "["+a+","+(b+1)+"]" ;
    }
}