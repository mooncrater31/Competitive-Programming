import java.util.* ;
import java.io.BufferedReader ;
import java.io.InputStreamReader ;

public class SegmentTrees
{
    public static void main(String args[]) throws Exception
    {
        BufferedReader bro = new BufferedReader(new InputStreamReader(System.in)) ;
        String[] S = bro.readLine().split(" ") ;
        int n = Integer.parseInt(S[0]) ;
        int q = Integer.parseInt(S[1]) ;
        int[] A = new int[n] ;
        S = bro.readLine().split(" ") ;
        for(int i=0;i<n;i++) A[i] = Integer.parseInt(S[i]) ;
        int[] tree = new int[n+1] ;
        build(1,0,n-1,A,tree) ;
        for(int i=0;i<q;i++)
        {
            S = bro.readLine().split(" ") ;
            char op = S[0].charAt(0) ;
            int a = Integer.parseInt(S[1]) ;
            int b = Integer.parseInt(S[2]) ;
            if(q=='q') System.out.println(query(1,0,n-1,a,b,tree)) ;
            if(q=='u') update(1,0,n-1,a,b-A[a],A,tree) ;
            
        }
        
    }
    static void build(int node,int s,int e,int[] A,int[] tree)
    {
        if(s==e)
            tree[node]=A[s] ;
        else
        {
            int mid = (s+e)/2 ;
            build(2*node,s,mid,A,tree) ;
            build(2*node+1,mid+1,e,A,tree) ;
            tree[node]=tree[2*node]+tree[2*node+1] ;
        }
    }
    static void update(int node,int s,int e,int idx,int val,int[] A,int[] tree)
    {
        if(s==e) 
        {
            A[idx]+=val ;
            tree[node]+=val ;
        }
        else 
        {
            int mid = (s+e)/2 ;
            if(s<=idx && idx<=mid)
                update(2*node,s,e,idx,val,A,tree);
            else update(2*node+1,s,e,idx,val,A,tree) ;
            tree[node]=tree[2*node]+tree[2*node+1] ;
        }
    }
    static int query(int node,int s,int e,int l,int r,int[] tree)
    {
        if(r<s || e<l) return 0 ;
        if(l<=s && e<=r) return tree[node] ;
        int mid = (s+e)/2 ;
        int p1 = query(2*node,s,mid,l,r,tree) ;
        int p2 = query(2*node+1,mid+1,e,l,r,tree) ;
        return (p1+p2) ;
    }
}