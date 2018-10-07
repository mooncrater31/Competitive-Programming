import java.util.* ;
import java.io.BufferedReader ;
import java.io.InputStreamReader ;

public class NumbersOnTheChessboard
{
    private static final boolean debug = false ;
    public static void main(String args[]) throws Exception
    {
        BufferedReader bro = new BufferedReader(new InputStreamReader(System.in)) ;
        String[] S = bro.readLine().split(" ") ;
        long n = Long.parseLong(S[0]) ;
        int q = Integer.parseInt(S[1]) ;
        for(int i=0;i<q;i++)
        {
            S = bro.readLine().split(" ") ;
            long x = Long.parseLong(S[0]) ;
            long y = Long.parseLong(S[1]) ;
            // int x = Integer.parseInt(S[0]) ;
            // int y = Integer.parseInt(S[1]) ;
            System.out.println(solve(x,y,n)) ;
        }
    }
    static long solve(long x,long y,long n)
    {
        long rowSum = 0 ;
        long sq = 0 ;
        if(n%2==0)
            sq = n*n/2 ;
        else sq = (n*n+1)/2 ;
        if(n%2==0)
        {//n is even
            if((x+y)%2==0)
            {//x+y is even
                rowSum = (n*(x-1)/2) ;
            }
            else
            {//x+y is odd
                rowSum = sq+((n*(x-1))/2) ;
            }
        }
        else
        {//n is odd
            if((x+y)%2==0)//x+y is even
            {
                if(x%2!=0)//x is odd
                {
                    rowSum = ((x-1)/2)*(n) ;
                }
                else
                {
                    rowSum = (((x-2)/2)*(n)+((n+1)/2)) ;
                }
            }
            else//x+y is odd
            {
                if(x%2!=0)//x is odd
                {
                    
                    rowSum = sq+(n*(x-1)/2) ;
                    if(debug){System.out.println("rowSum :"+rowSum+" woo :"+(long)Math.ceil(n*n/2.0D)) ;}
                }
                else//x is even
                {
                    rowSum = sq+((x-2)/2)*(n)+(n-1)/2 ;
                }
            }
        }
        return rowSum+(long)Math.ceil((double)y/2) ;
    }
}
// 999999999 1
// 590350599 637664270
