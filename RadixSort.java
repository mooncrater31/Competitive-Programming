public class RadixSort
{
	int[] rObjSort(int[] A,int[] D)
	{
		int[] C = new int[10] ;//already initialized to 0
		int[] B = new int[A.length] ;
		int[] oA = new int[A.length] ;
		for(int i=0;i<D.length;i++)
		{
			C[D[i]] = C[D[i]]+1 ;
		}
		for(int i=1;i<=9;i++)
		{
			C[i] = C[i-1]+C[i] ;
		}
		
		for(int i=A.length-1;i>=0;i--)
		{
			B[C[D[i]]-1] = D[i] ;
			oA[C[D[i]]-1] = A[i] ;
			C[D[i]] = C[D[i]]-1 ;
			
		}
		return oA ;
		
	}
	double[] rObjSort(double[] A,int[] D)
	{
		int[] C = new int[10] ;//already initialized to 0
		int[] B = new int[A.length] ;
		double[] oA = new double[A.length] ;
		for(int i=0;i<D.length;i++)
		{
			C[D[i]] = C[D[i]]+1 ;
		}
		for(int i=1;i<=9;i++)
		{
			C[i] = C[i-1]+C[i] ;
		}
		
		for(int i=A.length-1;i>=0;i--)
		{
			B[C[D[i]]-1] = D[i] ;
			oA[C[D[i]]-1] = A[i] ;
			C[D[i]] = C[D[i]]-1 ;
			
		}
		return oA ;
		
	}
	int[] digits(int[] A,int i)
	{
		int[] D = new int[A.length] ;
		for(int j=0;j<A.length;j++)
		{
			D[j] = (A[j]/(int)(Math.pow(10,i-1)))%10 ;
		}
		return D ;
	}
	double[] rSort(double[] A,int d)
	{
		int[] B = new int[A.length] ;
		for(int i=0;i<A.length;i++)
		{
			B[i] = (int)(A[i]*(int)(Math.pow(10,d))) ;
		/* 	System.out.print(B[i]+" ") ; */
		}
		/* System.out.println() ; */
		
		for(int i=1;i<=d;i++)
		{
			int[] D = digits(B,i) ;
			A = rObjSort(A,D) ;
		}
		/* for(int i=0;i<B.length;i++)
		{
			System.out.print(B[i]+" ") ;
		}
		System.out.println() ;
		for(int i=0;i<A.length;i++)
		{
			A[i] = (float)B[i]/(int)(Math.pow(10,d)) ;
		} */
		
		return A ;
	}
	int[] rSort(int[] A,int d)
	{
     for(int i=1;i<=d;i++)
	 {
		 int[] D = digits(A,i) ;
		 A = rObjSort(A,D) ; 
	 }		 
	 return A ;
	}
	public static void main(String args[])
	{
		int[] A = {329,457,657,839,436,720,355} ;
		double[] B = {1.234,1.556,1.887,1.112,1.001,1.000} ;
		RadixSort R = new RadixSort() ;
		A = R.rSort(A,3) ;
		B = R.rSort(B,4) ;
		/* for(int i=0;i<A.length;i++)
		{
			System.out.print(A[i]+" ") ;
		} */
		for(int i=0;i<B.length;i++)
		{
			System.out.print(B[i]+ " ") ;
		}
	}
}