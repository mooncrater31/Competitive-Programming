class A
{
 int i,j ;
 
 void showij()
 {
  System.out.println("i and j are: "+i+" "+j) ;
 }
}

class B extends A
{
 int k ;
  
 void showk()
 {
  System.out.println("k is: "+k) ;
 }
 
 void sumijk()
 {
  System.out.println("i+j+k: "+(i+j+k)) ;
 }
}

class LetsTest
{
 public static void main(String args[])
 {
  A Sobj = new A() ;
  B sobj = new B() ;
  Sobj.i=10 ;
  Sobj.j=20 ;
  System.out.println("Contents is the Sobj :") ;
  Sobj.showij() ;
  System.out.println() ;
  sobj.i = 7 ;
  sobj.j = 8 ;
  sobj.k = 9 ;
 
  System.out.println("Contents in sobj :");
  sobj.showij() ;
  sobj.showk() ;
  System.out.println("Sum of contents :") ;
  sobj.sumijk() ;
 } 
}