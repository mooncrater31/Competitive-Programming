import java.util.* ;

public class Socks
{
	private static final boolean debug = false ;
	public static void main(String args[]) 
	{
		Scanner in = new Scanner(System.in) ;
		int[] A = new int[3] ;
		A[0] = in.nextInt() ;
		A[1] = in.nextInt() ;
		A[2] = in.nextInt() ;
		Arrays.sort(A) ;
		int num = in.nextInt() ;
		System.out.println(solve(A[0],A[1],A[2],num)) ;
	}
	static int solve(int A,int B,int C,int N)
	{
		int[] p = new int[3] ;//pairs
		int[] b = new int[3] ;
		p[0] = (A/2)*3 ;
		p[1] = p[0]+((B-(A/2)*2)/2)*2 ;
		p[2] = ((C-(A/2)*2-(p[1]-p[0]))/2) ;
		b[0] = 0 ;
		b[1] = p[0]*2+(A%2==0?0:1) ;
		b[2] = (p[1]-p[0])*2+b[1]+(B%2==0?0:1) ;
		if(debug)
		{
			System.out.println("p: "+Arrays.toString(p)) ;
			System.out.println("b: "+Arrays.toString(b)) ;
		}
		if(N<=p[0])
			return f1(N) ;
		else if(N<=p[1])
			return b[1]+f2(N-p[0]) ;
		else return b[2]+2*(N-p[1]) ;
		
		
	}
	static int f1(int k)
	{
		return 6*((k-1)/3+1)+(k-1)%3-2 ;
	}
	static int f2(int k)
	{
		return 4*((k-1)/2+1)+(k-1)%2-1 ;
	}
	static int largestEven(int i)
	{
		if(i%2==0)
			return i ;
		else return i-1 ;
	}
}