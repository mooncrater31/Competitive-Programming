import java.util.* ;
class InRow
{
	public static void main(String args[])
	{
		Scanner in = new Scanner(System.in) ;
		int N = in.nextInt() ;
		int[] A = new int[N] ;
		for(int i=0;i<N;i++)
		{
			A[i] = in.nextInt() ;
		}
		for(int i=0;i<N;i++)
		{
			System.out.print(A[i]+",") ;
		}
	}
}
