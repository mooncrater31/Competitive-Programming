class pos
{
	int index1 =-1;
	int index2 =-1;
}
class PerfectHash
{
	int hash(int k,int a,int b,int p,int m)
	{
		return ((a*k+b)%p)%m ;
	}
	int[] findIndexArray(int[] arr,int a,int b,int p,int m)
	{
		int[] A = new int[arr.length] ;
		for(int i=0;i<A.length;i++)
		{
			A[i] = hash(arr[i],a,b,p,m) ;
		}
		return A ;
	}
	int[] numberInSlot(int[] A,int m)
	{
		int[] M = new int[m] ;
		for(int i=0;i<A.length;i++)
		{
			M[A[i]]++ ;
		}
		return M ;
	}
	int[][] findAB(int[][] HT)
	{
		for(int i=0;i<HT.length;i++)
		{
			if(HT[i]!=null)
			{
				if(HT[i][0]==1)
				{
					HT[i][1] = 0 ;//A
					HT[i][2] = 0 ;//B
				}
				else
				{
					HT[i][1] = HT[i][0]+5 ;
					HT[i][2] = 5*HT[i][0]+8 ;
				}
				
			}
			
		}
		return HT ;
	}
	void printTable(int[][] HT)
	{
		for(int i=0;i<HT.length;i++)
		{
			if(HT[i]!=null)
			{
				for(int j=0;j<HT[i].length;j++)
				{
					System.out.print(HT[i][j]+" ") ;
				}
			}
			System.out.println() ;
		}
	}
	/* arr is the inserted array, a,b are arbitrary constants, p is a prime number(more than all the keys of the table), m is the size of the hash Table */
	int[][] createHashTable(int[] arr,int a,int b,int p,int m)
	{
		int[][] HT = new int[m][] ;
		int[] A = findIndexArray(arr,a,b,p,m) ;
		int[] M = numberInSlot(A,m) ;
		for(int i=0;i<m;i++)
		{
			if(M[i]!=0)
			{
				HT[i] = new int[3+(int)(Math.pow(M[i],2))] ;
				HT[i][0] = (int)Math.pow(M[i],2) ;
			}
			
			
		}
		HT = findAB(HT) ;
		for(int i=0;i<HT.length;i++)
		{
			System.out.print("#"+i+" ") ;
			if(HT[i]!=null)
			{
				for(int j=0;j<HT[i].length;j++)
					System.out.print(HT[i][j]+" ") ;
				
			}
			System.out.println() ;
		}
		
		 for(int i=0;i<arr.length;i++)
		{
			
			HT[A[i]][(3+hash(arr[i],HT[A[i]][1],HT[A[i]][2],p,HT[A[i]][0]))] = arr[i];
			
		}
		return HT ; 
		
	}
	
	void printArray(int[] A)
	{
		for(int i=0;i<A.length;i++)
		{
			System.out.print(A[i]+ " ") ;
			
		}
		System.out.println() ;
	}
	pos search(int[][] HT,int x,int a,int b,int p)
	{
		pos P = new pos() ;
		int ix = hash(x,a,b,p,HT.length) ;
		if(HT[ix]!=null)
		{
			int tlei = 3+hash(x,HT[ix][1],HT[ix][2],p,HT[ix][0]) ;
			if(HT[ix][tlei]==x)
			{
				P.index1 = ix ;
				P.index2 = tlei ;
			}
		}
		else
		{
			System.out.println("Not Found.") ;
			return null ;
		}
		return P ;
	}
	public static void main(String args[])
	{
		int[] arr = {10,22,37,40,52,60,70,72,75} ;
		PerfectHash P = new PerfectHash() ;
		int a = 3,b=42,p = 101,m = 9 ;//Mark my words--><--
		int[] A = P.findIndexArray(arr,a,b,p,m) ;
		P.printArray(A) ;
		P.printArray(P.numberInSlot(A,m)) ;
		 int[][] H = P.createHashTable(arr,a,b,p,m) ;
		P.printTable(H) ;
		pos alpha = P.search(H,37,a,b,p) ;
		if(alpha!=null)
		{
			System.out.println("Present at "+alpha.index1+" in "+alpha.index2) ;
		}
		
	}
	
}