class Point
{
	double x,y ;
	Point(double a, double b)
	{
		x = a ;
		y = b ;
	}
}
class Stack
{ 
	int  top[] = new int[2] ;
	top[0] = -1 ;
	top[1] = -1 ;
	int[][] S ;
	Stack(int l)
	{
      S = new int[2][l] ;
	}
	

	int pop(int i)
	{
		if(top[i]!=0)
			return S[i][top[i]--] ;
		else
		{
			System.out.println("StackUnderflow") ;
			return -1 ;
		}
			
	}
	void push(int i,int x)
	{
		if(top[i] == n-1)
			System.out.println("StackOverflow.") ;
		else
		{
			S[i][++top[i]] = x ;
		}
	}
	
}
class BETSP
{
	
	public static void main(String args[])
	{
      BETSP b = new BETSP() ;
      Point p1 = new Point(0,0) ;
      Point p2 = new Point(1,1) ;
      Point p3 = new Point(2,-1) ;
      Point p4 = new Point(3,1) ;
      Point p5 = new Point(4,-1) ;
      Point p6 = new Point(5,0) ;
      Point P[] = {p1,p2,p3,p4,p5,p6} ;
	  int[] T = b.betsp(P) ;
	  for(int i=0;i<T.length;i++)
	  {
		  System.out.println(T[i]+" ") ;
	  }
      /*pack p = b.betsp(P) ;
      System.out.println("l is:") ;
      for(int i=0;i<P.length;i++)
      {
		for(int j=0;j<P.length;j++)
		{
		 System.out.print(p.l[i][j]+" ") ;
		}
		System.out.println() ;
      }
		System.out.println("N is:") ;
	  for(int i=0;i<P.length;i++)
		{
			for(int j=0;j<P.length;j++)
			{
			 System.out.print(p.N[i][j]+" ") ;
			}
			System.out.println() ;
		} */

      


	}
	double dist(Point p1, Point p2)
	{
		return Math.sqrt(Math.pow(p1.y-p2.y,2)+Math.pow(p1.x-p2.x,2)) ;
	}
	
	int[] betsp(Point[] P)
	{   
	    int n = P.length ;
	    Stack s = new Stack(n) ;
		int[][] N = new int[n][n] ;
		double[][] l = new double[n][n] ;
		
		for(int j=1;j<n;j++)
		{
			for(int i = 0;i < n;i++)
			{
				if(i==0 && j==1)
				{
					l[i][j] = dist(P[i],P[j]) ;
					N[i][j] = i ;
				}
				else if(j>i+1)
				{
					l[i][j] = l[i][j-1]+dist(P[j-1],P[j]) ;
					N[i][j] = j-1 ;
				}
				else
				{
					double inf = Double.POSITIVE_INFINITY ;
					l[i][j] = inf ;
					for(int k=0;k<i;k++)
					{
						double q = l[k][i]+dist(P[k],P[j]) ;
						if(q<l[i][j])
						{
							l[i][j] = q ;
							N[i][j] = k ;
						}
					}
				}
			}
		}
		int k = 0 ;
		int i=n-2,j = n-1 ;
		while(j>0)
		{
			s.push(k,j) ;
			j = N[i][j] ;
			if(j<i)
			{
				int c = i ;
				i = j ;
				j = c ;
				k = 1- k ;
			}
		}
		s.push(0,0) ;
		int[] T = new int[n] ;
		while(s.top[1]!=0)
        {
			s.push(0,s.pop(1)) ;
		}
		for(int alp = 0 ; alp<n;alp++)
		{
			T[alp] = s.pop(0) ;
		}
		return T ;
        			
	}
}