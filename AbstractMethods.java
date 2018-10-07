import java.util.* ;
abstract class A 
{
 abstract void callme() ;
 void callmetoo()
 {
  System.out.println("This is a concrete method.") ;
 }
}
class B extends A
{
 void callme()
 {
  System.out.println("B's implementation of callme(that is declared in A actually).") ;
 }
}
class AbstractDemo
{
 public static void main(String args[])
{
 B b = new B() ;
 b.callme() ;
 b.callmetoo() ;
}
} 