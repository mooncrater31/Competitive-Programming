import java.util.* ;
import java.io.BufferedReader ;
import java.io.InputStreamReader ;
/*
UVa- 11060
Implementing Kahn's algorithm for topological sorting.

*/

/*Bugs:
#1: Second input is considered a number.
Solved: Did bro.readLine() for n too. Now n takes s.
#2: EOF not correctly described.
Solved: A continue when a blank is seen+ putting the break condition above the if(s.equals("")) condition.
#3: The output is wrong.
Solved: Printed M[ans[i]] instead of M[i].
*/
public class Beverages
{
	static int[] toposort(int[][] A)
	{
		int[] incoming = new int[A.length] ;
		// int[] outgoing = new int[A.length] ;
		// boolean[] inco = new boolean[A.length] ;
		HashSet<Integer> h = new HashSet<Integer>() ;
		for(int i=0;i<A.length;i++)
		{
			h.add(i) ;
		}
		for(int i=0;i<A.length;i++)
		{
			for(int j=0;j<A[i].length;j++)
			{
				if(A[i][j]==1)
				{
					incoming[j]++ ;
					// outgoing[i]++ ;
					if(h.contains(j))
						h.remove(j) ;
				}
			}
		}
		//opttion #1
		Iterator itr = h.iterator() ;
		Queue<Integer> Q = new ArrayDeque<Integer>() ;
		int[] ans = new int[A.length] ;
		int index = 0 ;
		//option #2: Find the 0 entries in the "incoming" vector
		while(itr.hasNext())
		{
			Object o = itr.next() ;
			Q.add((int)o) ;//Possible bug. Changing an object to int.---> It wasn't a bug. 
		}
		while(!Q.isEmpty())
		{
			int val = Q.poll() ;
			ans[index++]=val ;
			for(int j=0;j<A[0].length;j++)
			{
				if(A[val][j]==1)
				{
					incoming[j]-- ;
					if(incoming[j]==0)
						Q.add(j) ;
					A[val][j] = 0 ;
				}
			}
		}
		return ans ;
		
		
	}
	
	public static void main(String args[]) throws Exception
	{
		BufferedReader bro = new BufferedReader(new InputStreamReader(System.in)) ;
		int blanks = 0 ;
		int cases = 0 ;
		for(String s = bro.readLine();;s = bro.readLine())
		{
			cases++ ;
			if(blanks==2)
				break ;
			if(s.equals(""))
			{
				blanks++ ;
				continue ;
			}
			if(blanks==1 && !s.equals(""))
				blanks = 0 ;
			
			int n = Integer.parseInt(s) ;
			HashMap<String,Integer> H = new HashMap<String,Integer>() ;
			String[] M = new String[n] ;
			for(int i=0;i<n;i++)
			{
				M[i] = bro.readLine() ;
				H.put(new String(M[i]),new Integer(i)) ;
			}
			int[][] A = new int[n][n] ;
			int E = Integer.parseInt(bro.readLine()) ;
			for(int i=0;i<E;i++)
			{
				String[] ss = bro.readLine().split(" ") ;
				int a = H.get(ss[0]),b = H.get(ss[1]) ;
				A[a][b] = 1 ;
			}
			int[] ans = toposort(A) ;
			System.out.print("Case #"+cases+": Dilbert should drink beverages in this order: ") ;
			for(int i=0;i<ans.length;i++)
			{
				System.out.print(M[ans[i]]+" ") ;
			}
			System.out.println() ;
		}
	}
	
}