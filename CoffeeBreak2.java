import java.io.BufferedReader ;
import java.io.InputStreamReader ;
import java.util.* ;

public class CoffeeBreak2
{
    public static void main(String args[]) throws Exception
    {
        BufferedReader bro = new BufferedReader(new InputStreamReader(System.in)) ;
        String[] S = bro.readLine().split(" ") ;
        int n = Integer.parseInt(S[0]) ;
        int m = Integer.parseInt(S[1]) ;
        int d = Integer.parseInt(S[2]) ;
        TreeSet<Integer> TS = new TreeSet<Integer>() ;
        int[] arr = new int[n] ;
        S = bro.readLine().split(" ") ;
        HashMap<Integer,Integer> HM = new HashMap<Integer,Integer>() ;
        for(int i=0;i<n;i++)
        {
            arr[i] = Integer.parseInt(S[i]) ;
            HM.put(arr[i],i) ;
            TS.add(arr[i]) ;
        }
        int[] ans = new int[n] ;//Will be used according to arr 
        int day=1 ;
        while(!TS.isEmpty())
        {
            int start = TS.pollFirst() ;
            ans[HM.get(start)] = day ;
            for(int i=start+d+1;i<=m;i+=d+1)
            {
                if(TS.contains(i))
                {
                    ans[HM.get(i)] = day ;
                    TS.remove(i) ;
                }
                else 
                {
                    Integer val = TS.higher(i) ;
                    if(val!=null)
                    {
                        ans[HM.get(val)] = day ;
                        TS.remove(val) ;
                        i = val ;
                    }
                }
            }
            day++ ;
        }
        System.out.println(day-1) ;
        for(int i=0;i<n;i++)
        {
            System.out.print(ans[i]+" ") ;
        }
    }
    
}