class Arr
{
	int arrNo ;
	Arr next = null ;
	Arr prev = null ;
	boolean in_use ;
	int[] arr ;
	Arr( int l)
    {
		arr = new int[l] ;
	}
}
class obj
{
	int arrNo ;
	int index ;
	obj(int a,int i)
	{
		arrNo = a ;
		index = i ;
	}
}
class DynamicBinarySearch
{
	Arr last = null,start = null ;
	obj search(int x)
	{
		BinarySearch B = new BinarySearch() ;
		Arr temp = start ;
		int count = 0 ;
		int pos ;
		while(temp!=null)
		{
			if(temp.in_use && x<temp.arr[temp.arr.length-1] && x>temp.arr[0])// the array node should be in use, x is more than the last and first elements of the current array node.
			{
				pos = B.BSearch(temp.arr,x) ;//binary searching inside the array 
				if(pos!=-1)//if found then return the array number and the index of the element
				{
					obj o = new obj(count,pos) ;
					return o ;
				}
			}
			else if(x == temp.arr[0])//x is the first element of the array of the current node
			{
				obj o = new obj(count,0) ;
				return o ;
			}
			else if(x == temp.arr[temp.arr.length -1])// x is the last element of the array of the cuurent node
			{
				obj o = new obj(count,temp.arr.length-1) ;
				return o ;
			}
			else// the current node's array doesn't contain x
			{
				count++ ;// it has the array number in it
				temp = temp.next ;//temp points to the next node now
			}
		}
		obj o = new obj(-1,-1) ;//if x is not found inside any array then -1,-1 is returned
		return o ;
	}
	Arr newArray()//creates a new array
	{
		Arr a ;
		if(last!=null)//if atleast a node exists in the linked list
		{
		    a = new Arr(2*last.arr.length) ;//this node's array will have twice the number of elements in it than the last node's array
			a.arrNo = last.arrNo+1 ;
			last.next = a ;
			a.prev = last ;
			a.in_use = true ;
			last = a ;
		}
		else//if no node exists initially in the linked list
		{
			a = new Arr(1) ;
			a.arrNo = 0 ;
			last  = a ;
			start = a ;
		}
		return a ;
	}
	Arr getArray(int arr_no)//arr_no is the number of the node which is requested
	{
		Arr temp = start ;
		while(temp!=null && temp.arrNo!= arr_no)
		{
			temp = temp.next ;
		}
		return temp ;
	}
    boolean empty(int[] index_arr)
	{
		int l = index_arr.length ;
		for(int i=0;i<l;i++)
		{
			if(index_arr[i]!=-1)
			{
				return false ;
			}
		}
		return true ;
	}
	void show()
	{
		Arr temp ;
		temp = start ;
		while(temp!=null)
		{
			if(temp.in_use)
			{
				System.out.println("temp.arrNo :"+temp.arrNo ) ;
				for(int i=0;i<temp.arr.length;i++)
				{
				System.out.print(temp.arr[i]+ " ") ;
				}
			
				System.out.println() ;
			}
			temp = temp.next ;
		}
		
	}
	void showIndexArray(int[] index_arr)
	{
		System.out.println("index array:") ;
		for(int i=0;i<index_arr.length;i++)
		{
			System.out.print(index_arr[i]+" ") ;
		}
		System.out.println() ;
	}
    int[] ultraMerger(int[] index_arr)
	{
		int l = index_arr.length ;
		
		int[] NewArr = new int[(int)Math.pow(2,index_arr.length-1)] ;
		Arr temp = start ;
		int arrNo=0,alpha = 0 ;//arrNo has the array's number whose first element was used, count has the current arrays index in it,alpha has the current index of NEwArr in it.
		Integer min = Integer.MAX_VALUE ;//min keeps track of the least element amongst the first(availabe) elements of the arrays of the nodes
		Arr pointer = null ;//pointer points towards the array node which has the min element in it
		 int iteration = 0;
 
		while(!empty(index_arr))//until all arrays in the linked list are empty
		{
			
			temp = start ;
			min = Integer.MAX_VALUE ;
			
			while(temp!=null && temp.arrNo<l)//finds min,alpha and arrNo
			{
				if(temp.in_use)
				{       
					
					if(temp.arr[index_arr[temp.arrNo]]<min)
					{
	 					min = temp.arr[index_arr[temp.arrNo]] ;
						
						arrNo = temp.arrNo ;
						pointer = temp ;
					}
				}
				temp = temp.next ;
			}
			
			NewArr[alpha++] = min ;
			index_arr[arrNo]++ ;	
			if(index_arr[arrNo] == pointer.arr.length )
			{
				index_arr[arrNo] = -1 ;
				pointer.in_use = false ;
			}
						
						
		}
		
		return NewArr ;
	}
    void insert(int x,int total)
	{
		total++ ;
		if(Math.log(total)/Math.log(2) %1 ==0)
		{
			Arr A = newArray() ;	
			
		}
		
		FirstSetBitFromRight F = new FirstSetBitFromRight() ;
		int i = (int)(Math.log(F.fsbfr(total))/Math.log(2)) ; //gives the array into which all the values will be copied and stuff!
		
		Arr lst = getArray(i) ; //lst contains the pointer to this ith array
		
		int[] index_arr = new int[i+1] ;//a new array that has the indexes of the first element(of the remaining) in the arrays 
		index_arr[i] = (lst.arr.length)-1 ;//since this ith array remains empty
		
		
		lst.arr[(lst.arr.length)-1] = x ;
		Arr temp = start ;
		
		
		temp.in_use = true ; //temp points towards ith array now
		for(int j=0;j<=i;j++)
		{
			if(!temp.in_use)
			{
				index_arr[j] = -1 ;//if in_use=false then those arrays are considered to be empty
			}
		}
		lst.arr = ultraMerger(index_arr) ;
		lst.in_use = true ;
	}	
    public static void main(String args[])
	{
		DynamicBinarySearch D = new DynamicBinarySearch() ;
	
		int[] a1 = {1} ;
		Arr A1 = new Arr(1) ;
		A1.arr = a1 ;
		A1.arrNo = 0 ;
		A1.in_use = true ;
		int el = 0 ;
		D.start = A1 ;
		D.last = A1 ;
		D.insert(2,++el) ;
		D.insert(3,++el) ;
		D.insert(77,++el) ;
		D.insert(44,++el) ; 
		D.show() ;
		obj o = D.search(77) ;
		System.out.println("Found in the array No "+o.arrNo+" at the index "+o.index) ;
	}		
}