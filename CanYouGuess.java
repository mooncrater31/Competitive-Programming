import java.util.* ;

import java.io.BufferedReader ;
import java.io.InputStreamReader ;

public class CanYouGuess
{
	public static void main(String args[])
	{
		BufferedReader bro = new BufferedReader(new InputStreamReader(System.in)) ;
		int T = Integer.parseInt(bro.readLine()) ;
		for(int t=0;t<T;t++)
		{
			int val = Integer.parseInt(bro.readLine()) ;
			System.out.println((val==0?0:sumOfDivisors(val))) ;
		}
	}
	static long sumOfDivisors(long n)
	{
		long sum = 0 ;
		for(int i=2;i<n/2+1;i++)
		{
			if(n%i == 0)
				sum+=i ;
		}
		return sum+1 ;
	}
}