import java.util.* ;
import java.io.BufferedReader ;
import java.io.InputStreamReader ;

public class OmarAndStrings
{
	private static final boolean debug = false ;
	public static void main(String args[]) throws Exception
	{
		BufferedReader bro = new BufferedReader(new InputStreamReader(System.in)) ;
		String S = bro.readLine() ;
		String ans = solve(S) ;
		int[] temp = zAlgo(ans.toCharArray()) ;
		temp[0] = temp.length ;
		if(debug) 
		{
			System.out.println(Arrays.toString(temp)) ;
		}
		int[] prfx = prefix(temp) ;
		System.out.println(ans) ;
		for(int i=1;i<prfx.length;i++)
			System.out.print(prfx[i]+" ") ;
		System.out.println() ;
		
	}
	static String solve(String S)
	{
		// char[] charS = S.toCharArray() ;
		int n = S.length() ;
		String rev = reverse(S.toCharArray()) ;
		
		int[] bPrefix = kmpPreprocess((S+rev).toCharArray()) ;
		int[] bSuffix = kmpPreprocess((rev+S).toCharArray()) ;
		if(debug) 
		{
			System.out.println("rev :"+rev) ;
			System.out.println(Arrays.toString(bPrefix)) ;
			System.out.println(Arrays.toString(bSuffix)) ;
		}
		return rev.substring(0,bPrefix[bPrefix.length-1]>n?n:bPrefix[bPrefix.length-1])+S.substring(0,bSuffix[bSuffix.length-1]>n?n:bSuffix[bSuffix.length-1]) ;
		
		
		
		
	}
	static int[] prefix(int[] z)
	{
		int n = z.length ;
		int[] occurrences = new int[n+1] ;
		for(int i=0;i<n;i++)
			occurrences[z[i]]++ ;
		int[] prfx = new int[n+1] ;
		prfx[0] = n ;
		for(int i=1;i<n+1;i++)
		{
			prfx[i] = prfx[i-1]-occurrences[i-1] ;
		}
		return prfx ;
	}
	static int[] zAlgo(char[] S)
	{
		int L = 0, R = 0 ;
		int n = S.length ;
		int[] Z = new int[n] ;
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
	static String reverse(char[] S)
	{
		char[] rev = new char[S.length] ;
		for(int i=0;i<S.length;i++)
		{
			rev[i] = S[S.length-i-1] ;
		}
		return new String(rev) ;
	}
	static int[] kmpPreprocess(char[] P)
	{
		int i=0,j=-1 ;
		int n = P.length ;
		int[] b = new int[n+1] ;
		b[0] = -1 ;
		while(i<n)
		{
			while(j>=0 && P[i]!=P[j]) j = b[j] ;
			i++;j++;
			b[i] = j ;
		}
		return b ;
	}
	
}