import java.util.* ;
import java.io.BufferedReader ;
import java.io.InputStreamReader ;

public class WalkToRemember
{
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
            L.get(a-1).add(b-1) ;
        }
        boolean[] visit = new boolean[n] ;
        ArrayDeque<Integer> DQ = new ArrayDeque<Integer>() ;
        for(int i=0;i<n;i++)
        {
            if(!visit[i])
            {
                dfs1(i,L,DQ,visit) ;
            }
        }
        List<List<Integer>> RL = transpose(L) ;
        int[] ans = dfs2(RL,DQ) ;
        for(int i=0;i<n;i++)
        {
            System.out.print(ans[i]+" ") ;
        }
    }
    
    static void dfs1(int s,List<List<Integer>> L,ArrayDeque<Integer> DQ,boolean[] visit)
    {
        visit[s] = true ;
        // int count =1 ;
        for(int i=0;i<L.get(s).size();i++)
        {
            int val = L.get(s).get(i) ;
            if(!visit[val])
            {
                dfs1(val,L,DQ,visit) ;
            }
            
        }
        DQ.push(s) ;
        // return count ;
    }
    // static void dfs1(int s,List<List<Integer>> L,ArrayDeque<Integer> DQ,boolean[] visit)
    // {
        // ArrayDeque<Integer> stck = new ArrayDeque<Integer>() ;
        // stck.push(s) ;
        // while(!stck.isEmpty())
        // {
            // int val = stck.pop() ;
            // visit[val] = true ;
            // for(int i=0;i<L.get(val).size();i++)
            // {
                // int toNode = L.get(val).get(i) ;
                // if(!visit[toNode])
                // {
                    // stck.push(toNode) ;
                // }
            // }
            // DQ.push(val) ;
        // }
    // }
    static List<List<Integer>> transpose(List<List<Integer>> L)
    {
        List<List<Integer>> RL = new ArrayList<List<Integer>>() ;
        int n = L.size() ;
        for(int i=0;i<n;i++)
        {
            RL.add(new ArrayList<Integer>()) ;
        }
        for(int i=0;i<n;i++)
        {
            for(int j=0;j<L.get(i).size();j++)
            {
                RL.get(L.get(i).get(j)).add(i) ;
            }
        }
        return RL ;
    }
    static int[] dfs2(List<List<Integer>> RL,ArrayDeque<Integer> DQ)
    {
        boolean[] visit = new boolean[RL.size()] ;
        int[] ans = new int[RL.size()] ;
        while(!DQ.isEmpty())
        {
            int val = DQ.pop() ;
            if(visit[val])
                continue ;
            else
            {
                ArrayDeque<Integer> dq = new ArrayDeque<Integer>() ;
                dfs1(val,RL,dq,visit) ;
                if(dq.size()==1)
                    ans[val] = 0 ;
                else
                {
                    while(!dq.isEmpty())
                    {
                        ans[dq.pop()] = 1 ;
                    }
                }
            }
            
        }
        return ans ;
    }
}