import java.util.* ;
import java.io.BufferedReader ;
import java.io.InputStreamReader ;
/*
Trial division : failed. 6 points
Sieve of Eratosthenes : failed 8 points.
Segmented sieve of Eratosthenes : failed.  8 points.
*/
/*Bugs:
#1: Wrong answer for 
1
2 3
My answer: 
3
Correct answer:
2
-> Solved. Added a condition.
#2: ArrayIndexOutOfBoundsException for the input:
1
6 9
->Solved. Math.ceil couldn't work due to integer formation inside it.
#3 : Wrong answer for
1
6 9
My answer :
3
Correct answer :
1
-> Solved. high should also be considered when putting falses in "mark".
#4 : Wrong answer for 
1
3 4
My answer :
0
Correct answer :
1
-> Solved. Counting from the "prime" list must be allowed when 'l' is equal to Math.sqrt(n)+1 
*/
public class MoguLovesNumbers
{
	private static final boolean debug = true   ;
	public static void main(String args[]) throws Exception
	{
		BufferedReader bro = new BufferedReader(new InputStreamReader(System.in)) ;
		int T = Integer.parseInt(bro.readLine()) ;
		for(int t=0;t<T;t++)
		{
			String[] S = bro.readLine().split(" ") ;
			int l = Integer.parseInt(S[0]) ;
			int r = Integer.parseInt(S[1]) ;
			int temp = l<r?l:r ;
			r = l<r?r:l ;
			l = temp ;
			
			
			System.out.println(segmentedSieve(l,r)) ;
		}
		
		
	}
	static void sieveOfEratosthenes(int l,int r,List<Integer> prime)
	{
		boolean[] mark = new boolean[r+1] ;
		int counter = 0 ;
		Arrays.fill(mark,true ) ;
		
		for(int i=2;i<r+1;i++)
		{
			if(mark[i])
			{
				if(i>=l)
				{
					if(debug)
						System.out.println("Simple sieve added : "+i) ;
					prime.add(i) ;
					counter++ ;
				}
				for(int j=i*2;i*i<=r && j<r+1;j+=i)
					mark[j] = false ;
			}
		}
		// return counter ;
	}
	static int segmentedSieve(int l,int n)
	{
		int limit = (int)Math.sqrt(n)+1 ;
		List<Integer> prime = new ArrayList<Integer>() ;
		sieveOfEratosthenes(0,limit,prime) ;
		int low = limit ;
		int high = limit*2 ;
		int count = 0 ;
		while(low<n)
		{
			if(high>n)
				high = n ;
			boolean mark[] = new boolean[limit+1] ;
			Arrays.fill(mark,true ) ;
			for(int i=0;i<prime.size();i++)
			{
				int loLim = (int)(Math.ceil((float)low/prime.get(i)))*prime.get(i) ;
				if(debug)
				{
					System.out.println("low :"+low+" high "+high) ;
					if(loLim < low)
						System.out.println("loLim : "+loLim+" low : "+low+" prime.get(i) : "+prime.get(i)+":::"+Math.ceil(low/prime.get(i))) ;
				}
				for(int j=loLim;j<=high;j+=prime.get(i))
				{
					mark[j-low] = false ;
				}
			}
			for(int i = 0;i<mark.length;i++)
				if(mark[i] && (i+low>=l)&& (i+low<=high))
				{
					if(debug) {System.out.println("Added "+(i+low)+" to the list");}
					count++ ;
				}
			low+=limit ;
			high+=limit ;
		}
		if(l<=Math.sqrt(n)+1)
		{
			for(int i=0;i<prime.size();i++)
				if(prime.get(i)>=l)
					count++ ;
		}
		return count ;
		
	}
}