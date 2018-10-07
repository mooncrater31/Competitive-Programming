import java.util.* ;
import java.io.BufferedReader ;
import java.io.InputStreamReader ;

public class TravellingTom
{
    private static final boolean debug = false ;
    public static void main(String args[]) throws Exception
    {
        BufferedReader bro = new BufferedReader(new InputStreamReader(System.in)) ;
        String[] S = bro.readLine().split(" ") ;
        int n = Integer.parseInt(S[0]) ;
        int m = Integer.parseInt(S[1]) ;
        int k = Integer.parseInt(S[2]) ;
        long[] K = new long[k] ;
        // List<Triplet> Edges = new ArrayList<Triplet>() ;
        List<List<Integer>> CL = new ArrayList<List<Integer>>() ;
        List<List<Triplet>> AdjList = new ArrayList<List<Triplet>>() ;
        for(int i=0;i<n;i++) { AdjList.add(new ArrayList<Triplet>()) ;}
        for(int i=0;i<m;i++){CL.add(new ArrayList<Integer>()) ;} 
        S = bro.readLine().split(" ") ;
        for(int i=0;i<k;i++)
            K[i] = Long.parseLong(S[i]) ;
        for(int i=0;i<m;i++)
        {
            S = bro.readLine().split(" ") ;
            int a = Integer.parseInt(S[0])-1 ;
            int b = Integer.parseInt(S[1])-1 ;
            int count = Integer.parseInt(S[2]) ;
            // List<Integer> L = new ArrayList<Integer>() ;
            for(int j=0;j<count;j++)
                CL.get(i).add(Integer.parseInt(S[3+j])-1) ;
            
            
            AdjList.get(a).add(new Triplet(b,i,0)) ;
            AdjList.get(b).add(new Triplet(a,i,0)) ;
        }
        if(!dfs(AdjList)) System.out.println("-1") ;
        else
        {
            System.out.println(solve(AdjList,CL,K)) ;
        }
        
    }
    static boolean checkValidity(List<List<Integer>> CL,int x,boolean[] tokenAllowed)
    {
        for(int i=0;i<CL.get(x).size();i++)
        {
            if(!tokenAllowed[CL.get(x).get(i)])
                return false ;
        }
        return true ;
    }
    static long solve(List<List<Triplet>> L,List<List<Integer>> CL,long[] C)
    {
        long ans = 0 ;
        boolean[] tokenAllowed = new boolean[C.length] ;
        Arrays.fill(tokenAllowed,true) ;
        for(int k=C.length-1;k>=0;k--)
        {
            int count = 0 ;
            tokenAllowed[k]=false ;
            boolean[] visited = new boolean[L.size()] ;
            ArrayDeque<Integer> dq = new ArrayDeque<Integer>() ;
            dq.push(0) ;
            List<Integer> Edges = new ArrayList<Integer>() ;
            while(!dq.isEmpty())
            {
                int val = dq.pop() ;
                if(!visited[val] )//&& checkValidity(CL,t.b,tokenAllowed)) //a=node number, b=Edge number
                {
                    count++ ;
                    visited[val] = true ;
                    // Edges.add(t.b) ;
                    for(Triplet tr:L.get(val))
                    {
                        if(!visited[tr.a] && checkValidity(CL,tr.b,tokenAllowed))
                            dq.push(tr.a) ;
                    }
                }
            }
            if(count!=L.size())
            {
                ans+=C[k];
                tokenAllowed[k]=true ;
            }
            if(debug){ System.out.println("For k= "+(k+1)+" Edges:"+Edges.toString()) ;}
        }
        if(debug) System.out.println("tokenAllowed :"+Arrays.toString(tokenAllowed)) ;
        return ans ;
    }
    
    static boolean dfs(List<List<Triplet>> L)
    {
        int n = L.size() ;
        boolean[] visited = new boolean[n] ;
        ArrayDeque<Integer> dq = new ArrayDeque<Integer>() ;
        dq.push(0) ;
        while(!dq.isEmpty())
        {
            int val = dq.pop() ;
            if(!visited[val])
            {
                visited[val] = true ;
               for(int i=0;i<L.get(val).size();i++)
               {
                   Triplet u = L.get(val).get(i) ;
                   if(!visited[u.a])
                       dq.push(u.a) ;
               }
            }
        }
        for(int i=0;i<n;i++) if(!visited[i]) return false ;
        return true ;
    }
}
class Triplet implements Comparable<Triplet>
{
    int b,c ;
    int a ;
    Triplet(int a,int b,int c)
    {
        this.a =a ;
        this.b = b ;
        this.c = c;
    }
    @Override
    public int compareTo(Triplet t)
    {
        return (this.a>t.a?1:(this.a<t.a?-1:0)) ;
    }
    @Override
    public String toString()
    {
        return "["+a+","+(b+1)+","+(c+1)+"]" ;
    }
}