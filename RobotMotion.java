import java.util.* ;
import java.io.BufferedReader ;
import java.io.InputStreamReader ;

public class RobotMotion
{
    public static void main(String args[]) throws Exception
    {
        BufferedReader bro = new BufferedReader(new InputStreamReader(System.in)) ;
        for(String S = bro.readLine();!S.equals("0 0 0");S = bro.readLine())
        {
            String[] S1 = S.split(" ") ;
            int rows = Integer.parseInt(S1[0]) ;
            int cols = Integer.parseInt(S1[1]) ;
            int enterCol = Integer.parseInt(S1[2])-1 ;
            char[][] grid = new char[rows][cols] ;
            for(int i=0;i<rows;i++)
            {
                String temp = bro.readLine() ;
                for(int j=0;j<cols;j++)
                {
                    grid[i][j] = temp.charAt(j) ;
                }
            }
            int[][] steps = new int[rows][cols] ;
            boolean loop = false ;
            int nx = 0, ny = enterCol ;
            int step = 0 ;
            int backX = nx,backY = ny ;
            while(nx>=0 && nx<rows && ny>=0 && ny<cols && !loop)
            {
                backX = nx ;
                backY = ny ;
                if(steps[nx][ny]==0)//not visited until now 
                {
                   step++ ;
                   steps[nx][ny] = step ;
                   if(grid[nx][ny]=='N')
                       nx-- ;
                   else if(grid[nx][ny]=='W')
                       ny-- ;
                   else if(grid[nx][ny]=='S')
                       nx++ ;
                   else if(grid[nx][ny]=='E')
                       ny++ ;
                }
                else 
                {
                    loop = true ;
                    System.out.println((steps[nx][ny]-1)+" step(s) before a loop of "+(step-steps[nx][ny]+1)+" step(s)") ;
                }
            }
            if(!loop)
                System.out.println((steps[backX][backY])+" step(s) to exit") ;
        }
        
    }
}