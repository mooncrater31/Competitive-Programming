class EfficientLMIS
{
	int[] elmis(int[] A)
	{
		int[][] S = new int[A.length][] ;
		for(int i=0;i<A.length;i++)
		{
			S[i] = new int[i+1] ;
		}
		int[] END = new int[A.length] ;
		int current = 0 ;
		END[0] = A[0] ;
		S[0][0] = A[0] ;
		for(int i=1;i<A.length;i++) //case 1
		{
			if(A[i]<END[0])
			{
				END[0] = A[i] ;
				S[0][0] = A[i] ;
			}
			else if(A[i]> END[current]) // case 2
			{
				System.arraycopy(S[current],0,S[current+1],0,S[current].length) ;
				S[current+1][S[current+1].length-1] = A[i] ;
				END[current+1] = A[i] ;
				current++ ;
			}
			else // case 3
			{
				int index = findMinIndex(END,A[i]) ;
				System.arraycopy(S[index],0,S[index+1],0,S[index].length) ;
				S[index+1][S[index+1].length-1] = A[i] ;
				END[index+1] = A[i] ;
			}
		}

		return S[current] ;
	}
	int findMinIndex(int[] END,int a)
	{
		for(int i=1;i<END.length;i++)
		{
			if(END[i]>a)
				return i-1 ;
		}
		return 0 ;
	}
	public static void main(String args[])
	{
		EfficientLMIS L = new EfficientLMIS() ;
		int[] A = {15,7,8,1,2,6,3,9} ;
		int[] ANS = L.elmis(A) ;
		for(int i=0;i<ANS.length;i++)
		{
			System.out.print(ANS[i]+" ") ;
		}
		
	}
}