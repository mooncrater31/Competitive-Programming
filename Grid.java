import java.util.* ;
import java.io.BufferedReader ;
import java.io.InputStreamReader ;

public class Grid
{
    private static final boolean debug = false ;
    public static void main(String args[]) throws Exception
    {
        BufferedReader bro = new BufferedReader(new InputStreamReader(System.in)) ;
        String[] S = bro.readLine().split(" ") ;
        int n = Integer.parseInt(S[0]) ;
        int m = Integer.parseInt(S[1]) ;
        int q = Integer.parseInt(S[2]) ;
        char[][] grid = new char[n][m] ;
        for(int i=0;i<n;i++)
        {
            String S2 = bro.readLine() ;
            for(int j=0;j<m;j++)
                grid[i][j] = S2.charAt(j) ;
        }
        S = bro.readLine().split(" ") ;
        int sx = Integer.parseInt(S[0])-1 ;
        int sy = Integer.parseInt(S[1])-1 ;
        Triplet source = new Triplet(sx,sy,0) ;
        int[][] dist = bfs(source,grid) ; 
        for(int i=0;i<q;i++)
        {
            S = bro.readLine().split(" ") ;
            int dx = Integer.parseInt(S[0])-1 ;
            int dy = Integer.parseInt(S[1])-1 ;
            System.out.println(dist[dx][dy]) ;
            
        }
        
    }
    
    static int[][] bfs(Triplet source,char[][] grid)
    {
        int n = grid.length, m = grid[0].length ;
        boolean[][] visited = new boolean[n][m] ;
        int[][] dist = new int[n][m] ;
        for(int i=0;i<n;i++)
            Arrays.fill(dist[i],-1) ;
        int[] xc = {-1,0,1,0} ;
        int[] yc = {0,-1,0,1} ;
        ArrayDeque<Triplet> dq = new ArrayDeque<Triplet>() ;
        dq.add(source) ;
        dist[source.a][source.b] = 0 ;
        while(!dq.isEmpty())
        {
            
            Triplet p = dq.poll() ;
            if(debug) System.out.println("::::"+p.a+" "+p.b) ;
            if(!visited[p.a][p.b])
            {
                visited[p.a][p.b] = true ;
                for(int i=0;i<4;i++)
                {
                    int x = p.a+xc[i] ;
                    int y = p.b+yc[i] ;
                    
                    if(x>=0 && x<n && y >=0 && y<m)
                    {
                        if(!visited[x][y] && !(grid[x][y]=='*'))
                        {
                            dq.add(new Triplet(x,y,p.c+1)) ;
                            dist[x][y] = p.c+1 ;
                        }
                    }
                }
            }
        }
        return dist ;
    }
}
class Triplet
{
    int a,b,c ;
    Triplet(int a,int b,int c)
    {
        this.a = a ;
        this.b = b ;
        this.c = c ;
    }
}