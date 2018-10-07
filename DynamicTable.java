class Table
{
	int size = 0;
	int num = 0 ;
	int[] A ;
	Table(int n)
	{
		size = n ;
		A = new int[n] ;
	}
}
class DynamicTable//this one works like a stack
{
	Table insert(Table T,int x)
	{
		if(T.size == 0)
		{
			 T = new Table(1) ;
			 T.size = 1 ;
			 T.A[T.num++] = x ;
			 return T ;
		}
		else if(T.num == T.size)
		{
			Table T2 = new Table(2*T.size) ;
			for(int i=0;i<T.size;i++)
			{
				T2.A[i] = T.A[i] ;
			}
			T2.num = T.num ;
			T = T2 ;
		}
		T.A[T.num++] = x ;
		return T ;
	}
	void printTable(Table T)
	{
		for(int i=0;i<T.num;i++)
		{
			System.out.print(T.A[i]+" ") ;
		}
		
		System.out.println(" (T.num and T.size are :"+T.num+" "+T.size+")") ;
		
	}
	Table delete(Table T)
	{
		if(T.size == 0)
			return T ;
		else if(T.size == 1)
		{
			Table T2 = new Table(0) ;
			T = T2 ;
		}
		else if(T.num == T.size/4)
		{
			Table T2 = new Table((T.size)/2) ;
			for(int i=0;i<T.num;i++)
			{
				T2.A[i] = T.A[i] ;
			}
			T2.num = T.num ;
			T = T2 ;
			System.out.println("T.size and T2.size "+T.size +" "+T2.size) ;
		}
		T.num--;
		System.out.print(" T.size now is :"+T.size+"\n") ;
		return T ;
	}
	
	public static void main(String args[])
	{
		DynamicTable D = new DynamicTable() ;
		int[] A = {2,5,7,3,5,7} ;
		Table T = new Table(0) ;
		for(int i=0;i<A.length;i++)
		{
			T = D.insert(T,A[i]) ;
	    }
		D.printTable(T) ;
		 T =  D.delete(T) ;
		D.printTable(T) ;
		 T =  D.delete(T) ;
		D.printTable(T) ;
		 T =  D.delete(T) ;
		D.printTable(T) ;
		 T =  D.delete(T) ;
		D.printTable(T) ;
		 T =  D.delete(T) ;
		D.printTable(T) ;
		 T =  D.delete(T) ;
		
		D.printTable(T) ;
	}
}
