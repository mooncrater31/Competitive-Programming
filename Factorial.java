class Recursion
{
 
  int fact(int n)
  {int result ;
  
  if (n==1) return 1  ;
  else return fact(n-1)*n ;
  }
}


class TestRec
{
 public static void main(String args[])
 {
  Recursion f = new Recursion() ;
  System.out.println("Factorial of 5 is: "+f.fact(5));
 }
} 