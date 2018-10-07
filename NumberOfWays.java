import java.util.* ;
import java.io.BufferedReader ;
import java.io.InputStreamReader ;

public class NumberOfWays
{
    private static final boolean debug = false ;
    static void print(String S)
    {
        System.out.println(S) ;
    }
    public static void main(String args[]) throws Exception
    {
        BufferedReader bro = new BufferedReader(new InputStreamReader(System.in)) ;
        int N = Integer.parseInt(bro.readLine()) ;
        int[] A = new int[N] ;
        String[] S = bro.readLine().split(" ") ;
        long sum = 0 ;
        for(int i=0;i<N;i++)
        {
            A[i] = Integer.parseInt(S[i]) ;
            sum+=A[i] ;
        }
        System.out.println(solve(A,sum)) ;
        
    }
    static long solve(int[] A,long sum)
    {
        if((double)sum/3!=sum/3)
            return 0 ;
        int n = A.length ;
        long[] sumA = new long[n] ;
        long[] revsumA = new long[n] ;
        List<Integer> fpos = new ArrayList<Integer>() ;
        List<Integer> revpos = new ArrayList<Integer>() ;
        sumA[0] = A[0] ;
        revsumA[0] = A[n-1] ;
        if(A[0]==sum/3)
            fpos.add(0) ;
        if(A[n-1]==sum/3)
            revpos.add(0) ;
        for(int i=1;i<n;i++)
        {
            sumA[i]=sumA[i-1]+A[i] ;
            if(sumA[i]==sum/3)
                fpos.add(i) ;
        }
        for(int i=1;i<n;i++)
        {
            revsumA[i]=revsumA[i-1]+A[n-1-i] ;
            if(revsumA[i]==sum/3)
                revpos.add(i) ;
        }
        long ways = 0 ;
        for(int i=0;i<fpos.size();i++)
        {
            int toS = n-2-fpos.get(i) ;
            long val = Collections.binarySearch(revpos,toS) ;
            if(val<0)
                val = (val*-1)-1 ;
            ways+=val ;
        }
        if(debug)
        {
            print(fpos.toString()) ;
            print(revpos.toString()) ;
            print(Arrays.toString(sumA)) ;
            print(Arrays.toString(revsumA)) ;
        }
        return ways ;
        
    }
    
}