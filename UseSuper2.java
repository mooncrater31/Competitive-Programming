class A 
{
 int i ;
}
 class B extends A
{
 int  i ;
 B(int a,int b)
 {
  super.i = a ;
  i = b ;
 }
  
 void show ()
 {
  System.out.println("i in superclass: "+ super.i ) ;
  System.out.println("i in subclass: "+ i) ;
 }
}

class UseSuper2
{
 public static void main(String args[])
 {
  B subob = new B(1,2) ;
 
  subob.show() ;
 }
} 