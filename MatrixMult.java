import java.util.* ;
class MatrixMult
{
 int[][] multiply(int A[][],int B[][],int n)
 {
  int C[][] = new int[3][3] ;
  for(int i=0;i<n;i++)
  {
   for(int j=0;j<n;j++)
   {
    C[i][j] = 0 ;
	for(int k=0;k<n;k++)
    {
     C[i][j] = C[i][j] + A[i][k]*B[k][j] ;
    } 
   }
  }
  return C ;
 }
 public static void main(String args[])
 {
  Scanner in = new Scanner(System.in) ;
  MatrixMult M = new MatrixMult() ;
  System.out.println("Enter the value of n for a n x n matrix.") ;
  int n = in.nextInt() ;
  int A[][] = new int[n][n],B[][] = new int[n][n],C[][] = new int[n][n] ;
  System.out.println("Enter the first Matrix.") ;
  for(int i=0;i<n;i++)
  {
   for(int j=0;j<n;j++)
   {
    System.out.println("Enter the value of the element ("+(i+1)+","+(j+1)+") for the first Matrix.") ;
    A[i][j] = in.nextInt() ;
   }
  }
  for(int i=0;i<n;i++)
  {
   for(int j=0;j<n;j++)
   {
    System.out.println("Enter the value of the element ("+(i+1)+","+(j+1)+") for the second Matrix.") ;
    B[i][j] = in.nextInt() ;
   }
  }
   C = M.multiply(A,B,n) ;
  System.out.println("The Matrix hence formed is:") ;
  for(int i=0;i<n;i++)
  {
   for(int j=0;j<n;j++)
   {
     System.out.print(C[i][j]+" ") ;
   }
   System.out.println() ;
 }
}
} 
  
	 
       