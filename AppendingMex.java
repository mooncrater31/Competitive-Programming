import java.util.* ;
import java.io.BufferedReader ;
import java.io.InputStreamReader ;

public class AppendingMex
{
    public static void main(String args[]) throws Exception

    {
        BufferedReader bro = new BufferedReader(new InputStreamReader(System.in)) ;
        int N = Integer.parseInt(bro.readLine()) ;
        String[] S = bro.readLine().split(" ") ;
        int[] A = new int[N] ;
        HashSet<Integer> H = new HashSet<Integer>() ;
        for(int i=0;i<N;i++) A[i] = Integer.parseInt(S[i]) ;
        boolean print = false ;
        if(A[0]!=0)
            System.out.println("1") ;
        else 
        {
            H.add(0) ;
            for(int i=1;i<N;i++)
            {
                
                if(!H.contains(A[i]))
                {
                    if(H.contains(A[i]-1))
                    {
                        H.add(A[i]) ;
                    }
                    else 
                    {
                        System.out.println(i+1);
                        print = true ;
                        break ;
                    }
                }
                


            }
            if(!print)
                System.out.println("-1");
        }
    }
}