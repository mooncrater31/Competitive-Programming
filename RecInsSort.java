class REC
{
 int[] rec(int A[],int i,int j)//Test input {4,6,5,9,3,1,2}
 {
   REC R1 = new REC() ;
   if (i<j)
   {
    A =  R1.rec(A,i,j-1) ;
	 A = R1.add(A,i,j) ;
	}
	return A ;
}	
  int[] add(int A[],int i,int j)// i= initial index and j= final index
 {// what is this shit?
  int key = A[j] ;//value of the final index
  int k=0,l = j-1 ;//k has an initial value of 0, and l has an initial value of final index -1 
  while(A[k++] <key) ;// since the array is sorted, we just have to find the last index that is smaller than key,+1
  int m = k-1 ;
  System.out.println("Here, k is "+k+" j is "+ j +" and l is "+l) ;
  while(l>=m)
  {
   A[l+1] = A[l] ;
   l--;
  }
  A[m] = key ;
  return A ;
 } 
 
  
}
class RecInsSort
{
 public static void main(String args[])
 {
  REC R = new REC() ;
  int count = 0 ;
  for(String x:args)
  count++ ;
  
  int A[] = new int[count] ;
  for(int x=0;x<count;x++)
  {
   A[x] = Integer.valueOf(args[x]) ;
  }
  A = R.rec(A,0,count-1) ;
  for(int x=0;x<count;x++)
  {
   System.out.println(A[x]) ;  
   }
  }
}  