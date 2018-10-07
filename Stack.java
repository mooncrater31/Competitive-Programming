class Stack
{
 int stck[]=new int [10] ;
 int tos ;
void stack()
 {
  tos=-1 ;
 }
 void push (int item)
 {
  if(tos==9)
   System.out.println("STACK IS FULL.") ;
  else
   stck[++tos] = item ;
 }
int pop()
{
 if (tos<0)
  { System.out.println("Stack underflow") ;
   return 0 ;
   }
else return stck[tos--] ;
}
} 