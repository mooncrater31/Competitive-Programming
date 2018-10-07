import java.util.* ;
import java.io.BufferedReader ;
import java.io.InputStreamReader ;

public class MaximumSumOfDigits
{
    private static final boolean debug = false ;
    static int getDigits(long num)
    {
        int n = 1 ;
        if( num>=((int)1e8)) { n+=8 ; num/=(int)1e8 ;}
        if( num>=(int)1e4) { n+=4 ; num /= (int)1e4 ;} 
        if( num>= (int)1e2) { n+=2 ; num /= (int)1e2;}
        if( num>10) {n+=1;}
        return n ;
    }
    public static void main(String args[]) throws Exception 
    {
        BufferedReader bro = new BufferedReader(new InputStreamReader(System.in)) ;
        long n  = Long.parseLong(bro.readLine()) ;
        int dig = getDigits(n) ;
        long num1 = (n/((long)Math.pow(10,dig-1)))*(long)Math.pow(10,dig-1)-1 ;
        long num2 = n-num1 ;
        if(debug) 
        {
            System.out.println("num1 :"+num1 + " num2 :"+num2+" dig :"+dig) ;
        }
        System.out.println(sumD(num1)+sumD(num2)) ;
    }
    static int sumD(long n)
    {
        int sum = 0 ;
        while(n!=0)
        {
            sum+=n%10 ;
            n/=10 ;
        }
        return sum ;
    }
}