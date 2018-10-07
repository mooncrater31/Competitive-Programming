class BinaryToDecimalVV
{
	public static void main(String args[])
	{
		BinaryToDecimalVV B = new BinaryToDecimalVV() ;
		int a = 110101 ;
		System.out.println(B.binaryToDecimal(a)) ;
		int b = 192 ;
		System.out.println(B.decimalToBinary(b)) ;
		
	}
	public int binaryToDecimal(int a)
	{
		int  n = a ;
		int sum = 0 ;
		int count = 0 ;
		while(n!=0)
		{
			if(n%10==1)
				sum=sum+(int)Math.pow(2,count) ;
			n = n/10 ;
			count++ ;
		}
		return sum ;
	}
	 public int decimalToBinary(int a)
	{
		int n=a ;
		int sum = 0 ;
		int count = 0 ;
		while(n!=0)
		{
			if(n%2==1)
			    sum = sum+(int)Math.pow(10,count) ;
			n = n/2 ;
			count++ ;
		}
		return sum ;
	} 
}