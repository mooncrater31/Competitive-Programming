class FindIfSum
{
 public static void main(String args[])
 {
  int count = 0,sum = 100;
  for(String x:args)
  count++ ;
  int A[] = new int[count] ;
  for(int i=0;i<count;i++)
  A[i]=Integer.valueOf(args[i]) ;
  boolean b = false ;
  BS B = new BS() ;
  for(int i=0;i<count;i++)
  {
   if(B.BiS(A,i+1,count,sum-A[i]))
   {
    System.out.println("The values are found.") ;
	b = true ;
	break ;
  }
  }
  if(!b)
  System.out.println("Not Found!") ;
 }
} 
  class BS/* input cases: {2},{0,1,2},{0,1} are giving incorrect answers, changed BiS(A,p,r,x) to BiS(A,p,r-1,x)....similar changes to others*/
{
 boolean BiS(int A[],int p, int q,int x)
 {
  BS B1 = new BS() ;
  int r = (p+q)/2 ;
  if(p>q)
  return false ;
  if (A[r] > x)
  return B1.BiS(A,p,r-1,x) ;
  else if(A[r]<x)
  return B1.BiS(A,r+1,q,x) ;
  else 
  return true ;
  }
 }