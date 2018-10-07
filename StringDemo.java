class StringDemo
{
 public static void main(String args[]) 
 {
  String s1 = "First String " ;
  String s2 = "Second String" ;
  String s3 =s1 ;
 
  System.out.println("Length of s1 is: "+s1.length()) ;
  if (s1.equals(s2))
  System.out.println("s1 = s2 ") ;
  else 
  System.out.println("s1!=s2") ;
 
  if (s1.equals(s3))
  System.out.println("s1=s3") ;
  else
  System.out.println("s1!=s3") ;
 
  System.out.println("For string s1 character at index 3 is : "+s1.charAt(3)) ;
 }
}