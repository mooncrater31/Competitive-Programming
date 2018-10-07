import java.util.* ;
import java.io.BufferedReader ;
import java.io.InputStreamReader ;

public class MaxTipCalculator
{
	public static void main(String args[]) throws Exception
	{
		BufferedReader bro = new BufferedReader(new InputStreamReader(System.in)) ;
		int T = Integer.parseInt(bro.readLine()) ;
		for(int t=0;t<T;t++)
		{
			String[] S = bro.readLine().split(" ") ;
			int N = Integer.parseInt(S[0]) ;
			int X = Integer.parseInt(S[1]) ;
			int Y = Integer.parseInt(S[2]) ;
			int[] A = new int[N] ;
			int[] B = new int[N] ;
			S = bro.readLine().split(" ") ;
			for(int i=0;i<S.length;i++)
			{
				A[i] = Integer.parseInt(S[i]) ;
			}
			S = bro.readLine().split(" ") ;
			for(int i=0;i<S.length;i++)
			{
				B[i] = Integer.parseInt(S[i]) ;
			}
			System.out.println(solve(X,Y,A,B)) ;
		}
	}
	static long solve(int X,int Y,int[] A,int[] B)
	{
		PriorityQueue<Pair> pq = new PriorityQueue<Pair>() ;
		for(int i=0;i<A.length;i++)
		{
			pq.add(new Pair(Math.abs(A[i]-B[i]),i,A[i]>B[i]?0:1)) ;
		}
		int x = X,y = Y ;
		long sum = 0 ;
		while(!pq.isEmpty())
		{
			Pair p = pq.poll() ;
			// sum+=p.type==0?A[p.index]:B[p.index] ;
			if(p.type==0)
			{
				if(x>0)
				{
					sum+=A[p.index];
					x-- ;
				}
				else
				{
					sum+=B[p.index] ;
					y-- ;
				}
			}
			else
			{
				if(y>0)
				{
					sum+=B[p.index] ;
					y-- ;
				}
				else
				{
					sum+=A[p.index] ;
					x-- ;
				}
			}	
		}
		return sum ;
	}
}
class Pair implements Comparable<Pair>
{
	int elem,index,type ;//type 0-> A, type->1 B
	Pair(int e,int i,int t)
	{
		this.elem = e ;
		this.index = i ;
		this.type = t ;
	}
	@Override
	public int compareTo(Pair other)
	{
		return other.elem-this.elem ;
	}
}