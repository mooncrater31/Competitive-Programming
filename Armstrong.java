class RunArmstrong
{
 
  int pow(int n,int d)
  {
   int k=1 ;
   for (int i=0;i<d;i++)
   {
    k=k*n ;
}
return k ;
}

}
class Armstrong 
{
 public static void main (String args[])
 {
  RunArmstrong MyArm = new RunArmstrong() ;
  int n = Integer.valueOf( args[0]) , d=0;
  while(n!=0)
  {
   n=n/10 ;
   d++ ;
  } 
  int A[] = new int[d], k=d-1, sum=0,save = n ;
  while (n!=0)
  {
   A[k--]=n%10 ;
   n=n/10 ;
  } 
  for (int i=0;i<d ;i++)
  {
   sum = sum + MyArm.pow(A[i],d) ;
  }
    if (save == sum)
	System.out.println("It is an armstrong number.") ;
	else
	System.out.println("Nope, it isn't an armstrong number.") ;
 }	
 }