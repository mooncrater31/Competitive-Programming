import java.util.* ;
import java.io.BufferedReader ;
import java.io.InputStreamReader ;

public class MaxSum
{
	public static void main(String args[]) throws Exception
	{
		BufferedReader bro = new BufferedReader(new InputStreamReader(System.in)) ;
		int T = Integer.parseInt(bro.readLine()) ;
		for(int t=0;t<T;t++)
		{
			boolean noNegs=true,evenNegs=false,zero = false  ;
			int negCounter = 0 ;
			int N = Integer.parseInt(bro.readLine()) ;
			String[] S = bro.readLine().split(" ") ;
			int[] A = new int[N] ;
			for(int i=0;i<N;i++)
			{
				A[i] = Integer.parseInt(S[i]) ;
				if(!zero && A[i]==0)
					zero = true ;
				else if(noNegs && A[i]<0)
					noNegs = false ;
				
				if(A[i]<0)
					negCounter++ ;
			}
			if(negCounter%2==0)
				evenNegs = true ;
			System.out.println(solve(A,zero,evenNegs,noNegs)) ;
		}
	}
	static long solve(int[] A,boolean zero,boolean evenNegs,boolean noNegs)
	{
		Arrays.sort(A) ;
		int sum = 0 ;
		if(A.length%2==0)
		{//Even number of elements
			return (evenSumFinder(A)%((int)1e9+7)) ;
		}
		else
		{//Odd number of elements
			int[] temp = new int[A.length-1] ;
			int var = 0 ;
			boolean firstPos = false ;
			if(noNegs)
			{
				var = A[0] ;
				for(int i=1,j=0;i<A.length;i++)
				{
					temp[j++] = A[i] ;
				}
				
			}
			else
			{
				if(evenNegs)
				{
					int j=0 ;
					
					for(int i=0;i<A.length;i++)
					{
						if(!firstPos && (A[i]>=0||i==A.length-1))
						{
							firstPos = true ;
							var = A[i] ; 
						}
						else temp[j++] = A[i] ;
					}
				}
				else
				{
					if(zero)
					{
						boolean zeroSeen = false,nextToZero = false ;
						for(int i=0,j=0;i<A.length;i++)
						{
							if(!zeroSeen && A[i]==0)
							{
								// if(i==A.length-1)
									// var = A[i]
								
								
								zeroSeen = true ;
								temp[j++] = A[i] ;
								
							}
							else if(zeroSeen && !nextToZero)
							{
								nextToZero = true ;
								var = A[i] ;
							}
							else temp[j++] = A[i] ;
						}

					}
					else
					{
						for(int i=0,j=0;i<A.length;i++)
						{
							if(!firstPos && i==A.length-1)
							{
								firstPos = true ;
								var = A[i] ;
							}
							else if(!firstPos && (A[i]>=0))
							{
								firstPos = true ;
								var = temp[j-1] ;
								temp[j-1] = A[i] ;
							}
							else temp[j++] = A[i] ;
						}
					}
				}
			}
			return (evenSumFinder(temp)+var)%((int)1e9+7) ;	
		}
	}
	static long evenSumFinder(int[] A)
	{
		long sum = 0 ;
		for(int i=0;i<A.length/2;i++)
			sum += A[2*i]*A[2*i+1] ;
		return sum ;
	}
	
	
}