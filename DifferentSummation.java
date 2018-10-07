import java.util.* ;
import java.io.BufferedReader ;
import java.io.InputStreamReader ;
public class DifferentSummation
{
    static int MOD = 998244353 ;
    private static final boolean debug = false ;
    public static void main(String args[]) throws Exception,InterruptedException,java.io.IOException
    {
        BufferedReader bro = new BufferedReader(new InputStreamReader(System.in)) ;
        int n = Integer.parseInt(bro.readLine()) ;
        long[] A = new long[n] ;
        String[] S = bro.readLine().split(" ") ;
        for(int i=0;i<n;i++)
        {
            A[i] = Long.parseLong(S[i]) ;
        }
        System.out.println(solve(A)) ;
    }
    static long solve(long[] A)
    {
        long sum = 0 ;
        for(int i=0;i<64;i++)
        {
            int ones = 0 ;
            for(int j=0;j<A.length;j++)
            {
                if((A[j]&(1L<<i))==(1L<<i)) ones++; 
            }
            int zeros = A.length-ones ;
            if(debug)
            {
                System.out.println("Bit :"+i) ;
                System.out.println("Ones :"+ones+" zeros :"+zeros) ;
            }
            long tempSum = 0 ;
            for(int j=1;j<=ones;j++)
            {
                if(j%2==1)
                {
                    long pscl = mod(pascalCombinations(ones,j)) ;
                    //temp = pscl*(zeros
                    long temp = mod(pscl*(mod(zeros*mod(1L<<(zeros-1)))+mod(j*mod(1L<<zeros)))) ;//Problem can be here.
                    if(debug)
                    {
                        System.out.println("zeros :"+zeros+" j :"+j) ;
                        System.out.println("pascal("+ones+","+j+") = "+pscl) ;
                        System.out.println("temp :"+temp) ;
                    }
                    tempSum = mod(tempSum+temp) ;
                }
            }
            sum = mod(sum+mod(mod(1L<<i)*tempSum)) ;
        }
        return sum ;
    }
    static long mod(int val)
    {
        
        long temp =  (val%MOD) ;
        if(temp<0) return temp+MOD ;
        else return temp ;
    }
    static long mod(long val)
    {
        int MOD = 998244353 ;
        long temp =  (val%MOD) ;
        if(temp<0) return temp+MOD ;
        else return temp ;
    }
    
    static long pascalCombinations(int n,int k)
    {
        long r = 1;
        for(int d=1;d<=k;d++)
        {
            r = mod(mod(r*(n--))*modInverse(d,MOD)) ;
        }
        return r ;
    }
    static int modInverse(int a,int m)
    {
        int x,y ;
        int[] A = new int[2] ;
        int g = gcdExtended(a,m,A) ;
        if(g!=1) {System.out.println("INVERSE DOESNT EXIST.") ;return -1;}
        else
        {
            int res = (A[0]%m+m)%m ;
            return res ;
        }
    }
    public static int gcdExtended(int a, int b, int[] x)
	{
		// Base Case
		if (a == 0)
		{
			x[0] = 0;
			x[1] = 1;
			return b;
		}

		int[] t = {1,1}; // To store results of recursive call
		int gcd = gcdExtended(b%a, a,t);

		// Update t using results of recursive
		// call
		
		x[0] = t[1] - (b/a) * t[0];
		x[1] = t[0];
		return gcd;
	}
}