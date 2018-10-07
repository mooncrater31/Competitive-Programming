import java.util.* ;
import java.io.BufferedReader ;
import java.io.InputStreamReader ;

class SheokandAndNumber
{
    private static final boolean debug = false ;
    public static void main(String args[]) throws Exception
    {
        BufferedReader bro = new BufferedReader(new InputStreamReader(System.in)) ;
        int T = Integer.parseInt(bro.readLine()) ;
        
        for(int t=0;t<T;t++) System.out.println(solve(Integer.parseInt(bro.readLine()))) ;
    }
    static long solve(int N)
    {
        long min = Integer.MAX_VALUE ;
        for(int i=0;i<32;i++)
        {
            for(int j=i+1;j<32;j++)
            {
                long val = (1<<i)+(1<<j) ;
                if(debug) 
                {
                    System.out.println("i :"+i+" j :"+j) ;
                    System.out.println("val :"+val) ;
                    System.out.println("diff :"+Math.abs(N-min)) ;
                    System.out.println("min :"+min) ;
                }
                min = Math.min(min,Math.abs(N-val)) ;
            }
        }
        return min ;
    }
}
    