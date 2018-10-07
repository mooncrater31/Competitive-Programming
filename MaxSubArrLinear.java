import java.util.Scanner ;
class MaxSubArrLinear
{
 public static void main(String args[])
 {
  MaxSubArrLinear MSAL = new MaxSubArrLinear() ;
  Scanner in = new Scanner(System.in) ;
  System.out.println("Enter the number of elements in the array.") ;
  int n = in.nextInt(),A[] = new int[n] ;
  System.out.println("Enter the array.") ;
  for(int i=0;i<n;i++)
  {
   System.out.println("Enter the value of element number "+i) ;
   A[i] = in.nextInt() ;
  }
  int B[] = new int[n-1],C[] = new int[3] ;
  for(int i=0;i<n-1;i++)
  {
   B[i] = A[i+1]-A[i] ;
  } 
   
  C = MSAL.msal(B,0,B.length-1) ;
  System.out.println("The starting point is "+C[0]+" and the end point is "+(C[1]+1)+" with the sum "+C[2]) ;
 }
 int MAX(int a,int b)
 {
  if (a>b)
  return a ;
  else
  return b ;
 }
 int MAX(int a,int b,int c)
 {
  if(a>b && a>c)
  return a ;
  else if(b>a && b>c)
  return b ;
  else
  return c ;
 }
 int[] msal(int[] A,int low,int high)
 {
  MaxSubArrLinear lawl = new MaxSubArrLinear() ;
  int left = 0,right = 0,sum = A[low],temp_sum = 0,temp_left = 0,i=0 ;
  for (i = low;i<= high ;i++)
    {
	 temp_sum = lawl.MAX(A[i], temp_sum + A[i]) ;// to find if the next element is greater than <-. If yes then it's obvious that left element is A[i]
        if (temp_sum > sum) //to find if the temporary sum is greater then the last maximum sum (that's saved in sum)
		   {
		    System.out.println("The value of sum before "+sum) ;
            sum = temp_sum ;
			System.out.println("The value of sum after "+sum) ;
			System.out.println("The value of right before "+right) ;
            right = i ;//the index keeps going ......
			System.out.println("The value of right after "+right) ;
			System.out.println("The value of left before "+left) ;
            left = temp_left ;//this is the same
			System.out.println("The value of left after "+right) ;
		   }	
        if (temp_sum == A[i])
            {
			 temp_left = i ;// if the new element if bigger than the maxsubarray upto now.
			} 
	}	
   int B[] = new int[3] ;	
   B[0] = left ;
   B[1] = right ;
   B[2] = sum ;
   return B ;
   }
}   
  
  
  