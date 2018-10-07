import java.io.BufferedReader ;
import java.io.InputStreamReader ;
import java.util.* ;
public class MaximalIntersection
{
    public static void main(String args[]) throws Exception
    {
        BufferedReader bro = new BufferedReader(new InputStreamReader(System.in)) ;
        int l=0,r=Integer.MAX_VALUE,lsn=-1,rsn=-1 ;
        int n = Integer.parseInt(bro.readLine()) ;
        List<Integer> L = new ArrayList<Integer>() ;
        for(int i=0;i<n;i++)
        {
            String[] S = bro.readLine().split(" ") ;
            int a = Integer.parseInt(S[0]) ;
            L.add(a) ;
            int b = Integer.parseInt(S[1]) ;
            L.add(b) ;
            if(b!=-1 && a!=-1 && a>l && a<=r)
            {
                l=a ;
                lsn = i ;
            }
            if (b!=-1 && a!=-1 && b<r && b>=l)
            {
                r = b ;
                rsn = i ;
            }
            if(a>r){
                r = -1 ;
                rsn = i ;
                break ;
            }
            if(b<l)
            {
                b = -1 ;
                lsn = i ;
                break ;
            }   
        }
        Collections.sort(
        
    }
}