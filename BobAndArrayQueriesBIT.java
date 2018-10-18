import java.util.* ;
import java.io.BufferedReader ;
import java.io.InputStreamReader ;

public class BobAndArrayQueriesBIT
{
    private static final boolean debug = false ;
    public static void main(String args[]) throws Exception
    {
        BufferedReader bro = new BufferedReader(new InputStreamReader(System.in)) ;
        String[] S = bro.readLine().split(" ") ;
        int N = Integer.parseInt(S[0]) ;
        int Q = Integer.parseInt(S[1]) ;
        long[] A = new long[N] ;
        int[] BIT = new int[N+1] ;
        for(int i=0;i<Q;i++)
        {
            S = bro.readLine().split(" ") ;
            int a = Integer.parseInt(S[0]) ;
            int b = Integer.parseInt(S[1]) ;
            if(a==1)
            {
                A[b-1] = (A[b-1]<<1)+1 ;
                update(b,1,BIT) ;
            }
            else if(a==2) 
            {
                if(A[b-1]%2!=0)//odd
                    update(b,-1,BIT) ;
                A[b-1]>>=1 ;//Divide by 2 
            }
            else
            {
                int c = Integer.parseInt(S[2]) ;
                int uptoc = query(c,BIT) ;
                if(debug) System.out.println("uptoc :"+uptoc);
                System.out.println(uptoc-(b>1?query(b-1,BIT):0)) ;

            }
        }
    }
    static void update(int x,int val,int[] BIT)
    {
        for(;x<BIT.length;x+=x&-x) BIT[x]+=val ;
    }
    static int query(int x,int[] BIT)
    {
        int sum = 0 ;
        for(;x>0;x-=x&-x) sum+=BIT[x] ;
        return sum ;
    }
}