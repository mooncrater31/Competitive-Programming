import java.util.* ;
import java.io.BufferedReader ;
import java.io.InputStreamReader ;

public class CandiesDistribution
{
    public static void main(String args[]) throws Exception
    {
        BufferedReader bro = new BufferedReader(new InputStreamReader(System.in)) ;
        int N = Integer.parseInt(bro.readLine()) ;
        int[] L = new int[N] ;
        int[] R = new int[N] ;
        int[] A = new int[N] ;
        String[] S = bro.readLine().split(" ") ;
        for(int i=0;i<N;i++) L[i] = Integer.parseInt(S[i]) ;
        S = bro.readLine().split(" ") ;
        int min = Integer.MAX_VALUE ;
        for(int i=0;i<N;i++)
        {
            R[i] = Integer.parseInt(S[i]) ;
            A[i] = N-(L[i]+R[i]) ;
            min = Math.min(min,A[i]) ;
        }
        HashSet<Integer> H = new HashSet<Integer>() ;
        for(int i=min;i<=N;i++) H.add(i) ;
        for(int i=0;i<N;i++)
            H.remove(A[i]) ;
        if(H.size()==0)
            {
                
                boolean answered = false ;
                int[] BIT1 = new int[N+1] ;
                int[] BIT2 = new int[N+1] ;
                for(int i=0;i<N;i++)
                {
                    update(A[i],1,BIT2) ;
                }
                for(int i=0;i<N;i++)
                {
                    update(A[i],1,BIT1) ;
                    int r = (N-i)-query(A[i],BIT2) ;
                    int l = (i+1)-query(A[i],BIT1) ;
                    update(A[i],-1,BIT2) ;
                    if(R[i]!=r || L[i]!=l )
                    {
                        System.out.println("NO");
                        answered = true ;
                        break ;
                    }
                }
                if(!answered)
                {
                    System.out.println("YES");
                    for(int i=0;i<N;i++)
                        System.out.print(A[i]+" ");
                }
            }
        else 
        {
            System.out.println("NO");
        }
            
        
    }
    static void update(int x,int val,int[] BIT)
    {
        int n = BIT.length ;
        for(;x<n;x+=x&-x) BIT[x]+=val ;
    }
    static int query(int x,int[] BIT)
    {
        int sum = 0 ;
        for(;x>0;x-=x&-x) sum += BIT[x] ;
        return sum ;
    }
}