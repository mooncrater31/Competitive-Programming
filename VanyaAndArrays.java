import java.util.* ;
import java.io.BufferedReader ;
import java.io.InputStreamReader ;

public class VanyaAndArrays
{
    private static final boolean debug = false ;
    public static void main(String args[]) throws Exception
    {
        BufferedReader bro = new BufferedReader(new InputStreamReader(System.in)) ;
        String[] S = bro.readLine().split(" ") ;
        int n = Integer.parseInt(S[0]) ;
        long k = Long.parseLong(S[1]) ;
        int[] A = new int[n] ;
        S = bro.readLine().split(" ") ;
        
        
        int[] BIT = new int[n+1] ;
        for(int i=n-1;i>=0;i--)
        {
            A[i] = Integer.parseInt(S[i]) ;//Reversed
        }
        int[] map = new int[n] ;
        List<Pair> init = new ArrayList<Pair>() ;
        for(int i=0;i<n;i++)
        {
            init.add(new Pair(A[i],i)) ;
        }
        Collections.sort(init) ;
        for(int i=0;i<n;i++)
        {
            map[init.get(i).index] = i ;
        }
        long[] pairs = new long[n] ;
        long ans = 0 ;
        
        if(debug) sout("map :"+Arrays.toString(map)) ;
        for(int i=n-1;i>=0;i--)
        {
            update(map[i]+1,1,BIT) ;
            pairs[i] = (n-i-query(map[i]+1,BIT)) ;
            
            
        }
        
        Arrays.sort(pairs) ;
        if(debug)
        {
            System.out.println(Arrays.toString(pairs)) ;
        }
        List<Pair> L = new ArrayList<Pair>() ;
        for(int i=0;i<n;i++)
        {
            L.add(new Pair(pairs[i],i)) ;
        }
        
        for(int i=n-1;i>=0;i--)
        {
            long rem = k-pairs[i] ;
            //search
            int idx = Collections.binarySearch(L,new Pair(rem,Integer.MIN_VALUE)) ;
            if(idx<0) idx = -idx-1 ;
            if(debug) System.out.println(" rem :"+rem+" idx = "+idx+" el :"+(idx<n&&idx>=0?pairs[idx]:"no such element")+" Addition :"+(i-idx>0?i-idx:0)) ;
            if(idx<i)
                ans+=i-idx ;
            
        }
        System.out.println(ans) ;
        
        
    }
    static void sout(String S)
    {
        System.out.println(S) ;
    }
    static void update(int x,int val,int[] BIT)
    {
        int n = BIT.length ; 
        for(;x<n;x+=x&-x)
        {
            BIT[x]+=val ;
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
class Pair implements Comparable<Pair>
{
    long value ;
    int  index ;
    Pair(long v,int i)
    {
        this.value = v ;
        this.index = i ;
    }
    @Override
    public int compareTo(Pair p)
    {
        if(p.value<this.value)
            return 1 ;
        else if(p.value>this.value)
            return -1 ;
        else 
        {
            if(p.index<this.index)
                return 1 ;
            else if(p.index>this.index)
                return -1 ;
            else return 0 ;
        }
    }
}