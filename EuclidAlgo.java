class EuclidAlgo
{
 public static void main (String args[]) 
 {
  int a = Integer.valueOf(args[0]), b = Integer.valueOf(args[1]) ;
  System.out.println("a is "+a+" and b is "+b) ;
  while ( a % b != 0 && b!=1)
  {
   int c = a ;
   a = b ;
   b = c % b ;
  }
  System.out.println(b + " is the greatest common divisor.") ;
}
}  