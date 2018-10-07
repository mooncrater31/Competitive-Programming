import java.util.* ;

public class ColorfulPoints
{
	public static void main(String args[])
	{
		Scanner in = new Scanner(System.in) ;
		// String S = "abcd" ;
		// S.replaceAll("a","") ;
		// System.out.println(S) ;
		System.out.println(deletor(in.nextLine())) ;
	}
	static int deletor(String S)
	{
		int counter=0 ;
		boolean diff = true ;
		while(S!="" && diff )
		{
			counter++ ;
			diff = false ;
			for(int i=0;i<S.length();i++)
			{
				// System.out.println("-->checking :"+"#"+i+" :"+S.charAt(i));
				int j=i ;
				while(j+1<S.length() && S.charAt(j)!=S.charAt(j+1))
				{
					diff = true ;
					j++;
				}
				// if(j+1==S.length())
					// System.out.println("Yay!");
				if(j!=i)
				for(int k=i;k<j+1;k++)
				{
					// S.charAt(k)=' ';
					StringBuilder sb = new StringBuilder(S) ;
					sb.setCharAt(k,' ');
					S = sb.toString() ;
				}
				if(j!=i)
				i=j;
			}
			// System.out.println(S+"\"") ;
			
			S = S.replaceAll(" ","") ;
			// System.out.println(S+"\"") ;
		}
			
		return counter-1 ;
	}
}