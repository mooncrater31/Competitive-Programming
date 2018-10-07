import java.util.* ;
import java.io.BufferedReader ;
import java.io.InputStreamReader ;

public class FlawedAPI
{
	static HashSet<Integer> indices = new HashSet<Integer>() ;
	private static final boolean debug = false ;
	public static void main(String args[]) throws Exception
	{
		BufferedReader bro = new BufferedReader(new InputStreamReader(System.in)) ;
		int T = Integer.parseInt(bro.readLine()) ;
		String fibo = fibonacciBuildup() ;
		if(debug) System.out.println(fibo) ;
		for(int t=0;t<T;t++)
		{
			System.out.println(solve(bro.readLine(),fibo)?"Valid":"Invalid") ;
		}
	}
	static boolean solve(String S,String fibo)
	{
		return indices.contains(kmpSearch(S.toCharArray(),fibo.toCharArray(),kmpPreprocess(S.toCharArray()))) ;
	}
	static String fibonacciBuildup()
	{
		long i=0,j=1 ;
		String S = "01" ;
		indices.add(0);
		indices.add(1);
		while(S.length()<1000)
		{
			long temp = i ;
			i = j ;
			j = temp+j ;
			S+=String.valueOf(j) ;
			indices.add(S.length()-1) ;
			if(debug) System.out.println(S) ;
		}
		return S ;
	}
	static int[] kmpPreprocess(char[] P)
	{
		int i=0,j=-1 ;
		int[] b = new int[P.length+1] ;
		b[0] = -1 ;
		int m = P.length ;
		while(i<m)
		{
			while(j>=0 && P[i]!=P[j]) j = b[j] ;
			i++;j++;
			b[i] = j ;
				
		}
		return b ;
	}
	static int kmpSearch(char[] P,char[] T,int[] b)
	{
		int i=0,j=0;
		int m = P.length ;
		int n = T.length ;
		while(i<n)
		{
			while(j>=0 && P[j]!=T[i])j = b[j] ;
			i++;j++;
			if(j==m)
				return i-1 ;
		}
		return -1 ;
	}
}