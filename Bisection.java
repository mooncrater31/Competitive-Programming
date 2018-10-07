import java.util.* ;
class Bisection
{
 
 double f(double x) 
 {
  Bisection M = new Bisection() ;
  double C = M.pow(x,2) - 5*M.pow(x,1) - 4 ; 
  return C ;
 }
 double pow(double a,double b) 
 {
  double c = 1 ;
  for(int i=0;i<b ;i++)
  {
   c = c*a ;
  }
  return c ;  
 }
 double Bisect(double a, double b,int n)
 {
  Bisection M = new Bisection() ;
  double c = (a+b)/2,d ; 
  int count=0 ; 
  while(count!=n)
  {
   
   
    c = (a+b)/2 ;
	
    d = M.f(c) ;
   
   if(d==0)
   break ;
   else if(d>0)
   {
    b = c ;
	count++ ;
   }
   else
   {
    a = c ;
    count++ ;
   }	
  }
  return c ;
 }  
  
   	
  
    
 public static void main(String args[])
 {
  Bisection B = new Bisection() ;
  Scanner in = new Scanner(System.in) ;
  double a,b ;
  System.out.println("Enter a such that, f(a)<0 .") ;
  a = in.nextDouble() ;
  System.out.println("Enter b such that, f(b)>0 .") ;
  b= in.nextDouble() ;
  System.out.println("Enter the number of cycles.") ;
  int n = in.nextInt() ;
  double c = B.Bisect(a,b,n) ;
  System.out.println("The approximate value of the function is :"+c) ;
 } 
}
 