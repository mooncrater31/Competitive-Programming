import java.util.Scanner ;
class FindPoly
{
 public static void main(String args[])
 {
  FindPoly FP = new FindPoly() ;
  Scanner in = new Scanner(System.in) ;
  System.out.println("Enter the value of n.") ;
  int n = in.nextInt() ;
  int A[] = new int[n+1] ;
  for(int i=0;i<=n;i++)
  {
   System.out.println("Enter the value of a["+i+"] :") ;
   A[i] = in.nextInt() ;
  } 
  System.out.println("Enter the value of x.") ;
  int x = in.nextInt() ;
  System.out.println("The answer is "+FP.poly(A,x) );
 }
 int poly(int A[],int x)
 {
  int n=A.length;
  for(int i=0;i<n;i++)
  {
   int j=0,y=1 ;
   while(j<i)
   {
    y = y*x ;
    j++;
	}
  A[i] = A[i]*y ;
 } 
  int sum = 0;
  for(int i=0;i<n;i++)
  {
     sum=sum+A[i] ;
}
return sum ;
}
}	 
	
  	
   
  
  
   	
  