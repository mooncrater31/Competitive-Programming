import java.util.* ;
import java.io.BufferedReader ;
import java.io.InputStreamReader ;

public class MurciaSkyline2
{
    public static void main(String args[]) throws Exception
    {
        BufferedReader bro = new BufferedReader(new InputStreamReader(System.in)) ;
        int T = Integer.parseInt(bro.readLine()) ;
        for(int t=0;t<T;t++)
        {
            int N = Integer.parseInt(bro.readLine()) ;
            int[] h = new int[N] ;
            int[] w = new int[N] ;
            String[] H = bro.readLine().trim().split(" +") ;
            String[] W = bro.readLine().trim().split(" +") ;
            for(int i=0;i<N;i++)
            {
                h[i] = Integer.parseInt(H[i]) ;
            }
            for(int i=0;i<N;i++)
            {
                w[i] = Integer.parseInt(W[i]) ;
            }
            Pair p = solve(h,w) ;
            if(p.lis>=p.minlis)
            {
                System.out.println("Case "+(t+1)+". Increasing ("+p.lis+"). Decreasing ("+p.minlis+").") ;
            }
            else
            {
                System.out.println("Case "+(t+1)+". Decreasing ("+p.minlis+"). Increasing ("+p.lis+").") ;
            }
        }
    }
    static Pair solve(int[] h,int[] w)
    {
        // int[] dpLIS = new int[h.length] ;
        // int[] dpLDS = new int[h.length] ;
        int[] LIS = new int[h.length] ;
        int[] LDS = new int[h.length] ;
        LIS[0] = w[0] ;
        LDS[0] = w[0] ;
        // dpLIS[0] = w[0] ;
        // dpLDS[0] = w[0] ;
        for(int i=1;i<LIS.length;i++)
        {
            LIS[i] = w[i] ;
            LDS[i] = w[i] ;
            for(int j=0;j<i;j++)
            {
                if(h[j]<h[i])
                {
                    LIS[i] = Math.max(LIS[i],LIS[j]+w[i]) ;
                }
                else if(h[j]>h[i])
                {
                    LDS[i] = Math.max(LDS[i],LDS[j]+w[i]) ;
                }
                
            }
        }
        int lis=1,minlis=1 ;
        for(int i=0;i<LIS.length;i++)
        {
            lis = Math.max(lis,LIS[i]) ;
            minlis = Math.max(minlis,LDS[i]) ;
        }
        return new Pair(lis,minlis) ;
    }
}
class Pair
{
    int lis,minlis ;
    Pair(int l,int m)
    {
        this.lis = l ;
        this.minlis = m ;
    }
}