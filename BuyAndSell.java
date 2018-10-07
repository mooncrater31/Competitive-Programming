import java.util.* ;
import java.io.BufferedReader ;
import java.io.InputStreamReader ;

public class BuyAndSell
{
    public static void main(String args[]) throws Exception
    {
        BufferedReader bro = new BufferedReader(new InputStreamReader(System.in)) ;
        int n = Integer.parseInt(bro.readLine()) ;
        HashMap<String,Integer> HM = new HashMap<String,Integer>() ;
        for(int i=0;i<n;i++)
        {
            String[] S = bro.readLine().split(" ") ;
            HM.put(S[0],Integer.parseInt(S[1])) ;
        }
        int[] BIT = new int[(int)1e5+1] ;
        int Q = Integer.parseInt(bro.readLine()) ;
        HashMap<String,Integer> occ = new HashMap<String,Integer>() ;
        for(int i=0;i<Q;i++)
        {
            String[] S = bro.readLine().split(" ") ;
            char ch = S[0].charAt(0) ;
            
            if(ch=='+')
            {
                int k = HM.get(S[1]) ;

                update(k,1,BIT) ;
                if(!occ.containsKey(S[1]))
                {
                    occ.put(S[1],1) ;
                }
                else 
                {
                    occ.put(S[1],occ.get(S[1])+1) ;
                }
            }
            else if(ch=='-')
            {
                int k = HM.get(S[1]) ;
                // if(BIT[k]>0)
                //     update(k,-1,BIT) ;
                if(occ.containsKey(S[1]) && occ.get(S[1])!=0)
                {
                    update(k,-1,BIT) ;
                    occ.put(S[1],occ.get(S[1])-1) ;
                }

            }
            else 
            {
                int k = Integer.parseInt(S[1]) ;
                int rem = query((int)1e5,BIT)-query(k,BIT) ; 
                Shout(rem+"") ;
            }
        }
    }
    static void Shout(String S)
    {
        System.out.println(S) ;
    }
    static void update(int x,int val,int[] BIT)
    {
        int n = BIT.length ;
        for(;x<n;x+=x&-x)
        {
            BIT[x]+=val ;
        }
        
    }
    static int query(int x,int[] BIT)
    {
        int sum = 0 ;
        for(;x>0;x-=x&-x)
        {
            sum+=BIT[x] ;
        }
        return sum ;
    }
}