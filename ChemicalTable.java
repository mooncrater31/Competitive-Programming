import java.util.* ;
import java.io.BufferedReader ;
import java.io.InputStreamReader ;

public class ChemicalTable
{
    static void dfs(int i,boolean[] seen,List<List<Integer>> L)
    {
        seen[i] = true ;
        for(int j=0;j<L.get(i).size();j++)
        {
            if(!seen[L.get(i).get(j)])
            {
                dfs(L.get(i).get(j),seen,L) ;
            }
        }
    }
    public static void main(String args[]) throws Exception
    {
        BufferedReader bro = new BufferedReader(new InputStreamReader(System.in)) ;
        String[] S = bro.readLine().split(" ") ;
        int n = Integer.parseInt(S[0]) ;
        int m = Integer.parseInt(S[1]) ;
        int q = Integer.parseInt(S[2]) ;
        // int[] a = new int[(int)1e6] ;
        List<List<Integer>> L = new ArrayList<List<Integer>>() ;
        for(int i=0;i<n+m;i++)
        {
            L.add(new ArrayList<Integer>()) ;
        }
        for(int i=0;i<q;i++)
        {
            S = bro.readLine().split(" ") ;
            int a = Integer.parseInt(S[0]) ;
            int b = Integer.parseInt(S[1]) ;
            a-- ;
            b-- ;
            b+=n ;
            L.get(a).add(b) ;
            L.get(b).add(a) ;
            
        }
        long cnt = 0 ;
        boolean[] seen = new boolean[n+m] ;
        for(int i=0;i<n;i++)
        {
            if(!seen[i])
            {
                dfs(i,seen,L) ;
                cnt++ ;
            }
        }
        for(int i=n;i<n+m;i++)
        {
            if(!seen[i])
            {
                dfs(i,seen,L) ;
                cnt++ ;
            }
        }
        System.out.println(cnt-1) ;
    }
    
}