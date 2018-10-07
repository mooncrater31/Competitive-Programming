import java.util.* ;
import java.io.BufferedReader ;
import java.io.InputStreamReader ;

public class CODLove
{
    private static final boolean debug = false ;
    public static void main(String args[]) throws Exception
    {
        BufferedReader bro = new BufferedReader(new InputStreamReader(System.in)) ;
        String S = bro.readLine() ;
        int n = S.length() ;
        int[][] BITS = new int[26][n+1] ;
        for(int i=0;i<n;i++)
        {
            int ch = S.charAt(i)-'a' ;
            update(i+1,1,BITS[ch]) ;
        }
        HashSet<Integer> H = new HashSet<Integer>() ;
        if(debug)
        {
            shout("BITS") ;
            for(int i=0;i<26;i++)
            {
                shout(Arrays.toString(BITS[i])) ;
            }
        }
        int Q = Integer.parseInt(bro.readLine()) ;
        for(int i=0;i<Q;i++)
        {
            String[] S1 = bro.readLine().split(" ") ;
            int k = Integer.parseInt(S1[0]) ;
            int ch = S1[1].charAt(0)-'a' ;
            int idx = modBinarySearch(k,BITS[ch]) ;
            if(idx==-1) System.out.println("Not found.") ;
            else 
            {
                update(idx,-1,BITS[ch]) ;
                if(debug) shout("Deleted :"+(idx-1)) ;
                H.add(idx-1) ;
            }
        }
        for(int i=0;i<n;i++)
        {
            System.out.print(H.contains(i)?"":S.charAt(i)) ;
        }
        
    }
    static void shout(String S)
    {
        System.out.println(S) ;
    }
    static int modBinarySearch(int k,int[] BIT)
    {
        int n = BIT.length ; 
        int start = 0, end = n-1 ;
        while(start<=end)
        {
            int mid = start+(end-start)/2 ;
            int val = query(mid,BIT) ;
            if(val>k) end = mid-1 ;
            else if(val<k) start = mid+1 ;
            else if((query(mid,BIT)-((mid>0)?query(mid-1,BIT):0))>0)
                return mid ;
            else end = mid-1; 
        }
        return -1 ;
    }
    static void update(int x,int val,int[] BIT)
    {
        int n = BIT.length ;
        for(;x<n;x+=x&-x)
        {
            BIT[x] += val ;
        }
        
    }
    static int query(int x,int[] BIT)
    {
        int sum = 0 ;
        for(;x>0;x-=x&-x)
        {
            sum+=BIT[x] ;
        }
        return sum ;
    }
}