import java.io.BufferedReader ;
import java.io.InputStreamReader ;
import java.util.* ;
class Pair
{
    int a,b ;
    Pair(int a,int b)
    {
        this.a = a;
        this.b = b;
    }
}

public class LeShivanshu
{
    static int tsp(int mask,int pos,int n)
    {
         System.out.println(" "+pos);
        if(mask==(1<<n)-1) return dist[pos][0] ;
        if(dp[mask][pos]!=-1) return dp[mask][pos] ;
        int ans = 100000;
        for(int i=0;i<n;i++)
        {
            if((mask&(1<<i))==0 && dist[pos][i]!=-1 ) 
            {
                int newAns = dist[pos][i]+tsp((mask|(1<<i)),i,n) ;
                if(ans>newAns)ans=newAns;
            }
        }
         System.out.println(ans+" "+pos);
        return dp[mask][pos]=ans ;
    }
    static int distCoo(Pair A,Pair B)
    {
        int alpha = A.a-B.a<0?B.a-A.a:A.a-B.a ;
        int beta = A.b-B.b<0?B.b-A.b:A.b-B.b ;
        
        return alpha+beta ;
    }
    static int[][] dist,dp ;
    private static final boolean debug = true ;
    public static void main(String args[]) throws Exception
    {
        BufferedReader bro = new BufferedReader(new InputStreamReader(System.in)) ;
        int N = Integer.parseInt(bro.readLine()) ;
        String[] S = bro.readLine().split(" ") ;
        dist = new int[N+3][N+3] ;
        for(int i=0;i<N+3;i++)
        {
            for(int j=0;j<N+3;j++)
            {
                dist[i][j]=-1 ;
            }
        }
        dist[N+1][N+2]=0 ;
        dist[N+2][0]=0 ;
        dist[N+2][N+2]=-1 ;
        dist[N+2][N+2]=-1 ;
        Pair[] pl = new Pair[N+2] ;
        pl[0] = new Pair(Integer.parseInt(S[0]),Integer.parseInt(S[1])) ;
        pl[N+1]=new Pair(Integer.parseInt(S[2]),Integer.parseInt(S[3])) ;
        for(int i=1;i<=N;i++)
        {
            pl[i] = new Pair(Integer.parseInt(S[2+2*i]),Integer.parseInt(S[2*i+3])) ;
        }
        
        for(int i=0;i<N+2;i++)
        {
            for(int j=0;j<N+2;j++)
            {
                dist[i][j]=distCoo(pl[i],pl[j]) ;
            }
        }
        
        if(debug)
        {
            for(int i=0;i<dist.length;i++)
                System.out.println(Arrays.toString(dist[i])) ;
        }
        dp = new int[1<<(N+3)][N+3] ;
        for(int i=0;i<(1<<(N+3));i++)
        {
            for(int j=0;j<N+3;j++)
            {
                dp[i][j]=-1 ;
            }
        }
        for(int i=0;i<dp.length;i++)
        {
            System.out.println(Arrays.toString(dp[i])) ;
        }
        
        System.out.println((tsp(1,0,N+3))) ;
        
    }
    
}