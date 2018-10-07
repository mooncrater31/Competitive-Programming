class container
{
	int[] m,s ;
	container(int l)
	{
		m = new int[l] ;
		s = new int[l] ;
	}
	
}
class LMIS
{
	container lmis(int[]  A)
	{
		container c = new container(A.length) ;
		int n = A.length -1 ;
		int[] m = new int[n+1] ;
		int[] s = new int[n+1] ;
		for(int i=0;i<s.length-1;i++)
			s[i] = i ;
		m[n] = 1 ;
		s[n] = n ;
		for (int i=n-1;i>=0;i--)
		{
			for(int j=i+1;j<=n;j++)
			{
				if(A[j] > A[i])
				{
				if(m[j]+1>m[i])
				{
					m[i] = m[j]+1 ;
					s[i] = j ;
				}
				}
			}
		}
	//System.arraycopy(m,0,c.m,0,m.length) ;
	for(int i=0;i<m.length;i++)
	{
		c.m[i] = m[i] ;
		c.s[i] = s[i] ;
	}
	//System.arraycopy(s,0,c.s,0,s.length) ;
	return c ;
	}
	int max(int[] A)
	{
		int mx=0 ;
		for(int i=0;i<A.length-1;i++)
		{
			if(A[i]>A[mx])
				mx = i ;
		}
		return mx ;
	}
	void lmis_main(int[] A)
	{
		container c = lmis(A) ;
		int M = max(c.m) ;
		int i = M,j = M  ;
		System.out.println("m:") ;
		for(int k=0;k<A.length;k++)
			System.out.print(c.m[k]+" ") ;
		System.out.println("\ns:") ;
		for(int k=0;k<A.length;k++)
			System.out.print(c.s[k]+" ") ;
		System.out.println() ;
		while(j!=c.s[i]) 
		{
			j = i ;
			System.out.println(A[i]) ;
			i = c.s[i] ;
			
		}
	}
	public static void main(String args[])
	{
		final long startTime = System.nanoTime() ;
		int[] A = {5,7,3,1,2,8,33,10} ;
		LMIS L = new LMIS() ;
		L.lmis_main(A) ;
		final long endTime = System.nanoTime() ;
		System.out.println("Duration"+(double)(endTime-startTime)/1000000000) ;
		
	}
}