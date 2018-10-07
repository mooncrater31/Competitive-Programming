import java.util.* ;
import java.io.BufferedReader ;
import java.io.InputStreamReader ;

public class ObtainingTheString
{
    private static final boolean debug = true ;
    public static void main(String args[]) throws Exception
    {
        BufferedReader bro = new BufferedReader(new InputStreamReader(System.in)) ;
        int N = Integer.parseInt(bro.readLine()) ;
        String s = bro.readLine(), t = bro.readLine() ;
        char[] s1 = s.toCharArray() ;
        char[] t1 = t.toCharArray() ;
        Arrays.sort(s1) ;
        Arrays.sort(t1) ;
        String sn = new String(s1) ;
        String tn = new String(t1) ;
        if(!sn.equals(tn))
            System.out.println("-1") ;
        else
        {
            char[] S = s.toCharArray() ;
            List<Integer> ans = new ArrayList<Integer>() ;
            for(int i=0;i<S.length;i++)
            {
                if(S[i]!=t.charAt(i))
                {
                    int pos = -1 ;
                    for(int j=i+1;j<N;j++)
                    {
                        if(t.charAt(i)==S[j])
                        {
                            pos = j ;
                            break ;
                        }
                    }
                    for(int p = pos-1;p>=i;p--)
                    {
                        char temp = S[p] ;
                        S[p] = S[p+1] ;
                        S[p+1] = temp ;
                        ans.add(p) ;
                    }
                }
                    
            }
            System.out.println(ans.size()) ;
            for(int el:ans)
                System.out.print((el+1)+" ") ;
        }            
        
    }
}