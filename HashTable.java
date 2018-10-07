class Node
{
	int key ;
	Node prev = null ;
	Node next =null ;
	Node(int n)
	{
		key = n ;
	}
}
class UltraNode
{
	Node first ;
	Node last ;
}
class HashTable
{
	UltraNode[] createHashTable(int m)
	{
		UltraNode[] arr = new UltraNode[m] ;
		return arr ;
	}
	int h(int k,int m)
	{
		return k%m ;// p is a prime, m is the size of the secondary hash table, m<p,k is the key,a and b are arbitrary constants
	}
	void insert(UltraNode[] arr,int key)
	{   
		int index = h(key,arr.length) ;
		Node N = new Node(key) ;
		if(arr[index]==null)
		{
			arr[index] = new UltraNode() ;
			arr[index].first = N ;
			arr[index].last = N ;
		}
		else
		{
			arr[index].last.next = N ;
			N.prev = arr[index].last ;
			arr[index].last = N ;
		}
	}
	void delete(UltraNode[] arr, int key)
	{
		int index = h(key,arr.length) ;
		if(search(arr,key)==null)
		{
			System.out.println("Doesn't exist!") ;
			return ;
		}
		else
		{
			Node n = search(arr,key) ;
			if(arr[index].first==n)
			{
				arr[index].first = null ;
				if(arr[index].last==n)
					arr[index].last = null ;
			}
			else
			{
				if(arr[index].last == n)
				{
					arr[index].last = n.prev ;
					arr[index].last.next = null ;
				}
				n.prev.next = n.next ;
				n.next.prev = n.prev ;
			}
		}
		
	}
	void print(UltraNode[] arr)
	{
		for(int i=0;i<arr.length;i++)
		{
			System.out.print(i+": ") ;
			if(arr[i] != null)
			{
				
				for(Node temp = arr[i].first;temp!=null;temp=temp.next)
				{
					System.out.print(temp.key+" ") ;
				}
	
			}
			System.out.println() ;
		}
	}
	Node search(UltraNode[] arr,int key)
	{
		int index = h(key,arr.length) ;
	
		if(arr[index].first==null)
		{
			return null ;
		}
		for(Node temp=arr[index].first;temp!=null;temp = temp.next)
		{
			if(temp.key == key)
				return temp ;
		}
		System.out.println("Not Found!") ;
		return null ;
	}
	public static void main(String args[])
	{
		HashTable H = new HashTable() ;
		UltraNode[] arr = H.createHashTable(10) ;
		int[] a = {4,3,11,4,88,2,87,534,2,1} ;
		for(int i=0;i<a.length;i++)
		{
			H.insert(arr,a[i]) ;
	    }
		H.print(arr) ;
		H.delete(arr,88) ;
		H.print(arr) ;
		System.out.println(H.search(arr,4).key) ;
	}
}