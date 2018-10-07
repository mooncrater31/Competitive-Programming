import java.util.* ;

public class Segments
{
	public static void main(String args[])
	{
		Scanner in = new Scanner(System.in) ;
		int n = in.nextInt() ;
		if(n==1)
			System.out.println(1) ;
		else
		{
			int mids = n%2==1?n/2+1:n/2 ;
			int segs = n*(n+1)/2 ;
			int layers = (segs+mids)/2 ;
			System.out.println(layers) ;
		}
	}
}