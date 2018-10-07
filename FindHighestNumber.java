import java.util.* ;
import java.io.BufferedReader ;
import java.io.InputStreamReader ;

public class FindHighestNumber
{
	public static void main(String args[]) throws Exception
	{
		BufferedReader bro = new BufferedReader(new InputStreamReader(System.in)) ;
		int T = Integer.parseInt(bro.readLine()) ;
		for(int t=0;t<T;t++)
		{
			
		}
	}
	static int modSearch(int[] A)
	{
		int start = 0 ,end = A.length-1 ;
		while(start<end)
		{
			int mid = (start+end)/2 ;
			if(A[mid]<A[mid+1]) 
			{
				start = mid+1 ;
			}
			else if(A[mid-1]>A[mid])
			{
				end = mid-1 ;
			}
			else
				return mid ;
		}
	}
}