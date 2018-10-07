import java.util.* ;
import java.io.* ;

public class LocalExtrema
{
	int extremaCounter(int[] A)
	{
		int C = 0 ;
		for(int i=1;i<A.length-1;i++)
		{
			if((A[i]>A[i-1] && A[i]>A[i+1])||(A[i]<A[i-1] && A[i]<A[i+1]))
				C++ ;
		}
		return C ;
	}
	public static void main(String args[]) throws Exception
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)) ;
		String line = br.readLine() ;
		int N = Integer.parseInt(line) ;
		line = br.readLine() ;
		String[] s = line.split(" ") ;
		int[] A = new int[N] ;
		for(int i=0;i<N;i++)
		{
			A[i] = Integer.parseInt(s[i]) ;
		}
		LocalExtrema L = new LocalExtrema() ;
		System.out.println(L.extremaCounter(A)) ;
	}
}