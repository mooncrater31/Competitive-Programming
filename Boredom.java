import java.util.* ;

public class Boredom
{
	public static void main(String args[])
	{
		Scanner in = new Scanner(System.in) ;
		int n = in.nextInt() ;
		long[] A = new long[(int)1e5+1] ;
		int min = Integer.MAX_VALUE,max = Integer.MIN_VALUE ;
		for(int i=0;i<n;i++)
		{
			int val = in.nextInt() ;
			
			A[val]++ ;
			if(min>val)
				min = val ;
			if(max<val)
				max = val ;
			// System.out.println(val+" "+min+" "+max) ;
		}
		long f1 = A[min]*min,f2=0 ,f=A[min]*min;
		for(int i=min+1;i<=max;i++)
		{
			f = Math.max(f1,f2+A[i]*i) ;
			f2 = f1 ;
			f1 = f ;
		}
		System.out.println(f) ;
		
		
	}
}