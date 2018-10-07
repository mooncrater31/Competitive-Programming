class Bubble
{
 int[] BubbleSort(int A[])
 {
  int n = A.length, aloha = 0 ;
  boolean swap = true ;
  while(swap)
  { 
   int count =0 ;
   for(int i=0;i<n-1;i++)
   {
    if(A[i]>A[i+1])
	{
	 int c = A[i] ;
	 A[i] = A[i+1] ;
	 A[i+1] = c ;
	 count++ ;
	}
   }	
	if(count>0)
	swap = true ;
	else
	swap = false;
	aloha++ ;
  }
  System.out.println("Value of aloha is "+aloha) ;
 return A ;
}
}
class BubbleSort
{
 public static void main(String args[])
 {
  int count = 0 ;
  Bubble Bub = new Bubble() ;
  for(String x:args)
  count++ ;
  System.out.println("Value of count is "+count) ;
  int A[] = new int[count] ;
  for(int x=0;x<count;x++)
  A[x] = Integer.valueOf(args[x]) ;
  A = Bub.BubbleSort(A) ;
  for(int x=0;x<count;x++)
  System.out.println(A[x]) ;
  }
  }
    