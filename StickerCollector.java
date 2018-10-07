import java.util.* ;
import java.io.BufferedReader ;
import java.io.InputStreamReader ;

public class StickerCollector
{
    private static final boolean debug = false   ;
    public static void main(String args[]) throws Exception
    {
        
        BufferedReader bro = new BufferedReader(new InputStreamReader(System.in)) ;
        for(String S = bro.readLine();!S.equals("0 0 0");S = bro.readLine())
        {
            String[] S1 = S.split(" ") ;
            int rows = Integer.parseInt(S1[0]) ;
            int cols = Integer.parseInt(S1[1]) ;
            int comms = Integer.parseInt(S1[2]) ;
            int startRow = -1 ;
            int startCol = -1 ;
            
            char[][] arena = new char[rows][cols] ;
            for(int i=0;i<rows;i++)
            {
                String S2 = bro.readLine() ;
                for(int j=0;j<S2.length();j++)
                {
                    arena[i][j] = S2.charAt(j) ;
                    if(arena[i][j]=='N' || arena[i][j]=='S' ||arena[i][j]=='L' || arena[i][j]=='O')
                    {
                        startRow = i ;
                        startCol = j ;
                    }
                }
                
            }
            char[] co = bro.readLine().toCharArray() ;
            System.out.println(solve(startRow,startCol,arena,co)) ;
            
        }
    }
    static int solve(int startRow,int startCol,char[][] arena,char[] co)
    {
        char face = arena[startRow][startCol] ;
        int nx = startRow ;
        int ny = startCol ;
        int tots= 0 ;
        for(int i=0;i<co.length;i++)
        {
            if(debug)
            {
                System.out.println(" At :"+nx+" "+ny) ;
            }
            if(co[i]=='E')
            {
                if(face=='N')
                    face='O' ;
                else if(face=='O')
                    face='S' ;
                else if(face=='S')
                    face='L' ;
                else face='N' ;
            }
            else if(co[i]=='D')
            {
                if(face=='N')
                    face='L' ;
                else if(face=='L')//East
                    face='S' ;
                else if(face=='S')
                    face='O' ;//West
                else face='N' ;//West to North
            }                
            else if(co[i]=='F')
            {
                int orignx = nx ;
                int origny = ny ;
                if(face=='N') nx-- ;
                else if(face=='S') nx++ ;
                else if(face=='L') ny++ ;
                else if(face=='O') ny-- ;
                
                
                
                if(nx>=0 && ny>=0 && nx<arena.length && ny<arena[0].length && arena[nx][ny]!='#')
                {
                    if(arena[nx][ny]=='*')
                    {
                        tots++ ;
                        arena[nx][ny] = '.' ;
                    }
                }
                else 
                {
                    nx = orignx ;
                    ny = origny ;
                }
            }
        }
        return tots ;
    }
}