import java.util.* ;
import java.io.BufferedReader ;
import java.io.InputStreamReader ;

public class GridOfManyXORs
{
    
    public static void main(String args[]) throws Exception
    {
        BufferedReader bro = new BufferedReader(new InputStreamReader(System.in)) ;
        int T = Integer.parseInt(bro.readLine()) ;
        for(int t=0;t<T;t++)
        {
            String[] S = bro.readLine().split(" ") ;
            int n = Integer.parseInt(S[0]) ;
            int m = Integer.parseInt(S[1]) ;
            S = bro.readLine().split(" ") ;
            int sx = Integer.parseInt(S[0]) ;
            int sy = Integer.parseInt(S[1]) ;
            int dx = Integer.parseInt(S[2]) ;
            int dy = Integer.parseInt(S[3]) ;
            int[][] grid = new int[n][m] ;
            for(int i=0;i<n;i++)
            {
                S = bro.readLine().split(" ") ;
                for(int j=0;j<m;j++)
                {
                    grid[i][j] = Integer.parseInt(S[j]) ;
                }
            }
            System.out.println(prim(grid)) ;
        }
    }
    static int prim(int[][] grid)
    {
        int n = grid.length,m=grid[0].length ;
        boolean[][] visited = new boolean[n][m] ;
        int[] dx = {-1,0,1,0} ;
        int[] dy = {0,-1,0,1} ;
        PriorityQueue<Triplet> pq = new PriorityQueue<Triplet>() ;
        pq.add(new Triplet(0,0,0)) ;
        int mst = 0 ;
        while(!pq.isEmpty())
        {
            Triplet t = pq.poll() ;
            if(!visited[t.b][t.c])
            {
                
                visited[t.b][t.c] = true ;
                mst+=t.a ;
                for(int i=0;i<4;i++)
                {
                    int x = t.b+dx[i] ;
                    int y = t.c+dy[i] ;
                    if(x>=0 && y>=0 && x<n && y<m)
                    {
                        int val = grid[t.b][t.c]^grid[x][y] ;
                        
                        pq.add(new Triplet(val,x,y)) ;
                    }
                }
            }
        }
        return mst ;
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
    public int compareTo(Triplet t){ return t.a>this.a?-1:(t.a<this.a?1:0) ;}
    
}