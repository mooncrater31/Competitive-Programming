import java.util.* ;
import java.io.* ;

public class Wrath
{
	static int livingCounter(int[] L)
	{
		
		int len = L.length ;
		int counter = len ;
		int deathC = len-1 ;
		boolean[] dead = new boolean[len] ;
		for(int i=len-1;i>=0;i--)
		{
			int val = L[i] ;
			if(L[i]!=0 && deathC!=0 && i-L[i]<deathC)
			{
				// System.out.println("Considering "+i+" th index: "+ L[i]) ;
				int dcOld = deathC<i?deathC:i ;
				deathC = i-L[i] ;
					for(int j=dcOld-1;j>=(deathC>0?deathC:0);j--)
					{
						// System.out.println("	"+j+" is dead") ;
						dead[j] = true ;
						counter-- ;
					}
			}
		}
		// for(int i=0;i<dead.length;i++)
		// {
			// if(!dead[i])
				// counter++ ;
		// }
		return counter ;
	}
	public static void main(String args[]) throws Exception
	{
		// BufferedReader br = new BufferedReader(new InputStreamReader(System.in)) ;
		// String line1 = br.readLine() ;
		// String line2 = br.readLine() ;
		
		
		// int N = Integer.parseInt(line1.split(" ")[0]) ;
		// int[] L =  new int[N] ;
		// for(int i=0;i<N;i++)
		// {
			// L[i] = Integer.parseInt(line2.split(" ")[i]) ;
		// }
		Scanner in  = new Scanner(System.in) ;
		int N = in.nextInt() ;
		int[] L = new int[N] ;
		for(int i=0;i<N;i++)
		{
			L[i] = in.nextInt() ;
		}
		System.out.println(livingCounter(L)) ;
	}
}