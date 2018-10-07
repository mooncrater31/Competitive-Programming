import java.util.* ;
import java.io.BufferedReader ;
import java.io.InputStreamReader ;

public class SmartTravelAgent
{
    private static final boolean debug = false ;
    public static void main(String args[]) throws Exception
    {
        BufferedReader bro = new BufferedReader(new InputStreamReader(System.in)) ;
        String[] S = bro.readLine().split(" ") ;
        int n = Integer.parseInt(S[0]) ;
        int m = Integer.parseInt(S[1]) ;
        List<List<Pair>> L = new ArrayList<List<Pair>>() ;
        for(int i=0;i<n;i++) L.add(new ArrayList<Pair>()) ;
        for(int i=0;i<m;i++)
        {
            S = bro.readLine().split(" ") ;
            int a = Integer.parseInt(S[0])-1 ;
            int b = Integer.parseInt(S[1])-1 ;
            int c = Integer.parseInt(S[2]) ;
            L.get(a).add(new Pair(b,c)) ;
            L.get(b).add(new Pair(a,c)) ;
        }
        if(debug){println("L :"+L.toString()) ;} 
        S = bro.readLine().split(" ") ;
        int source = Integer.parseInt(S[0])-1 ;
        int destin = Integer.parseInt(S[1])-1 ;
        int tourists = Integer.parseInt(S[2]) ;
        solve(L,source,destin,tourists) ;
    }
    static void solve(List<List<Pair>> L,int source,int destin,int tourists)
    {
        int n = L.size() ;
        // int min = Integer.MAX_VALUE ;
        boolean[] visited = new boolean[n] ;
        List<Integer> vertices = new ArrayList<Integer>() ;
        
        int min = dfs(source,L,vertices,visited,destin) ;
        if(debug) System.out.println("min :"+min) ;
        // Collections.reverse(vertices) ;
        for(int i=0;i<vertices.size();i++)
            System.out.print((vertices.get(i)+1)+" ") ;
        int trips = (int)Math.ceil((float)tourists/(min-1)) ;
        System.out.println("\n"+trips);
        
        
        
    }
    static void println(String S)
        {
            System.out.println(S) ;
        }
            static void print(String S)
        {
            System.out.print(S) ;
        }
    static int dfs(int v,List<List<Pair>> L,List<Integer> vertices,boolean[] visited,int destin)//a=vertex, b=distance
    {
        visited[v] = true ;
        if(debug) println("At :"+(v+1)) ;
        int n = L.size() ;
        int min = 0 ;
        List<List<Integer>> bigList = new ArrayList<List<Integer>>() ;
        List<Integer> currList = new ArrayList<Integer>();
        for(int i=0;i<n;i++)
            bigList.add(new ArrayList<Integer>()) ;
        
        for(int i=0;i<L.get(v).size();i++)
        {
            
            Pair p = L.get(v).get(i) ;
            if(debug) println("    Checking :"+(p.a+1)) ;
            
            if(!visited[p.a])
            {
                int currmin = Integer.MAX_VALUE ;
                boolean[] vis2 = new boolean[n] ;
                System.arraycopy(visited,0,vis2,0,visited.length);
                if(p.a==destin)
                {
                    currmin = p.b ;
                    bigList.get(i).add(p.a) ;
                }
                else currmin = dfs(p.a,L,bigList.get(i),vis2,destin) ;
                if(debug) {print("    Path min :"+currmin+" with path :") ;for(int j=0;j<bigList.get(i).size();j++) print((bigList.get(i).get(j)+1)+" ") ;println("");}
                if(currmin==-1)
                    continue ;
                if(currmin>p.b)
                {
                    currmin = p.b ;
                }
                // bigList.get(i).add(p.a) ;
                if(currmin>min)
                {
                    min = currmin ;
                    currList = bigList.get(i) ;
                }
            }
            
        }
        if(min==Integer.MAX_VALUE)
            return -1 ;
        vertices.add(v) ;
        vertices.addAll(currList) ;
        return min ;
        
    }
    
}
class Pair
{
    int a,b ;
    // List<Integer> L ;
    Pair(int a,int b)
    {
        this.a = a ;
        this.b = b ;
        // L = new ArrayList<Integer>() ;
    }
    @Override
    public String toString()
    {
        return "["+a+","+b+"]" ;
    }
    
}