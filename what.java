import java.util.* ;
class what
{
static int[] countingSort(int[] A,int max)
    {
        int[] C = new int[max+1] ;
        int[] B = new int[A.length] ;
        for(int i=0;i<A.length;i++)
        {
            C[A[i]]++ ;
        }
        for(int i=1;i<=max;i++)
            C[i] += C[i-1] ;
        System.out.println(Arrays.toString(C)) ;
        for(int i=A.length-1;i>=0;i--)//Makes sure it's inplace
        {
            B[C[A[i]]-1] = A[i] ;
            C[A[i]]-=1 ;
        }
        return B ;
        
    }
    public static void main(String args[]) throws Exception
    {
        int[] A = {6,3,2,5,9,6,10} ;
        System.out.println(Arrays.toString(countingSort(A,11))) ;
    }
}