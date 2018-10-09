import java.util.* ;
import java.io.BufferedReader ;
import java.io.InputStreamReader ;

public class SherlockAndRobots
{
    private static final boolean debug = false ;
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
        int MAX = 3*(int)1e5+1 ;
        long[] BIT1 = new long[MAX] ;//To check sum before a number
        long[] BIT2 = new long[MAX] ;//To check how many numbers are before
        long[] ans = new long[N] ;
        for(int i=N-1;i>=0;i--)
        {
            update(queries[i][0],queries[i][1],BIT1) ;
            update(queries[i][0],1,BIT2) ;
            long k = query(queries[i][0]-1,BIT2) ;//number of elements before qi
            int no = N-i ;//Number of elements seen
            long sumUptoi = query(queries[i][0]-1,BIT1) ;//Not including element at i
            long sum = sumUptoi-(k)*queries[i][1] ;//Less humourous than i
            sum+= (no-k)*queries[i][1] - (query(MAX-1,BIT1)-sumUptoi) ;
            if(debug)
            {
                sss("i :"+i) ;
                sss("   k :"+k+" no :"+no+" sumUptoi :"+sumUptoi) ;
            }
            ans[i] = sum ;
        }
        for(long op:ans) System.out.println(op) ;
    }
    static void sss(String S)
    {
        System.out.println(S) ;
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