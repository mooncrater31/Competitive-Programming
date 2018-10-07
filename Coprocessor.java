import java.util.* ;
import java.io.* ;

public class Coprocessor
{
	static List[] L ; 
	static int[] A ;
	static int[][] dp;
	static boolean[] V ;
	
	static int dfs(int cur,int prev)
	{
		if(dp[cur][prev]!=-1)
			return dp[cur][prev] ;
		V[cur]=true ;
		int count = 0 ;
		for(Object i1:L[cur])
		{
			int i = (int)i1 ;
			count = Math.max(count,dfs(i,A[cur])) ;//this I don't understand
		}
		if(prev!=A[cur] && A[cur]==1)
			count++ ;
		return (dp[cur][prev] = count) ;
	}
	public static void main(String args[])
	{
		// Scanner in = new Scanner(System.in) ;
		InputReader in = new InputReader(System.in);//Filling the global variables

		int n = in.readInt() ;
		int m = in.readInt() ;
		L = new List[n]  ;
		A = new int[n] ;
		dp = new int[n][2] ;
		for(int i=0;i<dp.length;i++)
		{
			for(int j=0;j<2;j++)
				dp[i][j] = -1 ;
		}
		// for(int i=0;i<A.length;i++)
		// {
			// A[i] = in.nextInt() ;
		// }
		in.readInt(A) ;
		for(int i=0;i<n;i++)
		{
			L[i] = new ArrayList<Integer>() ;
		}
		for(int i=0;i<m;i++)
		{
			int a = in.readInt() ;
			int b = in.readInt() ;
			L[a].add(b) ;
		}
		V = new boolean[n] ;
		int count = 0 ;	
		for(int i=0;i<n;i++)
		{
			if(!V[i])
				count = Math.max(count,dfs(i,0)) ;
		}
		System.out.println(count) ;
	}
}
class InputReader {
	private InputStream stream;
	private byte[] buf = new byte[1024];
	private int curChar;
	private int numChars;
	private SpaceCharFilter filter;

	public InputReader(InputStream stream) {
		this.stream = stream;
	}

	public int read() {
		if (numChars == -1)
			throw new InputMismatchException();
		if (curChar >= numChars) {
			curChar = 0;
			try {
				numChars = stream.read(buf);
			} catch (IOException e) {
				throw new InputMismatchException();
			}
			if (numChars <= 0)
				return -1;
		}
		return buf[curChar++];
	}

	public int readInt() {
		int c = read();
		while (isSpaceChar(c))
			c = read();
		int sgn = 1;
		if (c == '-') {
			sgn = -1;
			c = read();
		}
		int res = 0;
		do {
			if (c < '0' || c > '9')
				throw new InputMismatchException();
			res *= 10;
			res += c - '0';
			c = read();
		} while (!isSpaceChar(c));
		return res * sgn;
	}

	public void readInt(int[] A) {
		for (int i = 0; i < A.length; i++)
			A[i] = readInt();
	}

	public long readLong() {
		int c = read();
		while (isSpaceChar(c))
			c = read();
		int sgn = 1;
		if (c == '-') {
			sgn = -1;
			c = read();
		}
		long res = 0;
		do {
			if (c < '0' || c > '9')
				throw new InputMismatchException();
			res *= 10;
			res += c - '0';
			c = read();
		} while (!isSpaceChar(c));
		return res * sgn;
	}

	public void readLong(long[] A) {
		for (int i = 0; i < A.length; i++)
			A[i] = readLong();
	}

	public double readDouble() {
		int c = read();
		while (isSpaceChar(c))
			c = read();
		int sgn = 1;
		if (c == '-') {
			sgn = -1;
			c = read();
		}
		double res = 0;
		while (!isSpaceChar(c) && c != '.') {
			if (c == 'e' || c == 'E')
				return res * Math.pow(10, readInt());
			if (c < '0' || c > '9')
				throw new InputMismatchException();
			res *= 10;
			res += c - '0';
			c = read();
		}
		if (c == '.') {
			c = read();
			double m = 1;
			while (!isSpaceChar(c)) {
				if (c == 'e' || c == 'E')
					return res * Math.pow(10, readInt());
				if (c < '0' || c > '9')
					throw new InputMismatchException();
				m /= 10;
				res += (c - '0') * m;
				c = read();
			}
		}
		return res * sgn;
	}

	public char[] readCharA() {
		return readString().toCharArray();
	}

	public String readString() {
		int c = read();
		while (isSpaceChar(c))
			c = read();
		StringBuilder res = new StringBuilder();
		do {
			res.appendCodePoint(c);
			c = read();
		} while (!isSpaceChar(c));
		return res.toString();
	}

	public boolean isSpaceChar(int c) {
		if (filter != null)
			return filter.isSpaceChar(c);
		return c == ' ' || c == '\n' || c == '\r' || c == '\t' || c == -1;
	}

	public String next() {
		return readString();
	}

	public interface SpaceCharFilter {
		public boolean isSpaceChar(int ch);
	}

}

