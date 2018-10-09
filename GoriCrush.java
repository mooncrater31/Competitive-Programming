import java.util.* ;
import java.io.BufferedReader ;
import java.io.InputStreamReader ;

public class GoriCrush
{
    public static void main(String args[]) throws Exception
    {
        BufferedReader bro = new BufferedReader(new InputStreamReader(System.in)) ;
        String[] S = bro.readLine().split(" ") ;
        int N = Integer.parseInt(S[0]) ;
        int Q = Integer.parseInt(S[1]) ;
        int K = Integer.parseInt(S[2]) ;
        
        int[] A = new int[N] ;
        // List<Integer> L = new ArrayList<Integer>() ;
        int[] L = new int[(int)1e6] ;
        S = bro.readLine().split(" ") ;
        for(int i=0;i<N;i++)
        {
            A[i] = Integer.parseInt(S[i]) ;
            L[i] = A[i] ;
        }
        int index = N ;
        int[][] queries = new int[Q][] ;
        for(int i=0;i<Q;i++)
        {
            S = bro.readLine().split(" ");
            
            if(S[0].charAt(0)=='0')
            {
                queries[i] = new int[3] ;
                queries[i][0] = 0 ;
                queries[i][1] = Integer.parseInt(S[1]) ;
                queries[i][2] = Integer.parseInt(S[2]) ;
                L[index++] = queries[i][2] ;
            }
            else 
            {
                queries[i] = new int[5] ;
                queries[i][0] = 1 ;
                queries[i][1] = Integer.parseInt(S[1]) ;
                queries[i][2] = Integer.parseInt(S[2]) ;
                queries[i][3] = Integer.parseInt(S[3]) ;
                queries[i][4] = Integer.parseInt(S[4]) ;
                L[index++] = queries[i][1] ;
                L[index++] = queries[i][2] ;
                L[index++] = queries[i][3] ;
                L[index++] = queries[i][4] ;
            }
        }
        Arrays.sort(L,0,index) ;//Takes 4 times less time.
        
        HashMap<Integer,Integer> map = new HashMap<Integer,Integer>() ;
        int idx = 1; 
        for(int i=0;i<index;i++)
        {
            if(!map.containsKey(L[i])) 
                map.put(L[i],idx++) ;
        }
        int[] BIT = new int[idx] ;
        for(int i=0;i<N;i++)
            update(map.get(A[i]),1,BIT) ;
        for(int i=0;i<Q;i++)
        {
            if(queries[i][0]==0)
            {
                int x = queries[i][1]-1 ;
                int y = queries[i][2] ;
                update(map.get(A[x]),-1,BIT) ;
                A[x] = y ;
                update(map.get(y),1,BIT) ;
            }
            else 
            {
                int a = queries[i][1] ;
                int b = queries[i][2] ;
                int c = queries[i][3] ;
                int d = queries[i][4] ;
                if(b<c) System.out.println("Do not propose") ;
                else if(d<a) System.out.println("Do not propose") ;
                else 
                {
                    int x = Math.max(a,c) ;
                    int y = Math.min(b,d) ;
                    int res = query(map.get(y),BIT) - query(map.get(x)-1,BIT) ;
                    if(res>=K)
                        System.out.println("Propose") ;
                    else System.out.println("Do not propose") ;
                }
            }
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
        for(;x>0;x-=x&-x) sum+=BIT[x] ;
        return sum ;
    }

}