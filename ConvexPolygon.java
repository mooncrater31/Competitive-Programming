class Point
{
 float x,y ;
 Point(float a, float b)
 {
  x = a ;
  y = b ;
 }
}

class ConvexPolygon
{
  
 float crossProduct(Point p1, Point p2, Point p3) 
 {
  float x1 = p1.x, y1 = p1.y ;
  float x2 = p2.x, y2 = p2.y ;
  float x3 = p3.x, y3 = p3.y ;
  return ((x3-x1)*(y2-y1)-(x2-x1)*(y3-y1)) ;// (p3-p0) x (p2-p0) 
 }
 boolean CPolygon(Point[] P)
 {
  float dir[] = new float[P.length] ;
  int n = P.length ;
  for(int i=0;i<P.length;i++)
  {
   dir[i] = crossProduct(P[i],P[(i+1)%n],P[(i+2)%n]) ;
  }   
  float a = dir[0] ;
  if(dir[0]>0)
  {
   for(int i=0;i<dir.length;i++)
   {
    if(dir[i]<=0)
	 return false ;
   }
   return true ;
  }
  else if(dir[0]<0)
  {
   for(int i=0;i<dir.length;i++)
   {
    if(dir[i]>=0)
	 return false ;
   }
   return true ;
  }
  else
   return false ;
 }
 public static void main(String args[])
 {
  Point p1 = new Point(0,0) ;
  Point p2 = new Point(1,0) ;
  Point p3 = new Point(1,1) ;
  Point p4 = new Point(0,1) ;
  Point P[]  = {p1,p2,p3,p4} ;
  ConvexPolygon C = new ConvexPolygon() ;
  System.out.println(C.CPolygon(P)) ;
}
} 