import java.util.* ;
import java.io.BufferedReader ;
import java.io.InputStreamReader ;

public class SegmentTrees
{
    private static final boolean debug = false ;
    public static void main(String args[]) throws Exception
    {
        Scanner in = new Scanner(System.in) ;
        int N = in.nextInt() ;
        int Q = in.nextInt() ;
        int[] A = new int[N] ;
        int[] tree = new int[2000005] ;
        Arrays.fill(tree,Integer.MAX_VALUE) ;
        for(int i=0;i<N;i++) A[i] = in.nextInt() ;
        build(1,1,N,A,tree) ;
        for(int i=0;i<Q;i++)
        {
            String S = in.next() ;
            if(S.charAt(0)=='q')
            {
                System.out.println(query(1,1,N,in.nextInt(),in.nextInt(),tree)) ;
            }
            else 
            {
                int idx = in.nextInt() ;
                update(1,1,N,idx,in.nextInt()-A[idx-1],A,tree) ;
            }
        }
    }
    static void build(int node,int start,int end,int[] A,int[] tree)
    {
        if(debug) System.out.println("Building :"+node+" start :"+start+" end :"+end) ;        
        if(start==end)
        {
            // if(debug) System.out.println("start :"+start+" end :"+end) ;
            tree[node] = A[start-1] ;
        }
        else
        {
            int mid = (start+end)/2 ;

                build(2*node,start,mid,A,tree) ;
                build(2*node+1,mid+1,end,A,tree) ;
                tree[node] = Math.min(tree[2*node],tree[2*node+1]) ;
                
            
            

        }
    }
    static void update(int node,int start,int end,int idx,int val,int[] A,int[] tree)
    {
        if(start==end)
        {
            A[idx-1]+=val ;
            tree[node]+=val ;
        }
        else 
        {
            int mid = (start+end)/2 ;
            
                if(start<=idx && idx<=mid) update(2*node,start,mid,idx,val,A,tree) ;
                else update(2*node+1,mid+1,end,idx,val,A,tree) ;
                tree[node] = Math.min(tree[2*node],tree[2*node+1]) ;
            
        }
    }
    static int query(int node,int start,int end,int l,int r,int[] tree)
    {
        if(r<start || l>end) return Integer.MAX_VALUE ;
        if(l<=start && end<=r) return tree[node] ;
        int ret = Integer.MAX_VALUE ;
        int mid = (start+end)/2 ;
        
            int p1 = query(2*node, start, mid, l, r, tree) ;
            int p2 = query(2*node+1, mid+1, end, l, r, tree) ;
            ret = Math.min(p1,Math.min(p2,ret)) ;
        
        return ret ;

    }
}