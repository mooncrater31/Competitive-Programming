import java.util.* ;

public class GenerateLogin
{
	public static void main(String args[]) 
	{
		Scanner in = new Scanner(System.in) ;
		String s = in.nextLine() ;
		String[] S = s.split(" ") ;
		int l1 = S[0].length() ;
		// int l2 = length(S[1]) ;
		int s1=1 ;
		while(s1!=l1)
		{
			if(S[0].charAt(s1)<S[1].charAt(0))
			{
				s1++ ;
			}
			else
				break ;
		}
		System.out.println(S[0].subSequence(0,s1)+""+S[1].charAt(0));//+S[1].charAt(0));
	}
}