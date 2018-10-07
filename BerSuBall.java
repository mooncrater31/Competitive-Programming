import java.util.* ;
public class BerSuBall
{
    private static final boolean debug = false ;
    public static void main(String args[]) throws Exception
    {
        Scanner in = new Scanner(System.in) ;
        int b = in.nextInt() ;
        int[] B = new int[b] ;
        for(int i=0;i<b;i++)
        {
            B[i] = in.nextInt() ;
        }
        int g = in.nextInt() ;
        int[] G = new int[g] ;
        for(int i=0;i<g;i++)
        {
            G[i] = in.nextInt() ;
        }
        System.out.println(solve(B,G)) ;
        
    }
    static int solve(int[] B,int[] G)
    {
        Arrays.sort(B) ;
        int max = findMax(G) ;
        if(debug)
        {
            System.out.println("max :"+max) ;
        }
        int b = B.length ;
        int g = G.length ;
        int[] temp = new int[max+1] ;
        for(int i=0;i<g;i++) 
        {
            temp[G[i]]++ ;
        }
        int sum = 0 ;
        for(int i=0;i<b;i++)
        {
            if(B[i]-1<=max && B[i]-1>0 && temp[B[i]-1]>0)
            {
                temp[B[i]-1]-- ;
                sum++ ;
            }
            else if(B[i]<=max&&temp[B[i]]>0)
            {
                temp[B[i]]-- ;
                sum++ ;
            }
            else if(B[i]+1<=max && temp[B[i]+1]>0)
            {
                temp[B[i]+1]-- ;
                sum++ ;
            }
        }
        return sum ;
    }
    static int findMax(int[] G)
    {
        int max = 0;
        for(int el:G)
            max = max<el?el:max ;
        return max ;
    }
    
}