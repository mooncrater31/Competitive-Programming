import java.util.* ;
import java.io.BufferedReader ;
import java.io.InputStreamReader ;

public class PeriodicStrings
{
	char[] T,P ;
	int b[],n,m ;
	int matches = 0 ;
	PeriodicStrings(int n,int m)
	{
		// this.T = new char[n] ;
		// this.P = new char[m] ;
		this.b = new int[m+1] ;
		this.n = n ;
		this.m = m;
	}
	public static void main(String args[]) throws Exception
	{
		BufferedReader bro = new BufferedReader(new InputStreamReader(System.in)) ;
		int T = Integer.parseInt(bro.readLine()) ;
		for(int t=0;t<T;t++)
		{
			bro.readLine() ;
			char[] C = bro.readLine().toCharArray() ;
			for(int i=1;i<=C.length;i++)
			{
				PeriodicStrings P = new PeriodicStrings(C.length,i) ;
				P.T = C ;
				P.P = Arrays.copyOfRange(C,0,i) ;
				P.kmpPreprocess() ;
				P.kmpSearch() ;
				if(P.matches*i==C.length)
				{
					System.out.println(i);
					break ;
				}
			}
		}
	}
	void kmpPreprocess()	
	{
		int i=0,j=-1;
		b[0] = -1 ;
		while(i<m)
		{
			while(j>=0 && P[i]!=P[j]) j = b[j] ;
			i++;j++;
			b[i] = j ;
		}
	}
	void kmpSearch()
	{
		int i=0,j=0;
		while(i<n)
		{
			while(j>=0 && T[i]!=P[j]) j = b[j] ;
			i++;j++;
			if(j==m)
			{
				matches++ ;
				// j = b[j] ;
				j=0 ;
			}
		}
	}
	
}