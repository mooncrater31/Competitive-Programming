import java.util.* ;
import java.io.BufferedReader ;
import java.io.InputStreamReader ;

public class MurciaSkyline
{
    private static final boolean debug = true ;
    public static void main(String args[]) throws Exception
    {
        BufferedReader bro = new BufferedReader(new InputStreamReader(System.in)) ;
        int T = Integer.parseInt(bro.readLine()) ;
        for(int t=0;t<T;t++)
        {
            int N = Integer.parseInt(bro.readLine()) ;
            int[] h = new int[N] ;
            int[] w = new int[N] ;
            String[] H = bro.readLine().trim().split(" +") ;
            String[] W = bro.readLine().trim().split(" +") ;
            for(int i=0;i<N;i++)
            {
                h[i] = Integer.parseInt(H[i]) ;
            }
            for(int i=0;i<N;i++)
            {
                w[i] = Integer.parseInt(W[i]) ;
            }
            Pair p = solve(h,w) ;
            if(p.lis>=p.minlis)
            {
                System.out.println("Case "+(t+1)+". Increasing ("+p.lis+"). Decreasing ("+p.minlis+").") ;
            }
            else
            {
                System.out.println("Case "+(t+1)+". Decreasing ("+p.minlis+"). Increasing ("+p.lis+").") ;
            }
        }
    }
    static Pair solve(int[] h,int[] w)
    {
        List<Integer> L = new ArrayList<Integer>() ;
        List<Integer> minL = new ArrayList<Integer>() ;
        // int[] dpLIS = new int[w.length] ;
        // int[] dpLDS = new int[w.length] ;
        List<Integer> dpLIS = new ArrayList<Integer>() ;
        List<Integer> dpLDS = new ArrayList<Integer>() ;
        int lis = 0 ,minlis = 0 ;
        for(int i=0;i<h.length;i++)
        {
            int pos = Collections.binarySearch(L,h[i]) ;
            List<Integer> clone = new ArrayList<Integer>(minL) ;
            int minpos = Collections.binarySearch(clone,h[i]) ;
            if(pos<0)
                pos = -(pos+1) ;
            if(pos>=L.size())
            {
                L.add(h[i]) ;
                if(L.size()>1) dpLIS.add(dpLIS.get(dpLIS.size()-1)+w[i]) ;
                else dpLIS.add(w[0]) ;
            }
            else
            {
                L.set(pos,h[i]) ;
                if(pos>0)
                {
                    if(L.get(pos-1)+w[i]>L.get(pos))
                    {
                        L.set(pos,L.get(pos-1)+w[i]) ;
                    }
                }
            }
            
            if(minpos==-1)
            {
                minL.add(h[i]) ;
                minpos = minL.size()-1 ;
                
                if(minL.size()>1) dpLDS.add(dpLDS.get(dpLDS.size()-1)+w[i]) ;
                else dpLDS.add(w[0]) ;
            }
            else
            {
                if(minpos<0)
                {
                    minpos = (-minpos-2)%minL.size() ;
                }
                minL.set(minpos,h[i]) ;
                if(minpos>0)
                {
                    if(dpLDS.get(pos-1)+w[i]>dpLDS.get(pos))
                    {
                        dpLDS.set(pos,dpLDS.get(pos-1)+w[i]) ;
                    }
                }
            }
            if(debug)
            {
                System.out.println("Input :"+h[i]) ;
                System.out.println("    L :"+L) ;
                System.out.println("    minL :"+minL) ;
                System.out.println("    dpLIS :"+dpLIS) ;
                System.out.println("    dpLDS :"+dpLDS) ;
            }
            
        }
        for(int el:dpLIS)
        {
            if(el>lis) lis = el ;
        }
        for(int el:dpLDS)
        {
            if(el>minlis) minlis = el ;
        }
        return new Pair(lis,minlis) ;
    }
}
class Pair
{
    int lis,minlis ;
    Pair(int l,int m)
    {
        this.lis = l ;
        this.minlis = m ;
    }
}