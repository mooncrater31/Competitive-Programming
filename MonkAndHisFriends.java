import java.util.* ;
import java.io.* ;
class node
{
	long value ;
	
	node left = null ;
	node right = null ;
	node(long v)
	{
		value = v ;
	}
}
class MonkAndHisFriends
{
	boolean addNode(node root,long val)
	{
			node temp = root ;
			while(temp!=null)
			{
				if(temp.value == val)
				{
					return true ;
				}
				else if(temp.value>val)
				{//go to the left child
					if(temp.left!=null)
					{//if the left child exists
						temp = temp.left ;
					}
					else
					{					
						node N = new node(val) ;
						temp.left = N ;
						if(temp.value!=val)
						{
							return false ;
						}
						return true ;
						
					}
				}
				else
				{//go to the right child
					if(temp.right!=null)
					{//if the right child exists
						temp = temp.right ;
					}
					else
					{
						node N = new node(val) ;
						temp.right = N ;
						if(temp.value!=val)
						{
							return false ;
						}
						return true ; 
					}
				}
			}
			return true ;
	}
	public static void main(String args[]) throws Exception
	{
		// Scanner in = new Scanner(System.in) ;
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)) ;
		// int tests = in.nextInt() ;
		String line = br.readLine() ;
		int tests = Integer.parseInt(line) ;
		boolean[][] result = new boolean[tests][] ;
		for(int i=0;i<tests;i++)
		{
			MonkAndHisFriends m = new MonkAndHisFriends() ;
			line = br.readLine() ;
			int N = Integer.parseInt(line.split(" ")[0]) ;
			int M = Integer.parseInt(line.split(" ")[1]) ;
			String line2 = br.readLine() ;
			result[i] = new boolean[M] ;
			node root = new node(Integer.parseInt(line2.split(" ")[0])) ;
			for(int j=1;j<N;j++)
			{
				m.addNode(root,Integer.parseInt(line2.split(" ")[j])) ;
			}
			for(int j=0;j<M;j++)
			{
				//long vvv = in.nextLong() ;
				result[i][j] = m.addNode(root,Integer.parseInt(line2.split(" ")[j+N])) ;
				//System.out.println(vvv+" was "+(result[i][j]?"":"not")+" found.") ;
			}
		}
		for(int i=0;i<tests;i++)
		{
			for(int j=0;j<result[i].length;j++)
			{
				System.out.println(result[i][j]?"YES":"NO") ;
			}
		}
	}
}