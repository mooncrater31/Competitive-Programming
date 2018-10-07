abstract class Figure
{
 double dim1,dim2 ;
 
 Figure(double a,double b)
 {
  dim1=a ;
  dim2=b ;
 }
 abstract double area() ;
 
}
class  Rectangle extends Figure
{
 Rectangle(double a,double b)
 {
  super(a,b) ;
 }
 double area()
 {
  System.out.println("Inside rectangle for area") ;
  return dim1*dim2 ;
 }
}
class Triangle extends Figure
{
 Triangle (double a,double b)
 {
  super(a,b) ;
 }  
 
 double area()
 {
  System.out.println("Inside triangle for area.") ;
  return dim1*dim2/2 ;
 }
}
class FindAreas
{
 public static void main(String args[])
 {
  
  Rectangle r = new Rectangle (9,5) ;
  Triangle t = new Triangle (10,8) ;
  Figure figuref ;
  figuref = r ;
  System.out.println("Area of rectangle is "+figuref.area()) ;
  System.out.println("Area of triangle is "+t.area()) ;

 }
} 
 