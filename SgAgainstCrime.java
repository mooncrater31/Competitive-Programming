import java.util.* ;
import java.io.BufferedReader ;
import java.io.InputStreamReader ;

public class SgAgainstCrime
{
    static int[] dfs_low,dfs_num,dfs_parent ;
    static boolean[] visited ;
    static int counter ;
    static List<List<Integer>> L ;
    static HashSet<Pair> H = new HashSet<Pair>() ;
    
    public static void main(String args[]) throws Exception,InterruptedException,java.io.IOException
    {
        Thread t = new Thread(null,null,"TT",(int)1e7)
        {
            @Override
            public void run()
            {
                try
                {
                    BufferedReader bro = new BufferedReader(new InputStreamReader(System.in)) ;
                    String[] S = bro.readLine().split(" ") ;
                    int n = Integer.parseInt(S[0]) ;
                    int m = Integer.parseInt(S[1]) ;
                    int q = Integer.parseInt(S[2]) ;
                    dfs_low = new int[n] ;
                    dfs_num = new int[n] ;
                    dfs_parent = new int[n] ;
                    visited = new boolean[n] ;
                    L = new ArrayList<List<Integer>>() ;
                    for(int i=0;i<n;i++)
                        L.add(new ArrayList<Integer>()) ;
                    HashMap<Integer,Pair> map = new HashMap<Integer,Pair>() ;
                    for(int i=0;i<m;i++)
                    {
                        S = bro.readLine().split(" ") ;
                        int a = Integer.parseInt(S[0])-1 ;
                        int b = Integer.parseInt(S[1])-1 ;
                        int id = Integer.parseInt(S[2]) ;
                        L.get(a).add(b) ;
                        L.get(b).add(a) ;
                        map.put(id,new Pair(a,b)) ;
                        
                    }
                    for(int i=0;i<n;i++)
                    {
                        if(!visited[i])
                        {
                            articulationBridges(i) ;
                        }
                    }
                    for(int i=0;i<q;i++)
                    {
                        int idx = Integer.parseInt(bro.readLine()) ;
                        if(H.contains(map.get(idx)))
                            System.out.println("YES") ;
                        else System.out.println("no") ;
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
    static void articulationBridges(int u)
    {
        visited[u] = true ;
        dfs_low[u] = dfs_num[u] = counter++ ;
        for(int i=0;i<L.get(u).size();i++)
        {
            int v = L.get(u).get(i) ;
            if(!visited[v])
            {
                dfs_parent[v] = u ;
                articulationBridges(v) ;
                if(dfs_low[v]>dfs_num[u])
                {
                    H.add(new Pair(u,v)) ;
                    H.add(new Pair(v,u)) ;
                }
                dfs_low[u] = Math.min(dfs_low[u],dfs_low[v]) ;
            }
            else if(dfs_parent[u]!=v)
            {
                dfs_low[u] = Math.min(dfs_low[u],dfs_num[v]) ;
            }
        }
    }
    
}
class Pair
{
    int a,b ;
    Pair(int a,int b)
    {
        this.a = a ;
        this.b = b ;
    }
    @Override
    public boolean equals(Object o)
    {
        if( o instanceof Pair)
        {
            Pair p = (Pair)o ;
            return this.a==p.a && this.b==p.b ;
        }
        return false ;
    }
    public int hashCode()
    {
        return 31*a+b ;
    }
}