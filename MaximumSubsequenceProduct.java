import java.util.* ;
import java.io.BufferedReader ;
import java.io.InputStreamReader ;

public class MaximumSubsequenceProduct
{
	public static void main(String args[]) throws Exception
	{
		
	}
	static BigInteger solveWithZeros(List<BigInteger> A)
	{
		int lastZero = -1 ;
		BigInteger max = BigInteger.ZERO ;
		int zeros = 0 ;
		for(int i=0;i<A.size();i++)
		{
			if(A.get(i)==0)
			{
				zeros++ ;
				BigInteger temp = solveWithoutZeros(A,lastZero+1,i-1) ;
				max = max.compareTo(temp)==1?temp:max ;
				lastZero = i ;
			}
		}
		if(zeros==0)
			return solveWithoutZeros(A,0,A.size()-1) ;
		return max ;
	}
	static BigInteger solveWithoutZeros(List<BigInteger> A,int from,int to)
	{
		if(from==to) return A.get(from) ;
		else if(from>to) return BigInteger.ZERO ;
		BigInteger findMin  = BigInteger.valueOf(Integer.MAX_VALUE) ;
		boolean negNotSeen = false ;
		BigInteger product = BigInteger.ONE ;
		for(int i=from ;i<=to;i++)
		{
			product = product.multiply(A.get(i)) ;
			if(!flag && product.compareTo(BigInteger.ZERO)==-1)
			{
				findMin = product ;
				flag = true ;
			}
			else if(product.compareTo(BigInteger.ZERO)==-1)
			{
				BigInteger temp = product.divide(findMin) ;
				if(temp.compareTo(product.divide(A.get(i)))==1)
				{
					product = temp ;
				}
				
			}
			
		}
	}
}