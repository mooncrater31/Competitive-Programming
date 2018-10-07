import java.util.* ;
class StrassenM
{
 int[][] sub(int A[][],int B[][])
 {
  int l = A[0].length ;
  int C[][] = new int[l][l] ;
  for(int i=0;i<l;i++)
  {
   for(int j=0;j<l;j++)
   {
    C[i][j] = A[i][j]- B[i][j] ;
   }
  }
  return C ;
 }  
  
 int[][] add(int A[][], int B[][])
 {
  int l = A[0].length ;
  int C[][] = new int[l][l] ;
  for(int i=0;i<l ;i++)
  {
   for(int j=0;j<l;j++)
   {
    C[i][j] = A[i][j] + B[i][j] ;
   }
  }   
    
  return C ;
 }
 int[][] multiply(int A[][] , int B[][])
 {//only the indexes change, not the matrices A and B
  StrassenM M = new StrassenM() ;
  int l = A[0].length ;// or l = j2-j1+1
  if(l==1)
  {
   int C[][] = new int[1][1] ;
    C[0][0] = A[0][0]*B[0][0] ;
   return C ;
  }
  else
  {
    int A00[][]  = new int[l/2][l/2], A01[][] = new int[l/2][l/2], A10[][] = new int[l/2][l/2], A11[][] = new int[l/2][l/2],a=0,b=0 ;
	int B00[][] = new int[l/2][l/2], B01[][] = new int[l/2][l/2], B10[][] = new int[l/2][l/2] , B11[][] =new int[l/2][l/2] ;
	int C00[][] = new int[l/2][l/2], C01[][] = new int[l/2][l/2], C10[][] = new int[l/2][l/2], C11[][] = new int[l/2][l/2] ;
	// for the partition of A
	{
	for(int i = 0;i<l/2;i++)
	{// in A00
	 for(int j=0;j<l/2;j++)
	 {
	  A00[a][b++] = A[i][j] ;
	 }
     b= 0 ;
     a++ ;
   }
    a=0;
	b=0 ;
    for(int i=0;i<l/2 ;i++)
    {// A01
     for(int j=l/2 ; j<l ;j++)
     {
      A01[a][b++] = A[i][j] ;
     }
    b = 0;
    a++ ;
   }
    a=0;
	b=0 ;
	for(int i=l/2 ; i<l;i++)
	{// A10
	 for(int j = 0;j<l/2 ;j++)
	 {
       A10[a][b++] = A[i][j] ;
     }
    b=0;
    a++ ;
   }
   a=0;
   b=0 ;
   for(int i=l/2;i<l;i++)
   {// A11
    for(int j=l/2;j<l;j++)
	{
	 A11[a][b++] = A[i][j] ;
	}
    b=0;
    a++ ;
   }	
   }
   // for the partition of B
   a=0;
   b=0 ;
   {
   for(int i = 0;i<l/2;i++)
	{// in B00
	 for(int j=0;j<l/2;j++)
	 {
	  B00[a][b++] = B[i][j] ;
	 }
     b= 0 ;
     a++ ;
   }
    a=0;
	b=0 ;
    for(int i=0;i<l/2 ;i++)
    {// B01
     for(int j=l/2 ; j<l ;j++)
     {
      B01[a][b++] = B[i][j] ;
     }
    b = 0;
    a++ ;
   }
    a=0;
	b=0 ;
	for(int i=l/2 ; i<l;i++)
	{// B10
	 for(int j = 0;j<l/2 ;j++)
	 {
       B10[a][b++] = B[i][j] ;
     }
    b=0;
    a++ ;
   }
   a=0;
   b=0 ;
   for(int i=l/2;i<l;i++)
   {// B11
    for(int j=l/2;j<l;j++)
	{
	 B11[a][b++] = B[i][j] ;
	}
    b=0;
    a++ ;
   }	
   }
   int S1[][] = new int[l/2][l/2],S2[][] = new int [l/2][l/2] ,S3[][] = new int [l/2][l/2] ,S4[][] = new int[l/2][l/2] ;
   int S5[][] = new int[l/2][l/2],S6[][] = new int [l/2][l/2] ,S7[][] = new int [l/2][l/2] ,S8[][] = new int[l/2][l/2] ;
   int S9[][] = new int[l/2][l/2],S10[][] = new int[l/2][l/2] ;
   S1 = M.sub(B01,B11) ;
   S2 = M.add(A00,A01) ;
   S3 = M.add(A10,A11) ;
   S4 = M.sub(B10,B00) ;
   S5 = M.add(A00,A11) ;
   S6 = M.add(B00,B11) ;
   S7 = M.sub(A01,A11) ;
   S8 = M.add(B10,B11) ;
   S9 = M.sub(A00,A10) ;
   S10= M.add(B00,B01) ;
   int P1[][] = new int[l][l],P2[][] = new int[l][l], P3[][] = new int[l][l],P4[][] = new int[l][l], P5[][] = new int[l][l], P6[][] = new int[l][l], P7[][] = new int[l][l];
   P1 = M.multiply(A00,S1) ;
   P2 = M.multiply(S2,B11) ;
   P3 = M.multiply(S3,B00) ;
   P4 = M.multiply(A11,S4) ;
   P5 = M.multiply(S5,S6)  ;
   P6 = M.multiply(S7,S8) ;
   P7 = M.multiply(S9,S10) ;
   
   C00 = M.add(M.add(P5,P4),M.sub(P6,P2)) ;
   C01 = M.add(P1,P2) ;
   C10 = M.add(P3,P4) ;
   C11 = M.sub(M.add(P5,P1),M.add(P3,P7)) ;
   int C[][] = new int[l][l] ;
   /*
   C00 = M.add(M.multiply(A00,B00),M.multiply(A01,B10)) ;
   C01 = M.add(M.multiply(A00,B01),M.multiply(A01,B11)) ;
   C10 = M.add(M.multiply(A10,B00),M.multiply(A11,B10)) ;
   C11 = M.add(M.multiply(A10,B01),M.multiply(A11,B11)) ;
  */ 
   for(int i=0;i<l;i++) // For normal method
   {
    for(int j=0;j<l;j++)
	{
	 if(i<l/2 && j<l/2)
	 {
	  C[i][j] = C00[i][j] ;
	 }
     else if(i<l/2 && j>=l/2)
     {
      C[i][j] = C01[i][j-l/2] ;
     }
    else if(i>=l/2 && j<l/2)
     {
      C[i][j] = C10[i-l/2][j] ;
     }
    else
     {
      C[i][j] = C11[i-l/2][j-l/2] ;
     }
    
   }
     } 
	 return C ;

}
}
 public static void main(String args[])
 {
  StrassenM M = new StrassenM() ;
  Scanner in = new Scanner(System.in) ;
  System.out.println("Enter the degree of the matrix.") ;
  int n = in.nextInt() ;
  System.out.println("Enter the Matrix number 1.") ;
  int A[][] = new int[n][n], B[][] = new int[n][n] , C[][] = new int[n][n] ;
  for(int i=0;i<n;i++)
  {
   for(int j=0;j<n;j++)
   {
    System.out.println("Enter the value of element ("+i+","+j+") of the first matrix.") ;
	A[i][j] = in.nextInt() ;
   }
 }
 System.out.println("Enter the Matrix number 2.") ;
 for(int i=0;i<n;i++)
 {
  for(int j=0;j<n;j++)
  {
   System.out.println("Enter the value of element ("+i+","+j+") of the second matrix.") ;
   B[i][j] = in.nextInt() ;
  }
 }
  C = M.multiply(A,B) ;
  System.out.println("Their multiplication is: ") ;
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
   
   