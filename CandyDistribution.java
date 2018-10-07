import java.util.* ;
import java.io.BufferedReader ;
import java.io.InputStreamReader ;

public class CandyDistribution
{
    static final int MOD = (int)1e9+7 ;
    private static final boolean debug=true ;
    public static void main(String args[]) throws Exception
    {
        BufferedReader bro = new BufferedReader(new InputStreamReader(System.in)) ;
        long N = Long.parseLong(bro.readLine()) ;
        // long[] dp = new long[N+1] ;
        HashMap<Long,Long> HM = new HashMap<Long,Long>() ;
        HM.put(new Long(1),new Long(1)) ;
        HM.put(new Long(2),new Long(1)) ;
        HM.put(new Long(3),new Long(1)) ;
        System.out.println(ways(N,HM)) ;
        
    }
    static long mod(long num)
    {
        return (num+(int)1e9+7)%((int)1e9+7) ;
    }
    static long modPower(long a,long b)
    {
        long[] arr = new long[(int)(Math.log(b)/Math.log(2))+1] ;
        arr[0]=mod(a) ;
        for(int i=1;i<arr.length;i++)
        {
            arr[i]=mod(arr[i-1]*arr[i-1]) ;
        }
        int pow=0 ;
        long ans=1 ;
        for(long val=b;val>0;val>>=1,pow++)
        {
            if((val&1)==1)
                ans=mod(ans*arr[pow]) ;
            
        }
        if(debug) System.out.println("a :"+a+" b :"+b+" ans :"+ans) ;
        return ans ;
    }
    static long ways(long N,HashMap<Long,Long> HM)
    {
        if(HM.containsKey(N))
            return HM.get(N) ;
        else 
        {
            HM.put(N,new Long(0)) ;
            for(int i=2;i<=(int)Math.sqrt(N-1);i++)
            {
                if((N-1)%i==0)
                {
                    if(debug)
                    {
                        System.out.println("N-1 :"+(N-1)+" i :"+i+" (N-1)/i :"+(N-1)/i) ;
                    }
                    HM.put(N,mod(HM.get(N)+modPower(ways(i,HM),(N-1)/i))) ;
                    if(i!=(N-1)/i) HM.put(N,mod(HM.get(N)+modPower(ways((N-1)/i,HM),i))) ;
                }
            }
            // dp[N]++ ;//pile with 1 candy each
            if(debug) System.out.println("  ::::"+N+" "+(HM.get(N)+1)) ;
            HM.put(N,HM.get(N)+1) ;
        }
        return HM.get(N) ;
    }
}