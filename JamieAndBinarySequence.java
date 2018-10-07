import java.util.* ;
public class JamieAndBinarySequence
{
	public static void main(String args[])
	{
		Scanner in = new Scanner(System.in) ;
		int n = in.nextInt(),k=in.nextInt() ;
		if(numberOfSetBits(n)>k)
			System.out.println("NO") ;
		else
		{
			if(n>k)
			{
				
			}
			else
			{
				
			}
		}
	}
	static numberOfSetBits(int n)
	{
	}
	static int[] moreThanK(int n,int k,int num,int times)
	{
		if(n==0)
			return null ;
		if(n<k)
			return lessThanK(n,k) ;
		int maxN = Integer.numberOfLeadingZeros(n) ;
		int neededMax ;
		for(int i=0;1<<i!=maxN;i++)
		{
			if(n/(1<<i)<=k)
				break ;
		}
		neededMax = 1<<i ;
		int div1 = n/neededMax ;
		if(div1==k)
		{
			return ;
		}
		for(int i=div1;i!=0;i--)
		{
			moreThanK(
		}
	}
	static int[] lessThanK(int n,int k)
	{
		
	}
}