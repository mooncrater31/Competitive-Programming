class ObjClass
{
 int a,b ;
 
 ObjClass(int i,int j) //constructor for the class ObjClass
 {
  a = i;
  b = j ;
 }
  
 void meth(ObjClass o) // method that takes in an object and changes it's internal variables
 {
  o.a*=2 ;
  o.b/=2 ;
 }
}
 class PassObjRef
{
 public static void main(String args[])
 {
 ObjClass ob = new ObjClass(15,20) ;
 System.out.println("ob.a and ob.b before call:"+ob.a+" "+ob.b) ; // printing the initial values 
 ob.meth(ob) ;// object "ob" has a method "meth" which is called, and "ob" itself is given as a parameter
 System.out.println("ob.a and ob.b after call:"+ob.a+" "+ob.b) ;
 }
} 
 