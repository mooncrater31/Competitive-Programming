import java.io.BufferedReader ;
import java.io.InputStreamReader ;
import java.util.* ;

public class FFTFast
{
    private static final boolean debug = true ;
    public static void main(String args[])
    {
        double[] A = {4,3,2,1,0,0,0,0} ;
        int n = A.length ;
        Complex[] as = new Complex[n] ;
        int l = (int)(Math.log(n)/Math.log(2)) ;
        if(debug) {System.out.println("l :"+l) ;for(int i=0;i<8;i++) {System.out.print(reverse(i,l)+" ") ;}System.out.println();}
        for(int i=0;i<n;i++) as[i]=new Complex(A[i],0) ;
        long startTime = System.nanoTime() ;
        System.out.println(Arrays.toString(fft(as))) ;
        long endTime = System.nanoTime() ;
        System.out.println("Time taken :"+(endTime-startTime)) ;
    }
    static Complex[] makeReady(Complex[] as)
    {
        int n = as.length ;
        Complex[] newas = new Complex[n] ; 
        int l = (int)(Math.log(n)/Math.log(2)) ;
        for(int i=0;i<n;i++)
        {
            newas[reverse(i,l)]=as[i] ;
        }
        return newas ;
    }
    static int reverse(int x,int n)
    {
        int rev = 0 ;
        // if(debug) System.out.println("x :"+x) ;
        for(int i=n-1;i>=0;i--)
        {
            rev|=(x&1) ;
            x>>=1 ;
            rev<<=1 ;
        }
        rev>>=1 ;
        return rev; 
    }
    static Complex[] fft(Complex[] as)
    {
        Complex[] asNew = makeReady(as) ;
        int n = as.length ;
        for(int s=1;s<=(int)(Math.log(n)/Math.log(2));s++)
        {
            int m = (int)Math.pow(2,s) ;
            double alpha = 2*Math.PI/m ;
            Complex wm = new Complex(Math.cos(alpha),Math.sin(alpha)) ;
            for(int k=0;k<n;k+=m)
            {
                Complex w = new Complex(1,0) ;
                for(int j=0;j<m/2;j++)
                {
                    Complex t = multiply(w,asNew[k+j+m/2]) ;
                    Complex u = asNew[k+j] ;
                    asNew[k+j]=add(u,t) ;
                    asNew[k+j+m/2]=sub(u,t) ;
                    w = multiply(w,wm) ;
                }
            }
        }
        return asNew ;
    }
    static Complex add(Complex a,Complex b)
    {
        return new Complex(a.real+b.real,a.imag+b.imag) ;
    }
    static Complex multiply(Complex a,Complex b)
    {
        return new Complex(a.real*b.real-a.imag*b.imag,a.real*b.imag+b.real*a.imag) ;
    }
    static Complex sub(Complex a,Complex b)
    {
        return new Complex(a.real-b.real,a.imag-b.imag) ;
    } 
}
class Complex
{
    double real,imag ;
    Complex(double r,double i)
    {
        this.real = r ;
        this.imag = i ;
    }
    @Override
    public String toString()
    {
        return "["+real+","+imag+"]" ;
    }
    
}