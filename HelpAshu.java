import java.util.* ;
import java.io.BufferedReader ;
import java.io.InputStreamReader ;

public class HelpAshu
{
    private static final boolean debug = false ;
    private static final boolean debug2 = false ;
    // public static void main(String args[]) throws Exception
    // {
        // BufferedReader bro = new BufferedReader(new InputStreamReader(System.in)) ;
        // int N = Integer.parseInt(bro.readLine()) ;
        
        // String[] S = bro.readLine().split(" ") ;
        // if(debug) System.out.println("N :"+N+" S.length :"+S.length) ;
        // int[] bitODD = new int[N+1] ;
        // int[] bitEVEN = new int[N+1] ;
        // for(int i=0;i<N;i++) 
        // {
            // if(debug) System.out.println("i :"+i) ;
            // update(i+1,pint(S[i]),bitEVEN,false) ;
            // update(i+1,pint(S[i]),bitODD,true) ;
        // }
        // int Q = pint(bro.readLine()) ;
        // for(int i=0;i<Q;i++)
        // {
            // S = bro.readLine().split(" ") ;
            // int op = pint(S[0]) ;
            // if(op==0)
            // {
                // update(pint(S[1]),pint(S[2]),bitEVEN,false) ;
                // update(pint(S[1]),pint(S[2]),bitODD,true) ;
            // }
            // else if(op==1)
            // {
                // System.out.println((query(pint(S[2]),bitEVEN)-query(pint(S[1]),bitEVEN))) ;
            // }
            // else 
            // {
                // System.out.println((query(pint(S[2]),bitODD)-query(pint(S[1]),bitODD))) ;
            // }
        // }
        
    // }
    public static void main(String args[]) 
    {
        Scanner in = new Scanner(System.in) ;
        int N = in.nextInt() ;
        int[] bitEVEN = new int[N+1] ;
        int[] bitODD = new int[N+1] ;
        int[] A = new int[N] ;
        for(int i=0;i<N;i++)
        {
            int val = in.nextInt() ;
            A[i] = val ;
            update(i+1,val%2==1?1:0,bitODD) ;
            update(i+1,val%2==0?1:0,bitEVEN) ;
            
        }
        int Q = in.nextInt() ;
        for(int i=0;i<Q;i++)
        {
            int op = in.nextInt() ;
            int a = in.nextInt() ;
            int b = in.nextInt() ;
            if(op==0)
            {
                // update(a,b,bitEVEN,false) ;
                // update(a,b,bitODD,true) ;
                if(A[a-1]%2!=b%2)
                {
                    if(A[a-1]%2==0 && b%2==1)
                    {
                        update(a,1,bitODD) ;
                        update(a,-1,bitEVEN) ;
                    }
                    else 
                    {
                        update(a,-1,bitODD) ;
                        update(a,1,bitEVEN) ;
                    }
                    
                }
                A[a-1]=b ;
                if(debug2)
                {
                    sout("bitEVEN :"+Arrays.toString(bitEVEN)) ;
                    sout("bitODD :"+Arrays.toString(bitODD)) ;
                    
                }
            }
            else if(op==1)
            {
                System.out.println((query(b,bitEVEN)-query(a-1,bitEVEN))) ;
            }
            else 
            {
                System.out.println((query(b,bitODD)-query(a-1,bitODD))) ;
                
            }
        }
    }
    static int pint(String S)
    {
        return Integer.parseInt(S) ;
    }
    static void sout(String S)
    {
        System.out.println(S) ;
    }
    static long query(int x,int[] BIT)
    {
        int sum = 0 ;
        for(;x>0 ; x-=x&-x)
        {
            sum+=BIT[x] ;
            // if(debug) System.out.println("R") ;
        }
        return sum ;
    }
    static void update(int x,int val,int[] BIT)
    {
        int n = BIT.length ;
        for(;x<n;x+=x&-x)
        {
            BIT[x] += val ;
            if(debug) System.out.println("x :"+x) ;
        }
    }
}