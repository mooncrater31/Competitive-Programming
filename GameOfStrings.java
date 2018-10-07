import java.util.* ;
import java.io.BufferedReader ;
import java.io.InputStreamReader ;
//https://code.hackerearth.com/d99a48x
class GameOfStrings
{
	private static final boolean debug = true ;
    public static void main(String args[]) throws Exception
    {
        BufferedReader bro = new BufferedReader(new InputStreamReader(System.in)) ;
        int T = Integer.parseInt(bro.readLine()) ;
        for(int t=0;t<T;t++)
        {
            String[] S = bro.readLine().split(" ") ;
            int n = S[0].length() ;
            int K = Integer.parseInt(S[1]) ;
            int[] b = kmpPreprocess(S[0].toCharArray()) ;
			if(n!=1 && b[0]==n)
				System.out.println(S[0].substring(1,K)) ;
			else
			{
				//int[] b2 = kmpPreprocess2(S[0].toCharArray()) ;
				if(debug)
				{
					System.out.println("b :"+Arrays.toString(b)) ;
				}
				
				boolean done = false ;
				for(String pattern = S[0].substring(0,b[n]) ;b[b.length-1]!=0;b = kmpPreprocess(pattern.toCharArray()),pattern = S[0].substring(0,b[b.length-1]))
				{
					if(!pattern.equals("") && 1<K && S[0].substring(1,K).contains(pattern))
					{
						System.out.println(pattern) ;
						done = true ;
						break ;
					}
					
				}
				if(!done)
					System.out.println("Puchi is a cheat!") ;    
			}
            
        }
    }
    static int[] kmpPreprocess(char[] P)
    {
        int i=0,j=-1,count=0 ;
        int[] b = new int[P.length+1] ;
		char c = P[0] ;
        b[0] = -1 ;
        int m = P.length ;
        while(i<m)
        {
			
            while(j>=0 && P[i]!=P[j]) j = b[j] ;
            
			if(count!=-1 && P[i]==c)
				count++;
			else
				count = -1 ;
			i++;j++;
            b[i] = j ;
			if(debug) System.out.println("i : "+i+" j : "+j) ;
        }
		b[0] = count ;
        return b ;
    }
	static int[] kmpPreprocess2(char[] P)
    {
        int i=0,j=-1;
		char c = P[0] ;
        int[] b = new int[P.length+1] ;
        b[0] = -1 ;
        int m = P.length ;
        while(i<m)
        {
			
            while((j>=0 && P[i]!=P[j]) || j>=(i-1)-b[i]+1) j = b[j] ;
            i++;j++;
            b[i] = j ;
			if(debug) System.out.println("i : "+i+" j : "+j) ;
        }
        return b ;
    }
}