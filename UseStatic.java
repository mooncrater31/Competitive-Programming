class UseStatic
{
 static int a = 3,b ;
 static void meth (int x)
 {
  System.out.print("a is: "+a+"\nb is: "+b+"\nx is: "+x) ;
 }
 
 static 
 {
  b= a *3 ;
 }
 
 public static void main(String args[])
 {
  meth(42) ;
 }
} 
  