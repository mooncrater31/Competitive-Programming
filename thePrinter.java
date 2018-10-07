import java.util.* ;
class thePrinter
{
	public static void main(String args[])
	{
		Scanner in = new Scanner(System.in) ;
		int n = in.nextInt() ;
		int[] A = new int[n] ;
		for(int i=0;i<n;i++)
		{
			A[i] = in.nextInt() ;
		}
		for(int i=0;i<n;i++)
		{
			if(i!=n-1)
			System.out.print(A[i]+",") ;
			else
			System.out.print(A[i]) ;	
		}
	}
	
}