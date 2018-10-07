import java.util.* ;
import java.io.BufferedReader ;
import java.io.InputStreamReader ;

public class CoffeeBreak
{
    public static void main(String args[]) throws Exception
    {
        BufferedReader bro = new BufferedReader(new InputStreamReader(System.in)) ;
        String[] S = bro.readLine().split(" ") ;
        int n = Integer.parseInt(S[0]) ;
        int m = Integer.parseInt(S[1]) ;
        int d = Integer.parseInt(S[2]) ;
        int[] arr = new int[n] ;
        List<Integer> A = new ArrayList<Integer>(); 
        
        S = bro.readLine().split(" ") ;
        for(int i=0;i<n;i++)
        {
            A.add(Integer.parseInt(S[i])) ;
            arr[i]=A.get(A.size()-1) ;
        }
        Collections.sort(A) ;
        HashMap<Integer,Integer> HM = new HashMap<Integer,Integer>() ;
        for(int i=0;i<n;i++)
        {
            HM.put(A.get(i),i) ;
        }
        int[] ans = new int[n] ;
        // Arrays.fill(ans,-1) ;
        boolean[] deleted = new boolean[n] ;
        int day=1 ;
        // while(A.size()!=0)
        // {
            // int start = A.get(0) ;
            // ans[HM.get(start)]=day ;
            // A.remove(0) ;
            // for(int i=start+d+1;i<=m;i+=d+1)
            // {
                // int idx = Collections.binarySearch(A,i) ;
                // if(A.size()!=0 && idx>=0)
                // {
                    // ans[HM.get(A.get(idx))]=day ;
                    // A.remove(idx) ;
                // }
                // else 
                // {//negative idx value
                    // idx=-idx-1 ;
                    // if(idx<A.size()  && A.size()!=0)
                    // {
                        // ans[HM.get(A.get(idx))]=day ;
                        // i=A.get(idx) ;
                        // A.remove(idx) ;
                    // }
                // }
            // }
            // day++ ;
            
        // }
        int rem = n ;
        for(int i=0;i<n;i++)
        {
            if(!deleted[i])
            {
                int start = A.get(i) ;
                ans[HM.get(start)]=day ;
                deleted[i]=true ;
                rem-- ;
                for(int j=start+d+1;j<=m;j+=d+1)
                {
                    int idx = Collections.binarySearch(A,j) ;
                    // idx = idx<0?-idx-1:idx ;
                    
                    int temp = idx ;
                    if((temp<0 && -temp-1<n && deleted[-temp-1])||(temp>0 && deleted[temp]))
                    {
                        for(int k=j;k<n;k++)
                            if(!deleted[k])
                            {
                                j=k ;
                                idx = Collections.binarySearch(A,j) ;
                                break ;
                            }
                    }                        
                        if(idx>=0 && rem>0 && !deleted[idx])
                        {
                            ans[HM.get(A.get(idx))]=day ;
                            
                            deleted[idx] = true ;
                            rem--; 
                        }
                        else 
                        {
                            idx=-idx-1 ;
                            if(idx<n && rem>0 && !deleted[idx])
                            {
                                ans[HM.get(A.get(idx))]=day ;
                            
                                deleted[idx] = true ;
                                rem--;
                                j = A.get(idx) ;
                            }
                        }
                       
                    
                    
                }
                day++ ;
            }
        }
        System.out.println(day-1) ;//OK
        for(int i=0;i<n;i++)
        {
            System.out.print(ans[HM.get(arr[i])]+" ") ;
        }
        // System.out.println() ;
    }
}