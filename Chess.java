import java.io.BufferedReader ;
import java.io.InputStreamReader ;

public class Chess
{
    static int[] xa = {-2,-1,1,2,2,1,-1,-2} ;
    static int[] ya = {1,2,2,1,-1,-2,-2,-1} ;
    public static void main(String args[]) throws Exception
    {
        BufferedReader bro = new BufferedReader(new InputStreamReader(System.in)) ;
        String[] S = bro.readLine().split(" ") ;
        int a = Integer.parseInt(S[0])-1 ;
        int b = Integer.parseInt(S[1])-1 ;
        int moves = Integer.parseInt(S[2]) ;
        boolean[][] visited = new boolean[10][10] ;
        isAns = new boolean[10][10] ;
        solve(a,b,0,moves) ;
        int count=0 ;
        for(int i=0;i<10;i++)
        {
            for(int j=0;j<10;j++)
                count+=isAns[i][j]?1:0 ;
        }
        System.out.println(count) ;
        
    }
    static boolean[][] isAns ;
    static void solve(int x,int y,int moves,int maxMoves)
    {
        
        if(moves==maxMoves)
        {
            isAns[x][y]=true ;
            return ;
        }
        if(moves>maxMoves)
            return ;
        for(int i=0;i<8;i++)
        {
            
            int a = x+xa[i] ;
            int b = y+ya[i] ;
            if(isValid(a)&&isValid(b))
            {
                
                solve(a,b,moves+1,maxMoves) ;
            }
        }
    }
    static boolean isValid(int a)
    {
        return a>=0 && a<10 ;
    }
}