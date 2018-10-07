class BS/* input cases: {2},{0,1,2},{0,1} are giving incorrect answers, changed BiS(A,p,r,x) to BiS(A,p,r-1,x)....similar changes to others*/
{
 int BiS(int A[],int p, int q,int x)
 {
  BS B1 = new BS() ;
  int r = (p+q)/2 ;
  if(p>q)
  return p ;
  if (A[r] > x)
  return B1.BiS(A,p,r-1,x) ;
  else if(A[r]<x)
  return B1.BiS(A,r+1,q,x) ;
  else 
  return r ;
  }
 }
public class RecBSearch2
{
 int RBS(int A[],int n)
 {
  BS B = new BS() ;
  int count = A.length ;
  int ans  = B.BiS(A,0,count-1,n) ;
  return ans ;
 }
} 