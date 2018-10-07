class OneOne
{
 boolean OO (int A[],int B[],int C[][])
 {
  int l = A.length ;
  int E[] = new int[l] ;
  int F[] = new int[l] ;
  boolean flag1=false,flag2=false ;
  
  for (int i=0 ;i<l;i++)
  {
   E[i]=C[i][0] ;
   F[i]=C[i][1] ;
  }   
  for (int i=0;i<l;i++)
  {
   for (int j=0; j<l;j++)
    {
	 if (B[i]==F[j])
	 {
	  flag1=true ;
	  continue ;
	 } 
	 if(!flag1)
	 return false ;
			 
	} 
}
  return true ;
}
}
class TestOneOne
{
 public static void main(String args[])
 {
  int A[]={1,2,3},B[]={4,5,6},C[][]={{1,5},{1,6},{2,5},{2,6},{3,5},{3,6}} ;
  OneOne Alp = new OneOne() ;
  boolean kya=Alp.OO(A,B,C) ;
  System.out.println("One-One hai  kya? = "+kya) ;
 }  
} 