class Node
{
	int num = 0;
}
class TestPassByReference
{
	void increaseByOne(Node N)
	{
		N.num++ ;
	}
	public static void main(String args[])
	{
		Node N = new Node() ;
		TestPassByReference T  = new TestPassByReference() ;
		T.increaseByOne(N) ;
		System.out.println("num = "+N.num) ;
		T.increaseByOne(N) ;
		System.out.println("num = "+N.num) ;
		T.increaseByOne(N) ;
		System.out.println("num = "+N.num) ;
	}
}