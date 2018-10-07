import java.util.* ;
class MirrorTrees
{
	public static void main(String args[])
	{
		Scanner in = new Scanner(System.in) ;
		int T = in.nextInt() ;
		int[][] results = new int[T][] ;
		for(int k=0;k<T;k++)
		{
			int N = in.nextInt() ;
			int[] R = new int[binlog(N)+1] ;
			int[] L = new int[binlog(N)+1] ;
			int r=0,l=0,j=0;
			R[r++] = in.nextInt() ;//root node
			for(int i=1;i<binlog(N+1);i++)
			{
				int[] temp = new int[(int)Math.pow(2,i)] ;
				for(int m=0;m<temp.length;m++)
				{
					temp[m] = in.nextInt() ;
				}
				
				int fromRight = temp.length -1 ;
				//System.out.println("fromRight :"+fromRight) ;
				while(fromRight!=-1 && temp[fromRight]==0)
				{
					fromRight-- ;
				}
				if(fromRight!=-1)
					R[r++] = temp[fromRight] ;
				int fromLeft = 0 ;
				while(fromLeft!=temp.length && temp[fromLeft]==0 )
				{
					fromLeft++ ;
				}
				if(fromLeft!=fromRight && fromLeft!=temp.length)
					L[l++] = temp[fromLeft] ;
				
				
				
			}
			//System.out.println("l is :"+l+" r is :"+r) ;
			results[k] = new int[l+r] ;
			for(int i=0;i<r;i++)
			{
				results[k][i] = R[i] ;
			}
			for(int i=r;i<r+l;i++)
			{
				results[k][i] =L[i-r] ;
			}
				
		}
		for(int i=0;i<results.length;i++)
		{
			for(int j=0;j<results[i].length;j++)
			{
				System.out.println(results[i][j]) ;
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
}