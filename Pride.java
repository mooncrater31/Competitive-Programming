import java.util.* ;

public class Pride
{
	public static void main(String args[])
	{
		Scanner in  = new Scanner(System.in) ;
		int N = in.nextInt() ;
		int[] arr = new int[N] ;
		int ones = 0 ;
		for(int i=0;i<N;i++)
		{
			arr[i] = in.nextInt() ;
			if(arr[i]==1)
				ones++ ;
		}
		if(ones!=0)
			System.out.println(N-ones) ;
		else System.out.println(arrayChecker(arr)) ;
		
	}
	static int gcd(int a,int b)
	{
		if(a==0)
			return b ;
		else if(b==0)
			return a;
		else if(a==1 || b==1)
			return 1 ;
		else if(a%b==0)
			return b;
		else
		{
			return gcd(b,a%b) ;
		}
	}

	static int arrayChecker(int[] A)
	{
		int dist = A.length ;
		for(int i=0;i<A.length;i++)
		{
			for(int j=i;j<A.length;j++)
			{
				if(gcd(A[i],A[j])==1)
					dist = (j-i)<dist?(j-i):dist ;
			}
		}
		if(dist==A.length)
			return -1 ;
		return dist+A.length-1; 
	}
}