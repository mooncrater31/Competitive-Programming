import java.util.* ;
import java.io.BufferedReader ;
import java.io.InputStreamReader ;

public class Mystery
{
	private static final boolean debug = true ;
	public static void main(String args[]) throws Exception
	{
		BufferedReader bro = new BufferedReader(new InputStreamReader(System.in)) ;
		String[] S = bro.readLine().split(" ") ;
		if(S.length>1)
		{//Format 3
			for(int i=1;i<S.length;i++)
			{
				System.out.println(countDivisors(Long.parseLong(S[i]))) ;
			}
		}
		else
		{
			String[] s2 = bro.readLine().split(" ") ;
			if(s2.length>1)
			{// format 2
				for(String s:s2)
					System.out.println(countDivisors(Long.parseLong(s))) ;
				for(String s3 = bro.readLine();s3!=null && !s3.equals("");s3 = bro.readLine())
				{
					String s4[] = s3.split(" ") ;
					for(int i=0;i<s4.length;i++)
					{
						System.out.println(countDivisors(Long.parseLong(s4[i]))) ;
					}
				}
			}
			else
			{//format 1
				System.out.println(countDivisors(Long.parseLong(s2[0]))) ;//s2.length==1
				for(String s = bro.readLine();s!=null && !s.equals("") ;s = bro.readLine())
				{
					System.out.println(countDivisors(Long.parseLong(s))) ;
				}
			}
		}
	}
	static int countDivisors(long n)
	{
		HashSet<Long> H = new HashSet<Long>() ;
		for(long i=1;i<(int)Math.sqrt(n)+1;i++)
		{
			if(n%i==0)
			{
				H.add(i) ;
				H.add(n/i) ;
			}
		}
		return H.size() ;
	}
	
}