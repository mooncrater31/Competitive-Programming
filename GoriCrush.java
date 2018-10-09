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
        List<Integer> L = new ArrayList<Integer>() ;
        S = bro.readLine().split(" ") ;
        for(int i=0;i<N;i++)
        {
            A[i] = Integer.parseInt(S[i]) ;
            L.add(A[i]) ;
        }
        String[][] queries = new String[Q][] ;
        for(int i=0;i<Q;i++)
        {
            queries[i] = bro.readLine().split(" ");
            if(queries[i][0].charAt(0)=='0')
            {
                L.add(Integer.parseInt(queries[i][2])) ;
            }
            else 
            {
                L.add(Integer.parseInt(queries[i][1])) ;
                L.add(Integer.parseInt(queries[i][2])) ;
                L.add(Integer.parseInt(queries[i][3])) ;
                L.add(Integer.parseInt(queries[i][4])) ;
            }
        }
        Collections.sort(L) ;
        
        HashMap<Integer,Integer> map = new HashMap<Integer,Integer>() ;
        int idx = 1; 
        for(int i=0;i<L.size();i++)
        {
            if(!map.containsKey(L.get(i))) 
                map.put(L.get(i),idx++) ;
        }
        int[] BIT = new int[idx] ;
        for(int i=0;i<N;i++)
            update(map.get(A[i]),1,BIT) ;
        for(int i=0;i<Q;i++)
        {
            if(queries[i][0].charAt(0)=='0')
            {
                int x = Integer.parseInt(queries[i][1])-1 ;
                int y = Integer.parseInt(queries[i][2]) ;
                update(map.get(A[x]),-1,BIT) ;
                A[x] = y ;
                update(map.get(y),1,BIT) ;
            }
            else 
            {
                int a = Integer.parseInt(queries[i][1]) ;
                int b = Integer.parseInt(queries[i][2]) ;
                int c = Integer.parseInt(queries[i][3]) ;
                int d = Integer.parseInt(queries[i][4]) ;
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