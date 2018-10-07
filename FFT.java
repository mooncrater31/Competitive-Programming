import java.io.BufferedReader ;
import java.io.InputStreamReader ;
import java.util.* ;

public class FFT
{
    private static final boolean debug = true ;
    public static void main(String args[])
    {
        // double[] A = {1.25,2.34,3.36,4.01,6.23,9.19,1.004,1.001} ;
        double[] A = {4,3,2,1,0,0,0,0} ;
        int n = A.length ;
        Complex[] as = new Complex[n] ;
        for(int i=0;i<n;i++) as[i]=new Complex(A[i],0) ;
        long startTime = System.nanoTime() ;
        System.out.println(Arrays.toString(fft(as))) ;
        long endTime = System.nanoTime() ;
        System.out.println("Time taken :"+(endTime-startTime)) ;
    }
    static Complex[] fft(Complex[] as)
    {
        int n = as.length ;
        if(n==1) return as ;
        Complex[] w = new Complex[n] ;
        for(int i=0;i<n;i++)
        {
            double alpha = 2* Math.PI *i/n ;
            w[i] = new Complex(Math.cos(alpha),Math.sin(alpha)) ;
        }
        if(debug)
        {
            System.out.println(n+"th roots of unity :") ;
            System.out.println(Arrays.toString(w)) ;
        }
        // Complex A = new Complex[n/2] ;
        // Complex B = new Complex[n/2] ;
        Complex[] A = new Complex[n/2] ;
        Complex[] B = new Complex[n/2] ;
        for(int i=0;i<n/2;i++)
        {
            A[i]=as[2*i] ;
            B[i]=as[2*i+1] ;
        }
        Complex[] Av = fft(A) ;
        Complex[] Bv = fft(B) ;
        Complex[] res = new Complex[n] ;
        for(int i=0;i<n;i++)
        {
            // res[i]=Av[i%(n/2)]+w[i]*Bv[i%(n/2)] ;
            res[i]=add(Av[i%(n/2)],multiply(w[i],Bv[i%(n/2)])) ;
        }
        return res ;
        
    }
    static Complex add(Complex a,Complex b)
    {
        return new Complex(a.real+b.real,a.imag+b.imag) ;
    }
    static Complex multiply(Complex a,Complex b)
    {
        return new Complex(a.real*b.real-a.imag*b.imag,a.real*b.imag+b.real*a.imag) ;
    }
}
class Complex 
{
    double real,imag ;
    Complex(double r,double im)
    {
        this.real = r ;
        this.imag = im ;
    }
    @Override
    public String toString()
    {
        return "["+real+","+imag+"]" ;
    }
}