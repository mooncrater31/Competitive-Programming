class CPP4REAL
{
 double[] thy_strip(int A[], int B[], int p,int q,int r, int d)
 {
  int arr[r-p+1] ;
  int c = A[q],k=0 ;
  for(int i=p;i<=r ;i++)
  {
   if(i!=q)
   {
    if(mod(c-A[i])<=d)
	 arr[k++] = i ;
   }	 
  }
  
 } 
 int mod(x)
 {
  if(x<0)
  return -x ;
  return x ;
 }  
 double distance(int A[],int B[],int p,int r)
  {
   int x1,x2,y1,y2;	
   x1 = A[p] ;
   x2 = A[r] ;
   y1 = B[p] ; 
   y2 = B[r] ;
   return Math.sqrt(Math.pow(x1-x2,2)+Math.pow(y1-y2,2)) ;
  }
  double[] division(int A[],int B[], int p, int r)
 {
  if(p==r)
   { 	
    double arr[] = new double[3] ;
	arr[0] = mydoubleinf ;
	return arr ;
   }   
  double L[],R[],d_min[],d,smaller ;
  int q ;
  q = (p+r)/2 ;
  
  L = division(A,B,p,q) ;
  R = division(A,B,q+1,r) ;
   if(L[0]<=R[0])
    smaller = 0 ;
   else
    smaller = 1 ;
   d = L[0]<=R[0]?L[0]:R[0] ;
   d_min = thy_strip(A,B,p,q,r,d) ; 
   if (d_min[0]<=d)
    smaller = 2 ;
   switch(smaller)
   {
     case 0 :
     min[0] = L[0] ;
     min[1] = L[1] ;
     min[2] = L[2] ;
     case 1:
     min[0] = R[0] ;
     min[1] = R[1] ;
     min[2] = R[2] ;
     case 2:
     min[0] = d_min[0] ;
     min[1] = d_min[1] ;
     min[2] = d_min[2] ;
  }	 	
   return min ;
 }
  public static void main(String args[])
  {
   MergeSort M = new MergeSort() ;
   int A[] = {-1,0,0,1,2,2} ; //Assuming the x-coordinates are already sorted
   int B[] = {1,0,-2,0,1,-1} ;
   int C[] = new int[3] ;
   C = division(A,B,0,A.length-1) ;
   for(int x=0;x<A.length ; x++)
   System.out.print(A[x]+" ") ;
 }
 