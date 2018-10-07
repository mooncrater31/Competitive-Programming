import java.util.* ;
import java.io.BufferedReader ;
import java.io.InputStreamReader ;
/*
Bugs:
#1 : The conversion from hexa Matrix to binary Matrix doesn't work properly
->Solved: S[i][j] maps to M[i][4*j] not M[i][j].
#2 : nh = 16 for the input 
4 4
f0f0
f0f0
f0f0 
f0f0
0 0
Should be only 2.
-> r to r_new and c to c_new 
-> the cell checked is assigned nh.
-> The bfs isn't working properly.
	-> The condition was checking M[r][c] instead of M[r_new][c_new]
Solved.
#3 : IndexOutOfBoundsException: At the instantiation of the HashSets of heiroglyphs ArrayList
Solved: instead of heiroglyphs.set(i,new HashSet<Integer>()), did heiroglyphs.add(new HashSet<Integer>()) 
#4 : IndexOutOfBoundsException: Instead of "nh-2" did "nh"
Solved.
#5 : Wrong answer for the input :
4 4
0f0f
ff00
0000
f00f
0 0
-> A layer of padding for the input is needed. So that no heiroglyph closes a white area unintentionally.
-> i was from 0 to n-1, now 1 to n. j not changed. Just from M[i][4*j+k] to M[i][4*j+k+1], to shift all columns to right
# 6 : Recognizing only 7 inputs out of 10 inputs for uDebug test case 2.
-> The condition s.charAt(0) !=0 && s.charAt(2)!=0, would be very wrong. Changed it. So it recognized 200 50 as EOF.
*/
class pair
{
	int first,second ;
	pair(int f,int s)
	{
		first = f ;
		second = s ;
	}
}
public class AncientMessages
{
	private static final boolean debug = false ;
	private static final boolean debug2 = false ;
	static char[] symbols = {'0','W','A','K','J','S','D'} ;//In accordance to the number of corresponding white spaces
	static int dr[] = {-1,0,1,0} ;
	static int dc[] = {0,1,0,-1} ;
	static void bfs_floodfill_black(int r,int c,int[][] matrix,int nh)//nh = number of heiroglyphs, starts from 2
	{//for the heiroglyphs 
		if(debug)
			System.out.println("Into ["+r+"]["+c+"]") ;
		if(matrix[r][c]!=1)
			return ;
		matrix[r][c] = nh ;
		int rows = matrix.length, cols = matrix[0].length ;
		Queue<pair> Q = new ArrayDeque<pair>() ;
		Q.add(new pair(r,c)) ;
		while(!Q.isEmpty())
		{
			if(debug)
				System.out.println("Inside the while loop.") ;	 
			pair p = Q.poll() ;
			r = p.first ;
			c = p.second ;
			for(int i=0;i<4;i++)
			{//finding the nearby cells
				int r_new = r+dr[i] ;
				int c_new = c+dc[i] ;
				if(r_new>=0 && r_new <rows && c_new>=0 && c_new <cols && matrix[r_new][c_new]==1)
				{//if not out of the input
					matrix[r_new][c_new]=nh ;
					if(debug)
					{
						System.out.println("	Checking : ["+r_new+"]["+c_new+"]") ;
					}
					Q.add(new pair(r_new,c_new)) ;
				}
			}
		}
	}
	static void bfs_floodfill_white(int r,int c,int[][] matrix, int nw,List<HashSet<Integer>> heiroglyphs)//nw = number of whitespaces(-ve)
	{//for the white spaces
		if(matrix[r][c]!=0)
			return ;
		int rows = matrix.length, cols = matrix[0].length ;
		Queue<pair> Q = new ArrayDeque<pair>() ;
		Q.add(new pair(r,c)) ;
		while(!Q.isEmpty())
		{
			pair p = Q.poll() ;
			r = p.first ;
			c = p.second ;
			for(int i=0;i<4;i++)
			{
				int r_new = r+dr[i] ;
				int c_new = c+dc[i] ;
				if(r_new>=0 && r_new <rows && c_new>=0 && c_new <cols)
				{
					if(matrix[r_new][c_new] == 0)
					{
						matrix[r_new][c_new] = nw ;
						Q.add(new pair(r_new,c_new)) ;
					}
					else if(matrix[r_new][c_new]>0)
					{//a heiroglyph is found
						heiroglyphs.get(matrix[r_new][c_new]-2).add(nw) ;
					}
				}

			}
		}
		
	}
	static void ArrayPrinter(int[][] M)
	{
		for(int i=0;i<M.length;i++)
		{
			for(int j=0;j<M[0].length;j++)
			{
				System.out.print(M[i][j]) ;
			}
			System.out.println() ;
		}
	}
	public static void main(String args[]) throws Exception
	{
		BufferedReader bro = new BufferedReader(new InputStreamReader(System.in)) ;
		int cases = 0;
		for(String s = bro.readLine();;s = bro.readLine())
		{//Input reading
			cases++ ;
			String[] spl = s.split(" ") ;
			if(Integer.parseInt(spl[0])==0 && Integer.parseInt(spl[1])==0)
				break ;
			int n = Integer.parseInt(spl[0]), m = Integer.parseInt(spl[1]) ;
			if(debug2) System.out.println("n :"+n+" m :"+m) ;
			int[][] M = new int[n+2][4*m+2] ;//+2 for the padding
			// String S = bro.readLine() ;
			for(int i=1;i<=n;i++)
			{
				String S = bro.readLine() ;
				for(int j = 0;j<m;j++)
				{
					char ch = S.charAt(j) ;
					int val = Character.isLetter(ch)? ch-'a'+10:ch-'0' ;
					if(debug) System.out.println(ch+" :: "+val) ;
					for(int k=0,b=8;k<4;k++,b>>=1)
					{
						M[i][4*j+k+1] = (b & val)>0?1:0 ; 
					}
				}
			}
			if(debug)
			{
				System.out.println("Original :") ;
				ArrayPrinter(M) ;
			}
			int nh = 2 ;
			for(int i=0;i<M.length;i++)
			{
				for(int j=0;j<M[i].length;j++)
				{
					if(M[i][j]==1)
					{
						bfs_floodfill_black(i,j,M,nh) ;
						nh++ ;
					}
				}
			}
			if(debug)
			{
				System.out.println("No. of heiroglyphs :"+(nh-2)) ;
				ArrayPrinter(M) ;
			}
			List<HashSet<Integer>> heiroglyphs = new ArrayList<HashSet<Integer>>() ;
			for(int i=0;i<nh-2;i++)
			{//Instantiation
				// heiroglyphs.set(i,new HashSet<Integer>()) ;
				heiroglyphs.add(new HashSet<Integer>()) ;
			}
			int nw = 0 ;
			for(int i=0;i<M.length;i++)
			{
				for(int j=0;j<M[i].length;j++)
				{
					if(M[i][j]==0)
					{
						bfs_floodfill_white(i,j,M,-1*(nw+1),heiroglyphs) ;
						nw++ ;
					}						
				}
			}
			if(debug)
			{
				System.out.println("nw :"+(nw)) ;
				ArrayPrinter(M) ;
			}
			
			int[] size = new int[nh-2] ;
			for(int i=0;i<nh-2;i++)
			{
				size[i] = heiroglyphs.get(i).size() ;
			}
			// Arrays.sort(size) ;
			char[] Heiro = new char[nh-2] ;
			System.out.print("Case "+cases+": ") ;
			for(int i=0;i<nh-2;i++)
			{
				// System.out.print(symbols[size[i]]) ;
				Heiro[i] = symbols[size[i]] ;
			}
			Arrays.sort(Heiro) ;
			for(char i : Heiro)
				System.out.print(i) ;
			System.out.println() ;
		}
		
		
	}
	
	
}