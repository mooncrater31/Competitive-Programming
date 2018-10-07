import java.util.* ;
public class SpecificArraySorter
{
	static int[][] specificArraySorter(int[][] A,int c)// O(A.length * lg (A.length)) 
	{
		HashMap<Integer,Integer> H = new HashMap<Integer,Integer>() ;
		for(int i=0;i<A.length;i++)
		{
			H.put(A[i][c],i) ;
		}
		int[] temp = new int[A.length] ;
		for(int i=0;i<A.length;i++)
		{
			temp[i] = A[i][c] ;
		}
		Arrays.sort(temp) ;
		int[][] newA = new int[A.length][A[0].length] ;
		for(int i=0;i<A.length;i++)
		{
			newA[i] = A[H.get(temp[i])] ;
		}
		return newA ;
	}
	public static void main(String args[])
	{
		int[][] A = {{1,2},{2,5},{3,6},{4,1},{5,3}} ;
		int[][] B = specificArraySorter(A,1) ;
		for(int i=0;i<B.length;i++)
		{
			System.out.println(B[i][0]+" "+B[i][1]) ;
		}
	}
	
}