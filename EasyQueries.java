import java.util.* ;
import java.io.BufferedReader ;
import java.io.InputStreamReader ;

public class EasyQueries
{
    private static final boolean debug = false ;
    public static void main(String args[]) throws Exception
    {
        BufferedReader bro = new BufferedReader(new InputStreamReader(System.in)) ;
        String[] S = bro.readLine().split(" ") ;
        int N = Integer.parseInt(S[0]) ;
        int Q = Integer.parseInt(S[1]) ;
        int[] A = new int[N] ;
        int[][] tree = new int[2000005][2] ;
        S = bro.readLine().split(" ") ;
        for(int i=0;i<N;i++)
            A[i] = Integer.parseInt(S[i]) ;
        build(1,1,N,tree,A) ;
        for(int i=0;i<Q;i++)
        {
            S = bro.readLine().split(" ") ;
            int a = Integer.parseInt(S[0]) ;
            int b = Integer.parseInt(S[1]) ;
            if(a==0)
            {
                Pair p1 = query(1,1,N,1,b,tree) ;
                Pair p2 = query(1,1,N,b+2,N,tree) ;
                System.out.println(p1.r+" "+p2.l) ;
            }
            else 
            {
                if(A[b]==0)
                {
                    update(1,b+1,1,N,tree,A) ;
                }
            }
        }
        
        
    }
    static void build(int node,int start,int end,int[][] tree,int[] A)
    {
        if(start==end)
        {
            tree[node][0] = (A[start-1]==1?start-1:-1) ;//left
            tree[node][1] = (A[start-1]==1?start-1:-1) ;//right
            
        }
        else 
        {
            int mid = (start+end)/2 ;
            build(2*node,start,mid,tree,A) ;
            build(2 * node + 1,mid + 1,end,tree,A) ;
            tree[node][0] =  tree[2 * node][0]==-1?tree[2 * node + 1][0]:tree[2*node][0] ;//leftmost 1
            tree[node][1] = tree[2*node+1][1]==-1?tree[2*node][1]:tree[2*node+1][1] ;//rightmost 1
        }
    }
    static void update(int node,int idx,int start,int end,int[][] tree,int[] A)
    {
        if(start==end)
        {
            A[idx-1]=1 ;
            tree[node][0]=idx-1 ;
            tree[node][1]=idx-1 ;
        }
        else 
        {
            int mid = (start+end)/2 ;
            if(start<=idx && idx<=mid)
            {
                update(2*node,idx,start,mid,tree,A) ;       
            }
            else update(2*node+1,idx,mid+1,end,tree,A) ;
            tree[node][0] =  tree[2 * node][0]==-1?tree[2 * node + 1][0]:tree[2*node][0] ;//leftmost 1
            tree[node][1] = tree[2*node+1][1]==-1?tree[2*node][1]:tree[2*node+1][1] ;//rightmost 1
        }
        
    }
    static Pair query(int node,int start,int end,int l,int r,int[][] tree)
    {
        if(debug) System.out.println("start :"+start+" end :"+end+" node :"+node) ;
        if( r< start || l>end) 
            return new Pair(-1,-1) ;
        if(l<=start && end<=r)
            return new Pair(tree[node][0],tree[node][1]) ;
        int mid = (start+end)/2 ;
        Pair p1 = query(2*node,start,mid,l,r,tree) ;
        Pair p2 = query(2*node+1,mid+1,end,l,r,tree) ;
        if(debug) System.out.println("start :"+start+" end :"+end+" node :"+node+" l :"+l+" r :"+r)  ;
        if(debug) System.out.println("  "+p1+" "+p2);
        return new Pair(p1.l==-1?p2.l:p1.l,p2.r==-1?p1.r:p2.r) ;
    }
}
class Pair
{
    int l,r ;
    Pair(int l,int r)
    {
        this.l = l ;
        this.r = r ;
    }
    @Override public String toString()
    {
        return "["+l+","+r+"]" ;
    }
}