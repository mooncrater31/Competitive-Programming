class Point
{
 double x,y ;
 Point(double a, double b)
 {
  x = a ;
  y = b ;
 }
} 
 
public class SegmentsIntersect
{
 double Direction ( Point p1, Point p2, Point p3)
 {
  double x1 = p1.x, y1 = p1.y ;
  double x2 = p2.x, y2 = p2.y ;
  double x3 = p3.x, y3 = p3.y ;
  return ((x3-x1)*(y2-y1)-(x2-x1)*(y3-y1)) ;
 }
 boolean OnSegment(Point p1, Point p2, Point p3)
 {
  SegmentsIntersect S = new SegmentsIntersect() ;
  double x1 = p1.x, y1 = p1.y ;
  double x2 = p2.x, y2 = p2.y ;
  double x3 = p3.x, y3 = p3.y ;
  if(S.min(x1,x2)<=x3 && x3<=S.max(x1,x2) && S.min(y1,y2)<=y3 && y3<=S.max(y1,y2))
  return true ;
  return false ;
 }
public boolean SIntersect(Point p1, Point p2, Point p3, Point p4)
 {
  SegmentsIntersect S = new SegmentsIntersect() ;
  double d1 = S.Direction(p3,p4,p1) ;
  double d2 = S.Direction(p3,p4,p2) ;
  double d3 = S.Direction(p1,p2,p4) ;
  double d4 = S.Direction(p1,p2,p3) ;
  if(((d1>0 && d2<0) || (d1<0 &&d2>0)) && ((d3>0 && d4<0) || (d3<0 && d4>0)))
   return true ;
  else if (d1==0 && S.OnSegment(p3,p4,p1))
   return true ;
  else if(d2==0 && S.OnSegment(p3,p4,p2))
   return true ;
  else if(d3==0 && S.OnSegment(p1,p2,p3))
   return true ;
  else if(d4==0 && S.OnSegment(p1,p2,p4))
   return true ;
  else return false ;
 }
 double min(double a, double b)
 {
  if(a<b)
   return a ;
  return b ;
 }
 double max(double a, double b)
 {
  if(a>b)
   return a ;
  return b ;
 }  
 public static void main(String args[])
 {
  Point p1 = new Point(1,1) ;
  Point p2 = new Point(1,-1) ;
  Point p3 = new Point(0,0) ;
  Point p4 = new Point(2,0) ;
  SegmentsIntersect S = new SegmentsIntersect() ;
  System.out.println(S.SIntersect(p1,p2,p3,p4)) ; 
 }
} 
 
