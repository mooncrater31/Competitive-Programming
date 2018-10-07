import java.util.* ;
//https://www.quora.com/anonymous/158c63bbebe746b389238a78c61c1d4b
class ReverseModifiedMergeSort
{
 static twod RMerge(twod A,int p,int q,int r)
 {
  Integer myinf = Integer.MAX_VALUE ;
  int n1 = q-p+2 ;
  int n2 = r-q+1 ;
  int i,j ;
  twod L = new twod(n1) ;
  twod R = new twod(n2) ;
  for (i=0;i<n1-1;i++)
  {
   L.values[i] = A.values[p+i] ;
   L.number[i] = A.number[p+i] ;
  }
  for(j=0;j<n2-1;j++)
  {
   R.values[j] = A.values[q+1+j] ;
   R.number[i] = A.number[q+1+j] ;
  }
  L.values[i] = myinf ;
  R.values[j] = myinf ;
  int x=p ;
  i=0;
  j=0;
  while(x!=r+1)
  {
   if(L[i]<=R[j])
    {
	 A.values[x] = R.values[i] ;
	 A.number[x] = R.number[i] ;
	 i++ ;
	} 
   else
   {
    A.values[x] = L.values[j] ;
	A.number[x] = L.number[j] ;
	j++ ;
   }
   x++ ;   
  }	
	return A ; 
 }
 twod MSort(twod A, int p, int r)
 {
  if(p==r)
   return A ;
  int q = (p+r)/2 ;
  A = RMSort(A,p,q) ;
  A = RMSort(A,q+1,r) ;
  A = RMerge(A,p,q,r) ;
  return A ;  
 }
}
class twod
{
	int[] values ;
	int[] number ;
	
	twod(int n)
	{
		values = new int[n] ;
		number = new int[n] ;
	}
	
}
class LearningGraph
{
	int[] neighbourCounter(int N,int[][] E)
	{
		int[] neigh = new int[N+1] ;
		for(int i=0;i<E.length;i++)
		{
			for(int j=0;j<2;j++)
			{
				neigh[E[i][j]]++ ;
			}
		}
		return neigh ;
	}
	twod[] modifiedAList(int N,int[] neigh,int[][] E,int[] value)
	{
		twod[] ar  = new 2d[N] ;
		for(int i=0;i<N;i++)
		{
			ar[i] = new twod(neigh[i]) ;
		}
		int[] indices = new int[N] ;
		
		for(int i=0;i<E.length;i++)
		{
			for(int j=0;j<2;j++)
			{
				ar[E[i][j]].values[indices[E[i][j]]] = value[E[i][j]] ;
				ar[E[i][j]].number[indices[E[i][j]]] = E[i][j] ;
				indices[E[i][j]]++ ;
			}
		}
		return ar ;
		
	}
	twod[] ReverseSortedAList(twod[] ar)
	{
		ReverseModifiedMergeSort R = new ReverseModifiedMergeSort() ;
		for(int i=0;i<ar.length;i++)
		{
			R.MSort(ar[i]) ;
		}
		return ar ;
	}
	public static void main(String args[]) 
	{
		Scanner in = new Scanner(System.in) ;
		
	}
}