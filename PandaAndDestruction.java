import java.util.* ;
import java.io.BufferedReader ;
import java.io.InputStreamReader ;

public class PandaAndDestruction
{
    private static final boolean debug = false ;
    public static void main(String args[]) throws Exception,InterruptedException,java.io.IOException
    {
        BufferedReader bro = new BufferedReader(new InputStreamReader(System.in)) ;
        String[] S = bro.readLine().split(" ") ;
        int n = Integer.parseInt(S[0]) ;
        int m = Integer.parseInt(S[1]) ;
        List<List<Integer>> L = new ArrayList<List<Integer>>() ;
        List<HashSet<Integer>> HL = new ArrayList<HashSet<Integer>>() ;
        for(int i=0;i<n;i++) {L.add(new ArrayList<Integer>()) ; HL.add(new HashSet<Integer>()) ;}
        HashSet<Pair> bigH = new HashSet<Pair>() ;
        for(int i=0;i<m;i++)
        {
            S = bro.readLine().split(" ") ;
            int a = Integer.parseInt(S[0])-1 ;
            int b = Integer.parseInt(S[1])-1 ;
            L.get(a).add(b) ;
            L.get(b).add(a) ;
            HL.get(a).add(b) ;
            HL.get(b).add(a) ;
            bigH.add(new Pair(a,b)) ;
            bigH.add(new Pair(b,a)) ;
            
        }
        int[] conns = countConns(L) ;
        
        List<Pair> cl = new ArrayList<Pair>() ;
        for(int i=0;i<n;i++){ cl.add(new Pair(conns[i],i));} 
        Collections.sort(cl) ;
        if(debug){System.out.println("conns :"+Arrays.toString(conns)+" cl :"+cl.toString()) ;}
        int count = 0 ;
        for(int i=n-1;i>=0 && !bigH.isEmpty();i--)
        {
            int idx = cl.get(i).b ;
            if(debug) System.out.println("idx :"+idx) ;
            if(HL.get(idx).size()>0) count++ ;
            for(int j=0;j<L.get(idx).size();j++)
            {
                int ne = L.get(idx).get(j) ;
                if(HL.get(idx).contains(ne))
                {
                    HL.get(idx).remove(ne) ;
                    HL.get(ne).remove(idx) ;
                    bigH.remove(new Pair(ne,idx)) ;
                    bigH.remove(new Pair(idx,ne)) ;
                    if(debug) {System.out.println(" bigH.size :"+bigH.size());}
                
                }
                
            }
            // count++ ;
        }
        System.out.println(count) ;
    }
    static int[] countConns(List<List<Integer>> L)
    {
        int n = L.size() ;
        int[] conns = new int[n] ;
        boolean[] visited = new boolean[n] ;
        for(int i=0;i<n;i++)
        {
            if(!visited[i])
            {
                dfs(i,L,visited,conns) ;
            }
        }
        return conns ;
    }
    static void dfs(int x,List<List<Integer>> L,boolean[] visited,int[] conns)
    {
        visited[x] = true ;
        for(int i=0;i<L.get(x).size();i++)
        {
            int ne = L.get(x).get(i) ;
            if(!visited[ne])
            {
                conns[ne]++ ;
                dfs(ne,L,visited,conns) ;
            }
            else conns[ne]++ ;
        }
    }
}
class Pair implements Comparable<Pair>
{
    int a,b ;
    Pair(int a,int b)
    {this.a=a;this.b=b;}
    @Override
    public int compareTo(Pair p){return this.a>p.a?1:(this.a<p.a?-1:0) ;}
    @Override
    public int hashCode(){return 31*a+b ;} ;
    @Override
    public boolean equals(Object o){if((o instanceof Pair)) return this.a==((Pair)o).a && this.b==((Pair)o).b; else return false;}
    @Override
    public String toString(){return "["+a+","+(b+1)+"]" ;}
}