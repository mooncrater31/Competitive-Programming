import java.util.* ;
import java.io.BufferedReader ;
import java.io.InputStreamReader ;

public class IncreasingSubsequence
{
    private static final boolean debug = false ;
    public static void main(String args[]) throws Exception
    {
        BufferedReader bro = new BufferedReader(new InputStreamReader(System.in)) ;
        String[] S = bro.readLine().split(" ") ;
        int N = Integer.parseInt(S[0]) ;
        int K = Integer.parseInt(S[1]) ;
        int[] A = new int[N] ;
        int[] aCopy = new int[N] ;
        S = bro.readLine().split(" ") ;
        for(int i=0;i<N;i++) 
        {
            A[i] = Integer.parseInt(S[i]) ;
            aCopy[i] = A[i] ;
        }
        int[][] BITS = new int[K][N+1]  ;
        int[][] ans = new int[K][N+1] ;
        for(int i=0;i<K;i++) Arrays.fill(BITS[i],Integer.MAX_VALUE) ;
        HashMap<Integer,Integer> HM = new HashMap<Integer,Integer>() ;
        Arrays.sort(aCopy) ;
        for(int i=0;i<N;i++)
        {
            HM.put(aCopy[i],i+1) ;    
        }
        int max = -1 ;
        for(int i=0;i<N;i++)
        {
            int x = HM.get(A[i]) ;
            ans[0][x] = x ;
            update(0,x,x,BITS) ;
            for(int j=1;j<K;j++)
            {
                int res = query(j-1,x-1,BITS) ;
                ans[j][x] = res ;
                update(j,x,res,BITS) ;
            }
            if(ans[K-1][x]<=N) max = Math.max(max,A[i]-aCopy[ans[K-1][x]-1]) ;
        }
        System.out.println(max) ;
    }
    static void update(int len,int x,int val,int[][] BITS)
    {
        int n = BITS[len].length ;
        for(;x<n;x+=x&-x) BITS[len][x] = Math.min(BITS[len][x],val) ;
    }
    static int query(int len,int x,int[][] BITS)
    {
        int res = Integer.MAX_VALUE ;
        for(;x>0;x-=x&-x) res = Math.min(res,BITS[len][x]) ;
        return res ;
    }
}