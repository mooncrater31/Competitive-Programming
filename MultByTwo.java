class MultByTwo
{
 public static void main(String args[])
 {
  int i;
  byte b=127 ;// 01111111
  i=b<<1 ;
  b=(byte)(b<<1) ;
  System.out.println("i and b are: "+i+" "+b);
  }
  }
  