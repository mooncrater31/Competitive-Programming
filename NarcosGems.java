import java.util.* ;
import java.io.BufferedReader ;
import java.io.InputStreamReader ;

public class NarcosGems
{
	private static final boolean debug = true ;
	public static void main(String args[]) throws Exception
	{
		BufferedReader bro = new BufferedReader(new InputStreamReader(System.in)) ;
		int T = Integer.parseInt(bro.readLine()) ;
		for(int t=0;t<T;t++)
		{
			String[] S = bro.readLine().split(" ") ;
			int k = Integer.parseInt(S[1]) ;
			// String S2 = "" ;
			// for(int i=0;i<k;i++)
			// {
				// S2+=S[0] ;
			// }
			// if(debug) System.out.println("S2 :"+S2) ;
			System.out.println(solve(zAlgo(S[0].toCharArray()),k)) ;
		}
	}
	static int solve2(int[] Z, int k)
	{
		if(debug) System.out.println(Arrays.toString(Z)) ;
		int m = Z.length ;
		int count = 0 ;
		for(int i=0;i<m;i++)
		{
			count+=Z[i] ;
		}
		return count ;
	}
	static int solve(int[] Z,int k)
	{
		if(debug) System.out.println(Arrays.toString(Z)) ;
		int m = Z.length ;
		int count = k*(k+1)*m/2 ;
		for(int i=1;i<m;i++)
		{
			int temp = Z[i] ;
			if(k!=1 && i+temp==m && Z[temp%m]!=0)
			{
				int length = temp+Z[temp%m] ;
				temp = Z[temp%m] ;
				while(temp+Z[temp%m]==m && length/m<k) 
				{
					temp = Z[temp%m] ;
					length+=temp ;
				}
				count+=length+Z[i] ;
			}
			else if(temp!=-1)
			{
				count+=Z[i]*k ;
			}
			
			if(debug) System.out.println("Count :"+count) ;
		}
		return count ;
	}

	static int[] zAlgo(char[] S)
	{
		int L=0,R = 0 ;
		int m = S.length; 
		int[] Z = new int[m] ;
		Z[0] = m ;
		for(int i=1;i<m;i++)
		{
			if(i>R)
			{
				L = R = i ;
				while(R < m && S[R-L]==S[R])
				{
					R++ ;
				}
				Z[i] = R-L ;
				R-- ;
			}
			else
			{
				int k=i-L ;
				if(Z[k] < R-i+1)
				{
					Z[i] = Z[k] ;
				}
				else
				{
					L = i ;
					while(R<m && S[R-L]==S[R])
					{
						R++ ;
					}
					Z[i] = R-L ;
					R-- ;
				}
			}
		}
		for(int i:Z)
			if(i==0) i=-1 ;
		return Z ;
	}
}