import java.io.BufferedReader ;
import java.io.InputStreamReader ;
import java.util.* ;
public class SherlockAndTheGoodSubsequences
{
    private static final boolean debug = true ;
    public static void main(String args[]) throws Exception
    {
        BufferedReader bro = new BufferedReader(new InputStreamReader(System.in)) ;
        int T = Integer.parseInt(bro.readLine()) ;
        for(int t=0;t<T;t++)
        {
            String S = bro.readLine() ;
            System.out.println(solve(S)) ;  
        }
    }
    static long combi(int a,int b)
    {
        long ans = 1;
        long val = a ;
        for(int i=b;i>0;i--)
        {
            ans=(ans*val--)/i ;
        }
        return ans ;    
    }
    static long solve(String S)
    {
        int n = S.length() ;
        int[] inp = new int[n] ;
        
        for(int i=0;i<n;i++)
        {
            char c = S.charAt(i) ;
            if(c!='a' && c!='i' && c!='e' && c!='o' && c!='u')
            {
                inp[i]=1 ;
            }
        }
        if(debug) System.out.println("inp :"+Arrays.toString(inp)) ;
        long ans = 0  ;
        for(int i=1;i<n;i++) inp[i]+=inp[i-1] ;
        if(debug) System.out.println("inp :"+Arrays.toString(inp)) ;
        for(int i=0;i<n;i++)
        {
            char c = S.charAt(i) ;
            if((c=='a' || c=='i' || c=='o' ||c=='e' || c=='u') && i!=0 && i!=n-1)
            {
                int lc = inp[i-1] ;
                int rc = inp[n-1]-inp[i-1] ;
                int min = lc<rc?lc:rc ;
                for(int j=1;j<=min;j++)
                {
                    ans+=(combi(lc,j)*combi(rc,j)) ;
                }
                
            }
        }
        return (1<<n)-1-ans ;
        
    }
}