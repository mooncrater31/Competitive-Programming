import java.util.* ;
import java.io.BufferedReader ;
import java.io.InputStreamReader ;
//root should be visited, root and rootC should change inside the loop, ap[root] checking inside the loop, three int arrays and two boolean arrays
public class DestructiveMind
{
    private static final boolean debug = false ;
    static int[] dfs_low,dfs_num,dfs_parent ;
    static int root,rootC,counter ;
    static boolean[] visited ,ap;
    static List<List<Integer>> L ;
    public static void main(String args[]) throws Exception
    {
        BufferedReader bro = new BufferedReader(new InputStreamReader(System.in)) ;
        String[] S = bro.readLine().split(" ") ;
        int n = Integer.parseInt(S[0]) ;
        int m = Integer.parseInt(S[1]) ;
        dfs_low = new int[n] ;
        dfs_parent = new int[n] ;
        dfs_num = new int[n] ;
        visited = new boolean[n] ;
        ap = new boolean[n] ;
        root = 0 ;
        rootC = 0 ;
        counter = 0 ;
        L = new ArrayList<List<Integer>>() ;
        for(int i=0;i<n;i++)
        {
            L.add(new ArrayList<Integer>()) ;
        }
        for(int i=0;i<n;i++)
        {
            S = bro.readLine().split(" ") ;
            int a = Integer.parseInt(S[0])-1 ;
            int b = Integer.parseInt(S[1])-1 ;
            L.get(a).add(b) ;
            L.get(b).add(a) ;
            
        }
        for(int i=0;i<n;i++)
        {
            
            if(!visited[i])
            {
                root = i ;
                rootC = 0 ;
                
                articulationPoints(i) ;
                if(debug) System.out.println("root :"+root+" rootC :"+rootC) ;
            }
            ap[root] = rootC>1 ;
        }
        if(debug)
        {
            System.out.println("dfs_num :"+Arrays.toString(dfs_num)) ;
            System.out.println("dfs_low :"+Arrays.toString(dfs_low)) ;
        }
        int q = Integer.parseInt(bro.readLine()) ;
        for(int i=0;i<q;i++)
        {
            int val = Integer.parseInt(bro.readLine()) ;
            if(ap[val-1])
                System.out.println("Satisfied "+L.get(val-1).size()) ;
            else
                System.out.println("Not Satisfied") ;
        }
    }
    static void articulationPoints(int u)
    {
        dfs_low[u] = dfs_num[u] = counter++ ;
        visited[u] = true ;
        for(int i=0;i<L.get(u).size();i++)
        {
            int val = L.get(u).get(i) ;
            if(!visited[val])
            {
                dfs_parent[val] = u ;
                if(u==root)
                    rootC++ ;
                articulationPoints(val) ;
                if(dfs_num[u]<=dfs_low[val])
                {
                    ap[u] = true ;
                    
                }
                
                dfs_low[u] = Math.min(dfs_low[u],dfs_low[val]) ;
                
            }
            else if(val!=dfs_parent[u])
            {
                dfs_low[u] = Math.min(dfs_low[u],dfs_num[val]) ;
            }
        }
    }
}