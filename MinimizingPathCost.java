import java.util.* ;
import java.io.BufferedReader ;
import java.io.InputStreamReader ;

public class MinimizingPathCost
{
    private static final boolean debug = false ;
    public static void main(String args[]) throws Exception
    {
        BufferedReader bro = new BufferedReader(new InputStreamReader(System.in)) ;
        String[] S = bro.readLine().split(" ") ;
        int n = Integer.parseInt(S[0]) ;
        int m = Integer.parseInt(S[1]) ;
        HashMap<String,Integer> H = new HashMap<String,Integer>() ;
        // List<String> sl = new ArrayList<String>() ;
        S = bro.readLine().split(" ") ;
        for(int i=0;i<n;i++)
        {
            
            // sl.add(new String(S[i])) ;
            H.put(S[i],i) ;
        }
        // Iterator it = H.iterator() ;
        if(debug)
        {
            for(Map.Entry<String,Integer> entry : H.entrySet())
            {
                String key = entry.getKey() ;
                Integer i = entry.getValue() ;
                System.out.println(key+" "+i) ;
            }
        }
        int[][] adj = new int[n][n] ;
        for(int i=0;i<n;i++)
            Arrays.fill(adj[i],(int)1e8) ;
        for(int i=0;i<m;i++)
        {
            S = bro.readLine().split(" ") ;
            int a = H.get(S[0]) ;
            int b = H.get(S[1]) ;
            int d = Integer.parseInt(S[2]) ;
            adj[a][b] = d ;
            adj[b][a] = d ;
        }
        adj = apsp(adj) ;
        int q = Integer.parseInt(bro.readLine()) ;
        for(int i=0;i<q;i++)
        {
            S = bro.readLine().split(" ") ;
            int a = H.get(S[0]) ;
            int b = H.get(S[1]) ;
            System.out.println(adj[a][b]) ;
        }
        
    }
    static int[][] apsp(int[][] adj)
    {
        int n = adj.length ;
        for(int k=0;k<n;k++)
            for(int i=0;i<n;i++)
                for(int j=0;j<n;j++)
                    adj[i][j] = Math.min(adj[i][j],adj[i][k]+adj[k][j]) ;
        return adj ;
            
    }
}