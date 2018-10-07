
class Point
{
 double x,y ;
 Point(double a, double b)
 {
  x = a ;
  y = b ;
 }
}
class ClosestPointProblem
{
 double mod(double a)
 {
  if(a<0)
   return -a ;
  return a ;
 } 
 double distance (Point A, Point B)
 {
  double x1 = A.x, y1 = A.y ;
  double x2 = B.x, y2 = B.y ;
  return Math.sqrt(Math.pow(x1-x2,2)+Math.pow(y1-y2,2)) ;
 }
 double[] CPP(Point[] P,double[][] Y)
 {
  double arr[] = new double[5] ;
  double inf = Double.POSITIVE_INFINITY ;
  double[] min_d = new double[5] ;
  min_d[0] = inf ;
  int count = 0 ;
  if(P.length == 1)
   {
    arr[0] = inf ;
	return arr ;
   }
  if(P.length == 2)
  {
   arr[0] =  distance(P[0],P[1]) ;
   arr[1] = P[0].x ;
   arr[2] = P[0].y ;
   arr[3] = P[1].x ;
   arr[4] = P[1].y ;
   return arr ;
  } 
  // We assume P is already sorted according to x-coordinate 
  int n = P.length,val ;
  if(n%2!=0)
   val = (n/2)+1 ;
  else val = n/2 ; 
   
  Point S1[] = new Point[val] ;
  Point S2[] = new Point[n-val] ;
  for(int i=0;i<P.length;i++)
  {
   if(i<val)
   {
    S1[i] = P[i] ;
   }
   else
   {
    S2[i-val] = P[i] ;
   }
  }
  double mid = (S1[S1.length-1].x+S2[0].x)/2 ;
  double[][] Y1 = new double[val][2] ;
  double[][] Y2 = new double[n-val][2] ;  
  double[][] temp = new double[Y.length][2] ;
  int alpha = 0, beta = 0 ;
   
  for (int i=0;i<Y.length;i++)
  {
   if(Y[i][0] <val)
    {
	 Y1[alpha][0] = Y[i][0] ;
	 Y1[alpha][1] = Y[i][1] ;
	 alpha++ ;
	}
   else
    {
	 Y2[beta][0] = Y[i][0] - val ;
	 Y2[beta][1] = Y[i][1] ;
	 beta++ ;
    }	
  }
  double[] d1 = CPP(S1,Y1) ;
  double[] d2 = CPP(S2,Y2) ;
  double[] d = new double[5] ;
  if(d1[0]<d2[0])
  {
   System.arraycopy(d1,0,d,0,d1.length) ;
  }
  else
  {
   System.arraycopy(d2,0,d,0,d2.length) ;
  }
  for(int i=0;i<Y.length;i++)
  {
   if(mod(P[(int)Y[i][0]].x-mid)<d[0])
    {
	 temp[count][0] = Y[i][0] ;
	 temp[count][1] = Y[i][1] ;
	 count++ ;
	} 
  }
 for(int i=0;i<count;i++)
 {
  int j=i+1 ;
  while(j<count && temp[j][1] < temp[i][1]+d[0])
  {
    double ptd = distance(P[(int)temp[i][0]],P[(int)temp[j][0]]) ;
   if(ptd<d[0])
   {
    if(ptd<min_d[0])
     {
	  min_d[0] = ptd ;
	  min_d[1] = P[(int)temp[i][0]].x ;
	  min_d[2] = P[(int)temp[i][0]].y ;
	  min_d[3] = P[(int)temp[j][0]].x ;
	  min_d[4] = P[(int)temp[j][0]].y ;
	 }
   }	
   j++ ;	
  }
 }
 
 if(min_d[0]<d[0])
  return min_d ;
 return d ; 
 }
 public static void main(String args[])
 {
  ClosestPointProblem cpp = new ClosestPointProblem() ;
  MergeSort M = new MergeSort() ;
  Point p1 = new Point(2,2) ;
  Point p2 = new Point(2,6) ;
  Point p3 = new Point(3,9) ;
  Point p4 = new Point(3,10) ;
  Point p5 = new Point(4,5) ;
  Point p6 = new Point(4,9) ;
  Point p7 = new Point(6,6) ;
  Point p8 = new Point(7,4) ;
  Point p9 = new Point(7,10) ;
  Point p10 = new Point(8,5) ;
  Point P[] = {p1,p2,p3,p4,p5,p6,p7,p8,p9,p10} ;
  double Y[][] = { {0,p1.y},{1,p2.y},{2,p3.y},{3,p4.y},{4,p5.y},{5,p6.y},{6,p7.y},{7,p8.y},{8,p9.y},{9,p10.y}} ;
  /*Point p1 = new Point(1,2) ;
  Point p2 = new Point(1,11) ;
  Point p3 = new Point(7,8) ;
  Point P[] = {p1,p2,p3} ;
  double Y[][] = { {0,2},{1,11},{2,8}} ; // 2 is p1.y, 11 is p2.y and 2 is p3.y*/
  Y = M.MSort(Y,0,Y.length-1) ;
  double d[] = cpp.CPP(P,Y) ;
  System.out.println("Minimum Distance found: "+d[0]+" Between : ("+d[1]+","+d[2]+") and ("+d[3]+","+d[4]+")") ;
 } 
}// Need1-Sort Y(Asked-26/12/2016, Done-26/12/2016), Need2-returning the closest pair(Asked-26/12/2016, Done-26/12/2016)  
  