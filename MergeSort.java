public class MergeSort
{
 int[] Merge(int A[],int p,int q,int r)
 {
  Integer myinf = Integer.MAX_VALUE ;
  int n1 = q-p+2 ;
  int n2 = r-q+1 ;
  int i,j ;
  int L[] = new int[n1] ;
  int R[] = new int[n2] ;
  for (i=0;i<n1-1;i++)
  {
   L[i] = A[p+i] ;
  }
  for(j=0;j<n2-1;j++)
  {
   R[j] = A[q+1+j] ;
  }
  L[i] = myinf ;
  R[j] = myinf ;
  int x=p ;
  i=0;
  j=0;
  while(x!=r+1)
  {
   if(L[i]<=R[j])
    {
	 A[x] = L[i] ;
	 i++ ;
	} 
   else
   {
    A[x] = R[j] ;
	j++ ;
   }
   x++ ;   
  }	
	return A ; //////////////////////////////////////////////////////////
 } 
  float[] Merge(float A[],int p,int q,int r)
 {
  float myinf = Float.POSITIVE_INFINITY ;
  int n1 = q-p+2 ;
  int n2 = r-q+1 ;
  int i,j ;
  float L[] = new float[n1] ;
  float R[] = new float[n2] ;
  for (i=0;i<n1-1;i++)
  {
   L[i] = A[p+i] ;
  }
  for(j=0;j<n2-1;j++)
  {
   R[j] = A[q+1+j] ;
  }
  L[i] = myinf ;
  R[j] = myinf ;
  int x=p ;
  i=0;
  j=0;
  while(x!=r+1)
  {
   if(L[i]<=R[j])
    {
	 A[x] = L[i] ;
	 i++ ;
	} 
   else
   {
    A[x] = R[j] ;
	j++ ;
   }
   x++ ;   
  }	
  return A ;
 }//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
 
 double[][] Merge(double A[][],int p,int q,int r)// indexes in 0 and values in 1
 {
  double myinf = Double.POSITIVE_INFINITY ;
  int n1 = q-p+2 ;
  int n2 = r-q+1 ;
  int i,j ;
  double L[][] = new double[n1][2] ;
  double R[][] = new double[n2][2] ;
  for (i=0;i<n1-1;i++)
  {
   L[i][0] = A[p+i][0] ;
   L[i][1] = A[p+i][1] ;
   
  }
  for(j=0;j<n2-1;j++)
  {
   R[j][0] = A[q+1+j][0] ;
   R[j][1] = A[q+1+j][1] ;
  }
  L[i][1] = myinf ;
  R[j][1] = myinf ;
  int x=p ;
  i=0;
  j=0;
  while(x!=r+1)
  {
   if(L[i][1]<=R[j][1])
    {
	 A[x][1] = L[i][1] ;
	 A[x][0] = L[i][0] ;
	 i++ ;
	} 
   else
   {
    A[x][1] = R[j][1] ;
	A[x][0] = R[j][0] ;
	j++ ;
   }
   x++ ;   
  }	
  return A ;
 }///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
 
 int[] MSort(int A[] , int p, int r)
 {
  if(p==r)
   return A ;
  int q = (p+r)/2 ;
  A = MSort(A,p,q) ;
  A = MSort(A,q+1,r) ;
  A = Merge(A,p,q,r) ;
  return A ;  
 }
 float[] MSort(float A[] , int p, int r)
 {
  if(p==r)
   return A ;
  int q = (p+r)/2 ;
  A = MSort(A,p,q) ;
  A = MSort(A,q+1,r) ;
  A = Merge(A,p,q,r) ;
  return A ;  
 }
 double[][] MSort(double A[][] , int p, int r)
 {
  if(p==r)
   return A ;
  int q = (p+r)/2 ;
  A = MSort(A,p,q) ;
  A = MSort(A,q+1,r) ;
  A = Merge(A,p,q,r) ;
  return A ;  
 }
 public static void main(String args[])
 {
  MergeSort M = new MergeSort() ;
  int A[] = {4,6,3,2,1} ;
  A = M.MSort(A,0,A.length-1) ;
  for(int x=0;x<A.length;x++)
  System.out.print(A[x]+" ") ;
  
 }
} 
