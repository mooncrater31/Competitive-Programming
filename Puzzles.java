import java.util.* ;

public class Puzzles
{
	public static void main(String args[])
	{
		Scanner in = new Scanner(System.in) ;
		int n = in.nextInt() ;
		int m = in.nextInt() ;
		int[] A = new int[m] ;
		for(int i=0;i<m;i++)
		{
			A[i] = in.nextInt() ;
		}
		Arrays.sort(A) ;
		int min = Integer.MAX_VALUE ;
		for(int i=0;i<m-n+1;i++)
		{
			if(A[i+n-1]-A[i]<min)
				min = A[i+n-1]-A[i] ;
				
		}
		System.out.println(min) ;
	}
}
