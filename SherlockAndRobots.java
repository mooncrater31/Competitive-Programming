import java.util.* ;
import java.io.BufferedReader ;
import java.io.InputStreamReader ;

public class SherlockAndRobots
{
    public static void main(String args[]) throws Exception
    {
        BufferedReader bro = new BufferedReader(new InputStreamReader(System.in)) ;
        int N = Integer.parseInt(bro.readLine()) ;
        int[][] queries = new int[N][2] ;
        for(int i=0;i<N;i++)
        {
            String[] S = bro.readLine().split(" ") ;
            queries[i][0] = Integer.parseInt(S[0]) ;
            queries[i][1] = Integer.parseInt(S[1]) ;
        }
        long[] BIT1 = new int[N+1] ;//To check sum before a number
        long[] BIT2 = new int[3*(int)1e5+1] ;//To check how many numbers are before

        for(int i=N-1;i>=0;i--)
        {
            update(i+1,queries[i][1],BIT1) ;
            update(queries[i][0],1,BIT2) ;
            long k = query(queries[i][0],BIT2) ;
            int no = N-i ;
            long sum = query(i+1,BIT1)-k*queries[i][1] ;
            sum+= (query(N,BIT1)-k)-(no-k)*queries[i][1] ;
            System.out.println(sum) ;
        }
    }
    static void update(int x,long val,long[] BIT)
    {
        int n = BIT.length ;
        for(;x<n;x+=x&-x) BIT[x]+=val ;

    }
    static long query(int x,long[] BIT)
    {
        long sum = 0 ;
        for(;x>0;x-=x&-x) sum += BIT[x] ;
        return sum ;
    }
}