class Point
{
 float x,y ;
 Point(float a, float b)
 {
  x = a ;
  y = b ;
 }
} 
class Colinear
{
 float[] slopes(Point[] P,int start, int end,Point p)
 {
  float slope[] = new float[end-start+1] ;
  for(int i=start;i<= P.length-1;i++)
  {
   if((P[i].x-p.x)!=0)
   slope[i-start] = (P[i].y-p.y)/(P[i].x-p.x) ;   
   else if(P[i].y-p.y>=0)
   slope[i-start] = Float.POSITIVE_INFINITY ;
   else
   slope[i-start] = Float.NEGATIVE_INFINITY ;
  }
  return slope ;
 } 
 boolean similar(float[]  A)
 {
  float a = A[0] ;
  for(int i=1;i<A.length;i++)
  {
   if(a==A[i])
    return true ;
   a = A[i] ;
  }
  return false ;
 }  
 boolean Clnr(Point[] P)
 {
  MergeSort M = new MergeSort() ;
  boolean found = false ;
  for(int i=0;i<P.length-1 && !found;i++)
  {
   float slp[] ;
   slp = slopes(P,i+1,P.length-1,P[i]) ;
   for(int j=0;j<slp.length;j++)
    System.out.print(slp[j]+" ") ;
   	
   System.out.println() ;
   slp = M.MSort(slp,0,slp.length-1) ;
   for(int j=0;j<slp.length;j++)
    System.out.print(slp[j]+" ") ;
   System.out.println() ;	
   found = similar(slp) ;
  }
  return found ;  
 }
  
 public static void main(String args[])
 {
  Colinear C = new Colinear() ;
  Point p1 = new Point(0,0) ;
  Point p2 = new Point(1,1) ;
  Point p3 = new Point(1,2) ;
  Point p4 = new Point(3,2) ;
  Point p5 = new Point(3,3) ;
  Point P[] = {p1,p2,p3,p4,p5} ;
  System.out.println(C.Clnr(P)) ;
 }
 
}