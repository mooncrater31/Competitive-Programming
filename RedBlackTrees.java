 
// black is 0 and red 1	
class Node
{
 int key ;
 Node left ;
 Node right ;
 Node p ;
 int color  ;
}
class RedBlackTrees
{
 Node insert(Node T, Node z)
 {
  Node y = null ;
  Node x = T ;
  while(x!=null)
  {
   y = x ;
   if(z.key<x.key)
    x = x.left ;
   else x = x.right ;
  }
  z.p = y ;
  if(y==null)
   T = z ;
  else if (z.key<y.key)
    y.left = z ;
  else y.right = z ;
  z.left = null ;
  z.right = null ;
  z.color = 1 ;  
  T = rbInsertFixup(T,z) ;
  return T ;
 }
 Node delete(Node T,Node z)
 {
  Node y = z ;
  Node x = new Node() ;
  int  yOriginalColor = y.color ;
  if(z.left == null)
  {
   x = z.right ;
   T = rbTransplant(T,z,z.right) ;
  }
  else if(z.right == null)
  {
   x = z.left ;
   T = rbTransplant(T,z,z.left) ;
  } 
  else 
  {
   y = treeMinimum(z.right)  ;
   x = y.right ;
   if(y.p == z)
    x.p = y ;
   else 
   {
    T = rbTransplant(T,y,y.right) ;
	y.right = z.right ;
	y.right.p = y ;
   }
   T = rbTransplant(T,z,y) ;
   y.left = z.left ;
   y.left.p = y ;
   y.color = z.color ;
  if(yOriginalColor == 0)
   T = rbDeleteFixup(T,x) ;  
  return T ; 
 }
 }
 Node treeMinimum(Node T)
 {
  while (T.left!=null)
   T = T.left ;
  return T ;  
 }
 Node rbDeleteFixup(Node T, Node x)
 {
  Node w = new Node();
  while(x!=T && x.color == 0)
  {
   if(x == x.p.left)
   {
    w = x.p.right ;	
	if(w.color == 1)
	{
	 w.color = 0 ;
	 x.p.color = 1 ;
	 T = leftRotate(T,x.p) ;
	 w = x.p.right ;
	}
	if (w.left.color == 0 && w.right.color == 0)
	{
	 w.color = 1 ;
	 x = x.p ;
	}
	else if( w.right.color == 0)
	{
	 w.left.color = 0 ;
	 w.color = 1 ;
	 T = rightRotate(T,w) ;
	 w = x.p.right ;
	}
	if(w.right.color == 1)
	{
	 w.color = x.p.color ;
	 x.p.color = 0 ;
	 w.right.color = 0 ;
	 T = leftRotate(T,x.p) ;
	 x = T ;
	}
   }
   else
   {
    w = x.p.left ;
	if(w.color == 1)
	{
	 w.color = 0 ;
	 x.p.color = 1 ;
	 T = rightRotate(T,x.p) ;
	 w = x.p.left ;
	}
	if(w.right.color == 0 && w.left.color == 0)
	{
	 w.color = 1 ;
	 x = x.p ;
	}
	else if(w.left.color == 0)
	{
	 w.right.color = 0 ;
	 w.color = 1 ;
	 T = leftRotate(T,w) ;
	 w = x.p.left ;
	}
	if (w.left.color == 1)
    {
	 w.color = x.p.color ;
	 x.p.color = 0 ;
	 w.left.color = 0 ;
	 T = rightRotate(T,x.p) ;
	 x = T ;
	}
   }
     
  }
 x.color = 0 ;
 return T ; 
 }
 Node rbTransplant(Node T, Node u, Node v)
 {
  if(u.p == null)
   T = v ;
  else if(u==u.p.left)
   u.p.left = v ;
  else u.p.right = v ;
  v.p = u.p ;
  return T ;  
 }
 Node rbInsertFixup(Node T,Node x)
 {
  while(z.p.color == 1)
  {
   if(z.p == z.p.p.left)
   {
    y = z.p.p.right ;
	if(y.color == 1)
	{
	 z.p.color = 0 ;
	 y.color = 0 ;
	 z.p.p.color = 1 ;
	 z = z.p.p ;
	}
	else if(z == z.p.right)
	{
	 z = z.p ;
	 T = leftRotate(T,z) ;
	}
	z.p.color = 0 ;
	z.p.p.color = 1 ;
	T = rightRotate(T,z.p.p) ;
   }
   else
   {
    y = z.p.p.left ;
	if(y.color == 1)
	{
	 z.p.color = 0 ;
	 y.color = 0 ;
	 z.p.p.color = 1 ;
	 z = z.p.p ;
	}
	else if(z==z.p.left)
	{
	 z = z.p ;
	 T = rightRotate(T,z.p.p) ;
	}
	else
	{
	 y = z.p.p.right ;
	 if(y.color == 1)
	 {
	  z.p.color = 0 ;
	  y.color = 0 ;
	  z.p.p.color = 1 ;
	  z = z.p.p ;
	 }
	}
   }
   
  }
  T.color = 0 ;
   return T ;  
 }
 Node leftRotate(Node T,Node x)
 {
  Node y = x.right ;
  x.right = y.left ;
  if(y.left!=null)
   y.left.p = x ;
  y.p = x.p ;
  if(x.p ==null)
   T = y ;
  else if (x == x.p.left)
   x.p.left = y ;
  else
   x.p.right = y ;
  y.left = x ;
  x.p = y ;
  return T ;  
 }
 Node rightRotate(Node T,Node x)
 {
  Node y = x.left ;
  x.left = y.right ;
  if(y.right!=null)
   y.right.p = x ;
  y.p = x.p ;
 if(x.p==null)
  T = y ;
 else if( x== x.p.right)
  x.p.right = y ;
 else x.p.right = y ;
 y.left = x ;
 x.p = y ;
 return T ; 
 }
 public static void main(String args[])
 {
  Node n1 = new Node() ;
  Node n2 = new Node() ;
  Node n3 = new Node() ;
  Node n4 = new Node() ;
  n1.key = 3 ;
  System.out.println("n1.info :"+n1.key+" n1.left :"+n1.left+" n1.right :"+n1.right) ;
 }
} 