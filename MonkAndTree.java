import java.util.* ;
import java.io.BufferedReader ;
import java.io.InputStreamReader ;

public class MonkAndTree
{
    public static void main(String args[]) throws Exception, InterruptedException, java.io.IOException
    {
        Thread t = new Thread(null,null,"TT",(int)1e9)
        {
            @Override
            public void run()
            {
                try
                {
                    BufferedReader bro = new BufferedReader(new InputStreamReader(System.in)) ;
        
                    int T = Integer.parseInt(bro.readLine()) ;
                    for(int t=0;t<T;t++)
                    {
                        String[] S = bro.readLine().split(" ") ;
                        int n = Integer.parseInt(S[0]) ;
                        int m = Integer.parseInt(S[1]) ;
                        List<Pair> edges = new ArrayList<Pair>() ;
                        List<List<Pair>> L = new ArrayList<List<Pair>>() ;
                        HashSet<Integer> H = new HashSet<Integer>() ;
                        for(int i=0;i<n;i++) L.add(new ArrayList<Pair>()) ;
                        
                        for(int i=0;i<m;i++)
                        {
                            S= bro.readLine().split(" ") ;
                            int a = Integer.parseInt(S[0])-1 ;
                            int b = Integer.parseInt(S[1])-1 ;
                            edges.add(new Pair(a,b,Math.abs(a-b))) ;
                            H.add(i) ;
                            L.get(a).add(new Pair(i,b,0)) ;
                            L.get(b).add(new Pair(i,a,0)) ;
                        }
                        System.out.println(solve(L,H,edges)) ;
                    }
                }
                catch(java.io.IOException e)
                {
                    
                }
            }
        };
        t.start() ;
        t.join() ;
    }
    static void prim(List<List<Pair>> L,HashSet<Integer> H,int source,boolean[] visited)
    {
        PriorityQueue<Pair> pq = new PriorityQueue<Pair>() ;    
        // boolean[] visited = new boolean[L.size()] ;
        pq.add(new Pair(0,source,-1)) ;//Distance,vertex,edge number
        while(!pq.isEmpty())
        {
            Pair p = pq.poll() ;
            if(!visited[p.b])
            {
                visited[p.b] = true ;
                H.remove(p.c) ;
                for(int i=0;i<L.get(p.b).size();i++)
                {
                    Pair temp = L.get(p.b).get(i) ;
                    if(!visited[temp.b])
                    {
                        // H.remove(temp.a) ;
                        pq.add(new Pair(Math.abs(temp.b-p.b),temp.b,temp.a)) ;
                    }
                }
            }
        }
    }
    static int solve(List<List<Pair>> L,HashSet<Integer> H,List<Pair> edges)
    {
        boolean[] visited = new boolean[L.size()] ;
        int comp = 0 ;
        int ans = 0 ;
        for(int i=0;i<L.size();i++)
        {
            if(!visited[i])
            {
                comp++ ;
                // dfs(L,visited,i) ;
                prim(L,H,i,visited) ;
            }
        }
        List<Integer> ansL = new ArrayList<Integer>(H) ;
        for(int i=0;i<ansL.size();i++)
        {
            ans+=edges.get(ansL.get(i)).c ;
        }
        return ans+comp-1 ;
    }   
}
class Pair implements Comparable<Pair>
{
    int a,b,c ;//a is the weight and b is the index number
    Pair(int a,int b,int c)
    {
        this.a =a ;
        this.b = b;
        this.c = c ;
    }
    @Override
    public int compareTo(Pair p)
    {
        return this.a>p.a?-1:(this.a<p.a?1:0) ;
    }
    
}