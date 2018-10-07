import java.util.* ;
class QBS
{
 Integer myinf =  Integer.MAX_VALUE ;
 int top1,top2,size ;
 int stack[] ;
 QBS(int m)
 {
  size = m ;
  top1 = -1 ;
  top2 = -1 ;
 }
 void push1(int x)
 {
   if(top1==size)
   {
    System.out.println("Overflow.") ;
	}
   else if(top1==-1 && top2==-1)
   {
    top1=0 ;
	top2=0 ;
   }	
   else
   top1++ ;
   stack[top1] = x ;
 }
 int pop2()
 {
  if(top2==-1)
  {
     
	 return myinf ;
  }
  else if(top2==top1)
  {
   int c = top1 ;
   top2=-1;
   top1=-1 ;
   return stack[c] ;
  }   
  else
  {
   return stack[top2++] ;
  } 
 }
}
class QueueByStacks
{
 public static void main(String args[]) 
 {
  Integer myinf =  Integer.MAX_VALUE ;
  Scanner in = new Scanner(System.in) ;  
  System.out.println("Enter the size of the queue.") ;
  int size = in.nextInt() ;
  QBS qbs = new QBS(size) ;
  do
  {
   System.out.println("Select a choice:\n1. Enqueue.\n2. Dequeue.\n3. Traverse.\n4. Exit.") ;
   int choice = in.nextInt() ;
   switch(choice)
   {
    case 1:
	System.out.println("Enter the value to be enqueued.") ;
	int x = in.nextInt() ;
	qbs.push1(x) ;
	break ;
	case 2:
	int val = qbs.pop2() ;
	if(val == myinf)
	 System.out.println("Underflow.") ;
	else
     System.out.println("Dequeued value id "+x) ;
    break ;
    case 3:
    qbs.traverse() ;
    break ;
    default:
     System.out.println("WTFHUEB.") ;
   }
  }while(choice!=4) ;   
 }
}
 