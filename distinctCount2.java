import java.util.* ;
class distinctCount2
{
	public static void main(String args[])
	{
		Scanner in = new Scanner(System.in) ;
		int tests = in.nextInt() ;
		int[] results = new int[tests] ;
		for(int i=0;i<tests;i++)
		{
			int N = in.nextInt() ;
			long X = in.nextInt() ;
			HashSet<Long> H = new HashSet<Long>() ;
			for(int j=0;j<N;j++)
			{
				H.add(in.nextLong()) ;
			}
			if(H.size() <X)
			{
				results[i] = -1 ;//bad
			}
			else if(H.size() >X)
			{
				results[i] = 0 ;//Average
			}
			else
			{
				results[i] = 1 ;//Good
			}
		}
		for(int i=0;i<tests;i++)
		{
			if(results[i]==-1)
				System.out.println("Bad") ;
			else if(results[i]==0)
				System.out.println("Average") ;
			else
				System.out.println("Good") ;
		}
	}
}