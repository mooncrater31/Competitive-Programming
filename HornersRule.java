import java.util.Scanner ;

 class HornersRule
 {
  public static void main(String args[])
  {
   Scanner one = new Scanner(System.in) ;
   System.out.println("Enter the value of n.") ;
   int n = one.nextInt() ;
   System.out.println("Enter the value of x") ;
   int x = one.nextInt() ;
   int A[] = new int[n+1] ;
   for(int i=0;i<=n;i++)
   {
    System.out.println("a["+i+"] is :") ;
	A[i] = one.nextInt() ;
   }
   
   int y=0;
   for (int i=n;i>=0;i--)
   {
    y = A[i] + x*y ;
   }
   System.out.println("The Result is "+y) ;
}
}   
	