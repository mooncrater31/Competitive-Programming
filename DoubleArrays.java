import java.util.* ;
class DA
{
 Integer myinf = Integer.MAX_VALUE ;
 int n ;
 int stack[] ;
 int top1,top2 ;
 DA(int m)
 {
   n=m ;
   stack  = new int[n] ;
   top1 = -1 ;
   top2 = n ;
 } 
  
 void push1(int x)
 {
  if(top1==n-1)
   {
    System.out.println("Stack Overflow.") ;
	return ;
   }
  else if(top1==(top2-1))
  {
   System.out.println("Stack Overlap.") ;
   return ;
  }
  else
  {
   stack[++top1] =  x ;
   return ;
  }   
 }
 void push2(int x)
 {
  if(top2==0)
  {
   System.out.println("Stack Overflow.") ;
   return ;
  }
  else if(top2==(top1+1))
   {
     System.out.println("Stack Overlap.") ;
	 return ;
   }
  else
  {
   stack[--top2]= x;
   return ;
  }   
 }
 int pop1()
 {
  if(top1==-1)
  {
   System.out.println("Stack underflow.") ;
   return -myinf ;
  }
  else 
  {
   return stack[top1--] ;
  }   
 }
 int pop2()
 {
  
  if(top2==n)
  {
   System.out.println("Stack Underflow.") ;
   return -myinf ;
  }
 else
  {
   return stack[top2++] ;
  }   
 }
 void traverse1()
 {
  if(top1==-1)
   System.out.println("Empty Stack.") ;
  else
  {
   for(int i=0;i<=top1;i++)
   {
    System.out.println(stack[i]+" ") ;
   }
  }   
 }
 void traverse2()
 {
  if(top2==n)
   System.out.println("Empty Stack.") ;
   else
    {
	 for(int i=n-1;i>=top2;i--)
	  System.out.println(stack[i]) ;
	}
 }	
} 

class DoubleArrays
{
 public static void main(String args[]) 
 {
  Integer myinf = Integer.MAX_VALUE ;
  
  Scanner in = new Scanner(System.in) ;
  System.out.println("Enter the length of the array to be used.") ;
  int n = in.nextInt() ;
  DA d = new DA(n) ;
  
  do
  {
   System.out.println("Enter your choice:\n1.PUSH 1\n2. PUSH 2\n3. POP 1\n4. POP 2\n5. TRAVERSE 1\n6. TRAVERSE 2\n 7. EXIT.") ;
   int choice = in.nextInt() ;
   switch (choice)
   {
    case 1:
	 System.out.println("Enter the value to be pushed in the first stack.") ;
	 int val = in.nextInt() ;
	 d.push1(val) ;
	 break ;
	case 2:
     System.out.println("Enter the value to be pushed in the second stack.") ;
     int val2 = in.nextInt() ;
     d.push2(val2) ;
     break ;
    case 3:
     int x = d.pop1() ;
	 if(x!=-myinf)
     System.out.println("The popped value is "+x) ;
     break ;
    case 4:
     int x2 = d.pop2() ;
	 if(x2!=-myinf)
     System.out.println("The popped value is "+x2) ;
     break ;
    case 5:
     d.traverse1() ;
     break ;
    case 6:
     d.traverse2() ;
     break ;
    default:
     System.out.println("Wrong Choice.") ;
   }
  }while(n!=7) ;
 }  
 }
 