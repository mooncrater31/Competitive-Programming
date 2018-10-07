import java.util.* ;
public class NewYearTransportation
{
	public static void main(String args[])
	{
		f2() ;
	}
	// static void f1()
	// {
		// Scanner in = new Scanner(System.in) ;
		// int n = in.nextInt(),k = in.nextInt() ;
		// int[] A = new int[n] ;
		// for(int i=0;i<n-1;i++)
		// {
			// A[i] = in.nextInt() ;
		// }
		// int index = 1 ;
		// while(index<k)
		// {
			// index += A[index-1] ;
			// // System.out.println(index) ;
		// }
		// System.out.println((index==k?"YES":"NO")) ;
	// }
	static void f2()
	{
		// System.out.println("It's f2() motherfuxkers!") ;
		Scanner in = new Scanner(System.in) ;
		int n= in.nextInt(),k=in.nextInt() ;
		int index = 1 ;
		int currentIndex = 1 ;
		while(index<k)
		{
			int el = in.nextInt() ;
			index+=el ;
			int toSkip = index - currentIndex-1 ;
			for(int i=0;i<toSkip;i++)
				in.nextInt() ;
			currentIndex = index ;
			
		}
		System.out.println((index==k?"YES":"NO")) ;

	}
	
}