import java.util.* ;
import java.io.* ;
public class Greed
{
	public static void main(String args[]) throws Exception
	{
		// BufferedReader br = new BufferedReader(new InputStreamReader(System.in)) ;
		// String line1 = br.readLine() ;
		// String line2 = br.readLine() ;
		// String line3 = br.readLine() ;
		// int N = Integer.parseInt(line1.split(" ")[0]) ;
		// int[] rem = new int[N] ;
		// int[] cap = new int[N] ;
		// int rem_sum = 0 ;
		// for(int i=0;i<N;i++)
		// {
			// rem[i] = Integer.parseInt(line2.split(" ")[i]) ;
			// cap[i] = Integer.parseInt(line3.split(" ")[i]) ;
			// rem_sum = rem_sum + rem[i] ;
		// }
		// Arrays.sort(cap) ;
		Scanner in = new Scanner(System.in) ;
		int N = in.nextInt() ;
		if(N==2)
			System.out.println("YES") ;
		else
		{
			long[] rem = new long[N] ;
			long[] cap = new long[N] ;
			long rem_sum = 0;
			for(int i=0;i<N;i++)
			{
				rem[i] = in.nextLong() ;
				rem_sum = rem_sum+rem[i] ;
			}
			for(int i=0;i<N;i++)
			{
				cap[i] = in.nextLong() ;
			}
			long max=0, sec_max=0 ;
			for(int i=0;i<N;i++)
			{
				if(cap[i]>max)
				{
					sec_max = max ;
					max = cap[i] ;
				}
				else if(cap[i]>sec_max)
				{
					sec_max = cap[i] ;
				}
			}
			if(max+sec_max>=rem_sum)
				System.out.println("YES") ;
			else
				System.out.println("NO") ;
		}
	}
}