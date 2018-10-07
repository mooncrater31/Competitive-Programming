class TESTwht
{
	
	int p;
	int q ;
}
class TESTIT
{
  public static void main(String args[])
  {
	  TESTwht[] T = new TESTwht[10] ;
	  for(int i=0;i<T.length;i++)
	  {
		  T[i] = new TESTwht() ;
		  T[i].p = i ;
		  T[i].q = i ;
	  }
	  
  }
}