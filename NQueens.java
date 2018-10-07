import java.io.BufferedReader ;
import java.io.InputStreamReader ;

public class NQueens
{
    private static final boolean debug = true ;
    public static void main(String args[]) throws Exception
    {
        BufferedReader bro = new BufferedReader(new InputStreamReader(System.in)) ;
        int N = Integer.parseInt(bro.readLine()) ;
        boolean[][] visited = new boolean[N][N] ;
        boolean[][] ans = rec(0,N,visited) ;
        if(ans==null)
            System.out.println("Not possible") ;
        else 
            for(int i=0;i<N;i++)
            {
                for(int j=0;j<N;j++)
                {
                    System.out.print(ans[j][i]?0:1) ;
                }
                System.out.println() ;
            }
        
    }
    static void visitor(int x,int y,int N,boolean[][] visited)
    {
        
        //Row 
        for(int j=y+1;j<N;j++)
            visited[x][j]=true ;
        //Column 
        for(int i=0;i<N;i++)
            if(i!=x)
                visited[i][y]=true ;
        //Upper diagonal
        for(int i=x+1,j=y+1;i<N && j<N;i++,j++)
            visited[i][j]=true ;
        //Lower diagonal
        for(int i=x-1,j=y+1;i>=0 && j<N;i--,j++)
            visited[i][j]=true ;
        
    }
    
    static boolean[][] rec(int y,int N,boolean[][] visited)
    {
        boolean[][] vis2 = new boolean[N][N] ;
        
        if(y==N) return visited ;
        for(int i=0;i<N;i++)
        {
            if(!visited[i][y])
            {
                for(int k=0;k<N;k++) for(int j=0;j<N;j++) vis2[k][j]=visited[k][j];
                visitor(i,y,N,vis2) ;
                boolean[][] ans = rec(y+1,N,vis2) ;
                if(ans!=null) return ans ;
            }
            
        }
        return null ;
    }
}