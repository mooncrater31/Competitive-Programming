import java.util.* ;
import java.io.* ;
public class Dominator3
{
	public static void main(String args[]) throws Exception
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)) ;
		int cases = Integer.parseInt(br.readLine()) ;
		// Scanner in =  new Scanner(System.in) ;
		int N,count1,count2,adjMatrix[][],domMatrix[][],caseNumber=1;
		while(cases!=0)
		{
			cases-- ;
			// N = in.nextInt() ;
			N = Integer.parseInt(br.readLine()) ;
			adjMatrix = new int[N][N] ;
			domMatrix = new int[N][N] ;
			count1 = 0 ;
			for(int i=0;i<N;i++)
			{
				String s = br.readLine() ;
				String[] s1 = s.split(" ") ;
				for(int j=0;j<N;j++)
				{
					adjMatrix[i][j] = Integer.parseInt(s1[j]) ;
				}
			}
			
			int canReach[] = new int[N] ;
			depthFirst(adjMatrix,0,N,-1,canReach) ;
			
			for(int i=0;i<N;i++)
			{
				int visitedNodes[] = new int[N] ;
				depthFirst(adjMatrix,0,N,i,visitedNodes) ;
				for(int j=0;j<N;j++)
				{
					if(canReach[j] == 1 && visitedNodes[j]==0)
						domMatrix[i][j] = 1 ;
				}
			}
			String breakLine = "" ;
			for(int i=0;i<2*N+1;i++)
			{
				if(i==0||i==2*N)
				{ 	
					breakLine+="+" ;
				}
				else
				{
					breakLine+="-" ;
				}
			}
			System.out.println("Case "+caseNumber+":") ;
			caseNumber++ ;
			System.out.println(breakLine) ;
			for(int i=0;i<N;i++)
			{
				System.out.print("|") ;
				for(int j=0;j<N;j++)
				{
					if(domMatrix[i][j]==1)
					{
						System.out.print("Y|") ;
						
					}
					else System.out.print("N|") ;
				}
			
				System.out.println() ;
				System.out.println(breakLine) ;
			}
			
			
		}
		System.out.println()
	}
	static void depthFirst(int adjMatrix[][],int vFirst,int N,int remove,int isVisited[])
	{
		if(vFirst==remove)
		{
			return ;
		}
		isVisited[vFirst] = 1 ;
		for(int i=0;i<N;i++)
		{
			if(adjMatrix[vFirst][i]==1 && isVisited[i]!=1)
				depthFirst(adjMatrix,i,N,remove,isVisited) ;
		}
	}
}
