import java.util.* ;

public class Gluttony
{
	public static void main(String args[])
	{
		Scanner in = new Scanner(System.in) ;
		HashMap<Integer,Integer> HOrig = new HashMap<Integer,Integer>() ;
		HashMap<Integer,Integer> HSorted = new HashMap<Integer,Integer>() ;
		int N = in.nextInt() ;
		int[] A = new int[N] ;
		for(int i=0;i<N;i++)
		{
			A[i] = in.nextInt() ;
			HOrig.put(A[i],i) ;
		}
		int[] sortedA = new int[N] ;
		System.arraycopy(A,0,sortedA,0,N) ;
		Arrays.sort(sortedA) ;
		for(int i=0;i<N;i++)
		{
			HSorted.put(sortedA[i],i) ;
		}
		for(int i=0;i<N-1;i++)
		{
			A[HOrig.get(sortedA[i])] = sortedA[HSorted.get(sortedA[i])+1] ;
		}
		A[HOrig.get(sortedA[N-1])] = sortedA[0] ;
		for(int i=0;i<N;i++)
		{
			System.out.print(A[i]+" ") ;
		}
		System.out.println() ;
	}
}