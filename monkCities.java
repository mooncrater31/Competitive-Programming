import java.util.* ;
class monkCities
{
    static int monkCity(int N,int[][] E)
    {
        Hashtable<Integer,Boolean> ht = new Hashtable<Integer,Boolean>() ;
        int counter=0 ;
        for(int i=0;i<E.length;i++)
        {
            for(int j=0;j<E[0].length;j++)
            {
                if(!ht.containsKey(E[i][j]))
                {
                    ht.put(E[i][j],true) ;
                    counter++ ;
                }
            }
        }
        return counter ;
    }
    public static void main(String args[])
    {
		Scanner in = new Scanner(System.in) ;
		int tests = in.nextInt() ;
		int[] results = new int[tests] ;
		for(int k=0;k<tests;k++)
		{
			int N = in.nextInt() ;
			int[][] E = new int[N][2] ;
			for(int i=0;i<N;i++)
			{
				for(int j=0;j<=1;j++)
				{
					E[i][j] = in.nextInt() ;
				}
			}
			results[k] = monkCity(N,E) ;	
		}
		for(int k=0;k<tests;k++)
		{
			System.out.println(results[k]) ;
		}
        
    }
}