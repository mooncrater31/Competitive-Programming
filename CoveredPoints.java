import java.io.BufferedReader ;
import java.io.InputStreamReader ;
import java.util.* ;

public class CoveredPoints
{
    public static void main(String args[]) throws Exception
    {
        BufferedReader bro = new BufferedReader(new InputStreamReader(System.in)) ;
        int N = Integer.parseInt(bro.readLine()) ;
        Segment[] SA = new Segment[N] ;
        long ans = 0 ;
        for(int i=0;i<N;i++)
        {
            String[] S = bro.readLine().split(" ") ;
            long x1 = Long.parseLong(S[0]) ;
            long y1 = Long.parseLong(S[1]) ;
            long x2 = Long.parseLong(S[2]) ;
            long y2 = Long.parseLong(S[3]) ;
            SA[i] = new Segment(x1,y1,x2,y2) ;
            ans+=get(SA[i]) ;
        }
       
        for(int i=0;i<N;i++)
        {
            HashSet<Point> H = new HashSet<Point>() ;
            for(int j=i+1;j<N;j++)
            {
                Point p = intersect(SA[i],SA[j]) ;
                if(p!=null)
                    H.add(p) ;
            }
            ans-=H.size() ;
        }
        System.out.println( ans) ;
    } 
    static long get(Segment S)
    {
        return gcd(Math.abs(S.x2-S.x1),Math.abs(S.y2-S.y1))+1 ;
    }
    static long gcd(long a,long b)
    {
        if(a==0) return b ;
        return gcd(b%a,a) ;
    }
    static long det(long a,long b,long c,long d) 
    {
        return a*d-b*c ;
    }
    static boolean in(long x,long y,Segment a)
    {
        long x1 = Math.min(a.x1,a.x2) ;
        long x2 = Math.max(a.x1,a.x2) ;
        long y1 = Math.min(a.y1,a.y2) ;
        long y2 = Math.max(a.y1,a.y2) ;
        
        if(x>=x1 && x<=x2 && y>=y1 && y<=y2) return true ;
        return false ;
    }
    static Point intersect(Segment a,Segment b)
    {
        Line l1 = new Line(a) ;
        Line l2 = new Line(b) ;
        long dy = det(l1.A,l1.C,l2.A,l2.C) ;
        long dx = det(l1.C,l1.B,l2.C,l2.B) ;
        long d = det(l1.A,l1.B,l2.A,l2.B) ;
        if(d==0) return null ;
        if(dx%d!=0 ||dy%d!=0) return null ;
        long x = -dx/d,y=-dy/d ;
        if(!in(x,y,a) || !in(x,y,b)) return null ;
        return new Point(x,y) ;
        
    }
}
class Segment
{
    long x1,y1,x2,y2 ;
    Segment(long x1,long y1,long x2,long y2)
    {
        this.x1 = x1 ;
        this.y1 = y1 ;
        this.x2 = x2 ;
        this.y2 = y2 ;
    }
}
class Point 
{
    long x,y ;
    Point(long x,long y)
    {
        this.x = x ;
        this.y = y ;
    }
    @Override
    public int hashCode()
    {
        return (int)((31*x+y+(int)(1e9+7))%((long)1e9+7)) ;
    }
    @Override 
    public boolean equals(Object o)
    {
        if(o instanceof Point)
        {
            Point p = (Point)o ;
            return p.x==x && p.y==y ;
        }
        return false ;
    }
    
}
class Line 
{
    long A,B,C ;
    Line(){} 
    Line(Segment a)
    {
        A = a.y1-a.y2 ;
        B = a.x2-a.x1 ;
        C = -A*a.x1-B*a.y1 ;
    }
}
