class Point
{
 double x,y ;
 Point(double a, double b)
 {
  x = a; 
  y = b;
 }
 }
 class GrahamsScan
 {
  GrahamsScan() 
  {
   int top = -1 ;
  }
  Point min_y(Point[] P) //runs in O(n) time
  {
   double inf = Double.POSITIVE_INFINITY ;
   Point temp ;
   temp.x = inf ;
   temp.y = inf ;
   for(int i=0;i<P.length;i++)
   {
    if(P[i].y<temp.y)
	{
	 if(P[i].x<temp.x)
	 {
	  temp.x = P[i].x ;
	  temp.y = P[i].y ;
	 }
   }
  }
  return temp ;
 }
 double polarAngle(Point A, Point B)
 {
  double inf = Double.POSITIVE_INFINITY ;
  if(A.equals(B))
   return 0 ;  
  return Math.atan2((A.y-B.y),(A.x-B.x)) ; //range of atan2 is (-pi,pi], and our case has the need of the range [0,pi) 
 }
 Point[] pointMergeSort(Point[] P,Point P0,int p, int r)
 {
  if(p==r)
   return P ;
  int q = (p+r)/2 ;
  P = pointMergeSort(P,P0,p,q) ;
  P = pointMergeSort(P,P0,q+1,r) ;
  P = pointMerge(P,P0,p,q,r) ;
  return P ;
 }
 Point[] pointMerge(Point[] P,Point P0,int p, int q, int r)
 {
  double myinf = Double.POSITIVE_INFINITY ;
  int n1 = q-p+2 ;
  int n2 = r-q+1 ;
  int i,j ;
  Point L[] = new Point[n1] ;
  Point R[] = new Point[n2] ;
  for (i=0;i<n1-1;i++)
  {
   L[i] = P[p+i] ;
  }
  for(j=0;j<n2-1;j++)
  {
   R[j] = P[q+1+j] ;
  }
  L[i].y = myinf ;
  R[j].y = myinf ;
  L[i].x = 1 ;
  R[j].x = 1 ;
  int x=p ;
  i=0;
  j=0;
  while(x!=r+1)
  {
   if(polarAngle(L[i],P0)<=polarAngle(R[j],P0))
    {
	 P[x] = L[i] ;
	 i++ ;
	} 
   else
   {
    P[x] = R[j] ;
	j++ ;
   }
   x++ ;
  }
 return P ;  
 } 
  Point[] push(Point[] S,Point P)
  {
   if(top == P.length-1)
    {
	  System.out.println("StackOverflow") ;
	  return S ;
	}
   S[top++] = P ;
   return S ;   
  }
  void pop(Point[] S)
  {
   if(top == -1)
   {
    System.out.println("StackUnderflow.") ;
	return ;
   }
   top-- ;
  }
  double direction ( Point p1, Point p2, Point p3)
 {
  double x1 = p1.x, y1 = p1.y ;
  double x2 = p2.x, y2 = p2.y ;
  double x3 = p3.x, y3 = p3.y ;
  return ((x3-x1)*(y2-y1)-(x2-x1)*(y3-y1)) ;
 }
  Point[] GScan(Point[] P)
  {
   Point[] S = new Point[P.length] ;
   
   Point P0 = min_y(P) ;
   Point[] P = pointMergeSort(P,P0,0,P.length-1) ;
   S = push(S,P[0]) ;
   S = push(S,P[1]) ;
   S = push(S,P[2]) ;
   for(int i=3;i<P.length;i++)
   {
    while(direction(S[top-1],S[top],P[i])>=0)
	 S = pop(S) ; 
	S = push(S,P[i]) ;  
   }
   return S ;
  }
  public static void main(String args[])
  {
   GrahamsScan G = new GrahamsScan() ;
   Point p1 = new Point(0,0) ;
   Point p2 = new Point(1,0) ;
   Point p3 = new Point(2,0) ;
   Point p4 = new Point(0,1) ;
   Point p5 = new Point(1,1) ;
   Point p6 = new Point(2,1) ;
   Point p7 = new Point(0,2) ;
   Point p8 = new Point(1,2) ;
   Point p9 = new Point(2,2) ;
   Point p10 = new Point(1,0.5) ;
   Point p11 = new Point(1,1.5) ;
   Point p12 = new Point(0.5,1) ;
   Point p13 = new Point(1.5,1) ;
   Point P[] = {p1,p2,p3,p4,p5,p6,p7,p8,p9,p10,p11,p12,p13} ;
   Point P_new  = G.GScan(P) ;
   for(int i=0;i<P_new.length;i++)
   {
    System.out.println("("+P_new[i].x+","+P_new[i].y+") ") ;
   }
   
  }
 }