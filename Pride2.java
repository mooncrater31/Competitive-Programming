import java.util.* ;
public class Pride2 
{
	
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
	static int midOps(int[] A)
	{
		int n = A.length ;
		int minL = Integer.MAX_VALUE ;
		
		for(int i=0;i<A.length;i++)
		{
			int recgcd = 0;
			for(int j=i;j<A.length;j++)
			{
				recgcd = gcd(recgcd,A[j]) ;
				if(recgcd==1)
				{
					minL = minL>(j-i)?(j-i):minL ;
					break ;
				}
			}
		}
		if(minL==Integer.MAX_VALUE)
			return -1 ;
		else
			return minL+n-1 ;
	}		
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
		else System.out.println(midOps(arr)) ;
		
	}
}