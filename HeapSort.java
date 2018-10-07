
public class HeapSort
{
	int leftChild(int index)
	{
		return (2*index+1) ;
	}
	int rightChild(int index)
	{
		return (2*index+2) ;
	}
	public int[] minHeapify(int[] A,int index)
	{
		int maximum ;
		int l = leftChild(index) ;
		int r = rightChild(index) ;
		if(l<A.length && A[l]<A[index])// finds if any child of the current element is bigger than it.
		{
			maximum = l ;
		}
		else 
		{
			maximum = index ;
		}
		if(r<A.length && A[r]<A[maximum])
		{
			maximum = r ;
		}
		// maximum has the index of the larger of the three elements. I.E, the given element, it's left child and it's right child.
		if(maximum == index)
		{
			return A ;
		}
		else
		{
			//exchange A[maximum] with A[index] ;
			int c = A[maximum] ;
			A[maximum] = A[index] ;
			A[index] = c ;
			A = minHeapify(A,maximum) ;
		}
		return A ;
		
	}
	public int[] extractMin(int[] A)
	{
		int[] B = new int[A.length-1] ;
		A[0] = A[A.length-1] ;
		for(int i=0;i<B.length;i++)
		{
			B[i] = A[i] ;
		}
		B = minHeapify(B,0) ;
		return B ;
	}
	public int[] heapSort(int[] A)
	{
		int[] B = new int[A.length] ;
		
		int n = A.length/2+1 ;
		for(int i=n;i>=0;i--)
		{
			A = minHeapify(A,i) ;
		}
		return A ;
	}
	public static void main(String args[])
	{
		HeapSort H = new HeapSort() ;
		int[] A = {4,2,1,4,5,7,8} ;
		 A = H.heapSort(A) ;
		for(int i=0;i<A.length;i++)
		{
			System.out.print(A[i]+" ") ;
		
        }  
	}
	
}