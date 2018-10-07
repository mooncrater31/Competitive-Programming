class A
{
 int i ;
}

class B extends A
{
 int i ;
 
 B(int a,int b)
 {
  super.i = a ;
  this.i =  b ;
 }
 void showOb ()
 { 
  System.out.println("i in superclass: "+super.i) ;
  System.out.println("i in subclass: "+i); 
  }
 }

class UseSuper
{
 public static void main(String args[])
{
 B subobj = new B(10,20);
 
 subobj.showOb() ;
}
} 