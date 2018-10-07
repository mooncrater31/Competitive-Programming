class BitReversal
{
	public static void main(String args[])
	{
		BitReversal B = new BitReversal() ;
		System.out.println(B.rev(4,3)) ;
	}
	int rev(int k,int a)
	{
		int x = 0 ;
		for(int i=0;i<k;i++)
		{
			x<<=1 ;
			x+=(a&1) ;
			a>>=1 ;
		}
		return x ;
	}
	int dirty8BITRev(long  a)
	{
		a = (a * 0x0202020202ULL & 0x010884422010ULL) % 1023 ;
		return a ;
	}
	
	
}