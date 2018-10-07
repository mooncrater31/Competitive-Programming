class ConsecutiveRemoval
{
	static void print_array(int[] A)
	{
		for(int i=0;i<A.length;i++)
		{
			System.out.print(A[i]+" ") ;
		}
		System.out.println() ;
	}
	static int[] consecutive_removal(int[] A)
	{
		int first = -1 ;
		boolean consec = false ;
		for(int i=0;i<A.length-1;i++)
		{
			if(A[i]==A[i+1])
			{
				first = i ;
				consec = true ;
				System.out.println("first = "+first) ;
				break ;
			}
			
		}
		if(consec)
		{
		int[] A2 = new int[A.length-2] ;
		int j=0;
		for(int i=0;i<A.length;i++)
		{
			
			if(i!=first && i!=(first+1))
			{
				A2[j] = A[i] ;
				System.out.println("A2["+j+"] = "+A2[j]+" = A["+i+"] = "+A[i]) ;
				j++ ;
			}
		}
		print_array(A2) ;
		return consecutive_removal(A2) ;
		}
		else
			return A ;
		
		
	}
	public static void main(String args[])
	{
		int[] A = {1,2,3,4,5,5,4,3,2,1} ;
		A = consecutive_removal(A) ;
		
		print_array(A) ;
	}
}