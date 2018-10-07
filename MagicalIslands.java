import java.util.* ;
import java.io.BufferedReader ;
import java.io.InputStreamReader ;

public class MagicalIslands
{
    private static final boolean debug = false ;
    public static void main(String args[]) throws Exception,InterruptedException,java.io.IOException
    {
        Thread t = new Thread(null,null,"TT",10000000)
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
                        L.get(a-1).add(b-1) ;
                    }
                    boolean[] visited = new boolean[n] ;
                    ArrayDeque<Integer> DQ = new ArrayDeque<Integer>() ;
                    for(int i=0;i<n;i++)
                    {
                        if(!visited[i])
                            dfs1(i,visited,L,DQ) ;
                    }
                    if(debug)
                        System.out.println(DQ.toString()) ;
                    List<List<Integer>> RL = transpose(L) ;
                    System.out.println(dfs2(RL,DQ)) ;
                }
                catch(java.io.IOException e)
                {
                    
                }
                catch(StackOverflowError e)
                {
                    
                }
            }
        };
        t.start() ;
        t.join() ;
    }
    static List<List<Integer>> transpose(List<List<Integer>> L)
    {
        List<List<Integer>> RL = new ArrayList<List<Integer>>();
        for(int i=0;i<L.size();i++)
        {
            RL.add(new ArrayList<Integer>()) ;
        }
        for(int i=0;i<L.size();i++)
        {
            for(int j=0;j<L.get(i).size();j++)
                RL.get(L.get(i).get(j)).add(i) ;
        }
        return RL ;
    }
    static void dfs1(int s,boolean[] visited,List<List<Integer>> L,ArrayDeque<Integer> DQ)
    {
        visited[s] = true ;
        for(int i=0;i<L.get(s).size();i++)
        {
            int val = L.get(s).get(i) ;
            if(!visited[val])
            {
                dfs1(val,visited,L,DQ) ;
            }
        }
        if(debug) System.out.println(s+" is pushed into DQ.") ;
        DQ.push(s) ;
    }
    static int dfs2(List<List<Integer>> RL,ArrayDeque<Integer> DQ)
    {
        int n = RL.size() ;
        boolean[] visited = new boolean[n] ;
        int maxSize = 0 ;
        while(!DQ.isEmpty())
        {
            int val = DQ.pop() ;
            if(visited[val])
                continue ;
            else
            {
                
                ArrayDeque<Integer> dq = new ArrayDeque<Integer>() ;
                dfs1(val,visited,RL,dq) ;
                maxSize = dq.size()>maxSize?dq.size():maxSize ;
            }
        }
        return maxSize ;
    }
}