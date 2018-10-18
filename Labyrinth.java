import java.util.* ;
import java.io.BufferedReader ;
import java.io.InputStreamReader ;

public class Labyrinth
{
    static int[] dx = {-1,1,0,0} ;//Up,down,left,right
    static int[] dy = {0,0,-1,1} ;
    private static final boolean debug = false ;
    public static void main(String args[]) throws Exception
    {
        BufferedReader bro = new BufferedReader(new InputStreamReader(System.in)) ;
        String[] S = bro.readLine().split(" ") ;
        int n = Integer.parseInt(S[0]) ;
        int m = Integer.parseInt(S[1]) ;
        S = bro.readLine().split(" ") ;
        int sx = Integer.parseInt(S[0])-1 ;
        int sy = Integer.parseInt(S[1])-1 ;
        S = bro.readLine().split(" ") ;
        int left = Integer.parseInt(S[0]) ;
        int right = Integer.parseInt(S[1]) ;
        char[][] M = new char[n][m] ;
        for(int i=0;i<n;i++)
        {
            String s1 = bro.readLine() ;
            for(int j=0;j<m;j++)
                M[i][j] = s1.charAt(j) ;
        }
        
        System.out.println(touch(M,sx,sy,left,right)) ;


    }
    static int touch(char[][] M,int x,int y,int left,int right)
    {
        int[][][] visited = new int[M.length][M[0].length][2] ;
        boolean[][] seen = new boolean[M.length][M[0].length] ;
        int H = M.length ;
        int W = M[0].length ;
        ArrayDeque<Pair> pq = new ArrayDeque<Pair>() ;
        pq.add(new Pair(x,y,left,right)) ;
        int sum = 0 ;
        while(!pq.isEmpty())
        {
            Pair p = pq.poll() ;
            
            if(p.x<0 || p.x>=H || p.y<0 || p.y>=W) continue ;
            
            if(seen[p.x][p.y] && p.left<=visited[p.x][p.y][0] && p.right<=visited[p.x][p.y][1]) continue ;
            if(M[p.x][p.y]=='*') continue ;
            // visited[p.x][p.y] = true ;
            if(!seen[p.x][p.y]) sum++ ;
            seen[p.x][p.y] = true ;
            visited[p.x][p.y][0] = Math.max(visited[p.x][p.y][0],p.left) ;
            visited[p.x][p.y][1] = Math.max(visited[p.x][p.y][1],p.right) ;
            if(debug) System.out.println("Visiting :"+p.x+" "+p.y+" left :"+p.left+" right :"+p.right);
        
            for(int i=0;i<4;i++)
            {
                int nx = p.x+dx[i] ;
                int ny = p.y+dy[i] ;
                if(i==2) //left 
                {

                    if(p.left>0)
                        pq.add(new Pair(nx,ny,p.left-1,p.right)) ;
                }
                else if(i==3) //right
                {
                    if(p.right>0) pq.add(new Pair(nx,ny,p.left,p.right-1)) ;

                }
                else pq.add(new Pair(nx,ny,p.left,p.right)) ;
            }
        }
        return sum ;
        
    }

}
class Pair
{
    int x,y,left,right ;
    Pair(int x,int y,int left,int right)
    {
        this.x = x ;
        this.y = y ;
        this.left = left ;
        this.right = right ;
    }
}