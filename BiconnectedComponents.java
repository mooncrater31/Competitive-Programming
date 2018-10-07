import java.util.* ;
import java.io.BufferedReader ;
import java.io.InputStreamReader ;

public class BiconnectedComponents
{
    private static final boolean debug = false ;
    private static final boolean debug2 = false ;
    static int[] dfs_low,dfs_num,dfs_parent ;
    static boolean[] visited ;
    static int children=0,root,dfs_counter=0 ;
    static List<List<Integer>> L = new ArrayList<List<Integer>>() ;
    static ArrayDeque<Pair> dq = new ArrayDeque<Pair>() ;
    static int even = 0 ,odd = 0 ;
    public static void main(String args[]) throws Exception
    {
        BufferedReader bro = new BufferedReader(new InputStreamReader(System.in)) ;
        String[] S = bro.readLine().split(" ") ;
        int n = Integer.parseInt(S[0]) ;
        int m = Integer.parseInt(S[1]) ;
        
        for(int i=0;i<n;i++)
        {
            L.add(new ArrayList<Integer>()) ;
        }
        for(int i=0;i<m;i++)
        {
            S = bro.readLine().split(" ") ;
            int a = Integer.parseInt(S[0]) ;
            int b = Integer.parseInt(S[1]) ;
            L.get(a).add(b) ;
            L.get(b).add(a) ;
        }
        dfs_low = new int[n] ;
        dfs_num = new int[n] ;
        dfs_parent = new int[n] ;
        visited = new boolean[n] ;
        for(int i=0;i<n;i++)
        {
            if(!visited[i])
                articulationPoints(i) ;
        }
        HashSet<Integer> H = new HashSet<Integer>() ;
        while(!dq.isEmpty())
        {
            Pair temp = dq.pop() ;
            if(debug) System.out.println("popped : "+temp.a+" "+temp.b) ;
            H.add(temp.a) ;
            H.add(temp.b) ;
        }
        if(H.size()%2==0)
            even++ ;
        else odd++ ;
        System.out.println(odd+" "+even) ;
    }
    static void articulationPoints(int u)
    {
        if(debug) System.out.println("u :"+u) ;
        visited[u] = true ;
        dfs_low[u] = dfs_num[u] = dfs_counter++ ;
        for(int i=0;i<L.get(u).size();i++)
        {
            int v = L.get(u).get(i) ;
            if(!visited[v])
            {
                dfs_parent[v] = u ;
                if(u==root) children++ ;
                dq.push(new Pair(u,v)) ;
                articulationPoints(v) ;
                if(debug) System.out.println("  u :"+u) ;
                
                if((u==root && children>1) ||(u!=root&&dfs_num[u]<=dfs_low[v]))
                {
                    HashSet<Integer> H = new HashSet<Integer>() ;
                    H.add(u) ;
                    H.add(v) ;
                    if(debug2) System.out.println("dq : "+dq.toString()) ;
                    // dq.pop() ;
                    // if(debug2) System.out.println("dq : "+dq.toString()) ;
                    Pair temp = dq.pop() ;
                    if(debug2) System.out.println("u v ="+u+" "+v) ;
                    while(temp.a!=u || temp.b!=v)
                    {
                        H.add(temp.a) ;
                        H.add(temp.b) ;
                        // count++ ;
                        if(debug) System.out.println(temp.a+" "+temp.b) ;
                        temp = dq.pop() ;
                    }
                    // dq.pop() ;
                    if(debug2) System.out.println("dq : "+dq.toString()) ;
                    
                    if(H.size() %2==0)
                        even++ ;
                    else odd++ ;
                }
                dfs_low[u] = Math.min(dfs_low[u],dfs_low[v]) ;
            }
            else if(dfs_parent[u]!=v && dfs_low[u]>dfs_num[v])
            {
                dfs_low[u] = dfs_num[v] ;
                dq.push(new Pair(u,v)) ;
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
    public String toString()
    {
        return "["+a+","+b+"]" ;
    }
}