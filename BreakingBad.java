import java.util.* ;
import java.io.* ;
class BreakingBad{
	public static void main(String args[]) throws Exception
	{
		BufferedReader bro = new BufferedReader(new InputStreamReader(System.in)) ;
		int T = Integer.parseInt(bro.readLine()) ;
		int M = Integer.parseInt(bro.readLine()) ;
		for(int t=0;t<T;t++)
		{
			// String[][] S = new String[M][] ;
			int max = 0;
			String maxStr = "" ;
			for(int i=0;i<M;i++)
			{
				String[] S = bro.readLine().split(" ") ;
				int res = commaCounter(S[1]) ;
				if(res>=max)
				{
					maxStr = S[0] ;
					max = res ;
				}
			}
			System.out.println(maxStr+" "+max) ;
		}
	}
	static int commaCounter(String S)
	{
		int counter = 0 ;
		for(int i=0;i<S.length();i++)
		{
			if(S.charAt(i)==',')
				counter++ ;
		}
		return counter ;
	}
	
	
}