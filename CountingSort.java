public class CountingSort
{
	int[] cSort(int[] A,int k)
	{
		int[] C = new int[k+1] ;//already initialized to 0
		int[] B = new int[A.length] ;
		for(int i=0;i<A.length;i++)
		{
			C[A[i]] = C[A[i]]+1 ;
		}
		for(int i=1;i<=k;i++)
		{
			C[i] = C[i-1]+C[i] ;
		}
		
		for(int i=A.length-1;i>=0;i--)
		{
			B[C[A[i]]-1] = A[i] ;
			C[A[i]] = C[A[i]]-1 ;
			
		}
		return B ;
	}
	int find_max_value(int[] A)
	{
		int max = A[0]  ;
		for(int i=1;i<A.length;i++)
		{
			if(max<A[i])
				max = A[i] ;
		}
		return max ;
	}
	public static void main(String args[])
	{
		CountingSort C = new CountingSort() ;
		int A[] = {5,6,3,9,8,10} ;
		int k = C.find_max_value(A) ;
		System.out.println("K is: "+k) ;
		A = C.cSort(A,k) ;
		for(int i=0;i<A.length;i++)
		{
			System.out.print(A[i]+" ") ;
		}
	}
}