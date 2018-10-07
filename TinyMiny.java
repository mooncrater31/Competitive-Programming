import java.util.* ;
import java.io.BufferedReader ;
import java.io.InputStreamReader ;

public class TinyMiny
{
	public static void main(String args[]) throws Exception
	{
		BufferedReader bro = new BufferedReader(new InputStreamReader(System.in)) ;
		int T = Integer.parseInt(bro.readLine()) ;
		for(int t=0;t<T;t++)
		{
			String[] S = bro.readLine().split(" ") ;
			int a = Integer.parseInt(S[0]) ;
			int n = Integer.parseInt(S[1]) ;
			solve(a,n) ;
		}
	}
	static void solve(int a,int n)
	{
		int[] A = new int[10] ;
		long temp =  1 ;
		if(a==0)
		{
			System.out.println(n==0?"01":"0") ;
			return ;
		}
		else if(n==0)
		{
			System.out.println("1") ;
			return ;
		}
		for(int i=0;i<n;i++)
		{
			temp = temp*a ;
			for(long temp2 = temp;temp2!=0;temp2=temp2/10)
			{
				A[(int)(temp2%10)]++ ;
			}
		}
		
		for(int i=1;i<10;i++)
		{
			if(A[i]!=0)
			{
				for(int j=0;j<A[i];j++)
				{
					System.out.print(i) ;
				}
			}
		}
		System.out.println() ;
		
	}
}