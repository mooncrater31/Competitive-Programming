import java.util.* ;
import java.io.BufferedReader ;
import java.io.InputStreamReader ;

public class MrPresident
{
    private static final boolean debug = false ;
    public static void main(String args[]) throws Exception
    {
        BufferedReader bro = new BufferedReader(new InputStreamReader(System.in)) ;
        
        String temp = bro.readLine() ;
        try
        {
            
            String[] S = temp.split(" ") ;
            long n = Long.parseLong(S[0]) ;
            long m = Long.parseLong(S[1]) ;
            long k = Long.parseLong(S[2]) ;
            List<List<Triplet>> AdjList = new ArrayList<List<Triplet>>() ;
            List<Triplet> Edges = new ArrayList<Triplet>() ;
            for(long i=0;i<n;i++) AdjList.add(new ArrayList<Triplet>()) ;
            
            for(long i=0;i<m;i++)
            {
                S = bro.readLine().split(" ") ;
                long a = Long.parseLong(S[0])-1 ;
                long b = Long.parseLong(S[1])-1 ;
                long c = Long.parseLong(S[2]) ;
                Edges.add(new Triplet(c,a,b)) ;
                AdjList.get(a).add(new Triplet(c,b,0)) ;
                AdjList.get(b).add(new Triplet(c,a,0)) ;
            }
            if(!dfs(AdjList)) 
                System.out.println("-1") ;
            else
            {
                System.out.println(ans(Edges,k,n)) ;
            }
        }
        catch(Exception e)
        {
            System.out.println("temp :"+temp) ;
        }
    }
    static long ans(List<Triplet> E,long k,long n)
    {
        Collections.sort(E) ;
        List<Triplet> L = new ArrayList<Triplet>() ;
        long[] p = new long[n] ;
        long[] rank = new long[n] ;
        for(int i=0;i<n;i++) p[i] = i ;
        long mst = 0 ;
        for(int i=0;i<E.size();i++)
        {
            // L.add(E.get(i)) ;
            Triplet t = E.get(i) ;
            if(!isSameSet(t.b,t.c,p))
            {
                L.add(t) ;
                mst+=t.a ;
                unionSet(t.b,t.c,p,rank) ;
                if(debug)
                {
                    System.out.println("  "+(t.b+1)+" "+(t.c+1)+" are put longo the same set.") ;
                    // System.out.prlongln("p :"+Arrays.toString(p)) ;
                    // for(long j=0;j<n;j++) System.out.prlong((p[j]+1)+" ") ;
                }
            }
            
        }
        long idx = L.size()-1 ;
        long ctr = 0 ;
        if(debug) 
        {
            System.out.println("mst :"+mst+" L.size() :"+L.size())  ;
            System.out.println("L last :"+L.get(L.size()-1)) ;
        }
        while(idx>=0 && mst>k)
        {
            mst = mst-L.get(idx).a+1 ;
            if(debug) System.out.println("      mst :"+mst) ;
            idx-- ;
            ctr++ ;
        }
        if(debug) System.out.println("Final ctr :"+ctr) ;
        if(mst>k) return -1 ;
        
        return ctr ;
            
    }
    static long findSet(long v,long[] p)
    {
        return p[v]==v?v: (p[v] = findSet(p[v],p)) ;
    }
    static void unionSet(long x,long y,long[] p,long[] rank)
    {
        
        if(!isSameSet(x,y,p))
        {
            long xp = findSet(x,p),yp = findSet(y,p) ;
            if(rank[xp]>rank[yp]) p[yp] = xp ;
            else if(rank[xp]<rank[yp]) p[xp] = yp ;
            else 
            {
                p[xp] = yp ;
                rank[yp]++ ;
            }
        }
    }
    static boolean isSameSet(long x,long y,long[] p)
    {
        return findSet(x,p)==findSet(y,p) ;
    }
    static boolean dfs(List<List<Triplet>> L)
    {
        long n = L.size() ;
        boolean[] visited = new boolean[n] ;
        ArrayDeque<Long> dq = new ArrayDeque<Long>() ;
        dq.push(0) ;
        while(!dq.isEmpty())
        {
            long val = dq.pop() ;
            if(!visited[val])
            {
                visited[val] = true ;
                for(long i=0;i<L.get(val).size();i++)
                {
                    long neigh = L.get(val).get(i).b ;
                    if(!visited[neigh])
                        dq.push(neigh) ;
                }
            }
        }
        for(int i=0;i<n;i++)
            if(!visited[i])
                return false ;
            
        return true ;
    }
    
}
class Triplet implements Comparable<Triplet>
{
    long a,b,c ;
    Triplet(long a,long b,long c)
    {
        this.a = a;
        this.b = b ;
        this.c = c ;
    }
    @Override
    public long compareTo(Triplet t)
    {
        return this.a>t.a?1:(this.a<t.a?-1:0) ;
    }
    @Override
    public String toString()
    {
        return "["+this.a+","+this.b+","+this.c+"]" ;
    }
}
