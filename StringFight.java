import java.util.* ;
import java.io.BufferedReader ;
import java.io.InputStreamReader ;
/*
input: 'c' is the character, p times, s is the string
1. Traverse the string to get the indices of the character, in A
2. Take p values at a time, if less than p values, then 0 is the answer. (A bracket)
3. Let lft be the difference of the leftmost element of the bracket and it's left element.
4. Similarly, compute rht. 
5. Add (lft+rht+2) to the total.
*/
public class StringFight
{
	private static final boolean debug = false ;
	public static void main(String args[]) throws Exception
	{
		BufferedReader bro = new BufferedReader(new InputStreamReader(System.in)) ;
		int T = Integer.parseInt(bro.readLine()) ;
		for(int t = 0 ;t<T;t++)
		{
			String[] S = bro.readLine().split(" ") ;
			char c = S[1].charAt(0) ;
			int p = Integer.parseInt(S[0]) ;
			String str = bro.readLine() ;
			System.out.println(solve(str.toCharArray(),c,p)) ;
		}
	}
	static void prt(String S)
	{
		System.out.println(S) ;
	}
	static int solve(char[] S,char c,int p)
	{
		
		List<Integer> A = new ArrayList<Integer>() ;
		for(int i=0;i<S.length;i++)
		{
			if(c==S[i])
				A.add(i) ;
			
		}
		if(A.size()<p)
			return 0 ;
		
		// int l=1,r=p ;
		// int total = 0 ;
		if(debug) System.out.println("List :"+Arrays.toString(A.toArray())) ;
		int total = (A.get(0)+1)*(A.get(p)-A.get(p-1)) ;
		if(debug) prt("Initial :"+total) ;
		for(int l=1,r=p-1;l+r<A.size()-1;l++)
		{
			total+=(A.get(l)-A.get(l-1))*(A.get(l+r+1)-A.get(l+r)) ;
			if(debug) prt("middle :"+total) ;
		}
		total+=(S.length-A.get(A.size()-1))*(A.get(A.size()-p)-A.get(A.size()-p-1)) ;
		if(debug) prt("final :"+total) ;
		return total ;
		
	}
}
