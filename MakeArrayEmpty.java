import java.util.* ;
import java.io.BufferedReader ;
import java.io.InputStreamReader ;
/*
BUGS:
#1: Wrong output for the input:
1
20
9 15 9 3 8 4 6 17 7 11 17 7 3 18 13 9 7 12 2 8
My answer: 127
Correct answer: 129
*/
public class MakeArrayEmpty
{
	private static final boolean debug = true ;
	public static void main(String args[]) throws Exception
	{
		BufferedReader bro = new BufferedReader(new InputStreamReader(System.in)) ;
		int T = Integer.parseInt(bro.readLine()) ;
		for(int t=0;t<T;t++)
		{
			int N = Integer.parseInt(bro.readLine()) ;
			String[] S = bro.readLine().split(" ") ;
			int[] Arr = new int[N] ;
			for(int i=0;i<N;i++)
			{
				Arr[i] = Integer.parseInt(S[i]) ;
			}
			int[] A = new int[21] ;
			for(int i=0;i<Arr.length;i++)
			{
				A[Arr[i]]+=Arr[i] ;
			}
			System.out.println(solve(A)) ;
		}
	}
	static int solve(int[] A)
	{
		int sum = 0 ;
		int delete = 0 ;
		for(int i=A.length-1;i>0;i--)
		{
			if(A[i]/i-delete>0)
			{
				sum+=A[i]-delete*i ;
				delete = A[i]/i-delete ;
			}
			else delete = 0 ;
		}
		return sum ;
	}
}