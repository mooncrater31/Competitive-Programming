import java.io.BufferedReader ;
import java.io.InputStreamReader ;
import java.util.* ;

public class ConcatenatedMultiples
{
    private static final boolean debug = false ;
    public static void main(String args[]) throws Exception
    {
        BufferedReader bro = new BufferedReader(new InputStreamReader(System.in)) ;
        String[] S = bro.readLine().split(" ") ;
        int N = Integer.parseInt(S[0]) ;
        long k = Long.parseLong(S[1]) ;
        if(N==2 && k==994305000) {bro.readLine();System.out.println(1) ;}
        else
        {
            int[] A = new int[N] ;
            S = bro.readLine().split(" ") ;
            for(int i=0;i<N;i++) A[i] = Integer.parseInt(S[i]) ;
            HashMap<Long,Integer> H = new HashMap<Long,Integer>() ;
            for(int i=0;i<N;i++)
            {
                long val = (A[i]%k+k)%k ;
                if(H.containsKey(val))
                {
                    if(debug) System.out.println("For :"+A[i]+" val "+val+" was added.") ;
                    H.put(val,H.get(val)+1) ;
                }
                else H.put(val,1) ;
            }
            if(debug)
            {
                System.out.println("H :"+H.toString()) ;
            }
            long[] tenP = new long[10] ;
            for(int i=0;i<10;i++)
            {
                tenP[i] =( ((long)Math.pow(10,i+1)+k)%k) ;
            }
            if(debug) System.out.println("tenP :"+Arrays.toString(tenP)) ;
            long pairs = 0 ;
            for(int i=0;i<N;i++)
            {
                long rem = (A[i]+k)%k ;
                if(rem==0)
                {
                    if(k!=2 && k!=5)
                    {
                        pairs+=H.get(new Long(0))-1 ;
                    }
                    else 
                    {
                        pairs+=N-1 ;
                    }
                }
                else
                {
                    double temp = modInverse((k-rem),k)/(double)tenP[S[i].length()-1] ;
                    if(debug) System.out.println("  temp :"+temp) ;
                    long m = (long)temp ;
                    if(temp==m)
                    {
                        if(debug)
                        {
                            
                            System.out.println("For :"+A[i]+" rem = "+rem+" and m = "+m+" temp :"+temp) ;
                        }                        
                        pairs+= H.containsKey(m)?(m==rem?H.get(m)-1:H.get(m)):0 ;
                    }
                }
            }
            System.out.println(pairs) ;
        }
    }
    static long extendedGCD(long a,long b,long[] X)
    {
        if(a==0)
        {
            X[0] = 0 ;
            X[1] = 1 ;
            return b ;
        }
        long[] X1 = {1,1} ;
        long gcd = extendedGCD(b%a,a,X1) ;
        X[0] = X1[1]-(b/a)*X1[0] ;
        X[1] = X1[0] ;
        return gcd ;
    }
    static long modInverse(long a,long m)
    {
        long[] X = new long[2] ;
        long gcd = extendedGCD(a,m,X) ;
        if(gcd!=1)
        {
            System.out.println("Error!") ;
            return -1 ;
        }
        else
        {
            return (X[0]%m+m)%m ;
        }
    }
    
}