import java.util.* ;
import java.io.BufferedReader ;
import java.io.InputStreamReader ;

public class FuzzySearch
{
    public static void main(String args[]) throws Exception
    {
        BufferedReader bro = new BufferedReader(new InputStreamReader(System.in)) ;
        String[] S = bro.readLine().split(" ") ;
        int k = Integer.parseInt(S[2]) ;
        System.out.println(solve(bro.readLine(),bro.readLine(),k)) ;
    }
    static int solve(String S,String T,int k)
    {
        int n = S.length(),m = T.length() ;
        int[][] helper = new int[4][n] ;
        BitSet[] bs = new BitSet[4] ;
        for(int i=0;i<4;i++) bs[i] = new BitSet(n); 
        for(int i=0;i<n;i++)
        {
            char c = S.charAt(i) ;
            int min = Math.max(i-k,0) ;
            int max = Math.min(i+k,n-1) ;
            
            if(c=='A')
            {
                helper[0][min]++ ;
                helper[0][max]-- ;
                if(min==max) bs[0].set(i) ;
            }
            else if(c=='C')
            {
                helper[1][min]++ ;
                helper[1][max]-- ;
                if(min==max) bs[1].set(i) ;
            }
            else if(c=='G')
            {
                helper[2][min]++ ;
                helper[2][max]-- ;
                if(min==max) bs[2].set(i) ;
            }
            else if(c=='T')
            {
                helper[3][min]++ ;
                helper[3][max]-- ;
                if(min==max) bs[3].set(i) ;
            }
        }
        
        for(int i=0;i<4;i++)
        {
            int count = 0 ;
            for(int j=0;j<n;j++)
            {
                count+=helper[i][j] ;
                if(count>0 || (count==0 && helper[i][j]<0))
                    bs[i].set(j) ;
            }
        }
        BitSet ans = new BitSet(n) ;
        for(int i=0;i<n-m+1;i++) ans.set(i) ;
        for(int i=0;i<m;i++)
        {
            ans.and(bs[mapper(T.charAt(i))].get(i,n)) ;
        }
        return ans.cardinality() ;
    }
    static int mapper(char c)
    {
        if(c=='A') return 0 ;
        else if(c=='C') return 1 ;
        else if(c=='G') return 2 ;
        else return 3 ;
    }
}