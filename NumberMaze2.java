import java.io.IOException;
import java.util.Comparator;
import java.util. PriorityQueue; 
import java.util.Scanner;
public class NumberMaze2
{
	private static final int UNREACHABLE = Integer.MAX_VALUE / 2;
	private static final boolean debug = true ;
	private static int polls = 0 ;
	public static void main(String[] args) throws IOException 
	{
		Scanner scan = new Scanner(System.in);
		double t1 = System.nanoTime() ;
		for (int t = scan.nextInt() - 1; t >= 0; --t)
		{
			int rows = scan.nextInt();
			int cols = scan.nextInt();
			scan.nextLine() ;
			int[][] costs = new int[rows][cols] ;
			final int[] distFromSrc = new int[rows*cols];
			PriorityQueue<int[]> toprocess = new PriorityQueue<int[]>(100, new Comparator<int[]>() 
			{
				@Override
				public int compare(int[] a, int[] b){
					int delta = a[0] - b[0];
					if (delta == 0)
						delta = a[1] - b[1];
					return delta;
				}
			});
			for (int i = 0; i < rows; i++) 
			{
				String[] row = scan.nextLine().trim().split("\\s+");
				for (int j = cols - 1; j >= 0; --j) {
					costs[i][j] = Integer.parseInt( row[j]) ;
					distFromSrc[i* cols + j] = UNREACHABLE;//An index for every point. Mapping: index i-> row = i/cols , column = i%cols .
				}
			}
// Dijkstra's algorithm with some optimizations. No need to queue
// all nodes to toProcess, and end when we first reach dst node.
		distFromSrc[0] = costs[0][0]; 
		toprocess.offer(new int[] { distFromSrc[0], 0 }) ;
		while (!toprocess.isEmpty()) 
		{
			int node = toprocess.poll()[1]; 
			int x = node % cols, y = node / cols, next;
			if (node == distFromSrc.length - 1)
				break;
			
			if (x > 0) 
			{
				next = y * cols + (x - 1);
				int toNextThroughNode = distFromSrc[node] + costs[y][x - 1];
				if (toNextThroughNode < distFromSrc[next]) 
				{
					distFromSrc[next] = toNextThroughNode ;
					toprocess.offer(new int[] { distFromSrc[next], next}) ;
					polls++ ;
				}
			}
			if (x < cols - 1) 
			{
				next = y* cols + (x + 1);
				int toNextThroughNode = distFromSrc[node] + costs[y][x +1] ;
				if (toNextThroughNode < distFromSrc[next]) 
				{
					distFromSrc[next] = toNextThroughNode;
					toprocess.offer (new int[] { distFromSrc[next], next }) ;
					polls++ ;
				}
			}
			if (y > 0) 
			{
				next = (y - 1) * cols + x;
				int toNextThroughNode = distFromSrc[node] + costs[y - 1][x];
				if (toNextThroughNode < distFromSrc[next]) 
				{
					distFromSrc [next] = toNextThroughNode;
					toprocess.offer(new int [] { distFromSrc[next], next }) ;
					polls++ ;
				}
			}
			if (y < rows - 1) 
			{
				next = (y + 1)*cols + x;
				int toNextThroughNode = distFromSrc[node] + costs[y + 1][x] ;
				if (toNextThroughNode < distFromSrc[next]) 
				{
					distFromSrc[next] = toNextThroughNode;
					toprocess.offer(new int[] { distFromSrc[next], next }) ;
					polls++ ;
				}
			}
		}
		System.out.println(distFromSrc[distFromSrc.length - 1]) ;
		}
		double t2 = System.nanoTime() ;
		if(debug) 
		{
			System.out.println((t2-t1)/1000000000) ;
			System.out.println("Total adds : "+polls) ;
		}
	}
}
