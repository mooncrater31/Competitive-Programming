class OptimalBST
{
	void constructOBST(int[][] root,int i, int j, int k)
	{
		
		if(j==i-1)
		{
			System.out.println("d"+i+" is the right child of k"+j) ;
		}
		else if(j==i)
		{
			System.out.println("k"+i+" is the left child of k"+(i+1)) ;
			System.out.println("d"+(i-1)+" is the left child of k"+i) ;
			System.out.println("d"+i+" is the right child of k"+i) ;
		}
		else
		{
			OptimalBST O = new OptimalBST() ;
			int v = root[i][j]; 
			System.out.println("k"+v+" is the"+(v>k?"right":"left")+" child of k"+k) ;
			O.constructOBST(root,i,v-1,v) ;
			O.constructOBST(root,v+1,j,v) ;
		}
	}
	int[][] OBST(int[] p,int[] q)
	{
		int n = p.length ;
	}
	public static void main() 
	{
	}
}