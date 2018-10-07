import java.util.* ;
class Point
{
	double x,y ;
    Point(double a, double b)
    {
		x = a ;
        y = b ; 		
    }		
}
class MaximalLayers
{
	Point[][] MLayers (Point[] P)
	{    
	     Point[][] mlayers = new Point[P.length][] ;
		 System.out.println("P:") ;
		  for(int i=0;i<P.length;i++)
			 System.out.print("("+P[i].x+","+P[i].y+") ") ;
		 Point[] y_sort = pointSorty(P,0,P.length-1) ;
		 System.out.println("P:") ;
		  for(int i=0;i<P.length;i++)
			 System.out.print("("+P[i].x+","+P[i].y+") ") ;
		 System.out.println("\ny_sort:") ;
		 for(int i=0;i<y_sort.length;i++)
			 System.out.print(y_sort[i].x+","+y_sort[i].y+" ") ;
		 Point[] x_sort = pointSortx(P,0,P.length-1) ;// After this line y_sort changes!
		 System.out.println("\nP:") ;
		  for(int i=0;i<P.length;i++)
			 System.out.print("("+P[i].x+","+P[i].y+") ") ;
		 System.out.println("\ny_sort:") ;
		 for(int i=0;i<y_sort.length;i++)
			 System.out.print(y_sort[i].x+","+y_sort[i].y+" ") ;
		 System.out.println("\nx_sort:") ;
		 for(int i=0;i<x_sort.length;i++)
			 System.out.print(x_sort[i].x+","+x_sort[i].y+" ") ;
		 boolean[] x_avail = new boolean[P.length] ;
		 Arrays.fill(x_avail,true) ;
		 int top = P.length-1 ;
		 int i=P.length-1 ;
		 int j=0 ;
		 while(i>=0)
		 {
			 if(x_avail[i])
			 {
				 int pos = ybinaryPointSearch(y_sort,x_sort[i]) ;
				 System.out.println(pos) ;
				 mlayers[j] = new Point[top-pos+1] ;
				 int truth=0 ;
				 for(int k=pos;k<=top;k++)
				 {
					 mlayers[j][truth] = y_sort[k] ;
					 truth++ ;
				 }
				 top = pos -1 ;
				 for(int k=0;k<mlayers[j].length;k++)
				 {
					 int pos2 = xbinaryPointSearch(x_sort,mlayers[j][k]) ;
					 x_avail[pos] = false ;
				 }
				 j++ ;
			 }
		 }
		 return mlayers ;
		 
	}
	int ybinaryPointSearch(Point[] P, Point P0)
	{
		int p=0,r = P.length-1 ;
		while(p!=r)
		{
		 int q = (p+r)/2 ;
		 if(P[q].y == P0.y)
		  return q ;
	     if(P[q].y < P0.y)
			 p = q+1 ;
		 else
			 r = q-1 ;
		}
		return -1 ;
		
	}
	int xbinaryPointSearch(Point[] P, Point P0)
	{
		
		int p=0,r = P.length-1 ;
		while(p!=r)
		{
		 int q = (p+r)/2 ;
		 if(P[q].x == P0.x)
		  return q ;
	     if(P[q].x < P0.x)
			 p = q+1 ;
		 else
			 r = q-1 ;
		}
		return -1 ;
	}
	public static void main(String args[])
	{
		MaximalLayers M = new MaximalLayers() ;
		Point p1 = new Point(4,6) ;
		Point p2 = new Point(3,2) ;
		Point p3 = new Point(2,3) ;
		Point p4 = new Point(1.6,4) ;
		Point p5 = new Point(1,1) ;
		Point p6 = new Point(1.5,-1) ;
		Point[] P = {p1,p2,p3,p4,p5,p6} ;
		Point[][] m = M.MLayers(P) ;
		int i=0 ;
		while(m[i][0]!=null)
		{
			for(int j=0;j<m[i].length;j++)
			{
				System.out.print("("+m[i][j].x+","+m[i][j].y+") ") ;
			}
			System.out.println() ;
			i++ ;
		}
		
	}
	Point[] pointSortx(Point[] P, int p, int r)
	{
	   if(p==r)
        return P ;
	   Point[] grave = new Point[r-p+1]  ;
       int q = (p+r)/2 ;
       grave = pointSortx(P,p,q) ;
       grave = pointSortx(P,q+1,r) ;
       grave = pointMergex(grave,p,q,r) ;
       return grave ;	   
	}
	Point[] pointMergex(Point[] P, int p, int q, int r)
	{
		
		double myinf = Double.POSITIVE_INFINITY ;
		Point inf = new Point(myinf,myinf) ;
		int n1 = q-p+2 ;
		int n2 = r-q+1 ;
		int i,j ;
		Point[] L = new Point[n1] ;
		Point[] R = new Point[n2] ;
		
		for(i=0;i<n1-1;i++)
		{
			L[i] = P[p+i] ;
		}
		for(j=0;j<n2-1;j++)
		{
			R[j] = P[q+1+j] ;
		}
		
		L[i] = inf ;
		R[j] = inf ;
		
		int alpha = p; 
		i=0  ;
		j=0 ;
        while(alpha!=r+1)
		{
			if(L[i].x<=R[j].x)
			{
				P[alpha] = L[i] ;
				i++ ;
			}
			else
			{   
		     
				P[alpha] = R[j] ;
				j++ ;
				
			}
			alpha++ ;
		}
		return P ;
	}
	Point[] pointSorty(Point[] P, int p, int r)
	{
		if(p==r)
        return P ;
	   Point[] grave = new Point[r-p+1] ;
       int q = (p+r)/2 ;
       grave = pointSorty(P,p,q) ;
       grave = pointSorty(P,q+1,r) ;
       P = pointMergey(grave,p,q,r) ;
       return grave ;
	}
	Point[] pointMergey(Point[] P, int p, int q , int r)
	{
		double myinf = Double.POSITIVE_INFINITY ;
		Point inf = new Point(myinf,myinf) ;
		int n1 = q-p+2 ;
		int n2 = r-q+1 ;
		int i,j ;
		Point[] L = new Point[n1] ;
		Point[] R = new Point[n2] ;
		for(i=0;i<n1-1;i++)
		{
			L[i] = P[p+i] ;
		}
		for(j=0;j<n2-1;j++)
		{
			R[j] = P[q+1+j] ;
		}
		L[i] = inf ;
		R[j] = inf ;
		int x = p; 
		i=0  ;
		j=0 ;
        while(x!=r+1)
		{
			if(L[i].y<=R[j].y)
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
}