public class InsSort
{
 public static void main(String args[])
 { 
  int count = 0,i=0 ;
  for (String x :args)
  {
   count++;
  }
  int A[] = new int[count] ;
  while(i++<count)
  {
   A[i] = Integer.valueOf(args[i]) ;
  } 
    for (int j=1;j<count ;j++)
	{
	 int key = A[j] ;
	 i = j-1 ;
	 while(i>=0 && A[i]>key)
	 {
	  A[i+1]=A[i];
	  i=i-1 ;
	 }
    A[i+1] = key ;
}
 for (int x: A)
 System.out.println(x) ;
}
 int[] IS (int A[],int p,int q)
 {
 int i=0,count = A.length ;
 
    for (int j=p;j<=q ;j++)
	{
	 int key = A[j] ;
	 i = j-1 ;
	 while(i>=0 && A[i]>key)
	 {
	  A[i+1]=A[i];
	  i=i-1 ;
	 }
    A[i+1] = key ;
}
 return A ;
}
}	