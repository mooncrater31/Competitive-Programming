//suggestion: Declare new rows for st, as we find new elements in r[]. That would save space.
//Incomplete
class stacks
{
	int[][] st
	int[] top ;
	stacks(int n)
	{
		st = new int[n][n] ;
		top = new int[n] ;
		Array.fills(top,-1) ;
	}
	void push(int[][] st,int i,int el)
	{
			st[i][++top[i]] = el ;
	}
}
class obj
{
	int[][] st ;
	int[] save ;
	obj(int[][] st1,int[] save1)
	{
		st = st1 ;
		save = save1 ;
	}
}
class OfflineCaching
{
	obj distanceConstructor(int[] r,int n)
	{
		stacks s = new stacks(n) ; //saves the indexes of the occurrence of the element.
		int[] save = new int[n] ;  // saves the relation between the index and the actual value of the element
		int k = 0 ;
		for(int i=0;i<r.length;i++) // total time O(n*r.length)
		{
			int indx = index(save,r[i]) //index gives a -1 if "el" doesn't exist in save[] , takes O(n) time.
			if(indx!=-1)
			{
				push(s.st,indx,i) ;
				
			}
			else //then add "el" into the save[], and into a new stack.
			{
				k++ ;
				save[k] = r[i] ;
				push(s.st,k,i) ;
			}
		}
		obj o = new obj(s.st,save)
		return o ;
	}
	int index(int[] save,int el)
	{
		int i ;
		for( i=0;i<save.length;i++)
		{
			if(save[i] == el)
				return i ;
		}
		return -1 ;
	}
	int twistedSearch(int[] arr, int el) //returns the index of the element less than or equal to the element searched.
	{
		int i=0 ;
		while(arr[i]<=el)
		{
			i++ ;
		}
		return i-1 ;
	}
	int distance(int[] r,int n,int el, int i)
	{
		obj o = distanceConstructor(r,n) ;
		int indx = index(o.save,el) ; //index of el in save[] and o.st
		int index2 = twistedSearch(o.st[indx],el) ; // returns the index of the element less than or equal to the element searched.
		return o.st[index2+1] - i ; 
	}
	int distance(obj o,int el,int i)
	{
		int indx = index(o.save,el) ; //index of el in save[] and o.st
		int index2 = twistedSearch(o.st[indx],el) ; // returns the index of the element less than or equal to the element searched.
		return o.st[index2+1] - i ; 
	}
	int differentElementCount(int[] r)
	{
		int[] save = new int[r.length] ;
		int k=0 ;
		for(int i=0;i<r.length;i++)
		{
			if(index(save,r[i])==-1)
			{
				save[k++] = r[i] ;
			}
		}
		return k ;
	}
	int max(int[] arr)
	{
		int mx = 0 ;
		for(int i=0;i<arr.length;i++)
		{
			if(arr[i]>arr[max])
				max = i ;
		}
		return max ;
	}
	int[][] offCaching(int[] r,int k) // r is the list of element requests and k is the size of the cache
	{// an internal 2D stack S to maintain the running tasks. A heap to maintain the runnable tasks.
		//initial filling of cache memory
		int[] cache = new int[k] ;
		int n = differentElementCount(r) ;//number of distinct elements in r :O(r.length*n) 
		boolean[] inCache = new boolean[n] ;
		obj o = distanceConstructror(r,n) ; //O(r.length*n)
		int j = 0
		for(int i=0;i!=k;j++)
		{
			int in = index(o.save,r[j]) ; //index of elements.
			if(!inCache[in])
			{
				inCache[in] = true ;
				cache[i++] = j ;
			}
		}
		//eviction and modification of cache elements
		int[][] result = new int[r.length-j][k] ;
		System.arraycopy(cache,0,result[0],0) ;
		int[] cacheDis = new int[n] ;
		for(int i=0;i<k;i++)
		{
			cacheDis[i] = distance(o,r[cache[i]],cache[i]) ;
		}
		int mx = max(cacheDis) ;
		for(int i=j+1;i<r.length;i++)
		{
			int in = index(o.save,r[i]) ;
			if(!inCache[in])
			{
				cache[mx] = i ;
				inCache[in] = true ;
				inCache[index(o.save,r[mx])] = false ;
				cacheDis[mx] = 
			}
		}
	}
}