class IBT
{
 int a ;
 
 IBT(int i)
 {
  a=i;
 }
 IBT IncByTen()
 {
  IBT temp=new IBT(a+10) ;
  return temp ;
 }
}

class RetOb
{
 public static void main(String args[])
{
 IBT ob1= new IBT(2) ;
 IBT ob2 ;
 ob2 = ob1.IncByTen() ;
 System.out.println("ob1.a is: "+ob1.a) ;
 System.out.println("ob2.a is: "+ob2.a) ;
 
 ob2=ob2.IncByTen() ;
 System.out.println("ob2.a after second increasement: "+ob2.a) ;
 }
}

