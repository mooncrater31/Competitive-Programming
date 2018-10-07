  class JustSort
{
 int[] JSort(int A[],int p, int r)
 {
  int a,j=0 ;
  for(int i=p;i<r;i++)
  {
   a = A[i+1] ;
   j=i ;
   while(j>=p && A[j]>a  )
   {
    A[j+1] = A[j] ;
	j-- ;
   }
   A[j+1] = a ;    
  }
  return A ;
 }
 
 
 public static void main(String args[])
 {
  int A[] = {2,5,1,4,3} ;
  JustSort J = new JustSort() ;
  
  A  = J.JSort(A,0,A.length-1) ;
  for(int x=0;x<A.length;x++)
   System.out.print(A[x]+" ") ; 
 }
 }
   