class Point
{
 double x,y ;
 Point(double a, double b)
 {
  x = a ;
  y = b ;
 }
}
class PolygonArea
{
 double triangleArea(Point p1,Point p2, Point p3)
 {
  double x1 = p1.x, y1 = p1.y ;
  double x2 = p2.x, y2 = p2.y ;
  double x3 = p3.x, y3 = p3.y ;
  
  double Area = mod((x1*(y2-y3)+x2*(y3-y1)+x3*(y1-y2))/2);
  
  return Area ;
 }
 double mod(double x)
 {
  if(x<0)
   return -x;
  return x ;
 }  
 boolean isMidIn(Point[] P,Point p1, Point p2)
 {
  Interior I = new Interior() ;
  double x = (p1.x+p2.x)/2 ;
  double y = (p1.y+p2.y)/2 ;
  Point p = new Point(x,y) ;
  return I.isInterior(P,p) ;
  
 }
 Point[] deletePoint(Point[] P,int i)
 {
  Point P1[] = new Point[P.length-1] ;
  int k=0 ;
  for(int j=0 ; j<P.length ;j++)
  {
   if(j!=i)
   {
    P1[k] = P[j] ;
    k++ ;
   }
  }
  return P1 ;
 }
 double polygonArea(Point P[])
 {
  int i=0 ;
  double A = 0;
  while(P.length>2)
  {
   int n = P.length ;
   if(isMidIn(P,P[i%n],P[(i+2)%n]))
   {
    A = A + triangleArea(P[i%n],P[(i+1)%n],P[(i+2)%n]) ;
	P = deletePoint(P,(i+1)%n) ;
   }
   i++ ;   
  }
  return A ;
 }
 public static void main(String args[])
 {
  PolygonArea PA = new PolygonArea() ;
  Point p1 = new Point(0,1) ;
  Point p2 = new Point(0.707,1.707) ;
  Point p3 = new Point(1.707,0.707) ;
  Point p4 = new Point(1,0) ;
  Point p5 = new Point(1.707,-0.707) ;
  Point p6 = new Point(0.707,-1.707) ;
  Point p7 = new Point(0,-1) ;
  Point p8 = new Point(-0.707,-1.707) ;
  Point p9 = new Point(-1.707,-0.707) ;
  Point p10 = new Point(-1,0) ;
  Point p11 = new Point(-1.707,0.707) ;
  Point p12 = new Point(-0.707,1.707) ;
  Point[] P = {p1,p2,p3,p4,p5,p6,p7,p8,p9,p10,p11,p12} ;
  System.out.println(PA.polygonArea(P)) ;
 } 
} 