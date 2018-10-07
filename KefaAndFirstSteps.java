import java.util.* ;

public class KefaAndFirstSteps
{
	public static void main(String args[])
	{
		Scanner in = new Scanner(System.in) ;
		int n=in.nextInt() ;
		int[] A = new int[n] ;
		for(int i=0;i<n;i++)
		{
			A[i] = in.nextInt() ;
		}
		int i_main = 0,j_main =0,i2=0,j2=0 ;
		for(int i=1;i<n;i++)
		{
			
			if(A[i]>=A[j2])
			{
				j2++ ;
			}
			else 
			{
				i2 = i ;
				j2 = i ;
			}
			if(j_main-i_main<j2-i2)
			{
				i_main = i2 ;
				j_main = j2 ;	
			}
			// System.out.println("j2 :"+j2) ;
		}
		// System.out.println("j_main :"+j_main+" i_main :"+i_main) ;
		System.out.println(j_main-i_main+1) ;
	}
}