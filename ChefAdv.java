import java.util.* ;
import java.io.BufferedReader ;
import java.io.InputStreamReader ;

class ChefAdv
{
    public static void main(String args[]) throws Exception
    {
        BufferedReader bro = new BufferedReader(new InputStreamReader(System.in)) ;
        int T = Integer.parseInt(bro.readLine()) ;
        for(int t=0;t<T;t++)
        {
            String[] S = bro.readLine().split(" ") ;
            int n = Integer.parseInt(S[0])-1 ;
            int m = Integer.parseInt(S[1])-1 ;
            int x = Integer.parseInt(S[2]) ;
            int y = Integer.parseInt(S[3]) ;
            System.out.println(solve(x,y,n,m)?"Chefirnemo":"Pofik") ;
        }
    }
    static boolean solve(int x,int y,int n,int m)
    {
        if(n%x==0 && m%y==0)//Done
        {
            return true ;
        }
        else if(n>0 && m>0 && (n-1+x)%x==0 && (m-1+y)%y==0) //Done
        {
                return true ;
        }
        
        return false ;//Done
    }
    
    static int gcd(int a,int b)
    {
        if(a==0)
        {
            return b ;
        }
        return gcd(b%a,a) ;
    }
}