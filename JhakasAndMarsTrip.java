import java.util.* ;
import java.io.BufferedReader ;
import java.io.InputStreamReader ;

public class JhakasAndMarsTrip
{
    private static final boolean debug = false ;
    public static void main(String args[]) throws Exception
    {
        BufferedReader bro = new BufferedReader(new InputStreamReader(System.in)) ;
        int T = Integer.parseInt(bro.readLine()) ;
        for(int t=0;t<T;t++)
        {
            String[] S = bro.readLine().split(" ") ;
            int n = Integer.parseInt(S[0]) ;
            int k = Integer.parseInt(S[1]) ;
            List<Triplet> points = new ArrayList<Triplet>() ;
            for(int i=0;i<n;i++)
            {
                S = bro.readLine().split(" ") ;
                int a = Integer.parseInt(S[0]) ;
                int b = Integer.parseInt(S[1]) ;
                int c = Integer.parseInt(S[2]) ;
                points.add(new Triplet(a,b,c)) ;
            }
            List<Triplet> L = new ArrayList<Triplet>() ;
            for(int i=0;i<n;i++)
            {
                for(int j=i+1;j<n;j++)
                {
                    long dis = dist(i,j,points) ;
                    if(debug) System.out.println("  dis b/w:"+(i+1)+" and "+(j+1)+" : "+dis) ;
                    L.add(new Triplet(dis,i,j)) ;
                }
            }
            System.out.println(kruskal(L,k,n)) ;
        }
    }
    static long dist(int i,int j,List<Triplet> L)
    {
        int ax = (int)L.get(i).a ;
        int ay = L.get(i).b ;
        int az = L.get(i).c ;
        int bx = (int)L.get(j).a ;
        int by = L.get(j).b ;
        int bz = L.get(j).c ;
        return (ax-bx)*(ax-bx)+(ay-by)*(ay-by)+(az-bz)*(az-bz) ;
    }
    static long kruskal(List<Triplet> L,int k,int n)
    {
        Collections.sort(L) ;
        if(debug) System.out.println("L :"+ L.toString()) ;
        // int n = L.size() ;
        int[] p = new int[n] ;
        int[] rank = new int[n] ;
        for(int i=0;i<n;i++)p[i]=i;
        long count=0;
        int idx = - 1;
        for(int i=0;i<L.size();i++)
        {
            Triplet t = L.get(i) ;
            if(!isSameSet(t.b,t.c,p))
            {
                unionSet(t.b,t.c,p,rank) ;
                count++ ;
            }
            if(count>n-k)
            {
                idx = i ;
                break ;
            }
        }
        return L.get(idx).a ;
    }
    static void unionSet(int a,int b,int[] p,int[] rank)
    {
        int x = findSet(a,p),y = findSet(b,p) ;
        if(x!=y)
        {
            if(rank[x]>rank[y]) p[y]=x ;
            else 
            {
                p[x] = y ;
                if(rank[x]==rank[y]) rank[y]++ ;
                
            }
        }
    }
    static int findSet(int x,int[] p)
    {
        return (p[x]==x)?x: (p[x] = findSet(p[x],p))     ;
    }
    static boolean isSameSet(int x,int y,int[] p)
    {
        return findSet(x,p)==findSet(y,p) ;
    }
}
class Triplet implements Comparable<Triplet>
{
    long a ;
    int b,c ;
    Triplet(long a,int b,int c)
    {
        this.a = a ;
        this.b = b ;
        this.c = c ;
        
    }
    @Override 
    public int compareTo(Triplet t)
    {
        return this.a>t.a?1:(this.a<t.a?-1:0) ;
    }
    @Override
    public String toString()
    {return "["+a+","+b+","+c+"]";}
}