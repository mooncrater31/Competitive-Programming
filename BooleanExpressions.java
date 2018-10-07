import java.util.* ;
import java.io.BufferedReader ;
import java.io.InputStreamReader ;

public class BooleanExpressions
{
    long MOD = (int)1e9+9 ;
    public static void main(String args[]) throws Exception
    {
        
    }
    static int solve(int[][][] dp,int s,int e,boolean res,String ops)
    {
        int n = dp.length ;
        int bef0=1,aft0=1,bet0=1 ;
        int bef1=1,aft1=1,bet1=1 ;
        long ans1 = 0, ans0 = 0 ;
        bet0 = dp[s][e][0] ;
        bet1 = dp[s][e][1] ;
        if(s!=0)
        {
            bef0 = dp[0][s-1][0] ;
            bef1 = dp[0][s-1][1] ;
        }
        if(e != n-1)
        {
            aft0 = dp[e+1][n-1][0] ;
            aft1 = dp[e+1][n-1][1] ;
        }            
        if(s!=0 && e!=n-1)
        {
            if(ops.getChar(s-1)=='a' && ops.getChar(e)=='a')
            {
                ans1+=bef1*bet1*aft1 ;
                ans0+= bef1*bet1*aft0 + bef1*bet0*aft1 + bef0*bet1*aft1 + bef0*bet0*aft1 + bef0*bet1*aft0 + bef1*bet0*aft1 + bef1*bet0*aft0 + bef0*bet0*aft0 ;
            }
            else if(ops.getChar(s-1)=='a' && ops.getChar(e)=='o')
            {
                ans+=bef1*
            }
        }
        else 
        {
            
        }
        
        
    }
    static int mod(long num)
    {
        return (int)((num+MOD)%MOD) ;
    }
    
    static int[][][] dpCreation(String bs,String ops)
    {
        String[] BS = bs.split(""); 
        
        int n = bs.length() ;
        int[] nos = new int[n] ; 
        for(int i=1;i<n;i++) nos[i-1]=Integer.parseInt(BS[i]) ;
        
        long[][][] dp = new long[n][n][2] ;
        for(int i=0;i<n-1;i++)
        {//Initialization
            if(ops.get(i)=='a')
            {
                dp[i][i+1][nos[i]&nos[i+1]]++ ;
            }
            else if(ops.get(i)=='o')
                dp[i][i+1][nos[i]|nos[i+1]]++ ;
            else if(ops.get(i)=='x')
                dp[i][i+1][nos[i]^nos[i+1]]++ ;
            
        }
        for(int d=2;d<n;d++)
        {
            for(int i=0;i+d<n;i++)//from i to i+d 
            {
                for(int j=0;j<d;j++)// i to i+j, i+j+1 to i+d 
                {
                    if(ops.get(i+j)=='a')
                    {
                        dp[i][i+d][1] = mod(dp[i][i+j][1]*dp[i+j+1][i+d][1]) ;
                        dp[i][i+d][0] = mod(mod(dp[i][i+1][0]*mod(dp[i+j+1][i+d][0]+dp[i+j+1][i+d][1]))+mod(dp[i][i+1][1]*dp[i+j+1][i+d][0])) ;
                    }
                    else if(ops.get(i+j)=='o')
                    {
                        dp[i][i+d][1] = mod(mod(dp[i][i+j][1]*mod(dp[i+j+1][i+d][0]+dp[i+j+1][i+d][1]))+mod(dp[i][i+j][0]*dp[i+j+1][i+d][1])) ;
                        dp[i][i+d][0]=mod(dp[i][i+j][0]*dp[i+j+1][i+d][0]) ;
                    }
                    else if(ops.get(i+j)=='x')
                    {
                        dp[i][i+d][1] = mod(mod(dp[i][i+j][1]*dp[i+j+1][i+d][0]) + mod(dp[i][i+j][0]*dp[i+j+1][i+d][1])) ;
                        dp[i][i+d][0] = mod(mod(dp[i][i+j][1] * dp[i+j+1][i+d][1]) + mod(dp[i][i+j][0] * dp[i+j+1][i+d][0])) ;
                    }
                }
            }
        }
        return dp ;
    }
}