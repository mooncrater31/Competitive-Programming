import java.util.Scanner ;
public class MaxSubarray
{
 public static void main(String args[])
 {
  Scanner input = new Scanner(System.in) ;
  System.out.println("Enter the number of days.") ;
  int days = input.nextInt() ;
  int A[] = new int[days],ans[] = new int[3] ;
  for(int i=0;i<days;i++)
  {
   System.out.println("Enter the price at the day number "+(i)) ;
   A[i] = input.nextInt() ;
  }
  /*int A[] = {100,113,110,85,105,102,86,63,81,101,94,106,101,79,94,90,97} ;
  int days = A.length ;
  int B[] = new int[days-1] ;*/
  MaxSubarray alpha = new MaxSubarray() ;
  ans = alpha.MaxSubarray(A) ;
  System.out.println("The value of Maxsubarray is "+ans[2]+" from "+ans[0]+" to "+(ans[1]+1))  ;
  
 }
int[] MaxSubarray(int[] A)
{ 
  Integer myInf =  Integer.MAX_VALUE ;
  int TSUM=-myInf,from=-1,to=-1,days = A.length ;
  int  B[] = new int[days-1] ;
  for(int i=0;i<days-1;i++)
  {
   B[i] = A[i+1]-A[i] ;
  }
  for(int i =0;i<days-1;i++)
  {
   int sum = 0;
   for(int j=i;j<days-1;j++)
   {
     
    sum = sum+B[j] ;
	if(sum>TSUM)
	{ 
	 
	 TSUM = sum ;
	 from = i;
	 to = j;
	} 
   }
 }
  int ans[] = new int[3] ;
  
  ans[0] = from ;
  ans[1] = to ;
  ans[2] = TSUM ;
  return ans ;
  
 }
} 
  
  
  
  