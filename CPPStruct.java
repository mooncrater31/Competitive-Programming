import java.lang.* ;
class CPP
{
 Integer myinf = Integer.MAX_VALUE ;
 double mydoubleinf = Double.POSITIVE_INFINITY ; 
 double min[] = new double[3] ;
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
  double L[],R[],d ;
  int q ;
  q = (p+r)/2 ;
  
  L = division(A,B,p,q) ;
  System.out.println("\nFor p,r="+p+","+r+" L is:") ;
   for(int x=0;x<L.length;x++)
   System.out.print(L[x]+" ") ;
  R = division(A,B,q+1,r) ;
   System.out.println("\nFor p,r="+p+","+r+" R is:") ;
   for(int x=0;x<R.length;x++)
   System.out.print(R[x]+" ") ;
  d = distance(A,B,q,q+1) ;
    System.out.println("\nFor p,r="+p+","+r+" And the distance is:") ;
   System.out.println(d) ;
  
  if(L[0]<=R[0])
  {
   if(R[0]<=d)
   {
	 min[0] = L[0] ;
	 min[1] = L[1] ;
	 min[2] = L[2] ;
   }
   else if(d<=L[0])
   {
     min[0] = d ;
	 min[1] = q ;
	 min[2] = q+1 ;
   }
   else 
   {
     min[0] = L[0] ;
	 min[1] = L[1] ;
	 min[2] = L[2] ;
   }  
  }	
  else
  {
   if(L[0]<=d)
   {
    min[0] = R[0] ;
	min[1] = R[1] ;
	min[2] = R[2] ;
   }
   else if(d<=R[0])
   {
    min[0] = d ;
	min[1] = q ;
	min[2] = q+1 ;
   }
   else
   {
    min[0] = R[0] ;
	min[1] = R[1] ;
	min[2] = R[2] ;
   }
   
  }
   
   if(p==0 && r==5)
   {
    System.out.println("\nL for 0,5 is:") ;
	for(int x=0;x<L.length;x++)
	System.out.print(L[x]+" ") ;
    System.out.println() ;
   }	
   for(int x=0;x<min.length;x++)
   System.out.print(min[x] + " ") ;
   System.out.println() ;	
   return min ;
     
  /*double min(double a, double b, double c)
  {
   return a<=b?(b<=c?a:(c<=a?c:a)):(a<=c?b:(c<=b?c:b)) ;
  } */
 }
 }
 
 class CPPStruct
 {
 public static void main(String args[])
 {
  CPP cpp = new CPP() ;
  int A[] = {-1,0,0,1,1,2,2} ;
  int B[] = {5,0,2,1,8,4,-1} ;
  double C[] = new double[3] ;
  C = cpp.division (A,B,0,A.length-1) ;
  System.out.println("Minimum distance: "+C[0]+" from "+C[1]+" to "+C[2]) ; 
 }
 }