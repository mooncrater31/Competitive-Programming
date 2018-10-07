import java.util.* ;
import java.io.BufferedReader ;
import java.io.InputStreamReader ;

public class RhezoAndHackerearth
{
    static int[] dfs_low,dfs_num,dfs_parent ;
    static int counter,root,rootC ;
    static boolean[] visited ;
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
                    dfs_low = new int[n] ;
                    dfs_num = new int[n] ;
                    dfs_parent = new int[n] ;
                    visited = new boolean[n] ;
                    int[][] edges = new int[m][2] ;
                    L = new ArrayList<List<Integer>>() ;
                    for(int i=0;i<n;i++)
                    {
                        L.add(new ArrayList<Integer>()) ;
                        
                    }
                    for(int i=0;i<m;i++)
                    {
                        S = bro.readLine().split(" ") ;
                        int a = Integer.parseInt(S[0])-1 ;
                        int b = Integer.parseInt(S[1])-1 ;
                        L.get(a).add(b) ;
                        L.get(b).add(a) ;
                        edges[i][0] = a ;
                        edges[i][1] = b ;
                        
                        
                    }
                    counter = 0 ;
                    for(int i=0;i<n;i++)
                    {
                        if(!visited[i])
                        {
                            // root = i ;
                            // rootC = 0 ;
                            articulationBridges(i) ;
                            
                        }
                    }
                    int q = Integer.parseInt(bro.readLine()) ;
                    for(int i=0;i<q;i++)
                    {
                        int idx = Integer.parseInt(bro.readLine()) ;
                        if(H.contains(new Pair(edges[idx-1][0],edges[idx-1][1])))
                            System.out.println("Unhappy") ;
                        else System.out.println("Happy") ;
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
                if(root==u)
                    rootC++ ;
                articulationBridges(v) ;
                if(dfs_low[v]>dfs_num[u])
                {
                    H.add(new Pair(v,u)) ;
                    H.add(new Pair(u,v)) ;
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
        if (o instanceof Pair)
        {
            Pair p = (Pair)o ;
            return this.a==p.a && this.b==p.b ;
        }
        return false ;
    }
    @Override
    public int hashCode()
    {
        return this.a*31+this.b ;
    }
}
