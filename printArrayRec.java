class ArrayPrint
{
 int values[] ;
 ArrayPrint(int i)
 {
  values = new int [i] ;
 }
 
 
 void ArrPrintLimit(int i)
 {
  for (int k=0;k<i;k++)
  {
   System.out.println("Value of the Element #"+k+" is "+ values[k]) ;
  }
 }
}

class LetsCheckArr
{
 public static void main(String args[])
 {
  ArrayPrint A = new ArrayPrint(11) ;
  
  for (int i=0;i<10;i++)
  {
   A.values[i]=i ;
  }
  A.ArrPrintLimit(10) ;
 }
} 
     