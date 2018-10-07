import java.util.* ;
import java.io.BufferedReader ;
import java.io.InputStreamReader ;
public class IlyaAndQueries
{
    public static void main(String args[]) throws Exception
    {
        // Scanner in = new Scanner(System.in) ;
        // String S = in.next() ;
        // int[] temp = solve(S) ;
        // int N = in.nextInt() ;
        // for(int i=0;i<N;i++)
        // {
            // int l = in.nextInt() ;
            // int r = in.nextInt() ;
            // if(l==1)
                // System.out.println(temp[r-2]) ;
            // else
                // System.out.println(temp[r-2]-temp[l-2]) ;
        // }
        BufferedReader bro = new BufferedReader(new InputStreamReader(System.in)) ;
        String S = bro.readLine() ;
        int[] temp = solve(S) ;
        int N = Integer.parseInt(bro.readLine()) ;
        for(int i=0;i<N;i++)
        {
            String[] S1 = bro.readLine().split(" ") ;
            int l = Integer.parseInt(S1[0]) ;
            int r = Integer.parseInt(S1[1]) ;
            if(l==1)
                System.out.println(temp[r-2]) ;
            else
                System.out.println(temp[r-2]-temp[l-2]) ;
            
        }
    }
    static int[] solve(String S)
    {
        int l = S.length() ;
        int[] temp = new int[l] ;
        for(int i=0;i<l-1;i++)
        {
            if(S.charAt(i)==S.charAt(i+1))
                temp[i] = 1 ;
            
        }
        
        for(int i=1;i<l;i++)
        {
            temp[i]+=temp[i-1] ;
        }
        return temp ;
    }
}