import java.util.* ;
class BS// input cases: {2},{0,1,2},{0,1} are giving incorrect answers
{
 int BiS(int A[],int p, int q,int x)
 {
  BS B1 = new BS() ;
  int r = (p+q)/2 ;
  if(p>q)
  return -1 ;
  if (A[r] > x)
  return B1.BiS(A,p,r-1,x) ;
  else if(A[r]<x)
  return B1.BiS(A,r+1,q,x) ;
  else 
  return r ;
  }
 }
public class RecBSearch
{
 public static void main(String args[])
 {
  Scanner in = new Scanner(System.in) ;
  BS B = new BS() ;
  /*int count = 0 ;
  for(String x:args)
  count++ ;
  int A[] = new int[count]  ;
  
  for(int x=0;x<count;x++)
  A[x]  = Integer.valueOf(args[x]) ;*/ 
  int n = 2;
  System.out.println("Enter the number of elements in the array.") ;
  int count = in.nextInt()  ;
  int A[] = new int[count] ;
  for(int i=0;i<count;i++)
  {
   System.out.println("Enter the value of the element number "+i+" of the array.") ;
   A[i] = in.nextInt() ;
  }
  int ans  = B.BiS(A,0,count-1,n) ;
  if( ans==-1)
  System.out.println("The element is not found.") ;
  else
  System.out.println("The element is found, and it's position is "+ans) ;
 }
} 