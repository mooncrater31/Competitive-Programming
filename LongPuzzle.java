import java.util.* ;
import java.io.BufferedReader ;
import java.io.InputStreamReader ;

public class LongPuzzle
{
    public static void main(String args[]) throws Exception
    {
        BufferedReader bro = new BufferedReader(new InputStreamReader(System.in)) ;
        String[] S = bro.readLine().split(" ") ;
        int n = Integer.parseInt(S[0]) ;
        int m = Integer.parseInt(S[1]) ;
        char[][] grid = new char[n][m] ;
        for(int i=0;i<n;i++)
        {
            String temp = bro.readLine() ;
            for(int j=0;j<m;j++)
            {
                grid[i][j] = temp.charAt(j) ;
            }
            
        }
        S = bro.readLine().split(" ") ;
        int sx = Integer.parseInt(S[0])-1 ;
        int sy = Integer.parseInt(S[1])-1 ;
        S = bro.readLine().split(" ") ;
        int dx = Integer.parseInt(S[0])-1 ;
        int dy = Integer.parseInt(S[1])-1 ;
        int[][] dist = dijkstra(new Triplet(sx,sy,0),grid) ;
        System.out.println(dist[dx][dy]==(int)1e8?-1:dist[dx][dy]) ;
        
        
    }
    static int[][] dijkstra(Triplet source,char[][] grid)
    {
        int n = grid.length ;
        int m = grid[0].length ;
        int[] xc = {-1,0,1,0} ;
        int[] yc = {0,-1,0,1} ;
        
        boolean[][] visited = new boolean[n][m] ;
        int[][] dist = new int[n][m] ;
        for(int i=0;i<n;i++)
            Arrays.fill(dist[i],(int)1e8) ;
        PriorityQueue<Triplet> pq = new PriorityQueue<Triplet>() ;
        dist[source.a][source.b] = 0 ;
        pq.add(source) ;
        while(!pq.isEmpty())
        {
            Triplet t = pq.poll() ;
            if(!visited[t.a][t.b])
            {
                visited[t.a][t.b] = true ;
                if(dist[t.a][t.b]<t.c)
                    continue ;
                for(int i=0;i<4;i++)
                {
                    int x = t.a+xc[i] ;
                    int y = t.b+yc[i] ;
                    if(x>=0 && x<n && y>=0 && y<m)
                    {
                        if(!visited[x][y])
                        {
                            int base = (int)grid[t.a][t.b] ;
                            int to = (int)grid[x][y] ;
                            if(Math.abs(base-to)+dist[t.a][t.b]<dist[x][y])
                            {
                                dist[x][y] = dist[t.a][t.b]+Math.abs(base-to) ;
                                pq.add(new Triplet(x,y,dist[x][y])) ;
                            }
                        }
                    }
                }
            }
        }
        return dist ;
        
    }
}
class Triplet implements Comparable<Triplet>
{
    int a,b,c ;
    Triplet(int a,int b,int c)
    {
        this.a = a ;
        this.b = b ;
        this.c = c ;
    }
    @Override
    public int compareTo(Triplet t)
    {
        return this.c>t.c?1:(this.c<t.c?-1:0) ;
    }
}
