import java.util.* ;
class testes
{
	static int var = 0 ;
	void adder()
	{
		if(var!=5) 
			var++ ;
		testes T = new testes() ;
		T.adder() ;
	}
	public static void main(String args[])
	{
		testes T = new testes() ;
		T.adder() ;
		System.out.println(T.var) ;
	}
}