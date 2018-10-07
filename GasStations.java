import java.util.* ;
import java.io.BufferedReader ;
import java.io.InputStreamReader ;

public class GasStations
{
	public static void main(String args[]) throws Exception
	{
		BufferedReader bro = new BufferedReader(new InputStreamReader(System.in)) ;
		String[] s1 = bro.readLine().split(" ") ;
		int N = Integer.parseInt(s1[0]) ;
		long X = Long.parseLong(s1[1]) ;
		int count = 0 ;
		long val = X ;
		String[] S = bro.readLine().split(" ") ;
		for(int i=0;i<S.length;i++)
		{
			val = val - Long.parseLong(S[i]) ;
			count++ ;
			if(val<=0)
				break ;
		}
		System.out.println(count) ;
	}
	
}