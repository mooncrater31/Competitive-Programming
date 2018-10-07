import java.util.* ;
import java.io.BufferedReader ;
import java.io.InputStreamReader ;

public class kosaraju
{
    private static final boolean debug = false ;
    static int odd = 0,even = 0 ;
    public static void main(String args[]) throws Exception
    {
        BufferedReader bro = new BufferedReader(new InputStreamReader(System.in)) ;
        String[] S = bro.readLine().split(" ") ;
        int n = Integer.parseInt(S[0]) ;
        int m = Integer.parseInt(S[1]) ;
        List<List<Integer>> L = new ArrayList<List<Integer>>() ;
        for(int i=0;i<n;i++)
        {
            L.add(new ArrayList<Integer>()) ;
        }
        for(int i=0;i<m;i++)
        {
            S = bro.readLine().split(" ") ;
            int a = Integer.parseInt(S[0]) ;
            int b = Integer.parseInt(S[1]) ;
            L.get(a-1).add(b-1) ;//directed
            
        }
        ArrayDeque<Integer> DQ = new ArrayDeque<Integer>() ;
        boolean[] visited = new boolean[n] ;
        for(int i=0;i<n;i++)
        {
            if(!visited[i])
            {
                dfs1(i,L,visited,DQ) ;
            }
        }
        List<List<Integer>> RL = reverse(L) ;
        if(debug)
        {
            System.out.println("DQ :"+DQ.toString()) ;
        }
        
        dfs2(RL,DQ) ;
        if(debug)
        {
            System.out.println("even :"+even+" odd :"+odd) ;
        }
        System.out.println(odd-even) ;
    }
    static List<List<Integer>> reverse(List<List<Integer>> L)
    {
        List<List<Integer>> RL = new ArrayList<List<Integer>>() ;
        for(int i=0;i<L.size();i++)
        {
            RL.add(new ArrayList<Integer>()) ;
            
        }
        for(int i=0;i<L.size();i++)
        {
            for(int j=0;j<L.get(i).size();j++)
            {
                RL.get(L.get(i).get(j)).add(i) ;
            }
        }
        return RL ;
    }
    static int dfs1(int s,List<List<Integer>> L,boolean[] visited,ArrayDeque<Integer> DQ)
    {
        //ArrayDeque<Integer> DQ = new ArrayDeque<Integer>() ;
        int count = 1 ;
        if(debug) System.out.println("Visited :"+(s+1));
        visited[s] = true ;
        for(int i=0;i<L.get(s).size();i++)
        {
            int val = L.get(s).get(i) ;
            if(!visited[val])
            {
                count+= dfs1(val,L,visited,DQ) ;
            }
            
        }
        if(debug) System.out.println(s+" is added to DQ") ;
        DQ.push(s) ;
        return count ;
    }
    static void dfs2(List<List<Integer>> RL,ArrayDeque<Integer> DQ)
    {
        boolean[] visited = new boolean[RL.size()] ;
        while(!DQ.isEmpty())
        {
            int el = DQ.pop() ;
            if(debug) System.out.println((el+1)+" is visited.") ;
            if(visited[el])
                continue ;
            else
            {
                int count = dfs1(el,RL,visited,new ArrayDeque<Integer>()) ;
                if(count%2==0)
                    even+=count ;
                else odd+=count ;
            }
        }
    }
    
}