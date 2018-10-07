import java.util.Scanner ;
class MSARec_-_
{
 int[] MSAC(int A[],int low,int mid,int high)
 {
  Integer myinf = Integer.MAX_VALUE ;
  int leftsum = -myinf,sum = 0,maxleft=mid,maxright=mid ;
  boolean flag1= false,flag2 = false ;
  for(int i=mid;i>=0;i--)
  {
   sum = sum+A[i] ;
   if(sum>leftsum)
   {
    leftsum = sum ;
	maxleft=i ;
	flag1 = true ;
   }
}   
   int rightsum = -myinf ;
   sum =0;
   for(int i=mid+1;i<=high;i++)
   {
    sum = sum + A[i] ;
	if(sum>rightsum)
	{
	 rightsum = sum ;
	 maxright = i ;
	 flag2 = true ;
	}
   }
 int B[] = new int[3] ;
 B[0] = maxleft ;
 B[1] = maxright ;
 //B[2] = flag1 && flag2 ?leftsum+rightsum :( flag1 ? leftsum :( flag2 ? rightsum : -myinf)) ;
 B[2] = leftsum+rightsum ;
 return B ;
}
 public static void main(String args[])
 {
  MSARec msa  = new MSARec() ;
  System.out.println("Enter the number of elements in the array.") ;
  Scanner input = new Scanner(System.in) ;
  int m= input.nextInt(),l=m-1 ;
  int C[] = new int[m],B[] = new int[3],A[] = new int[l] ;
   
  for(int i=0;i<m;i++)
  {
   System.out.println("Enter the value of element number "+(i+1)) ;
   C[i] = input.nextInt() ;
  }
  
  for(int i=0;i<l;i++)
  {
   A[i] = C[i+1]-C[i] ;
  } 
  
  B = msa.FMSA(A,0,l-1) ;
 
  System.out.println("The lower limit is "+B[0]+", the upper limit is "+(B[1]+1)+", and the sum is "+B[2]) ;
 }
int[] FMSA (int A[],int low,int high)
{
  Integer myinf = Integer.MAX_VALUE ;
  int B[] = new int[3],mid,leftlow,lefthigh,leftsum,rightlow,righthigh,rightsum,crosslow,crosshigh,crosssum ;
  MSARec msa = new MSARec() ;
 if (high == low)
 {
   B[0] = low ;
   B[1] = high ;
   B[2] = A[low] ;
   return B ;
 }
 else
 {
  mid = (low+high)/2 ;
  B = msa.FMSA(A,low,mid) ;
  leftlow = B[0];
  lefthigh = B[1] ;
  leftsum = B[2] ;
  B =  msa.FMSA(A,mid+1,high) ;
  rightlow = B[0];
  righthigh = B[1] ;
  rightsum = B[2] ;
  B = msa.MSAC(A,low,mid,high) ;
  crosslow = B[0];
  crosshigh = B[1] ;
  crosssum = B[2] ;
  if(leftsum>=rightsum && leftsum>=crosssum)
  {
   B[0] = leftlow ;
   B[1] = lefthigh ;
   B[2] = leftsum ;
   return B ;
  }
  else if(rightsum>=leftsum && rightsum>=crosssum)
  {
   B[0] = rightlow ;
   B[1] = righthigh ;
   B[2] = rightsum ;
   return B;
  }
  else
  {
   B[0] = crosslow ;
   B[1] = crosshigh ;
   B[2] = crosssum ;
   return B ;
  }
 }  
 }
} 
   
 