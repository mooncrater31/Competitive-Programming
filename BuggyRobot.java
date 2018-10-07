import java.util.* ;
import java.io.* ;
public class BuggyRobot
{
	int min(int a,int b)
	{
		return a<b?a:b ;
	}
	int counter(String S)
	{
		int L=0,R=0,D=0,U=0 ;
		for(int i=0;i<S.length();i++)
		{
			if(S.charAt(i)=='L')
				L++ ;
			else if(S.charAt(i)=='R')
				R++ ;
			else if(S.charAt(i)=='D')
				D++ ;
			else
				U++ ;
		}
		return 2*(min(L,R)+min(U,D)) ;		
	}
	public static void main(String args[]) throws Exception
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)) ;
		String line = br.readLine() ;
		String S = br.readLine() ;
		BuggyRobot B = new BuggyRobot() ;
		System.out.println(B.counter(S)) ;
	}
}
