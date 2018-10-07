import java.util.* ;

public class RadioStation
{
	public static void main(String args[])
	{
		Scanner in = new Scanner(System.in) ;
		int n = in.nextInt(),m = in.nextInt() ;
		String nothing = in.nextLine() ;
		HashMap<String,String> H = new HashMap<String,String>() ;
		for(int i=0;i<n;i++)
		{
			String S = in.nextLine() ;
			// System.out.println("--->"+S) ;
			String[] S1 = S.split(" ") ;
			H.put(S1[1],S1[0]) ;
		}
		String[] M = new String[m] ;
		String[] ans = new String[m] ;
		for(int i=0;i<m;i++)
		{
			M[i] = in.nextLine() ;
			String[] M1 = M[i].split(" ") ;
			ans[i] = H.get(M1[1].substring(0,M1[1].length()-1)) ;
		}
		for(int i=0;i<m;i++)
		{
			System.out.println(M[i]+" #"+ans[i]) ;
		}
	
	}
	
}