import java.util.* ;

class BreakingBad2
{
	public static void main(String args[]) throws Exception
	{
		Scanner in = new Scanner(System.in) ;
		int T = in.nextInt() ;
		int M = in.nextInt() ;
		for(int t=0;t<T;t++)
		{
			int max = 0 ;
			String maxStr="" ;
			for(int i=0;i<M;i++)
			{
				String name = in.next() ;
				int res = commaCounter(in.next()) ;
				if(res>=max)
				{
					maxStr = name ;
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