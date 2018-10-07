class FirstSetBitFromRight
{
	int fsbfr(int x)
	{
		return x & ~(x-1) ;
	}
	public static void main(String args[])
	{
		FirstSetBitFromRight F = new FirstSetBitFromRight() ;
		System.out.println(F.fsbfr(16)) ;
		
	}
	
}