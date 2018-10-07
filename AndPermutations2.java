import java.util.* ;
import java.io.* ;

public class AndPermutations2 
{
	static int[] andZero(int n)
	{
		if(n%2!=0)
			return null ;
		else
		{
			int[] ans = new int[n+1] ;
			Arrays.fill(ans,-1) ;
			int maxLog = binlog(n) ;
			boolean allDone = false ;
			while(!allDone)
			{
				
				int maxTwo = (1<<maxLog) ;
				while(ans[maxTwo]!=-1)
					maxTwo = maxTwo>>1 ;
				ans[maxTwo] = maxTwo -1 ;
				ans[maxTwo-1] = maxTwo ;
				if(maxTwo==2)
					break ;
				int temp = maxTwo -1 ;
				for(int i=maxTwo+1 ; i<=n;i++)
				{
					
					if(ans[i]!=-1)
						break ;
					ans[i] = --temp ;			
					ans[temp] = i ;
					if(temp==1)
						allDone = true ;	
				}
				maxLog-- ;
			}
			return ans ;
		}
	}
	public static void main(String args[])
	{
		InputReader in = new InputReader(System.in) ;
		int n = in.readInt() ;
		int[] ans1 = andZero(n) ;
		if(ans1==null)
			System.out.println("NO") ;
		else 
		{
			System.out.println("YES") ;
			for(int i=1;i<ans1.length;i++)
			{
				System.out.print(ans1[i]+" ") ;
			}
			System.out.println() ;
		}
		int[] ans2 = nonZeroAnd(n) ;
		if(ans2==null)
			System.out.println("NO") ;
		else 
		{
			System.out.println("YES") ;
			for(int i=1;i<ans2.length;i++)
			{
				System.out.print(ans2[i]+" ") ;
			}
			System.out.println() ;
		}
	}
	public static int binlog( int bits ) // returns 0 for bits=0
	{
		int log = 0;
		if( ( bits & 0xffff0000 ) != 0 ) { bits >>>= 16; log = 16; }
		if( bits >= 256 ) { bits >>>= 8; log += 8; }
		if( bits >= 16  ) { bits >>>= 4; log += 4; }
		if( bits >= 4   ) { bits >>>= 2; log += 2; }
		return log + ( bits >>> 1 );
	}
	static int[] nonZeroAnd(int n)
	{
		int maxLog = binlog(n) ;
		if((1<<maxLog) == n || n<6)
			return null ;
		else
		{
			int[] ans ;
			if(n==6)
			{
				ans = new int[]{-1,3,6,2,5,1,4} ;
			}
			else 
			{
				ans = new int[n+1] ;
				ans[1] = 7 ;
				ans[2] = 3 ;
				ans[3] = 6 ;
				ans[4] = 5 ;
				ans[5] = 1 ;
				ans[6] = 2 ;
				ans[7] = 4 ;
				if(n>7)
				{
					for(int i=3;i<maxLog;i++)
					{
						int initial = (1<<i) ;
						int max = (initial<<1) ;
						for(int j=initial;j<max-1;j++)
						{
							ans[j] = j+1 ;
						}
						ans[max-1] = initial ;
					}
					int maxTwo = (1<<maxLog) ;
					for(int i=maxTwo;i<n;i++)
					{
						ans[i] = i+1 ;
					}
					ans[n] = maxTwo ;
				}
			}
			return ans ;	
		}
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
