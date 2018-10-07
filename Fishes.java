import java.util.* ;
import java.io.* ;
// import java.io.BufferedReader ;
// import java.io.InputStreamReader ;
public class Fishes
{
	public static void main(String args[]) 
	{
		// BufferedReader br = new BufferedReader(new InputStreamReader(System.in)) ;
		// Scanner in = new Scanner(System.in) ;
		// System.out.println(setter(in.nextLong(),in.nextLong(),in.nextLong(),in.nextLong())) ;
		// String str = br.readLine() ;
		// String[] strA = str.split(" ") ;
		// System.out.println(setter(Integer.parseInt(strA[0]),Integer.parseInt(strA[1]),Integer.parseInt(strA[2]),Integer.parseInt(strA[3]))) ;
		InputReader in = new InputReader(System.in) ;
		System.out.println(setter(in.readInt(),in.readInt(),in.readInt(),in.readInt())) ;
	}
	// static void arrayPrinter(int[][] A)
	// {
		// for(int i=0;i<A.length;i++)
		// {
			// for(int j=0;j<A[0].length;j++)
			// {
				// System.out.print(String.format("%4d",A[i][j])) ;
			// }
			// System.out.println() ;
		// }
	// }
	static double setter(long n,long m,long r,long k)
	{
		// int[][] M = new int[n+1][m+1] ;
		// for(int i=0;i<=n;i++)
		// {
			// for(int j=0;j<=m;j++)
			// {
				// if(i==0)
					// M[i][j] = j ;
				// else if(j==0)
					// M[i][j] = i ;
				// else
					// M[i][j] = (Math.min(n+1,i+r)-Math.max(i,r))*(Math.min(m+1,j+r)-Math.max(j,r)) ;
			// }
		// }
		// arrayPrinter(M) ;
		long mid = (m+1)/2 ;
		PriorityQueue<Box> P = new PriorityQueue<Box>() ;
		long sum  = 0;
		for(long i=mid-1;i<=mid;i++)
		{
			for(long j=1;j<=n;j++)
			{
				Box B = new Box(j,i,i==mid-1?false:true) ;
				B.val = -1*(Math.min(n+1,j+r)-Math.max(j,r))*(Math.min(m+1,i+r)-Math.max(i,r)) ;
				// System.out.println("--> M["+j+"]["+i+"] = "+(-1*B.val)+" was added to P.") ;
				P.add(B) ;
			}
		}
		for(long i=0;i<k;i++)
		{
			Box B =P.poll() ;
			// System.out.println("M["+B.x+"]["+B.y+"] = "+(-1*B.val)+" was chosen!") ;
			sum+=-1*B.val ;
			if(B.right)
			{
				if(B.y < m)
				{
					
					Box nB = new Box(B.x,B.y+1,B.right) ;
					nB.val = -1*(Math.min(n+1,B.x+r)-Math.max(B.x,r))*(Math.min(m+1,B.y+1+r)-Math.max(B.y+1,r)) ;
					P.add(nB) ;
					// System.out.println("	and M["+(B.x)+"]["+(B.y+1)+"] = "+(-1*nB.val)+" was added to P.") ;
				}
			}
			else
			{
				if(B.y>1 )
				{
					Box nB = new Box(B.x,B.y-1,B.right) ;
					nB.val = -1*(Math.min(n+1,B.x+r)-Math.max(B.x,r))*(Math.min(m+1,B.y-1+r)-Math.max(B.y-1,r)) ;
					P.add(nB) ;
					// System.out.println("	and M["+(B.x)+"]["+(B.y-1)+"] = "+(-1*nB.val)+" was added to P.") ;
				}
			}
		}
		// System.out.println("Sum = "+sum) ;
		return ((double)sum/((n-r+1)*(m-r+1))) ;
		
	}
}
class Box implements Comparable<Box>
{
	long x,y,val ;
	boolean right ;
	Box(long X,long Y,boolean r)
	{
		this.x = X ;
		this.y = Y ;
		this.right = r ;
	}
	public int compareTo(Box compareBox)
	{
		return (int)(this.val-compareBox.val) ;
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
