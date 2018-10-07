class TestOAP
{
 int a,b ;
  
 TestOAP(int i,int j)
 {
  a=i ;
  b=j ;
 }

boolean equalTo(TestOAP o)
{
 if(o.a==a&& o.b==b) return true ;
 else return false ;
}
}

class PassOb
{
 public static void main(String args[])
 {
  TestOAP ob1=new TestOAP(100,22) ;
  TestOAP ob2=new TestOAP(100,22) ;
  TestOAP ob3=new TestOAP(-1,-1) ;
  
  System.out.println("ob1=ob2: "+ob1.equalTo(ob2)) ;
  System.out.println("ob1=ob3: "+ob1.equalTo(ob3)) ;
 }
} 