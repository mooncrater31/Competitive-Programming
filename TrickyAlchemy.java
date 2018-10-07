import java.util.* ;

public class TrickyAlchemy
{
	public static void main(String args[])
	{
		Scanner in = new Scanner(System.in) ;
		long Y = in.nextLong() ;
		long B = in.nextLong() ;
		long y = in.nextLong() ;
		long g = in.nextLong() ;
		long b = in.nextLong() ;
		long y_needed = 2*y+g-Y,b_needed = g+3*b-B ;
		if (y_needed < 0)
			y_needed = 0 ;
		if (b_needed<0)
			b_needed = 0 ;
		System.out.println(y_needed+b_needed) ;
		
		
	}
}