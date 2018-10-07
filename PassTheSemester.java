import java.util.* ;
import java.io.BufferedReader ;
import java.io.InputStreamReader ;

public class PassTheSemester
{
	private static final boolean debug = false ;
	public static void main(String args[]) throws Exception
	{
		BufferedReader bro = new BufferedReader(new InputStreamReader(System.in)) ;
		int T = Integer.parseInt(bro.readLine()) ;
		for(int t=0;t<T;t++)
		{
			String[] S = bro.readLine().split(" ") ;
			int n = Integer.parseInt(S[0]) ;
			int m = Integer.parseInt(S[1]) ;
			int p = Integer.parseInt(S[2]) ;
			int[][] topics = new int[n][2] ;
			for(int i=0;i<n;i++)
			{
				S = bro.readLine().split(" ") ;
				int time = Integer.parseInt(S[0]) ;
				int weightage = Integer.parseInt(S[1]) ;
				topics[i][0] = time ;
				topics[i][1] = weightage ;
			}
			int ans = solve(topics,m,p) ;
			System.out.println(ans==-1?"NO":"YES "+ans) ;
		}
	}
	static void arrayPrinter(int[][] A)
	{
		for(int i=0;i<A.length;i++)
		{
			System.out.print(i+"::: ") ;
			for(int j=0;j<A[i].length;j++)
			{
				System.out.print(A[i][j]+" ") ;
			}
			System.out.println() ;
		}
	}
	static int solve(int[][] topics,int time,int minN)
	{
		//topic[][], with first column --> time, second column --> weightage
		int[][] DP = new int[topics.length][time+1] ;
		for(int i=topics[0][0];i<DP[0].length;i++)
		{
			DP[0][i] = topics[0][1] ;
		}
		for(int i=1;i<DP.length;i++)
		{
			for(int j=0;j<DP[i].length;j++)
			{
				DP[i][j] = Math.max(DP[i-1][j],j>=topics[i][0]?DP[i-1][j-topics[i][0]]+topics[i][1]:0) ;
			}
		}
		if(debug) 
		{
			System.out.println("DP::::"+DP[DP.length-1][DP[0].length-1]+" and minN : "+minN) ;
			arrayPrinter(DP) ;
		}
		if(DP[DP.length-1][DP[0].length-1]<minN)
			return -1 ;
		int totalTime = 0 ;
		//backtracking
		int x=DP.length-1,y =DP[0].length-1 ;
		while(x!=0)
		{
			if(DP[x][y]!=DP[x-1][y])
			{
				totalTime+=topics[x][0] ;
				if(debug) System.out.print("\ny is "+y) ;
				y-=topics[x][0] ;
				if(debug) System.out.print("Time of "+x+(x%10==1?"st":(x%10==2?"nd":(x%10==3?"rd":"th")))+" is "+topics[x][0]+"\n") ;
			}
			x-- ;
		}
		if(DP[x][y]!=0)
		{
			if(debug) System.out.println("y :"+y) ;
			totalTime+=topics[0][0] ;
		}
		return totalTime ;
	}
}