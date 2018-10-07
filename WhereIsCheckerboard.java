import java.util.* ;
import java.io.BufferedReader ;
import java.io.InputStreamReader ;

class WhereIsCheckerboard
{
	public static void main(String args[]) throws Exception
	{
		
	}
	static int solve(String[] S)
	{
		int n = S[0].length() ;
		int m = S.length ;
		List<List<Integer>> L = new List<ArrayList<Integer>>() ;
		
		
	}
	static int[] kmpPreprocess(char[] P)
	{
		int[] b = new int[P.length+1] ;
		int i=0,j=-1 ;
		while(j<n)
		{
			while(j>=0 && P[i]!=P[j]) j = b[j] ;
			i++;j++;
			b[i] = j ;
		}
		return b ;
	}
	static List<Integer> kmpSearch(char[] P,char[] T,int[] b)
	{
		List<Integer> L = new ArrayList<Integer>() ;
		int i=0,j=0;
		while(i<n)
		{
			while(j>=0 && T[i]!=P[j]) j = b[j] ;
			i++;j++;
			if(j==m)
			{
				L.add(i) ;
				j = b[j] ;
			}
		}
		return L ;
	}
}