class BinaryBitIncrement
{
	public static void main(String args[])
	{
		BinaryBitIncrement B = new BinaryBitIncrement() ;
		int n = 63 ;
		System.out.println(B.binaryBitIncrement(n)) ;
		int m = 1 ;
		System.out.println(B.reversedBitIncrement(m,4)) ;
	}
	int binaryBitIncrement(int n)
	{
		int count = 0; // it counts the number of right shifts
		/* int k = n & 1 ;
		while(k==1)// this is working
		{
			n = n >> 1 ;
			k = n & 1 ;
			count++ ;
		} */
		while((n&1)==1)// the bracket is necessary, '=='  has a high precedence than '&'
		{
			n = n >> 1;
			count++ ;	
		}
		n = n|1 ;// the loop will end if the LSB of n is 0. Otherwise, if initially n didn't have any 1 as LSB then too this line works. It changes the 0 to 1
		n = n<<count ; // shift the bit-sequence by count times to the left!
		return n ;
	}
	int reversedBitIncrement(int n,int k)
	{
		int count = 0;
		int pwr = (int)Math.pow(2,k-1) ; 
		int pwr2 = 2*pwr-1 ;
		while(((n>>(k-1))&1) == 1)
		{
			n = n<<1 ;
			count++ ;
		}
		n = n&pwr2 ;
		 	
		n = n+pwr ;
		n = n>>count ;
		return n&pwr2 ;
	}
	
}