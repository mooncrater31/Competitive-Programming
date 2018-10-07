    import java.util.* ;
    import java.io.BufferedReader ;
    import java.io.InputStreamReader ;
     
    public class SignalRange
    {
        public static void main(String args[]) throws Exception
        {
            BufferedReader bro = new BufferedReader(new InputStreamReader(System.in)) ;
            int T = Integer.parseInt(bro.readLine()) ;
            for(int t=0;t<T;t++)
            {
                int N = Integer.parseInt(bro.readLine()) ;
                int[] A = new int[N] ;
                String[] S = bro.readLine().split(" ") ;
                int[] ans = new int[N] ;
                ans[0] = 1 ;
                for(int i=0;i<N;i++)
                {
                    A[i] = Integer.parseInt(S[i]) ;
                    if(i>0)
                    {
                        int idx=i-1 ;
                        ans[i]=1 ;
                        while(idx>=0 && A[idx]<=A[i])
                        {
                            ans[i]+=ans[idx];
                            idx-=ans[idx] ;
                        }
                    }
                }
                
                for(int i=0;i<N;i++)
                {
                    System.out.print(ans[i]+" ") ;
                }
                System.out.println() ;
            }
        }
        
    }