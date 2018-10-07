import java.util.* ;
import java.io.BufferedReader ;
import java.io.InputStreamReader ;
class Height
{
    int h ;
    
}
public class SherlockAndHighways
{
    private static final boolean debug = false ;
    static int dfs_low[],dfs_num[],dfs_parent[],dfs_counter ;
    static boolean[] visited ;
    static List<List<Integer>> L = new ArrayList<List<Integer>>() ;
    static List<Pair> AB = new ArrayList<Pair>() ;
    static HashSet<Pair> H = new HashSet<Pair>() ;
    static void init(int n)
    {
        dfs_low = new int[n] ;
        dfs_num = new int[n] ;
        dfs_parent = new int[n] ;
        dfs_counter = 0;
        visited = new boolean[n] ;
    }
    public static void main(String args[]) throws Exception,java.io.IOException,InterruptedException
    {
        Thread t = new Thread(null,null,"TT",(int)1e8)
        {
            @Override
            public void run()
            {
                try
                {
                    BufferedReader bro = new BufferedReader(new InputStreamReader(System.in)) ;
                    String[] S = bro.readLine().split(" ") ;
                    int n = Integer.parseInt(S[0]) ;
                    init(n) ;
                    int m = Integer.parseInt(S[1]) ;
                    for(int i=0;i<n;i++) L.add(new ArrayList<Integer>()) ;
                    List<Pair> edges = new ArrayList<Pair>() ;
                    
                    for(int i=0;i<m;i++)
                    {
                        S = bro.readLine().split(" ") ;
                        int a = Integer.parseInt(S[0])-1 ;
                        int b = Integer.parseInt(S[1])-1;
                        L.get(a).add(b) ;
                        L.get(b).add(a) ;
                        edges.add(new Pair(a,b)) ;
                    }
                    articulationBridges(0) ;//Put articulationBridges in AB hashset and H hashset 
                    List<List<Integer>> whiteGraph = makeWhiteGraph(n,edges) ;//make a graph without ABs
                    if(debug) {System.out.println("whiteGraph size :"+whiteGraph.size()) ;}
                    // static void dfs_comp(int u,int[] comp,int curr,boolean[] vis,List<List<Integer>> wg)
                    int[] comp = new int[n] ;//Array representing which component a vertex is in.
                    boolean[] vis = new boolean[n] ;//Visited or not
                    int curr = 0 ;
                    for(int i=0;i<n;i++)
                    {
                        if(!vis[i])
                        {
                            vis[i] = true ;
                            dfs_comp(i,comp,curr++,vis,whiteGraph) ;//Component DFS
                        }
                    }//comp is filled now
                    List<List<Integer>> compGraph = makeCompGraph(curr,comp) ;//COnstruct the component graph
                    Height H = new Height() ;
                    H.h=0 ;
                    if(debug)
                    {
                        
                        System.out.println("compGraph :") ;
                        for(int i=0;i<compGraph.size();i++)
                            System.out.println(compGraph.get(i).toString()) ;
                    }
                    boolean[] visComp = new boolean[curr] ;
                    int d = diameter(0,H,compGraph,visComp) ;
                    System.out.println(AB.size()+" "+d) ;
                }
            catch(java.io.IOException e)
            {
                System.err.println("There was an error") ;
            }
            catch(StackOverflowError e)
            {
                System.err.println("There was an error") ;
            }
                
    
            }
        };
        t.start() ;
        t.join() ;
    }
    static List<List<Integer>> makeWhiteGraph(int n,List<Pair> edges)
    {
        List<List<Integer>> wg = new ArrayList<List<Integer>>(); 
        for(int i=0;i<n;i++)
            wg.add(new ArrayList<Integer>()) ;
        for(int i=0;i<edges.size();i++)
        {
            int x = edges.get(i).a ;
            int y = edges.get(i).b ;
            if(!H.contains(new Pair(x,y)))
            {
                wg.get(x).add(y) ;
                wg.get(y).add(x) ;
            }
        }
        return wg ;
    }
    static List<List<Integer>> makeCompGraph(int n_comps,int[] comp)
    {
        List<List<Integer>> cl = new ArrayList<List<Integer>>() ;
        for(int i=0;i<n_comps;i++)
        {
            cl.add(new ArrayList<Integer>()) ;
        }
        for(int i=0;i<AB.size();i++)
        {
            Pair p = AB.get(i) ;
            cl.get(comp[p.a]).add(comp[p.b]) ;
            cl.get(comp[p.b]).add(comp[p.a]) ;
        }
        return cl ;
    }
    static int diameter(int u,Height H,List<List<Integer>> cl,boolean[] visited)
    {
        List<Height> hl = new ArrayList<Height>() ;
        int maxH = 0,secMaxH = 0,maxD = 0 ;
        visited[u]=true;
        for(int i=0;i<cl.get(u).size();i++)
        {
            
            int v = cl.get(u).get(i) ;
            if(!visited[v]) 
            {
                hl.add(new Height()) ;
                maxD = Math.max(maxD,diameter(cl.get(u).get(i),hl.get(i),cl,visited)) ;
            }
        }
        if(hl.size()==0)
        {
            H.h= 0 ;
            return 0 ;
        }
        for(int i=0;i<hl.size();i++)
        {
            if(hl.get(i).h>maxH) {secMaxH=maxH;maxH = hl.get(i).h;} 
            else if(hl.get(i).h>secMaxH){secMaxH=hl.get(i).h;} //Important
        }
        H.h = maxH+1 ;
        if(hl.size()==1)
            return Math.max(maxH+1,maxD) ;
        else
            return Math.max(maxH+secMaxH+2,maxD) ;
    }
    
    static void dfs_comp(int u,int[] comp,int curr,boolean[] vis,List<List<Integer>> wg)
    {
        vis[u] = true ;
        comp[u] = curr ;    
        for(int i=0;i<wg.get(u).size();i++)
        {
            int v = wg.get(u).get(i) ;
            if(!vis[v])
            {
                dfs_comp(v,comp,curr,vis,wg) ;
            }
        }
    }
    static void articulationBridges(int u)
    {
        dfs_low[u] = dfs_num[u] = dfs_counter++ ;
        visited[u] = true ;
        for(int i=0;i<L.get(u).size();i++)
        {
            int v = L.get(u).get(i) ;
            if(!visited[v])
            {
                dfs_parent[v] = u ;
                articulationBridges(v) ;
                if(dfs_num[u]<dfs_low[v])
                {
                    AB.add(new Pair(u,v)) ;
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
        int a,b;
        Pair(int a,int b)
        {
            this.a = a ;
            this.b = b ;
        }
        @Override
        public boolean equals(Object o)
        {
            if(o instanceof Pair)
            {Pair p = (Pair)o ;return (p.a==this.a && p.b==this.b);}
            return false ;
        }
        @Override
        public int hashCode()
        {return 31*a+b;}
    }