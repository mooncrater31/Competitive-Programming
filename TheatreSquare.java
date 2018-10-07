import java.util.* ;
public class TheatreSquare
{
	public static void main(String args[])
	{
		Scanner in = new Scanner(System.in) ;
		long n = in.nextInt() ;
		long m = in.nextInt() ;
		longf a = in.nextInt() ;	
		
		long ans = (n%a==0?(n/a):(n/a+1))*(m%a==0?(m/a):(m/a+1)) ;
		System.out.println(ans) ;
	}
}