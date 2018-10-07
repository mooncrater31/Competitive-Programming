import java.util.* ;
import java.io.BufferedReader ;
import java.io.InputStreamReader ;

public class RhezoAndDivision
{
	long[][][][][] dp ;
	private static final boolean debug = true ;
	RhezoAndDivision(int n)
	{
		dp = new long[n][4][3][2][2] ;
		fillMinusOnes(dp) ;
	}
	static void fillMinusOnes(long[][][][][] dp)
	{
		for(int i=0;i<dp.length;i++)
		{
			for(int j=0;j<dp[i].length;j++)
			{
				for(int k = 0;k<dp[i][j].length;k++)
				{
					for(int l=0;l<dp[i][j][k].length;l++)
					{
						for(int m=0;m<dp[i][j][k][l].length;m++)
						{
							dp[i][j][k][l][m] = -1 ;
						}
					}
				}
			}
		}
	}
	public static void main(String args[]) throws Exception
	{
		BufferedReader bro = new BufferedReader(new InputStreamReader(System.in)) ;
		String P = bro.readLine() ;
		String T = bro.readLine() ;
		int[] Z = zAlgo((P+"$"+T).toCharArray()) ;
		int m = P.length() ;
		List<Integer> L = new ArrayList<Integer>() ;
		for(int i=0;i<Z.length;i++)
		{
			if(Z[i]==m)
			{
				L.add(i) ;
			}
		}
		int[] ar = new int[L.size()] ;
		RhezoAndDivision R = new RhezoAndDivision(ar.length) ;
		for(int i=0;i<L.size();i++)
		{
			ar[i] = L.get(i) ;
		}
		System.out.println(R.go(ar,0,0,0,0,0)) ;
	}
	long go(int[] ar,int idx,int two,int three,int five,int seven)
	{
		if(debug) System.out.println("("+idx+","+two+","+three+","+five+","+seven+")") ;
		if(idx==dp.length  && two>=3 && three>=2 && five>=1 && seven>=1)
			return 1 ;
		else if(idx==dp.length) return 0 ;
		if(dp[idx][two][three][five][seven]!=-1) return dp[idx][two][three][five][seven] ;
		long ret = 0 ;
		int cc = ar[idx] ;
		int tw = 0 ;
		while(cc%2==0 && tw<=3)
		{
			tw++;
			cc/=2 ;
		}
		cc = ar[idx] ;
		int thr = 0 ;
		while(cc%3==0 && thr<=2)
		{
			thr++ ;
			cc/=3 ;
		}
		cc = ar[idx] ;
		int fiv = 0 ;
		while(cc%5==0 && fiv<=1)
		{
			fiv++ ;
			cc/=5 ;
		}
		cc = ar[idx] ;
		int sev = 0 ;
		while(cc%7==0 && sev<=1)
		{
			sev++ ;
			cc/=7 ;
		}
		ret = go(ar,idx+1,two,three,five,seven) ;
		ret+=go(ar,idx+1,Math.min(3,two+tw),Math.min(2,three+thr),Math.min(1,five+fiv),Math.min(1,seven+sev)) ;
		if(ret>=(int)(1e9+7)) ret-=(int)(1e9+7) ;
		return dp[idx][two][three][five][seven] = ret ; 
	}
	static int[] zAlgo(char[] S)
	{
		int n = S.length ;
		int[] Z = new int[n] ;
		int L=0,R = 0 ;
		Z[0] = n ;
		for(int i=1;i<n;i++)
		{
			if(i>R)
			{
				L = R = i ;
				while(R<n && S[R-L]==S[R])
					R++ ;
				Z[i] = R-L ;
				R-- ;
			}
			else 
			{
				int k = i-L ;
				if(Z[k]<R-i+1)
				{
					Z[i] = Z[k] ;
				}
				else
				{
					L = i ;
					while(R<n && S[R-L]==S[R])
						R++ ;
					Z[i] = R-L ;
					R-- ;
				}
			}
		}
		return Z ;
	}
}