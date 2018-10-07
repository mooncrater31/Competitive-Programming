import java.util.* ;
import java.io.BufferedReader ;
import java.io.InputStreamReader ;

public class MonkAndGraphG
{
    static boolean debug = false ;
    public static void main(String args[]) throws Exception
    {
        BufferedReader bro = new BufferedReader(new InputStreamReader(System.in)) ;
        String[] S = bro.readLine().split(" ") ;
        int n = Integer.parseInt(S[0]) ;
        int m = Integer.parseInt(S[1]) ;
        List<List<Integer>> L = new ArrayList<List<Integer>>() ;
        for(int i=0;i<n;i++) L.add(new ArrayList<Integer>()) ;
        for(int i=0;i<m;i++)
        {
            S = bro.readLine().split(" ") ;
            int a = Integer.parseInt(S[0])-1 ;
            int b = Integer.parseInt(S[1])-1 ;
            L.get(a).add(b) ;
            L.get(b).add(a) ;
        }
        S = bro.readLine().split(" ") ;
        int s = Integer.parseInt(S[0])-1 ;
        int d = Integer.parseInt(S[1])-1 ;
        int ans = solve(L,s,d) ;
        System.out.println(ans==(int)1e8?-1:ans) ;
        
    }
    static int[] bfs(List<List<Integer>> L,int source)
    {
        int n = L.size() ;
        int[] dist = new int[n] ;
        Arrays.fill(dist,(int)1e8) ;
        ArrayDeque<Integer> dq = new ArrayDeque<Integer>() ;
        boolean[] visited = new boolean[n] ;
        dq.add(source);
        dist[source]=0;
        while(!dq.isEmpty())
        {
            int v = dq.poll() ;
            if(!visited[v])
            {
                visited[v] = true ;
                for(int i=0;i<L.get(v).size();i++)
                {
                    int u = L.get(v).get(i) ;
                    if(!visited[u])
                    {
                        dq.add(u) ;
                        dist[u] = Math.min(dist[u],dist[v]+1) ;
                    }
                }
            }
        }
        return dist ;
    }
    static int solve(List<List<Integer>> L,int A,int B)
    {
        int n = L.size() ;
        int[] distA = bfs(L,A) ;
        int[] distB = bfs(L,B) ;
        if(debug)
        {
            System.out.println("distA :"+Arrays.toString(distA)) ;
            System.out.println("distB :"+Arrays.toString(distB)) ;
            
        }
        // int[] distCount = new int[(int)1e5+1] ;
        List<List<Integer>> distCount = new ArrayList<List<Integer>>() ;
        for(int i=0;i<n;i++) distCount.add(new ArrayList<Integer>()) ;//Min distance can't exceed n
        for(int i=0;i<n;i++)
        {
            if(distA[i]+distB[i]==distA[B] && distA[i]!=(int)1e8)
            {
                distCount.get(distA[i]).add(i) ;
            }
        }
        int ans = (int)1e8 ;
        for(int i=0;i<n;i++)
        {
            if(distCount.get(i).size()==1 && distCount.get(i).get(0)!=A && distCount.get(i).get(0)!=B)
            {
                ans = Math.min(ans,distCount.get(i).get(0)+1) ;
            }
        }
        return ans ;
        
    }
    
}