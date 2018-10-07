import java.util.* ;
class node
{
	node parent ;
	int value ;
	int color ;
	node(int v,int c)
	{
		value = v ;
		color = c ;
	}
	
}
class Mancunian
{
	public static void main(String args[])
	{
		Scanner in = new Scanner(System.in) ;
		int N = in.nextInt() ;
		int C = in.nextInt() ;
		Map<Integer,node> map = new HashMap<Integer,node>() ;
		int[] par = new int[N-1] ;
		int[] col = new int[N] ;
		for(int i=0;i<par.length;i++)
		{
			par[i] = in.nextInt() ;
		}
		for(int i=0;i<col.length;i++)
		{
			col[i] = in.nextInt() ;
		}
		//Input is completed
		node root = new node(1,col[0]) ;
		map.put(new Integer(1),root) ;
		for(int i=0;i<N-1;i++)
		{
			node temp = new node(i+2,col[i+1]) ;
			temp.parent = map.get(par[i])  ;
			map.put(new Integer(i+2),temp) ;
		}
		//tree is made
		int[] results = new int[N] ;
		for(int i=0;i<N;i++)
		{
			node temp = map.get(i+1).parent ;
			while(temp!=null)
			{
				if(col[i]==temp.color)
				{
					results[i] = temp.value ;
					break ;
				}
				temp = temp.parent ;
			}
			if(results[i]==0)
				results[i] = -1 ;
		}
		for(int i=0;i<results.length;i++)
			System.out.print(results[i]+" ") ;
		
	}
}