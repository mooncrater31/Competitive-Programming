class ImpStack
{
 private int tos ;
 private int values[] ;
 ImpStack( int limit)
 {
  tos=-1 ;
  values = new int [limit] ;
 } 
  
  void push(int item)
  {
   if (tos==values.length-1)
   System.out.println("Stack Overflow") ;
   else
   values[++tos]=item ;
  }
 int pop()
 {
  if (tos<-1)
  {
   System.out.println("Stack Underflow") ;
   return 0 ;
  }
  else
  return values[tos--] ;
 }
} 
class TestImpStack
{
 public static void main (String args[])
 { 
  ImpStack ob1 = new ImpStack(10) ;
  ImpStack ob2 = new ImpStack(10) ;
  for (int i=0 ;i<10;i++) ob1.push(i) ;
  for (int i=10 ;i<20 ;i++) ob2.push(i) ;
  
  System.out.println("Stack in mystack1 :") ;
  for (int i=0;i<10;i++)
   System.out.println(ob1.pop()) ;
  System.out.println("Stack in mystack2 :") ; 
  for (int i=0; i<10 ;i++)
   System.out.println(ob2.pop()) ;
 }
}
 
  
   