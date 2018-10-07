import java.util.* ;
import java.io.BufferedReader ;
import java.io.InputStreamReader ;

public class LargeFactorials
{
	public static void main(String args[]) throws Exception
	{
		BufferedReader bro = new BufferedReader(new InputStreamReader(System.in)) ;
		int T = Integer.parseInt(bro.readLine()) ;
		for(int t=0;t<T;t++)
		{
			int N = Integer.parseInt(bro.readLine()) ;
			Pair p = factorial(N) ;
			System.out.println((int)p.a+" "+p.b) ;
		}
		
	}
	static Pair factorial(int N)
	{
		double val = 1 ;
		int count = 0 ;
		
		for(int i=1;i<=N;i++)
		{
			val = val*i ;
			while(val>10)
			{
				val = val/10 ;
				count++ ;
			}
		}
		return (new Pair(val,count)) ;
	}
}
class Pair
{
	double a ;
	int b ;
	Pair(double a,int b)
	{
		this.a = a ;
		this.b = b ;
	}
}