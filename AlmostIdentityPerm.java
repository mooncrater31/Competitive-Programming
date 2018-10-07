import java.util.* ;
import java.io.* ;

public class AlmostIdentityPerm
{
	static int derangement(int k)
	{
		if(k==2)
			return 1 ;
		else if(k==3)
			return 2 ;
		else if(k==4)
			return 9 ;
		return 0 ;
	}
	static long combi(int n,int k)
	{
		long mul_n = 1 ;
		int temp = n ;
		// System.out.println("n is "+n+" and k is "+k) ;
		while(temp!=(n-k))
		{
			mul_n = mul_n*temp ;
			// System.out.println("mul_n is "+mul_n) ;
			temp-- ;
		}
		long mul_k = 1 ;
		int temp2 = k ;
		while(temp2!=0)
		{
			mul_k = mul_k*temp2 ;
			// System.out.println("mul_k is "+mul_k) ;

			temp2-- ;
		}		
		// System.out.println("mul_n is:"+mul_n+" and mul_k is:"+mul_k) ;
		// System.out.println("mul_n is:"+mul_n+" and mul_k is:"+mul_k) ;
		return (mul_n/mul_k) ;
	}
	public static void main(String args[]) throws Exception
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)) ;
		String line = br.readLine() ;
		int N = Integer.parseInt(line.split(" ")[0]) ;
		int K = Integer.parseInt(line.split(" ")[1]) ;
		// System.out.println("N is "+N+" and K is "+K) ;
		long sum = 0;
		for(int i=K;i>=2;i--)
		{
			sum = sum + combi(N,i)*derangement(i) ;
		}
		System.out.println(sum+1) ;
	}
	
}