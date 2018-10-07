import java.util.* ;
import java.io.BufferedReader ;
import java.io.InputStreamReader ;

public class JillRidesAgain
{
	public static void main(String args[]) throws Exception
	{
		BufferedReader bro = new BufferedReader(new InputStreamReader(System.in)) ;
		int T = Integer.parseInt(bro.readLine()) ;
		for(int t=0;t<T;t++)
		{
			int stops = Integer.parseInt(bro.readLine().trim()) ;
			int[] roads = new int[stops-1] ;
			for(int i=0;i<stops-1;i++)
			{
				roads[i] = Integer.parseInt(bro.readLine().trim()) ;
			}
			obj o = solve(roads) ;
			if(o.ans>0)
			{
				System.out.println("The nicest part of route "+(t+1)+" is between stops "+(o.init+1)+" and "+(o.end+1)) ;
			}
			else System.out.println("Route "+(t+1)+" has no nice parts") ;
		}
	}
	static obj solve(int[] A)
	{
		int ans = 0 ;
		int sum = 0 ;
		int init = 0, end = 0 ;
		int count = 0 ;
		for(int i=0;i<A.length;i++)
		{
			sum+=A[i] ;
			count++ ;
			if((sum>ans) || ((sum==ans) && (count>end-init)))
			{
				ans = sum ;
				end = i+1 ;
				init = end-count ;
			}
			if(sum<0)
			{
				sum = 0 ;
				count = 0 ;
			}
		}
		return new obj(ans,init,end) ;
	}
}
class obj
{
	int ans,init,end ;
	obj(int a,int i,int e)
	{
		this.ans = a ;
		this.init = i ;
		this.end = e ;
	}
}