class Point
{
 double x,y ;
 Point(double a,double b)
 {
  x = a ;
  y = b ;
 }
}
public class Interior
{
 double max(double a, double b)
 {
  if(a>b)
   return a ;
  return b ;
 }
 
 public boolean isInterior(Point[] P,Point p)
 {
  int n = P.length ;
  int count = 0 ;
  Point p1 = new Point(0,0);
  SegmentsIntersect S = new SegmentsIntersect() ;
  for(int i=0;i<P.length;i++)
  {
   p1.x = max(P[i%n].x,P[(i+1)%n].x)+1 ;
   p1.y = p.y ;
   if(S.SIntersect(P[i],P[(i+1)%n],p,p1))
    count++ ;	
  }
  if(count%2!=0)
   return true ;
  return false ; 
 }
 public static void main(String args[]) 
 {
  Interior I = new Interior() ;
  Point p1 = new Point(-1,-1) ;
  Point p2 = new Point(1,-1) ;
  Point p3 = new Point(1,1) ;
  Point p4 = new Point(-1,1) ;
  Point P[] = {p1,p2,p3,p4} ;
  Point p = new Point(0,0) ;
  System.out.println(I.isInterior(P,p)) ;
  
 }
}