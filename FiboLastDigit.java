class FiboLastDigit
{
 public static void main(String args[])
 {
  int n = Integer.valueOf(args[0]) ;
  int a = 0,b = 1,c ;
  for (int i=0;i<=n-2 ;i++)
  {
   c = a ;
   a = b ;
   b = c + b ;
  }
  System.out.println((a%10)) ;
 }
} 