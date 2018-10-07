class BST
{
 int A2[] ;
 void bst(int A1[])
 {
  int l = A1.length ;
  int k = 1 ;
  for (int i = 1 ; i <= l ; i++)
   {
    k *=2 ;
   }
  A2 = new int[k-1] ;
  A2[1] = A1[1] ;
  for (int i =2 ;i<=l;i++)
  {
   for (int j = 1 ; i<l ;j = A1[i]>A2[j] ? 2*j+1:2*j)
   {
    if (A2[j]==0)
    {
	 A2[j] = A1 [i]	;
	 break ;
	}
  }
 }
}
 void Show()
 {
  int l = A2.length,j ;
  for ( j = l-1 ; A2[j]==0;j--) ;
  for (int i = 1; i<=j ;i++) 
  {
   System.out.println(" Element #"+i+" is "+A2[i]) ;
  }
 }
}
class TestBST
 {
  public static void main(String args[])
  {
  int A1[]={0,1,4,9,8,2,3,7} ;
  BST A = new BST() ;
  A.bst(A1) ;
  A.Show() ;
 }
} 