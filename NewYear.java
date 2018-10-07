import java.util.* ;

public 	class NewYear
{
	public static void main(String args[]) 
	{
		Scanner in = new Scanner(System.in) ;
		long n = in.nextLong() ;
		long k = in.nextLong() ;
		if(k==1)
			System.out.println(n) ;
		else
		{
			System.out.println(2*(Long.highestOneBit(n))-1) ;
		}
	}
}