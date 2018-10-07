public class BinarySearch
{
	public int BSearch(int[] A,int el)
	{
		int s = 0 ,end = A.length-1 ;
		
		while(s<=end)
		{
			int mid = (s+end)/2 ;
			if(A[mid] == el)
				return mid ;
			else
			{
				if(A[mid]>el)
				{
			       end = mid-1 ;
				}
				else
				{
					s = mid+1 ;
				}
			}
		}
		return -1 ;
	}
	public static void main(String args[])
	{
		int A[] = {1,2,5,5,8,10} ;
		BinarySearch B = new BinarySearch() ;
		System.out.println(B.BSearch(A,5)) ;
	}

}