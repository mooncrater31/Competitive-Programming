class TAM
{
 int a ;
 public int b ;
 private int c ;
 void setc(int i)
 {
  c=i ;
 }
 int getc()
 {
  return c ;
 }
}

class TAMW
{
 public static void main(String args[])
{
 TAM tam = new TAM() ;
 tam.a = 10 ;
 tam.b = 20 ;
 tam.setc(100) ;
 System.out.println("a,b and c are : "+tam.a+" "+tam.b+" "+tam.getc()) ;
 }
} 