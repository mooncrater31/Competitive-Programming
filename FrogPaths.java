import java.util.* ;
import java.io.BufferedReader ;
import java.io.InputStreamReader ;

public class FrogPaths
{
    private static final boolean debug = false ;
    static boolean[][] visited ;
    public static void main(String args[]) throws Exception
    {
        BufferedReader bro = new BufferedReader(new InputStreamReader(System.in)) ;
        String[] S = bro.readLine().split(" ") ;
        int x = Integer.parseInt(S[0]) ;
        int y = Integer.parseInt(S[1]) ;
        int s = Integer.parseInt(S[2]) ;
        int t = Integer.parseInt(S[3]) ;
        visited = new boolean[x+s+1][y+s+1] ;
        System.out.println(bfs(x,y,t,s,0,0)) ;
    }
    static int bfs(int x,int y,int t,int s,int a,int b)
    {
        ArrayDeque<Pair> q = new ArrayDeque<Pair>() ;
        q.add(new Pair(a,b)) ;
        int count = 0 ;
        
        while(!q.isEmpty())
        {
            Pair p = q.poll() ;
            if(!visited[p.a][p.b])
            {
                if(debug) System.out.println("pair :"+p.a+" "+p.b) ;
                visited[p.a][p.b] = true ;
                
                if(p.a+p.b>t)
                    break ;
                boolean val = inSquare(x,y,s,p.a,p.b) ;
                if(debug) System.out.println("val :"+val) ;
                if(val) {count++;} 
                if(p.a+1<=x+s && !visited[p.a+1][p.b]) q.add(new Pair(p.a+1,p.b)) ;
                if(p.b+1<=y+s && !visited[p.a][p.b+1]) q.add(new Pair(p.a,p.b+1)) ;
            }
        }
        return count ;
    }
    
    static boolean inSquare(int x,int y,int s,int a,int b)
    {
        if(a>=x && a<=x+s && b>=y && b<=y+s)
            return true ;
        return false ;
    }
    
    
}
class Pair
{
    int a,b ;
    Pair(int a,int b)
    {
        this.a = a;
        this.b = b;
    }
}