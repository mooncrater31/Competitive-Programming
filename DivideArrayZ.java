import java.util.* ;
import java.io.BufferedReader ;
import java.io.InputStreamReader ;
class DivideArrayZ {
	private static final boolean debug = false ;
    public static void main(String args[] ) throws Exception {
        BufferedReader bro = new BufferedReader(new InputStreamReader(System.in)) ;
        int N = Integer.parseInt(bro.readLine()) ;
        String[] S = bro.readLine().split(" ") ;
		int[] A = new int[S.length] ;
		if(debug)
		{
			System.out.println("S.length = "+S.length+" N = "+N) ;
		}
        else
		{
		for(int i=0;i<S.length;i++)
        {
            A[i] = Integer.parseInt(S[i]) ;
        }
        int Q = Integer.parseInt(bro.readLine()) ;
        long mult = 1 ;
        for(int i=0;i<Q;i++)
        {
            mult*=Integer.parseInt(bro.readLine()) ;
        }
        for(int i=0;i<S.length	;i++)
        {
            System.out.print((mult==0?0:A[i]/mult)+" ") ;
        }
		}
    }
    
}
