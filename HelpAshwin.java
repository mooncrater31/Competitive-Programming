import java.util.* ;
import java.io.BufferedReader ;
import java.io.InputStreamReader ;

public class HelpAshwin
{
    public static void main(String args[]) throws Exception
    {
        BufferedReader bro = new BufferedReader(new InputStreamReader(System.in)) ;
        int T = Integer.parseInt(bro.readLine()) ;
        for(int t=0;t<T;t++)
        {
            String[] S = bro.readLine().split(" ") ;
            int N = Integer.parseInt(S[0]) ;
            int M = Integer.parseInt(S[1]) ;
            int[][] A = new int[N][M] ;
            for(int i=0;i<N;i++)
            {
                S = bro.readLine().split(" ") ;
                for(int j=0;j<S.length;j++)
                {
                    A[i][j] = Integer.parseInt(S[j]) ;
                }
            }
            System.out.println(solve(A)) ;
        }
    }
    static int solve(int[][] M)
    {
        int maxSum=0,maxInd=0,count=0;
        for(int i=0;i<M[0].length;i++)
        {
            if(M[0][i]>M[0][maxInd])
            {
                
                maxInd = i ;
                count = 1 ;
            }
            else if(M[0][i]==M[0][maxInd])
            {
                count++ ;
            }
        }
        maxSum = M[0][maxInd] ;
        // secMaxSum = M[0][secMaxInd] ;
        for(int i=1;i<M.length;i++)
        {
            int currMaxInd = 0, currSecMaxInd = 0,secondMAX = 0 ;
            count = 0 ;
            for(int j=0;j<M[i].length;j++)
            {
                if(M[i][j]>M[i][currMaxInd])
                {
                    count = 1 ;
                    currSecMaxInd = currMaxInd ;
                    currMaxInd = j ;
                }
                else if(M[i][j]==M[i][currMaxInd])
                {
                    count++ ;
                    if(count==2)
                        secondMAX = j ;
                }
            }
            if(currMaxInd==maxInd)
            {
                if(count>1)
                {
                    maxSum+=M[i][currMaxInd] ;
                    maxInd = currMaxInd ;
                }
                else
                {
                    maxSum+=M[i][currSecMaxInd] ;
                    maxInd = currSecMaxInd ;
                }
                    
            }
            else
            {
                maxSum+=M[i][currMaxInd] ;
                maxInd = currMaxInd ;
            }
        }
        return maxSum ;
    }
}