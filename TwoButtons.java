import java.util.* ;
public class TwoButtons
{
	static boolean[] checked ;
	// static int[] reached ;
	static int[] val ;
	static int MAX = Integer.MAX_VALUE ;
	static int bfs(int n,int m)
	{
		// if(n<m && checked[n])
		// {
			// return MAX ;
		// }
		// if(n<m)
			// checked[n] = true ;
		
		if(checked[n] && val[n]==0)
			return MAX ;
		if(val[n]!=0 )
		{
			return val[n]+1 ;
		}
		checked[n]= true ;
		// System.out.println(n+" "+m) ;
		if(n>m)
		{
			val[n]= n-m ;
			return n-m ;
		}
		if(n==m)
		{
			val[n] = 0 ;
			return 0 ;
		}
		if(n==0)
			return MAX ;
		int min = Math.min(bfs(n<<1,m),bfs(n-1,m)) ;
		val[n] = min ;
		// System.out.println("	"+min+"("+n+","+m+")") ;
		
		if(min == MAX)
			return MAX ;
		else
			return (min+1) ;
		
	}
	public static void main(String args[])
	{
		Scanner in = new Scanner(System.in) ;
		int n = in.nextInt(), k =in.nextInt() ;
		if(n>k)
			System.ut.println(n-k) ;
		else
		{
			checked = new boolean[2*k-2] ;
			// reached = new int[2*k-2] ;
			val = new int[2*k-2] ;
			System.out.println(bfs(n,k)) ;
		}
	}
}
