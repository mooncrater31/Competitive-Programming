import java.io.BufferedReader ;
import java.io.InputStreamReader ;
import java.util.* ;

public class AnotherQueryQuestion
{
    private static final boolean debug = false ;
    public static void main(String args[]) throws Exception
    {
        BufferedReader bro = new BufferedReader(new InputStreamReader(System.in)) ;
        int N = pint(bro.readLine()) ;
        int[] A = new int[N+1] ;
        String[] S = bro.readLine().split(" ") ;
        for(int i=1;i<=N;i++) A[i] = pint(S[i-1]) ;
        String S1 = bro.readLine() ;
        long[] BIT = new long[N+1] ;
        if(S1.split(" ").length==1)
        {
            int Q = pint(S1) ;
            
            for(int i=0;i<Q;i++)
            {
                S = bro.readLine().split(" ") ;
                int op = pint(S[0]) ;
                if(op==1)
                {
                    update(Math.min(pint(S[1]),N),BIT) ;
                    if(debug) sout("BIT :"+Arrays.toString(BIT)) ;
                }
                else if(op==2)
                {
                    long el = pint(S[1]) ;
                    boolean found  = binarySearch(A,el,BIT) ;
                    sout((found?"yes":"no")) ;
                }
            }
        }
        else 
        {
            
            for(;S1!=null && !S1.equals("");S1=bro.readLine())
            {
                S = S1.split(" ") ;
                int op = pint(S[0]) ;
                if(op==1)
                {
                    update(Math.min(pint(S[1]),N),BIT) ;
                    if(debug) sout("BIT :"+Arrays.toString(BIT)) ;
                }
                else if(op==2)
                {
                    long el = pint(S[1]) ;
                    boolean found  = binarySearch(A,el,BIT) ;
                    sout((found?"yes":"no")) ;
                }
            }
        }
    
    }
    static boolean binarySearch(int[] A,long el,long[] BIT)
    {
        int n = A.length ;
        int i=1,j=n-1; 
        while(i<=j)
        {
            int mid = (i+j)/2 ;
            long val = getEl(mid,BIT,A) ;
            if(debug) sout("    Found :"+val) ;
            if(val<el)
            {
                i = mid+1 ;
            }
            else if(val>el)
            {
                j = mid-1 ;
            }
            else return true ;
        }
        return false ;
    }
    
    
    static void update(int x,long[] BIT)
    {
        for(;x>0;x-=x&-x)
        {
            BIT[x]-=(x&-x) ;
            if(debug) 
            {
                sout("  x :"+x) ;
            }
        }
    }
    static long query(int x,long[] BIT)
    {
        long sum = 0 ;
        int n = BIT.length ;
        for(;x<n;x+=x&-x)
            sum+=BIT[x]/(x&-x) ;
        return sum ;
    }
    static long getEl(int i,long[] BIT,int[] A)
    {
        return A[i]+(query(i,BIT)) ;
    }
    static int pint(String S)
    {
        return Integer.parseInt(S) ;
    }
    static void sout(String S)
    {
        System.out.println(S) ;
    }
}