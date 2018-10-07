import java.util.* ;
import java.io.BufferedReader ;
import java.io.InputStreamReader ;


public class HelpOz
{
	public static void main(String args[]) 
	{
		try
		{
		BufferedReader bro = new BufferedReader(new InputStreamReader(System.in)) ;
		int m = Integer.parseInt(bro.readLine()) ;
		long[] A = new long[m] ;
		for(int i=0;i<m;i++)
		{
			A[i] = Long.parseLong(bro.readLine()) ;
		}
		List<Long> they  = solve(A) ;
		Collections.sort(they) ;
		if(they.size()==1)
			System.out.print(they.get(i)+" ") ;
		for(int i=1;i<they.size();i++)
		{
			System.out.print(they.get(i)+" ") ;
		}
		}
		catch(Exception E)
		{
			
		}
	}
	static List<Long> solve(long[] A)
	{
		Arrays.sort(A) ;
		long[] diff = new long[A.length-1] ;
		for(int i=0;i<A.length-1;i++)
		{
			diff[i] = A[i+1]-A[i] ;
		}
		HashSet<Long> H = new HashSet<Long>() ;
		long val = -1 ;
		for(int i=0;i<diff.length;i++)
		{
			
			if(i!=0 && diff[i] == val)
				continue ;
			val = diff[i] ;
			HashSet<Long> divs = findDivisors(val) ;
			if(i==0)
				H.addAll(divs) ;
			else H.retainAll(divs) ;
		}
		List<Long> they = new ArrayList<Long>(H) ;
		return they ;
	}
	static HashSet<Long> findDivisors(long val)
	{
		HashSet<Long> H = new HashSet<Long>() ;
		long limit = (long)Math.sqrt(val)+1 ;
		for(long i=1;i<limit;i++)
		{
			if(val%i==0)
			{
				H.add(i) ;
				H.add(val/i) ;
			}
		}
		return H ;
	}
}	
