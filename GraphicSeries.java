class GraphicSeries
{
 boolean GraphicSeries(int[] A)
 { 
  boolean b= false ;
  int l = A.length ;
  if (l ==2 || l==1)
  return true ;
  int B[]= new int[l-1] ;
  int k = 0, j = 1 ,alpha ;
  for (int i = 1 ;i<= A[0] ; i++)
  {
   B[k++]=A[j++]-1 ;
  }
  for (int i = l-A[0]+2 ; i<=l-2 ;i++)
  {
   B[k++] = A [j++] ;
  }
  for ( alpha =0 ;alpha<=l-2 ; alpha++)
  {
   if (B[alpha]<0)
   {
    System.out.println("Not a Graphic Series.") ; 
	b = false ;
	break ;
   }
  
  }   
  if (alpha == l-2)
  b = GraphicSeries(B)  ;
  if (b == true)
  return true ;
  else 
  return false ;
  }
  }
  class checkGS
  {
   public static void main(String args[])
    {
	 int[] A={3,3,3,3} ;
	 int[] B={5,4,3,2,1} ;
	 GraphicSeries obj = new GraphicSeries() ;
	 boolean a = obj.GraphicSeries( A) ;
     
	 if (a) System.out.println("A is a graphic series.") ;
	 else System.out.println("A is not a graphic series.") ;
	 
	}
  }
  
  