import java.util.*;
import java.io.BufferedReader ;
import java.io.InputStreamReader ;

public class GoldMines
{
    public static void main(String args[]) throws Exception
    {
        BufferedReader bro = new BufferedReader(new InputStreamReader(System.in)) ;
        String[] inp = bro.readLine().split(" ") ;
        int R = Integer.parseInt(inp[0]) ;
        int C = Integer.parseInt(inp[1]) ;
        int[][] M = new int[R][C] ;
        for(int i=0;i<R;i++)
        {
            String[] S = bro.readLine().split(" ") ;
            for(int j=0;j<C;j++)
            {
                M[i][j] = Integer.parseInt(S[j]) ;
            }
        }
        preMatrix(M) ;
        int Q = Integer.parseInt(bro.readLine()) ;
        for(int i=0;i<Q;i++)
        {
            String[] S = bro.readLine().split(" ") ;
            int x1 = Integer.parseInt(S[0]) ;
            int y1 = Integer.parseInt(S[1]) ;
            int x2 = Integer.parseInt(S[2]) ;
            int y2 = Integer.parseInt(S[3]) ;
            System.out.println(solve(M,x1,y1,x2,y2)) ;
        }
    }
    static int[][] preMatrix(int[][] M)
    {
        for(int i=0;i<M.length;i++)
        {
            for(int j=0;j<M[0].length;j++)
            {
                if(i>0) M[i][j]+=M[i-1][j] ;
                if(j>0) M[i][j]+=M[i][j-1] ;
                if(i>0 && j>0) M[i][j]-=M[i-1][j-1] ;
                
            }
        }
        return M ;
    }
    static int solve(int[][] M,int x1,int y1,int x2,int y2)
    {
        
        int ans = M[x2-1][y2-1] ;
        if(x1>1) ans-=M[x1-2][y2-1] ;
        if(y1>1) ans-=M[x2-1][y1-2] ;
        if(x1>1 && y1>1) ans+=M[x1-2][y1-2] ;
        return ans ;
        
    }
    
}