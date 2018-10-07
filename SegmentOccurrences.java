import java.util.* ;
import java.io.BufferedReader ;
import java.io.InputStreamReader ;

public class SegmentOccurrences
{
    private static final boolean debug = false ;
    public static void main(String args[]) throws Exception
    {
        // String T = "aanonoaanonoaanono" ;
        // String P = "aa" ;
        // int[] b = kmpPreprocess(P.toCharArray()) ;
        // List<Integer> L = kmpSearch(P.toCharArray(),T.toCharArray(),b) ;
        // System.out.println(L.toString());
        BufferedReader bro = new BufferedReader(new InputStreamReader(System.in)) ;
        String[] S = bro.readLine().split(" ") ;
        int n = Integer.parseInt(S[0]) ;
        int m = Integer.parseInt(S[1]) ;
        int q = Integer.parseInt(S[2]) ;
        String T = bro.readLine() ;
        String P = bro.readLine() ;
        int[] b = kmpPreprocess(P.toCharArray()) ;
        List<Integer> L = kmpSearch(P.toCharArray(),T.toCharArray(),b) ;
        HashSet<Integer> H = new HashSet<Integer>(L) ;
        // int[] ppc = new int[T.length()] ;
        // int count = 0,idx = 0 ;
        // for(int i=0;i<T.length();i++)
        // {
            // if(idx<L.size() && i==L.get(idx))
            // {
                // count++ ;
                // idx++ ;
            // }
            // ppc[i] = count ;
        // }
        // if(debug)
        // {
            // System.out.println(Arrays.toString(ppc)) ;
        // }
        // for(int i=0;i<q;i++)
        // {
            // S = bro.readLine().split(" ") ;
            // int a = Integer.parseInt(S[0]) ;
            // int b2 = Integer.parseInt(S[1]) ;
            // a-- ;
            // b2-- ;
            // System.out.println(ppc[b2]-ppc[a]) ;
        // }
        for(int i=0;i<q;i++)
        {
            S = bro.readLine().split(" ") ;
            int x = Integer.parseInt(S[0]) ;
            int y = Integer.parseInt(S[1]) ;
            x--;
            y-- ;
            if(y-x+1<P.length())
                System.out.println(0) ;
            else
            {
                int idx = Collections.binarySearch(L,x) ;
                int idy = Collections.binarySearch(L,y) ;
                if(idx<0)
                    idx = -idx-1 ;
                if(idy<0)
                    idy = -idy-1 ;
                if(debug)
                {
                    // System.out.println(idx+" "+idy) ;
                }
                int count = 0 ;
                for(int j=idx;j<=idy;j++)
                {
                    if(j<L.size()&&L.get(j)+P.length()-1<=y)
                        count++ ;
                }
                System.out.println(count);
            }
            
        }
        
    }
    static int[] kmpPreprocess(char[] P)
    {
        int n  = P.length ;
        int[] b = new int[n+1];
        int i=0,j=-1 ;
        b[0] = -1 ;
        while(i<n)
        {
            while(j>=0 && P[i]!=P[j]) j = b[j] ;
            i++ ;j++ ;
            b[i] = j ;
        }
        return b ;
    }
    static List<Integer> kmpSearch(char[] P,char[] T,int[] b)
    {
        int m = P.length ;
        int n = T.length ;
        int i=0,j=0 ;
        List<Integer> L = new ArrayList<Integer>() ;
        while(i<n)
        {
            while(j>=0 && T[i]!=P[j]) j=b[j] ;
            i++;j++;
            if(j==m)
            {
                L.add(i-j) ;
                j=b[j] ;
            }
        }
        return L ;
    }
}