class Scheduling
{
	int[] top = new int[2] ;
	top[0] = -1 ; // 0 for running 
	top[1] = -1 ; // 1 for available
	int[][] sling(int[] s,int[] f)
	{
		MergeSort M = new MergeSort() ;
		int i ;
		int[][] list = new int[s.length+f.length][2] ;
		for( i=0;i<s.length;i++)
		{
			list[i][1] = s[i] ;
			list[i][0] = i ;// saves the indexes
		}
		for(int j=i;j<list.length;j++)
		{
			list[j][1] = f[j-s.length] ;
			list[j][0] = j-s.length ;
		}
		list = M.MSort(list,0,list.length-1) ;
		int[] running = new int[s.length] ;// 0 for running Halls
		int[] avail = new int[s.length] ;// 1 for available Halls
		boolean used[] = new boolean[s.length] ;
		int[][] rec = new int[s.length][s.length+1] ;// the last column keeps track of the number of elements in the ith hall
		for(int alpha = 0;alpha<rec.length;alpha++)
		{
			for(int beta = 0;beta<rec[0].length;beta++)
			{
				rec[alpha][beta] = -1 ;
			}
		}
		for(int j = 0;j<avail.length;j++)
		{
			avail[i] = avail.length-1-i ;
		}
		top[1] = avail.length-1 ;
		
		for(int j = 0 ; j<list.length-1;j++)
		{
			if(s[list[j][0]] == list[j][1]) // starting point is added
			{
				int k = pop(avail,1) ;// hall number k is available ;
				used[k] = true ;
				running[list[j][0]] = k ;
				rec[k][rec[k][s.length-1]++] = list[j][0] ;
			}
			else // f[list[j][0] == list[j][1]
			{
				int h = running[list[j][0]] ;
				push(avail,1,h) ;
			}
		}
		return rec ;
	}
	void push(int[] arr,int i,int x)
	{
		if(!full(arr,i))
		{
			arr[top[i]++] = x ;
		}
		
	}
	int pop(int[] arr, int i)
	{
		if(!empty(arr,i))
		{
			return arr[top[i]--] ;
		}
		return -1 ;
	}
	boolean full(int[] arr,int i)
	{
		if(top[i] == arr.length-1)
			return true ;
		else return false ;
	}
	boolean empty(int[] arr,int i)
	{
		if(top[i] == -1)
		{
			return true ;
		}
		else return false ;
	}
	
	
	public static void main(String args[])
	{
		Scheduling S = new Scheduling() ;
		int[] p = {0,1,2,3,3,5,5,6,8,8,12} ;
		int[] q = {6,4,14,5,9,7,9,10,11,12,16} ;
		int[][] rec = S.sling(p,q) ;
		int i=0 ;
		while(rec[i][0]!=-1)
		{
			System.out.println("In Hall number "+i+" following activities will take place:\n") ;
			for(int j=0;j<rec[i][rec.length-1];j++)
			{
				System.out.print(rec[i][j]+" ") ;
				
			}
			System.out.println() ;
			i++ ;
		}
		
	}
	
}