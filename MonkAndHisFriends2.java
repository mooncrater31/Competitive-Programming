import java.util.* ;
import java.io.* ;
import java.lang.Long.* ;

class MonkAndHisFriends2
{
	public static void main(String args[])
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)) ;
		// Scanner in = new Scanner(System.in) ;
		String line = br.nextLine() ;
		int tests = Integer.parseInt(line) ;
		boolean[][] result = new boolean[tests][] ;
		for(int i=0;i<tests;i++)
		{
			String line2 = br.readLine() ;
			long N = Integer.parseInt(line2.split(" ")[0]) ;
			long M = Integer.parseInt(line2.split(" ")[1]) ;
			result[i] = new boolean[(int)M] ;
			HashSet<Long> S = new HashSet<Long>() ;
			String line3 = br.readLine() ;
			for(long j=0;j<N;j++)
			{
				S.add(java.lang.Long.parseLong(line3.split(" ")[j])) ;
			}
			for(long j=0;j<M;j++)
			{
				long value = java.lang.Long.parseLong(line3.split(" ")[j+N]) ;
				if(S.contains(value))
				{
					result[i][(int)j] = true ;
				}
				else
				{
					result[i][(int)j] = false ;
					S.add(value) ;
				}
			}
		}
		for(int i=0;i<tests;i++)
		{
			for(long j=0;j<result[i].length;j++)
			{
				System.out.println(result[i][(int)j]?"YES":"NO") ;
			}
		}
	}
}