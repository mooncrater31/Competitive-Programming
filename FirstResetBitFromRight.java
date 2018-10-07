public class FirstResetBitFromRight // returns the first 0 from the right in the binary representation of x
{
	public int firstResetBitFromRight(int x)
	{
		if(x==0)
		{
			return 0 ;	
		}
		return (~x&~(~x-1)) ;
	}
	public static void main(String args[])
	{
		FirstResetBitFromRight F = new FirstResetBitFromRight() ;
		System.out.println(F.firstResetBitFromRight(15)) ;
	}
}