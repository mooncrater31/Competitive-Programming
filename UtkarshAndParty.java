import java.util.* ;
import java.io.BufferedReader ;
import java.io.InputStreamReader ;

public class UtkarshAndParty
{
    public static void main(String args[]) throws Exception
    {
        BufferedReader bro = new BufferedReader(new InputStreamReader(System.in)) ;
        String[] S = bro.readLine().split(" ") ;
        int N = Integer.parseInt(S[0]) ;
        int Q = Integer.parseInt(S[1]) ;
        S = bro.readLine().split(" ") ;
        long[] A = new long[N] ;
        long[] BIT1 = new long[N+1] ;
        long[] BIT2 = new long[N+1] ;
        for(int i=0;i<N;i++)
        {
            A[i] = Integer.parseInt(S[i]) ;
            update(i+1,A[i],BIT1) ;
            update(i+1,A[i]*(i+1),BIT2) ;
        }
        for(int i=0;i<Q;i++)
        {
            S = bro.readLine().split(" ") ;
            int q = Integer.parseInt(S[0]) ;
            if(q==1)
            {
                int k = Integer.parseInt(S[1]) ;
                int l = Integer.parseInt(S[2]) ;
                int r = Integer.parseInt(S[3]) ;
                long ans = 0 ;
                if(k<=l || k>=r)
                    ans = getAns(k,l,r,BIT1,BIT2) ;
                else
                    ans = getAns(k,l,k,BIT1,BIT2)+getAns(k,k,r,BIT1,BIT2) ;
                System.out.println(ans) ;
            }

            else 
            {
                int k = Integer.parseInt(S[1]) ;
                long s = Long.parseLong(S[2]) ;
                update(k,s,BIT1) ;
                update(k,s*k,BIT2) ;
            }
        }
    }
    static long getAns(long k,int l,int r,long[] BIT1,long[] BIT2)
    {
        return Math.abs(k*(query(r,BIT1)-query(l-1,BIT1))-(query(r,BIT2)-query(l-1,BIT2))) ;
    }
    static void update(int x,long val,long[] BIT)
    {
        int n = BIT.length ;
        for(;x<n;x+=x&-x) BIT[x]+=val ;
    }
    static long query(int x,long[] BIT)
    {
        long sum = 0 ;
        for(;x>0;x-=x&-x) sum+=BIT[x] ;
        return sum ;
    }
}
