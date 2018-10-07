import java.util.* ;

public class CutRibbon
{
	static int[] dp ;
	static int a,b,c ;
	static int ribbon(int r)
	{
		// System.out.println(r) ;
		if(dp[r]!=-1)
			return dp[r] ;
		if(r-a==0)
		{
			return dp[r]=1 ;
		}
		if(r-b==0)
		{
			return dp[r] = Math.max(1,ribbon(r-a)+1) ;
		}
		if(r-c==0)
		{
			return dp[r] = Math.max(1,Math.max(ribbon(r-a),ribbon(r-b))+1) ;
		}
		return dp[r] = Math.max(r-a>0?ribbon(r-a):Integer.MIN_VALUE,Math.max(r-b>0?ribbon(r-b):Integer.MIN_VALUE,r-c>0?ribbon(r-c):Integer.MIN_VALUE))+1 ; ;
		
	}
	public static void main(String args[])
	{
		Scanner in = new Scanner(System.in) ;
		int n = in.nextInt(),a1 = in.nextInt(),b1 = in.nextInt(),c1 = in.nextInt() ;
		dp = new int[n+1] ;
		Arrays.fill(dp,-1) ;
		a = Math.min(a1,Math.min(b1,c1)) ;
		c = Math.max(a1,Math.max(b1,c1)) ;
		b = a1+b1+c1-a-c ;
		// System.out.println("a :"+a+" b :"+b+" c :"+c) ;
		System.out.println(ribbon(n)) ;	
		// System.out.println("dp:") ;
		// for(int i:dp)
		// {
			// System.out.print(i+" ") ;
		// }
	}
}