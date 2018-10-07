import java.util.* ;

public class Eleven
{
	public static void main(String args[])
	{
		Scanner in = new Scanner(System.in) ;
		int n = in.nextInt() ;
		int f1 = 1,f2 = 1 ;
		for(int i=1;i<=n;i++)
		{
			
			if(i==f1 || i==f2)
			{
				System.out.print("O") ;
				// System.out.println("f2 = "+f2) ;
				int t = f1 ;
				f1 = f2 ;
				f2 = t+f2 ;
			}
			else System.out.print("o") ;
		}
	}
}