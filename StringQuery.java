import java.util.* ;
import java.io.BufferedReader ;
import java.io.InputStreamReader ;

public class StringQuery
{
    private static final boolean debug = false ;
    public static void main(String args[]) throws Exception
    {
        BufferedReader bro = new BufferedReader(new InputStreamReader(System.in)) ;
        String S = bro.readLine() ;
        int[][] BIT = new int[26][S.length()+1] ;
        for(int i=0;i<S.length();i++)
        {
            int val = (int)S.charAt(i)-(int)'a' ;
            update(i+1,1,val,BIT) ;
        }
        int q = Integer.parseInt(bro.readLine()) ;
        boolean[] vis = new boolean[S.length()] ;
        for(int i=0;i<q;i++)
        {
            String[] inp = bro.readLine().split(" ") ;
            int occ = Integer.parseInt(inp[0]) ;
            int ch = inp[1].charAt(0)-'a' ;
            int del = getDelPos(occ,ch,BIT) ;
            vis[del-1]=true ;
        }
        for(int i=0;i<S.length();i++)
        {
            if(!vis[i]) System.out.print(S.charAt(i)) ;
        }
        
    }
    static void update(int x,int val,int ch,int[][] BIT)
    {
        int n = BIT[ch].length; 
        for(;x<n;x+=x&-x)
        {
            BIT[ch][x]+=val ;
        }
    }
    static int query(int x,int ch,int[][] BIT)
    {
        int sum = 0 ;
        for(;x>0;x-=x&-x)
        {
            
            sum+=BIT[ch][x] ;
        }
        return sum ;
    }
    static int getDelPos(int occ,int ch,int[][] BIT)
    {
        int n = BIT[ch].length ;
        int lb = 0, ub = n-1,ans=-1 ;
        while(lb<=ub)
        {
            int mid = (lb+ub)>>1 ;
            
            if(occ<=query(mid,ch,BIT))//The "=" is very important
            {
                ub = mid-1 ;
                ans = mid ;
            }
            else lb = mid+1 ;
        }
        update(ans,-1,ch,BIT) ;
        return ans ;
    }
}