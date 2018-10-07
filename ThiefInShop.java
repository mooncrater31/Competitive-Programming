import java.util.* ;
import java.io.BufferedReader ;
import java.io.InputStreamReader ;

public class ThiefInShop
{
    public static void main(String args[]) throws Exception
    {
        BufferedReader bro = new BufferedReader(new InputStreamReader(System.in)) ;
        String[] S = bro.readLine().split(" ") ;
        int n = Integer.parseInt(S[0]) ;
        int k = Integer.parseInt(S[1]) ;
        Complex[] CA = new Complex[n] ;
        S = bro.readLine().split(" ") ;
        for(int i=0;i<n;i++)
        {
            CA[i].real=Integer.parseInt(S[i]) ;
        }
        
    }
    static Complex[] square_inplace()
    static int reverse(int x,int n)
    {
        int rev=0 ;
        for(int i=n-1;i>=0;i--)
        {
            rev|=(x&1) ;
            x>>=1 ;
            rev<<=1 ;
        }
        rev>>=1 ;
        return rev ;
    }
    static Complex[] makeReady(Complex[] as)
    {
        int n = as.length ;
        Complex[] newas = new Complex[n] ;
        int l=(int)(Math.log(n)/Math.log(2)) ;
        for(int i=0;i<n;i++) newas[reverse(i,l)]=as[i] ;
        return newas ;
    }
    static Complex[] fft(Complex[] CA,boolean inv)
    {
        Complex[] asNew = makeReady(CA) ;
        int n = CA.length ;
        
        
    }
    
}
class Complex 
{
    double real=0,imag=0 ;
    Complex(double real,double imag)
    {
        this.real = real ;
        this.imag = imag ;
    }
    Complex add(Complex b)
    {
        return new Complex(this.real+b.real,this.imag+b.imag) ;
    }
    Complex multiply(Complex b)
    {
        return new Complex(this.real*b.real-this.imag*b.imag,this.real*b.imag-b.real*this.imag) ;
    }
    Complex sub(Complex b)
    {
        return new Complex (this.real-b.real,this.imag-b.imag) ;
    }
    @Override
    public String toString()
    {
        return "["+real+","+imag+"]" ;
    }
    
}