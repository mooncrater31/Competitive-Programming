class proto1vanEmdeBoasTrees
{
	boolean[][] createSuperimposedBinaryTree(boolean[] Arr)
	{
		int r = (int)(Math.log(Arr.length)/Math.log(2)) ;
		
		boolean[][] A = new boolean[r+1][] ;
		for(int i=0;i<=r;i++)
		{
			A[i] = new boolean[(int)(Math.pow(2,i))] ;
		}
		for(int i=0;i<Arr.length;i++)
		{
			A[r][i] = Arr[i] ;
		}
		for(int j=r-1;j>=0;j--)
		{
			for(int i=0;i<A[j].length;i++)
			{
				A[j][i] = A[j+1][2*i] | A[j+1][2*i+1] ;
			}
		}
		return A ;
	}
	boolean[][] delete(boolean[][] T,int i)
	{
		int r = T.length-1 ;
		T[r][i] = false ;
		int p = i ;
		for(int j = r-1 ;j>=0;j--)
		{
			p = p/2 ;
			T[j][p] = T[j+1][2*p] | T[j+1][2*p+1] ;
		}
		return T ;
	}
	boolean[][] insert(boolean[][] T,int i)
	{
		try
		{
			int r = T.length-1 ;
			T[r][i] = true ;
			int k = i ;
			for(int j=r-1;j>=0;j--)
			{
				k = k/2 ;
				T[j][k] = true ;
			}
			
		}
		catch(ArrayIndexOutOfBoundsException e)
		{
			System.out.println(i+" is not in the array.") ;
		}
		return T ;
	}
	int maximum(boolean[][] T)
	{
		if(!T[0][0])
		{
			return -1 ;
		}
		int i=0,j=0 ;
		while(i!=T.length-1)
		{
			if(T[i+1][2*j+1])
			{
				i++ ;
				j = 2*j+1 ;
			}
			else if(T[i+1][2*j])
			{
				i++ ;
				j = 2*j ;
			}
		}
		return j ;
	}
	int maximum(boolean[][] T,int row,int p)
	{
		if(!T[row][p])
		{
			return -1 ;
		}
		int i=row,j=p ;
		while(i!=T.length-1)
		{
			if(T[i+1][2*j+1])
			{
				i++ ;
				j = 2*j+1 ;
			}
			else if(T[i+1][2*j])
			{
				i++ ;
				j = 2*j ;
			}
			
		}
		return j ;
	}
	int minimum(boolean[][] T)
	{
		if(!T[0][0])
		{
			return -1 ;
		}
		int i=0,j=0 ;
		while(i!=T.length-1)
		{
			if(T[i+1][2*j])
			{
				i++ ;
				j = 2*j ;
			}
			else if(T[i+1][2*j+1])
			{
				i++ ;
				j = 2*j+1 ;
			}
		}
		return j ;
	}
	int minimum(boolean[][] T,int row,int p)
	{
		if(!T[row][p])
		{
			return -1 ;
		}
		int i=row,j=p ;
		while(i!=T.length-1)
		{
			if(T[i+1][2*j])
			{
				i++ ;
				j = 2*j ;
			}
			else if(T[i+1][2*j+1])
			{
				i++ ;
				j = 2*j+1 ;
			}
		}
		return j ;
	}
	void printSuperimposedBinaryTree(boolean[][] arr)
	{
		 for(int i=0;i<arr.length;i++) //prints the created Superimposed binary tree
		{
			for(int j=0;j<arr[i].length;j++)
			{
				System.out.print(arr[i][j]+" ") ;
			}
			System.out.println() ;
		} 
	}
	int predecessor(boolean[][] T,int i)
	{
		int row = T.length - 1 ;
		int p = i ;
		while(row!=-1)
		{
			
			int p1 = p/2 ;
			System.out.println("T["+row+"]["+2*p1+"] is :"+T[row][2*p1]) ;
			if(p!=2*p1)
			{
				if(T[row][2*p1])
				{
					p = 2*p1 ;
					System.out.println("Break was instructed!") ;
					break ;
				}
					
			}
			p=p/2 ;
			row-- ;
		}
		if(row==-1)
		{
			System.out.println("This happened.") ;
			return -1 ;
		}
		System.out.println("max :"+maximum(T,row,p)) ;
		return maximum(T,row,p) ;
		
	}
	int successor(boolean[][] T,int i)
	{
		int row = T.length-1 ;
		int p = i ;
		while(row!=-1)
		{
			int p1 = p/2 ;
			if(p!=2*p1+1)
			{
				if(T[row][2*p1+1])
				{
					p = 2*p1+1 ;
					break ;
				}
			}
			p = p/2 ;
			row-- ;
		}
		if(row==-1)
		{
			return -1 ;
		}
		System.out.println("min :"+minimum(T,row,p)) ;
		return minimum(T,row,p) ;
		
	}
	public static void main(String args[])
	{
		boolean[] A = {false,false,true,true,true,true,false,true,false,false,false,false,false,false,true,false} ;
		proto1vanEmdeBoasTrees V = new proto1vanEmdeBoasTrees() ;
		boolean[][] T = V.createSuperimposedBinaryTree(A) ; 	 	
		System.out.println("Predecessor of 14 "+V.predecessor(T,14)) ;
		System.out.println("Successor of 7 "+V.successor(T,7)) ;
	}
}