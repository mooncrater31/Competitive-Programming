class TESTF
{
	void R()
	{
	}
	void Q()
	{
		int x = 30 ;
		System.out.println(x) ;
		R() ;
		x++ ;
		System.out.println(x) ;
	}
	void P()
	{
		Q() ;
	}
	public static void main(String args[])
	{
		TESTF T = new TESTF() ;
		T.P() ;
	}



}