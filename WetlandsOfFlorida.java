import java.util.* ;
import java.io.BufferedReader ;
import java.io.InputStreamReader ;

public class WetlandsOfFlorida
{
	public static void main(String args[]) throws Exception
	{
		BufferedReader bro = new BufferedReader(new InputStreamReader(System.in)) ;
		int T = Integer.parseInt(bro.readLine()) ;
		bro.readLine() ;
		//int cases = 0 ;
		for(int t = 0;t<T;t++)
		{
		    //System.out.println("Case :"+(++cases)) ;
			String[] S = new String[100];
			int row = 0;//number of rows+Number of queries
			int n = 1;//number of rows
			boolean nIsSet = false ;
			
			
			for(String s = bro.readLine();!s.equals("");s = bro.readLine())
			{
				// row++ ;
				
				S[row] = s ;
				//System.out.println(S[row]) ;
				if(!nIsSet && S[row].charAt(0)!='L'&&S[row].charAt(0)!='W')
				{
					n = row ;//n x m matrix
					nIsSet = true ;
				}
				row++ ;
			}
			//System.out.println("S[0] :"+S[0]) ;
			int m = S[0].length() ;
			//System.out.println("n :"+n+" rows :"+row) ;//testing
			
			for(int i=0;i<row-n;i++)
			{//for the queries
				// System.out.println(S[n+i].charAt(0)+" "+S[n+i].charAt(2)) ;
				boolean[][] seen = new boolean[n][m] ;
				String[] s = S[n+i].split(" ") ;
				System.out.println(noOfComponents(Integer.parseInt(s[0])-1,Integer.parseInt(s[1])-1,seen,S)) ;
			}
			System.out.println() ;
		}
		
	
	}
	static int dr[] = {1,1,0,-1,-1,-1, 0, 1}; // trick to explore an implicit 2D grid
	static int dc[] = {0,1,1, 1, 0,-1,-1,-1}; // S,SE,E,NE,N,NW,W,SW neighbors
	static int noOfComponents(int r,int c,boolean[][] seen,String[] S)
	{
		// System.out.println("Checking for : ["+r+"]["+c+"]") ;
		int ans = 0 ;
		// if(r<0)
			// System.out.println("r<0") ;
		// else if(c<0)
			// System.out.println("c<0") ;
		// else if(r>=seen.length)
			// System.out.println("r>=seen.length") ;
		// else if(c>=seen[0].length)
			// System.out.println("c>=seen[0].length") ;
		// else if(seen[r][c])
			// System.out.println("seen[r][c]") ;
		// else if(S[r].charAt(c)!='W')
			// System.out.println("S[r].charAt(c)!='W'") ;
		if(r<0||c<0||r>=seen.length||c>=seen[0].length||seen[r][c]||S[r].charAt(c)!='W')
		{
			return 0;
		}
		seen[r][c] = true ;
		ans = 1 ;
		// char empty = ' ' ;
		// S[r].charAt(c) = empty ;
		for(int i=0;i<8;i++)
		{
			ans+=noOfComponents(r+dr[i],c+dc[i],seen,S) ;
			// System.out.println("----------------------") ;
		}
		// System.out.println("--> :"+ans) ;
		return ans ;
		
	}
}