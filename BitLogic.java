class BitLogic
{
 public static void main(String args[])
 {
  String binary[]={
  "0000","0001","0010","0011","0100","0101","0110","0111","1000","1001","1010","1011","1100","1101","1110","1111"
  } ;
  int a=3,b=6,c=a|b ,d=a&b ,e=a^b ,f=(~a&b)|(a&~b) ,g=~a&0x0f ;
  System.out.print("a= "+ binary[a]+"\nb= "+ binary[b]+"\nc= "+binary[c]+"\na&b= "+binary[d]+"\na^b"+binary[f]+"\ne= "+binary[f]+"\n~a= "+binary[g]);
  }
  }
  