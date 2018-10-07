import java.util.* ;

public class GivenLengthAndSum
{
	
	static void printArray(int[] Ar)
	{
		for(int i:Ar)
			System.out.print(i) ;
		System.out.print(" ") ;
	}
	static int[] reverse(int[] Ar)
	{
		for(int i=0;i<Ar.length/2;i++)
		{
			int temp = Ar[i] ;
			Ar[i] = Ar[Ar.length-1-i] ;
			Ar[Ar.length-1-i] = temp ;
		}
		return Ar ;
	}
	static int[] maxSolve2(int m,int s)
	{
		int nines = s/9 ;
		int[] Ar = new int[m] ;
		int i ;
		for(i=0;i<nines;i++)
			Ar[i] = 9 ;
		if(i<m)
			Ar[i] = s%9 ;
		return Ar ;
	}
	static int[] minSolve2(int[] maxA)
		{
			int i;
			for(i=0;i<maxA.length;i++)
			{
				if(maxA[i]==0)
					break ;
			}
			if(i==maxA.length)
				return reverse(maxA) ;
			if(i==1 && maxA[0]==1)
				return maxA ;
			maxA[i-1]-- ;
			maxA[maxA.length-1] = 1 ;
			return reverse(maxA) ;
		}
	public static void main(String args[])
	{
		int noUse ;
		Scanner in = new Scanner(System.in) ;
		int m = in.nextInt(),s = in.nextInt() ;
		if(m==1 && s==0)
			System.out.println("0 0") ;
		else if(s==0 || s/(float)m>9  )
			System.out.println("-1 -1") ;
		else
		{
			int[] Arr = maxSolve2(m,s) ;
			int[] Arr2 = new int[m] ;
			System.arraycopy(Arr,0,Arr2,0,Arr.length) ;
			printArray(minSolve2(Arr2)) ;
			printArray(Arr) ;
		}	
		
	}
}
