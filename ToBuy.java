import java.util.* ;
import java.io.BufferedReader ;
import java.io.InputStreamReader ;
/*
1
7 10
1 2 5
1 4 3
1 5 12
1 6 5
4 5 1
5 6 2
5 3 1
3 6 16
4 7 1
2 4 1
5
1 6
2 4
3 5
3 6
1 2
*/
public class ToBuy
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
            int m = Integer.parseInt(S[1]) ;
            boolean[] canUseEdge = new boolean[m] ;
            List<List<Triplet>> L = new ArrayList<List<Triplet>>() ;
            for(int i=0;i<n;i++)L.add(new ArrayList<Triplet>()) ;
            List<Triplet> edges = new ArrayList<Triplet>() ;
            HashMap<Triplet,Integer> HM = new HashMap<Triplet,Integer>() ;
            for(int i=0;i<m;i++)
            {
                S = bro.readLine().split(" ") ;
                int a = Integer.parseInt(S[0])-1 ;
                int b = Integer.parseInt(S[1])-1 ;
                int c = Integer.parseInt(S[2]) ;
                L.get(a).add(new Triplet(c,b,0,i)) ;
                L.get(b).add(new Triplet(c,a,0,i)) ;
                edges.add(new Triplet(c,a,b,i)) ;
                HM.put(new Triplet(c,a,b,i),i) ;
                HM.put(new Triplet(c,b,a,i),i) ;
            }
            if(debug) System.out.println("HM :"+HM.toString()) ;    
            Collections.sort(edges) ;
            if(debug) System.out.println("edges :"+edges.toString()) ;
            boolean[] ansE= new boolean[m] ;
            Arrays.fill(canUseEdge,true) ;
            for(int i=m-1;i>=0;i--)
            {
                Triplet e = edges.get(i) ;
                canUseEdge[e.d] = false ;
                ansE[e.d]=dfs(L,e.b,e.c,canUseEdge) ;
            }
            if(debug) System.out.println("ansE :"+Arrays.toString(ansE)) ;
            int q = Integer.parseInt(bro.readLine()) ;
            int ans = 0 ;
            for(int i=0;i<q;i++)
            {
                S = bro.readLine().split(" ") ;
                int a = Integer.parseInt(S[0])-1 ;
                int b = Integer.parseInt(S[1])-1 ;
                if(HM.containsKey(new Triplet(0,a,b,0)))
                {
                    int idx = HM.get(new Triplet(0,a,b,0)) ;
                    if(debug) System.out.println("HM contains "+a+","+b+" : "+ HM.containsKey(new Triplet(0,a,b,0))+" at idx :"+idx) ;
                    if(!ansE[idx])ans++ ;
                }
            }
            int g = gcd(ans,q) ;
            if(debug) System.out.println(" ans :"+ans +" q :"+q+" g :"+g) ;
            System.out.println((ans/g)+"/"+(q/g)) ;
        }
    }
    static int gcd(int a,int b)
    {
        if(a==0) return b ;
        return gcd(b%a,a) ;
    }
    static boolean dfs(List<List<Triplet>> L, int s,int e,boolean[] canUseEdge)
    {
        ArrayDeque<Integer> dq = new ArrayDeque<Integer>() ;
        int n = L.size() ;
        boolean[] visited = new boolean[n] ;
        dq.push(s) ;
        while(!dq.isEmpty())
        {
            int val = dq.pop() ;
            if(!visited[val])
            {
                visited[val ] = true ;
                for(int i=0;i<L.get(val).size();i++)
                {
                    Triplet ne = L.get(val).get(i) ;
                    if(canUseEdge[ne.d])
                        dq.push(ne.b) ;
                }
            }
        }
        return visited[e] ;
    }
}
class Triplet implements Comparable<Triplet>
{
    int a,b,c,d ;
    Triplet(int a,int b,int c,int d)
    {
        this.a =a ;this.b = b ;this.c = c;this.d = d ;
    }
    @Override
    public int compareTo(Triplet t){return this.a>t.a?1:(this.a<t.a?-1:0) ;}
    @Override
    public int hashCode(){return 31*b+c;}
    @Override 
    public boolean equals(Object o)
    {
        if(o instanceof Triplet) 
            return this.b==((Triplet)o).b && this.c==((Triplet)o).c;
        return false;
    }
    @Override
    public String toString(){return "["+a+","+(b+1)+","+(c+1)+","+d+"]" ;}
}