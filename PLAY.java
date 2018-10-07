class Merge
{
 int[] merge(int[] A,int p,int q,int r)//p=0,q=3,r=7 and the array is {4,6,8,9,1,3,5,7}
 {
  int i,j=0,k=0 ;
  int L[] = new int[q-p+2] ; //both have a length of 5
  int R[] = new int[r-q+1] ;
  Integer myInf = Integer.MAX_VALUE ;
  L[q-p+1] =  myInf ;
  R[r-q] = myInf ;
  for (i = 0 ; i<q-p+1 ;i++)
  {
   L[i] = A[i+p] ;
  }
  for(int x=0;x<q-p+2;x++)
  System.out.println("At index "+x+" value of L["+x+"] is "+L[x]) ;
  for (i=0 ;i<r-q;i++)
  {
   R[i] = A[i+q+1] ;
  }
  for(int x=0;x<r-q+1;x++)
  System.out.println("At index "+x+" value of R["+x+" is "+R[x]) ;//correct until here
   
  for (i = p;i<=r ;i++)//the problem is here. L = {4,6,8,9,10000} and R = {1,3,5,7,10000} and j=k=1
  {
    if(L[j]<=R[k])
	{
	 A[i] = L[j] ;
	 j++;
	}
   else
    {
	 A[i] = R[k] ;   
	 k++ ;
	} 
  }
  return A ;
 }
} 
class PLAY
{
 public static void main(String args[])
 {
  int A[] = {4,6,8,9,1,3,5,7} ;
  Merge M = new Merge() ;
  A = M.merge(A,0,3,7) ;
  for(int x=0;x<7;x++)
  System.out.println(A[x]) ;
 }
} 
  