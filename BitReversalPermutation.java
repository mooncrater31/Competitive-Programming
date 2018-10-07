//Done! on 11/02/2017
class BitReversalPermutation
{
	public static void main(String args[])
	{
		int[] A = {0,1,2,3,4,5,6,7} ;
		BitReversalPermutation B = new BitReversalPermutation() ;
		A = B.bitReversalPermutation(A) ;
		for(int i=0;i<A.length;i++)
		{
			System.out.print(A[i]+" ") ;
		}
	}
	int[] bitReversalPermutation(int[] A)
	{
		boolean[] done = new boolean[A.length] ;
		BinaryBitIncrement B = new BinaryBitIncrement() ;
		int k = (int)(Math.log(A.length)/Math.log(2))  ;
		
		int rev_i = 0 ;
		for(int i=1;i<A.length;i++)
		{
		    rev_i = B.reversedBitIncrement(rev_i,k) ; //reverse incrementation
			
			if(i!=rev_i)//checks if the reverse is equal to the number or not
			{
				if(!done[i])// a boolean array records the exchanged items, to avoid double exchanges
				{
					int c = A[rev_i] ;
					A[rev_i] = A[i] ;
					A[i] = c ;
					done[rev_i] = true ;
				}
			}
			done[i] = true ;
		}
		return A ;
	}
}