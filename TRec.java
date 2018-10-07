class RECURSIVE
{
 int rec (int n)
 {
  if (n==2)
  {
   return 2 ;
  }
  else
  {
   return 2*rec(n/2) + n ;
  }
}
}
class TRec
{
 public static void main(String args[])
 {
  RECURSIVE R = new RECURSIVE() ;
  int n = Integer.valueOf(args[0]) ;
  System.out.println(R.rec(n)) ;
 }
} 